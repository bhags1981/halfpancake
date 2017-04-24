package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.core.Constants
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase13Step1ConditionTask
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase26Step1ConditionTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask

/**
 * Created by bhags on 2016/12/30.
 * Case 2-6 (s( ˜n−1,n) ∈ P(d) and d(n) ̸∈ P(s))
 */
class HPDCase2_6_ServiceV2 (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        LogData.append("[HPDCase2_6_Service] started Case 2_6V2")
        constructSubPaths()
        constructPNS()
    }

    private fun constructSubPaths() {
        /**
         * Check following condition.
         * Select path first then check d is selected or not.
         * If (1,2, ..., ˜n) ∈ {(sn−1, sn, sn−2, ..., s ˜n, s1), (sn−2,sn−1, sn, sn−3, ..., s ˜n, s1), ..., (s ˜n+1, s ˜n+2, ..., sn, s ˜n, s1),(s ˜n, s ˜n+1, ..., sn, s1), (s1, s ˜n+1, s ˜n+2, ..., sn)}
         */

        val (isIncluded, positionL) = CheckCase26Step1ConditionTask(_sourceNode, _destinationNode).executeTask().getResult()
        val n = _currentGraph.getDimension()
        val _n = _sourceNode.getHalfPosition()


        //Create Path from s to d directly
        if (isIncluded) {


            //Step 11) Select sub path s˜ n−1: s → s( ˜ n−1) → s( ˜ n−1;n) def = a˜ n−1.
            var tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(_n - 1, n)
            _disjointPaths.add(tmpPath)


            /*Step 8) Assume that (1;2; : : : ; ˜ n) = (sn−l+1; sn−l+2; : : : ; sn;
            sn−l ; sn−l−1; : : : ; s ˜ n; s1). Select path rl : s → s(l) →
            s(l;n)→s(l;n;l)→s(l;n;l;n)→s(l;n;l;n;l)→s(l;n;l;n;l; ˜ n−1)→
            s(l;n;l;n;l; ˜ n−1;n)(= d):*/

            //if _n == positionL need more steps.
            //1789a23456 (_n) -> 2a98713456 (n) -> 65431789a2 {ここで2が後ろに行くのでこのままでは789aにはいけない。}
            //_n,n,_n-1,n,
            //d^n ->
            if(positionL == _n  ){
                tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(positionL, positionL - 1, 2, n)
                var tmpPath2 = UniquePath(_destinationNode).prependNodesByIndexes(n, positionL - 2, 2)
                tmpPath = PancakeSimpleRoutingTask(tmpPath as UniquePath, tmpPath2.getFirstNode().getNeighborByIndex(n)).executeTask().getResult()
                tmpPath.appendPath(tmpPath2)
                _disjointPaths.add(tmpPath)
            }else if(positionL == _n - 1){

            }else {
                tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(positionL, n, positionL, n, positionL, _n - 1, n)
                _disjointPaths.add(tmpPath)
            }




            //Step 9) Select s1: s → s(n) → s(n; ˜ n−1) → s(n; ˜ n−1;n) → s(n; ˜ n−1;n; ˜ n−1) →s(n; ˜ n−1;n; ˜ n−1;n) def = a1.
            //If positionL == _n - 1 a1 = d
            tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(n, _n - 1, n, _n - 1, n)
            _disjointPaths.add(tmpPath)

            /** Step 10) Select ( ˜ n − 4) sub paths si: s → s(i) →
             * s(i;n) →s(i;n;i) →s(i;n;i;n) →s(i;n;i;n;i) →s(i;n;i;n;i; ˜ n−1) →
             * s(i;n;i;n;i; ˜ n−1;n) def = ai (2 ≤ i ̸= l ≤ ˜ n−2).
             */
            for (i in 2.._n - 2) {
                if (i == positionL)
                    continue;
                var tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(i, n, i, n, i, _n - 1, n)
                _disjointPaths.add(tmpPath)
            }


            /**
             * Step 12) Select sub path s˜ n: s → s( ˜ n) → s( ˜ n;n) → s( ˜ n;n;
             * ˜ n−1) → s( ˜ n;n; ˜ n−1;n) → s( ˜ n;n; ˜ n−1;n; ˜ n−1) → s( ˜ n;n; ˜ n−1;n; ˜ n−1;
             * ˜ n−2) →s( ˜ n;n; ˜ n−1;n; ˜ n−1; ˜ n−2;n) def = a˜ n.
             */
            if(positionL != _n) {
                tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(_n, n, _n - 1, n, _n - 1, _n - 2, n)
                _disjointPaths.add(tmpPath)
            }

            /**
             *
             * Step 13) Apply Algorithm PNS in P(d) to obtain ( ˜ n−1)
             * */
        } else {





            var symbol_n = _destinationNode.getCharForPaperIndex(_n)


            //Step 4)
            //Select sub path s˜n−1: s → s( ˜n−1) → s( ˜n−1,n) def = a˜n−1.
            var tmpPath =  UniquePath(_sourceNode).appendNodesByIndexes(_n - 1, n)
            _disjointPaths.add(tmpPath)


            /**
             * Step 1
             * Create path from s to d without use P(d)
             * Find position of _n
             */
            var positionFor_n = _sourceNode.getPaperIndexForChar(symbol_n)
            if(positionFor_n < 1 ){
                throw Exception("Failed to find symbol for $symbol_n" )
            }

            /**
             * If s1 = ˜n, select path r1: s → s(n) ; a → a(n) → a(n; ˜ n−1)(= d(n))→d
             * where a = (1, 2, ...,  ˜n−1, n, n− 1, ..., ˜n).
             */
            if(positionFor_n == 1){
                tmpPath =  UniquePath(_sourceNode).appendNodesByIndexes(n)
                var tmpPath2 = UniquePath(_destinationNode).prependNodesByIndexes(n,_n-1)
                tmpPath = PancakeSimpleRoutingTask(tmpPath as UniquePath, tmpPath2.getFirstNode().getNeighborByIndex(n)).executeTask().getResult()
                tmpPath.appendPath(tmpPath2)
                _disjointPaths.add(tmpPath)

            }

            /**
             * Otherwise, assume that sl = ˜ n ( ˜ n≤l ≤n), and select path r1: s→s(n) →s(n;n−l+1) →s(n;n−l+1; ˜ n) →
             * s(n;n−l+1; ˜ n) → s(n;n−l+1; ˜ n; ˜ n−1) → s(n;n−l+1; ˜ n; ˜ n−1;n) →
             * s(n;n−l+1; ˜ n; ˜ n−1;n; ˜ n)→s(n;n−l+1; ˜ n; ˜ n−1;n; ˜ n;n);a→a(n)→
             * a(n;2) →a(n;2; ˜ n−1) →a(n;2; ˜ n−1; ˜ n−2)(= d(n))→d where
             * a = (1, 2, ... , _n−1, _n+1,..., n−1, _n,n).
             */
            else{

                //以下の重複経路を防止。
                //123456789abc, cba987654321, 6789abc54321, ba9876c54321, 12345c6789ab, 6c54321789ab, ba98712345c6, 6c54321789ab, c654321789ab, 23456c1789ab, 65432c1789ab, ba9871c23456]
                if(positionFor_n == n ){
                    tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(n,_n,_n-1,n)
                    var tmpPath2 = UniquePath(_destinationNode)
                    tmpPath = PancakeSimpleRoutingTask(tmpPath as UniquePath, tmpPath2.getFirstNode().getNeighborByIndex(n)).executeTask().getResult()
                    tmpPath.appendPath(tmpPath2)
                }else{
                    tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(n,n-positionFor_n+1,_n,_n-1,n,_n,n)
                    var tmpPath2 = UniquePath(_destinationNode).prependNodesByIndexes(n,_n-2,_n-1,2)
                    tmpPath = PancakeSimpleRoutingTask(tmpPath as UniquePath, tmpPath2.getFirstNode().getNeighborByIndex(n)).executeTask().getResult()
                    tmpPath.appendPath(tmpPath2)
                }
                _disjointPaths.add(tmpPath)

            }


            /**
             * Select ( ˜n−3) sub paths si: s → s(i) → s(i;n) → s(i;n;i) → s(i;n;i;n) → s(i;n;i;n;i) → s(i;n;i;n;i; ˜ n−1) → s(i;n;i;n; i; ˜ n−1;n) def = ai (2 ≤ i ≤ n−2).
             *
             */
            for (i in 2.._n - 2) {
                 tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(i, n, i, n, i, _n - 1, n)
                _disjointPaths.add(tmpPath)
            }

            /**
             * Step 5) Select path s˜ n: s → s( ˜ n) → s( ˜ n;n) → s( ˜ n;n; ˜ n−1) →
             *  s( ˜ n;n; ˜ n−1;n) → s( ˜ n;n; ˜ n−1;n; ˜ n−1) → s( ˜ n;n; ˜ n−1;n; ˜ n−1; ˜ n−2) →
             * s( ˜ n;n; ˜ n−1;n; ˜ n−1; ˜ n−2;n) def = a˜ n.
             */
            tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(_n,n,_n-2,n,_n-1,_n-2,n)
            _disjointPaths.add(tmpPath)
        }
    }

}
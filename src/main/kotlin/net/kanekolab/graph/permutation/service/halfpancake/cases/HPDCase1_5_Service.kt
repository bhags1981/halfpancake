package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.core.Constants
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase14Step1ConditionTask
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase15Step1ConditionTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask

/**
 * Created by bhags on 2016/12/30.
 * Case 1-5 (s( ˜n,n) ∈ P(d) and s( ˜n,n) ̸= d)
 */
class HPDCase1_5_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    private var _positionL = -1
    override fun constructDisjointPaths() {
        LogData.append("[HPDCase1_5_Service] started Case 1_5")
        step1(_sourceNode,_destinationNode)
    }


    /**
     * If (1,2, ... , ˜ n − 1) ∈ {(sn−1, sn, sn−2 ... , s ˜ n+1), (sn−2, sn−1, sn, sn−3, ... , s ˜ n+1), ... , (s ˜ n+1, s ˜ n+2, ... , sn)}, go to Step 7.
     */
    private fun step1(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode){
        var (isExist,positionL) = CheckCase15Step1ConditionTask(sourceNode, destinationNode).executeTask().getResult()
        LogData.append("[Step 1] Is exist position L ? {$positionL} (-1 is false) ")
        if(isExist == true){
            _positionL = positionL
            step7(sourceNode,positionL)
        }else{
            step2(sourceNode,destinationNode)
        }
    }

    /**
     * Step 2)
     * If s1 = ˜n, select path r1: s → s(n) , a → a(n) →a(n, ˜ n)(= d(n)) → d where a = (1, ...  , ˜ n − 1,n,n −1, ...  , ˜ n).
     * Otherwise, assume that sl = ˜n ( ˜n<l ≤ n), 
     * and select path r1: s→s(n) →s(n, ˜ n−l+1) →s(n, ˜ n−l+1, ˜ n) → s(n, ˜ n−l+1, ˜ n,n) → s(n, ˜ n−l+1, ˜ n,n, ˜ n) → s(n, ˜ n−l+1, ˜ n,n, ˜ n,n) ,
     * b → b(n) → b(n, ˜ n) → b(n, ˜ n, ˜ n−1) → b(n, ˜ n, ˜ n−1, ˜ n−2) → b(n, ˜ n, ˜ n−1, ˜ n−2, ˜ n−1)(= d(n))→d where b = (1,2, ... , ˜ n− 1,n, ˜ n+1, ... ,n−1, ˜ n).
     */
    private fun step2(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()
        var _nthChar:Char = destinationNode.getId().get(_n-1)
        var l = sourceNode.getId().indexOf(_nthChar) + 1
        var pathFromSource = UniquePath(sourceNode)

        if(l == 1){
            //If s1 = ˜ n, select path r1: s → s(n) , a → a(n) → a(n, ˜ n)(= d(n)) → d where a = (1, ... , ˜ n − 1;n;n − 1, ... , ˜ n).

            //s(n)
            pathFromSource.append(n)


            var pathFromDestination =
                    UniquePath(destinationNode)
                            .prepend(n)
                            .prepend(_n)

            var nodeA = pathFromDestination.getFirstNode().getNeighborByIndex(n)
            //s(n) ~> a
            pathFromSource = PancakeSimpleRoutingTask(pathFromSource,nodeA).executeTask().getResult()
            pathFromSource.appendPath(pathFromDestination)


        }else{
            /**
            // This Make Path long.
            s→s(n) →s(n, ˜ n−l+1) →s(n, ˜ n−l+1, ˜ n) →  s(n, ˜ n−l+1, ˜ n;n) → s(n, ˜ n−l+1, ˜ n;n, ˜ n) → s(n, ˜ n−l+1, ˜ n,n, ˜ n,n),
            b → b(n) → b(n, ˜ n) → b(n, ˜ n, ˜ n−1) → b(n, ˜ n, ˜ n−1, ˜ n−2) →
            b(n, ˜ n, ˜ n−1, ˜ n−2, ˜ n−1)(= d(n))→d where b = (1;2, ... , ˜ n−1,n, ˜ n+1, ... ,n−1, ˜ n)
            */
            //s(n)
            pathFromSource.append(n)

            //s(n,n-l+1)
            var pos = (n-l) + 1

            if(pos > 1) {
                pathFromSource.append(pos)
            }

            //s(n,~n-l+1,~n)

            pathFromSource
                    .append(_n)
                    .append(n)
                    .append(_n)
                    .append(n)


            var pathFromDestination =
                    UniquePath(destinationNode)
                            .prepend(n)
                            .prepend(_n-1)
                            .prepend(_n-2)
                            .prepend(_n-1)
                            .prepend(_n)

            var nodeB = pathFromDestination.getFirstNode().getNeighborByIndex(n)

            //s(n,~n-l+1,~n,n) ~> b
            pathFromSource = PancakeSimpleRoutingTask(pathFromSource,nodeB).executeTask().getResult()
            pathFromSource.appendPath(pathFromDestination)


        }
        _disjointPaths.add(pathFromSource)
        LogData.append("[Step 2] ~n position is ${l} Selected Path ${pathFromSource.getList()}")

        step3(sourceNode,destinationNode)

    }//987654321


    /**
     *
     * Step 3) Select ( ˜n − 2) sub paths σi: s → s(i) →s(i,n) → s(i,n,i) → s(i,n,i,n) → s(i,n,i,n,i) → s(i,n,i,n,i,n˜) →s(i,n,i,n,i,n˜,n)def = ai (2 ≤ i ≤ n˜−1).
     *
     */
    private fun step3(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()
        for(i in 2.._n-1){

            var tmpPath =
                    UniquePath(sourceNode)
                            .append(i)
                            .append(n)
                            .append(i)
                            .append(n)
                            .append(_n)
                            .append(n)
            _disjointPaths.add(tmpPath)
            LogData.append("[Step 3] Selected Path[$i] ${tmpPath.getList()}")

        }
        step4(sourceNode)
    }

    /**
     * Select path σn˜: s → s(n˜) → s(n˜,n) def = an˜
     */
    private fun step4(sourceNode: HalfPancakeNode){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()
        var tmpPath =
                UniquePath(sourceNode)
                .append(_n)
                .append(n)

        _disjointPaths.add(tmpPath)
        LogData.append("[Step 4] Selected Path ${tmpPath.getList()}")
        step5()
    }


    /**
     * Step 5) Apply Algorithm PNS in P(d) to obtain ( ˜n − 1) disjoint sub paths τi: ai ~> d (2 ≤ i ≤ n˜).
     */
    private fun step5(){
        for(index in 0.._disjointPaths.size - 1){
            var path = _disjointPaths.get(index)
            if(!path.getLastNode().getId().equals(_destinationNode.getId()))
                path.appendOmittedPathWithDescription(_destinationNode, Constants.pathPNS)
            LogData.append("[Step 5] Path[${index+1}] : ${_disjointPaths.get(index)}")
        }
        step6()
    }

    /**
     * Step 6) Connect σi and τi to obtain τi (2 ≤ i ≤ n˜). Terminate.
     */
    private fun step6(){
        LogData.append("[Step 6] Finished construct disjoint paths from {${_sourceNode.getId()}} to {${_destinationNode.getId()}} - Terminate.")
    }


    /**
     * Assume that (1,2,...,n˜−1) = (sn−l+1,...,sn,sn−l,...,sn˜+1)
     * Select path ρl: s → s(l) → s(l,n) → s(l,n,l) →s(l,n,l,n) → s(l,n,l,n,l) → s(l,n,l,n,l,n˜) → s(l,n,l,n,l,n˜,n)(= d).
     *
     */
    private fun step7(sourceNode: HalfPancakeNode, positionL : Int){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()
        var tmpPath =
                UniquePath(sourceNode)
                        .append(positionL)
                        .append(n)
                        .append(positionL)
                        .append(n)
                        .append(positionL)
                        .append(_n)
                        .append(n)

        _disjointPaths.add(tmpPath)
        LogData.append("[Step 7] Selected path : ${tmpPath.getList()}")
        step8(sourceNode,positionL)
    }

    /**
     *  Select σ1: s → s(n) → s(n,n˜) → s(n,n˜,n) → s(n,n˜,n,n˜) → s(n,n˜,n,n˜,n)
     *  → s(n,n˜,n,n˜,n,n˜) def = b → b(n) → b(n,n˜) →b(n,n˜,n˜−1) → b(n,n˜,n˜−1,n˜−2) → b(n,n˜,n˜−1,n˜−2,n˜−1) → b(n,n˜,n˜−1,n˜−2,n˜−1,n)def = a1.
     */
    private fun step8(sourceNode: HalfPancakeNode, positionL: Int){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()
        var tmpPath =
                UniquePath(sourceNode)
                        .append(n)
                        .append(_n)
                        .append(n)
                        .append(_n)
                        .append(n)
                        .append(_n) // =b
                        .append(n)
                        .append(_n)
                        .append(_n-1)
                        .append(_n-2)
                        .append(_n-1)
                        .append(n)

        _disjointPaths.add(tmpPath)
        LogData.append("[Step 8] Selected path : ${tmpPath.getList()}")
        step9(sourceNode,positionL)
    }

    /**
     * Step 9) Select ( ˜n − 2) sub paths
     * σi: s → s(i) →s(i,n) → s(i,n,i) → s(i,n,i,n)
     * → s(i,n,i,n,i) → s(i,n,i,n,i,n˜) →s(i,n,i,n,i,n˜,n)def = ai (2 ≤ i != l ≤ n˜ −1)
     *
     */
    private fun step9(sourceNode: HalfPancakeNode,  positionL: Int){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()
        for(i in 2.._n - 1){
            if(i == positionL) continue
            var path = UniquePath(sourceNode)
                    .append(i)
                    .append(n)
                    .append(i)
                    .append(n)
                    .append(i)
                    .append(_n)
                    .append(n)
            _disjointPaths.add(path)
            LogData.append("[Step 9] Path[$i] : ${path.getList()}")
        }
        step10(sourceNode)
    }

    /**
     * Step 10) Select path σn˜: s → s(n˜) → s(n˜,n)def = an˜
     */
    private fun step10(sourceNode: HalfPancakeNode){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()
        var path = UniquePath(sourceNode).append(_n).append(n)
        _disjointPaths.add(path)
        LogData.append("[Step 10] Selected path : ${path.getList()}")
        step11()
    }

    /**
     * Step 11) Apply Algorithm PNS in P(d) to obtain ( ˜n − 1)disjoint sub paths τi: ai ~> d (1 ≤ i != l ≤ n˜).
     */
    private fun step11(){
        for(index in 0.._disjointPaths.size - 1){
            var path = _disjointPaths.get(index)
            if(!path.getLastNode().getId().equals(_destinationNode.getId()))
                path.appendOmittedPathWithDescription(_destinationNode,Constants.pathPNS)
            LogData.append("[Step 11] Path[${index+1}] : ${_disjointPaths.get(index)}")
        }
        step12()
    }

    private fun step12(){
        LogData.append("[Step 12] Finished construct disjoint paths from {${_sourceNode.getId()}} to {${_destinationNode.getId()}} - Terminate.")
    }
}
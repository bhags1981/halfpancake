package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.core.Constants
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase13Step1ConditionTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask

/**
 * Created by bhags on 2016/12/30.
 * Case 2-6 (s( ˜n−1,n) ∈ P(d) and d(n) ̸∈ P(s))
 */
class HPDCase2_6_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        LogData.append("[HPDCase2_5_Service] Start construct disjoint paths between {${_sourceNode.getId()}} and {${_destinationNode.getId()}} by Case 2-4 service.")
        constructSubPaths()
        constructPNS()
    }

    private fun constructSubPaths(){
        /**
         * Do not check following condition.
         * Select path first then check d is selected or not.
         * If (1,2, ..., ˜n) ∈ {(sn−1, sn, sn−2, ..., s ˜n, s1), (sn−2,sn−1, sn, sn−3, ..., s ˜n, s1), ..., (s ˜n+1, s ˜n+2, ..., sn, s ˜n, s1),(s ˜n, s ˜n+1, ..., sn, s1), (s1, s ˜n+1, s ˜n+2, ..., sn)}
         */
        val n = _currentGraph.getDimension()
        val _n = _sourceNode.getHalfPosition()

        /**
         * Select _n-3 paths with checking d is selected or not
         * Select( ˜n−3) sub paths si: s → s(i) → s(i,n) →s(i,n,i) → s(i,n,i,n) → s(i,n,i,n,i) → s(i,n,i,n,i, ˜n−1) → s(i,n,i,n,i, ˜n−1,n) def = ai
         */
        var isDestinationSelected = false
        for (i in 2.._n - 2 ){
            var tmpPath =
                    UniquePath(_sourceNode).appendNodesByIndexes(i,n,i,n,i,_n-1,n)
            isDestinationSelected = if(!isDestinationSelected)tmpPath.getLastNode().equalId(_destinationNode) else isDestinationSelected
            _disjointPaths.add(tmpPath)
        }


        //Select sub path s˜n: s → s( ˜n) → s( ˜n,n) → s( ˜n,n, ˜n−1) → s( ˜n,n, ˜n−1,n) → s( ˜n,n, ˜n−1,n, ˜n−1) → s( ˜n,n, ˜n−1,n, ˜n−1, ˜n−2) →s( ˜n,n, ˜n−1,n, ˜n−1, ˜n−2,n) def = a˜n.
        var tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(_n,n,_n-1,n,_n-1,_n-2,n);
        isDestinationSelected = if(!isDestinationSelected)tmpPath.getLastNode().equalId(_destinationNode) else isDestinationSelected

        _disjointPaths.add(tmpPath)

        //Select sub path s˜n−1: s → s( ˜n−1) → s( ˜n−1,n) def = a˜n−1.
        _disjointPaths.add(UniquePath(_sourceNode).appendNodesByIndexes(_n-1,n))
        /**
         * Check D is selected and find last path
         */
        if(isDestinationSelected){
            //Select s1: s → s(n) → s(n, ˜n−1) → s(n, ˜n−1,n) → s(n, ˜n−1,n, ˜n−1) →s(n, ˜n−1,n, ˜n−1,n) def = a1.
            _disjointPaths.add(
                    UniquePath(_sourceNode)
                            .appendNodesByIndexes(n,_n-1,n,_n-1,n)
            )
            return
        }


        //Find last path which connected to d with out using P(d)
        //Find _n position on s
        var position = 0
        run breaker@{ _sourceNode.getId().forEach { element ->
            position ++
            if(element.equals(_destinationNode.getHalfElement())) return@breaker //Break
            }
        }


        if(position > 1 && position < _n)
            throw Exception("Failed to find position for _n. Current position is {$position}" )

        //If s1 = ˜n, select path r1: s → s(n) , a → a(n) → a(n, ˜n−1)(= d(n))→d where a = (1,2, ... , ˜n−1,n,n− 1, ... , ˜n).
        if(position == 1){

            //s -> s(n)
            var pathFromSrc = UniquePath(_sourceNode).append(n) as UniquePath

            //d -> d(n) -> d(n,_n-1)
            var pathFromDst = UniquePath(_destinationNode).prependNodesByIndexes(n,_n-1)

            //s(n) ~> a
            PancakeSimpleRoutingTask(pathFromSrc,pathFromDst.getFirstNode().getNeighborByIndex(n)).executeTask()

            //Connect all path
            pathFromSrc.appendPath(pathFromDst)

            _disjointPaths.add(pathFromSrc)

            return
        }


        /**
         *  Otherwise, assume that sl = ˜n ( ˜n≤l ≤n), and
         *   select path r1: s→s(n) →s(n,n−l+1) →s(n,n−l+1, ˜n) →
         *   s(n,n−l+1, ˜n) → s(n,n−l+1, ˜n, ˜n−1) → s(n,n−l+1, ˜n, ˜n−1,n) →
         *   s(n,n−l+1, ˜n, ˜n−1,n, ˜n)→s(n,n−l+1, ˜n, ˜n−1,n, ˜n,n),a→a(n)→
         *   a(n,2) →a(n,2, ˜n−1) →a(n,2, ˜n−1, ˜n−2)(= d(n))→d where
         *   a = (1,2, ..., ˜n−1, ˜n+1, ...,n−1, ˜n,n)
         */

        var pathFromSrc = UniquePath(_sourceNode).appendNodesByIndexes(n) as UniquePath
        if(position < n ) {


            pathFromSrc.appendNodesByIndexes(n - position + 1, _n, _n - 1, n)
            //Check final dest placed in same sub graph of d(n)
            if(!pathFromSrc.getLastNode().getSuffixForSubGraph().equals(_destinationNode.getIthPrefixReversalNeighbor(n).getSuffixForSubGraph())){
                pathFromSrc.appendNodesByIndexes(_n,n)
            }

        }else{
            pathFromSrc.appendNodesByIndexes(_n,_n-1,n,_n,n)
        }


        var pathFromDst =
                UniquePath(_destinationNode)
                        .prependNodesByIndexes(n,_n-2,_n-1,2)
        PancakeSimpleRoutingTask(pathFromSrc, pathFromDst.getFirstNode().getNeighborByIndex(n)).executeTask()
        pathFromSrc.appendPath(pathFromDst)
        _disjointPaths.add(pathFromSrc)





    }

}
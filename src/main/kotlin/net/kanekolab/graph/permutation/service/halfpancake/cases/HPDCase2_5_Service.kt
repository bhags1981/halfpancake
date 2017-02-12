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
 * Case 2-5 (∃k(<˜n−1) such that s(k,n) ∈ P(d) and d(n) ̸∈ P(s))
 */
class HPDCase2_5_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode,positionK:Int) : HPDCaseService(graph,sourceNode,destinationNode){
    private var _positionK = positionK
    override fun constructDisjointPaths() {
        LogData.append("[HPDCase2_5_Service] started Case 2_5")

        /**
         * Do not check following condition.
         * Select path first then check d is selected or not.
         * {(s ˜n−1 , s ˜n , ...  , sn) , 
         * (s ˜n , s ˜n+1 , ...  , sn,s ˜n−1) , 
         * (s ˜n+1 , s ˜n+2 , ...  , sn , s ˜n , s ˜n−1) , ...  , 
         * (s ˜n+k−4 , ...  , sn,s ˜n+k−5 , ...  , s ˜n−1) , (s ˜n+k−2 , ...  , sn , s ˜n+k−3 , ...  , s ˜n−1),...  , (sn−1 , sn , sn−2 , sn−3 , ...  , s ˜n , s ˜n−1)},
         */
        val n = _currentGraph.getDimension()
        val _n = _sourceNode.getHalfPosition()

        /**
         * Select _n -1 paths with check d is selected or not
         * Select (˜n−2) paths si: s → s(i) → s(i,n) → s(i,n,˜n−i+2)→s(i,n,˜n−i+2,n)→s(i,n,˜n−i+2,n,i)→s(i,n,˜n−i+2,n,i,k)→s(i,n,˜n−i+2,n,i,k,n)
         */
        var isDestinationSelected = false
        for (i in 2.._n){
            var tmpPath =
                    UniquePath(_sourceNode).appendNodesByIndexes(i,n)
            if(i != _positionK)
                tmpPath.appendNodesByIndexes(_n-i+2,n,i,_positionK,n)

            isDestinationSelected = if(!isDestinationSelected)tmpPath.getLastNode().equalId(_destinationNode) else isDestinationSelected
            _disjointPaths.add(tmpPath)
        }

        /**
         * Check D is selected
         */

        if(!isDestinationSelected){
            /**
            Create an s → s(n) ~> an → a(n) n → a(n,k) n (= d(n))→d where an
             */

            //sn
            var pathFromSrc = UniquePath(_sourceNode).append(n) as UniquePath
            //d->d(n)->d(n,k)
            var pathFromDest = UniquePath(_destinationNode).prependNodesByIndexes(n,_positionK)
            //s(n) ~> an
            PancakeSimpleRoutingTask(pathFromSrc,pathFromDest.getFirstNode().getNeighborByIndex(n)).executeTask()
            //Connect all path
            pathFromSrc.appendPath(pathFromDest)

            _disjointPaths.add(
                    pathFromSrc
            )

        }else{

            //Select s1: s→s(n) →s(n,˜n−k+2) →s(n,˜n−k+2,n) → s(n,˜n−k+2,n,k) →s(n,˜n−k+2,n,k,n) def = a1.
            _disjointPaths.add(
                    UniquePath(_sourceNode)
                            .appendNodesByIndexes(n,_n-_positionK+2,n,_positionK,n)
            )



        }
        constructPNS()
    }



}
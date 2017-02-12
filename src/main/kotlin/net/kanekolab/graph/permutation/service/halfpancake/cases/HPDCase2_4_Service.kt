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
 * Case 2-4 (s(n) ∈ P(d), d(n) ̸∈ P(s))
 */
class HPDCase2_4_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        LogData.append("[HPDCase2_4_Service] started Case 2_4")

        /**
         * Do not check following condition.
         * Select path first then check d is selected or not.
         * If (1,2, ...,n) ∈ {(s ˜n−1, s ˜n, s ˜n+1, ..., sn−1, sn), (s ˜n,s ˜n+1, ..., sn−1, sn, s ˜n−1), ...,
         * (sn−2, sn−1, sn, sn−3, sn−4,..., s ˜n+1, s ˜n, s ˜n−1), (sn−1, sn, sn−2, ..., s ˜n−1)}, go to Step7.
         */
        LogData.append("Started Step 1")
        val n = _currentGraph.getDimension()
        val _n = _sourceNode.getHalfPosition()

        _disjointPaths.add(
                //Select a1
                UniquePath(_sourceNode).append(n)
        )

        /**
         * Select _n -1 paths with check d is selected or not
         * Select (˜n−2) paths si: s → s(i) → s(i,n) → s(i,n,˜n−i+2)→s(i,n,˜n−i+2,n)→s(i,n,˜n−i+2,n,i)→s(i,n,˜n−i+2,n,i,n)
         */
        var isDestinationSelected = false
        for (i in 2.._n){
            var tmpPath =
                    UniquePath(_sourceNode).appendNodesByIndexes(i,n,_n-i+2,n,i,n)
            isDestinationSelected = if(!isDestinationSelected)tmpPath.getLastNode().equalId(_destinationNode) else isDestinationSelected
            _disjointPaths.add(tmpPath)
        }

        /**
         * Check D is selected
         */

        if(!isDestinationSelected){
            //Remove second path and create new path.
            var tmpPath = _disjointPaths[1]
            tmpPath.getList().forEach{ element->UsedNodeIds.removeNodeId(element)}
            _disjointPaths.removeAt(1)

            //path from s
            var pathFromSource = UniquePath(_sourceNode).appendNodesByIndexes(2,n) as UniquePath

            //path from d
            var pathFromDest = UniquePath(_destinationNode).prependNodesByIndexes(n,2)

            //s ~> a
            PancakeSimpleRoutingTask(pathFromSource,pathFromDest.getFirstNode().getNeighborByIndex(n)).executeTask()

            //connect all path
            pathFromSource.appendPath(pathFromDest)

            _disjointPaths.add(pathFromSource)
        }

        constructPNS()
    }



}
package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode

/**
 * Created by bhags on 2016/12/30.
 */
class HPDCase2_2_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        LogData.append("[HPDCase2_2_Service] Started Case 2_2 ")
        var n = _sourceNode.getLength()
        var _n = _sourceNode.getHalfPosition()

        /**
         * Step 1) Select path r1: s→d of length 1.
         *
         * */
        _disjointPaths.add(UniquePath(_sourceNode).append(n))

        /**
            Step 2) Select (˜n−1) paths ri: s → s(i) → s(i,n) → s(i,n,˜n−i+2)→s(i,n,˜n−i+2,n)
            →s(i,n,˜n−i+2,n,i)→s(i,n,˜n−i+2,n,i,n)→s(i,n,˜n−i+2,n,i,n,˜n−i+2)(2 ≤ i ≤ ˜n).
         */

        // i = j + 1
        for(i in 2.._n){
            _disjointPaths.add(
                    UniquePath(_sourceNode)
                        .appendNodesByIndexes(i,n,_n-i+2,n,i,n,_n-i+2)
            )
        }


    }
}
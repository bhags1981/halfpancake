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
class HPDCase1_2_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        LogData.append("[HPDCase1_2_Service] Started Case 1_2 ")
        /**
         * Step 1) Select path r1: s→d of length 1.
         *
         * */
        _disjointPaths.add(UniquePath(_sourceNode).appendNode(_sourceNode.getNthNeighbor(_sourceNode.getDegree())))

        /**
            Step 2) Select (˜n−1) paths ri: s → s(i) → s(i,n) → s(i,n,˜n−i+2)→s(i,n,˜n−i+2,n)
            →s(i,n,˜n−i+2,n,i)→s(i,n,˜n−i+2,n,i,n)→s(i,n,˜n−i+2,n,i,n,˜n−i+2)(2 ≤ i ≤ ˜n).
         */
        var n = _sourceNode.getDegree()
        var _n = n -1

        // i = j + 1
        for(j in 1.. _n){
            var tmpPath = UniquePath(_sourceNode)
            // s(i)
            var intermediateNode  = _sourceNode.getNthNeighbor(j)
            tmpPath.appendNode(intermediateNode)

            //s(i,n)
            intermediateNode = intermediateNode.getNthNeighbor(n)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,˜n−i+2)
            intermediateNode = intermediateNode.getNthNeighbor(_n - (j+1) + 2)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,˜n−i+2,n)
            intermediateNode = intermediateNode.getNthNeighbor(n)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,˜n−i+2,n,i)
            intermediateNode = intermediateNode.getNthNeighbor(j)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,˜n−i+2,n,i,n)
            intermediateNode = intermediateNode.getNthNeighbor(n)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,˜n−i+2,n,i,n,˜n−i+2)
            intermediateNode = intermediateNode.getNthNeighbor(_n-(j+1)+2)
            tmpPath.appendNode(intermediateNode)

            _disjointPaths.add(tmpPath)
        }


    }
}
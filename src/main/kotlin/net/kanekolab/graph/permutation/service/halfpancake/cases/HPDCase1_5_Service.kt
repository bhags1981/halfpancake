package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode

/**
 * Created by bhags on 2016/12/30.
 * Case 1-5 (s( ˜n,n) ∈ P(d) and s( ˜n,n) ̸= d)
 */
class HPDCase1_5_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        throw UnsupportedOperationException()
    }
}
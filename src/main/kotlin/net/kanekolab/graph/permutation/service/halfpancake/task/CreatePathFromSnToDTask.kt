package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode

/**
 * Use for Case 1-1 (d ∈ P(s)) step 2.
 * Create path from s^n to d without use P(d) and P(s)
 * Created by bhags on 2016/11/24.
 */
class CreatePathFromSnToDTask(graph: HalfPancakeGraph, path: UniquePath) {
    private val _graph:HalfPancakeGraph = graph
    private val _path:UniquePath = path

    fun run(){
//        var sourceNode = _path.getDestinationNodeId() as HalfPancakeNode
//        //Reverse on 2. Reverse on 2 is the first neighbor node.
//        var neighborNode : HalfPancakeNode = sourceNode.getNthNeighbor(_makeUniquePermutationNeighborNumber) as HalfPancakeNode
//        _path.addNode(neighborNode)
//
//        var selectedNeighborNumber = _centerReverseIndex - 1
//        //Reverse on ~n or ~n - 1
//        neighborNode = neighborNode.getNthNeighbor(selectedNeighborNumber) as HalfPancakeNode
//        _path.addNode(neighborNode)
    }
}
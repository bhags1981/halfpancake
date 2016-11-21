package net.kanekolab.graph.permutation

import net.kanekolab.graph.permutation.manager.PermutationManager
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph

/**
 * Created by bhags on 2016/11/20.
 */

fun main(args: Array<String>) {
    //var allPermutations:List<String> = PermutationManager().getAllPermutationForDimension(11);
//    allPermutations.forEach {
//        println("$it")
//    }

    var halfPancakeGraph = HalfPancakeGraph(dimension  =  5)
    var vertex = halfPancakeGraph.getVertexWithId("12345")
    for(i in 1.. halfPancakeGraph.getDegree()){
        var neighbor = vertex.getNthNeighbor(i)
        println(neighbor.getId())
    }


}

package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by sinyu on 2016/11/28.
 */
class CreatePathFromSnToDTaskTest {
    @Test
    fun run() {
        var graph = HalfPancakeGraph(7)
        var sourceNode = graph.getNodeById("3124567")
        var destinationNode = graph.getNodeById("1234567") as HalfPancakeNode
        var uniquePath = UniquePath(sourceNode)
        uniquePath.addNode(sourceNode.getNthNeighbor(graph.getDegree()))
        var createPathFromSnToD = CreatePathFromSnToDTask(graph,uniquePath,destinationNode)
        createPathFromSnToD.run()
        println(createPathFromSnToD.getPath().toString())

    }

}
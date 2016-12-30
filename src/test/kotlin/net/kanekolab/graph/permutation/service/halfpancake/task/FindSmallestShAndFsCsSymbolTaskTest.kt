package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/30.
 */
class FindSmallestShAndFsCsSymbolTaskTest {
    @Test
    fun getResult() {
        var graph = HalfPancakeGraph(7)
        var sourceNode = graph.getNodeById("5612347") as HalfPancakeNode
        var destinationNode = graph.getNodeById("1234567") as HalfPancakeNode
        var result = FindSmallestShNotInRdAndFsCsSymbolTask(graph,sourceNode,destinationNode).executeTask().getResult()
        assertTrue(result.first.equals('1')&&result.second.equals("562"))
    }

}
package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/30.
 */
class FindShElementTaskTest {
    @Test
    fun testFindS2Node() {
        var graph = HalfPancakeGraph(7)
        var sourceNode = graph.getNodeById("2765431") as HalfPancakeNode
        var destinationNode = graph.getNodeById("1234567") as HalfPancakeNode
        var sh = FindS2NodeTask(graph,sourceNode,destinationNode).executeTask().getResult()
        assertTrue(sh.getId().equals("7652431"))

    }

}
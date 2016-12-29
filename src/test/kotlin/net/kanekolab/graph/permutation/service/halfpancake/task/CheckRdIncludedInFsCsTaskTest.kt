package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/29.
 */
class CheckRdIncludedInFsCsTaskTest {
    @Test
    fun checkCaseOdd() {
        var graph = HalfPancakeGraph(7)

        //Case of True
        var sourceNode = graph.getNodeById("1234567") as HalfPancakeNode
        var destinationNode = graph.getNodeById("1567234") as HalfPancakeNode
        assertTrue(CheckRdIncludedInFsCsTask(sourceNode,destinationNode).executeTask().getResult())


        //Case of false
        var sourceNode2 = graph.getNodeById("1234567") as HalfPancakeNode
        var destinationNode2 = graph.getNodeById("1562347") as HalfPancakeNode
        assertFalse(CheckRdIncludedInFsCsTask(sourceNode2,destinationNode2).executeTask().getResult())
    }

    @Test
    fun checkCaseEven() {
        var graph = HalfPancakeGraph(8)

        //Case of True
        var sourceNode = graph.getNodeById("12345678") as HalfPancakeNode
        var destinationNode = graph.getNodeById("15867234") as HalfPancakeNode
        assertTrue(CheckRdIncludedInFsCsTask(sourceNode,destinationNode).executeTask().getResult())


        //Case of false
        var sourceNode2 = graph.getNodeById("12345678") as HalfPancakeNode
        var destinationNode2 = graph.getNodeById("81562347") as HalfPancakeNode
        assertFalse(CheckRdIncludedInFsCsTask(sourceNode2,destinationNode2).executeTask().getResult())
    }
}
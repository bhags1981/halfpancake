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
    fun testOddCase() {
        var graph = HalfPancakeGraph(7)
        var sourceNode = graph.getNodeById("5612347") as HalfPancakeNode
        var destinationNode = graph.getNodeById("1234567") as HalfPancakeNode
        var (sh,distinctString) = FindSmallestShNotInRdAndFsCsSymbolTask(graph,sourceNode,destinationNode).executeTask().getResult()
        assertTrue(sh.equals("1")&&distinctString.equals("562"))
    }

    @Test
    fun testEvenCase(){
        var graph = HalfPancakeGraph(10)
        var sourceNode = graph.getNodeById("a956123478") as HalfPancakeNode
        var destinationNode = graph.getNodeById("a123456789") as HalfPancakeNode
        var (sh,distinctString) = FindSmallestShNotInRdAndFsCsSymbolTask(graph,sourceNode,destinationNode).executeTask().getResult()
        assertTrue(sh.equals("12")&&distinctString.equals("a956"))
    }
}
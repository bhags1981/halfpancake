package net.kanekolab.graph.permutation.strategy.halfpancake.casefactory

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.vo.halfpancake.HPCaseType
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/11/21.
 */
class HPCaseFactoryTest {
    @Test
    fun createCase() {
        var graph = HalfPancakeGraph(7)
        var destinationNode = graph.getNodeById("1234567") as HalfPancakeNode

        //Check Case 1-1
        run {
            var sourceNode = graph.getNodeById("4321567") as HalfPancakeNode
            var hpCase = HPN2NCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_1,hpCase.caseType)
        }

        //Check Case 1-2
        run {
            var sourceNode = graph.getNodeById("7654321") as HalfPancakeNode
            var hpCase = HPN2NCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_2,hpCase.caseType)
        }

        //Check Case 1-3
        run {
            var sourceNode = graph.getNodeById("7654312") as HalfPancakeNode
            var hpCase = HPN2NCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_3,hpCase.caseType)
        }

        //Check Case 1-4
        run {
            var sourceNode = graph.getNodeById("6752314") as HalfPancakeNode
            var hpCase = HPN2NCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_4,hpCase.caseType)
        }

        //Check Case 1-5
        run {
            var sourceNode = graph.getNodeById("1567243") as HalfPancakeNode
            var hpCase = HPN2NCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_5,hpCase.caseType)
        }

        //Check Case 1-6
        run {
            var sourceNode = graph.getNodeById("7412536") as HalfPancakeNode
            var hpCase = HPN2NCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_OTHERWISE,hpCase.caseType)
        }

    }

    @Test
    fun test2(){
        var graph = HalfPancakeGraph(9)
        var destinationNode = graph.getNodeById("123456789") as HalfPancakeNode
        var sourceNode = graph.getNodeById("679584321") as HalfPancakeNode
        var hpCase = HPN2NCaseFactory(graph,sourceNode,destinationNode).createCase()
        println(hpCase.caseType.toString()+" "+hpCase.isReversedPattern)
    }

    @Test
    fun test3(){
        var graph = HalfPancakeGraph(12)
        var destinationNode = graph.getNodeById("123456789abc") as HalfPancakeNode
        var sourceNode = graph.getNodeById("ca9871b23456") as HalfPancakeNode
        var hpCase = HPN2NCaseFactory(graph,sourceNode,destinationNode).createCase()
        println(hpCase.caseType.toString()+" "+hpCase.isReversedPattern)
    }
}
package net.kanekolab.graph.permutation.strategy.halfpancake.n2n

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
        var destinationNode = graph.getVertexWithId("1234567") as HalfPancakeNode

        //Check Case 1-1
        run {
            var sourceNode = graph.getVertexWithId("4321567") as HalfPancakeNode
            var hpCase = HPCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_1,hpCase.caseType)
        }

        //Check Case 1-2
        run {
            var sourceNode = graph.getVertexWithId("7654321") as HalfPancakeNode
            var hpCase = HPCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_2,hpCase.caseType)
        }

        //Check Case 1-3
        run {
            var sourceNode = graph.getVertexWithId("7654312") as HalfPancakeNode
            var hpCase = HPCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_3,hpCase.caseType)
        }

        //Check Case 1-4
        run {
            var sourceNode = graph.getVertexWithId("6752314") as HalfPancakeNode
            var hpCase = HPCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_4,hpCase.caseType)
        }

        //Check Case 1-5
        run {
            var sourceNode = graph.getVertexWithId("1567243") as HalfPancakeNode
            var hpCase = HPCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_5,hpCase.caseType)
        }

        //Check Case 1-6
        run {
            var sourceNode = graph.getVertexWithId("7412536") as HalfPancakeNode
            var hpCase = HPCaseFactory(graph,sourceNode,destinationNode).createCase()
            assertEquals(HPCaseType.ODD_OTHERWISE,hpCase.caseType)
        }



    }

}
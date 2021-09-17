package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/31.
 */
class HPDCase1_4_ServiceTest {
    //@Test
    fun constructDisjointPathsWhenStep1IsFalse() {
        val graph = HalfPancakeGraph(9)
        val sourceNode = graph.getNodeById("789651234") as HalfPancakeNode
        val destinationNode = graph.getNodeById("123456789") as HalfPancakeNode
        val positionK = 3
        var service = HPDCase1_4_Service(graph,sourceNode,destinationNode,positionK)
        service.constructDisjointPaths()
        println(LogData.getLog())
    }


    @Test
    fun constructDisjointPathsWhenStep1IsTrue() {
//
        val graph = HalfPancakeGraph(9)
        val sourceNode = graph.getNodeById("789612345") as HalfPancakeNode
        val destinationNode = graph.getNodeById("123456789") as HalfPancakeNode
        val positionK = 3
        var service = HPDCase1_4_Service(graph,sourceNode,destinationNode,positionK)
        service.constructDisjointPaths()
        println(LogData.getLog())
    }

}
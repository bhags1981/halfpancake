package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/31.
 */
class HPDCase1_3_ServiceTest {
    @Test
    fun constructDisjointPathsWhenStep1IsFalse() {
        var graph = HalfPancakeGraph(7)
        var sourceNode = graph.getNodeById("7652413") as HalfPancakeNode
        var destinationNode = graph.getNodeById("1234567") as HalfPancakeNode
        var service = HPDCase1_3_Service(graph,sourceNode,destinationNode)
        service.constructDisjointPaths()
        println(service.getLogData().getLog())
    }


    @Test
    fun constructDisjointPathsWhenStep1IsTrue() {
        var graph = HalfPancakeGraph(7)
        var sourceNode = graph.getNodeById("7651234") as HalfPancakeNode
        var destinationNode = graph.getNodeById("1234567") as HalfPancakeNode
        var service = HPDCase1_3_Service(graph,sourceNode,destinationNode)
        service.constructDisjointPaths()
        println(service.getLogData().getLog())
    }
}
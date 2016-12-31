package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/30.
 */
class HPDCase1_1_ServiceTest {
    @Test
    fun constructDisjointPaths() {
        var graph = HalfPancakeGraph(7)
        var sourceNode = graph.getNodeById("3124567") as HalfPancakeNode
        var destinationNode = graph.getNodeById("1234567")as HalfPancakeNode
        var hpdCaseService = HPDCase1_1_Service(graph,sourceNode,destinationNode)
        hpdCaseService.constructDisjointPaths()
        println(hpdCaseService.getPaths())
    }

}
package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2017/01/03.
 */
class HPDCase2_1_ServiceTest {
    @Test
    fun constructDisjointPaths() {
        var graph = HalfPancakeGraph(8)
        var sourceNode = graph.getNodeById("12354678") as HalfPancakeNode
        var destinationNode = graph.getNodeById("12345678")as HalfPancakeNode
        var hpdCaseService = HPDCase2_1_Service(graph,sourceNode,destinationNode)
        hpdCaseService.constructDisjointPaths()
        hpdCaseService.getPaths().forEach(::println)
    }

}
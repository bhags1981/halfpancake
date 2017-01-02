package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by sinyu on 2017/01/01.
 */
class HPDCase1_6_ServiceTest {
    @Test
    fun constructDisjointPaths() {
        var graph = HalfPancakeGraph(9)
        var sorc = graph.getNodeById("493871625") as HalfPancakeNode
        var dest = graph.getNodeById("123456789") as HalfPancakeNode
        var service = HPDCase1_6_Service(graph,sorc,dest)
        service.constructDisjointPaths()
        println(LogData.getLog())
    }

}
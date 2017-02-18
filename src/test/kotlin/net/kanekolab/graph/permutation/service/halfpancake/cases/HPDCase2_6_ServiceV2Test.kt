package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.PermutationService
import net.kanekolab.graph.permutation.strategy.halfpancake.casefactory.HPN2NCaseFactory
import net.kanekolab.graph.permutation.vo.halfpancake.HPCaseType
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2017/02/18.
 */
class HPDCase2_6_ServiceV2Test {
    @Test
    fun constructDisjointPaths() {
        val dimension = 10
        val graph = HalfPancakeGraph(dimension);
        val sourceNode = graph.getNodeById("1789a23456") as HalfPancakeNode
        val destNode = graph.getNodeById("123456789a") as HalfPancakeNode
        val service = HPDCase2_6_ServiceV2(graph,sourceNode,destNode)
        service.constructDisjointPaths()
        println(service.getPaths().toString())
        println(LogData.getLog())

    }

}
package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.PermutationService
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2017/01/04.
 */
class HPDCase2_4_ServiceTest {
    @Test
    fun constructDisjointPaths() {
        val dimension = 10
        val graph = HalfPancakeGraph(dimension)
        val dst = graph.getNodeById("123456789a") as HalfPancakeNode
        val allPermutation = PermutationService().getAllPermutationForString("123456")
        val excludedPattern = listOf("564321","654321")
        allPermutation.forEach { element->
            //Continue
            if(excludedPattern.contains(element))return@forEach
            UsedNodeIds.init()
            var src = graph.getNodeById("a987"+element) as HalfPancakeNode
            var service = HPDCase2_4_Service(graph,src,dst)
            service.constructDisjointPaths()
            //service.getPaths().forEach(::println)
        }
        println("FINISHED TEST [HPDCase2_4_Service]")
    }

}
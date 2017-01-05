package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.PermutationService
import net.kanekolab.graph.permutation.strategy.halfpancake.casefactory.HPN2NCaseFactory
import net.kanekolab.graph.permutation.vo.halfpancake.HPCaseType
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2017/01/05.
 */
class HPDCase2_7_ServiceTest {
    @Test
    fun constructDisjointPaths() {
        val dimension = 8
        val idString = "12345678"
        val graph = HalfPancakeGraph(dimension)
        val dst = graph.getNodeById(idString) as HalfPancakeNode
        val allPermutation = PermutationService().getAllPermutationForString(idString)
        val excludedElements = listOf(idString,idString.reversed())
        allPermutation.forEach { element->
            if(excludedElements.contains(element))return@forEach

            var src = graph.getNodeById(element) as HalfPancakeNode
            var case = HPN2NCaseFactory(graph, src, dst).createCase()
            if(case.caseType != HPCaseType.EVEN_7)return@forEach
            var service =
                    if(case.isReversedPattern)HPDCase2_7_Service(graph,dst,src)
                    else HPDCase2_7_Service(graph,src,dst)
            UsedNodeIds.init()
            UsedNodeIds.setIgnoreNodeId(service.getCurrentDestinationNode().getId())
            try {
                service.constructDisjointPaths()
            }catch(e:Exception){
                println(e.message)
                println("SRC ${service.getCurrentSourceNode().getId()} DST ${service.getCurrentDestinationNode().getId()}")
            }
        }
    }

    @Test
    fun constructDisjointPathsSpecificCase() {
        val dimension = 10
        val graph = HalfPancakeGraph(dimension)
        var src = graph.getNodeById("65789a4231") as HalfPancakeNode
        val dst = graph.getNodeById("123456789a") as HalfPancakeNode
        var case = HPN2NCaseFactory(graph, src, dst).createCase()
        var service =
                if(case.isReversedPattern)HPDCase2_7_Service(graph,dst,src)
                else HPDCase2_7_Service(graph,src,dst)

        UsedNodeIds.init()
        UsedNodeIds.setIgnoreNodeId(service.getCurrentDestinationNode().getId())

        try {
            service.constructDisjointPaths()
            service.getPaths().forEach(::println)
        }catch(e:Exception){
            println(e.message)
            println("SRC ${service.getCurrentSourceNode().getId()} DST ${service.getCurrentDestinationNode().getId()}")
            service.getPaths().forEach(::println)

        }
    }

}
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
 * Created by sinyu on 2017/01/01.
 */
class HPDCase1_6_ServiceTest {
    @Test
    fun constructDisjointPaths() {
        var graph = HalfPancakeGraph(7)
        var sorc = graph.getNodeById("1327546") as HalfPancakeNode
        var dest = graph.getNodeById("1234567") as HalfPancakeNode
        var service = HPDCase1_6_Service(graph,sorc,dest)
        service.constructDisjointPaths()
        service.getPaths().forEach(::println)
        println(LogData.getLog())
    }

//    fun constructDisjointPaths() {
//        val dimension = 7
//        val idString = "1234567"
//        val graph = HalfPancakeGraph(dimension)
//        val dst = graph.getNodeById(idString) as HalfPancakeNode
//        val allPermutation = PermutationService().getAllPermutationForString(idString)
//        val excludedElements = listOf(idString,idString.reversed())
//        allPermutation.forEach { element->
//            if(excludedElements.contains(element))return@forEach
//
//            var src = graph.getNodeById(element) as HalfPancakeNode
//            var case = HPN2NCaseFactory(graph, src, dst).createCase()
//            if(case.caseType != HPCaseType.ODD_OTHERWISE)return@forEach
//            var service = HPDCase1_6_Service(graph,src,dst)
//            UsedNodeIds.init()
//            UsedNodeIds.setIgnoreNodeId(service.getCurrentDestinationNode().getId())
//            try {
//                println("SRC ${service.getCurrentSourceNode().getId()} DST ${service.getCurrentDestinationNode().getId()}")
//                service.constructDisjointPaths()
//                service.getPaths().forEach(::println)
//            }catch(e:Exception){
//                println(e.message)
//                println("CASE : ${case.caseType}")
//                println("SRC ${service.getCurrentSourceNode().getId()} DST ${service.getCurrentDestinationNode().getId()}")
//                service.getPaths().forEach(::println)
//                throw(e)
//            }
//        }
//    }
}
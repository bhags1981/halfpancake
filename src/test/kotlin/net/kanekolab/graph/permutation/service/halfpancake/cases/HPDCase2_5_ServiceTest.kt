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
 * Created by bhags on 2017/01/04.
 */
class HPDCase2_5_ServiceTest {
    @Test
    fun constructDisjointPaths() {
        val dimension = 10
        val idString = "123456789a"
        val graph = HalfPancakeGraph(dimension)
        val dst = graph.getNodeById(idString) as HalfPancakeNode
        val allPermutation = PermutationService().getAllPermutationForString(idString)
        allPermutation.forEach { element->
            if(element.equals(idString))return@forEach
            UsedNodeIds.init()
            var src = graph.getNodeById(element) as HalfPancakeNode
            var case = HPN2NCaseFactory(graph,src,dst).createCase()
            if(case.caseType != HPCaseType.EVEN_5)return@forEach
            var service =
                if(case.isReversedPattern)HPDCase2_5_Service(graph,dst,src,case.prefixPositionK)
                    else HPDCase2_5_Service(graph,src,dst,case.prefixPositionK)
            service.constructDisjointPaths()
        }
        println("FINISHED TEST [HPDCase2_5_ServiceTest]")
    }
}
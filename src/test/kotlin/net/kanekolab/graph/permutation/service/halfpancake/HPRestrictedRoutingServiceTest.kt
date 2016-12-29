package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/29.
 */
class HPRestrictedRoutingServiceTest {
    //@Test
    fun findUniquePathForOddCaseStep1() {
        var dimension = 7
        var graph = HalfPancakeGraph(dimension)
        var sourceNode = graph.getNodeById("3241576")
        var destinationNode = graph.getNodeById("2134576")
        var hpRestrictedRouting = HPRestrictedRoutingService(graph)
        hpRestrictedRouting.setSourceNode(sourceNode as HalfPancakeNode)
        hpRestrictedRouting.setDestinationNode(destinationNode as HalfPancakeNode)
        hpRestrictedRouting.findUniquePath()
        print(hpRestrictedRouting.getCurrentPath())
        print(hpRestrictedRouting.getCurrentLog().getLog())

    }
    @Test
    fun findUniquePathForOddCaseStep3(){
        var dimension = 9
        var graph = HalfPancakeGraph(dimension)
        var sourceNode = graph.getNodeById("693872451")
        var destinationNode = graph.getNodeById("123456789")
        var hpRestrictedRouting = HPRestrictedRoutingService(graph)

        hpRestrictedRouting.setSourceNode(sourceNode as HalfPancakeNode)
        hpRestrictedRouting.setDestinationNode(destinationNode as HalfPancakeNode)
        hpRestrictedRouting.findUniquePath()
        println(hpRestrictedRouting.getCurrentLog().getLog())
    }

}
package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/29.
 */
class HPRestrictedRoutingServiceTest {
    @Test
    fun findUniquePathForOddCaseStep1() {
        var dimension = 7
        var graph = HalfPancakeGraph(dimension)
        var pathUniqueIdentifier : Int = 0

        var sourceNode = graph.getNodeById("3241576") as HalfPancakeNode
        var destinationNode = graph.getNodeById("2134576") as HalfPancakeNode
        var hpRestrictedRouting = HPRestrictedRoutingService(graph,pathUniqueIdentifier,sourceNode,destinationNode)
        hpRestrictedRouting.findUniquePath()
        print(hpRestrictedRouting.getCurrentPath())
        print(hpRestrictedRouting.getCurrentLog().getLog())

    }
    @Test
    fun findUniquePathForOddCaseStep3(){
        var dimension = 9
        var graph = HalfPancakeGraph(dimension)
        var pathUniqueIdentifier : Int = 0

        var sourceNode = graph.getNodeById("693872451") as HalfPancakeNode
        var destinationNode = graph.getNodeById("123456789") as HalfPancakeNode
        var hpRestrictedRouting = HPRestrictedRoutingService(graph,pathUniqueIdentifier,sourceNode,destinationNode)
        hpRestrictedRouting.findUniquePath()
        println(hpRestrictedRouting.getCurrentLog().getLog())
    }

    @Test
    fun findUniquePathForOddCaseStep45(){
        var dimension = 7
        var graph = HalfPancakeGraph(dimension)
        var pathUniqueIdentifier : Int = 0
        var sourceNode = graph.getNodeById("5612347") as HalfPancakeNode
        var destinationNode = graph.getNodeById("1234567") as HalfPancakeNode
        var hpRestrictedRouting = HPRestrictedRoutingService(graph,pathUniqueIdentifier,sourceNode,destinationNode)
        hpRestrictedRouting.findUniquePath()
        println(hpRestrictedRouting.getCurrentLog().getLog())
    }

}
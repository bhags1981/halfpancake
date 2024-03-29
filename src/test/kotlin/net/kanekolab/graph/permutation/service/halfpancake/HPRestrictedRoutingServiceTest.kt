package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

/**
 * Created by bhags on 2016/12/29.
 */
class HPRestrictedRoutingServiceTest {
    @Test
    fun routingTest(){
        val graph = HalfPancakeGraph(9)
        val src = graph.getNodeById("564798312") as HalfPancakeNode
        val dst = graph.getNodeById("123456789") as HalfPancakeNode
        var avoidSuffixA = "3241"
        var avoidSuffixB = "1425"
        var service = HPRestrictedRoutingService(graph,1,src,dst,avoidSuffixA,avoidSuffixB)
        service.findUniquePath()
        println(LogData.getLog())
    }

    @Test
    fun routingTestEven(){
        val graph = HalfPancakeGraph(10)
        val src = graph.getNodeById("56a4798312") as HalfPancakeNode
        val dst = graph.getNodeById("123456789a") as HalfPancakeNode
        var avoidSuffixA = "7983"
        var avoidSuffixB = "789a"
        var service = HPRestrictedRoutingService(graph,1,src,dst,avoidSuffixA,avoidSuffixB)
        service.findUniquePath()
        println(LogData.getLog())
    }


    @Test
    fun findUniquePathForOddCaseStep1() {
        var dimension = 7
        var graph = HalfPancakeGraph(dimension)
        var pathUniqueIdentifier : Int = 0

        var sourceNode = graph.getNodeById("3241576") as HalfPancakeNode
        var destinationNode = graph.getNodeById("2134576") as HalfPancakeNode
        var avoidSuffixA = "324"
        var avoidSuffixB = "142"
        var hpRestrictedRouting = HPRestrictedRoutingService(graph,pathUniqueIdentifier,sourceNode,destinationNode,avoidSuffixA,avoidSuffixB)
        hpRestrictedRouting.findUniquePath()
        print(hpRestrictedRouting.getCurrentPath())
        print(LogData.getLog())

    }
    @Test
    fun findUniquePathForOddCaseStep3(){
        var dimension = 9
        var graph = HalfPancakeGraph(dimension)
        var pathUniqueIdentifier : Int = 0

        var sourceNode = graph.getNodeById("693872451") as HalfPancakeNode
        var destinationNode = graph.getNodeById("123456789") as HalfPancakeNode
        var avoidSuffixA = "3241"
        var avoidSuffixB = "1425"
        var hpRestrictedRouting = HPRestrictedRoutingService(graph,pathUniqueIdentifier,sourceNode,destinationNode,avoidSuffixA,avoidSuffixB)
        hpRestrictedRouting.findUniquePath()
        println(LogData.getLog())
    }

    @Test
    fun findUniquePathForOddCaseStep45(){
        var dimension = 7
        var graph = HalfPancakeGraph(dimension)
        var pathUniqueIdentifier : Int = 0
        var sourceNode = graph.getNodeById("5612347") as HalfPancakeNode
        var destinationNode = graph.getNodeById("1234567") as HalfPancakeNode
        var avoidSuffixA = "3241"
        var avoidSuffixB = "1425"
        var hpRestrictedRouting = HPRestrictedRoutingService(graph,pathUniqueIdentifier,sourceNode,destinationNode,avoidSuffixA,avoidSuffixB)
        hpRestrictedRouting.findUniquePath()
        println(LogData.getLog())
    }


    @Test
    fun findUniquePathForEvenStep3(){
        var dimension = 8
        var graph = HalfPancakeGraph(dimension)
        var pathUniqueIdentifier : Int = 0
        var sourceNode = graph.getNodeById("61748235") as HalfPancakeNode
        var destinationNode = graph.getNodeById("12345678") as HalfPancakeNode
        var avoidSuffixA = "235"
        var avoidSuffixB = "678"
        var hpRestrictedRouting = HPRestrictedRoutingService(graph,pathUniqueIdentifier,sourceNode,destinationNode,avoidSuffixA,avoidSuffixB)
        hpRestrictedRouting.findUniquePath()
        println(LogData.getLog())
    }
}
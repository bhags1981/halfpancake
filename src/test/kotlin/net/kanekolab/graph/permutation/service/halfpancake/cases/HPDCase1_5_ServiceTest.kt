package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/31.
 */
class HPDCase1_5_ServiceTest {
    @Test
    fun constructDisjointPathsWhenStep1IsFalse() {
        val graph = HalfPancakeGraph(9)
        val step2ConditionTrueString = "678952314"
        val step2ConditionFalseString = "678952314"
        val sourceNode = graph.getNodeById(step2ConditionTrueString) as HalfPancakeNode
        val destinationNode = graph.getNodeById("123456789") as HalfPancakeNode
        var service = HPDCase1_5_Service(graph,sourceNode,destinationNode)
        service.constructDisjointPaths()
        println(service.getLogData().getLog())
    }


    @Test
    fun constructDisjointPathsWhenStep1IsTrue() {

    }

}
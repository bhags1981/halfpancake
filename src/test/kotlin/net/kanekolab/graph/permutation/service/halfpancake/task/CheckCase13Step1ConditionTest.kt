package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/31.
 */
class CheckCase13Step1ConditionTest {
    @Test
    fun executeTask() {
        var graph = HalfPancakeGraph(9)
        var sourceNode = graph.getNodeById("678954321") as HalfPancakeNode
        var destinationNode = graph.getNodeById("123456789") as HalfPancakeNode

        println(CheckCase13Step1ConditionTask(sourceNode,destinationNode).executeTask().getResult())
    }

}
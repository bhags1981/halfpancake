package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/31.
 */
class CheckCase14Step1ConditionTaskTest {
    @Test
    fun executeTask() {
        //Case 1-4 (∃k(< ˜ n) such that s(k,n) ∈ P(d) and s(k,n) ̸= d)
        var graph = HalfPancakeGraph(9)
        var sourceNode = graph.getNodeById("678951234") as HalfPancakeNode
        var destinationNode = graph.getNodeById("123456789") as HalfPancakeNode
        var positionK = 3
        var (isExistL,positionL) = CheckCase14Step1ConditionTask(sourceNode,destinationNode,positionK).executeTask().getResult()
        println("Result of check case condition between {${sourceNode.getId()}} and {${destinationNode.getId()}}  is {$isExistL,$positionL}")
    }

}
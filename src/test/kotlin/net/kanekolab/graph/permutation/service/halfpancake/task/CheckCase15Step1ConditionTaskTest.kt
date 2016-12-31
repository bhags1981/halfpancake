package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/31.
 */
class CheckCase15Step1ConditionTaskTest {
    @Test
    fun executeTask() {
        //Case 1-5 (s( ˜n,n) ∈ P(d) and s( ˜n,n) ̸= d)
        val graph = HalfPancakeGraph(11)
        val sourceNode = graph.getNodeById("ba987654312") as HalfPancakeNode
        val destinationNode = graph.getNodeById("123456789ab") as HalfPancakeNode
        var (isExistL,positionL) = CheckCase15Step1ConditionTask(sourceNode,destinationNode).executeTask().getResult()
        println("Result of check case condition between {${sourceNode.getId()}} and {${destinationNode.getId()}}  is {$isExistL,$positionL}")
    }

}
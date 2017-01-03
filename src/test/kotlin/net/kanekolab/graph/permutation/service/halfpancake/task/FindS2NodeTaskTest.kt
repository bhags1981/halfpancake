package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by sinyu on 2017/01/02.
 */
class FindS2NodeTaskTest {
    @Test
    fun executeTaskForOdd() {
        val graph = HalfPancakeGraph(9)
        val src = graph.getNodeById("432185679") as HalfPancakeNode
        val dst = graph.getNodeById("987651234") as HalfPancakeNode
        println(FindS2NodeTask(graph,src,dst).executeTask().getResult().getId())
    }


    @Test
    fun executeTaskForEven() {
        val graph = HalfPancakeGraph(10)
        val src = graph.getNodeById("a438215679") as HalfPancakeNode
        val dst = graph.getNodeById("a987651234") as HalfPancakeNode
        println(FindS2NodeTask(graph,src,dst).executeTask().getResult().getId())
    }
}
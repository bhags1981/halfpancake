package net.kanekolab.graph.permutation.service.pancake.task

import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by sinyu on 2016/11/25.
 */
class PancakeSimpleRoutingTest {
    @Test
    fun run() {
        var graph:HalfPancakeGraph = HalfPancakeGraph(9)
        var sourcePath = graph.getNodeById("315246789")
        var destinationNode = graph.getNodeById("123456789")
        var path: UniquePath = UniquePath(sourcePath)
        var pancakeSimpleRouting = PancakeSimpleRoutingTask(path,destinationNode)
        pancakeSimpleRouting.executeTask()
        println(pancakeSimpleRouting.getResult())
    }

}
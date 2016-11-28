package net.kanekolab.graph.permutation.service.pancake.task

import net.kanekolab.graph.permutation.model.Path
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
        var soureNode = graph.getNodeById("315246789")
        var destinationNode = graph.getNodeById("123456789")
        var path: Path = Path(soureNode)
        var pancakeSimpleRouting = PancakeSimpleRouting(path,destinationNode)
        pancakeSimpleRouting.run()
        println(pancakeSimpleRouting.getPath())
    }

}
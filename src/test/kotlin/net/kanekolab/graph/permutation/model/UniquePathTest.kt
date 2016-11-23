package net.kanekolab.graph.permutation.model

import net.kanekolab.graph.permutation.core.Config
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/11/23.
 */
class UniquePathTest {
    @Test
    fun addNode() {
        var graph = HalfPancakeGraph(5)
        var sourceNode = HalfPancakeNode("12345",graph.getDegree())
        var intermediateNode = HalfPancakeNode("21345",graph.getDegree())
        var uniquePath = UniquePath(sourceNode)
        uniquePath.addNode(intermediateNode)
    }


    @Test (expected = Exception::class)
    fun notUniquePathErrorTest()
    {
        var graph = HalfPancakeGraph(5)
        var sourceNode = HalfPancakeNode("12345",graph.getDegree())
        var notUniqueNode = HalfPancakeNode("12345",graph.getDegree())
        var uniquePath = UniquePath(sourceNode)
        uniquePath.addNode(notUniqueNode)
    }

    @Test (expected = Exception::class)
    fun notNeighborNodeErrorTest()
    {
        var graph = HalfPancakeGraph(5)
        var sourceNode = HalfPancakeNode("12345",graph.getDegree())
        var notNeighborNode = HalfPancakeNode("32415",graph.getDegree())
        var uniquePath = UniquePath(sourceNode)

        //Exception will not be thrown when Config.debugMode is false.
        uniquePath.addNode(notNeighborNode)

    }
}
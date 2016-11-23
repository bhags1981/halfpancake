package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by bhags on 2016/11/23.
 */
class CreatePathToAllNeighborTaskTest {
    @Test
    fun createPath() {
        var graph:HalfPancakeGraph = HalfPancakeGraph(7)
        var sourceNode: Node = graph.getNodeById("1234567")
        var task:CreatePathToAllNeighborTask = CreatePathToAllNeighborTask(graph,sourceNode)
        task.run()
        task.getCurrentPath().forEachIndexed { i, path ->  println("current path for " +i + " is " + path.toString())}
    }
}
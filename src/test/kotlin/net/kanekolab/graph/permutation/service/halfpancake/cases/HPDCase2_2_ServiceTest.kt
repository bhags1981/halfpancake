package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2017/01/04.
 */
//Case 2-2 (s(n) = d)
class HPDCase2_2_ServiceTest {
    @Test
    fun constructDisjointPaths() {
        val dimension = 8
        val graph = HalfPancakeGraph(dimension)
        val src = graph.getNodeById("87654321") as HalfPancakeNode
        val dst = graph.getNodeById("12345678") as HalfPancakeNode
        val service = HPDCase2_2_Service(graph,src,dst)
        UsedNodeIds.setIgnoreNodeId(dst.getId())
        service.constructDisjointPaths()
        service.getPaths().forEach(::println)
    }

}
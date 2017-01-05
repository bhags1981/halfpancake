package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Test

import org.junit.Assert.*

/**
 * Case 2-3 (s(n) ∈ P(d), d(n) ∈ P(s), and s(n) ̸= d)
 * 87645321 => 12345678
 * Created by bhags on 2017/01/04.
 */
class HPDCase2_3_ServiceTest {
    @Test
    fun constructDisjointPaths() {
        val dimension = 10
        val graph = HalfPancakeGraph(dimension)
        val src = graph.getNodeById("a987564321") as HalfPancakeNode
        val dst = graph.getNodeById("123456789a") as HalfPancakeNode
        val service = HPDCase2_3_Service(graph,src,dst)
        UsedNodeIds.setIgnoreNodeId(dst.getId())
        service.constructDisjointPaths()
        service.getPaths().forEach(::println)
    }

}
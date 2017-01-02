package net.kanekolab.graph.permutation.service.halfpancake.cases

import com.sun.tools.internal.xjc.reader.gbind.SourceNode
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode

/**
 * Created by bhags on 2016/12/30.
 */
abstract class HPDCaseService(graph:HalfPancakeGraph,sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode) {
    protected var _currentGraph = graph
    protected var _sourceNode = sourceNode
    protected var _destinationNode = destinationNode
    protected var _disjointPaths = mutableListOf<Path>()
    abstract fun constructDisjointPaths()
    fun getPaths():List<Path>{ return _disjointPaths }
}
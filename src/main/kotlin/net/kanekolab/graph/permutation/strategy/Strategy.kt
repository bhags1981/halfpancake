package net.kanekolab.graph.permutation.strategy

import net.kanekolab.graph.permutation.model.RegularGraph
import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.vo.ProblemCase

/**
 * Created by bhags on 2016/11/21.
 */
abstract  class Strategy (regularGraph: RegularGraph) {
    var _graph:RegularGraph = regularGraph
    var _case:ProblemCase? = null
    abstract fun init(graph:RegularGraph, originalSourceNode: Node, originalDestinationNode: Node)
    abstract fun init(graph:RegularGraph, originalSourceNode: Node, originalDestinationNode:List<Node> )
    abstract fun init(graph:RegularGraph, originalSourceNode: List<Node>, originalDestinationNode:List<Node> )
}
package net.kanekolab.graph.permutation.strategy

import net.kanekolab.graph.permutation.model.RegularGraph
import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.vo.ProblemCase

/**
 * Created by bhags on 2016/11/21.
 */
abstract  class Strategy (regularGraph: RegularGraph) {
    var graph:RegularGraph = regularGraph
    var case:ProblemCase = null!!
    abstract fun init(graph:RegularGraph, sourceNode: Node, destinationNode: Node)
    abstract fun init(graph:RegularGraph, sourceVertex: Node, destinationNode:List<Node> )
    abstract fun init(graph:RegularGraph, sourceVertex: List<Node>, destinationNode:List<Node> )
}
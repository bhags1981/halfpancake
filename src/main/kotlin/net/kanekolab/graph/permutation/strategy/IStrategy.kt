package net.kanekolab.graph.permutation.strategy

import net.kanekolab.graph.permutation.model.RegularGraph
import net.kanekolab.graph.permutation.model.Node

/**
 * Created by bhags on 2016/11/21.
 */
interface IStrategy {
    fun execute(graph:RegularGraph, sourceNode: Node, destinationNode: Node)
    fun execute(graph:RegularGraph, sourceVertex: Node, destinationNode:List<Node> )
    fun execute(graph:RegularGraph, sourceVertex: List<Node>, destinationNode:List<Node> )
}
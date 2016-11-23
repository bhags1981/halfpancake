package net.kanekolab.graph.permutation.service

import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.RegularGraph
import net.kanekolab.graph.permutation.strategy.Strategy

/**
 * Created by bhags on 2016/11/21.
 */
abstract class  DisjointPathService (regularGraph: RegularGraph) {
    var graph = regularGraph
    abstract fun initProblemN2N(sourceNode: Node, destinationNode: Node)
    abstract fun findDisjointPaths()
}
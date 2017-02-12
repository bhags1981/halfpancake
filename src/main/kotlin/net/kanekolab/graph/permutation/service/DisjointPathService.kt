package net.kanekolab.graph.permutation.service

import net.kanekolab.graph.permutation.model.*
import net.kanekolab.graph.permutation.strategy.Strategy

/**
 * Created by bhags on 2016/11/21.
 */
abstract class  DisjointPathService (regularGraph: RegularGraph) {
    var graph = regularGraph
    var disjointPaths : List<Path>? = null
    protected fun initPaths(){  disjointPaths = null }
    open fun initProblemN2N(sourceNode: Node, destinationNode: Node){
        LogData.init()
        UsedNodeIds.init()
        disjointPaths = null
    }
    abstract fun findDisjointPaths()
}
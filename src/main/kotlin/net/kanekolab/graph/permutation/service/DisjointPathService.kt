package net.kanekolab.graph.permutation.service

import net.kanekolab.graph.permutation.model.RegularGraph
import net.kanekolab.graph.permutation.strategy.IStrategy

/**
 * Created by bhags on 2016/11/21.
 */
abstract class  DisjointPathService (regularGraph: RegularGraph, strategy: IStrategy) {
    abstract fun findDisjointPaths()
}
package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.service.DisjointPathService
import net.kanekolab.graph.permutation.strategy.halfpancake.HPStrategy

/**
 * Created by bhags on 2016/11/22.
 */
class HPDijointPathService(halfPancakeGraph:HalfPancakeGraph) : DisjointPathService(regularGraph = halfPancakeGraph){

    override  fun initProblemN2N(sourceNode: Node, destinationNode: Node){
        val strategy:HPStrategy = HPStrategy(graph as HalfPancakeGraph)
        strategy.init(graph,sourceNode,destinationNode)

        //Run task step by step


    }

    override  fun findDisjointPaths(){

    }
}
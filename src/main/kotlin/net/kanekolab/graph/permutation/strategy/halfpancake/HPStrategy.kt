package net.kanekolab.graph.permutation.strategy.halfpancake

import net.kanekolab.graph.permutation.model.RegularGraph
import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.strategy.Strategy
import net.kanekolab.graph.permutation.strategy.halfpancake.casefactory.HPN2NCaseFactory
import net.kanekolab.graph.permutation.vo.halfpancake.HPCase

/**
 * Created by bhags on 2016/11/21.
 */
class HPStrategy (hpGraph:HalfPancakeGraph): Strategy (regularGraph = hpGraph){

    override fun init(graph: RegularGraph, sourceNode: Node, destinationNode: Node){
        if (graph !is HalfPancakeGraph)
            throw Exception("Strategy is not match with the graph. ")
        if (sourceNode !is HalfPancakeNode)
            throw Exception("Source vertex is not match with the graph")

        if (destinationNode !is HalfPancakeNode)
            throw Exception("Source vertex is not match with the graph")
        case = HPN2NCaseFactory(graph, sourceNode, destinationNode).createCase()

    }



    override fun init(graph : RegularGraph, sourceNode: Node, destinationNode: List<Node> ){
        throw Exception("You must implements strategy for problem of Node to Set disjoint paths.")
    }

    override fun init(graph  : RegularGraph, sourceNode: List<Node>, destinationNode: List<Node> ){
        throw Exception("You must implements strategy for problem of Set to Set disjoint paths.")
    }
}
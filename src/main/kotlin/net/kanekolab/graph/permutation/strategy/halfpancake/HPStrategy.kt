package net.kanekolab.graph.permutation.strategy.halfpancake

import net.kanekolab.graph.permutation.model.RegularGraph
import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.cases.*
import net.kanekolab.graph.permutation.strategy.Strategy
import net.kanekolab.graph.permutation.strategy.halfpancake.casefactory.HPN2NCaseFactory
import net.kanekolab.graph.permutation.vo.halfpancake.HPCaseType

/**
 * Created by bhags on 2016/11/21.
 */
class HPStrategy (hpGraph:HalfPancakeGraph): Strategy (regularGraph = hpGraph){
    private var _hpdCaseService:HPDCaseService? = null

    public fun getHPDCaseService():HPDCaseService{return _hpdCaseService!!}

    override fun init(graph: RegularGraph, sourceNode: Node, destinationNode: Node){
        if (graph !is HalfPancakeGraph)
            throw Exception("Strategy is not match with the graph. ")
        if (sourceNode !is HalfPancakeNode)
            throw Exception("Source vertex is not match with the graph")

        if (destinationNode !is HalfPancakeNode)
            throw Exception("Source vertex is not match with the graph")
        var currentCase  = HPN2NCaseFactory(graph, sourceNode, destinationNode).createCase()
        _case = currentCase

        when(currentCase.caseType){
            HPCaseType.ODD_1 -> {
                _hpdCaseService = HPDCase1_1_Service(graph,sourceNode,destinationNode)}
            HPCaseType.ODD_2 -> {
                _hpdCaseService = HPDCase1_2_Service(graph,sourceNode,destinationNode)}
            HPCaseType.ODD_3 -> {
                _hpdCaseService = HPDCase1_3_Service(graph,sourceNode,destinationNode)}
            HPCaseType.ODD_4 -> {
                _hpdCaseService = HPDCase1_4_Service(graph,sourceNode,destinationNode,currentCase.prefixPositionK)}
            HPCaseType.ODD_5 -> {
                _hpdCaseService = HPDCase1_5_Service(graph,sourceNode,destinationNode)}
            HPCaseType.ODD_OTHERWISE -> {
                _hpdCaseService = HPDCase1_6_Service(graph,sourceNode,destinationNode)}
            else ->{throw UnsupportedOperationException("CURRENT CASE DOES NOT SUPPORTED.")
            }
        }
    }



    override fun init(graph : RegularGraph, sourceNode: Node, destinationNode: List<Node> ){
        throw Exception("You must implements strategy for problem of Node to Set disjoint paths.")
    }

    override fun init(graph  : RegularGraph, sourceNode: List<Node>, destinationNode: List<Node> ){
        throw Exception("You must implements strategy for problem of Set to Set disjoint paths.")
    }
}
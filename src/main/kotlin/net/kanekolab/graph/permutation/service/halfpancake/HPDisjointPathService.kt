package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.core.ReversedPatternException
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.service.DisjointPathService
import net.kanekolab.graph.permutation.strategy.halfpancake.HPStrategy
import net.kanekolab.graph.permutation.vo.halfpancake.HPCase

/**
 * Created by bhags on 2016/11/22.
 */
class HPDisjointPathService(halfPancakeGraph:HalfPancakeGraph) : DisjointPathService(regularGraph = halfPancakeGraph){
    var _currentStrategy : HPStrategy? = null
    override fun initProblemN2N(sourceNode: Node, destinationNode: Node){
        super.initProblemN2N(sourceNode,destinationNode)
        UsedNodeIds.setIgnoreNodeId(destinationNode.getId())
        LogData.append("[HPDisjointPathService] initialized N2N with source = ${sourceNode.getId()} and ${destinationNode.getId()}")
        _currentStrategy = HPStrategy(graph as HalfPancakeGraph)
        _currentStrategy!!.init(graph, sourceNode, destinationNode)

    }

    override fun findDisjointPaths(){
        LogData.append("[HPDisjointPathService]  Start find disjoint paths.")
        if(_currentStrategy == null)
            throw NullPointerException("Current strategy is null")
        var  caseService = _currentStrategy!!.getHPDCaseService()
        caseService.constructDisjointPaths()
        disjointPaths = caseService.getPaths()
    }

}
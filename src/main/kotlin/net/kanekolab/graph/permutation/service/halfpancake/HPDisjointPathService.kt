package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.service.DisjointPathService
import net.kanekolab.graph.permutation.strategy.halfpancake.HPStrategy
import net.kanekolab.graph.permutation.vo.halfpancake.HPCase

/**
 * Created by bhags on 2016/11/22.
 */
class HPDisjointPathService(halfPancakeGraph:HalfPancakeGraph) : DisjointPathService(regularGraph = halfPancakeGraph){
    var _currentStrategy : HPStrategy? = null
    override  fun initProblemN2N(sourceNode: Node, destinationNode: Node){
        _currentStrategy = HPStrategy(graph as HalfPancakeGraph)
        _currentStrategy!!.init(graph,sourceNode,destinationNode)
    }

    override fun findDisjointPaths(){
        if(_currentStrategy == null)
            throw NullPointerException("Current strategy is null")
        var  caseService = _currentStrategy!!.getHPDCaseService()
        caseService.constructDisjointPaths()
        _logData = caseService.getLogData()
    }

}
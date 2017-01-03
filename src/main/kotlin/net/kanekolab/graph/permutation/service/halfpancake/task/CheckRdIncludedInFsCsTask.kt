package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.ServiceTask

/**
 * Created by bhags on 2016/12/29.
 * Check R(d) ⊂ F(s) ∪ C(s)
 *
 *
 */
class CheckRdIncludedInFsCsTask(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode) : ServiceTask <CheckRdIncludedInFsCsTask,Boolean>{
    var isIncluded:Boolean  = false
    var _sourceNode:HalfPancakeNode = sourceNode
    var _destinationNode:HalfPancakeNode = destinationNode

    override fun executeTask() : CheckRdIncludedInFsCsTask {
        var sourceRearString = _sourceNode.getRearString()
        var destinationNodeRearString = _destinationNode.getRearString()
        isIncluded = destinationNodeRearString.all { symbol:Char -> !sourceRearString.contains(symbol) }
        return this
    }

    override fun getResult(): Boolean {
        return isIncluded
    }



}
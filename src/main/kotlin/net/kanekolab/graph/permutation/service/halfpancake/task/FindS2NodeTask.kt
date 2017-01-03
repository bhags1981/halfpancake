package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.ServiceTask

/**
 * Created by bhags on 2016/12/30.
 */
class FindS2NodeTask(currentGraph:HalfPancakeGraph, sourceNode:HalfPancakeNode, destinationNode :HalfPancakeNode) : ServiceTask<FindS2NodeTask,HalfPancakeNode> {
    var _currentGraph = currentGraph
    var _sourceNode = sourceNode
    var _destinationNode = destinationNode
    var _s2: HalfPancakeNode? = null

    override fun executeTask(): FindS2NodeTask {
        var rearString = _destinationNode.getRearString()

        if(_currentGraph.isEvenDimension()){
            var sElements = mutableListOf<Char>()

            //Find sl and sh
            _sourceNode.getFrontCenterString().forEach {
                element->run{
                    if(!rearString.contains(element)){
                        sElements.add(element)
                    }
                }
            }
            if(sElements.size < 2)
                throw Exception("S Element needs two. source : ${_sourceNode.getId()} dest : ${_destinationNode.getId()}")

            _s2 = _currentGraph.getNodeById(rearString.reversed() + sElements[0] + sElements[1] + _sourceNode.getRearString()) as HalfPancakeNode

        }else {
            _sourceNode.getFrontCenterString().forEach {
                element ->
                run {
                    if (!rearString.contains(element)) {
                        _s2 = _currentGraph.getNodeById(rearString.reversed() + element + _sourceNode.getRearString()) as HalfPancakeNode
                        //Break Lambda Expression
                        return@forEach
                    }
                }
            }
        }
        return this
    }


    override fun getResult():HalfPancakeNode{
        _s2?.let { return it }
        throw Exception("Current s2 is null. Failed to find s2 on FindS2NodeTask ")
    }


}
package net.kanekolab.graph.permutation.model

import net.kanekolab.graph.permutation.core.Config
import net.kanekolab.graph.permutation.model.Node

/**
 * Created by bhags on 2016/11/23.
 */
open class Path (sourceNode: Node) {
    protected  var _pathList:MutableList<String> = mutableListOf(sourceNode.getId())
    protected  var _destinationNode: Node = sourceNode
    open fun addNode(intermediateNode: Node){
        _pathList.add(intermediateNode.getId())


        //isNeighborが O(n)であるため
        //Debug modeが trueの場合のみ使用。

        if(Config.debugMode && !_destinationNode.isNeighbor(intermediateNode)){
            throw Exception("The node " + intermediateNode.getId() + " is not neighbor node of " + _destinationNode.getId())
        }

        _destinationNode = intermediateNode
    }

    override fun toString():String{
        return _pathList.toString()
    }

    fun getDestinationNodeId():Node{
        return _destinationNode
    }
}
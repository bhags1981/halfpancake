package net.kanekolab.graph.permutation.model

import net.kanekolab.graph.permutation.core.Config
import net.kanekolab.graph.permutation.model.Node

/**
 * Created by bhags on 2016/11/23.
 */
open class Path (sourceNode: Node) {
    protected  var _pathList:MutableList<String> = mutableListOf(sourceNode.getId())
    protected  var _destinationNode = sourceNode
    protected  var _sourceNode = sourceNode

    open fun prependNode(intermediateNode: Node) : Path{
        UsedNodes.addNodeId(intermediateNode.getId())
        _pathList.add(0,intermediateNode.getId())
        //isNeighborが O(n)であるため
        //Debug modeが trueの場合のみ使用。a
        if(Config.debugMode && !_sourceNode.isNeighbor(intermediateNode)){
            throw Exception("The node " + intermediateNode.getId() + " is not neighbor node of " + _sourceNode.getId())
        }
        _sourceNode = intermediateNode
        return this
    }

    open fun prependPath(intermediatePath: Path) : Path{
        //Check path is neighbor
        //isNeighborが O(n)であるため
        //Debug modeが trueの場合のみ使用。a
        if(Config.debugMode && !intermediatePath.getLastNode().isNeighbor(_sourceNode)){
            throw Exception("The node " + intermediatePath.getLastNode().getId() + " is not neighbor node of " + _sourceNode.getId())
        }
        _sourceNode = intermediatePath.getFirstNode()
        _pathList.addAll(0,intermediatePath.getList())
        return this
    }


    open fun appendNode(intermediateNode: Node) : Path{

        //Todo create new logic for check UsedNodes
        UsedNodes.addNodeId(intermediateNode.getId())
        _pathList.add(intermediateNode.getId())

        //isNeighborが O(n)であるため
        //Debug modeが trueの場合のみ使用。a
        if(Config.debugMode && !_destinationNode.isNeighbor(intermediateNode)){
            throw Exception("The node " + intermediateNode.getId() + " is not neighbor node of " + _destinationNode.getId())
        }

        _destinationNode = intermediateNode
        return this
    }

    open fun appendPath(intermediatePath: Path) : Path{
        if(Config.debugMode && !intermediatePath.getFirstNode().isNeighbor(_destinationNode)){
            throw Exception("The node " + intermediatePath.getFirstNode().getId() + " is not neighbor node of " + _destinationNode.getId())
        }
        _pathList.addAll(intermediatePath.getList())
        _destinationNode = intermediatePath.getLastNode()
        return this
    }

    open fun appendOmittedPathWithDescription(omittedPathTerminalNode: Node, description:String):Path{
        _pathList.add(description)
        _pathList.add(omittedPathTerminalNode.getId())
        _destinationNode = omittedPathTerminalNode
        return this
    }


    override fun toString():String{
        return _pathList.toString()
    }

    fun getFirstNode():Node{
        return _sourceNode
    }
    fun getLastNode():Node{
        return _destinationNode
    }
    fun getList():MutableList<String>{
        return _pathList
    }
}
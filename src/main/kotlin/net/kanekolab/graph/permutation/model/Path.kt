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
    val size: Int get() = _pathList.size
    open fun prependNode(intermediateNode: Node) : Path{
        UsedNodeIds.addNodeId(intermediateNode.getId())
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
        if(intermediatePath.size < 1)
            return this
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

        //Todo create new logic for check UsedNodeIds
        UsedNodeIds.addNodeId(intermediateNode.getId())
        _pathList.add(intermediateNode.getId())

        //isNeighborが O(n)であるため
        //Debug modeが trueの場合のみ使用。a
        if(Config.debugMode && !_destinationNode.isNeighbor(intermediateNode)){
            throw Exception("The node " + intermediateNode.getId() + " is not neighbor node of " + _destinationNode.getId())
        }

        _destinationNode = intermediateNode
        return this
    }

    open fun append(neighborIndex:Int) : Path{
        var neighbor = _destinationNode.getNeighborByIndex(neighborIndex)
        appendNode(neighbor)
        return this
    }

    open fun prepend(neighborIndex:Int) : Path{
        var neighbor = _sourceNode.getNeighborByIndex(neighborIndex)
        prependNode(neighbor)
        return this
    }

    open fun removeLast():Path{
        UsedNodeIds.removeNodeId(_destinationNode.getId())
        _pathList.removeAt(_pathList.lastIndex)
        if(this.size > 0){
            _destinationNode = _destinationNode.getNeighborById(_pathList[_pathList.lastIndex])
        }else{

        }
        return this
    }

    open fun removeFirst():Path {

        UsedNodeIds.removeNodeId(_sourceNode.getId())
        _pathList.removeAt(0)
        if (_pathList.size > 0){
            _sourceNode = _sourceNode.getNeighborById(_pathList[0])
        }else{

        }

        return this
    }

    open fun appendPath(intermediatePath: Path) : Path{
        if(intermediatePath.size < 1 )
            return this

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


    open fun appendNodesByIndexes(vararg indexes:Int):Path{
        indexes.forEach {index->append(index)}
        return this
    }

    open fun prependNodesByIndexes(vararg indexes:Int):Path{
        indexes.forEach { index->prepend(index) }
        return this
    }
}
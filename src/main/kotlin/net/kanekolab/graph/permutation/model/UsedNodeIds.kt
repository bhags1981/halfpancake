package net.kanekolab.graph.permutation.model

/**
 * Created by bhags on 2016/11/24.
 */
object UsedNodeIds {
    private var _nodeMap:MutableMap<String,String> = mutableMapOf()
    private var _destinationNodeId:String=""
    fun init(){
        _nodeMap = mutableMapOf()
    }

    fun addNodeId(nodeId:String){
        if(_nodeMap.containsKey(nodeId)&&!nodeId.equals(_destinationNodeId))
            throw  Exception("Node id " + nodeId + " is already used.")
            //println("Node id " + nodeId + " is already used.")
        _nodeMap.put(nodeId,nodeId)
    }

    fun removeNodeId(nodeId:String){
        _nodeMap.remove(nodeId)
    }

    fun setDestinationNode(nodeId:String){
        _destinationNodeId = nodeId
    }

}
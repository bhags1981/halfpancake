package net.kanekolab.graph.permutation.model

/**
 * Created by bhags on 2016/11/24.
 */
object UsedNodeIds {
    private var _nodeMap:MutableMap<String,String> = mutableMapOf()
    private var _ignoreNodeId:String=""
    fun init(){
        _nodeMap = mutableMapOf()
    }

    fun addNodeId(nodeId:String){
        if(_nodeMap.containsKey(nodeId)&&!nodeId.equals(_ignoreNodeId)) {
            LogData.append("Node id ${nodeId} is already used.")
            //throw  Exception("Node id $nodeId is already used.")
        }

        _nodeMap.put(nodeId,nodeId)
    }

    fun removeNodeId(nodeId:String){
        _nodeMap.remove(nodeId)
    }

    fun setIgnoreNodeId(nodeId:String){
        _ignoreNodeId = nodeId
    }

}
package net.kanekolab.graph.permutation.model

/**
 * Created by bhags on 2016/11/24.
 */
object UsedNodes {
    var nodeMap:MutableMap<String,String> = mutableMapOf()
    fun addNodeId(nodeId:String){
        if(nodeMap.containsKey(nodeId))
//            throw Exception("Node id " + nodeId + " is already used.")
            println("Node id " + nodeId + " is already used.")
        nodeMap.put(nodeId,nodeId)
    }
}
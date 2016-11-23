package net.kanekolab.graph.permutation.model


/**
 *
 * 経路の中に閉路が存在しないパス
 * Created by bhags on 2016/11/23.
 */
class UniquePath(sourceNode: Node) : Path (sourceNode = sourceNode){

    override fun addNode(intermediateNode:Node){
        if(_pathList.contains(intermediateNode.getId())){
            throw Exception("The node  "+intermediateNode.getId()+" already exists in the path.")
        }
        super.addNode(intermediateNode)
    }
}
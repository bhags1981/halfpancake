package net.kanekolab.graph.permutation.model


/**
 *
 * 経路の中に閉路が存在しないパス
 * Created by bhags on 2016/11/23.
 */
class UniquePath(sourceNode: Node) : Path (sourceNode = sourceNode){

    override fun appendNode(intermediateNode:Node): Path{
        if(_pathList.contains(intermediateNode.getId())){
            throw Exception("The node  "+ intermediateNode.getId()+" already exists in the path.")
            //LogData.append("The node  "+intermediateNode.getId()+" already exists in the path.")
        }
        return super.appendNode(intermediateNode)
    }

    override fun prependNode(intermediateNode:Node) : Path{
        if(_pathList.contains(intermediateNode.getId()))
            throw Exception("The node  "+ intermediateNode.getId()+" already exists in the path.")
        return super.prependNode(intermediateNode)
    }
}
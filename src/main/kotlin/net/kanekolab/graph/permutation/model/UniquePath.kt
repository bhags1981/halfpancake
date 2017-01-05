package net.kanekolab.graph.permutation.model


/**
 *
 * 経路の中に閉路が存在しないパス
 * Created by bhags on 2016/11/23.
 */
class UniquePath(sourceNode: Node) : Path (sourceNode = sourceNode){

    override fun appendNode(node:Node): Path{
        if(_pathList.contains(node.getId())){
            throw Exception("The node  "+node.getId()+" already exists in the path.")
            //println("The node  "+node.getId()+" already exists in the path.")
        }
        return super.appendNode(node)
    }

    override fun prependNode(node:Node) : Path{
        if(_pathList.contains(node.getId()))
            throw Exception("The node  "+node.getId()+" already exists in the path.")
        return super.prependNode(node)
    }
}
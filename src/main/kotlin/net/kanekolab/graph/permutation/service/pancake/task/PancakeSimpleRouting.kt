package net.kanekolab.graph.permutation.service.pancake.task

import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.PrefixReversalOperator
import net.kanekolab.graph.permutation.model.UniquePath

/**
 * Created by bhags on 2016/11/24.
 */
class PancakeSimpleRouting (path: Path, destinationNode:Node ){
    private var _sourceNode:Node = path.getLastNode()
    private var _destinationNode:Node = destinationNode
    private var _path:Path = path

    fun run(){
        //Find index for reverse
        var currentNeighborNode = _sourceNode
        for(i in _destinationNode.getId().length - 1 downTo 1){
            var targetSymbol = _destinationNode.getId().get(i)

            for(j in  0 .. currentNeighborNode.getId().length){
                var currentSymbol = currentNeighborNode.getId().get(j)
                if(currentSymbol.equals(targetSymbol)){
                    if(i==j)break//No need to change

                    if(j!=0) {
                        currentNeighborNode = currentNeighborNode.getNthNeighbor(j)
                        _path.addNode(currentNeighborNode)
                    }

                    if(currentNeighborNode.getId().equals(_destinationNode.getId()))
                        return;

                    currentNeighborNode = currentNeighborNode.getNthNeighbor(i)
                    _path.addNode(currentNeighborNode)
                    break
                }
            }
        }
    }
    fun getPath():Path{
        return _path
    }
}
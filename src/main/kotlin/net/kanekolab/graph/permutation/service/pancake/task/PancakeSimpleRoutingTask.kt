package net.kanekolab.graph.permutation.service.pancake.task

import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.service.ServiceTask

/**
 * Created by bhags on 2016/11/24.
 */
class PancakeSimpleRoutingTask(path: UniquePath, destinationNode:Node ) : ServiceTask<PancakeSimpleRoutingTask,UniquePath>{
    private var _sourceNode:Node = path.getLastNode()
    private var _destinationNode:Node = destinationNode
    private var _path:UniquePath = path

    override fun executeTask():PancakeSimpleRoutingTask{
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
                        _path.appendNode(currentNeighborNode)
                    }

                    if(currentNeighborNode.getId().equals(_destinationNode.getId()))
                        return this
                    try {
                        currentNeighborNode = currentNeighborNode.getNthNeighbor(i)
                    }catch(e:IndexOutOfBoundsException){
                        println("Index outbound exception occurred ")
                        println("SOURCE " + _sourceNode)
                        println("DEST " + _destinationNode)
                    }
                    _path.appendNode(currentNeighborNode)
                    break
                }
            }
        }
        return this
    }
    override fun getResult():UniquePath{
        return _path
    }
}
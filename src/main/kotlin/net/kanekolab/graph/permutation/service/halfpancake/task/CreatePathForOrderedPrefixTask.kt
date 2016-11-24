package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode

/**
 *
 * index must be ~n (or ~n - 1  when n is even)
 * Created by bhags on 2016/11/23.
 */
class CreatePathForOrderedPrefixTask(graph:HalfPancakeGraph, path:UniquePath, isLeftCenter:Boolean = false){
    private val _graph:HalfPancakeGraph = graph
    private val _path:UniquePath = path
    private var _centerReverseIndex:Int = graph.getDegree()
    private val _isLeftCenter:Boolean = isLeftCenter
    private var _makeUniquePermutationNeighborNumber = 1
    fun run(){

        if(_graph.isEvenDimension()){
            if(!_isLeftCenter)
                runEvenType1()
            else
                runEvenType2()
        }else {
            runOdd()
        }
    }

    fun runEvenType1(){

        var sourceNode = _path.getDestinationNodeId() as HalfPancakeNode

        //Reverse on 2. Reverse on 2 is the first neighbor node.
        var neighborNode : HalfPancakeNode = sourceNode.getNthNeighbor(_makeUniquePermutationNeighborNumber) as HalfPancakeNode
        _path.addNode(neighborNode)

        var selectedNeighborNumber = _centerReverseIndex - 1
        //Reverse on ~n or ~n - 1
        neighborNode = neighborNode.getNthNeighbor(selectedNeighborNumber) as HalfPancakeNode
        _path.addNode(neighborNode)
    }

    fun runEvenType2(){
        var selectedNeighborNumber = _centerReverseIndex - 2
        var sourceNode = _path.getDestinationNodeId() as HalfPancakeNode

        //Reverse on 2. Reverse on 2 is the first neighbor node.
        var neighborNode : HalfPancakeNode = sourceNode.getNthNeighbor(_makeUniquePermutationNeighborNumber) as HalfPancakeNode
        _path.addNode(neighborNode)

        //Reverse on  ~n - 1
        neighborNode = neighborNode.getNthNeighbor(selectedNeighborNumber) as HalfPancakeNode
        _path.addNode(neighborNode)

        //Reverse on  ~n - 2
        neighborNode = neighborNode.getNthNeighbor(selectedNeighborNumber - 1) as HalfPancakeNode
        _path.addNode(neighborNode)


        //Reverse on ~n-1
        neighborNode = neighborNode.getNthNeighbor(selectedNeighborNumber) as HalfPancakeNode
        _path.addNode(neighborNode)
    }



    fun runOdd(){
        var selectedNeighborNumber = _centerReverseIndex - 1

        var sourceNode = _path.getDestinationNodeId() as HalfPancakeNode

        //Reverse on 2. Reverse on 2 is the first neighbor node.
        var neighborNode : HalfPancakeNode = sourceNode.getNthNeighbor(_makeUniquePermutationNeighborNumber) as HalfPancakeNode
        _path.addNode(neighborNode)

        //Reverse on ~n or ~n - 1
        neighborNode = neighborNode.getNthNeighbor(selectedNeighborNumber) as HalfPancakeNode
        _path.addNode(neighborNode)

        //Reverse on 2. Revers on 2 is the first neighbor node.
        neighborNode = neighborNode.getNthNeighbor(_makeUniquePermutationNeighborNumber) as HalfPancakeNode
        _path.addNode(neighborNode)

        //Reverse on ~n-1
        neighborNode = neighborNode.getNthNeighbor(selectedNeighborNumber - 1 ) as HalfPancakeNode
        _path.addNode(neighborNode)

        //Reverse on ~n
        neighborNode = neighborNode.getNthNeighbor(selectedNeighborNumber) as HalfPancakeNode
        _path.addNode(neighborNode)
    }


    fun getPath():Path{
        return _path
    }

}
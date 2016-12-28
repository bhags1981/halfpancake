package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.service.ServiceTask

/**
 * Created by bhags on 2016/11/23.
 */
class CreatePathToAllNeighborTask (halfPancakeGraph: HalfPancakeGraph, sourceNode: Node) :ServiceTask<MutableList<Path>>{
    private val _graph: HalfPancakeGraph = halfPancakeGraph
    private val _sourceNode: Node = sourceNode
    private val _pathList:MutableList<Path> = mutableListOf()



    override fun executeTask(){
        for( i in 1.._graph.getDegree()){
            var path = UniquePath(_sourceNode)
            path.addNode(_sourceNode.getNthNeighbor(i))
            _pathList.add(path)
        }
    }

    override fun getResult():MutableList<Path>{
        return _pathList
    }

}
package net.kanekolab.graph.permutation.service.halfpancake.task

import com.sun.tools.internal.xjc.reader.gbind.SourceNode
import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph

/**
 * Created by bhags on 2016/11/23.
 */
class CreatePathToAllNeighborTask (halfPancakeGraph: HalfPancakeGraph, sourceNode: Node){
    private val _graph: HalfPancakeGraph = halfPancakeGraph
    private val _sourceNode: Node = sourceNode
    private val _pathList:MutableList<Path> = mutableListOf()



    fun run(){
        for( i in 1.._graph.getDegree()){
            var path = UniquePath(_sourceNode)
            path.addNode(_sourceNode.getNthNeighbor(i))
            _pathList.add(path)
        }
    }

    fun getCurrentPath():MutableList<Path>{
        return _pathList
    }

}
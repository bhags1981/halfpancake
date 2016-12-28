package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.ServiceTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRouting

/**
 * Use for Case 1-1 (d ∈ P(s)) step 2.
 * Create path from s^n to d without use P(d) and P(s)
 * Created by bhags on 2016/11/24.
 */
class CreatePathFromSnToDTask(graph:HalfPancakeGraph, path:UniquePath, destinationNode:HalfPancakeNode) : ServiceTask<UniquePath>{
    private val _graph:HalfPancakeGraph = graph
    private val _path:UniquePath = path
    private val _destinationNode:HalfPancakeNode = destinationNode

    override fun executeTask(){
        var sourceNode = _path.getLastNode()

        //Do (S^n)^2
        var intermediateNode = sourceNode.getNthNeighbor(1)
        _path.addNode(intermediateNode)


        //Do ((S^n)^2)^n
        intermediateNode = intermediateNode.getNthNeighbor(_graph.getDegree())
        _path.addNode(intermediateNode)





        //For destination node
        //Do n
        var intermediateNode2 = _destinationNode.getNthNeighbor(_graph.getDegree())
        //Do 2
        var intermediateNode3 = intermediateNode2.getNthNeighbor(1)
        //Do n
        var intermediateNode4 = intermediateNode3.getNthNeighbor(_graph.getDegree())
        var pancakeSimpleRouting = PancakeSimpleRouting(_path,intermediateNode4)
        pancakeSimpleRouting.executeTask()

        _path.addNode(intermediateNode3)
        _path.addNode(intermediateNode2)
        _path.addNode(_destinationNode)
    }
    override fun getResult():UniquePath{
        return _path
    }
}
package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.ServiceTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask

/**
 * Use for Case 1-1 (d ∈ P(s)) step 2.
 * Step 2) Select path r1: s → s(n) → s(n,2) → s(n,2,n) ~> d(n,2,n) →d(n,2) →d(n) →d. See Figure 2.
 * Created by bhags on 2016/11/24.
 */
class CreatePathFromSnToDTask(graph:HalfPancakeGraph, path:UniquePath, destinationNode:HalfPancakeNode) : ServiceTask<CreatePathFromSnToDTask,UniquePath>{
    private val _graph:HalfPancakeGraph = graph
    private val _path:UniquePath = path
    private val _destinationNode:HalfPancakeNode = destinationNode

    override fun executeTask() : CreatePathFromSnToDTask{
        var sourceNode = _path.getLastNode()

        //Do (S^n)
        var intermediateNode = sourceNode.getNthNeighbor(sourceNode.getDegree())
        _path.appendNode(intermediateNode)

        //Do (S^n)^2
        intermediateNode = intermediateNode.getNthNeighbor(1)
        _path.appendNode(intermediateNode)


        //Do ((S^n)^2)^n
        intermediateNode = intermediateNode.getNthNeighbor(_graph.getDegree())
        _path.appendNode(intermediateNode)





        //For destination node
        //Do n
        var intermediateNode2 = _destinationNode.getNthNeighbor(_graph.getDegree())
        //Do 2
        var intermediateNode3 = intermediateNode2.getNthNeighbor(1)
        //Do n
        var intermediateNode4 = intermediateNode3.getNthNeighbor(_graph.getDegree())
        var pancakeSimpleRouting = PancakeSimpleRoutingTask(_path,intermediateNode4)
        pancakeSimpleRouting.executeTask()

        _path.appendNode(intermediateNode3)
        _path.appendNode(intermediateNode2)
        _path.appendNode(_destinationNode)
        return this
    }
    override fun getResult():UniquePath{
        return _path
    }
}
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
        var sourceNode = _path.getFirstNode() as HalfPancakeNode;
        var n = sourceNode.getLength()
        var _n = _destinationNode.getHalfPosition()

        if(_graph.isEvenDimension()
                && sourceNode.getSuffixForSubGraph().equals(_destinationNode.getFrontString().reversed())
                && _destinationNode.getSuffixForSubGraph().equals(sourceNode.getFrontString().reversed())
        ){
            //If P(s(n)) = P(d(n)), select path r1: s → s(n) → s(n,˜n) → s(n,˜n,2) → s(n,˜n,2,˜n)(= d(n)) → d.
            _path.append(n).append(_n).append(2).append(_n).append(n)
        }else {
            // s → s(n) → s(n,2) → s(n,2,n)
            _path.append(n).append(2).append(n)
            //d -> d->(n,2)
            var pathFromDest = UniquePath(_destinationNode).prepend(n).prepend(2)
            //s(n,2,n) ~> d(n,2,n)
            PancakeSimpleRoutingTask(_path, pathFromDest.getFirstNode().getNeighborByIndex(n)).executeTask()
            //Connect all path.
            _path.appendPath(pathFromDest)
        }

        return this
    }
    override fun getResult():UniquePath{
        return _path
    }
}
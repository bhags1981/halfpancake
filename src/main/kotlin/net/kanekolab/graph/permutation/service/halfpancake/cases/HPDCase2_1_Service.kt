package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.core.Constants
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CreatePathFromSnToDTask

/**
 * Created by bhags on 2017/01/03.
 */
class HPDCase2_1_Service(graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        var currentPath  = CreatePathFromSnToDTask(_currentGraph, UniquePath(_sourceNode), _destinationNode).executeTask().getResult()
        _disjointPaths.add(currentPath)
        for(i in 1.._sourceNode.getHalfPosition() - 1){
            var tmpPath  = UniquePath(_sourceNode).appendOmittedPathWithDescription(_destinationNode, Constants.pathPNN)
            _disjointPaths.add(tmpPath)
        }
    }
}
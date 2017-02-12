package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.core.Constants
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CreatePathFromSnToDTask

/**
 * Created by bhags on 2016/12/30.
 */
class HPDCase1_1_Service(graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        //Select path r1: s → s(n) → s(n;2) → s(n;2;n) ; d(n;2;n) →d(n;2) →d(n) →d. and terminate.
        //Other paths can find by Pancake N2N
        LogData.append("[HPDCase1_1_Service] started Case 1_1")

        var currentPath  = CreatePathFromSnToDTask(_currentGraph, UniquePath(_sourceNode),_destinationNode).executeTask().getResult()
        _disjointPaths.add(currentPath)
        for(i in 1.._sourceNode.getHalfPosition() - 1){
            var tmpPath  = UniquePath(_sourceNode).appendOmittedPathWithDescription(_destinationNode, Constants.pathPNN)
            _disjointPaths.add(tmpPath)
        }

    }



}
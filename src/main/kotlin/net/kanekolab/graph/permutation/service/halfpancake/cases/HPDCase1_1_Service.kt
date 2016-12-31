package net.kanekolab.graph.permutation.service.halfpancake.cases

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

        var currentPath  = CreatePathFromSnToDTask(_currentGraph, UniquePath(_sourceNode),_destinationNode).executeTask().getResult()
        _disjointPaths.add(currentPath)
    }



}
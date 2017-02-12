package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.core.Constants
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase13Step1ConditionTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask

/**
 *
 * Case 2-3 (s(n) ∈ P(d), d(n) ∈ P(s), and s(n) ̸= d)
 * 87645321 => 12345678
 * Created by bhags on 2016/12/30.
 */
class HPDCase2_3_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        LogData.append("[HPDCase2_3_Service] started Case 2_3")
        step1(_sourceNode,_destinationNode)
    }

    /**
     * Select path r1: s → s(n) → s(n,˜n) → s(n,˜n,2) → s(n,˜n,2,˜n)(= d).
     */
    private fun step1(sourceNode:HalfPancakeNode,destinationNode: HalfPancakeNode){
        LogData.append("Started Step 1")
        val n = _currentGraph.getDimension()
        val _n = _sourceNode.getHalfPosition()
        _disjointPaths.add(
                UniquePath(sourceNode)
                        .appendNodesByIndexes(n,_n,2,_n))
        step2(sourceNode,destinationNode)
    }

    /**
     * Select path r2: s → s(2) → s(2,n) → s(2,n,˜n−2) →s(2,n, ˜n−2, ˜n−1) → s(2,n,˜n−2, ˜n−1, ˜n)
     * → s(2,n,˜n−2,˜n−1,˜n,n) →s(2,n, ˜n−2, ˜n−1, ˜n,n,2)
     * → s(2,n,˜n−2, ˜n−1, ˜n,n,2,n)→s(2,n, ˜n−2, ˜n−1,˜n,n,2,n,˜n−1)(= d).
     *
     */
    private fun step2(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode){
        LogData.append("Started Step 2")
        val n = _currentGraph.getDimension()
        val _n = _sourceNode.getHalfPosition()
        _disjointPaths.add(
                UniquePath(sourceNode)
                        .appendNodesByIndexes(2,n,_n-2,_n-1,_n,n,2,n,_n-1))
        step3(sourceNode,destinationNode)
    }

    /**
     *
     * Select (˜n−4) paths ri: s→s(i)→s(i,n)→s(i,n,i)→s(i,n,i,n)
     * → s(i,n,i,n,i) → s(i,n,i,n,i,˜n) → s(i,n,i,n,i, ˜n,2)
     * → s(i,n,i,n,i, ˜n,2, ˜n) →s(i,n,i,n,i, ˜n,2, ˜n,n)
     * → s(i,n,i,n,i, ˜n,2, ˜n,n,i)(= d).(3 ≤ i ≤ ˜n−2).
     *
     */
    private fun step3(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode){
        LogData.append("Started Step 3")
        val n = _currentGraph.getDimension()
        val _n = _sourceNode.getHalfPosition()
        if(_n-4 > 1){
            for(i in 3.._n - 2){
                _disjointPaths.add(
                        UniquePath(sourceNode)
                                .appendNodesByIndexes(i,n,i,n,i,_n,2,_n,n,i))
            }
        }
        step4(sourceNode,destinationNode)
    }


    /**
     *
     * Step 4) Select path r ˜n−1: s→s( ˜n−1) →s( ˜n−1,n) →s( ˜n−1,n,2) → s( ˜n−1,n,2,n)
     * → s( ˜n−1,n,2,n, ˜n) → s( ˜n−1,n,2,n, ˜n, ˜n−1) → s( ˜n−1,n,2,n, ˜n, ˜n−1, ˜n−2)
     * →s( ˜n−1,n,2,n, ˜n, ˜n−1, ˜n−2,n) →s( ˜n−1,n, 2,n, ˜n, ˜n−1, ˜n−2,n,2)(= d).
     * 
     */
    private fun step4(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode){
        LogData.append("Started Step 4")
        val n = _currentGraph.getDimension()
        val _n = _sourceNode.getHalfPosition()
        _disjointPaths.add(
                UniquePath(sourceNode)
                        .appendNodesByIndexes(_n-1,n,2,n,_n,_n-1,_n-2,n,2))
        step5(sourceNode,destinationNode)
    }

    /**
     * Select path r ˜ n: s → s( ˜n) → s( ˜n,2) → s( ˜n,2,˜n) →s( ˜n,2,˜n,n)(= d).
     */
    private fun step5(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) {
        LogData.append("Started Step 5")
        val n = _currentGraph.getDimension()
        val _n = _sourceNode.getHalfPosition()
        _disjointPaths.add(
                UniquePath(sourceNode)
                        .appendNodesByIndexes(_n, 2, _n, n))

    }
}
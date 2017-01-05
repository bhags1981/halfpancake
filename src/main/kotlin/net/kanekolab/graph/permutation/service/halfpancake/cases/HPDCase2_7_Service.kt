package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.core.Constants
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase13Step1ConditionTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask

/**
 * Created by bhags on 2016/12/30.
 * Case 2-7 (s(˜n,n) ∈ P(d) and d(n) ̸∈ P(s))
 */
class HPDCase2_7_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        LogData.append("[HPDCase2_5_Service] Start construct disjoint paths between {${_sourceNode.getId()}} and {${_destinationNode.getId()}} by Case 2-4 service.")
        constructSubPaths()
        constructPNS()
    }

    private fun constructSubPaths(){
        /**
         * Do not check following condition.
         * Select path first then check d is selected or not.
         * {(sn−1, sn, sn−2, ... , s˜n+1, s1, s2),(sn−2, sn−1, sn, sn−3, ... , s˜n+1, s1, s2), ... , (s˜n+1, ... , sn,s1, s2), (s1, sn, ... , s˜n+1, s2), (sn, ... , s˜n+1, s2, s1)},
         */
        val n = _currentGraph.getDimension()
        val _n = _sourceNode.getHalfPosition()

        //Select path s˜n: s→s(˜n) →s(˜n,n) def = a˜n.
        var tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(_n,n)
        _disjointPaths.add(tmpPath)

        //Select ˜n−3 paths
        //Select (˜n − 3) sub paths si: s → s(i) → s(i,n) → s(i,n,i) → s(i,n,i,n) → s(i,n,i,n,i) → s(i,n,i,n,i,˜n) →
        var isDestinationSelected = false
        for(i in 2.._n-2){
            tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(i,n,i,n,i,_n,n)
            isDestinationSelected = if(!isDestinationSelected)tmpPath.getLastNode().equalId(_destinationNode) else isDestinationSelected
            _disjointPaths.add(tmpPath)
        }

        //  Select path s˜n−1: s → s(˜n−1) → s(˜n−1,n) → s(˜n−1,n,˜n−1) 
        // →s(˜n−1,n,˜n−1,˜n) →s(˜n−1,n,˜n−1,˜n,n) →s(˜n−1, n,˜n−1,˜n,n,˜n−2)
        // →s(˜n−1,n,˜n−1,˜n,n,˜n−2,˜n−1)→s(˜n−1,n,˜n−1,˜n,n,˜n−2,˜n−1,n) def = a˜n−1.
        tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(_n-1,n,_n-1,_n,n,_n-2,_n-1,n)
        isDestinationSelected = if(!isDestinationSelected)tmpPath.getLastNode().equalId(_destinationNode) else isDestinationSelected
        _disjointPaths.add(tmpPath)


        //Find last path from here.

        //Select s1: s → s(n) → s(n, 2) → s(n, ˜n−1,n) → s(n, ˜n−1,n, ˜n−1) →s(n, ˜n−1,n, ˜n−1,n) def = a1.
        if(isDestinationSelected){
            _disjointPaths.add(
                    UniquePath(_sourceNode)
                            .appendNodesByIndexes(n,2,3,2,n,_n,n)
            )
            return
        }



        //Select path to d without using p(d)
        tmpPath = UniquePath(_sourceNode)
        tmpPath.appendNodesByIndexes(n,2,3,2,n)
        var a = tmpPath.getLastNode() as HalfPancakeNode

        //Already joined same pancake graph.
        if(a.getSuffixForSubGraph().equals(_destinationNode.getFrontString().reversed())){
            PancakeSimpleRoutingTask(tmpPath,_destinationNode.getIthPrefixReversalNeighbor(n)).executeTask()
            tmpPath.appendNode(_destinationNode)
            return;
        }

        //Find by another method.
        var pathFromDst = UniquePath(_destinationNode).prependNodesByIndexes(n,2,3)
        var da = pathFromDst.getFirstNode().getNeighborByIndex(n) as HalfPancakeNode
        var suffix = da.getSuffixForSubGraph()
        var element1:Char?=null
        var element2:Char?=null

        a.getFrontCenterString().forEach{
            element->
            if(!suffix.contains(element)){
                if(element1 == null) element1 = element
                else if(element2 == null) element2 = element
            }
        }

        var a2:HalfPancakeNode = _currentGraph.getNodeById(suffix.reversed()+element1+element2+a.getRearString()) as HalfPancakeNode
        PancakeSimpleRoutingTask(tmpPath,a2).executeTask()
        tmpPath.append(n)
        PancakeSimpleRoutingTask(tmpPath,da).executeTask()
        tmpPath.appendPath(pathFromDst)
        _disjointPaths.add(tmpPath)

        return
    }

}
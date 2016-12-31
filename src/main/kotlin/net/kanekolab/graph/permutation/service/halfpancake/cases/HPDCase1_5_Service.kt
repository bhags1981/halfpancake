package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase14Step1ConditionTask
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase15Step1ConditionTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask

/**
 * Created by bhags on 2016/12/30.
 * Case 1-5 (s( ˜n,n) ∈ P(d) and s( ˜n,n) ̸= d)
 */
class HPDCase1_5_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    private var _positionL = -1
    override fun constructDisjointPaths() {
        _logData.append("Start construct disjoint paths between {${_sourceNode.getId()}} and {${_destinationNode.getId()}} by Case 1-5 service.")
        step1(_sourceNode,_destinationNode)
    }


    /**
     * If (1,2, ... , ˜ n − 1) ∈ {(sn−1, sn, sn−2 ... , s ˜ n+1), (sn−2, sn−1, sn, sn−3, ... , s ˜ n+1), ... , (s ˜ n+1, s ˜ n+2, ... , sn)}, go to Step 7.
     */
    private fun step1(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode){
        var (isExist,positionL) = CheckCase15Step1ConditionTask(sourceNode, destinationNode).executeTask().getResult()
        _logData.append("[Step 1] Is exist position L ? {$positionL} (-1 is false) ")
        if(isExist == true){
            _positionL = positionL
            //step7(sourceNode,destinationNode,positionL)
        }else{
            step2(sourceNode,destinationNode)
        }
    }

    /**
     * Step 2)
     * If s1 = ˜n, select path r1: s → s(n) , a → a(n) →a(n, ˜ n)(= d(n)) → d where a = (1, ...  , ˜ n − 1,n,n −1, ...  , ˜ n).
     * Otherwise, assume that sl = ˜n ( ˜n<l ≤ n), 
     * and select path r1: s→s(n) →s(n, ˜ n−l+1) →s(n, ˜ n−l+1, ˜ n) → s(n, ˜ n−l+1, ˜ n,n) → s(n, ˜ n−l+1, ˜ n,n, ˜ n) → s(n, ˜ n−l+1, ˜ n,n, ˜ n,n) ,
     * b → b(n) → b(n, ˜ n) → b(n, ˜ n, ˜ n−1) → b(n, ˜ n, ˜ n−1, ˜ n−2) → b(n, ˜ n, ˜ n−1, ˜ n−2, ˜ n−1)(= d(n))→d where b = (1,2, ... , ˜ n− 1,n, ˜ n+1, ... ,n−1, ˜ n).
     */
    private fun step2(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()
        var _nthChar:Char = destinationNode.getId().get(_n-1)
        var l = sourceNode.getId().indexOf(_nthChar) + 1
        var pathFromSource = UniquePath(sourceNode)

        if(l == 1){
            //If s1 = ˜ n, select path r1: s → s(n) , a → a(n) → a(n, ˜ n)(= d(n)) → d where a = (1, ... , ˜ n − 1;n;n − 1, ... , ˜ n).

            //s(n)
            var intermediateNode = sourceNode.getIthPrefixReversalNeighbor(n)
            pathFromSource.appendNode(intermediateNode)


            var pathFromDestination = UniquePath(destinationNode)
            //d(n)
            var intermediateNode2 = destinationNode.getIthPrefixReversalNeighbor(n)
            pathFromDestination.prependNode(intermediateNode2)

            //d(n,~n)
            intermediateNode2 = intermediateNode2.getIthPrefixReversalNeighbor(_n)
            pathFromDestination.prependNode(intermediateNode2)

            //d(n,~n,n) = a
            intermediateNode2 = intermediateNode2.getIthPrefixReversalNeighbor(n)

            //s(n) ~> a
            pathFromSource = PancakeSimpleRoutingTask(pathFromSource,intermediateNode2).executeTask().getResult()
            pathFromSource.appendPath(pathFromDestination)


        }else{
            /**
            // This Make Path long.
            s→s(n) →s(n, ˜ n−l+1) →s(n, ˜ n−l+1, ˜ n) →  s(n, ˜ n−l+1, ˜ n;n) → s(n, ˜ n−l+1, ˜ n;n, ˜ n) → s(n, ˜ n−l+1, ˜ n,n, ˜ n,n),
            b → b(n) → b(n, ˜ n) → b(n, ˜ n, ˜ n−1) → b(n, ˜ n, ˜ n−1, ˜ n−2) →
            b(n, ˜ n, ˜ n−1, ˜ n−2, ˜ n−1)(= d(n))→d where b = (1;2, ... , ˜ n−1,n, ˜ n+1, ... ,n−1, ˜ n)
            */
            //s(n)
            var intermediateNode = sourceNode.getIthPrefixReversalNeighbor(n)
            pathFromSource.appendNode(intermediateNode)

            //s(n,n-l+1)
            var pos = (l-_n) + 2
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(pos)
            pathFromSource.appendNode(intermediateNode)

            //s(n,~n-l+1,~n)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(_n)
            pathFromSource.appendNode(intermediateNode)

            //s(n,~n-l+1,~n,n)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
            pathFromSource.appendNode(intermediateNode)





            var pathFromDestination = UniquePath(destinationNode)

            //d(n)
            var intermediateNode2 = destinationNode.getIthPrefixReversalNeighbor(n)
            pathFromDestination.prependNode(intermediateNode2)

            //d(n,~n-1)
            intermediateNode2 = intermediateNode2.getIthPrefixReversalNeighbor(_n-1)
            pathFromDestination.prependNode(intermediateNode2)

            //d(n,~n-1,~n-2)
            intermediateNode2 = intermediateNode2.getIthPrefixReversalNeighbor(_n-2)
            pathFromDestination.prependNode(intermediateNode2)

            //d(n,~n-1,~n-2,~n-1)
            intermediateNode2 = intermediateNode2.getIthPrefixReversalNeighbor(_n-1)
            pathFromDestination.prependNode(intermediateNode2)

            //d(n,~n-1,~n-2,~n-1,~n)
            intermediateNode2 = intermediateNode2.getIthPrefixReversalNeighbor(_n)
            pathFromDestination.prependNode(intermediateNode2)

            //d(n,~n-1,~n-2,~n-1,~n,n) = b
            intermediateNode2 = intermediateNode2.getIthPrefixReversalNeighbor(n)

            //s(n,~n-l+1,~n,n) ~> b
            pathFromSource = PancakeSimpleRoutingTask(pathFromSource,intermediateNode2).executeTask().getResult()
            pathFromSource.appendPath(pathFromDestination)


        }
        _logData.append("[Step 2] ~n position is ${l} Selected Path ${pathFromSource.getList().toString()}")



    }//987654321


}
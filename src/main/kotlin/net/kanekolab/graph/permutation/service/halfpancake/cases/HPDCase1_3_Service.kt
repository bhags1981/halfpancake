package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase13Step1ConditionTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask

/**
 * Created by bhags on 2016/12/30.
 */
class HPDCase1_3_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){
    override fun constructDisjointPaths() {
        LogData.append("Start construct disjoint paths between {${_sourceNode.getId()}} and {${_destinationNode.getId()}} by Case 1-3 service.")
        step1()
    }

    /**
     * If (1,2,... , ˜n) ∈ {(s˜n, s˜n+1,...,sn ),(s˜n+1,s˜n+2,...,sn; s ˜ n),
     * (s˜n+2, s˜n+3,...,sn, s˜n+1, s˜n),...,(sn−1, sn, sn−2,sn−3,..., s˜n+1, s˜n)}, go to Step 7.
     * else go to step 2
     */
    private fun step1(){

        var (isExist,positionL) = CheckCase13Step1ConditionTask(_sourceNode, _destinationNode).executeTask().getResult()
        LogData.append("[Step 1] Is exist position L ? {$positionL} (-1 is false) ")
        if(isExist == true){
            step7(positionL)
        }else{
            step2(_sourceNode,_destinationNode)
        }

    }


    /**
     * Select path r2: s→s(2)→s(2,n)→s(2,n,˜n),a2→ a2(n)→ a2(n,2)→ a2(n,2,n) = d where a2 = (1,2,...,n−2,n,n−1).
     */
    private fun step2(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode){
        var n = sourceNode.getLength()
        var _n = _sourceNode.getHalfPosition()

        var tmpPathFromSourceNode = UniquePath(sourceNode).append(2).append(n)//.append(_n)


        var tmpPathFromDestNode = UniquePath(destinationNode).prepend(n).prepend(2)
        //create a2.

        //Create path from //s(2,n,~n) ~> a2
        tmpPathFromSourceNode = PancakeSimpleRoutingTask(tmpPathFromSourceNode as UniquePath,tmpPathFromDestNode.getFirstNode().getNeighborByIndex(n)).executeTask().getResult()
        tmpPathFromSourceNode.appendPath(tmpPathFromDestNode)
        LogData.append("[Step 2] Select path p2 result =  ${tmpPathFromSourceNode}")
        _disjointPaths.add(tmpPathFromSourceNode)
        step3(sourceNode,destinationNode)
    }

    /**
     * 3) Select si: s → s(i) → s(i,n) → s(i,n, ˜n−i+2) → s(i,n, ˜n−i+2,n) → s(i,n, ˜n−i+2,n,i) → s(i,n, ˜ n−i+2,n,i,n) def = ai (3 ≤ i ≤ ˜n).
     */
    private fun step3(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode){
        val n = sourceNode.getLength()
        val _n = _sourceNode.getHalfPosition()

        for(i in 3..sourceNode.getHalfPosition()){
            var tmpPath = UniquePath(sourceNode)

            //s(i)
            var intermediateNode = sourceNode.getIthPrefixReversalNeighbor(i)
            tmpPath.appendNode(intermediateNode)

            //s(i,n)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,~n-i+2)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(_n-i+2)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,~n-i+2,n)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,~n-i+2,n,i)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(i)
            tmpPath.appendNode(intermediateNode)

            //ai := s(i,n,~n-i+2,n,i,n)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
            tmpPath.appendNode(intermediateNode)
            LogData.append("[Step 3] Select [$i]th path result = ${tmpPath.getList()}")
            _disjointPaths.add(tmpPath)
        }
        step4(sourceNode,destinationNode)
    }
    //Step 4) Select s1: s→s(n) def = a1 .
    private fun step4(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode){
        var tmpPath = UniquePath(sourceNode)
        val n = sourceNode.getLength()

        //Append a1
        tmpPath.appendNode(sourceNode.getIthPrefixReversalNeighbor(n))
        LogData.append("[Step 4] Select path result = ${tmpPath.getList()}")
        _disjointPaths.add(tmpPath)
        step5()
    }

    //This
    private fun step5(){
        for(index in 0.._disjointPaths.size - 1){
            var path = _disjointPaths.get(index)
            if(!path.getLastNode().getId().equals(_destinationNode.getId()))
                path.appendOmittedPathWithDescription(_destinationNode,"~PNS~")
            LogData.append("[Step 5] Path[${index+1}] : ${_disjointPaths.get(index).toString()}")
        }
        step6()
    }

    /**
    Connect si and ti to obtain ( ˜ n−1) paths ri between
    s and d (1 ≤ i ̸= 2 ≤ ˜ n). Terminate.
     //Actually nothing tod.
    **/
    private fun step6(){
        LogData.append("[Step 6] Finished construct disjoint paths from {${_sourceNode.getId()}} to {${_destinationNode.getId()}} - Terminate.")
    }

    /**
     * Actually following step does not need to ru.
     * step8 can find following path.
     * Assume that (1;2; : : : ; ˜ n) = (s ˜ n+l−2; : : : ; sn; s ˜ n+l−3;
     *: : : ; s ˜ n). Select path rl : s → s(l) → s(l;n) → s(l;n;
     *˜ n−l+2) →s(l;n; ˜ n−l+2;n) →s(l;n; ˜ n−l+2;n;l) →s(l;n; ˜ n−l+2;n;l; n)(= d).
     */
    private fun step7(positionL : Int){
        LogData.append("[Step 7] Skip step7. Can find {$positionL}th path by step8.")
        step8(_sourceNode,_destinationNode)
    }


    /**
     * Step 8) Select ( ˜ n − 2) sub paths si: s → s(i) →
     * s(i;n) → s(i;n; ˜ n−i+2) → s(i;n; ˜ n−i+2;n) → s(i;n; ˜ n−i+2;n;i) →
     * s(i;n; ˜ n−i+2;n;i;n) def = ai (2 ≤ i ̸= l ≤ ˜ n).
     */
    private fun step8(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode){
        val n = sourceNode.getLength()
        val _n = _sourceNode.getHalfPosition()

        for(i in 2..sourceNode.getHalfPosition()){
            var tmpPath = UniquePath(sourceNode)

            //s(i)
            var intermediateNode = sourceNode.getIthPrefixReversalNeighbor(i)
            tmpPath.appendNode(intermediateNode)

            //s(i,n)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,~n-i+2)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(_n-i+2)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,~n-i+2,n)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
            tmpPath.appendNode(intermediateNode)

            //s(i,n,~n-i+2,n,i)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(i)
            tmpPath.appendNode(intermediateNode)

            //ai := s(i,n,~n-i+2,n,i,n)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
            tmpPath.appendNode(intermediateNode)
            LogData.append("[Step 8] Select [$i]th path result = ${tmpPath.getList()}")
            _disjointPaths.add(tmpPath)
        }
        step9(sourceNode,destinationNode)
    }

    //Step 9) Select s1: s→s(n) def = a1 of length 1.
    private fun step9(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode){
        var tmpPath = UniquePath(sourceNode)
        val n = sourceNode.getLength()

        //Append a1
        tmpPath.appendNode(sourceNode.getIthPrefixReversalNeighbor(n))
        LogData.append("[Step 9] Select path result = ${tmpPath.getList()}")
        _disjointPaths.add(tmpPath)
        step10()
    }

    /**
     * Step 10) Apply Algorithm PNS in P(d) to obtain (˜n−1) disjoint sub paths ti: ai ;d (1 ≤ i ̸= l ≤ ˜ n).
     */
    private fun step10(){
        for(index in 0.._disjointPaths.size - 1){
            var path = _disjointPaths.get(index)
            if(!path.getLastNode().getId().equals(_destinationNode.getId()))
                path.appendOmittedPathWithDescription(_destinationNode,"~PNS~")
            LogData.append("[Step 10] Path[${index+1}] : ${_disjointPaths.get(index).toString()}")
        }
        step11()
    }

    private fun step11(){
        LogData.append("[Step 11] Finished construct disjoint paths from {${_sourceNode.getId()}} to {${_destinationNode.getId()}} - Terminate.")

    }

}
package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.core.Constants
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase13Step1ConditionTask
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckCase14Step1ConditionTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask

/**
 * Created by bhags on 2016/12/30.
 *
 * Case 1-4 (∃k(< ˜n) such that s^(k,n) ∈ P(d) and s^(k,n) ̸=d)
 */
class HPDCase1_4_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode,positionK:Int) : HPDCaseService(graph,sourceNode,destinationNode){
    private val _positionK = positionK
    private var _positionL = -1
    override fun constructDisjointPaths() {
        LogData.append("Start construct disjoint paths between {${_sourceNode.getId()}} and {${_destinationNode.getId()}} by Case 1-4 service.")
        step1(_sourceNode,_destinationNode,_positionK)
    }


    /**
     *
     * If (1,2,... , ˜ n) ∈ {(s ˜ n, s ˜ n+1,... , sn), (s ˜ n+1, s ˜ n+2,... ,sn, s ˜ n), (s ˜ n+2, s ˜ n+3,... , sn, s ˜ n+1, s ˜ n),... ,
     * (s ˜ n+k−3,... , sn,s ˜ n+k−4,... , s ˜ n), (s ˜ n+k−1,... , sn, s ˜ n+k−2,... , s ˜ n),... ,(sn−1, sn, sn−2, sn−3,... , s ˜ n+1, s ˜ n)},`
     *
     *  Select path r1: s → s(n) , an → a(n) n → a(n,k) n (=d(n))→d where an = (1,2,... ,n−k,n,n−1,... ,n−k+1).
     */
    private fun step1(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode,positionK: Int){
        var (isExist,positionL) = CheckCase14Step1ConditionTask(sourceNode,destinationNode,positionK).executeTask().getResult()
        LogData.append("[Step 1] Is exist position L ? {$positionL} (-1 is false) ")
        if(isExist == true){
            _positionL = positionL
            step6(sourceNode,destinationNode,positionK,positionL)
        }else{
            step2(sourceNode,destinationNode,positionK)
        }
    }


    //Step 2) Select path r1: s → s(n) ~> an → an^(n) → an^(n,k)(= d^(n))→d where an = (1,2,...,n−k,n,n−1,...,n−k+1)
    private fun step2(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode, positionK: Int){

        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()

        var pathFromSourceNode = UniquePath(sourceNode)

        //s(n)
        var intermediateNode = sourceNode.getIthPrefixReversalNeighbor(n)
        pathFromSourceNode.appendNode(intermediateNode)



        var pathFromDestinationNode = UniquePath(destinationNode)

        //d(n)
        var intermediateNode2 = destinationNode.getIthPrefixReversalNeighbor(n)
        pathFromDestinationNode.prependNode(intermediateNode2)

        //d(n,k)
        intermediateNode2 = intermediateNode2.getIthPrefixReversalNeighbor(positionK)
        pathFromDestinationNode.prependNode(intermediateNode2)

        //d(n,k,n) = an
        intermediateNode2 = intermediateNode2.getIthPrefixReversalNeighbor(n)


        //s(n) ~> an
        pathFromSourceNode = PancakeSimpleRoutingTask(pathFromSourceNode,intermediateNode2).executeTask().getResult()

        //Connect all paths
        pathFromSourceNode.appendPath(pathFromDestinationNode)

        _disjointPaths.add(pathFromSourceNode)
        LogData.append("[Step 2] Select Path  ${pathFromSourceNode.getList()}")

        step3(sourceNode,destinationNode,positionK)

    }
    /**
    Step 3) Select (˜n−1)
    sub paths si: s → s(i) → s(i,n)
    → s(i,n,˜n−i+2) → s(i,n,˜n−i+2,n)
    → s(i,n,˜n−i+2,n,i) → s(i,n, ˜n−i+2,n,i,k)
    → s(i,n,˜n−i+2,n,i,k,n) (2 ≤ i ≤ ˜n).
    */
    private fun step3(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode, positionK: Int){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()

        for (i in 2..sourceNode.getHalfPosition()){
            //s(i),s(i,n)
            var tmpPath = UniquePath(sourceNode).append(i).append(n)


            if(_positionK != i ) {
                //s(i,n,~n-i+2,k,n)
                tmpPath.append(_n-i + 2)

                //Avoid conflict
                /**
                [678951234, 432159876, 123459876, >678954321<, 987654321, 123456789]
                [678951234, 768951234, 432159867, 512349867, 768943215, 678943215, ~PNS~, 123456789]
                [678951234, 876951234, 432159678, 123459678, 876954321, >678954321<, ~PNS~, 123456789]
                [678951234, 987651234, 432156789, ~PNS~, 123456789]
                [678951234, 598761234, 432167895, 342167895, 598761243, 678951243, ~PNS~, 123456789]
                 *
                 *
                 * */

                if((tmpPath.getLastNode() as HalfPancakeNode).getFrontString().equals(destinationNode.getFrontString())){
                   tmpPath.append(i)
                }
                tmpPath.append(n).append(i)

                //.append(positionK).append(n)
            }

            _disjointPaths.add(tmpPath)

            LogData.append("[Step 3] Select Path  ${tmpPath.getList()}")

        }
        step4()
    }

    /**
     * Step 4) Apply Algorithm PNS in P(d) to obtain (˜n−1) disjoint sub paths ti :  s(i,n, ˜n−i+2,n,i,k,n) ~> d (2 ≤ i ≤ ˜n).
     */
    private fun step4(){
        for(index in 0.._disjointPaths.size - 1){
            var path = _disjointPaths.get(index)
            if(!path.getLastNode().getId().equals(_destinationNode.getId()))
                path.appendOmittedPathWithDescription(_destinationNode, Constants.pathPNS)
            LogData.append("[Step 4] Path[${index+1}] : ${_disjointPaths.get(index).toString()}")
        }
        step5()
    }

    /**
     * Step 5) Connect si and ti to obtain ( ˜ n−1) paths ri (2 ≤ i ≤ ˜ n). Terminate.
     */
    private fun step5(){
        LogData.append("[Step 5] Finished construct disjoint paths from {${_sourceNode.getId()}} to {${_destinationNode.getId()}} - Terminate.")
    }

    /**
     * 897651234  = n~+1...n~ l = 3
     * 123456789
     * Step 6) Assume that (1,2, ...,˜n) = (s˜n+l−2,...,sn,s ˜n+l−3,...,s˜n).
     * Select path rl : s → s^(l) → s^(l,n) → s^(l,n,˜n−l+2) →s^(l,n,˜n−l+2,n) →s^(l,n,˜ n−l+2,n,l) →s^(l,n,˜ n−l+2,n,l,k) →s^(l,n,˜ n−l+2,n,l,k,n)(= d).
     */
    private fun step6(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode, positionK:Int, positionL:Int){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()
        var tmpPath = UniquePath(sourceNode)

        //s(l)
        var intermediateNode = sourceNode.getIthPrefixReversalNeighbor(positionL)
        tmpPath.appendNode(intermediateNode)

        //s(l,n)
        intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
        tmpPath.appendNode(intermediateNode)

        //s(l,n,~n-l+2)
        intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(_n - positionL  + 2)
        tmpPath.appendNode(intermediateNode)

        //s(l,n,~n-l+2,n)
        intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
        tmpPath.appendNode(intermediateNode)

        //s(l,n,~n-l+2,n,l)
        intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(positionL)
        tmpPath.appendNode(intermediateNode)

        //s(l,n,~n-l+2,n,l,k)
        intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(positionK)
        tmpPath.appendNode(intermediateNode)

        //s(l,n,~n-l+2,n,l,k,n) = d
        intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
        tmpPath.appendNode(intermediateNode)

        _disjointPaths.add(tmpPath)

        LogData.append("[Step 6] Select Path  ${tmpPath.getList().toString()}")
        step7(sourceNode,destinationNode,positionK,positionL)
    }

    /**
     * Step 7) Select s1: s→s(n) →s(n,˜n−k+2) →s(n,˜n−k+2,n) →s(n,˜n−k+2,n,k) →s(n, ˜ n−k+2,n,k,n) def = a1.
     */
    private fun step7(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode, positionK:Int, positionL:Int){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()
        var tmpPath = UniquePath(sourceNode)

        //Select s(n)
        var intermediateNode = sourceNode.getIthPrefixReversalNeighbor(n)
        tmpPath.appendNode(intermediateNode)

        //s(n,~n-k+2)
        intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(_n-positionK+2)
        tmpPath.appendNode(intermediateNode)

        //s(n,~n-k+2,n)
        intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
        tmpPath.appendNode(intermediateNode)

        //s(n,~n-k+2,n,k)
        intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(positionK)
        tmpPath.appendNode(intermediateNode)


        //s(n,~n-k+2,n,k,n)
        intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
        tmpPath.appendNode(intermediateNode)

        _disjointPaths.add(tmpPath)
        LogData.append("[Step 7] Select Path  ${tmpPath.getList().toString()}")

        step8(sourceNode,destinationNode,positionK,positionL)
    }

    /**
     * Step 8) Select ( ˜n − 2) sub paths
     * si: s → s(i) → s(i,n) → s(i,n, ˜ n−i+2) → s(i,n, ˜ n−i+2,n) → s(i,n, ˜ n−i+2,n,i) → s(i,n, ˜ n−i+2,n,i,k) →s(i,n, ˜ n−i+2,n,i,k,n) def = ai (2 ≤ i ̸= l ≤ ˜ n).
     */
    private fun step8(sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode, positionK:Int, positionL:Int){
        val n = sourceNode.getLength()
        val _n = sourceNode.getHalfPosition()


        for (i in 2 .. _n){
            if(i == positionL)continue

            var tmpPath = UniquePath(sourceNode)

            //s(i)
            var intermediateNode = sourceNode.getIthPrefixReversalNeighbor(i)
            tmpPath.appendNode(intermediateNode)

            //s(i,n)
            intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
            tmpPath.appendNode(intermediateNode)


            if(i !== positionK) {
                //s(i,n,~n-i+2)
                intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(_n - i + 2)
                tmpPath.appendNode(intermediateNode)

                //s(i,n,~n-i+2,n)
                intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
                tmpPath.appendNode(intermediateNode)

                //s(i,n,~n-i+2,n,i)
                intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(i)
                tmpPath.appendNode(intermediateNode)

                //s(i,n,~n-i+2,n,i,k)
                intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(positionK)
                tmpPath.appendNode(intermediateNode)

                //ai := s(,n,~n-i+2,n,i,k,n)
                intermediateNode = intermediateNode.getIthPrefixReversalNeighbor(n)
                tmpPath.appendNode(intermediateNode)
            }
            _disjointPaths.add(tmpPath)
            LogData.append("[Step 8] Select Path[$i]  ${tmpPath.getList().toString()}")

        }
        step9()
    }
    /**
    Step 9) Apply Algorithm PNS in P(d) to obtain ( ˜ n−1) disjoint sub paths ti: ai ;d (1 ≤ i ̸= l ≤ ˜ n).
     */
    private fun step9(){
        for(index in 0.._disjointPaths.size - 1){
            var path = _disjointPaths.get(index)
            if(!path.getLastNode().getId().equals(_destinationNode.getId()))
                path.appendOmittedPathWithDescription(_destinationNode,Constants.pathPNS)
            LogData.append("[Step 10] Path[${index+1}] : ${_disjointPaths.get(index).toString()}")
        }
        step10()
    }

    /**
     * Step 10) Connect si and ti to obtain the ( ˜ n−1) paths ri (1 ≤ i ̸= l ≤ ˜ n).
     */
    private fun step10(){
        LogData.append("[Step 10] Finished construct disjoint paths from {${_sourceNode.getId()}} to {${_destinationNode.getId()}} - Terminate.")

    }
}

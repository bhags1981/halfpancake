package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckRdIncludedInFsCsTask
import net.kanekolab.graph.permutation.service.halfpancake.task.FindS2NodeTask
import net.kanekolab.graph.permutation.service.halfpancake.task.FindSmallestShInRdAndFsCsSymbolTask
import net.kanekolab.graph.permutation.service.halfpancake.task.FindSmallestShNotInRdAndFsCsSymbolTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask
import sun.rmi.runtime.Log

/**
 * Created by sinyu on 2016/12/28.
 *
 * 3. Restricted routing in a half pancake graph
    For a source node s = (s1,s2,...,sn), a destination node
    d = (d1,d2,...,dn), two permutations of (n − n˜) integers
    a = (a1,a2,...,a_(n−n˜)) and b = (b1,b2,...,b_(n−n˜)),
and an integer i (1 ≤ i ≤ n˜), we give an algorithm, HR, that returns
a path unique to i from s to d without visiting two
pancake graphs that are induced by the nodes whose rightmost
permutations of (n−n˜) elements are (a1,a2,...,an−n˜)
and (b1,b2,...,bn−n˜). This algorithm can be invoked by
HR(s,d,a,b,i).
 *
 */
class HPRestrictedRoutingService(graph: HalfPancakeGraph, currentPathIdentifierI:Int, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode,avoidSuffixA:String,avoidSuffixB:String) {
    private val _currentGraph = graph
    private val _graphDimension = graph.getDimension()
    private var _originalSourceNode: HalfPancakeNode = sourceNode
    private var _originalDestinationNode: HalfPancakeNode = destinationNode
    private var _currentPathIdentifierI : Int = currentPathIdentifierI
    private var _avoidSuffixA : String = avoidSuffixA
    private var _avoidSuffixB : String = avoidSuffixB
    private var _currentPath: UniquePath = UniquePath(sourceNode)
    private var _isTerminated = false
    private var _hpUniquePermutationService = HPUniquePermutationService()

//    fun setSourceNode(node:HalfPancakeNode){ _sourceNode = node }
//    fun setDestinationNode(node:HalfPancakeNode){ _destinationNode = node }
    fun getCurrentPath():Path {  return _currentPath }




    fun findUniquePath(){
        LogData.append("[HPRestrictedRoutingService] Started find unique path between " + _originalSourceNode?.getId() + " and " + _originalDestinationNode?.getId())
        //Check Odd OR Even
        if(_graphDimension %2==0) {
            findUniquePathForEven(_originalSourceNode, _originalDestinationNode)
        } else {
            findUniquePathForOdd(_originalSourceNode, _originalDestinationNode)
        }
        appendFinishedLog()
    }

    private fun findUniquePathForOdd(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode){
        LogData.append("Started findUniquePathForOdd")

        //Check P(s) and P(d)
        //Step 1. If P(s) = P(d), select s ~> d in P(s), and terminate
        findUniquePathForOddStep1(sourceNode,destinationNode)
    }



    //Check P(s) and P(d)
    //Step 1. If P(s) = P(d), select s ~> d in P(s), and terminate
    private fun findUniquePathForOddStep1(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode){
        LogData.append("Started findUniquePathForOddStep1")
        //Check P(s) and P(d)
        if(sourceNode.getSuffixForSubGraph() == destinationNode.getSuffixForSubGraph()){
            LogData.append("P(s) and P(d) is same.")
            //Use PancakeSimpleRouting From s to d
            _currentPath = PancakeSimpleRoutingTask(_currentPath,destinationNode).executeTask().getResult()
            _isTerminated = true
            return

        }

        //Run Step2
        //Step 2) Let a := (sn˜+1,sn˜+2,...,sn) and b := (dn˜+1,dn˜+2,...,dn).
        findUniquePathForOddStep2(sourceNode,destinationNode)
    }

    //Step 2) Let a := (sn˜+1,sn˜+2,...,sn) and b := (dn˜+1,dn˜+2,...,dn).
    private fun findUniquePathForOddStep2(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode){
        LogData.append("Started findUniquePathForOddStep2")

//
//        setAvoidSuffixA(sourceNode.getRearString())
//        setAvoidSuffixB(destinationNode.getRearString())

        //Step 3) If R(d) ⊂ F(s) ∪ C(s),
        // let sh be the element  of (F(s) ∪ C(s)) \ R(d),
        // select path s ~>  (dn,dn−1,...,dn˜+1,sh,sn˜+1,sn˜+2,...,sn) def = s′ in P(s),
        // edge s′ → s′^(n), and path s′^(n) ~> d in P(d), and terminate.
        //Check R(d) ⊂ F(s) ∪ C(s)
        findUniquePathForOddStep3(sourceNode,destinationNode)
    }
    //Step 3)
    //Step 3) If R(d) ⊂ F(s) ∪ C(s),
    // let sh be the element  of (F(s) ∪ C(s)) \ R(d),
    // select path s ~>  (dn,dn−1,...,dn˜+1,sh,sn˜+1,sn˜+2,...,sn) def = s′ in P(s),
    // edge s′ → s′^(n), and path s′^(n) ~> d in P(d), and terminate.
    //Check R(d) ⊂ F(s) ∪ C(s)
    private fun findUniquePathForOddStep3(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode){
        LogData.append("Started findUniquePathForOddStep3")
        if(CheckRdIncludedInFsCsTask(sourceNode,destinationNode).executeTask().getResult()){
            LogData.append("checkRdIncludedInFsCsTask is return true.")
            var s2Node = FindS2NodeTask(_currentGraph,sourceNode,destinationNode).executeTask().getResult()
            LogData.append("Found s'' = " + s2Node.getId())
            //select path s ~>  (dn,dn−1,...,dn˜+1,sh,sn˜+1,sn˜+2,...,sn) def = s′ in P(s),
            PancakeSimpleRoutingTask(_currentPath,s2Node).executeTask()
            //edge s′ → s′^(n)
            _currentPath.appendNode(s2Node.getFinalNeighbor())
            //path s′^(n) ~> d in P(d), and terminate.
            PancakeSimpleRoutingTask(_currentPath,destinationNode).executeTask()
            _isTerminated = true
            return
        }

        //Start Step 4
        findUniquePathForOddStep4(sourceNode,destinationNode)

    }

    //Start Step 4
    //find
    private fun findUniquePathForOddStep4(sourceNode:HalfPancakeNode,destinationNode: HalfPancakeNode){
        LogData.append("Started findUniquePathForOddStep4 ")

        //Get Smallest element sh and distinct integers
        //Select path s ~> (s′1, s′2,..., s′n−˜ n, sh, s ˜ n+1, s ˜ n+2,..., sn) def = s2 where (s′1; s′2; : : : ; s′n−˜ n) = function(i,(F(s) ∪ C(s)) \ {sh},a,b)
        var (sh,distinctElement) = FindSmallestShNotInRdAndFsCsSymbolTask(_currentGraph,sourceNode,destinationNode).executeTask().getResult()
        var prefixElements = _hpUniquePermutationService.getPermutation(_currentPathIdentifierI,distinctElement,_avoidSuffixA.reversed(),_avoidSuffixB.reversed())
        LogData.append("Smallest sh : " + sh + " distinct elements " + distinctElement)

        //Create S2
        var s2 = _currentGraph.getNodeById(prefixElements+sh+sourceNode.getRearString())
        LogData.append("Current s2 " + s2.getId())

        _currentPath = PancakeSimpleRoutingTask(_currentPath,s2).executeTask().getResult()

        // Select edge s2 → s2^(n)
        var nextSourceNode = s2.getNthNeighbor(s2.getDegree()) as HalfPancakeNode
        _currentPath.appendNode(nextSourceNode)
        LogData.append("Added Edge " + nextSourceNode.getId())
        findUniquePathForOddStep5(nextSourceNode,destinationNode);


    }


    private fun findUniquePathForOddStep5(sourceNode:HalfPancakeNode,destinationNode: HalfPancakeNode) {
        LogData.append("Started findUniquePathForOddStep5 ")

        var shAndDistinctElements = FindSmallestShInRdAndFsCsSymbolTask(_currentGraph, sourceNode, destinationNode).executeTask().getResult()
        var prefixElements = _hpUniquePermutationService.getPermutation(_currentPathIdentifierI,shAndDistinctElements.second,_avoidSuffixA.reversed(),_avoidSuffixB.reversed())
        LogData.append("Smallest sh : " + shAndDistinctElements.first + " distinct elements " + shAndDistinctElements.second)

        //Create S2
        var s2 = _currentGraph.getNodeById(prefixElements+shAndDistinctElements.first+sourceNode.getRearString())
        LogData.append("Current s2 " + s2.getId())
        _currentPath = PancakeSimpleRoutingTask(_currentPath,s2).executeTask().getResult()
        // Select edge s2 → s2^(n)
        var nextSourceNode = s2.getNthNeighbor(s2.getDegree()) as HalfPancakeNode
        _currentPath.appendNode(nextSourceNode)
        LogData.append("Added Edge " + nextSourceNode.getId())

        findUniquePathForOddStep3(nextSourceNode,destinationNode)

    }

    private fun findUniquePathForEven(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode){
        LogData.append("[HPRestrictedRoutingService] Started findUniquePathForEven")
        findUniquePathForEvenStep1(sourceNode,destinationNode)
    }


    //Check P(s) and P(d)
    //Step 1. If P(s) = P(d), select s ~> d in P(s), and terminate
    private fun findUniquePathForEvenStep1(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode){
        LogData.append("Started findUniquePathForEvenStep1")
        //Check P(s) and P(d)
        if(sourceNode.getSuffixForSubGraph() == destinationNode.getSuffixForSubGraph()){
            LogData.append("P(s) and P(d) is same.")
            //Use PancakeSimpleRouting From s to d
            _currentPath = PancakeSimpleRoutingTask(_currentPath,destinationNode).executeTask().getResult()
            _isTerminated = true
            return

        }

        //Run Step2
        //Step 2) Let a := (sn˜+1,sn˜+2,...,sn) and b := (dn˜+1,dn˜+2,...,dn).
        findUniquePathForEvenStep2(sourceNode,destinationNode)
    }


    //
    /**
     *
     * Let a := (sn˜+1,sn˜+2,...,sn) and b := (dn˜+1,dn˜+2,...,dn).
     * nothing to do
     */
    private fun findUniquePathForEvenStep2(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode){
        findUniquePathForEvenStep3(sourceNode,destinationNode)
    }

    private fun findUniquePathForEvenStep3(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode){
        LogData.append("Started findUniquePathForEvenStep3")

        if(CheckRdIncludedInFsCsTask(sourceNode,destinationNode).executeTask().getResult()){
            LogData.append("checkRdIncludedInFsCsTask is return true.")
            var s2Node = FindS2NodeTask(_currentGraph,sourceNode,destinationNode).executeTask().getResult()

            LogData.append("Found s'' = " + s2Node.getId())
            //select path s ~>  (dn,dn−1,...,dn˜+1,sh,sn˜+1,sn˜+2,...,sn) def = s′ in P(s),
            PancakeSimpleRoutingTask(_currentPath,s2Node).executeTask()
            //edge s′ → s′^(n)
            _currentPath.appendNode(s2Node.getFinalNeighbor())
            //path s′^(n) ~> d in P(d), and terminate.
            PancakeSimpleRoutingTask(_currentPath,destinationNode).executeTask()
            _isTerminated = true
            return
        }

        findUniquePathForEvenStep4(sourceNode,destinationNode)


    }

    private fun findUniquePathForEvenStep4(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode){
        //Let sl and sh be the smallest and the second smallest  elements of (F(s)∪C(s)) \ R(d), respectively.
        //Select path s ~> (s′1, s′2,..., s′n−˜n, sl,sh, s˜n+1, s˜n+2,..., sn) def = s2 where (s′1,s′2,...,s′n−˜n) = function(i,(F(s) ∪ C(s)) \ {sh},a,b)
        var (sh,distinctElement) = FindSmallestShNotInRdAndFsCsSymbolTask(_currentGraph,sourceNode,destinationNode).executeTask().getResult()

        var prefixElements = _hpUniquePermutationService.getPermutation(_currentPathIdentifierI,distinctElement,_avoidSuffixA.reversed(),_avoidSuffixB.reversed())
        LogData.append("Smallest sh : " + sh + " distinct elements " + distinctElement)

        //Create S2
        var s2 = _currentGraph.getNodeById(prefixElements+sh+sourceNode.getRearString()) as HalfPancakeNode
        LogData.append("Current s2 " + s2.getId())

        PancakeSimpleRoutingTask(_currentPath,s2).executeTask()

        // Select edge s2 → s2^(n)
        var nextSourceNode = s2.getFinalNeighbor()
        _currentPath.appendNode(nextSourceNode)
        findUniquePathForEvenStep5(nextSourceNode,destinationNode);

    }


    private fun findUniquePathForEvenStep5(sourceNode: HalfPancakeNode,destinationNode: HalfPancakeNode){
        LogData.append("Started findUniquePathForEvenStep5 ")

        var (sh,distinctElements) = FindSmallestShInRdAndFsCsSymbolTask(_currentGraph, sourceNode, destinationNode).executeTask().getResult()
        var prefixElements = _hpUniquePermutationService.getPermutation(_currentPathIdentifierI,distinctElements,_avoidSuffixA.reversed(),_avoidSuffixB.reversed())
        LogData.append("Smallest sh : " + sh + " distinct elements " + distinctElements)

        //Create S2
        var s2 = _currentGraph.getNodeById(prefixElements+sh+sourceNode.getRearString()) as HalfPancakeNode
        LogData.append("Current s2 " + s2.getId())
        PancakeSimpleRoutingTask(_currentPath,s2).executeTask().getResult()
        // Select edge s2 → s2^(n)
        var nextSourceNode = s2.getFinalNeighbor()
        _currentPath.appendNode(nextSourceNode)
        findUniquePathForEvenStep3(nextSourceNode,destinationNode)
    }

    private fun appendFinishedLog(){
        LogData.append("Finished find unique path.")
        LogData.append(_currentPath?.toString()!!)
    }

}
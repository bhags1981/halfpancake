package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.task.CheckRdIncludedInFsCsTask
import net.kanekolab.graph.permutation.service.halfpancake.task.FindS2NodeTask
import net.kanekolab.graph.permutation.service.pancake.task.PancakeSimpleRoutingTask

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
class HPRestrictedRoutingService(graph: HalfPancakeGraph) {
    private val _currentGraph = graph
    private val _graphDimension:Int = graph.getDimension()
    private var _sourceNode: HalfPancakeNode? = null
    private var _destinationNode : HalfPancakeNode? = null
    private var _currentPathIdentifierI : Int = -1
    private var _avoidSuffixA : String? = null
    private var _avoidSuffixB : String? = null
    private var _currentPath: Path? = null
    private var _log : LogData = LogData("Initialized HPRestrictedRoutingService.")
    private var _isTerminated : Boolean = false


    fun setSourceNode(node:HalfPancakeNode){ _sourceNode = node }
    fun setDestinationNode(node:HalfPancakeNode){ _destinationNode = node }
    fun setAvoidSuffixA(suffix:String){ _avoidSuffixA = suffix }
    fun setAvoidSuffixB(suffix:String){ _avoidSuffixB = suffix }
    fun setPathIdentifierI(i:Int){ _currentPathIdentifierI = i }
    fun getCurrentPath():Path {
        if(_currentPath == null)
            throw NullPointerException("Current path is null. Check Case.")
        else
            return _currentPath!!
    }


    fun getCurrentLog():LogData{
        return _log
    }


    fun findUniquePath(){
        if(_sourceNode == null){
            throw NullPointerException("Source node is null")
        }

        if(_destinationNode == null){
            throw NullPointerException("Destination node is null")
        }

        var sourceNode = _sourceNode!!
        var destinationNode = _destinationNode!!

        _log.append("Started find unique path between " + _sourceNode?.getId() + " and " + _destinationNode?.getId())
        //Check Odd OR Even
        if(_graphDimension %2==0) {
            findUniquePathForEven(sourceNode,destinationNode)
        } else {
            findUniquePathForOdd(sourceNode,destinationNode)
        }
        appendFinishedLog()
    }

    private fun findUniquePathForOdd(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode){
        _log.append("Started findUniquePathForOdd")



        //Check P(s) and P(d)
        //Step 1. If P(s) = P(d), select s ~> d in P(s), and terminate
        _isTerminated = findUniquePathForOddStep1(sourceNode,destinationNode)
        if(_isTerminated) return

        //Step 2) Let a := (sn˜+1,sn˜+2,...,sn) and b := (dn˜+1,dn˜+2,...,dn).
        _isTerminated = findUniquePathForOddStep2(sourceNode,destinationNode)
        if(_isTerminated) return

        //Step 3) If R(d) ⊂ F(s) ∪ C(s),
        // let sh be the element  of (F(s) ∪ C(s)) \ R(d),
        // select path s ~>  (dn,dn−1,...,dn˜+1,sh,sn˜+1,sn˜+2,...,sn) def = s′ in P(s),
        // edge s′ → s′^(n), and path s′^(n) ~> d in P(d), and terminate.
        //Check R(d) ⊂ F(s) ∪ C(s)
        _isTerminated = findUniquePathForOddStep3(sourceNode,destinationNode)
        if(_isTerminated) return
    }



    //Check P(s) and P(d)
    //Step 1. If P(s) = P(d), select s ~> d in P(s), and terminate
    private fun findUniquePathForOddStep1(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode):Boolean{
        _log.append("Started findUniquePathForOddStep1")
        //Check P(s) and P(d)
        if(sourceNode.getSuffixForSubGraph() == destinationNode.getSuffixForSubGraph()){
            _log.append("P(s) and P(d) is same.")
            //Use PancakeSimpleRouting From s to d
            _currentPath = PancakeSimpleRoutingTask(UniquePath(sourceNode),destinationNode).executeTask().getResult()
            return true
        }
        return false

    }

    //Step 2) Let a := (sn˜+1,sn˜+2,...,sn) and b := (dn˜+1,dn˜+2,...,dn).
    private fun findUniquePathForOddStep2(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode):Boolean{
        _log.append("Started findUniquePathForOddStep2")
        _log.append("Set "+sourceNode.getRearString() + " for avoid suffix a")
        _log.append("Set "+destinationNode.getRearString() + " for avoid suffix b")

        setAvoidSuffixA(sourceNode.getRearString())
        setAvoidSuffixB(destinationNode.getRearString())
        return false
    }
    //Step 3)
    //Step 3) If R(d) ⊂ F(s) ∪ C(s),
    // let sh be the element  of (F(s) ∪ C(s)) \ R(d),
    // select path s ~>  (dn,dn−1,...,dn˜+1,sh,sn˜+1,sn˜+2,...,sn) def = s′ in P(s),
    // edge s′ → s′^(n), and path s′^(n) ~> d in P(d), and terminate.
    //Check R(d) ⊂ F(s) ∪ C(s)
    private fun findUniquePathForOddStep3(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode):Boolean{
        _log.append("Started findUniquePathForOddStep3")
        if(CheckRdIncludedInFsCsTask(sourceNode,destinationNode).executeTask().getResult()){
            _log.append("checkRdIncludedInFsCsTask is return true.")
            var s2Node = FindS2NodeTask(_currentGraph,sourceNode,destinationNode).executeTask().getResult()

            _log.append("Found s'' = " + s2Node.getId())
            var intermediatePath  =
                    //select path s ~>  (dn,dn−1,...,dn˜+1,sh,sn˜+1,sn˜+2,...,sn) def = s′ in P(s),
                    PancakeSimpleRoutingTask(UniquePath(sourceNode),s2Node).executeTask().getResult()
                        //edge s′ → s′^(n)
                        .addNode(s2Node.getNthNeighbor(s2Node.getDegree())) as UniquePath

            //path s′^(n) ~> d in P(d), and terminate.
            _currentPath = PancakeSimpleRoutingTask(intermediatePath,destinationNode).executeTask().getResult()
            return true
        }

        return false
    }



    private fun findUniquePathForEven(sourceNode:HalfPancakeNode,destinationNode:HalfPancakeNode){
        _log.append("Started findUniquePathForEven")

    }

    private fun appendFinishedLog(){
        _log.append("Finished find unique path.")
        _log.append(_currentPath?.toString()!!)
    }

}
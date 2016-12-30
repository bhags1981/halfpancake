package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.ServiceTask

/**
 * Created by bhags on 2016/12/30.
 */
class FindSmallestShNotInRdAndFsCsSymbolTask(currentGraph: HalfPancakeGraph, sourceNode:HalfPancakeNode, destinationNode :HalfPancakeNode) : ServiceTask<FindSmallestShNotInRdAndFsCsSymbolTask,Pair<Char,String>> {
    var _currentGraph = currentGraph
    var _sourceNode = sourceNode
    var _destinationNode = destinationNode
    var _smallestSh: Char? = null

    //(F(s) ∪ C(s)) \ {sh}
    var _elementsFsCsWithoutSh:String? = null

    override fun executeTask(): FindSmallestShNotInRdAndFsCsSymbolTask {
        if(_currentGraph.isEvenDimension())
            throw UnsupportedOperationException("This task not working for even case")

        var rearString = _destinationNode.getRearString()
        var smallestElement:Char? = null
        _sourceNode.getFrontCenterString().forEach{
            element ->
            run{
                if(!rearString.contains(element)) {
                    if (smallestElement == null){
                        smallestElement = element
                        }  else if(smallestElement!!.compareTo(element) > 0 ){
                        smallestElement = element
                    }
                }
            }
        }
        _smallestSh = smallestElement
        _elementsFsCsWithoutSh = _sourceNode.getFrontCenterString().filter { element-> element != smallestElement }
        return this
    }


    override fun getResult():Pair<Char,String>{

        if(_smallestSh == null || _elementsFsCsWithoutSh == null)
            throw Exception("Current s2 is null. Failed to find s2 on FindS2NodeWithSmallestShTask ")
        else
            return Pair(_smallestSh!!, _elementsFsCsWithoutSh!!)


    }

}
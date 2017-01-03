package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.ServiceTask

/**
 * Created by bhags on 2016/12/30.
 */
class FindSmallestShNotInRdAndFsCsSymbolTask(currentGraph: HalfPancakeGraph, sourceNode:HalfPancakeNode, destinationNode :HalfPancakeNode) : ServiceTask<FindSmallestShNotInRdAndFsCsSymbolTask,Pair<String,String>> {
    var _currentGraph = currentGraph
    var _sourceNode = sourceNode
    var _destinationNode = destinationNode
    var _smallestSh: String? = null

    //(F(s) ∪ C(s)) \ {sh}
    var _elementsFsCsWithoutSh:String? = null

    override fun executeTask(): FindSmallestShNotInRdAndFsCsSymbolTask {
        if(_currentGraph.isEvenDimension()){
            var rearString = _destinationNode.getRearString()
            var sl:Char? = null //smallest
            var sh:Char? = null //next
            _sourceNode.getFrontCenterString().forEach{
                element ->
                run{
                    if(!rearString.contains(element)) {
                        if (sl == null){
                            sl = element
                        }else if(sl!!.compareTo(element) > 0 ){
                            sh = sl
                            sl = element
                        }else if(sh == null) {
                            sh = element
                        }else if(sh!!.compareTo(element) > 0){
                            sh = element
                        }
                    }
                }
            }
            _smallestSh = sl!!.plus(sh!!.toString())
            _elementsFsCsWithoutSh = _sourceNode.getFrontCenterString().filter { element-> element != sh && element!=sl }
        }else{
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
            _smallestSh = smallestElement!!.toString()
            _elementsFsCsWithoutSh = _sourceNode.getFrontCenterString().filter { element-> element != smallestElement }
        }

        return this
    }


    override fun getResult():Pair<String,String>{

        if(_smallestSh == null || _elementsFsCsWithoutSh == null)
            throw Exception("Current s2 is null. Failed to find s2 on FindS2NodeWithSmallestShTask ")
        else
            return Pair(_smallestSh!!, _elementsFsCsWithoutSh!!)


    }

}
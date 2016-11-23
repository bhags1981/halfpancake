package net.kanekolab.graph.permutation.strategy.halfpancake.casefactory

import net.kanekolab.graph.permutation.model.Permutation
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.vo.halfpancake.HPCase
import net.kanekolab.graph.permutation.vo.halfpancake.HPCaseType

/**
 * Created by bhags on 2016/11/21.
 */
class HPN2NCaseFactory(graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) {
    var _graph = graph
    var _sourceNode = sourceNode
    var _destinationNode = destinationNode

    fun createCase(): HPCase {
        //Determine even or odd

        //case of even
        if (_graph.isEvenDimension()) {

            //case of odd
            return createCaseOfEven()
        } else {
            return createCaseOfOdd()

        }

    }

    /**
     * Case for n is odd
     */
    private fun createCaseOfOdd(): HPCase {


        //Case 1-1 (d ∈ P(s))
        if (_sourceNode.getSuffixForSubGraph() == _destinationNode.getSuffixForSubGraph())
            return HPCase(HPCaseType.ODD_1,0)

        //Case 1-2 (s^(n) = d)
        else if(_sourceNode.getNthNeighbor(_sourceNode.getDegree()).getId().equals(_destinationNode.getId()))
            return HPCase(HPCaseType.ODD_2,0)

        //Case 1-3 (s^(n) ∈ P(d)) and (s^(n) != d)
        else if(_sourceNode.getNthNeighbor(_sourceNode.getDegree()).getSuffixForSubGraph().equals(_destinationNode.getSuffixForSubGraph()))
            return HPCase(HPCaseType.ODD_3,0)

        //Case 1-4 (Exist k( < ~n) s^(k,n) ∈ P(d) and s^(k,n) != d)
        for (i in 1.._sourceNode.getHalfPosition() - 2){
            var node = (_sourceNode.getNthNeighbor(i) as HalfPancakeNode ).getNthNeighbor(_sourceNode.getDegree()) as HalfPancakeNode

            if(node.getId().equals(_destinationNode.getId())){
                throw Exception("Case 1-4 faced to  illegal pattern. Change source node and destination node")
            }
            if(node.getSuffixForSubGraph().equals(_destinationNode.getSuffixForSubGraph()))
                return HPCase(HPCaseType.ODD_4,i + 1)
        }


        //Case 1-5( s^(~n,n) ∈ P(d) and s^(~n,n) != d))
        var node = (_sourceNode.getNthNeighbor(_sourceNode.getHalfPosition() - 1 ) as HalfPancakeNode).getNthNeighbor(_sourceNode.getDegree()) as HalfPancakeNode
        if(node.getId().equals(_destinationNode.getId())){
            throw Exception("Case 1-5 faced to  illegal pattern. Change source node and destination node")
        }

        if(node.getSuffixForSubGraph().equals(_destinationNode.getSuffixForSubGraph()))
            return HPCase(HPCaseType.ODD_5,_sourceNode.getHalfPosition() - 1)

        //Case 1-6 Otherwise
        return HPCase(HPCaseType.ODD_OTHERWISE,0)

    }

    private fun createCaseOfEven(): HPCase{
        return HPCase(HPCaseType.EVEN_OTHERWISE,0)
    }
}
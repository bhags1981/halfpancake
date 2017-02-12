package net.kanekolab.graph.permutation.strategy.halfpancake.casefactory

import net.kanekolab.graph.permutation.core.ReversedPatternException
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.service.PermutationService
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
            return HPCase(HPCaseType.ODD_1,0,false)

        //Case 1-2 (s^(n) = d)
        else if(_sourceNode.getNthNeighbor(_sourceNode.getDegree()).getId().equals(_destinationNode.getId()))
            return HPCase(HPCaseType.ODD_2,0,false)


        //Case 1-3 (s^(n) ∈ P(d)) and (s^(n) != d)
        else if(_sourceNode.getFinalNeighbor().getSuffixForSubGraph().equals(_destinationNode.getSuffixForSubGraph())) {
            return HPCase(HPCaseType.ODD_3, 0, false)
        }else if(_destinationNode.getFinalNeighbor().getSuffixForSubGraph().equals(_sourceNode.getSuffixForSubGraph())){
            return HPCase(HPCaseType.ODD_3,0,true)
        }

        //Case 1-4 (Exist k( < ~n) s^(k,n) ∈ P(d) and s^(k,n) != d)
        for (i in 2.._sourceNode.getHalfPosition() - 1){
            var node = _sourceNode.getIthPrefixReversalNeighbor(i).getFinalNeighbor()

            if(node.getId().equals(_destinationNode.getId())){
                LogData.append("Reversed CASE ODD_3 ")
                return HPCase(HPCaseType.ODD_3,0,true)
            }
            if(node.getSuffixForSubGraph().equals(_destinationNode.getSuffixForSubGraph()))
                return HPCase(HPCaseType.ODD_4,i ,false)
        }



        //Reversed Case 1-4 (Exist k( < ~n) s^(k,n) ∈ P(d) and s^(k,n) != d)
        for (i in 2.._destinationNode.getHalfPosition() - 1){
            var node = _destinationNode.getIthPrefixReversalNeighbor(i).getFinalNeighbor()
            if(node.getSuffixForSubGraph().equals(_sourceNode.getSuffixForSubGraph())) {
                LogData.append("Reversed CASE ODD_4 ")

                return HPCase(HPCaseType.ODD_4,i ,true)
            }
        }


        var node = _sourceNode.getIthPrefixReversalNeighbor(_sourceNode.getHalfPosition()).getFinalNeighbor()
        //Case 1-5( s^(~n,n) ∈ P(d) and s^(~n,n) == d))
        if(node.getId().equals(_destinationNode.getId())){
            return HPCase(HPCaseType.ODD_3,0,true)
        }


        //Case 1-5( s^(~n,n) ∈ P(d) and s^(~n,n) != d))
        if(node.getSuffixForSubGraph().equals(_destinationNode.getSuffixForSubGraph()))
            return HPCase(HPCaseType.ODD_5,_sourceNode.getHalfPosition() - 1,false)

        if(_destinationNode
                .getIthPrefixReversalNeighbor(_destinationNode.getHalfPosition())
                .getFinalNeighbor()
                .getSuffixForSubGraph()
                .equals(_sourceNode.getSuffixForSubGraph())){
            return HPCase(HPCaseType.ODD_5,_sourceNode.getHalfPosition(),true)
        }


        //Case 1-6 Otherwise
        return HPCase(HPCaseType.ODD_OTHERWISE,0,false)

    }

    private fun createCaseOfEven(): HPCase{

        //Case 2-1 (d ∈ P(s))
        if (_sourceNode.getSuffixForSubGraph() == _destinationNode.getSuffixForSubGraph())
            return HPCase(HPCaseType.EVEN_1,0,false)

        //Case 2-2 (s^(n) = d)
        else if(_sourceNode.getNthNeighbor(_sourceNode.getDegree()).getId().equals(_destinationNode.getId()))
            return HPCase(HPCaseType.EVEN_2,0,false)





        //Case 2-3 (s^(n) ∈ P(d)) and d^(n) ∈ P(s) and (s^(n) != d)
        else if(
            _sourceNode.getFinalNeighbor().getSuffixForSubGraph().equals(_destinationNode.getSuffixForSubGraph())
            && _destinationNode.getFinalNeighbor().getSuffixForSubGraph().equals(_sourceNode.getSuffixForSubGraph())

        ) {
            return HPCase(HPCaseType.EVEN_3, 0, false)
        }

        //Case 2-4 (s^(n)∈ P(d))
        else if(
        _sourceNode.getFinalNeighbor().getSuffixForSubGraph().equals(_destinationNode.getSuffixForSubGraph())
        )
         {
            return HPCase(HPCaseType.EVEN_4, 0, false)
        }

        //Reversed Case 2-4
        else if(
        _destinationNode.getFinalNeighbor().getSuffixForSubGraph().equals(_sourceNode.getSuffixForSubGraph())
        ) {
            return HPCase(HPCaseType.EVEN_4, 0, true)
        }

        //Case 2-5, 2-6,7
        for (i in 2.._sourceNode.getHalfPosition()){
            var node = _sourceNode.getIthPrefixReversalNeighbor(i).getFinalNeighbor()
            if(
                node.getSuffixForSubGraph().equals(_destinationNode.getSuffixForSubGraph())
                && !_destinationNode.getFrontString().reversed().equals(_sourceNode.getSuffixForSubGraph())
            ) {
                //Case 2-5 (Exist k( < ~n - 1) s^(k,n) ∈ P(d) and s^(k,n) != d)
                if (i < _destinationNode.getHalfPosition() - 1 ){
                    return HPCase(HPCaseType.EVEN_5, i, false)

                //Case 2-6 (s( ˜n−1,n) ∈ P(d) and d(n) ̸∈ P(s))
                }else if ( i == _destinationNode.getHalfPosition() - 1  ) {
                    return HPCase(HPCaseType.EVEN_6, i, false)

                //Case 2-7
                } else if (i == _destinationNode.getHalfPosition() ){
                    return HPCase(HPCaseType.EVEN_7,i,false)
                }
            }
        }

        //Reversed Case 2-5,2-6,2-7
        for (i in 2.._destinationNode.getHalfPosition() - 1){
            var node = _destinationNode.getIthPrefixReversalNeighbor(i).getFinalNeighbor()
            if(
            node.getSuffixForSubGraph().equals(_sourceNode.getSuffixForSubGraph())
                    && !_sourceNode.getFrontString().reversed().equals(_destinationNode.getSuffixForSubGraph())
            ) {
                //Case 2-5 Reversed Pattern
                //Case 2-5 (Exist k( < ~n - 1) s^(k,n) ∈ P(d) and s^(k,n) != d)
                if (i < _sourceNode.getHalfPosition() - 1 ){
                    return HPCase(HPCaseType.EVEN_5, i, true)

                //Case 2-6 Reversed Pattern
                //Case 2-6 (s(˜n−1,n) ∈ P(d) and d(n) ̸∈ P(s))
                }else if( i == _sourceNode.getHalfPosition() - 1){
                    return HPCase(HPCaseType.EVEN_6,i, true)

                //Case 2-7 Reversed pattern
                //Case 2-7 (s(˜n,n) ∈ P(d) and d(n) ̸∈ P(s))
                } else if (i == _destinationNode.getHalfPosition() ){
                    return HPCase(HPCaseType.EVEN_7,i,true)
                }
            }
        }



        return HPCase(HPCaseType.EVEN_OTHERWISE,0,false)
    }
}
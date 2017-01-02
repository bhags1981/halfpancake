package net.kanekolab.graph.permutation.model.halfpancake

import net.kanekolab.graph.permutation.model.RegularGraph
import net.kanekolab.graph.permutation.model.Node
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.PermutationService

/**
 *
 *  @author : bhags
 *  @param dimension : Current dimension of a Half Pancake Graph
 */

class HalfPancakeGraph(dimension:Int) : RegularGraph(dimension,Math.ceil((dimension + 1 ) / 2.0).toInt()) {

    fun isEvenDimension():Boolean{
        return (_dimension % 2 == 0)
    }

    /**
     *
     * @return Degree of the graph
     */


    override fun getNodeById(id:String): Node {
        if(id.length != _dimension){
            throw IllegalArgumentException("Vertex ID "+id+" does not match with graph dimension " +_dimension)
        }

        return HalfPancakeNode(id, _degree)
    }

    override fun getRandomNode(): Node {
        //   PermutationService().getIdentifyPermutationForDimension(_dimension)
        throw UnsupportedOperationException()
    }
}
package net.kanekolab.graph.permutation.model.halfpancake

import net.kanekolab.graph.permutation.model.PrefixReversalGenerator
import net.kanekolab.graph.permutation.model.Node

/**
 * Created by bhags on 2016/11/21.
 */
class HalfPancakeNode(id:String, degree:Int) : Node(id,degree){

    override  fun getNthNeighbor(n:Int): Node
    {
        if(n < 1 || n > _degree){
            throw  IndexOutOfBoundsException("The vertex does not have "+n+"th neighbor. Range of n is from 1 to "+_degree)
        }

        var generatorFactor : Int  = n + 1
        if(n == _degree){
            generatorFactor = _id.length
        }
        var neighborId = PrefixReversalGenerator().generate(_id,generatorFactor);

        return HalfPancakeNode(neighborId, _degree)
    }

    override fun isNeighbor(other: Node):Boolean
    {
        for (i in 1.._degree){
            if(PrefixReversalGenerator().generate(_id,i).equals(other.getId()))
                return true
        }
        return false
    }

    override fun getSuffixForSubGraph():String
    {
        return _id.substring(_degree)
    }


    fun getHalfPosition():Int{
        return _degree
    }
}
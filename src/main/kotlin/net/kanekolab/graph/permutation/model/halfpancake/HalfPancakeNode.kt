package net.kanekolab.graph.permutation.model.halfpancake

import net.kanekolab.graph.permutation.model.PrefixReversalOperator
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
        var neighborId = PrefixReversalOperator().generate(_id,generatorFactor);

        return HalfPancakeNode(neighborId, _degree)
    }

    override fun getNeighborByIndex(index: Int): Node {
        return getIthPrefixReversalNeighbor(index)
    }
    
    override fun isNeighbor(other: Node):Boolean
    {
        for (i in 1.._degree){

            //Nth neighbor
            if(i == _degree && PrefixReversalOperator().generate(_id,_id.length).equals(other.getId()))
                return true

            //1 to ~n neighbor
            if(PrefixReversalOperator().generate(_id,i).equals(other.getId()))
                return true
        }
        return false
    }

    override fun getSuffixForSubGraph():String
    {
        return _id.substring(_degree)
    }

    override fun getNeighborById(index: String): Node {
        return HalfPancakeNode(index,_degree)
    }


    fun getHalfPosition():Int{
        return _degree
    }

    fun getHalfElement():Char{
        return _id[getHalfPosition()- 1]
    }

    //For a node a = (a1,a2,...,an) in an n-half pancake graph, let F(a) = {a1,a2,...,an−n˜}
    fun getFrontString():String{
        return _id.substring(0,(_id.length - _degree))
    }
    //For a node a = (a1,a2,...,an) in an n-half pancake graph,R(a) = {an˜+1,an˜+2,...,an}
    fun getRearString():String{
        return _id.substring(_degree)
    }

    fun getCenterString():String{
        if(_id.length % 2 == 1)
            return _id.substring(getHalfPosition()-1,getHalfPosition())
        else
            return _id.substring(getHalfPosition()-2,getHalfPosition());
    }

    fun getFrontCenterString():String{
        return _id.substring(0,getHalfPosition())
    }

    fun getCenterRearString():String{
        if(_id.length % 2 == 1)
            return _id.substring(getHalfPosition()-1)
        else
            return _id.substring(getHalfPosition()-2);
    }

    fun getFinalNeighbor():HalfPancakeNode{
        return getNthNeighbor(_degree) as HalfPancakeNode
    }

    fun getIthPrefixReversalNeighbor(i:Int):HalfPancakeNode{
        if(i <= _degree)
            return getNthNeighbor( i - 1) as HalfPancakeNode
        if(i == _id.length)
            return getFinalNeighbor()
        throw IndexOutOfBoundsException("Current index " + i + " is out of index range")
    }

}
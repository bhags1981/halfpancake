package net.kanekolab.graph.permutation.model

/**
 * Created by sinyu on 2016/11/18.
 */
abstract class Node(id:String, degree:Int):Comparable<Node>{
    protected val _id = id
    protected val _degree = degree

    fun getId():String {
        return _id
    }

    fun getDegree():Int {
        return _degree;
    }

    fun getLength():Int{
        return _id.length
    }

    override fun compareTo(other: Node):Int{
        return (other.getId().compareTo(this.getId()))
    }

    abstract fun getNthNeighbor(n:Int): Node

    abstract fun isNeighbor(other: Node):Boolean

    abstract fun getSuffixForSubGraph():String

}
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

    fun equalId(other:Node):Boolean{
        return (other.getId().equals(this.getId()))
    }

    fun getCharForPaperIndex(index:Int):Char{
        return _id[index-1];
    }

    fun getPaperIndexForChar(ch:Char):Int{
        for(i in 0 .. _id.length){
            if(_id[i].equals(ch))
                return i + 1
        }
        return -1
    }

    abstract fun getNthNeighbor(n:Int): Node

    abstract fun isNeighbor(other: Node):Boolean

    abstract fun getSuffixForSubGraph():String

    abstract fun getNeighborByIndex(index:Int) : Node

    abstract fun getNeighborById(index:String) : Node
}
package net.kanekolab.graph.permutation.model

import net.kanekolab.graph.permutation.model.Node

/**
 * Created by bhags on 2016/11/21.
 */
abstract class  RegularGraph constructor(dimension:Int,degree:Int){
    protected var _dimension = dimension
    protected var _degree = degree
    fun getDegree():Int = _degree
    fun getDimension():Int = _dimension
    abstract fun getNodeById(id:String): Node
    abstract fun getRandomNode() : Node
}
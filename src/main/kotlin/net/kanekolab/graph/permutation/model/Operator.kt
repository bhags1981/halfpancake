package net.kanekolab.graph.permutation.model

/**
 * Created by bhags on 2016/11/21.
 */
interface Operator {
    fun generate(symbol:String,factor:Int):String
}
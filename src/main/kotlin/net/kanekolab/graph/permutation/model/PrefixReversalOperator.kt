package net.kanekolab.graph.permutation.model

/**
 * Created by bhags on 2016/11/21.
 */
class PrefixReversalOperator : Operator {
    override fun generate(symbol:String,factor:Int):String {
        return symbol.substring(0,factor).reversed() + symbol.substring(factor);
    }
}
package net.kanekolab.graph.permutation.vo.halfpancake

import net.kanekolab.graph.permutation.vo.ProblemCase

/**
 * Created by bhags on 2016/11/21.
 */
class HPCase (caseType:HPCaseType, prefixPositionK:Int, isReversed:Boolean) : ProblemCase(){
    val caseType = caseType
    val prefixPositionK = prefixPositionK
    val isReversedPattern = isReversed


    override fun toString():String{
        return "Case : "+ caseType.name + " position k : " + prefixPositionK
    }
}
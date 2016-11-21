package net.kanekolab.graph.permutation.vo.halfpancake

/**
 * Created by bhags on 2016/11/21.
 */
class HPCase (caseType:HPCaseType, prefixPositionK:Int){
    val caseType = caseType
    val prefixPositionK = prefixPositionK

    override fun toString():String{
        return "Case : "+caseType.name + " position k : " + prefixPositionK
    }
}
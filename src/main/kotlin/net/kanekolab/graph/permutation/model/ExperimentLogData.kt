package net.kanekolab.graph.permutation.model

import java.util.*

/**
 * Created by bhags on 2016/12/29.
 */
object ExperimentLogData {
    private var _log:String = ""

    fun append(log:String){
        _log += "[" + System.currentTimeMillis() + "] " + log + "\r\n"
    }

    fun getLog():String{
        return _log
    }

    fun init(){
        _log = ""
    }
}
package net.kanekolab.graph.permutation.model

import java.util.*

/**
 * Created by bhags on 2016/12/29.
 */
class LogData(initMessage:String) {
    private var _log:String = ""

    init {
        append(initMessage)
    }


    fun append(log:String){
        _log += "[" + System.currentTimeMillis() + "] " + log + "\r\n"
    }

    fun getLog():String{
        return _log
    }
}
package net.kanekolab.graph.permutation.service

/**
 * Created by sinyu on 2016/12/28.
 */
interface ServiceTask <out T>{
    abstract fun executeTask()
    abstract fun getResult():T
}
package net.kanekolab.graph.permutation.service

/**
 * Created by sinyu on 2016/12/28.
 */
interface ServiceTask <out Me:ServiceTask<Me,T>,out T>{
    fun executeTask():Me
    fun getResult():T
}
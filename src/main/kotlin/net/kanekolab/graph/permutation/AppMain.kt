package net.kanekolab.graph.permutation

import net.kanekolab.graph.permutation.core.Config
import net.kanekolab.graph.permutation.service.PermutationService
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.service.ExperimentService
import net.kanekolab.graph.permutation.service.halfpancake.HPDisjointPathService
import java.math.BigInteger

/**
 * Created by bhags on 2016/11/20.
 */

fun main(args: Array<String>) {
    //Graph type(current only 0 : half pancake graph )Graph dimension, Start Id, End Id
    try {
        val graphType = args[0].toInt()
        val graphDimension = args[1].toInt()
        val startPermutationId = BigInteger(args[2])
        val endPermutationId = BigInteger(args[3])
        Config.interimReportInterval = args[4].toLong()
        ExperimentService(graphType,graphDimension,startPermutationId,endPermutationId).run()
    }catch(e:Exception){
        println("Exception e " + e.message)
        e.stackTrace.forEach(::println)
        println("Input graph type, dimension, start permutationId, end permutationId and report interval." )
        println("Ex: 0 7 1 500 500" )
    }




}

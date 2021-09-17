package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.PermutationService
import net.kanekolab.graph.permutation.strategy.halfpancake.casefactory.HPN2NCaseFactory
import net.kanekolab.graph.permutation.vo.halfpancake.HPCaseType
import org.junit.Test

import org.junit.Assert.*
import java.math.BigInteger
import kotlin.system.exitProcess

/**
 * Created by bhags on 2017/02/18.
 */
class HPDCase2_6_ServiceV2Test {
   // @Test
    fun constructDisjointPathsForCase26Step8Over() {

        /*
        * Found positionL : 6 for case 2_6 step 1 between 1789a23456 and 123456789a
          Found positionL : 5 for case 2_6 step 1 between 6789a12345 and 123456789a
          Found positionL : 4 for case 2_6 step 1 between 6789a51234 and 123456789a
          Found positionL : 3 for case 2_6 step 1 between 6789a54123 and 123456789a
          Found positionL : 2 for case 2_6 step 1 between 6789a54312 and 123456789a
        * */
        val dimension = 10
        val graph = HalfPancakeGraph(dimension);
        val sourceNode = graph.getNodeById("6789a54312") as HalfPancakeNode
        val destNode = graph.getNodeById("123456789a") as HalfPancakeNode
        val service = HPDCase2_6_ServiceV2(graph,sourceNode,destNode)
        service.constructDisjointPaths()
        println(service.getPaths().toString())
        println(LogData.getLog())

    }

    //@Test
    fun constructDisjointPathsForCase26SpecificCase() {

        /*
         *
        * Found positionL : 6 for case 2_6 step 1 between 1789a23456 and 123456789a
          Found positionL : 5 for case 2_6 step 1 between 6789a12345 and 123456789a
          Found positionL : 4 for case 2_6 step 1 between 6789a51234 and 123456789a
          Found positionL : 3 for case 2_6 step 1 between 6789a54123 and 123456789a
          Found positionL : 2 for case 2_6 step 1 between 6789a54312 and 123456789a
        * */
        val dimension = 12
        val graph = HalfPancakeGraph(dimension);
        val sourceNode = graph.getNodeById("123456789abc") as HalfPancakeNode //223811124
        val destNode = graph.getNodeById("ca9871b23456") as HalfPancakeNode
        val service = HPDCase2_6_ServiceV2(graph,sourceNode,destNode)
        service.constructDisjointPaths()
        println(service.getPaths().toString())
        println(LogData.getLog())
        println()
    }

    @Test
    fun constructDisjointPathsForCase26Step2Over() {
        val dimension = 12
        val graph = HalfPancakeGraph(dimension);

        val sourceNode = graph.getNodeById(PermutationService().getIdentifyPermutationForDimension(dimension)) as HalfPancakeNode
        var currentNodeId: BigInteger = BigInteger.valueOf(1)
        var endNodeId: BigInteger = BigInteger.valueOf(479001599)


        while(currentNodeId.compareTo(endNodeId) == -1 ){
            var destNode = graph.getNodeById(PermutationService().getNthPermutation(dimension,currentNodeId)) as HalfPancakeNode
            var currentCase = HPN2NCaseFactory(graph,sourceNode,destNode).createCase()

            if(currentNodeId.divideAndRemainder(BigInteger.valueOf(500000))[1].equals(BigInteger.ZERO))
                println(currentNodeId.toString() + "(" + "%.4f".format(currentNodeId.toLong() / 479001599.0) + "%)")

            if(currentCase.caseType.equals(HPCaseType.EVEN_6)) {
                var (tmpSourceNode,tmpDestNode) = if(currentCase.isReversedPattern){
                    Pair(destNode,sourceNode)
                }else{
                    Pair(sourceNode,destNode)
                }
                try {
                    UsedNodeIds.init()
                    val service = HPDCase2_6_ServiceV2(graph, tmpSourceNode, tmpDestNode)
                    service.constructDisjointPaths()
                    println("$currentNodeId")
                    println("Source : ${tmpSourceNode.getId()} Dest : ${tmpDestNode.getId()}")
                    //println(service.getPaths().toString())
                }catch (e:Exception){
                    println("Failed with error ${e.message}")
                    println("Source : ${tmpSourceNode.getId()} Dest : ${tmpDestNode.getId()}")
                    exitProcess(1)
                }
            }
            currentNodeId =  currentNodeId.add(BigInteger.ONE)
        }
    }
}
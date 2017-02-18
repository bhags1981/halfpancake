package net.kanekolab.graph.permutation.strategy.halfpancake.casefactory

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.PermutationService
import net.kanekolab.graph.permutation.vo.halfpancake.HPCaseType
import org.junit.Test

import org.junit.Assert.*
import java.math.BigInteger

/**
 * Created by bhags on 2017/02/17.
 */
class HPN2NCaseFactoryTest {
    @Test
    fun checkCase2_6() {
        val dimension = 10;
        val graph = HalfPancakeGraph(dimension);
        val sourceNode = graph.getNodeById(PermutationService().getIdentifyPermutationForDimension(dimension)) as HalfPancakeNode
        var currentNodeId:BigInteger = BigInteger.valueOf(200000)
        var endNodeId:BigInteger = BigInteger.valueOf(300000)


        while(currentNodeId.compareTo(endNodeId) == -1 ){
            var destNode = graph.getNodeById(PermutationService().getNthPermutation(dimension,currentNodeId)) as HalfPancakeNode
            var currentCase = HPN2NCaseFactory(graph,sourceNode,destNode).createCase()
            if(currentCase.caseType.equals(HPCaseType.EVEN_6))
                println("DEST ID : ${destNode.getId()}  ${currentCase.isReversedPattern}" )
            currentNodeId =  currentNodeId.add(BigInteger.ONE)
        }
    }
}
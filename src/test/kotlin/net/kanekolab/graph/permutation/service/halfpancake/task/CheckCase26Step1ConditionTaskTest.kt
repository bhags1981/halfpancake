package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.PermutationService
import net.kanekolab.graph.permutation.strategy.halfpancake.casefactory.HPN2NCaseFactory
import net.kanekolab.graph.permutation.vo.halfpancake.HPCaseType
import org.junit.Test
import java.math.BigInteger

/**
 * Created by bhags on 2017/02/18.
 */
class CheckCase26Step1ConditionTaskTest {
    @Test
    fun getResult() {
        val dimension = 10;
        val graph = HalfPancakeGraph(dimension);
        val destNode = graph.getNodeById(PermutationService().getIdentifyPermutationForDimension(dimension)) as HalfPancakeNode
        var currentNodeId: BigInteger = BigInteger.valueOf(1)
        var endNodeId: BigInteger = BigInteger.valueOf(3628799)

        while(currentNodeId.compareTo(endNodeId) == -1 ){
            var sourceNode = graph.getNodeById(PermutationService().getNthPermutation(dimension,currentNodeId)) as HalfPancakeNode
            var currentCase = HPN2NCaseFactory(graph, sourceNode, destNode).createCase()
            if(currentCase.caseType.equals(HPCaseType.EVEN_6)){
                //Check Step1 Condition
               var (tmpSource:HalfPancakeNode,tmpDest:HalfPancakeNode) =
                       if(currentCase.isReversedPattern){
                           Pair(destNode,sourceNode)
                       }else{
                           Pair(sourceNode,destNode)
                       }
               var (isIncluded:Boolean, positionL:Int) =
                       CheckCase26Step1ConditionTask(tmpSource,tmpDest).executeTask().getResult()

                if(isIncluded){
                    println("Found positionL : $positionL for case 2_6 step 1 between ${tmpSource.getId()} and ${tmpDest.getId()}")
                }
            }
            currentNodeId =  currentNodeId.add(BigInteger.ONE)
        }
    }

}
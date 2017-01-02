package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.PermutationService
import org.junit.Test

import org.junit.Assert.*
import kotlin.system.exitProcess

/**
 * Created by sinyu on 2017/01/02.
 */
class HPDisjointPathServiceTest {
    @Test
    fun findDisjointPaths() {
        val dimension = 9
        val graph = HalfPancakeGraph(dimension)
        val dst = graph.getNodeById("123456789") as HalfPancakeNode
        val allPermutation = PermutationService().getAllPermutationForDimension(dimension)


        allPermutation.forEach {
            sourceId ->
            println("Start with Source ID " + sourceId)
            UsedNodeIds.init()
            LogData.init()

            if(sourceId.equals(dst.getId())) return@forEach

            //if(sourceId.compareTo("912345678") < 0) return@forEach

            var src = graph.getNodeById(sourceId) as HalfPancakeNode
            var disjointPathService = HPDisjointPathService(graph)
            try {

                disjointPathService.initProblemN2N(src, dst)
                disjointPathService.findDisjointPaths()
              //  println(disjointPathService.disjointPaths)
            }catch (e:Exception){
                println("Exception for source " + sourceId)
                println(LogData.getLog())
                throw (e)
            }
        }
    }

    @Test
    fun findDisjointPathsSpecificSource(){
        val dimension = 9
        val graph = HalfPancakeGraph(dimension)
        val src = graph.getNodeById("987654321") as HalfPancakeNode
        val dst = graph.getNodeById("123456789") as HalfPancakeNode
        UsedNodeIds.init()
        LogData.init()
        var disjointPathService = HPDisjointPathService(graph)
        try {
            println("findDisjointPathsSpecificSourceStart with Source ID " + src.getId())
            disjointPathService.initProblemN2N(src, dst)
            disjointPathService.findDisjointPaths()
            println(LogData.getLog())
            disjointPathService.disjointPaths!!.forEach(::println)
        }catch (e:Exception){
            println(LogData.getLog())
            throw (e)

        }

    }

}
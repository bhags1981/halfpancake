package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.service.PermutationService
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.awt.Dimension

/**
 * Created by bhags on 2016/11/23.
 */
class CreatePathToSpecifiedPrefixWithInitReverseIndexTaskTest{
    var testGraphDimension:Int? = null
    var graph:HalfPancakeGraph? = null
    var sourceNode:HalfPancakeNode? =  null


    fun initTestGraph(dimension: Int){
        testGraphDimension = dimension
        graph = HalfPancakeGraph(testGraphDimension!!)
        sourceNode = graph!!.getNodeById(PermutationService().getIdentifyPermutationForDimension(testGraphDimension!!)) as HalfPancakeNode
    }


    //Test ~n
    @Test
    fun testCreatePathOnOddType1(){
        initTestGraph(8)
        //Check ~n-1
        var intermediateNode:HalfPancakeNode = sourceNode!!.getNthNeighbor(graph!!.getDegree() - 1) as HalfPancakeNode
        var path:UniquePath = UniquePath(sourceNode!!)
        path.appendNode(intermediateNode)
        var isLeftCenter:Boolean = false
        var newPath = CreatePathForOrderedPrefixTask(graph!!,path,isLeftCenter).executeTask().getResult()

        //todo How to verify the path?
        println(newPath.toString())
    }

    //Test ~n - 1
    //todo check unique path when n is 8.
    @Test
    fun testCreatePathOnOddType2(){


        initTestGraph(10)
        //Check ~n-1
        var intermediateNode:HalfPancakeNode = sourceNode!!.getNthNeighbor(graph!!.getDegree() - 2) as HalfPancakeNode
        var path:UniquePath = UniquePath(sourceNode!!)
        path.appendNode(intermediateNode)
        var isLeftCenter:Boolean = true

        var newPath = CreatePathForOrderedPrefixTask(graph!!,path,isLeftCenter).executeTask().getResult()

        //todo How to verify the path?
        println(newPath.toString())
    }


    //Test ~n
    @Test
    fun testCreatePathOnEven(){

        initTestGraph(9)
        //Check ~n-1
        var intermediateNode:HalfPancakeNode = sourceNode!!.getNthNeighbor(graph!!.getDegree() - 1) as HalfPancakeNode
        var path:UniquePath = UniquePath(sourceNode!!)
        path.appendNode(intermediateNode)
        var isLeftCenter:Boolean = false
        var newPath = CreatePathForOrderedPrefixTask(graph!!,path,isLeftCenter).executeTask().getResult()

        //todo How to verify the path?
        println(newPath.toString())
    }
}

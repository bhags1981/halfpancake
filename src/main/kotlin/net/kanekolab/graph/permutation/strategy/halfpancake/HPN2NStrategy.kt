package net.kanekolab.graph.permutation.strategy.halfpancake

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.strategy.halfpancake.n2n.HPCaseFactory
import net.kanekolab.graph.permutation.vo.halfpancake.HPCase
import net.kanekolab.graph.permutation.vo.halfpancake.HPCaseType

/**
 * Created by bhags on 2016/11/21.
 */
internal class HPN2NStrategy(halfPancakeGraph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) {
    var case : HPCase? = null
    val _graph = halfPancakeGraph
    val _sourceNode = sourceNode
    var _destinationNode =  destinationNode


    fun run(){
        case = HPCaseFactory(_graph,_sourceNode,_destinationNode).createCase()

        //Check Case
        // N is odd or even


        // case 1.
        //Do step1.

        //Do step2.

        //Do step3.

        //Do step4.



    }
}
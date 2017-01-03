package net.kanekolab.graph.permutation.service

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sinyu on 2017/01/02.
 */
class PermutationServiceTest {
//    @Test
//    fun getRandomPermutation() {
//        println(PermutationService().getRandomPermutation(9))
//    }

    @Test
    fun saveAllPermutation() {

        val saveDir ="/Users/jung/tmp/"+SimpleDateFormat("yyMMdd_HHmmss").format(Calendar.getInstance().getTime())
        val fileNum =5
        PermutationService().saveAllPermutationForString("23456789",saveDir,'1',fileNum)


    }

    @Test
    fun getAllPermutationForDimension(){
        PermutationService().getAllPermutationForDimension(9)
    }

}
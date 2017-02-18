package net.kanekolab.graph.permutation.service

import org.junit.Test

import org.junit.Assert.*
import java.math.BigInteger
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

    @Test
    fun getPermutationMaxNumberForN() {
        println(PermutationService().getMaxNumberForN(20));
    }

    @Test
    fun getNthPermutationAllTest() {
        val dimension = 7;
        val permutationService = PermutationService()
        val usedSting = mutableListOf<String>()
        for (i in 0 .. 5039){
            var n = BigInteger.valueOf(i.toLong())
            var result = permutationService.getNthPermutation(dimension,n);
            println("Your ${n}th permutation for $dimension dimension is $result")
            if(usedSting.contains(result))
                throw Exception("$result is already used.")
            usedSting.add(result)
        }
    }

    @Test
    fun getNthPermutationTest() {
        val dimension = 5;
        val permutationService = PermutationService()
        var n = BigInteger.valueOf(10)
        var result = permutationService.getNthPermutation(dimension, n);
        println("Your ${n}th permutation for $dimension dimension is $result")
    }

    @Test
    fun substringReverseTest(){
        assertEquals(PermutationService().prefixReversalOperation("123456",0),"123456");
        assertEquals(PermutationService().prefixReversalOperation("123456",1),"213456");
        assertEquals(PermutationService().prefixReversalOperation("123456",2),"321456");
        assertEquals(PermutationService().prefixReversalOperation("123456",3),"432156");
        assertEquals(PermutationService().prefixReversalOperation("123456",4),"543216");
        assertEquals(PermutationService().prefixReversalOperation("123456",5),"654321");
    }
}
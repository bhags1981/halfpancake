package net.kanekolab.graph.permutation.service

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by sinyu on 2017/01/02.
 */
class PermutationServiceTest {
    @Test
    fun getRandomPermutation() {
        println(PermutationService().getRandomPermutation(9))
    }

}
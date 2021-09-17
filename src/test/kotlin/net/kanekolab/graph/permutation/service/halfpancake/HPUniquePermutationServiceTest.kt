package net.kanekolab.graph.permutation.service.halfpancake

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by bhags on 2016/12/30.
 */
class HPUniquePermutationServiceTest {
    @Test
    fun getPermutation() {
        var hpUniquePermutationService = HPUniquePermutationService()
        var uniqueDefaultString = "1234567"
        var avoidA = "1234567"
        var avoidB = "3561274"
        for ( i in 0..4){
            var permutation = hpUniquePermutationService.getPermutation(i,uniqueDefaultString,avoidA,avoidB)
            assertFalse(avoidA.equals(permutation))
            assertFalse(avoidB.equals(permutation))
            assertFalse(avoidA.reversed().equals(permutation))
            assertFalse(avoidB.reversed().equals(permutation))
            println(permutation)
        }


    }

}
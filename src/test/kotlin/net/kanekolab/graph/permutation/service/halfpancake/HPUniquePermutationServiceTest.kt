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
        var uniqueDefaultString = "1234"
        var avoidA = "4312"
        var avoidB = "2143"
        for ( i in 0..5){
            var permutation = hpUniquePermutationService.getPermutation(i,uniqueDefaultString,avoidA,avoidB)
            assertFalse(avoidA.equals(permutation))
            assertFalse(avoidB.equals(permutation))
            assertFalse(avoidA.reversed().equals(permutation))
            assertFalse(avoidB.reversed().equals(permutation))

        }


    }

}
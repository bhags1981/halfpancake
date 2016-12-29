package net.kanekolab.graph.permutation.service.halfpancake.task

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by sinyu on 2016/12/28.
 */
class FindPermutationThatUniqueToEachPathTaskTest {
    @Test
    fun executeTask() {

        //Must have same kind of symbols
        // Ex 123 and 321 is OK
        // Ex 123 and 324 is not OK
        var defaultPermutation = "a3421"
        var defaultPermutation2 = "412a3"



        var permutations  = CreateSortedPermutationsTask(defaultPermutation).executeTask().getResult()
        var permutations2 = CreateSortedPermutationsTask(defaultPermutation2).executeTask().getResult()

        //Must be same order between permutations and permutations2.
        assertTrue(permutations.equals(permutations2))

    }

}


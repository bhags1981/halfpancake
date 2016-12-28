package net.kanekolab.graph.permutation.service.halfpancake.task

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by sinyu on 2016/12/28.
 */
class FindPermutationThatUniqueToEachPathTaskTest {
    @Test
    fun executeTask() {
        var defaultPermutation = "a3421"
        var permutationB = "214A3"
        var permutationC = "31A42"
        var findPermutationThatUniqueToEachPathTask = FindPermutationsThatUniqueToEachPathTask(defaultPermutation,permutationB,permutationC)
        findPermutationThatUniqueToEachPathTask.executeTask()
        var permutations = findPermutationThatUniqueToEachPathTask.getResult()

        //Check Constrains.
        assertFalse(permutations.contains(defaultPermutation))
        assertFalse(permutations.contains(permutationB))
        assertFalse(permutations.contains(permutationC))
        assertFalse(permutations.contains(permutationB.reversed()))
        assertFalse(permutations.contains(permutationC.reversed()))
        println(permutations)


    }

}


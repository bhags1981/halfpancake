package net.kanekolab.graph.halfpancake.manager

import net.kanekolab.graph.halfpancake.Constants

/**
 * Created by bhags on 2016/11/20.
 */
class PermutationManager {

    fun main(args: Array<String>) {
        permutation("123456789ab")
    }

    private fun permutation(str: String) {
        permutation("", str)
    }

    private fun permutation(prefix: String, str: String) {
        val n = str.length
        if (n == 0)
            println(prefix)
        else {
            for (i in 0..n - 1)
                permutation(prefix + str[i], str.substring(0, i) + str.substring(i + 1, n))
        }
    }

    fun craeteAndSaveAllPermutationForString(identityPermutation: String, filename:String) {

    }

    /**
     *
     */
    fun getIdentifyPermutationForDimension(Dimension:Int): String {

        if(Constants.maxPermutation.length < Dimension)
            throw Exception("Dimension overflow");

        return Constants.maxPermutation.substring(0,Dimension -1);

    }

}
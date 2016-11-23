package net.kanekolab.graph.permutation.model

import net.kanekolab.graph.permutation.core.Constants
import java.awt.Dimension

/**
 * Created by bhags on 2016/11/20.
 */
class Permutation {
    private var permutationList:MutableList<String> = mutableListOf();
    fun main(args: Array<String>) {
        saveAllPermutation("123456789ab")
    }

    private fun saveAllPermutation(str: String) {
        saveAllPermutation("", str)
    }

    private fun saveAllPermutation(prefix: String, str: String) {
        val n = str.length
        if (n == 0) {
            this.permutationList.add(prefix)
        } else {
            for (i in 0..n - 1)
                saveAllPermutation(prefix + str[i], str.substring(0, i) + str.substring(i + 1, n))
        }
    }

    fun getAllPermutationForDimension(dimension : Int):List<String>{
        return getAllPermutationForString(getIdentifyPermutationForDimension(dimension))
    }

    fun getAllPermutationForString(identityPermutation: String):List<String>{
        saveAllPermutation("",identityPermutation);
        return this.permutationList;
    }

    /**
     *
     */
    fun getIdentifyPermutationForDimension(Dimension:Int): String {

        if(Constants.maxPermutation.length < Dimension)
            throw Exception("Dimension overflow");

        return Constants.maxPermutation.substring(0,Dimension );
    }
}
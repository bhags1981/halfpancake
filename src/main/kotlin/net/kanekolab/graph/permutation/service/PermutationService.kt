package net.kanekolab.graph.permutation.service

import net.kanekolab.graph.permutation.core.Constants
import java.awt.Dimension
import java.io.File
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bhags on 2016/11/20.
 */
class PermutationService {
    private var _permutationList:MutableList<String> = mutableListOf()
    private var _pathToFile =""
    private var _excludedPrefix =""
    private var _counter:Int = 0
    private var _totalFileNum:Int = 10
    fun getAllPermutationForString(identityPermutation: String):List<String>{
        _permutationList = mutableListOf()
        addAllPermutation("",identityPermutation);
        return this._permutationList;
    }

    private fun addAllPermutation(prefix: String, str: String) {
        val n = str.length
        if (n == 0) {
            //Write to file.
            _permutationList.add(prefix)
        } else {
            for (i in 0..n - 1)
                addAllPermutation(prefix + str[i], str.substring(0, i) + str.substring(i + 1, n))
        }
    }


    private fun saveAllPermutation(prefix: String, str: String) {
        val n = str.length
        if (n == 0) {
            File("$_pathToFile${_counter % _totalFileNum}.txt").appendText(_excludedPrefix+prefix+"\n")
            _counter++
        } else {
            for (i in 0..n - 1)
                saveAllPermutation(prefix + str[i], str.substring(0, i) + str.substring(i + 1, n))
        }
    }

    fun getAllPermutationForDimension(dimension : Int):List<String>{
        return getAllPermutationForString(getIdentifyPermutationForDimension(dimension))
    }


    fun saveAllPermutationForString(permutationDefault: String, directory:String,excludedPrefix:Char,totalFileNum:Int){

        _excludedPrefix=""+excludedPrefix
        _totalFileNum = totalFileNum



        var fileManager = File(directory)
        if(!fileManager.exists()){ fileManager.mkdir() }
        if(_excludedPrefix.length > 0)
            _pathToFile = directory + "/" +_excludedPrefix+"_"

        saveAllPermutation("",permutationDefault)
    }



    fun getNthPermutation(permutationDimension:Int, n:BigInteger):String{
        if(n.compareTo(getMaxNumberForN(permutationDimension))!= -1){
            throw IndexOutOfBoundsException("n(your input is $n ) must less than permutation max " +getMaxNumberForN(permutationDimension))
        }

        val identityPermutation = getIdentifyPermutationForDimension(permutationDimension)
        val permSize = identityPermutation.length;
        val permArray = identityPermutation.toCharArray()
        val returnPermArray = CharArray(permArray.size)
        var arrangeList = mutableListOf<Int>()
        var remainedPositionList = mutableListOf<Int>()
        var numerator = n;
        remainedPositionList.add(0)
        for (i in permSize - 1 downTo 1 step 1){
            if(numerator.equals(BigInteger.ZERO)){
                arrangeList.add(0)

            }else{
                var result  = numerator.divideAndRemainder(getMaxNumberForN(i))
                arrangeList.add(result[0].toInt())
                numerator = result[1]
            }
            remainedPositionList.add(permSize - i)
        }
        //Last
        arrangeList.add(0)


        for(i in 0 .. permArray.size - 1){
            var arrange = arrangeList[i]
            //var newPosition = defaultPosition+arrange
            var newPosition = remainedPositionList[arrange]
            remainedPositionList.remove(newPosition)

            returnPermArray[newPosition] = permArray[i]
        }
        return String(returnPermArray)
    }

//    fun getN(permString:String):BigInteger{
////        val identityArray =
////        val permArray = permString.toCharArray()
////        return 0;
//
//
//        return BigInteger.ZERO;
//    }




    /**
     *
     */
    fun getIdentifyPermutationForDimension(Dimension:Int): String {

        if(Constants.maximumPermutation.length < Dimension)
            throw Exception("Dimension overflow");

        return Constants.maximumPermutation.substring(0,Dimension );
    }

    fun getRandomPermutation(dimension: Int):String{
        var identity = getIdentifyPermutationForDimension(dimension)
        var rand = shuffle(identity.toCharArray().toMutableList())
        var result = ""
        rand.forEach { char->result=result+char }
        return result

    }

    private fun <T:Comparable<T>>shuffle(items:MutableList<T>):List<T>{
        val rg : Random = Random()
        for (i in 0..items.size - 1) {
            val randomPosition = rg.nextInt(items.size)
            val tmp : T = items[i]
            items[i] = items[randomPosition]
            items[randomPosition] = tmp
        }
        return items
    }

    fun getMaxNumberForN(n:Int):BigInteger{
        if(n < 1 || n > Constants.maximumPermutationSize){
            throw IndexOutOfBoundsException("Current n($n) does not supported. n must be less or equal than ${Constants.maximumPermutationSize} and bigger than 0")
        }
        var maxNumber = BigInteger.ONE;
        for (i in n downTo 1 step 1){
            maxNumber = maxNumber.multiply( BigInteger.valueOf(i.toLong()))
        }
        return maxNumber
    }


}
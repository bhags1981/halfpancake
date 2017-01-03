package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.service.halfpancake.task.CreateSortedPermutationsTask


/**
 * To construct disjoint paths, we want to find a permutation
 * that is unique to each path.
 *  Detail of function ϕ.
For a set of k(≥ 4) distinct integers A = {a1,a2,...,ak},
two permutations of k distinct integers b = (b1,b2,...,bk)
and c = (c1, c2,..., ck), and an integer i (1 ≤ i ≤ k + 2),
ϕ(i,A,b, c) returns a permutation of A that satisfies the following conditions:
• ϕ(i,A,b,c) != ϕ(j,A,b,c) if i != j.
• ϕ(i,A,b,c) !∈ {b,c}.
 * Note that three sets A, {b1,b2,...,bk}, and {c1, c2,..., ck} may be distinct.
 * Created by sinyu on 2016/12/28.
 */
class HPUniquePermutationService {
    private var _permutationMap:MutableMap<String,List<String>> = mutableMapOf()

    //Warning -
    //distinctSortedElements must be sorted.
    fun getPermutation(uniqueIdentifierI:Int, distinctElements:String, avoidPermutationA:String, avoidPermutationB:String):String{

        var charArray = distinctElements.toCharArray()
        charArray.sort()
        var distinctSortedElements:String = String(charArray)
        //Do fly light
        if(_permutationMap[distinctSortedElements] == null){
          _permutationMap[distinctSortedElements] =
                  CreateSortedPermutationsTask(distinctSortedElements).executeTask().getResult().filter{
              element -> (element != avoidPermutationA && element != avoidPermutationB && element != avoidPermutationA.reversed() && element != avoidPermutationB.reversed())
            }
        }
        return _permutationMap[distinctSortedElements]!![uniqueIdentifierI]
    }
}
package net.kanekolab.graph.permutation.service.halfpancake

import net.kanekolab.graph.permutation.model.LogData
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

        LogData.append("AVOID FOR A $avoidPermutationA AVOID FOR B $avoidPermutationB")
        var charArray = distinctElements.toCharArray()
        charArray.sort()
        var distinctSortedElements:String = String(charArray)
        //Do fly light


        /**
         * element != avoidPermutationA && element != avoidPermutationB これだけをチェックすると
         * 偶数の場合shが一つの場合渡されるelementの長さが違うためエラーになる
         * なので
         * && element.subSequence(0,element.length-1) != avoidPermutationA
         *  && element.subSequence(0,element.length-1) != avoidPermutationB
         *  を追加した
         *  上記を削除したあと、出発頂点を123456789 / 目的頂点を12357a9864で設定すると頂点の重複を確認できる
         * これは3674982a51と4689a71235の間で発見できるshが3のみであり(FRONT_CENTER 367498 REAR 1235)
         * これによって得られる順列初期値は67498となり、さらにこれで得られる順列が46897になるため5321a79864を重複して使ってしまう。
         */
        if(_permutationMap[distinctSortedElements] == null){
          _permutationMap[distinctSortedElements] =
                  CreateSortedPermutationsTask(distinctSortedElements).executeTask().getResult().filter{
        //              element -> (element != avoidPermutationA && element != avoidPermutationB && element != avoidPermutationA.reversed() && element != avoidPermutationB.reversed())
                      element ->
                      (element != avoidPermutationA && element != avoidPermutationB
                      && element.subSequence(0,element.length-1) != avoidPermutationA
                      && element.subSequence(0,element.length-1) != avoidPermutationB )
                  }
        }
        var result =  _permutationMap[distinctSortedElements]!![uniqueIdentifierI]
        LogData.append("RESULT OF UNIQUE PERMUTATION $result" )
        return result
    }
}
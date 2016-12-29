package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.service.PermutationService
import net.kanekolab.graph.permutation.service.ServiceTask
import java.util.*

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
 * This class return sorted permutation.
 */
class CreateSortedPermutationsTask(defaultPermutation:String) : ServiceTask<CreateSortedPermutationsTask,List<String>>{


    var _defaultPermutation = defaultPermutation
    var _permutationList:List<String>? = null


    override fun executeTask() : CreateSortedPermutationsTask{
        //throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.

        //1. Make all permutation for default permutation
        var tmpPermutation = PermutationService().getAllPermutationForString(_defaultPermutation)

        //2. Remove B and C while Sorting.
        _permutationList = tmpPermutation.sorted()
        return this
    }

    override fun getResult(): List<String> {
        //throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        return _permutationList!!
    }
}
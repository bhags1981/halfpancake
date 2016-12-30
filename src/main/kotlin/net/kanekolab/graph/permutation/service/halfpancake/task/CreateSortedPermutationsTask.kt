package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.service.PermutationService
import net.kanekolab.graph.permutation.service.ServiceTask
import java.util.*


class CreateSortedPermutationsTask(defaultPermutation:String) : ServiceTask<CreateSortedPermutationsTask,List<String>>{


    var _defaultPermutation = defaultPermutation
    var _permutationList:List<String>? = null


    override fun executeTask() : CreateSortedPermutationsTask{
        //throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        //1. Make all permutation for default permutation
        _permutationList = PermutationService().getAllPermutationForString(_defaultPermutation).sorted()
        return this
    }

    override fun getResult(): List<String> {
        //throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        return _permutationList!!
    }
}
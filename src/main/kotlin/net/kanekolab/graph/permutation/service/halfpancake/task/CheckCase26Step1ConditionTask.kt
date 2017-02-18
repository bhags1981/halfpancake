package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.PermutationService
import net.kanekolab.graph.permutation.service.ServiceTask

/**
 * Created by bhags on 2016/12/31.
 *
 *
 *
  */
class CheckCase26Step1ConditionTask(sourceNode : HalfPancakeNode, destinationNode: HalfPancakeNode) : ServiceTask<CheckCase26Step1ConditionTask,Pair<Boolean,Int>>{
    var _sourceNode = sourceNode
    var _destinationNode = destinationNode
    var _isIncluded = false
    var _positionL = -1

    override fun executeTask(): CheckCase26Step1ConditionTask {
        val frontCenterOfDest = _destinationNode.getFrontCenterString()
        val centerRearOfSource = _sourceNode.getCenterRearString()

        //判断基準文字列は(S_n, S_{n-1}, S_{n−2}, ..., S_{~n},s1)
        //一回の反転で上記文字列になるものがあれば、経路構築を先に行う。
        val compareSubString = centerRearOfSource.substring(1).reversed().plus(_sourceNode.getId()[0])

        for(i in 0 .. _sourceNode.getDegree() - 1){
            if(PermutationService().prefixReversalOperation(compareSubString,i).equals(frontCenterOfDest)){

                //Case 2-6 (s^(˜n−1,n) ∈ P(d) and d^n ̸∈ P(s))であるため i = 0 は弾かれる。 i = 0で成立すると d^n ∈ P(s)である。
                if(i == 0 )
                    throw Exception("This source node ${_sourceNode.getId()} and ${_destinationNode.getId()} is not case 2_6.")

                _isIncluded = true
                _positionL = i + 1
                return this

            }
        }

        _isIncluded = false
        _positionL = -1
        return this
    }

    override fun getResult(): Pair<Boolean, Int> {
        return Pair(_isIncluded,_positionL)
    }
}
package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.ServiceTask

/**
 * Created by bhags on 2016/12/31.
 */
class CheckCase15Step1ConditionTask(sourceNode : HalfPancakeNode, destinationNode: HalfPancakeNode) :ServiceTask<CheckCase15Step1ConditionTask, Pair<Boolean,Int>> {
    var _sourceNode = sourceNode
    var _destinationNode = destinationNode
    var _isIncluded = false
    var _positionL = -1

    override fun executeTask(): CheckCase15Step1ConditionTask {
        var frontOfDest = _destinationNode.getFrontString()
        var rearOfSource = _sourceNode.getRearString()
        var reversedRearSourceString = rearOfSource.reversed()
        for(i in 1 .. frontOfDest.length - 1){
            var subString = reversedRearSourceString.substring(0,i + 1)
            var remainedString = if(i == frontOfDest.length) "" else reversedRearSourceString.substring(i+1)
            var newString = subString.reversed() + remainedString
            if(frontOfDest.equals(newString)){
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

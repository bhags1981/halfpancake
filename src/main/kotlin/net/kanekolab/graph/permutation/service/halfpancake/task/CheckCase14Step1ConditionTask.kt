package net.kanekolab.graph.permutation.service.halfpancake.task

import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.ServiceTask

/**
 * Created by bhags on 2016/12/31.
 */
class CheckCase14Step1ConditionTask(sourceNode : HalfPancakeNode, destinationNode: HalfPancakeNode, positionK:Int) : ServiceTask<CheckCase14Step1ConditionTask, Pair<Boolean,Int>> {
    var _sourceNode = sourceNode
    var _destinationNode = destinationNode
    var _isIncluded = false
    var _positionL = -1
    var _positionK = positionK

    override fun executeTask(): CheckCase14Step1ConditionTask {
        var frontCenterOfDest = _destinationNode.getFrontCenterString()
        var centerRearOfSource = _sourceNode.getCenterRearString()


        if(frontCenterOfDest.equals(centerRearOfSource)) {
            _isIncluded = true
            _positionL = 2
            return this
        }

        for(i in 1 .. centerRearOfSource.length - 1){
            // get Substring
            var subString = centerRearOfSource.substring(0,i)
            var remainedString = centerRearOfSource.substring(i)
            var newString = remainedString + subString.reversed()
            if(frontCenterOfDest.equals(newString)){
                _isIncluded = true
                _positionL = _sourceNode.getHalfPosition() - (centerRearOfSource.length - i) + 2
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
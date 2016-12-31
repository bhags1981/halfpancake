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
        var frontCenterOfDest = _destinationNode.getFrontCenterString()
        var centerRearOfSource = _sourceNode.getCenterRearString()


        if(frontCenterOfDest.equals(centerRearOfSource)) {
            _isIncluded = true
            _positionL = _sourceNode.getHalfPosition()
            return this
        }

        for(i in 1 .. centerRearOfSource.length - 1){
            /**
             * Except k position
             * Because  in case 1-4 k,n = P(d)
             *  Ex
             *  S = 8976(54312)
             *  D = 123456789
             *  k == 2
             *  Skip i == (centerRearOfSource.length - k )
             *
             */

            // get Substring
            var subString = centerRearOfSource.substring(0,i)
            var remainedString = centerRearOfSource.substring(i)
            var newString = remainedString + subString.reversed()
            if(frontCenterOfDest.equals(newString)){
                _isIncluded = true
                _positionL = centerRearOfSource.length - i
                return this
            }
        }

        _isIncluded = false
        _positionL = -1
        return this
    }

    override fun getResult(): Pair<Boolean, Int> {
        throw UnsupportedOperationException()
    }
}

//
//override fun executeTask(): CheckCase14Step1ConditionTask {
//    var frontOfDest = _destinationNode.getFrontString()
//    var rearOfSource = _sourceNode.getRearString()
//    var reversedRearSourceString = rearOfSource.reversed()
//    for(i in 1 .. frontOfDest.length){
//        var subString = reversedRearSourceString.substring(0,i + 1)
//        var remainedString = reversedRearSourceString.substring(i+1)
//        var newString = remainedString + subString.reversed()
//        if(frontOfDest.equals(newString)){
//            _isIncluded = true
//            _positionL = i + 1
//            return this
//        }
//    }
//
//    _isIncluded = false
//    _positionL = -1
//    return this
//}
//
//override fun getResult(): Pair<Boolean, Int> {
//    return Pair(_isIncluded,_positionL)
//}
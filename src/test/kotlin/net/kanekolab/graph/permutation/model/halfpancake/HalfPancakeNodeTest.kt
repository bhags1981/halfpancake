package net.kanekolab.graph.permutation.model.halfpancake

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by bhags on 2016/12/29.
 */
class HalfPancakeNodeTest{
    @Test
    fun checkPositionMethod(){

        //Check Odd Case
        var hpNodeOdd = HalfPancakeNode("1234567",4)
        assertTrue(hpNodeOdd.getFrontString().equals("123"))
        assertTrue(hpNodeOdd.getCenterString().equals("4"))
        assertTrue(hpNodeOdd.getRearString().equals("567"))
        assertTrue(hpNodeOdd.getFrontCenterString().equals("1234"))
        assertTrue(hpNodeOdd.getCenterRearString().equals("4567"))
        //Check Even Case
        var hpNodeEven = HalfPancakeNode("12345678",5)
        assertTrue(hpNodeEven.getFrontString().equals("123"))
        assertTrue(hpNodeEven.getCenterString().equals("45"))
        assertTrue(hpNodeEven.getRearString().equals("678"))
        assertTrue(hpNodeEven.getFrontCenterString().equals("12345"))
        assertTrue(hpNodeEven.getCenterRearString().equals("45678"))
    }

}
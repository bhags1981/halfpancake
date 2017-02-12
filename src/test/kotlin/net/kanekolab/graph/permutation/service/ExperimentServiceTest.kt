package net.kanekolab.graph.permutation.service

import net.kanekolab.graph.permutation.core.Config
import org.junit.Test

import org.junit.Assert.*
import java.math.BigInteger

/**
 * Created by bhags on 2017/01/30.
 */
class ExperimentServiceTest {
    @Test
    fun run() {
        Config.interimReportInterval = 10000
        ExperimentService(0,10, BigInteger.ONE, BigInteger.valueOf(100000)).run()
    }

}
package net.kanekolab.graph.permutation.service

import net.kanekolab.graph.permutation.core.Config
import net.kanekolab.graph.permutation.core.Constants
import net.kanekolab.graph.permutation.model.ExperimentLogData
import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.service.halfpancake.HPDisjointPathService
import java.math.BigInteger
import kotlin.system.exitProcess

/**
 * Created by bhags on 2017/01/30.
 */
class ExperimentService(graphType:Int, graphDimension:Int, startPermutationId:BigInteger, endPermutationId:BigInteger) {
    private val _graphType = graphType
    private val _graphDimension = graphDimension
    private val _startPermutationId = startPermutationId
    private val _endPermutationId  = endPermutationId
    fun run(){
        //create graph by graph type
        val mailService = MailService()
        val mailFrom = Config.gmailUserName
        val mailTo = "jungsinyu@gmail.com"




        if(_graphType != 0){
            mailService.sendMail(mailFrom,mailTo,"Experiments fail","Graph type does not supported : " + _graphType)
            return
        }

        val graph = HalfPancakeGraph(_graphDimension)
        val disjointPathService = HPDisjointPathService(graph)
        val permutationService = PermutationService()

        val max = permutationService.getMaxNumberForN(_graphDimension)

        if(
        _startPermutationId.compareTo(BigInteger.ZERO)!= 1 ||
                _endPermutationId.compareTo(BigInteger.ZERO)!= 1
        ){
            mailService.sendMail(mailFrom,mailTo,"Experiments fail"," Out of index.\r\n Graph dimension is $_graphDimension and your input is   $_startPermutationId , $_endPermutationId. \r\n Your input must bigger than 0" )
            return
        }


        if(
            _startPermutationId.compareTo(max)!= -1 ||
            _endPermutationId.compareTo(max)!= -1
        ){
            mailService.sendMail(mailFrom,mailTo,"Experiments fail"," Out of index.\r\n Graph dimension is $_graphDimension and your input is   $_startPermutationId , $_endPermutationId. \r\n Your input must less than $max" )
            return
        }


        if(_startPermutationId.compareTo(_endPermutationId)!= -1){
            mailService.sendMail(mailFrom,mailTo,"Experiments fail"," Out of index.\r\n Graph dimension is $_graphDimension and your input is   $_startPermutationId , $_endPermutationId. \r\n Your start permutation id  must less than end permutation id" )
            return
        }



        val startNode  = graph.getNodeById(permutationService.getIdentifyPermutationForDimension(_graphDimension))
        mailService.sendMail(mailFrom,mailTo,
                "Experiments Started from $_startPermutationId to $_endPermutationId ",
                "Find disjoint paths for half pancake graph experiment started \r\n" +
                " Graph dimension : $_graphDimension \r\n "+
                " From $_startPermutationId  to  $_endPermutationId \r\n "


        )

        var currentPermutationId = _startPermutationId
        var destinationNodeId = permutationService.getNthPermutation(_graphDimension, currentPermutationId)
        var destinationNode = graph.getNodeById(destinationNodeId)
        while(true){
            try {

                //Finding paths.
                disjointPathService.initProblemN2N(startNode, destinationNode)
                disjointPathService.findDisjointPaths()

//                ExperimentLogData.append("Finished between S: ${startNode.getId()} and D: ${destinationNode.getId()}")
//                ExperimentLogData.append(
//                        "\r\n"+disjointPathService.disjointPaths!!.joinToString("\r\n")
//                                + "\r\n===================================================================================================\r\n")


                //Check Unique nodes.
                var _nodeMap:MutableMap<String,String> = mutableMapOf()
                disjointPathService.disjointPaths!!.forEach{
                    it.getList().forEach {
                        if(_nodeMap.containsKey(it)&&
                                !it.equals(startNode.getId()) &&
                                !it.equals(destinationNode.getId()) &&
                                !it.equals(Constants.pathPNN) &&
                                !it.equals(Constants.pathPNS)){
                            var message =
                                    " Fatal error occur on half pancake graph experiment \r\n" +
                                            " Graph dimension : $_graphDimension \r\n "+
                                            " From $_startPermutationId  to  $_endPermutationId \r\n "+
                                            " Current id $currentPermutationId ($destinationNodeId) \r\n"+
                                            " Error : Unique node error for $it \r\n" +
                                            " ALL PATHS : " + disjointPathService.disjointPaths!!.joinToString("\r\n")
                            mailService.sendMail(mailFrom,mailTo,"Fatal error occur on pancake graph experiment",message)
                            exitProcess(-1)
                        }
                        _nodeMap.put(it,it)
                    }
                }

                //Send interim report
                if(currentPermutationId.divideAndRemainder(BigInteger.valueOf(Config.interimReportInterval))[1].equals(BigInteger.ZERO)){
                    var title = "Half pancake graph experiment interim report for $currentPermutationId ($destinationNodeId)"
                    var message =
                            "Half pancake graph experiment interim report \r\n" +
                                    " Graph dimension : $_graphDimension \r\n "+
                                    " From $_startPermutationId  to  $_endPermutationId \r\n "+
                                    " Current id $currentPermutationId ($destinationNodeId) \r\n"
                  //                  ExperimentLogData.getLog()
                    mailService.sendMail(mailFrom,mailTo,title,message)
                   // ExperimentLogData.init()
                }

                currentPermutationId = currentPermutationId.add(BigInteger.ONE)

                if(currentPermutationId.compareTo(_endPermutationId) == 1)
                    break
                destinationNodeId = permutationService.getNthPermutation(_graphDimension, currentPermutationId)
                destinationNode = graph.getNodeById(destinationNodeId)
            }catch(e:Exception){

                var message =
                        " Fatal error occur on half pancake graph experiment \r\n" +
                        " Graph dimension : $_graphDimension \r\n "+
                        " From $_startPermutationId  to  $_endPermutationId \r\n "+
                        " Current id $currentPermutationId ($destinationNodeId) \r\n"+
                        " Error " + e.message
                        " LOG " + LogData
                        " Stack " + e.stackTrace.joinToString("\r\n")

                mailService.sendMail(mailFrom,mailTo,"Fatal error occur on pancake graph experiment",message)
                exitProcess(-1)
            }


        }


        //Finish Log
        var title = "Half pancake graph experiment finished for $_endPermutationId ($destinationNodeId)"
        var message =
                "Half pancake graph experiment finished \r\n" +
                        " Graph dimension : $_graphDimension \r\n "+
                        " From $_startPermutationId  to  $_endPermutationId \r\n "
                        //ExperimentLogData.getLog()
        mailService.sendMail(mailFrom,mailTo,title,message)
    }
}
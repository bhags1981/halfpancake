package net.kanekolab.graph.permutation.service.halfpancake.cases

import net.kanekolab.graph.permutation.model.LogData
import net.kanekolab.graph.permutation.model.Path
import net.kanekolab.graph.permutation.model.UniquePath
import net.kanekolab.graph.permutation.model.UsedNodeIds
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeGraph
import net.kanekolab.graph.permutation.model.halfpancake.HalfPancakeNode
import net.kanekolab.graph.permutation.service.halfpancake.HPRestrictedRoutingService

/**
 * Created by bhags on 2017/01/05.
 */
class HPDCase2_8_Service (graph: HalfPancakeGraph, sourceNode: HalfPancakeNode, destinationNode: HalfPancakeNode) : HPDCaseService(graph,sourceNode,destinationNode){

    override fun constructDisjointPaths() {
        LogData.append("[HPDCase2_8_Service] started Case 2_8")
        val n = _sourceNode.getLength()
        val _n = _sourceNode.getHalfPosition()
        var tmpPathsA = mutableMapOf<String, Path>()
        var tmpPathsB = mutableMapOf<String, Path>()

        /**
         *
         * Step 1) 
         * Select (˜n − 2) paths si: s → s(i) → s(i,n) def = ai(2 ≤ i ≤ ˜n−2) and s1: s→s(n) def = a1. 
         * Also, select two paths s˜n−1: s → s( ˜n−1) → s( ˜n−1, ˜n) → s( ˜n−1, ˜n, ˜n−1) →s( ˜n−1, ˜n, ˜n−1,2) → s( ˜n−1, ˜n, ˜n−1,2,n) def = a˜n−1
         * and s˜n: s →s( ˜n)→s( ˜n, ˜n−1)→s( ˜n, ˜n−1,2)→s( ˜n, ˜n−1,2, ˜n−1)→s( ˜n, ˜n−1,2,˜n−1, ˜n) →s( ˜n, ˜n−1,2, ˜n−1, ˜n,n) def = a˜n.
         * 
         * 
         * 
         */


        //Add a1
        var tmpPath = UniquePath(_sourceNode).append(n)
        var key = tmpPath.getLastNode().getSuffixForSubGraph()
        tmpPathsA.put(key,tmpPath)


        //Add a2 ~ a(~n-2)
        for(i in 2.._n-2){
            tmpPath = UniquePath(_sourceNode).appendNodesByIndexes(i,n)
            key = tmpPath.getLastNode().getSuffixForSubGraph()
            tmpPathsA.put(key,tmpPath)
        }


        /**
         *  * Select ( ˜n−1) paths σi: s → s(i) → s(i,n) def = ai (2 ≤i ≤ n˜−1) and σ1: s → s(n)def = a1.
         * Also, select a path σn˜:s → s(n˜) → s(n˜,3) → s(n˜,3,2) → s(n˜,3,2,3) → s(n˜,3,2,3,n˜) →s(n˜,3,2,3,n˜,n)def = an˜
        */
        //Add ~n-1
        tmpPath = UniquePath(_sourceNode)
                .appendNodesByIndexes(_n-1,_n,_n-1)//,2,n)
        //2を行なって出発頂点のサブグラフに行く場合があるので確認して解除
        //これはケースを増やして例外処理をすることで治るが一旦こちらで対応
        if((tmpPath.getLastNode() as HalfPancakeNode).getIthPrefixReversalNeighbor(2).getFrontString().reversed().equals(_destinationNode.getRearString())){
            tmpPath.appendNodesByIndexes(3,n)
        }else{
            tmpPath.appendNodesByIndexes(2,n)
        }

        key = tmpPath.getLastNode().getSuffixForSubGraph()
        tmpPathsA.put(key,tmpPath)

        //Add ~n
        tmpPath = UniquePath(_sourceNode)
                .appendNodesByIndexes(_n,_n-1,2,_n-1,_n)//,n)

        if((tmpPath.getLastNode() as HalfPancakeNode).getFrontString().reversed().equals(_destinationNode.getRearString())){
            tmpPath.appendNodesByIndexes(2,n)
        }else{
            tmpPath.appendNodesByIndexes(n)
        }
        key = tmpPath.getLastNode().getSuffixForSubGraph()
        tmpPathsA.put(key,tmpPath)




        /**
         * Step 2) Select ( ˜n − 1) paths τ j: d → d(j) → d(j,n) def = b j(2 ≤ j ≤ n˜− 1) and
         * τ1: d → d(n) def = b1.
         * Also, select a path τn˜: d → d(n˜) → d(n˜,3) → d(n˜,3,2) → d(n˜,3,2,3) →d(n˜,3,2,3,n˜) → d(n˜,3,2,3,n˜,n) def = bn˜
         */

        var tmpPath2 = UniquePath(_destinationNode).prepend(n) //b1
        var key2 = tmpPath2.getFirstNode().getSuffixForSubGraph()

        tmpPathsB.put(key2,tmpPath2)

        //Add b2 ~ b(bn˜- 2)
        for(j in 2.._n-2){
            tmpPath2 = UniquePath(_destinationNode).prependNodesByIndexes(j,n)
            key2 = tmpPath2.getFirstNode().getSuffixForSubGraph()
            tmpPathsB.put(key2,tmpPath2)
        }

        //Add b~n-1

        tmpPath2 = UniquePath(_destinationNode)
                .prependNodesByIndexes(_n-1,_n,_n-1)//,2,n)
        //2を行なって出発頂点のサブグラフに行く場合があるので確認して解除
        //これはケースを増やして例外処理をすることで治るが一旦こちらで対応
        if((tmpPath2.getFirstNode() as HalfPancakeNode).getIthPrefixReversalNeighbor(2).getFrontString().reversed().equals(_sourceNode.getRearString())){
            tmpPath2.prependNodesByIndexes(3,n)
        }else{
            tmpPath2.prependNodesByIndexes(2,n)
        }


        key2 = tmpPath2.getFirstNode().getSuffixForSubGraph()
        tmpPathsB.put(key2,tmpPath2)

        //Add b˜
        tmpPath2 =
                UniquePath(_destinationNode)
                        .prependNodesByIndexes(_n,_n-1,2,_n-1,_n)
        ////これはケースを増やして例外処理をすることで治るが一旦こちらで対応
        if((tmpPath2.getFirstNode() as HalfPancakeNode).getFrontString().reversed().equals(_sourceNode.getRearString())){
            tmpPath2.prependNodesByIndexes(2,n)
        }else{
            tmpPath2.prependNodesByIndexes(n)
        }


//        if((tmpPath2.getFirstNode() as HalfPancakeNode).getFrontString().reversed().equals(_sourceNode.getSuffixForSubGraph())){
//            tmpPath2.removeFirst().prepend(2).prepend(_n)
//        }
//        tmpPath2 = tmpPath2.prepend(n)

        key2 = tmpPath2.getFirstNode().getSuffixForSubGraph()

        tmpPathsB.put(key2,tmpPath2)



        var matchedPaths = mutableListOf<Pair<Path, Path>>()

        var removeKeys = mutableListOf<String>()
        tmpPathsA.forEach { data ->
            if(tmpPathsB.containsKey(data.key)){
                var pair = Pair(data.value,tmpPathsB[data.key]!!)
                matchedPaths.add(pair)
                removeKeys.add(data.key)
            }
        }

        removeKeys.forEach { key ->
            tmpPathsA.remove(key)
            tmpPathsB.remove(key)
        }

        var remainedListA = tmpPathsA.values
        var remainedListB = tmpPathsB.values

        for(index in 0..remainedListA.size - 1) {
            matchedPaths.add(Pair(remainedListA.elementAt(index), remainedListB.elementAt(index)))
        }




        var avoidSuffixA = _sourceNode.getSuffixForSubGraph()
        var avoidSuffixB = _destinationNode.getSuffixForSubGraph()
        for(i in 0..matchedPaths.size - 1){
            var (pathFromSrc ,pathFromDst) = matchedPaths[i]
            var src = pathFromSrc.getLastNode() as HalfPancakeNode
            var dst = pathFromDst.getFirstNode() as HalfPancakeNode
            UsedNodeIds.removeNodeId(src.getId())
            UsedNodeIds.removeNodeId(dst.getId())

            var hrRouting = HPRestrictedRoutingService(_currentGraph, i, src, dst, avoidSuffixA, avoidSuffixB)
            hrRouting.findUniquePath()

            //Make Path
            _disjointPaths.add(pathFromSrc.appendPath(hrRouting.getCurrentPath().removeLast().removeFirst()).appendPath(pathFromDst))
        }
    }
}
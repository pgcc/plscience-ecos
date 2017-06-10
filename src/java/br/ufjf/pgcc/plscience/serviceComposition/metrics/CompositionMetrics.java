/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition.metrics;

import br.ufjf.pgcc.plscience.serviceCompositionGraph.CompositionGraph;
import br.ufjf.pgcc.plscience.serviceCompositionGraph.DependsOfEdge;
import br.ufjf.pgcc.plscience.serviceCompositionGraph.GraphNode;
import br.ufjf.pgcc.plscience.serviceCompositionGraph.SimpleEdge;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author phillipe
 */
public class CompositionMetrics {

    public static void SetEdgeSizeValue(CompositionGraph graph){
        for(DependsOfEdge dep:graph.getDependsOfList()){
            GraphNode nodeTo = dep.getDependsOf().getTo();
            if(nodeTo.getClosenessValue() <= 0.5){
                dep.setSize("10");
            }else{
                dep.setSize("1");
            }
            dep.setLabel("Depends of (C "+nodeTo.getClosenessValue()+")");
        }
    }
    
    /**
     * It calculates the closeness centrality for all nodes of a composition
     * @param graph 
     */
    public static void closenessCentrality(CompositionGraph graph) {
        Double minClosenessValue = Double.MAX_VALUE;
        Double maxClosenessValue = 0.0;
        Double closenessValue = 0.0;
        for (GraphNode node : graph.getServicesNodes()) {
            Map<GraphNode, Integer> distances;
            distances = graph.calculateShortestDistanceGraph(graph, node);
            closenessValue = node.calculateClosenessValue(graph, distances);
            
            if(closenessValue > maxClosenessValue)
                maxClosenessValue = closenessValue;
            
            if(closenessValue < minClosenessValue)
                minClosenessValue = closenessValue;
            
            node.setClosenessValue(node.calculateClosenessValue(graph, distances));
        }
        
        //normalization
        for (GraphNode node:graph.getServicesNodes()){
            Double closenessNormalized = CompositionMetrics.normalizeValueLinear(minClosenessValue, maxClosenessValue, node.getClosenessValue());
            node.setClosenessValue(closenessNormalized);
        }
    }

    /**
     * It Calculates the betweenness centrality for all nodes of a composition
     * graph
     *
     * @param cg
     */
    public static void betweennessCentrality(CompositionGraph cg) {
        List<GraphNode> nodes;
        List<SimpleEdge> edges;
        List<String> edgeNames = new ArrayList<>();
        List<Node> nodesA = new ArrayList<>();
        Double minBetweennessValue = Double.MAX_VALUE;
        Double maxBetweennessValue = 0.0;
        nodes = cg.getServicesNodes();
        edges = new ArrayList<>();
        for (int i = 0; i < cg.getInteroperatesWithList().size(); i++) {
            edges.add(cg.getInteroperatesWithList().get(i).getInteroperatesWith());
        }
        for (int i = 0; i < cg.getDependsOfList().size(); i++) {
            edges.add(cg.getDependsOfList().get(i).getDependsOf());
        }

        Graph graph = new SingleGraph("Betweenness");
        Map<String, Node> nodeMapStringNode = new HashMap<>();
        Map<Node, GraphNode> nodeMapNodes = new HashMap<>();

        for (GraphNode n : nodes) {
            String nodeName = n.getServiceName();
            Node node = graph.addNode(nodeName);
            nodesA.add(node);
            nodeMapStringNode.put(nodeName, node);
            nodeMapNodes.put(node, n);
        }

        for (SimpleEdge edge : edges) {
            String from = edge.getFrom().getServiceName();
            String to = edge.getTo().getServiceName();
            String edgeName = from + "_" + to;
            String edgeName2 = to + "_" + from;
            if (!edgeNames.contains(edgeName)) {
                graph.addEdge(edgeName, from, to);
            }
            edgeNames.add(edgeName);
            edgeNames.add(edgeName2);
        }

        BetweennessCentrality bcb = new BetweennessCentrality();
        bcb.setWeightAttributeName("weight");

        for (SimpleEdge edge : edges) {
            String from = edge.getFrom().getServiceName();
            String to = edge.getTo().getServiceName();
            Node f = nodeMapStringNode.get(from);
            Node t = nodeMapStringNode.get(to);
            bcb.setWeight(f, t, 0);
        }

        bcb.init(graph);
        bcb.compute();

        for (Node n : nodesA) {
            Double betweennessValue = n.getAttribute("Cb");
            if (betweennessValue < minBetweennessValue) {
                minBetweennessValue = betweennessValue;
            }
            if (betweennessValue > maxBetweennessValue) {
                maxBetweennessValue = betweennessValue;
            }
        }

        for (Node n : nodesA) {
            GraphNode node = nodeMapNodes.get(n);
            Double betweennessValue = n.getAttribute("Cb");
            betweennessValue = normalizeValueLinear(minBetweennessValue, maxBetweennessValue, betweennessValue);
            node.setBetweennessValue(betweennessValue);

//            System.out.println("N get atr:"+n.getAttribute("Cb"));
//            System.out.println("Node name:"+node.getServiceName());
//            System.out.println("Node betweenness:"+node.getBetweennessValue());
        }

//        for(GraphNode nodeF:nodes){
//            //Node n = nodeMapNodes.get(nodeF);
//            nodeF.setBetweennessValue(n.getAttribute("Cb"));
//            System.out.println("Node name: "+nodeF.getServiceName());
//            System.out.println("Node betweness Value: "+nodeF.getBetweennessValue());
//        }
    }

    /**
     * It normalize a value for a node using the linear normalization method
     * (Values between 0 and 1)
     *
     * @param min
     * @param max
     * @param value
     * @return
     */
    public static Double normalizeValueLinear(Double min, Double max, Double value) {
        Double normalizedValue = 0.0;
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        
        //It avoids mistakes for those who have the default language like Portuguese or French
        symbols.setDecimalSeparator('.');        

        DecimalFormat format = new DecimalFormat("#.##",symbols);
        
        if ((max - min) != 0.0) {
            normalizedValue = (value - min) / (max - min);
        }

        normalizedValue = Double.valueOf(format.format(normalizedValue));

        return normalizedValue;
    }

}

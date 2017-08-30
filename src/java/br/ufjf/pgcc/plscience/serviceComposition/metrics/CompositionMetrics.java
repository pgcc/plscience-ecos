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
import br.ufjf.pgcc.plscience.socialNetworkAnalysis.GraphSN;
import br.ufjf.pgcc.plscience.socialNetworkAnalysis.NodeSN;
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

    public static void SetEdgeSizeValue(CompositionGraph graph) {
        for (DependsOfEdge dep : graph.getDependsOfList()) {
            GraphNode nodeTo = dep.getDependsOf().getTo();
            if (nodeTo.getClosenessValue() <= 0.5) {
                dep.setSize("10");
            } else {
                dep.setSize("1");
            }
            dep.setLabel("Depends of (C " + nodeTo.getClosenessValue() + ")");
        }
    }

    /**
     * It calculates the closeness centrality for all nodes of a composition
     *
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

            if (closenessValue > maxClosenessValue) {
                maxClosenessValue = closenessValue;
            }

            if (closenessValue < minClosenessValue) {
                minClosenessValue = closenessValue;
            }

            node.setClosenessValue(node.calculateClosenessValue(graph, distances));
        }

        //normalization
        for (GraphNode node : graph.getServicesNodes()) {
            Double closenessNormalized = CompositionMetrics.normalizeValueLinear(minClosenessValue, maxClosenessValue, node.getClosenessValue());
            node.setClosenessValue(closenessNormalized);
        }
    }

    public static GraphSN closenessCentralitySocialNetworkResearchers(GraphSN graph) {
        Double minClosenessValue = Double.MAX_VALUE;
        Double maxClosenessValue = 0.0;
        Double closenessValue = 0.0;
        for (NodeSN node : graph.getResearcherNodes()) {
            Map<NodeSN, Integer> distances;
            
            System.out.println("Shortest Distances to "+node.getFullName());
            distances = calculateShortestDistanceGraph(graph, node);

            System.out.println("Closeness Value to "+node.getFullName());
            closenessValue = node.calculateClosenessValue(graph, distances);

            if (closenessValue > maxClosenessValue) {
                maxClosenessValue = closenessValue;
            }

            if (closenessValue < minClosenessValue) {
                minClosenessValue = closenessValue;
            }

            String globCentrality = node.calculateClosenessValue(graph, distances).toString();
            node.setGlobalCentrality(globCentrality);
        }

        //normalization
        for (NodeSN node : graph.getResearcherNodes()) {
            Double gC = Double.parseDouble(node.getGlobalCentrality());
            Double closenessNormalized = CompositionMetrics.normalizeValueLinear(minClosenessValue, maxClosenessValue, gC);
            node.setGlobalCentrality(closenessNormalized.toString());
        }
        return graph;
    }

    public static NodeSN minDistance(List<NodeSN> nodes) {
        if (nodes != null) {
            NodeSN menor;
            menor = nodes.get(0);
            for (NodeSN n : nodes) {
                if (n.getDistanceFromSource() < menor.getDistanceFromSource()) {
                    menor = n;
                }
            }
            return menor;
        }
        System.out.println("nodes list is null in minDistance method - CompositionMetrics Class");
        return null;
    }

    public static Map<NodeSN, Integer> calculateShortestDistanceGraph(GraphSN graph, NodeSN sourceNode) {

        Map<NodeSN, Integer> distanceToSource = new HashMap<>();
        List<NodeSN> unVisitedNodes = new ArrayList<>();
        List<NodeSN> neighborsSource;

        //unvisitedNodes initially contains all nodes
        for (NodeSN node : graph.getResearcherNodes()) {
            node.setDistanceFromSource(Integer.MAX_VALUE);
            unVisitedNodes.add(node);
        }

        //initially all vertices have infinite distance (constructor)
        //setting source distance as 0
        sourceNode.setDistanceFromSource(0);

        distanceToSource.put(sourceNode, 0);

        //unVisitedNodes = copy.getServicesNodes();
        unVisitedNodes.remove(sourceNode);

        neighborsSource = NodeSN.getNodeSNNeighbors(sourceNode);
        System.out.println("Neighbors: "+neighborsSource.size());
        
        if (neighborsSource.size() > 0) {
            for (NodeSN neighb : neighborsSource) {
                neighb.setDistanceFromSource(1);
                distanceToSource.put(neighb, 1);
            }

            //while	the arrayList is not empty
            while (!unVisitedNodes.isEmpty()) {
                NodeSN u = minDistance(unVisitedNodes);
                unVisitedNodes.remove(u);

                List<NodeSN> uNeigh = NodeSN.getNodeSNNeighbors(u);

                if (u != null && uNeigh != null) {
                    for (NodeSN v : uNeigh) {
                        if (v.getDistanceFromSource() > u.getDistanceFromSource()) {
                            v.setDistanceFromSource(u.getDistanceFromSource() + 1);
                            distanceToSource.put(v, u.getDistanceFromSource() + 1);
                        }
                    }
                }
            }

        }

        return distanceToSource;
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

        DecimalFormat format = new DecimalFormat("#.##", symbols);

        if ((max - min) != 0.0) {
            normalizedValue = (value - min) / (max - min);
        }

        normalizedValue = Double.valueOf(format.format(normalizedValue));

        return normalizedValue;
    }

}

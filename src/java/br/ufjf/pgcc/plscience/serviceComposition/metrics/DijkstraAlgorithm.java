/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition.metrics;

import br.ufjf.pgcc.plscience.serviceCompositionGraph.CompositionGraph;
import br.ufjf.pgcc.plscience.serviceCompositionGraph.GraphNode;
import br.ufjf.pgcc.plscience.serviceCompositionGraph.SimpleEdge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author phillipe
 * Adapted from: http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 */
public class DijkstraAlgorithm {
    private final List<GraphNode> nodes;
    private final List<SimpleEdge> edges;
    private Set<GraphNode> settledNodes;
    private Set<GraphNode> unSettledNodes;
    private Map<GraphNode, GraphNode> predecessors;
    private Map<GraphNode, Integer> distance;
    
    public DijkstraAlgorithm(CompositionGraph cg){
        nodes = cg.getServicesNodes();
        edges = new ArrayList<>();
        for(int i=0;i<cg.getInteroperatesWithList().size();i++){
            edges.add(cg.getInteroperatesWithList().get(i).getInteroperatesWith());
        }
        for(int i=0;i<cg.getDependsOfList().size();i++){
            edges.add(cg.getDependsOfList().get(i).getDependsOf());
        }
    }
    
    public void execute(GraphNode source){
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<GraphNode, Integer>();
        predecessors = new HashMap<GraphNode, GraphNode>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
                        GraphNode node = getMinimum(unSettledNodes);
                        settledNodes.add(node);
                        unSettledNodes.remove(node);
                        findMinimalDistances(node);
        }
    }
    
    private void findMinimalDistances(GraphNode node) {
                List<GraphNode> adjacentNodes = getNeighbors(node);
                for (GraphNode target : adjacentNodes) {
                        if (getShortestDistance(target) > getShortestDistance(node)
                                        + getDistance(node, target)) {
                                distance.put(target, getShortestDistance(node)
                                                + getDistance(node, target));
                                predecessors.put(target, node);
                                unSettledNodes.add(target);
                        }
                }

        }
    
    private int getDistance(GraphNode node, GraphNode target) {
                for (SimpleEdge edge : edges) {
                        if (edge.getFrom().equals(node)
                                        && edge.getTo().equals(target)) {
                                return 1;
                        }
                }
                return 0;
                //throw new RuntimeException("Should not happen");
        }

        private List<GraphNode> getNeighbors(GraphNode node) {
                List<GraphNode> neighbors = new ArrayList<GraphNode>();
                for (SimpleEdge edge : edges) {
                        if (edge.getFrom().equals(node)
                                && !isSettled(edge.getTo())) {
                                neighbors.add(edge.getTo());
                        }
                }
                return neighbors;
        }

        private GraphNode getMinimum(Set<GraphNode> vertexes) {
                GraphNode minimum = null;
                for (GraphNode vertex : vertexes) {
                        if (minimum == null) {
                                minimum = vertex;
                        } else {
                                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                                        minimum = vertex;
                                }
                        }
                }
                return minimum;
        }

        private boolean isSettled(GraphNode vertex) {
                return settledNodes.contains(vertex);
        }

        private int getShortestDistance(GraphNode destination) {
                Integer d = distance.get(destination);
                if (d == null) {
                        return Integer.MAX_VALUE;
                } else {
                        return d;
                }
        }

        /*
         * This method returns the path from the source to the selected target and
         * NULL if no path exists
         */
        public LinkedList<GraphNode> getPath(GraphNode target) {
                LinkedList<GraphNode> path = new LinkedList<GraphNode>();
                GraphNode step = target;
                // check if a path exists
                if (predecessors.get(step) == null) {
                        return null;
                }
                path.add(step);
                while (predecessors.get(step) != null) {
                        step = predecessors.get(step);
                        path.add(step);
                }
                // Put it into the correct order
                Collections.reverse(path);
                return path;
        }


}

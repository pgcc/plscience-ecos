/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceCompositionGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phillipe
 */
public class CompositionGraph implements Cloneable{
    private List<GraphNode> servicesNodes;
    private List<InteroperatesWithEdge> interoperatesWithList;
    private List<DependsOfEdge> dependsOfList;

    @Override
    public CompositionGraph clone() throws CloneNotSupportedException {
        return (CompositionGraph) super.clone();
    }
    
    /**
     * it calculates the shortest distance for all nodes
     * @param graph
     * @param sourceNode 
     * @return  
     */
    public Map<GraphNode, Integer> calculateShortestDistanceGraph(CompositionGraph graph,GraphNode sourceNode){
        
        Map<GraphNode, Integer> distanceToSource = new HashMap<>();
        List<GraphNode> unVisitedNodes = new ArrayList<>();
        
        //unvisitedNodes initially contains all nodes
        for(GraphNode node:graph.servicesNodes){
            node.setDistanceFromSource(Integer.MAX_VALUE);
            unVisitedNodes.add(node);
        }

        //initially all vertices have infinite distance (constructor)
        
        //setting source distance as 0
        sourceNode.setDistanceFromSource(0);
        
        distanceToSource.put(sourceNode,0);
        
        //unVisitedNodes = copy.getServicesNodes();
        unVisitedNodes.remove(sourceNode);

        List<GraphNode> neighborsSource = sourceNode.getNeighbors();
        
        for(GraphNode neighb:neighborsSource){
            neighb.setDistanceFromSource(1);
            distanceToSource.put(neighb,1);
        }
        
        //while	the arrayList is not empty
        while(!unVisitedNodes.isEmpty()){
            GraphNode u = minDistance(unVisitedNodes);
            unVisitedNodes.remove(u);
            
            for(GraphNode v:u.getNeighbors()){
                if(v.getDistanceFromSource() > u.getDistanceFromSource()){
                    v.setDistanceFromSource(u.getDistanceFromSource()+1);
                    distanceToSource.put(v, u.getDistanceFromSource()+1);
                }
            }
        }        
        
        return distanceToSource;
    }
    
    public Integer betweness (CompositionGraph graph,GraphNode source){
        Integer numShortestPaths = 0;
        Integer numShortestPathsSource = 1;
        List<GraphNode> nodesList1 = new ArrayList<>();
        List<GraphNode> nodesList2 = new ArrayList<>();
        
        //fill both lists
        for(GraphNode node:graph.servicesNodes){
            if(!source.getServiceName().equals(node.getServiceName())){
                nodesList1.add(node);
                nodesList2.add(node);
            }
        }
        
        //get shortest path
        
        for(GraphNode n1:nodesList1){
            for(GraphNode n2:nodesList2){
                if(!n1.getServiceName().equals(n2.getServiceName())){
                    numShortestPaths += 0;
                    numShortestPathsSource += 1;                    
                }            
            }
        }
        
        return 0;
    }
    
    /**
     * select the element of the list with the min distance to the source node
     * @param nodes
     * @return 
     */
    public GraphNode minDistance(List<GraphNode> nodes){
        if(nodes != null){
            GraphNode menor;
                menor = nodes.get(0);
            for(GraphNode n:nodes){
                if(n.getDistanceFromSource() < menor.getDistanceFromSource()){
                    menor = n;
                }
            }
            return menor;
        }
        System.out.println("nodes list is null in minDistance method - CompositionGraph Class");
        return null;        
    }
    
    /**
     * @return the servicesNodes
     */
    public List<GraphNode> getServicesNodes() {
        return servicesNodes;
    }

    /**
     * @param servicesNodes the servicesNodes to set
     */
    public void setServicesNodes(List<GraphNode> servicesNodes) {
        this.servicesNodes = servicesNodes;
    }

    /**
     * @return the interoperatesWithList
     */
    public List<InteroperatesWithEdge> getInteroperatesWithList() {
        return interoperatesWithList;
    }

    /**
     * @param interoperatesWithList the interoperatesWithList to set
     */
    public void setInteroperatesWithList(List<InteroperatesWithEdge> interoperatesWithList) {
        this.interoperatesWithList = interoperatesWithList;
    }

    /**
     * @return the dependsOfList
     */
    public List<DependsOfEdge> getDependsOfList() {
        return dependsOfList;
    }

    /**
     * @param dependsOfList the dependsOfList to set
     */
    public void setDependsOfList(List<DependsOfEdge> dependsOfList) {
        this.dependsOfList = dependsOfList;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceCompositionGraph;

import br.ufjf.pgcc.plscience.serviceComposition.ServiceFromVR;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phillipe
 */
public class GraphNode {

    private String serviceName;
    private String id;
    private String label;
    private String size;
    private String color;
    private String value;
    private String repositoryName;
    private String owner;
    private String type;
    private Integer fanIn;
    private Integer fanOut;
    private Double closenessValue;
    private Double betweennessValue;
    private List<ServiceFromVR> interoperatesWithNodeList;
    private List<ServiceFromVR> dependsOfNodeList;
    private List<ServiceFromVR> composedByNodeList;
    private int distanceFromSource;
    private List<GraphNode> neighbors;

    public GraphNode() {
        size = "20";
        color = "#000";
        value = "atomic";
        fanIn = 0;
        fanOut = 0;
        distanceFromSource = Integer.MAX_VALUE;
        neighbors = new ArrayList<>();
    }

    /**
     * @return the repositoryName
     */
    public String getRepositoryName() {
        return repositoryName;
    }

    /**
     * @param repositoryName the repositoryName to set
     */
    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the interoperatesWithNodeList
     */
    public List<ServiceFromVR> getInteroperatesWithNodeList() {
        return interoperatesWithNodeList;
    }

    /**
     * @param interoperatesWithNodeList the interoperatesWithNodeList to set
     */
    public void setInteroperatesWithNodeList(List<ServiceFromVR> interoperatesWithNodeList) {
        this.interoperatesWithNodeList = interoperatesWithNodeList;
    }

    /**
     * @return the composedByNodeList
     */
    public List<ServiceFromVR> getComposedByNodeList() {
        return composedByNodeList;
    }

    /**
     * @param composedByNodeList the composedByNodeList to set
     */
    public void setComposedByNodeList(List<ServiceFromVR> composedByNodeList) {
        this.composedByNodeList = composedByNodeList;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    public String getColor(String repositoryName, String type) {
        if (repositoryName.toLowerCase().contains("catalog")) {
            color = "#000";
        } else if (repositoryName.toLowerCase().contains("seco")) {
            color = "#0F0";
        }
        if (!type.contains("atomic")) {
            color = "#F00";
        }
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the dependsOfNodeList
     */
    public List<ServiceFromVR> getDependsOfNodeList() {
        return dependsOfNodeList;
    }

    /**
     * @param dependsOfNodeList the dependsOfNodeList to set
     */
    public void setDependsOfNodeList(List<ServiceFromVR> dependsOfNodeList) {
        this.dependsOfNodeList = dependsOfNodeList;
    }

    /**
     * @return the fanIn
     */
    public Integer getFanIn() {
        return fanIn;
    }

    /**
     * @param fanIn the fanIn to set
     */
    public void setFanIn(Integer fanIn) {
        this.fanIn = fanIn;
    }

    /**
     * @return the fanOut
     */
    public Integer getFanOut() {
        return fanOut;
    }

    /**
     * @param fanOut the fanOut to set
     */
    public void setFanOut(Integer fanOut) {
        this.fanOut = fanOut;
    }

    /**
     * @return the distanceFromSource
     */
    public int getDistanceFromSource() {
        return distanceFromSource;
    }

    /**
     * @param distanceFromSource the distanceFromSource to set
     */
    public void setDistanceFromSource(int distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    /**
     * @return the neighbors
     */
    public List<GraphNode> getNeighbors() {
        return neighbors;
    }

    /**
     * @param neighbors the neighbors to set
     */
    public void setNeighbors(List<GraphNode> neighbors) {
        this.neighbors = neighbors;
    }

    public void printAllNodeNeighbors(CompositionGraph graph) {
        System.out.println("Printing neighbors:");
        for (GraphNode node : graph.getServicesNodes()) {
            System.out.println("Service Name: " + node.getServiceName() + "\n");
            System.out.println("Neighbors list: ");
            for (GraphNode node1 : node.getNeighbors()) {
                System.out.println("----------------");
                System.out.println("Name: " + node1.getServiceName());
                System.out.println("Distance from source: " + node1.getDistanceFromSource());
                System.out.println("----------------");
            }
        }
    }

    public Double calculateClosenessValue(CompositionGraph graph, Map<GraphNode, Integer> distances) {

        Integer numberOfNodes = 0;
        Integer sumOfDistances = 0;
        Double closeness = 0.0;
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        
        //It avoids mistakes for those who have the default language like Portuguese or French
        symbols.setDecimalSeparator('.');
        
        DecimalFormat format = new DecimalFormat("#.##",symbols);  

        if (graph.getServicesNodes() == null || graph.getServicesNodes().isEmpty()) {
            return closeness;
        }

        numberOfNodes = graph.getServicesNodes().size();

        for (GraphNode node : graph.getServicesNodes()) {
            Integer distance = distances.get(node);
            sumOfDistances += distance;
            /*The source node does not influence 
            the calculation because his value is equal to 0*/
        }

        if (numberOfNodes > 0.0) {
            Double sumOfDistancesFinal = (double) sumOfDistances;
            Double numberOfNodesFinal = (double) (numberOfNodes - 1);
            closeness = sumOfDistancesFinal/numberOfNodesFinal;
        }
        
        closeness = Double.valueOf(format.format(closeness));
        return closeness;

    }

    /**
     * @return the closenessValue
     */
    public Double getClosenessValue() {
        return closenessValue;
    }

    /**
     * @param closenessValue the closenessValue to set
     */
    public void setClosenessValue(Double closenessValue) {
        this.closenessValue = closenessValue;
    }

    /**
     * @return the betweennessValue
     */
    public Double getBetweennessValue() {
        return betweennessValue;
    }

    /**
     * @param betweennessValue the betweennessValue to set
     */
    public void setBetweennessValue(Double betweennessValue) {
        this.betweennessValue = betweennessValue;
    }
}

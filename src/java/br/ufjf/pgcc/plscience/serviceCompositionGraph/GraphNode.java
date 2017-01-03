/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceCompositionGraph;

import br.ufjf.pgcc.plscience.serviceComposition.ServiceFromVR;
import java.util.List;

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
    private List<ServiceFromVR> interoperatesWithNodeList;
    private List<ServiceFromVR> composedByNodeList;

    public GraphNode(){
        size = "20";
        color = "#000";
        value = "atomic";
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
    
    public String getColor(String repositoryName,String type) {
        if(repositoryName.toLowerCase().contains("catalog")){
            color = "#000";
        }else if(repositoryName.toLowerCase().contains("seco")){
            color = "#0F0";
        }
        if(!type.contains("atomic")){
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
}

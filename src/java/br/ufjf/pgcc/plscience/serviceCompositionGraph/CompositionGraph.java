/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceCompositionGraph;

import java.util.List;

/**
 *
 * @author phillipe
 */
public class CompositionGraph {
    private List<GraphNode> servicesNodes;
    private List<InteroperatesWithEdge> interoperatesWithList;
    private List<ComposedByEdge> composedByList;

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
     * @return the composedByList
     */
    public List<ComposedByEdge> getComposedByList() {
        return composedByList;
    }

    /**
     * @param composedByList the composedByList to set
     */
    public void setComposedByList(List<ComposedByEdge> composedByList) {
        this.composedByList = composedByList;
    }
}

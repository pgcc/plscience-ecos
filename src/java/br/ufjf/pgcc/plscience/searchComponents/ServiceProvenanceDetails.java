/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * This class is used to present the provenance details of a service
 * or workflow previously used in a experiment
 * @author phillipe
 */

@ManagedBean(name = "serpd")
@ViewScoped
public class ServiceProvenanceDetails {
    private List<String> used;
    private List<String> wasAssociatedWith;
    private List<String> wasInformedBy;
    private List<String> wasEndedBy;
    private List<String> actedBehalfOf;

    /**
     * @return the used
     */
    public List<String> getUsed() {
        return used;
    }

    /**
     * @param used the used to set
     */
    public void setUsed(List<String> used) {
        this.used = used;
    }

    /**
     * @return the wasAssociatedWith
     */
    public List<String> getWasAssociatedWith() {
        return wasAssociatedWith;
    }

    /**
     * @param wasAssociatedWith the wasAssociatedWith to set
     */
    public void setWasAssociatedWith(List<String> wasAssociatedWith) {
        this.wasAssociatedWith = wasAssociatedWith;
    }

    /**
     * @return the wasInformedBy
     */
    public List<String> getWasInformedBy() {
        return wasInformedBy;
    }

    /**
     * @param wasInformedBy the wasInformedBy to set
     */
    public void setWasInformedBy(List<String> wasInformedBy) {
        this.wasInformedBy = wasInformedBy;
    }

    /**
     * @return the wasEndedBy
     */
    public List<String> getWasEndedBy() {
        return wasEndedBy;
    }

    /**
     * @param wasEndedBy the wasEndedBy to set
     */
    public void setWasEndedBy(List<String> wasEndedBy) {
        this.wasEndedBy = wasEndedBy;
    }

    /**
     * @return the actedBehalfOf
     */
    public List<String> getActedBehalfOf() {
        return actedBehalfOf;
    }

    /**
     * @param actedBehalfOf the actedBehalfOf to set
     */
    public void setActedBehalfOf(List<String> actedBehalfOf) {
        this.actedBehalfOf = actedBehalfOf;
    }
    
}

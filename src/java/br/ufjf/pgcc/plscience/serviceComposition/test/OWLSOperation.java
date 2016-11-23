/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition.test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phillipe
 */
public class OWLSOperation {
    
    private String operationName;
    private String operationRealName;
    private String operationDomainTerm;
    private String serviceURL;
    private List<OWLSParam> inputs;
    private List<OWLSParam> outputs;
    private String compatibility;
    private Restriction restriction; 
    private String serviceDescription;

    public OWLSOperation(){
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }
    
    /**
     * @return the operationName
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * @param operationName the operationName to set
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * @return the operationDomainTerm
     */
    public String getOperationDomainTerm() {
        return operationDomainTerm;
    }

    /**
     * @param operationDomainTerm the operationDomainTerm to set
     */
    public void setOperationDomainTerm(String operationDomainTerm) {
        this.operationDomainTerm = operationDomainTerm;
    }

    /**
     * @return the inputs
     */
    public List<OWLSParam> getInputs() {
        return inputs;
    }

    /**
     * @param inputs the inputs to set
     */
    public void setInputs(List<OWLSParam> inputs) {
        this.inputs = inputs;
    }

    /**
     * @return the outputs
     */
    public List<OWLSParam> getOutputs() {
        return outputs;
    }

    /**
     * @param outputs the outputs to set
     */
    public void setOutputs(List<OWLSParam> outputs) {
        this.outputs = outputs;
    }

    /**
     * @return the serviceURL
     */
    public String getServiceURL() {
        return serviceURL;
    }

    /**
     * @param serviceURL the serviceURL to set
     */
    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    /**
     * @return the compatibility
     */
    public String getCompatibility() {
        return compatibility;
    }

    /**
     * @param compatibility the compatibility to set
     */
    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }    
    
    @Override
    public String toString(){
        return this.getOperationName();
    }

    /**
     * @return the operationRealName
     */
    public String getOperationRealName() {
        return operationRealName;
    }

    /**
     * @param operationRealName the operationRealName to set
     */
    public void setOperationRealName(String operationRealName) {
        this.operationRealName = operationRealName;
    }

    /**
     * @return the restriction
     */
    public Restriction getRestriction() {
        return restriction;
    }

    /**
     * @param restriction the restriction to set
     */
    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }

    /**
     * @return the serviceDescription
     */
    public String getServiceDescription() {
        return serviceDescription;
    }

    /**
     * @param serviceDescription the serviceDescription to set
     */
    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}


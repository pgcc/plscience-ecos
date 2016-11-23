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
public class OWLSParam {
    
    private String paramName;
    private String paramDomainTerm;
    private String paramType;
    private List<OWLSOperation> connectorAssociated;
    private List<OWLSParam> paramConnectorAssociated;
    private List<OWLSParam> paramConnected;
    private String manualContent = "";
    private String parentName = "";
    private String valueReturned = "";

    public OWLSParam(){
        this.paramConnected = new ArrayList<>();
        this.connectorAssociated = new ArrayList<>();
        this.paramConnectorAssociated = new ArrayList<>();
    }
    
    /**
     * @return the paramName
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * @param paramName the paramName to set
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * @return the paramDomainTerm
     */
    public String getParamDomainTerm() {
        return paramDomainTerm;
    }

    /**
     * @param paramDomainTerm the paramDomainTerm to set
     */
    public void setParamDomainTerm(String paramDomainTerm) {
        this.paramDomainTerm = paramDomainTerm;
    }

    /**
     * @return the paramType
     */
    public String getParamType() {
        return paramType;
    }

    /**
     * @param paramType the paramType to set
     */
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    /**
     * @return the connectorAssociated
     */
    public List<OWLSOperation> getConnectorAssociated() {
        return connectorAssociated;
    }

    /**
     * @param connectorAssociated the connectorAssociated to set
     */
    public void setConnectorAssociated(List<OWLSOperation> connectorAssociated) {
        this.connectorAssociated = connectorAssociated;
    }

    /**
     * @return the paramConnectorAssociated
     */
    public List<OWLSParam> getParamConnectorAssociated() {
        return paramConnectorAssociated;
    }

    /**
     * @param paramConnectorAssociated the paramConnectorAssociated to set
     */
    public void setParamConnectorAssociated(List<OWLSParam> paramConnectorAssociated) {
        this.paramConnectorAssociated = paramConnectorAssociated;
    }

    /**
     * @return the paramConnected
     */
    public List<OWLSParam> getParamConnected() {
        return paramConnected;
    }

    /**
     * @param paramConnected the paramConnected to set
     */
    public void setParamConnected(List<OWLSParam> paramConnected) {
        this.paramConnected = paramConnected;
    }
    
    @Override
    public String toString() {
        if(paramName.contains("[Manual Value]")){
            return "[Manual Value]";
        }
        
        return this.paramName + " - " + this.parentName;
    }    

    /**
     * @return the manualContent
     */
    public String getManualContent() {
        return manualContent;
    }

    /**
     * @param manualContent the manualContent to set
     */
    public void setManualContent(String manualContent) {
        this.manualContent = manualContent;
    }

    /**
     * @return the parentName
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * @param parentName the parentName to set
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * @return the valueReturned
     */
    public String getValueReturned() {
        return valueReturned;
    }

    /**
     * @param valueReturned the valueReturned to set
     */
    public void setValueReturned(String valueReturned) {
        this.valueReturned = valueReturned;
    }
}


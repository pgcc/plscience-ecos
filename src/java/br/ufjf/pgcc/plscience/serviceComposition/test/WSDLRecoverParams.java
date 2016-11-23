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
public class WSDLRecoverParams{
    
    private String name;
    private String nameSpaceURI;
    private String localPart;
    private List<WSDLRecoverParams> subParams;
    private boolean complexType;
    private boolean mandatory;
    private String parameterTypeXSD;
    
    public WSDLRecoverParams(){
        this.subParams = new ArrayList<>();
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the nameSpaceURI
     */
    public String getNameSpaceURI() {
        return nameSpaceURI;
    }

    /**
     * @param nameSpaceURI the nameSpaceURI to set
     */
    public void setNameSpaceURI(String nameSpaceURI) {
        this.nameSpaceURI = nameSpaceURI;
    }

    /**
     * @return the localPart
     */
    public String getLocalPart() {
        return localPart;
    }

    /**
     * @param localPart the localPart to set
     */
    public void setLocalPart(String localPart) {
        this.localPart = localPart;
    }

    /**
     * @return the subParams
     */
    public List<WSDLRecoverParams> getSubParams() {
        return subParams;
    }

    /**
     * @param subParams the subParams to set
     */
    public void setSubParams(List<WSDLRecoverParams> subParams) {
        this.subParams = subParams;
    }

    /**
     * @return the complexType
     */
    public boolean isComplexType() {
        return complexType;
    }

    /**
     * @param complexType the complexType to set
     */
    public void setComplexType(boolean complexType) {
        this.complexType = complexType;
    }

    /**
     * @return the mandatory
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * @param mandatory the mandatory to set
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * @return the parameterTypeXSD
     */
    public String getParameterTypeXSD() {
        return parameterTypeXSD;
    }

    /**
     * @param parameterTypeXSD the parameterTypeXSD to set
     */
    public void setParameterTypeXSD(String parameterTypeXSD) {
        this.parameterTypeXSD = parameterTypeXSD;
    }
    
}


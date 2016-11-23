/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author phillipe
 */

@ManagedBean()
@ViewScoped
public class ServiceRequest {
    private List<String> inputParametersName;
    private List<String> inputParametersType;
    private List<String> outputParametersName;
    private List<String> outputParametersType;
    private List<String> keywords;
    /**
     * @return the inputParametersName
     */
    public List<String> getInputParametersName() {
        return inputParametersName;
    }

    /**
     * @param inputParametersName the inputParametersName to set
     */
    public void setInputParametersName(List<String> inputParametersName) {
        this.inputParametersName = inputParametersName;
    }

    /**
     * @return the inputParametersType
     */
    public List<String> getInputParametersType() {
        return inputParametersType;
    }

    /**
     * @param inputParametersType the inputParametersType to set
     */
    public void setInputParametersType(List<String> inputParametersType) {
        this.inputParametersType = inputParametersType;
    }

    /**
     * @return the outputParametersName
     */
    public List<String> getOutputParametersName() {
        return outputParametersName;
    }

    /**
     * @param outputParametersName the outputParametersName to set
     */
    public void setOutputParametersName(List<String> outputParametersName) {
        this.outputParametersName = outputParametersName;
    }

    /**
     * @return the outputParametersType
     */
    public List<String> getOutputParametersType() {
        return outputParametersType;
    }

    /**
     * @param outputParametersType the outputParametersType to set
     */
    public void setOutputParametersType(List<String> outputParametersType) {
        this.outputParametersType = outputParametersType;
    }

    /**
     * @return the keywords
     */
    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    
    
}

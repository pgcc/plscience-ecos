/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

import br.ufjf.pgcc.plscience.searchComponents.ResultsPatternFormat;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author phillipe
 */

public class ServiceRequest implements Serializable{
    private List<String> inputParametersName;
    private List<String> inputParametersType;
    private List<String> outputParametersName;
    private List<String> outputParametersType;
    private List<String> keywords;
    private ResultsPatternFormat patternRequest;
    
    /**
     
     * @throws java.io.IOException
     */
    public ServiceRequest() throws IOException{
        patternRequest = new ResultsPatternFormat();
    }
    
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

    /**
     * @return the patternRequest
     */
    public ResultsPatternFormat getPatternRequest() {
        return patternRequest;
    }

    /**
     * @param patternRequest the patternRequest to set
     */
    public void setPatternRequest(ResultsPatternFormat patternRequest) {
        this.patternRequest = patternRequest;
    }
    
    
}

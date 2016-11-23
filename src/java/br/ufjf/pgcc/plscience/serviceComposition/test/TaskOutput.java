/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition.test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author phillipe
 */
@ManagedBean()
@ViewScoped
public class TaskOutput{
    private String outputParameter;
    private String outputType;

    /**
     * @return the outputParameter
     */
    public String getOutputParameter() {
        return outputParameter;
    }

    /**
     * @param outputParameter the outputParameter to set
     */
    public void setOutputParameter(String outputParameter) {
        this.outputParameter = outputParameter;
    }

    /**
     * @return the outputType
     */
    public String getOutputType() {
        return outputType;
    }

    /**
     * @param outputType the outputType to set
     */
    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }
    
}

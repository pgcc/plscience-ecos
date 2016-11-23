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
public class TaskInput{
    private String inputParameter;
    private String inputType;

    /**
     * @return the inputParameter
     */
    public String getInputParameter() {
        return inputParameter;
    }

    /**
     * @param inputParameter the inputParameter to set
     */
    public void setInputParameter(String inputParameter) {
        this.inputParameter = inputParameter;
    }

    /**
     * @return the inputType
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * @param inputType the inputType to set
     */
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
}

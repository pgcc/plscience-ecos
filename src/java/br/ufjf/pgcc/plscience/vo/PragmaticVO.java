/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.vo;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Fran
 */
@ManagedBean()
@ViewScoped
public class PragmaticVO implements Serializable{
    private ArrayList<String> hasNonFunctionalRequirement;
    private ContextVO includesContext;
    private HardwareVO includesHardware;
    private String hasNonFunctionalReq;

    public PragmaticVO() {
    }

    public ArrayList<String> getHasNonFunctionalRequirement() {
        return hasNonFunctionalRequirement;
    }

    public void setHasNonFunctionalRequirement(ArrayList<String> hasNonFunctionalRequirement) {
        this.hasNonFunctionalRequirement = hasNonFunctionalRequirement;
    }

    public ContextVO getIncludesContext() {
        return includesContext;
    }

    public void setIncludesContext(ContextVO includesContext) {
        this.includesContext = includesContext;
    }

    public HardwareVO getIncludesHardware() {
        return includesHardware;
    }

    public void setIncludesHardware(HardwareVO includesHardware) {
        this.includesHardware = includesHardware;
    }

    public String getHasNonFunctionalReq() {
        return hasNonFunctionalReq;
    }

    public void setHasNonFunctionalReq(String hasNonFunctionalReq) {
        this.hasNonFunctionalReq = hasNonFunctionalReq;
    }
    
    
}

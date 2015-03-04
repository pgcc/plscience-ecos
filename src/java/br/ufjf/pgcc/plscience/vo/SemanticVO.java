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
public class SemanticVO implements Serializable {
    private String hasSemanticReturn;
    private ArrayList<String> hasFunctionalRequirements;
    private String hasSemanticReception;
    private String hasSemanticRepresentation;
    private String hasFunctionalRequirement;

    public SemanticVO() {
    }

    public String getHasSemanticReturn() {
        return hasSemanticReturn;
    }

    public void setHasSemanticReturn(String hasSemanticReturn) {
        this.hasSemanticReturn = hasSemanticReturn;
    }

    public ArrayList<String> getHasFunctionalRequirements() {
        return hasFunctionalRequirements;
    }

    public void setHasFunctionalRequirements(ArrayList<String> hasFunctionalRequirements) {
        this.hasFunctionalRequirements = hasFunctionalRequirements;
    }

    public String getHasSemanticReception() {
        return hasSemanticReception;
    }

    public void setHasSemanticReception(String hasSemanticReception) {
        this.hasSemanticReception = hasSemanticReception;
    }

    public String getHasSemanticRepresentation() {
        return hasSemanticRepresentation;
    }

    public void setHasSemanticRepresentation(String hasSemanticRepresentation) {
        this.hasSemanticRepresentation = hasSemanticRepresentation;
       
    }

    public String getHasFunctionalRequirement() {
        return hasFunctionalRequirement;
    }

    public void setHasFunctionalRequirement(String hasFunctionalRequirement) {
        this.hasFunctionalRequirement = hasFunctionalRequirement;
        this.hasFunctionalRequirements = new ArrayList();
        this.hasFunctionalRequirements.add(hasFunctionalRequirement);
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.vo;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Fran
 */
@ManagedBean()
@ViewScoped
public class ServiceDescriptionVO implements Serializable {
    private PragmaticVO includesPragmatic;
    private SemanticVO includesSemantic;
    private SyntacticVO includesSyntactic;
    private String name;

    public ServiceDescriptionVO() {
    }

    public PragmaticVO getIncludesPragmatic() {
        return includesPragmatic;
    }

    public void setIncludesPragmatic(PragmaticVO includesPragmatic) {
        this.includesPragmatic = includesPragmatic;
    }

    public SemanticVO getIncludesSemantic() {
        return includesSemantic;
    }

    public void setIncludesSemantic(SemanticVO includesSemantic) {
        this.includesSemantic = includesSemantic;
    }

    public SyntacticVO getIncludesSyntactic() {
        return includesSyntactic;
    }

    public void setIncludesSyntactic(SyntacticVO includesSyntactic) {
        this.includesSyntactic = includesSyntactic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

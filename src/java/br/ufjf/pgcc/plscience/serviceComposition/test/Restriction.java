/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition.test;

/**
 *
 * @author phillipe
 */
public class Restriction {
    private int time;
    private RestrictionType restrictionType;
    private OWLSParam owlsParam;
    private String operator;
    private String restrictionTerm;

    public Restriction(){
        this.time = 0;
        this.restrictionType = null;
        this.owlsParam = null;
    }
    
    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return the restrictionType
     */
    public RestrictionType getRestrictionType() {
        return restrictionType;
    }

    /**
     * @param restrictionType the restrictionType to set
     */
    public void setRestrictionType(RestrictionType restrictionType) {
        this.restrictionType = restrictionType;
    }

    /**
     * @return the owlsParam
     */
    public OWLSParam getOwlsParam() {
        return owlsParam;
    }

    /**
     * @param owlsParam the owlsParam to set
     */
    public void setOwlsParam(OWLSParam owlsParam) {
        this.owlsParam = owlsParam;
    }

    /**
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return the restrictionTerm
     */
    public String getRestrictionTerm() {
        return restrictionTerm;
    }

    /**
     * @param restrictionTerm the restrictionTerm to set
     */
    public void setRestrictionTerm(String restrictionTerm) {
        this.restrictionTerm = restrictionTerm;
    }
}


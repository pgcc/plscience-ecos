/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.vo;

/**
 *
 * @author Fran
 */
public class Syntactic {
    private String hasReturn;
    private String hasAddress;
    //Lista dos tipos de parâmetros separados por ,
    private String hasParameters;

    public Syntactic() {
    }

    public String getHasReturn() {
        return hasReturn;
    }

    public void setHasReturn(String hasReturn) {
        this.hasReturn = hasReturn;
    }

    public String getHasAddress() {
        return hasAddress;
    }

    public void setHasAddress(String hasAddress) {
        this.hasAddress = hasAddress;
    }

    public String getHasParameters() {
        return hasParameters;
    }

    public void setHasParameters(String hasParameters) {
        this.hasParameters = hasParameters;
    }
    
    
}

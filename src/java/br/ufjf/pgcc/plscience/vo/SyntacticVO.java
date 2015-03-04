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
public class SyntacticVO implements Serializable{
    private String hasReturn;
    private String hasAddress;
    //Lista dos tipos de parâmetros separados por ,
    private ArrayList<String> hasParameters;

    public SyntacticVO() {
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

    public ArrayList<String> getHasParameters() {
        return hasParameters;
    }

    public void setHasParameters(ArrayList<String> hasParameters) {
        this.hasParameters = hasParameters;
    }

   
    
    
}

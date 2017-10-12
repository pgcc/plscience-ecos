/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.social.api.model.scholar;

/**
 *
 * @author Jonathan
 */
public class Indice {
    
    private String description;
    private int valueAll;
    private int value2012;

    public Indice(String description, int valueAll, int value2012) {
        this.description = description;
        this.valueAll = valueAll;
        this.value2012 = value2012;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValueAll() {
        return valueAll;
    }

    public void setValueAll(int valueAll) {
        this.valueAll = valueAll;
    }

    public int getValue2012() {
        return value2012;
    }

    public void setValue2012(int value2012) {
        this.value2012 = value2012;
    }

    
}

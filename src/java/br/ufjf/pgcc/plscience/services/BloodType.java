/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.services;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fran
 */
@XmlRootElement
public class BloodType {
    private String type;
    private String rh;

    public BloodType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }
    
    
}

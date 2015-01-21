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
public class BloodTypeProbabilitiesTable {
    String PercentageA;
    String PercentageB;
    String PercentageAB;
    String PercentageO;

    public BloodTypeProbabilitiesTable() {
    }

    public String getPercentageA() {
        return PercentageA;
    }

    public void setPercentageA(String PercentageA) {
        this.PercentageA = PercentageA;
    }

    public String getPercentageB() {
        return PercentageB;
    }

    public void setPercentageB(String PercentageB) {
        this.PercentageB = PercentageB;
    }

    public String getPercentageAB() {
        return PercentageAB;
    }

    public void setPercentageAB(String PercentageAB) {
        this.PercentageAB = PercentageAB;
    }

    public String getPercentageO() {
        return PercentageO;
    }

    public void setPercentageO(String PercentageO) {
        this.PercentageO = PercentageO;
    }
    
    
}

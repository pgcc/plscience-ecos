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
public class HardwareVO {
    private String hasCPU;
    private String hasROM;
    private String hasRAM;
    private String hasOperationalSystem;

    public HardwareVO() {
    }

    public String getHasCPU() {
        return hasCPU;
    }

    public void setHasCPU(String hasCPU) {
        this.hasCPU = hasCPU;
    }

    public String getHasROM() {
        return hasROM;
    }

    public void setHasROM(String hasROM) {
        this.hasROM = hasROM;
    }

    public String getHasRAM() {
        return hasRAM;
    }

    public void setHasRAM(String hasRAM) {
        this.hasRAM = hasRAM;
    }

    public String getHasOperationalSystem() {
        return hasOperationalSystem;
    }

    public void setHasOperationalSystem(String hasOperationalSystem) {
        this.hasOperationalSystem = hasOperationalSystem;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.workflow;

/**
 *
 * @author vitorfs
 */
public enum TavernaServerStatus {
    
    INITIALIZED("Initialized"),
    OPERATING("Operating"),
    STOPPED("Stopped"),
    FINISHED("Finished");
    
    private final String status;

    private TavernaServerStatus(final String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return this.status;
    }
    
}

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
public enum TavernaServerMethods {
    
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");
    
    private final String method;

    private TavernaServerMethods(final String method) {
        this.method = method;
    }
    
    public String getMethod() {
        return this.method;
    }
}

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
public class WSDLRecoverPort {
    
    private String portName;
    private String adress;
    private WSDLRecoverBinding binding;

    /**
     * @return the portName
     */
    public String getPortName() {
        return portName;
    }

    /**
     * @param portName the portName to set
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }

    /**
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * @return the binding
     */
    public WSDLRecoverBinding getBinding() {
        return binding;
    }

    /**
     * @param binding the binding to set
     */
    public void setBinding(WSDLRecoverBinding binding) {
        this.binding = binding;
    }
    
}


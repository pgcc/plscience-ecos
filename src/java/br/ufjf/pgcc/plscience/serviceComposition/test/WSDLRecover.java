/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition.test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phillipe
 */
public class WSDLRecover {
    
    private String targetNameSpace;
    private List<WSDLRecoverService> services;
    private List<WSDLRecoverParams> inputs;
    private List<WSDLRecoverParams> outputs;
    private List<WSDLRecoverPortType> portType;

    public WSDLRecover(){        
        this.services = new ArrayList<>();
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.portType = new ArrayList<>();
    }
    
    /**
     * @return the targetNameSpace
     */
    public String getTargetNameSpace() {
        return targetNameSpace;
    }

    /**
     * @param targetNameSpace the targetNameSpace to set
     */
    public void setTargetNameSpace(String targetNameSpace) {
        this.targetNameSpace = targetNameSpace;
    }

    /**
     * @return the inputs
     */
    public List<WSDLRecoverParams> getInputs() {
        return inputs;
    }

    /**
     * @param inputs the inputs to set
     */
    public void setInputs(List<WSDLRecoverParams> inputs) {
        this.inputs = inputs;
    }

    /**
     * @return the outputs
     */
    public List<WSDLRecoverParams> getOutputs() {
        return outputs;
    }

    /**
     * @param outputs the outputs to set
     */
    public void setOutputs(List<WSDLRecoverParams> outputs) {
        this.outputs = outputs;
    }    

    /**
     * @return the services
     */
    public List<WSDLRecoverService> getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(List<WSDLRecoverService> services) {
        this.services = services;
    }

    /**
     * @return the portType
     */
    public List<WSDLRecoverPortType> getPortType() {
        return portType;
    }

    /**
     * @param portType the portType to set
     */
    public void setPortType(List<WSDLRecoverPortType> portType) {
        this.portType = portType;
    }

}

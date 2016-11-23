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
public class WSDLRecoverService {
    
    private String serviceName;
    private List<WSDLRecoverPort> ports;

    public WSDLRecoverService(){
        this.ports = new ArrayList<>();
    }
    
    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the port
     */
    public List<WSDLRecoverPort> getPorts() {
        return ports;
    }

    /**
     * @param port the port to set
     */
    public void setPorts(List<WSDLRecoverPort> ports) {
        this.ports = ports;
    }
}


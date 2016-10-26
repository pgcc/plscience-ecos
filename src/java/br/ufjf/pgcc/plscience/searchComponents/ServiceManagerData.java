/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author phillipe
 */

@ManagedBean
public class ServiceManagerData implements Serializable{
    private ArrayList<String> operationsName;
    private WSDLInput wsdlInput;
    private WSDLOutput wsdlOutput;
    
    public ServiceManagerData(){
        wsdlInput = new WSDLInput();
        wsdlOutput = new WSDLOutput();
    }

    /**
     * @return the operationsName
     */
    public ArrayList<String> getOperationsName() {
        return operationsName;
    }

    /**
     * @param operationsName the operationsName to set
     */
    public void setOperationsName(ArrayList<String> operationsName) {
        this.operationsName = operationsName;
    }

    /**
     * @return the wsdlInput
     */
    public WSDLInput getWsdlInput() {
        return wsdlInput;
    }

    /**
     * @param wsdlInput the wsdlInput to set
     */
    public void setWsdlInput(WSDLInput wsdlInput) {
        this.wsdlInput = wsdlInput;
    }

    /**
     * @return the wsdlOutput
     */
    public WSDLOutput getWsdlOutput() {
        return wsdlOutput;
    }

    /**
     * @param wsdlOutput the wsdlOutput to set
     */
    public void setWsdlOutput(WSDLOutput wsdlOutput) {
        this.wsdlOutput = wsdlOutput;
    }
    
}

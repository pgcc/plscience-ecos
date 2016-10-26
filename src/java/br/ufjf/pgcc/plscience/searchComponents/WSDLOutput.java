/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author phillipe
 */

@ManagedBean
public class WSDLOutput {
    private ArrayList<String> wsdlParameterOutput;
    private ArrayList<String> wsdlTypeOutput;

    /**
     * @return the wsdlParameterOutput
     */
    public ArrayList<String> getWsdlParameterOutput() {
        return wsdlParameterOutput;
    }

    /**
     * @param wsdlParameterOutput the wsdlParameterOutput to set
     */
    public void setWsdlParameterOutput(ArrayList<String> wsdlParameterOutput) {
        this.wsdlParameterOutput = wsdlParameterOutput;
    }

    /**
     * @return the wsdlTypeOutput
     */
    public ArrayList<String> getWsdlTypeOutput() {
        return wsdlTypeOutput;
    }

    /**
     * @param wsdlTypeOutput the wsdlTypeOutput to set
     */
    public void setWsdlTypeOutput(ArrayList<String> wsdlTypeOutput) {
        this.wsdlTypeOutput = wsdlTypeOutput;
    }
}

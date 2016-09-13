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
public class WSDLInput {
    private ArrayList<String> wsdlParameterInput;
    private ArrayList<String> wsdlTypeInput;

    /**
     * @return the wsdlParameterInput
     */
    public ArrayList<String> getWsdlParameterInput() {
        return wsdlParameterInput;
    }

    /**
     * @param wsdlParameterInput the wsdlParameterInput to set
     */
    public void setWsdlParameterInput(ArrayList<String> wsdlParameterInput) {
        this.wsdlParameterInput = wsdlParameterInput;
    }

    /**
     * @return the wsdlTypeInput
     */
    public ArrayList<String> getWsdlTypeInput() {
        return wsdlTypeInput;
    }

    /**
     * @param wsdlTypeInput the wsdlTypeInput to set
     */
    public void setWsdlTypeInput(ArrayList<String> wsdlTypeInput) {
        this.wsdlTypeInput = wsdlTypeInput;
    }
}

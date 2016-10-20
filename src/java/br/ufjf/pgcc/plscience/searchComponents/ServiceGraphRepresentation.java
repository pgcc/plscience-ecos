/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

/**
 *
 * @author phillipe
 */
public class ServiceGraphRepresentation {
    private WSDLInput wsdlInput;
    private WSDLOutput wsdlOutput;
    
    public ServiceGraphRepresentation(){
        wsdlInput = new WSDLInput();
        wsdlOutput = new WSDLOutput();
    }
}

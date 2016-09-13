/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author phillipe
 */

@ManagedBean()
@ViewScoped
public class ServiceManager implements Serializable{
    private String fileURL;
    private ServiceManagerData serviceInfo;

    public void generatesServiceInformation(){
        String operationsName1 = "operationsName1";
        String operationsName2 = "operationsName2";
        ArrayList<String> teste = new ArrayList<>();
        teste.add(operationsName1);
        teste.add(operationsName2);
        serviceInfo = new ServiceManagerData();
        serviceInfo.setOperationsName(teste);
        String inputParameter1 = "InputParameter1";
        String inputParameter2 = "InputParameter2";
        ArrayList<String> teste2 = new ArrayList<>();
        teste2.add(inputParameter1);
        teste2.add(inputParameter2);
        serviceInfo.getWsdlInput().setWsdlParameterInput(teste2);
        String inputType1 = "String";
        String inputType2 = "String";
        ArrayList<String> teste3 = new ArrayList<>();
        teste3.add(inputType1);
        teste3.add(inputType2);        
        serviceInfo.getWsdlInput().setWsdlTypeInput(teste3);

        String outputParameter1 = "db";
        String outputParameter2 = "name";
        ArrayList<String> teste4 = new ArrayList<>();
        teste4.add(outputParameter1);
        teste4.add(outputParameter2);
        serviceInfo.getWsdlOutput().setWsdlParameterOutput(teste4);
        String outputType1 = "String";
        String outputType2 = "String";
        ArrayList<String> teste5 = new ArrayList<>();
        teste5.add(outputType1);
        teste5.add(outputType2);        
        serviceInfo.getWsdlOutput().setWsdlTypeOutput(teste5);

    }
    
    /**
     * @return the fileURL
     */
    public String getFileURL() {
        return fileURL;
    }

    /**
     * @param fileURL the fileURL to set
     */
    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    /**
     * @return the serviceInfo
     */
    public ServiceManagerData getServiceInfo() {
        return serviceInfo;
    }

    /**
     * @param serviceInfo the serviceInfo to set
     */
    public void setServiceInfo(ServiceManagerData serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.webservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Guilherme
 */
@WebService(serviceName = "WsList")
public class WsList {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "allUsers")
    public String allUsers() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "usersByCompetence")
    public String usersByCompetence() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "usersByRole")
    public String usersByRole() {
        //TODO write your implementation code here:
        return null;
    }
    
    
}

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
 * @author Guilherme Martins
 */
@WebService(serviceName = "WsMessage")
public class WsMessage {

    @WebMethod(operationName = "getMessage")
    public String getMessage() {
        return "Message";
    }
}

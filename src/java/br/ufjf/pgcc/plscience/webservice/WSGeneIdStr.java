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
 * @author phillipe
 */
@WebService(serviceName = "WSGeneIdStr")
public class WSGeneIdStr {

    /**
     * Web service operation
     * @param input
     * @return 
     */
    @WebMethod(operationName = "getGeneId")
    public String getGeneId(@WebParam(name = "input") String input) {
        String result = input;
        if(input == null){
            result = "is null";
        }else{
                input = input.replace("<result xsi:type=\"ns1:geneIDResult\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><GeneID xsi:type=\"xsd:string\">", "");
                String id = "";
                for(int i=0;i<input.length();i++){
                    char a = input.charAt(i);
                    if(a != '<'){
                        id = id+a;
                    }else{                        
                        result = id;
                        return result;
                    }
                }
        }
        return result;
    }
}

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
@WebService(serviceName = "ConvertTemperature")
public class ConvertTemperature {

    /**
     * This is a sample web service operation
     * @param c
     * @return 
     */
    @WebMethod(operationName = "CelsiusToKelvin")
    public String celsiusToKelvin(@WebParam(name = "c") String c) {
        int celsius = Integer.valueOf(c);
        int kelvin = celsius + 273;
        return String.valueOf(kelvin);
    }
    
    @WebMethod(operationName = "kelvinToFahreinheith")
    public String kelvinToFahreinheith(@WebParam(name = "k") String k) {
        int kelvin = Integer.valueOf(k);
        double farenheidt = (kelvin - 273)*1.8d + 32;       
        return String.valueOf(String.format("%.1f",farenheidt));
    }
    
    @WebMethod(operationName = "celsiusToFahreinheith")
    public String celsiusToFahreinheith(@WebParam(name = "celsius") String celsius) {
        String kelvin = celsiusToKelvin(celsius);
        return kelvinToFahreinheith(kelvin);
    }
}

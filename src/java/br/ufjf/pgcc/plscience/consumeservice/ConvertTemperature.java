
package br.ufjf.pgcc.plscience.consumeservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ConvertTemperature", targetNamespace = "http://webservice.plscience.pgcc.ufjf.br/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ConvertTemperature {


    /**
     * 
     * @param c
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "CelsiusToKelvin")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "CelsiusToKelvin", targetNamespace = "http://webservice.plscience.pgcc.ufjf.br/", className = "client.CelsiusToKelvin")
    @ResponseWrapper(localName = "CelsiusToKelvinResponse", targetNamespace = "http://webservice.plscience.pgcc.ufjf.br/", className = "client.CelsiusToKelvinResponse")
    @Action(input = "http://webservice.plscience.pgcc.ufjf.br/ConvertTemperature/CelsiusToKelvinRequest", output = "http://webservice.plscience.pgcc.ufjf.br/ConvertTemperature/CelsiusToKelvinResponse")
    public String celsiusToKelvin(
        @WebParam(name = "c", targetNamespace = "")
        String c);

    /**
     * 
     * @param k
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "kelvinToFahreinheith", targetNamespace = "http://webservice.plscience.pgcc.ufjf.br/", className = "client.KelvinToFahreinheith")
    @ResponseWrapper(localName = "kelvinToFahreinheithResponse", targetNamespace = "http://webservice.plscience.pgcc.ufjf.br/", className = "client.KelvinToFahreinheithResponse")
    @Action(input = "http://webservice.plscience.pgcc.ufjf.br/ConvertTemperature/kelvinToFahreinheithRequest", output = "http://webservice.plscience.pgcc.ufjf.br/ConvertTemperature/kelvinToFahreinheithResponse")
    public String kelvinToFahreinheith(
        @WebParam(name = "k", targetNamespace = "")
        String k);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.webservice;

import br.ufjf.pgcc.plscience.integration.InteroperabilityStructXML;
import br.ufjf.pgcc.plscience.integration.InteroperabilityStructXMLDAO;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Guilherme Martins
 */
@WebService(serviceName = "WsAlignmentServices")
public class WsAlignmentServices {

    @WebMethod(operationName = "getAllAlignments")
    public List<InteroperabilityStructXML> getAllAlignments() {
        return new InteroperabilityStructXMLDAO().getAll();
    }
    
    @WebMethod(operationName = "getAlignmentByID")
    public InteroperabilityStructXML getAlignmentByID(@WebParam(name = "alignmentID") int alignmentID) {
        return new InteroperabilityStructXMLDAO().getInteroperabilityStructXMLById((long) alignmentID);
    }
}

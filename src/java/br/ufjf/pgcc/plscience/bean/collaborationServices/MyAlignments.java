/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CollaborationServiceDAO;
import br.ufjf.pgcc.plscience.integration.InteroperabilityStructXML;
import br.ufjf.pgcc.plscience.integration.InteroperabilityStructXMLDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "myAlignments")
@ViewScoped
public class MyAlignments implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public List<InteroperabilityStructXML> getInteroperabilityStructXMLs() {  
        return new InteroperabilityStructXMLDAO().getAll();
    }
}

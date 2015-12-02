/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.integration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "conceptXMLBean")
@ViewScoped
public class ConceptXMLBean implements Serializable {
    
    private ConceptXML conceptXML;
    private List conceptXMLs;
    
    public ConceptXMLBean(){
        conceptXMLs = new ArrayList<ConceptXML>();
        conceptXML = new ConceptXML();
    }
    
    public ConceptXMLBean(Long ID){
        conceptXMLs = new ConceptXMLDAO().getConceptXMLByIdStructXML(ID);
        conceptXML = new ConceptXML();
    }

    public void update(ActionEvent actionEvent){
        new ConceptXMLDAO().update(conceptXML);
        conceptXMLs = new ConceptXMLDAO().getConceptXMLByIdStructXML(conceptXML.getIdStructXml().getIdStructXml());
        conceptXML = new ConceptXML();
    }
        
    /**
     * @return the conceptXML
     */
    public ConceptXML getConceptXML() {
        return conceptXML;
    }

    /**
     * @param conceptXML the conceptXML to set
     */
    public void setConceptXML(ConceptXML conceptXML) {
        this.conceptXML = conceptXML;
    }

    /**
     * @return the conceptXMLs
     */
    public List getConceptXMLs() {
        return conceptXMLs;
    }

    /**
     * @param conceptXMLs the conceptXMLs to set
     */
    public void setConceptXMLs(List conceptXMLs) {
        this.conceptXMLs = conceptXMLs;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.model.Ontology;
import br.ufjf.pgcc.plscience.dao.OntologyDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author phillipe
 */

@ManagedBean(name = "ontologyBean")
@ViewScoped
public class OntologyBean implements Serializable{
    private Ontology ontology;
    private List ontologyList = new ArrayList();
    
    public OntologyBean() {
        ontologyList = new OntologyDAO().getAll();
        ontology = new Ontology();
    }    

    /**
     * @return the ontology
     */
    public Ontology getOntology() {
        return ontology;
    }

    /**
     * @param ontology the ontology to set
     */
    public void setOntology(Ontology ontology) {
        this.ontology = ontology;
    }

    /**
     * @return the ontologyList
     */
    public List getOntologyList() {
        return ontologyList;
    }

    /**
     * @param ontologyList the ontologyList to set
     */
    public void setOntologyList(List ontologyList) {
        this.ontologyList = ontologyList;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.integration;

import br.ufjf.pgcc.plscience.model.CollaborationService;
import br.ufjf.pgcc.plscience.model.Competence;
import br.ufjf.pgcc.plscience.model.Roler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme Martins
 */
public class InteroperabilityServices implements Serializable {
    
    //Serviços de Colaboração
    private CollaborationService collabService1;
    private CollaborationService collabService2;
    
    //Conceitos dos Serviços de Colaboração
    List<String> sameConcept; 
    List<String> differentConcept;
    private Double conceptRatio;
    
    //Arquixo com os dados da similaridade
    private InteroperabilityStructXML interoperabilityStructXML;
    
    public InteroperabilityServices() {
        //Serviços de Colaboração
        collabService1 = new CollaborationService();
        collabService2 = new CollaborationService();
          
        //Conceitos dos Serviços de Colaboração
        sameConcept = new ArrayList<String>();
        differentConcept = new ArrayList<String>();
        
        //Arquixo com os dados da similaridade
        interoperabilityStructXML = new InteroperabilityStructXML();
    }

    public void cleanInteroperabilityServices() {
        //Serviços de Colaboração
        collabService1 = new CollaborationService();
        collabService2 = new CollaborationService();
        
        //Conceitos dos Serviços de Colaboração
        sameConcept = new ArrayList<String>();
        differentConcept = new ArrayList<String>();
        
        //Arquixo com os dados da similaridade
        interoperabilityStructXML = new InteroperabilityStructXML();
    }
  
    /**
     * @return the collabService1
     */
    public CollaborationService getCollabService1() {
        return collabService1;
    }

    /**
     * @param collabService1 the collabService1 to set
     */
    public void setCollabService1(CollaborationService collabService1) {
        this.collabService1 = collabService1;
    }

    /**
     * @return the collabService2
     */
    public CollaborationService getCollabService2() {
        return collabService2;
    }

    /**
     * @param collabService2 the collabService2 to set
     */
    public void setCollabService2(CollaborationService collabService2) {
        this.collabService2 = collabService2;
    }

    /**
     * @return the sameConcept
     */
    public List<String> getSameConcept() {
        return sameConcept;
    }

    /**
     * @param sameConcept the sameConcept to set
     */
    public void setSameConcept(List<String> sameConcept) {
        this.sameConcept = sameConcept;
    }

    /**
     * @return the differentConcept
     */
    public List<String> getDifferentConcept() {
        return differentConcept;
    }

    /**
     * @param differentConcept the differentConcept to set
     */
    public void setDifferentConcept(List<String> differentConcept) {
        this.differentConcept = differentConcept;
    }
    
    /**
     * @return the conceptRatio
     */
    public Double getConceptRatio() {
        return conceptRatio;
    }

    /**
     * @param conceptRatio the conceptRatio to set
     */
    public void setConceptRatio(Double conceptRatio) {
        this.conceptRatio = conceptRatio;
    }
    
    /**
     * @return the interoperabilityStructXML
     */
    public InteroperabilityStructXML getInteroperabilityStructXML() {
        return interoperabilityStructXML;
    }

    /**
     * @param interoperabilityStructXML the interoperabilityStructXML to set
     */
    public void setInteroperabilityStructXML(InteroperabilityStructXML interoperabilityStructXML) {
        this.interoperabilityStructXML = interoperabilityStructXML;
    }

}

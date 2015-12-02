/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.integration;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "collabScientificExperimentationBean")
@ViewScoped
public class CollabScientificExperimentationBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
      
    /**
     * Função para testar se esta classe chama a "CollabScientificExperimentationDAO" de forma adequada.
     * @return Uma String com um texto pronto criado na "CollabScientificExperimentationBean".
     */
    public String testeBean(){
        CollabScientificExperimentationDAO collabOnt = new CollabScientificExperimentationDAO();
        return collabOnt.testeDAO();
    }
    
    public void load() throws IOException {
        try {
            CollabScientificExperimentationDAO collabOnt = new CollabScientificExperimentationDAO();
            collabOnt.loadCollabOntologyDAO();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }
    
    public String getSubclasses() throws IOException {
        
        String listSubClasses = "Error";
        
        try {
            CollabScientificExperimentationDAO collabOnt = new CollabScientificExperimentationDAO();            
            listSubClasses = collabOnt.getSubClasses("Communication");
            listSubClasses = listSubClasses.replaceAll("\n", "<BR/>");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        return listSubClasses;
    }
    
    public String getSubclassesSparQL() throws IOException {
        
        String listSubClasses = "Error";
        
        try {
            CollabScientificExperimentationDAO collabOnt = new CollabScientificExperimentationDAO();            
            listSubClasses = collabOnt.getSubClassesForSPARQL("Communication");
            //listSubClasses = listSubClasses.replaceAll("\n", "<BR/>");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        return listSubClasses;
    }
}

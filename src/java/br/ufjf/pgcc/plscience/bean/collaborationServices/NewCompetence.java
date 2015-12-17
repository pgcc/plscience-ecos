/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CompetenceDAO;
import br.ufjf.pgcc.plscience.model.Competence;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean()
@ViewScoped
public class NewCompetence implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Competence competence;

    public NewCompetence() {
        this.competence = new Competence();
    }
    
    public void save() {    
        try { 
            getCompetence().setId(null);
            new CompetenceDAO().save(getCompetence()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Competence saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }
    
    /**
     * @return the competence
     */
    public Competence getCompetence() {
        return competence;
    }

    /**
     * @param competence the competence to set
     */
    public void setCompetence(Competence competence) {
        this.competence = competence;
    }
    
}

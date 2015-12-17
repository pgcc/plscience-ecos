/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CollaborativeServiceTypeDAO;
import br.ufjf.pgcc.plscience.model.CollaborativeServiceType;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import org.hibernate.HibernateException;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean()
@ViewScoped
public class NewCollaborativeServiceType implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private CollaborativeServiceType collaborativeServiceType;

    
    public NewCollaborativeServiceType() {
        this.collaborativeServiceType = new CollaborativeServiceType();
    }
    
    /**
     * Salva um 'Tipo de Serviço Colaborativo
     */
    public void save() {    
        try { 
            getCollaborativeServiceType().setId(null);
            new CollaborativeServiceTypeDAO().save(getCollaborativeServiceType()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Collaboration Service Type saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }
    
    /**
     * @return the collaborativeServiceType
     */
    public CollaborativeServiceType getCollaborativeServiceType() {
        return collaborativeServiceType;
    }
    
    /**
     * @param collaborativeServiceType the collaborativeServiceType to set
     */
    public void setCollaborativeServiceType(CollaborativeServiceType collaborativeServiceType) {
        this.collaborativeServiceType = collaborativeServiceType;
    }
    
}

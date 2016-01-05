/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.StatusDAO;
import br.ufjf.pgcc.plscience.model.Status;
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
public class NewStatus implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Status status;

    public NewStatus() {
        this.status = new Status();
    }
    
    public void save() {    
        try { 
            getStatus().setId(null);
            new StatusDAO().save(getStatus()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Status saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }
        
}

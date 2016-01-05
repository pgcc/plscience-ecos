/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CompromiseDAO;
import br.ufjf.pgcc.plscience.model.Compromise;
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
public class NewCompromise implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Compromise compromise;

    public NewCompromise() {
        this.compromise = new Compromise();
    }
    
    public void save() {    
        try { 
            getCompromise().setId(null);
            new CompromiseDAO().save(getCompromise()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Compromise saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }

    /**
     * @return the product
     */
    public Compromise getCompromise() {
        return compromise;
    }

    /**
     * @param compromise the compromise to set
     */
    public void setCompromise(Compromise compromise) {
        this.compromise = compromise;
    }
        
}

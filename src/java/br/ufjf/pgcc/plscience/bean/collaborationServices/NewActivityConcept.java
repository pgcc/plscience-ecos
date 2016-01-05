/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.ActivityConceptDAO;
import br.ufjf.pgcc.plscience.model.ActivityConcept;
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
public class NewActivityConcept implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private ActivityConcept activity;

    public NewActivityConcept() {
        this.activity = new ActivityConcept();
    }
    
    public void save() {    
        try { 
            getActivity().setId(null);
            new ActivityConceptDAO().save(getActivity()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Activity saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }

    /**
     * @return the activity
     */
    public ActivityConcept getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(ActivityConcept activity) {
        this.activity = activity;
    }
        
}

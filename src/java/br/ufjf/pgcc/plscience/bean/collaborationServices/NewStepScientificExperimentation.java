/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.StepsScientificExperimentationDAO;
import br.ufjf.pgcc.plscience.model.StepsScientificExperimentation;
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
public class NewStepScientificExperimentation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private StepsScientificExperimentation stepsScientificExperimentation;

    public NewStepScientificExperimentation() {
        this.stepsScientificExperimentation = new StepsScientificExperimentation();
    }

    public void save() {    
        try { 
            getStepsScientificExperimentation().setId(null);
            new StepsScientificExperimentationDAO().save(getStepsScientificExperimentation()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Step Scientific Experimentation saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }
    
    /**
     * @return the stepsScientificExperimentation
     */
    public StepsScientificExperimentation getStepsScientificExperimentation() {
        return stepsScientificExperimentation;
    }

    /**
     * @param stepsScientificExperimentation the stepsScientificExperimentation to set
     */
    public void setStepsScientificExperimentation(StepsScientificExperimentation stepsScientificExperimentation) {
        this.stepsScientificExperimentation = stepsScientificExperimentation;
    }
    
}

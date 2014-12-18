/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.bean.experiments;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import org.hibernate.HibernateException;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class NewExperiment {
    
    private Experiment experiment;
    
    public NewExperiment() {
        this.experiment = new Experiment();
    }
    
    public void save() {
        try {
            new ExperimentDAO().persist(experiment);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Experiment created with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }

    /**
     * @return the experiment
     */
    public Experiment getExperiment() {
        return experiment;
    }

    /**
     * @param experiment the experiment to set
     */
    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }
    
}

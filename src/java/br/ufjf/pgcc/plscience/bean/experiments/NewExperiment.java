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
import br.ufjf.pgcc.plscience.domain.Experiment;
import br.ufjf.pgcc.plscience.repository.ExperimentRepository;
import org.hibernate.HibernateException;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class NewExperiment {
    
    private Experiment experiment;
    private final ExperimentRepository repository;
    
    public NewExperiment() {
        this.experiment = new Experiment();
        this.repository = new ExperimentRepository();
    }
    
    public void save() {
        try {
            repository.save(experiment);
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

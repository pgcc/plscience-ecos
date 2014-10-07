/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.bean.experiments;

import br.ufjf.pgcc.plscience.domain.Experiment;
import br.ufjf.pgcc.plscience.repository.ExperimentRepository;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class MyExperiments {
    
    private List<Experiment> experiments;
    private final ExperimentRepository repository;
    private Experiment selectedExperiment;
    
    public MyExperiments() {
        repository = new ExperimentRepository();
        experiments = repository.findAll();
    }
    
    public List<Experiment> getExperiments() {
        return experiments;
    }
    
    public String editExperiment() {
        return "experiment?faces-redirect=true";
    }

    /**
     * @return the selectedExperiment
     */
    public Experiment getSelectedExperiment() {
        return selectedExperiment;
    }

    /**
     * @param selectedExperiment the selectedExperiment to set
     */
    public void setSelectedExperiment(Experiment selectedExperiment) {
        this.selectedExperiment = selectedExperiment;
    }
    
}

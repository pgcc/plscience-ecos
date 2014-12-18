/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.bean.experiments;

import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class MyExperiments {
    
    private Experiment selectedExperiment;
    
    public MyExperiments() {
        
    }
    
    public List<Experiment> getExperiments() {
        return new ExperimentDAO().getAll();
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

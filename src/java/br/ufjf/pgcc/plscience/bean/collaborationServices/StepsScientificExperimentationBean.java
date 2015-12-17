/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.StepsScientificExperimentationDAO;
import br.ufjf.pgcc.plscience.model.StepsScientificExperimentation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "stepsScientificExperimentationBean")
@ViewScoped
public class StepsScientificExperimentationBean implements Serializable {
    
    private StepsScientificExperimentation stepsScientificExperimentation = new StepsScientificExperimentation();    
    private List stepsScientificExperimentations = new ArrayList();

    public StepsScientificExperimentationBean() {
        stepsScientificExperimentations = new StepsScientificExperimentationDAO().getAll();
        stepsScientificExperimentation = new StepsScientificExperimentation();
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

    /**
     * @return the stepsScientificExperimentations
     */
    public List getStepsScientificExperimentations() {
        return stepsScientificExperimentations;
    }

    /**
     * @param stepsScientificExperimentations the stepsScientificExperimentations to set
     */
    public void setStepsScientificExperimentations(List stepsScientificExperimentations) {
        this.stepsScientificExperimentations = stepsScientificExperimentations;
    }
    
}

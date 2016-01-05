/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.ActivityConceptDAO;
import br.ufjf.pgcc.plscience.model.ActivityConcept;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "activityConceptBean")
@ViewScoped
public class ActivityConceptBean implements Serializable {
    
    private ActivityConcept activityConcept = new ActivityConcept();
    private List activityConcepts = new ArrayList();
    
    public ActivityConceptBean() {
        activityConcepts = new ActivityConceptDAO().getAll();
        activityConcept = new ActivityConcept();
    }

    /**
     * @return the activityConcept
     */
    public ActivityConcept getActivityConcept() {
        return activityConcept;
    }

    /**
     * @param activityConcept the activityConcept to set
     */
    public void setActivityConcept(ActivityConcept activityConcept) {
        this.activityConcept = activityConcept;
    }

    /**
     * @return the activityConcepts
     */
    public List getActivityConcepts() {
        return activityConcepts;
    }

    /**
     * @param activityConcepts the activityConcepts to set
     */
    public void setActivityConcepts(List activityConcepts) {
        this.activityConcepts = activityConcepts;
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.TaskConceptDAO;
import br.ufjf.pgcc.plscience.model.TaskConcept;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "taskConceptBean")
@ViewScoped
public class TaskConceptBean implements Serializable {
    
    private TaskConcept taskConcept = new TaskConcept();
    private List taskConcepts = new ArrayList();
    
    public TaskConceptBean() {
        taskConcepts = new TaskConceptDAO().getAll();
        taskConcept = new TaskConcept();
    }

    /**
     * @return the taskConcept
     */
    public TaskConcept getTaskConcept() {
        return taskConcept;
    }

    /**
     * @param taskConcept the taskConcept to set
     */
    public void setTaskConcept(TaskConcept taskConcept) {
        this.taskConcept = taskConcept;
    }

    /**
     * @return the taskConcepts
     */
    public List getTaskConcepts() {
        return taskConcepts;
    }

    /**
     * @param taskConcepts the taskConcepts to set
     */
    public void setTaskConcepts(List taskConcepts) {
        this.taskConcepts = taskConcepts;
    }


}

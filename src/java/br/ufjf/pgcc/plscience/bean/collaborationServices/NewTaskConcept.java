/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.TaskConceptDAO;
import br.ufjf.pgcc.plscience.model.TaskConcept;
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
public class NewTaskConcept implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private TaskConcept task;

    public NewTaskConcept() {
        this.task = new TaskConcept();
    }
    
    public void save() {    
        try { 
            getTask().setId(null);
            new TaskConceptDAO().save(getTask()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Task saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }

    /**
     * @return the task
     */
    public TaskConcept getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(TaskConcept task) {
        this.task = task;
    }
        
}

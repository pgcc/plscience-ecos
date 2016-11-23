/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition.test;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author phillipe
 */
@ManagedBean()
@ViewScoped
public class TasksCompositionData {
    private String taskName;
    private List<TaskInput> taskInputs;
    private List<TaskOutput> taskOutputs;

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the taskInputs
     */
    public List<TaskInput> getTaskInputs() {
        return taskInputs;
    }

    /**
     * @param taskInputs the taskInputs to set
     */
    public void setTaskInputs(List<TaskInput> taskInputs) {
        this.taskInputs = taskInputs;
    }

    /**
     * @return the taskOutputs
     */
    public List<TaskOutput> getTaskOutputs() {
        return taskOutputs;
    }

    /**
     * @param taskOutputs the taskOutputs to set
     */
    public void setTaskOutputs(List<TaskOutput> taskOutputs) {
        this.taskOutputs = taskOutputs;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.workflow.model.input;

import java.util.ArrayList;

/**
 *
 * @author vitorfs
 */
public class TavernaInputDescription {
    private String workflowId;
    private String workflowRun;
    private String workflowRunId;
    private ArrayList<TavernaInput> input;

    /**
     * @return the workflowId
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * @param workflowId the workflowId to set
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * @return the workflowRun
     */
    public String getWorkflowRun() {
        return workflowRun;
    }

    /**
     * @param workflowRun the workflowRun to set
     */
    public void setWorkflowRun(String workflowRun) {
        this.workflowRun = workflowRun;
    }

    /**
     * @return the workflowRunId
     */
    public String getWorkflowRunId() {
        return workflowRunId;
    }

    /**
     * @param workflowRunId the workflowRunId to set
     */
    public void setWorkflowRunId(String workflowRunId) {
        this.workflowRunId = workflowRunId;
    }

    /**
     * @return the input
     */
    public ArrayList<TavernaInput> getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(ArrayList<TavernaInput> input) {
        this.input = input;
    }
}

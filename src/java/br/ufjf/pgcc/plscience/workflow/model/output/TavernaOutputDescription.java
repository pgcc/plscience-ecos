/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.workflow.model.output;

import java.util.ArrayList;

/**
 *
 * @author vitorfs
 */
public class TavernaOutputDescription {
    private String workflowId;
    private String workflowRun;
    private String workflowRunId;
    private ArrayList<TavernaOutput> output;

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
     * @return the output
     */
    public ArrayList<TavernaOutput> getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(ArrayList<TavernaOutput> output) {
        this.output = output;
    }
}

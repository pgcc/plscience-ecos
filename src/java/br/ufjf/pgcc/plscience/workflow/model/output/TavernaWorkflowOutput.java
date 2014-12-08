/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.workflow.model.output;

/**
 *
 * @author vitorfs
 */
public class TavernaWorkflowOutput {
    private TavernaOutputDescription workflowOutputs;

    /**
     * @return the workflowOutputs
     */
    public TavernaOutputDescription getWorkflowOutputs() {
        return workflowOutputs;
    }

    /**
     * @param workflowOutputs the workflowOutputs to set
     */
    public void setWorkflowOutputs(TavernaOutputDescription workflowOutputs) {
        this.workflowOutputs = workflowOutputs;
    }
}

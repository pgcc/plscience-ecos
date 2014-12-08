/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.workflow.model.input;

/**
 *
 * @author vitorfs
 */
public class TavernaExpectedInput {
    private TavernaInputDescription inputDescription;

    /**
     * @return the inputDescription
     */
    public TavernaInputDescription getInputDescription() {
        return inputDescription;
    }

    /**
     * @param inputDescription the inputDescription to set
     */
    public void setInputDescription(TavernaInputDescription inputDescription) {
        this.inputDescription = inputDescription;
    }
}

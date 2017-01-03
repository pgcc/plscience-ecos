/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

import java.util.List;

/**
 *
 * @author phillipe
 */
public class ServiceFromVR {
    private String name;
    private String description;
    private String type;
    private String repositoryName;
    private String owner;
    private List<String> inputs;
    private List<String> inputsType;
    private List<String> outputs;
    private List<String> outputsType;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the inputs
     */
    public List<String> getInputs() {
        return inputs;
    }

    /**
     * @param inputs the inputs to set
     */
    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }

    /**
     * @return the inputsType
     */
    public List<String> getInputsType() {
        return inputsType;
    }

    /**
     * @param inputsType the inputsType to set
     */
    public void setInputsType(List<String> inputsType) {
        this.inputsType = inputsType;
    }

    /**
     * @return the outputs
     */
    public List<String> getOutputs() {
        return outputs;
    }

    /**
     * @param outputs the outputs to set
     */
    public void setOutputs(List<String> outputs) {
        this.outputs = outputs;
    }

    /**
     * @return the outputsType
     */
    public List<String> getOutputsType() {
        return outputsType;
    }

    /**
     * @param outputsType the outputsType to set
     */
    public void setOutputsType(List<String> outputsType) {
        this.outputsType = outputsType;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the repositoryName
     */
    public String getRepositoryName() {
        return repositoryName;
    }

    /**
     * @param repositoryName the repositoryName to set
     */
    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}

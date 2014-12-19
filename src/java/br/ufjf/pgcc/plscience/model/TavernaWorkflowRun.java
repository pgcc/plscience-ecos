/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vitorfs
 */
@Entity
@Table(name="taverna_workflow_run")
public class TavernaWorkflowRun implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    @Column(name="uuid")
    private String uuid;

    @Column(name="name")
    private String name;
    
    @Column(name="status")
    private String status;

    @ManyToOne
    @JoinColumn(name="experiment_id")
    private Experiment experiment;
    
    @ManyToOne
    @JoinColumn(name="scientist_id")
    private Scientist scientist;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the scientist
     */
    public Scientist getScientist() {
        return scientist;
    }

    /**
     * @param scientist the scientist to set
     */
    public void setScientist(Scientist scientist) {
        this.scientist = scientist;
    }

    /**
     * @return the experiment
     */
    public Experiment getExperiment() {
        return experiment;
    }

    /**
     * @param experiment the experiment to set
     */
    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }
    
}

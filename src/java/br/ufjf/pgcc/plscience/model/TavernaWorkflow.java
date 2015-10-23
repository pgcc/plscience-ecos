/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vitorfs
 */
@Entity
@Table(name="taverna_workflow")
public class TavernaWorkflow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    @Column(name="t2flow", columnDefinition="MEDIUMTEXT")
    private String t2flow;
    
    @Column(name="name")
    private String name;
    
    @ManyToOne
    @JoinColumn(name="experiment_id")
    private Experiment experiment;
    
    @ManyToOne
    @JoinColumn(name="idAgent")
    private Agent agent;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tavernaWorkflow", cascade = CascadeType.ALL)
    private Set<TavernaWorkflowRun> runs = new HashSet<TavernaWorkflowRun>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tavernaWorkflow", cascade = CascadeType.ALL)
    private Set<TavernaWorkflowInput> inputs = new HashSet<TavernaWorkflowInput>();
        
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
     * @return the t2flow
     */
    public String getT2flow() {
        return t2flow;
    }

    /**
     * @param t2flow the t2flow to set
     */
    public void setT2flow(String t2flow) {
        this.t2flow = t2flow;
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

    /**
     * @return the scientist
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * @param agent the scientist to set
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
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
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the runs
     */
    public Set<TavernaWorkflowRun> getRuns() {
        return runs;
    }

    /**
     * @param runs the runs to set
     */
    public void setRuns(Set<TavernaWorkflowRun> runs) {
        this.runs = runs;
    }

    /**
     * @return the inputs
     */
    public Set<TavernaWorkflowInput> getInputs() {
        return inputs;
    }

    /**
     * @param inputs the inputs to set
     */
    public void setInputs(Set<TavernaWorkflowInput> inputs) {
        this.inputs = inputs;
    }
    
}

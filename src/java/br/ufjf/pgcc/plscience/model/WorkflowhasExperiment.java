/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tassio
 */
@Entity
@Table(name = "Workflow_has_Experiment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkflowhasExperiment.findAll", query = "SELECT w FROM WorkflowhasExperiment w"),
    @NamedQuery(name = "WorkflowhasExperiment.findByIdWorkflowhasExperiment", query = "SELECT w FROM WorkflowhasExperiment w WHERE w.idWorkflowhasExperiment = :idWorkflowhasExperiment")})
public class WorkflowhasExperiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWorkflow_has_Experiment")
    private Integer idWorkflowhasExperiment;
    @JoinColumn(name = "Experiment_Experiment", referencedColumnName = "Experiment")
    @ManyToOne(optional = false)
    private Experiment experimentExperiment;
    @JoinColumn(name = "Workflow_idWorkflow", referencedColumnName = "idWorkflow")
    @ManyToOne(optional = false)
    private Workflow workflowidWorkflow;

    public WorkflowhasExperiment() {
    }

    public WorkflowhasExperiment(Integer idWorkflowhasExperiment) {
        this.idWorkflowhasExperiment = idWorkflowhasExperiment;
    }

    public Integer getIdWorkflowhasExperiment() {
        return idWorkflowhasExperiment;
    }

    public void setIdWorkflowhasExperiment(Integer idWorkflowhasExperiment) {
        this.idWorkflowhasExperiment = idWorkflowhasExperiment;
    }

    public Experiment getExperimentExperiment() {
        return experimentExperiment;
    }

    public void setExperimentExperiment(Experiment experimentExperiment) {
        this.experimentExperiment = experimentExperiment;
    }

    public Workflow getWorkflowidWorkflow() {
        return workflowidWorkflow;
    }

    public void setWorkflowidWorkflow(Workflow workflowidWorkflow) {
        this.workflowidWorkflow = workflowidWorkflow;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWorkflowhasExperiment != null ? idWorkflowhasExperiment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkflowhasExperiment)) {
            return false;
        }
        WorkflowhasExperiment other = (WorkflowhasExperiment) object;
        if ((this.idWorkflowhasExperiment == null && other.idWorkflowhasExperiment != null) || (this.idWorkflowhasExperiment != null && !this.idWorkflowhasExperiment.equals(other.idWorkflowhasExperiment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.model.WorkflowhasExperiment[ idWorkflowhasExperiment=" + idWorkflowhasExperiment + " ]";
    }
    
}

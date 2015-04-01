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
@Table(name = "WasAttributedTo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasAttributedTo.findAll", query = "SELECT w FROM WasAttributedTo w"),
    @NamedQuery(name = "WasAttributedTo.findByIdWasAttributedTo", query = "SELECT w FROM WasAttributedTo w WHERE w.idWasAttributedTo = :idWasAttributedTo"),
    @NamedQuery(name = "WasAttributedTo.findByDescription", query = "SELECT w FROM WasAttributedTo w WHERE w.description = :description")})
public class WasAttributedTo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasAttributedTo")
    private Integer idWasAttributedTo;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Experiment_Experiment", referencedColumnName = "idExperiment")
    @ManyToOne
    private Experiment experimentExperiment;
    @JoinColumn(name = "Workflow_idWorkflow", referencedColumnName = "idWorkflow")
    @ManyToOne
    private Workflow workflowidWorkflow;

    public WasAttributedTo() {
    }

    public WasAttributedTo(Integer idWasAttributedTo) {
        this.idWasAttributedTo = idWasAttributedTo;
    }

    public Integer getIdWasAttributedTo() {
        return idWasAttributedTo;
    }

    public void setIdWasAttributedTo(Integer idWasAttributedTo) {
        this.idWasAttributedTo = idWasAttributedTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (idWasAttributedTo != null ? idWasAttributedTo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasAttributedTo)) {
            return false;
        }
        WasAttributedTo other = (WasAttributedTo) object;
        if ((this.idWasAttributedTo == null && other.idWasAttributedTo != null) || (this.idWasAttributedTo != null && !this.idWasAttributedTo.equals(other.idWasAttributedTo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.WasAttributedTo[ idWasAttributedTo=" + idWasAttributedTo + " ]";
    }
    
}

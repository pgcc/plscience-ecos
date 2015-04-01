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
@Table(name = "WasGeneratedBy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasGeneratedBy.findAll", query = "SELECT w FROM WasGeneratedBy w"),
    @NamedQuery(name = "WasGeneratedBy.findByIdWasGeneratedBy", query = "SELECT w FROM WasGeneratedBy w WHERE w.idWasGeneratedBy = :idWasGeneratedBy"),
    @NamedQuery(name = "WasGeneratedBy.findByDescription", query = "SELECT w FROM WasGeneratedBy w WHERE w.description = :description")})
public class WasGeneratedBy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idWasGeneratedBy")
    private Integer idWasGeneratedBy;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Experiment_Experiment", referencedColumnName = "idExperiment")
    @ManyToOne(optional = false)
    private Experiment experimentExperiment;
    @JoinColumn(name = "ResearchGroup_idResearchGroup", referencedColumnName = "idResearchGroup")
    @ManyToOne(optional = false)
    private ResearchGroup researchGroupidResearchGroup;

    public WasGeneratedBy() {
    }

    public WasGeneratedBy(Integer idWasGeneratedBy) {
        this.idWasGeneratedBy = idWasGeneratedBy;
    }

    public Integer getIdWasGeneratedBy() {
        return idWasGeneratedBy;
    }

    public void setIdWasGeneratedBy(Integer idWasGeneratedBy) {
        this.idWasGeneratedBy = idWasGeneratedBy;
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

    public ResearchGroup getResearchGroupidResearchGroup() {
        return researchGroupidResearchGroup;
    }

    public void setResearchGroupidResearchGroup(ResearchGroup researchGroupidResearchGroup) {
        this.researchGroupidResearchGroup = researchGroupidResearchGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasGeneratedBy != null ? idWasGeneratedBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasGeneratedBy)) {
            return false;
        }
        WasGeneratedBy other = (WasGeneratedBy) object;
        if ((this.idWasGeneratedBy == null && other.idWasGeneratedBy != null) || (this.idWasGeneratedBy != null && !this.idWasGeneratedBy.equals(other.idWasGeneratedBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.WasGeneratedBy[ idWasGeneratedBy=" + idWasGeneratedBy + " ]";
    }
    
}

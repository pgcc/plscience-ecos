/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tassio
 */
@Entity
@Table(name = "Experiment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experiment.findAll", query = "SELECT e FROM Experiment e"),
    @NamedQuery(name = "Experiment.findByExperiment", query = "SELECT e FROM Experiment e WHERE e.experiment = :experiment"),
    @NamedQuery(name = "Experiment.findByName", query = "SELECT e FROM Experiment e WHERE e.name = :name"),
    @NamedQuery(name = "Experiment.findByDateStarted", query = "SELECT e FROM Experiment e WHERE e.dateStarted = :dateStarted"),
    @NamedQuery(name = "Experiment.findByDateEnded", query = "SELECT e FROM Experiment e WHERE e.dateEnded = :dateEnded"),
    @NamedQuery(name = "Experiment.findByDescription", query = "SELECT e FROM Experiment e WHERE e.description = :description"),
    @NamedQuery(name = "Experiment.findByVersion", query = "SELECT e FROM Experiment e WHERE e.version = :version")})
public class Experiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Experiment")
    private Integer experiment;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "DateStarted")
    @Temporal(TemporalType.DATE)
    private Date dateStarted;
    @Column(name = "DateEnded")
    @Temporal(TemporalType.DATE)
    private Date dateEnded;
    @Column(name = "Description")
    private String description;
    @Column(name = "Version")
    private String version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experimentExperiment")
    private List<ResearchGroup> researchGroupList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experimentExperiment")
    private List<WorkflowhasExperiment> workflowhasExperimentList;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity activityidActivity;
    @JoinColumn(name = "Entity_idEntity", referencedColumnName = "idEntity")
    @ManyToOne(optional = false)
    private br.ufjf.pgcc.plscience.model.Entity entityidEntity;

    public Experiment() {
    }

    public Experiment(Integer experiment) {
        this.experiment = experiment;
    }

    public Experiment(Integer experiment, String name) {
        this.experiment = experiment;
        this.name = name;
    }

    public Integer getExperiment() {
        return experiment;
    }

    public void setExperiment(Integer experiment) {
        this.experiment = experiment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlTransient
    public List<ResearchGroup> getResearchGroupList() {
        return researchGroupList;
    }

    public void setResearchGroupList(List<ResearchGroup> researchGroupList) {
        this.researchGroupList = researchGroupList;
    }

    @XmlTransient
    public List<WorkflowhasExperiment> getWorkflowhasExperimentList() {
        return workflowhasExperimentList;
    }

    public void setWorkflowhasExperimentList(List<WorkflowhasExperiment> workflowhasExperimentList) {
        this.workflowhasExperimentList = workflowhasExperimentList;
    }

    public Activity getActivityidActivity() {
        return activityidActivity;
    }

    public void setActivityidActivity(Activity activityidActivity) {
        this.activityidActivity = activityidActivity;
    }

    public br.ufjf.pgcc.plscience.model.Entity getEntityidEntity() {
        return entityidEntity;
    }

    public void setEntityidEntity(br.ufjf.pgcc.plscience.model.Entity entityidEntity) {
        this.entityidEntity = entityidEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (experiment != null ? experiment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Experiment)) {
            return false;
        }
        Experiment other = (Experiment) object;
        if ((this.experiment == null && other.experiment != null) || (this.experiment != null && !this.experiment.equals(other.experiment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.model.Experiment[ experiment=" + experiment + " ]";
    }
    
}

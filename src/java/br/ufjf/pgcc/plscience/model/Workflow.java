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
@Table(name = "Workflow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Workflow.findAll", query = "SELECT w FROM Workflow w"),
    @NamedQuery(name = "Workflow.findByIdWorkflow", query = "SELECT w FROM Workflow w WHERE w.idWorkflow = :idWorkflow"),
    @NamedQuery(name = "Workflow.findByName", query = "SELECT w FROM Workflow w WHERE w.name = :name"),
    @NamedQuery(name = "Workflow.findByDescription", query = "SELECT w FROM Workflow w WHERE w.description = :description"),
    @NamedQuery(name = "Workflow.findByVersion", query = "SELECT w FROM Workflow w WHERE w.version = :version"),
    @NamedQuery(name = "Workflow.findByDateVersion", query = "SELECT w FROM Workflow w WHERE w.dateVersion = :dateVersion"),
    @NamedQuery(name = "Workflow.findBySGWfC", query = "SELECT w FROM Workflow w WHERE w.sGWfC = :sGWfC")})
public class Workflow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWorkflow")
    private Integer idWorkflow;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Version")
    private String version;
    @Basic(optional = false)
    @Column(name = "DateVersion")
    @Temporal(TemporalType.DATE)
    private Date dateVersion;
    @Basic(optional = false)
    @Column(name = "SGWfC")
    private String sGWfC;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workflowidWorkflow")
    private List<WorkflowhasExperiment> workflowhasExperimentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "derivedTo")
    private List<WasDerivedFrom> wasDerivedFromList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "derivedOf")
    private List<WasDerivedFrom> wasDerivedFromList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workflowidWorkflow")
    private List<Task> taskList;

    public Workflow() {
    }

    public Workflow(Integer idWorkflow) {
        this.idWorkflow = idWorkflow;
    }

    public Workflow(Integer idWorkflow, String name, String version, Date dateVersion, String sGWfC) {
        this.idWorkflow = idWorkflow;
        this.name = name;
        this.version = version;
        this.dateVersion = dateVersion;
        this.sGWfC = sGWfC;
    }

    public Integer getIdWorkflow() {
        return idWorkflow;
    }

    public void setIdWorkflow(Integer idWorkflow) {
        this.idWorkflow = idWorkflow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getDateVersion() {
        return dateVersion;
    }

    public void setDateVersion(Date dateVersion) {
        this.dateVersion = dateVersion;
    }

    public String getSGWfC() {
        return sGWfC;
    }

    public void setSGWfC(String sGWfC) {
        this.sGWfC = sGWfC;
    }

    @XmlTransient
    public List<WorkflowhasExperiment> getWorkflowhasExperimentList() {
        return workflowhasExperimentList;
    }

    public void setWorkflowhasExperimentList(List<WorkflowhasExperiment> workflowhasExperimentList) {
        this.workflowhasExperimentList = workflowhasExperimentList;
    }

    @XmlTransient
    public List<WasDerivedFrom> getWasDerivedFromList() {
        return wasDerivedFromList;
    }

    public void setWasDerivedFromList(List<WasDerivedFrom> wasDerivedFromList) {
        this.wasDerivedFromList = wasDerivedFromList;
    }

    @XmlTransient
    public List<WasDerivedFrom> getWasDerivedFromList1() {
        return wasDerivedFromList1;
    }

    public void setWasDerivedFromList1(List<WasDerivedFrom> wasDerivedFromList1) {
        this.wasDerivedFromList1 = wasDerivedFromList1;
    }

    @XmlTransient
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWorkflow != null ? idWorkflow.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Workflow)) {
            return false;
        }
        Workflow other = (Workflow) object;
        if ((this.idWorkflow == null && other.idWorkflow != null) || (this.idWorkflow != null && !this.idWorkflow.equals(other.idWorkflow))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.model.Workflow[ idWorkflow=" + idWorkflow + " ]";
    }
    
}

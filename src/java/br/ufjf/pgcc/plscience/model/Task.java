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
@Table(name = "Task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findByIdTask", query = "SELECT t FROM Task t WHERE t.idTask = :idTask"),
    @NamedQuery(name = "Task.findByName", query = "SELECT t FROM Task t WHERE t.name = :name"),
    @NamedQuery(name = "Task.findByType", query = "SELECT t FROM Task t WHERE t.type = :type"),
    @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description"),
    @NamedQuery(name = "Task.findByStarted", query = "SELECT t FROM Task t WHERE t.started = :started"),
    @NamedQuery(name = "Task.findByEnded", query = "SELECT t FROM Task t WHERE t.ended = :ended")})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTask")
    private Integer idTask;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Type")
    private String type;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Started")
    @Temporal(TemporalType.TIMESTAMP)
    private Date started;
    @Column(name = "Ended")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ended;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskchildren")
    private List<ActedOnBeHalfOfTask> actedOnBeHalfOfTaskList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskfather")
    private List<ActedOnBeHalfOfTask> actedOnBeHalfOfTaskList1;
    @JoinColumn(name = "Workflow_idWorkflow", referencedColumnName = "idWorkflow")
    @ManyToOne(optional = false)
    private Workflow workflowidWorkflow;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<WasInformedBy> wasInformedByList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<WasStartedByTaskActivity> wasStartedByTaskActivityList;

    public Task() {
    }

    public Task(Integer idTask) {
        this.idTask = idTask;
    }

    public Task(Integer idTask, String name, Date started) {
        this.idTask = idTask;
        this.name = name;
        this.started = started;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    @XmlTransient
    public List<ActedOnBeHalfOfTask> getActedOnBeHalfOfTaskList() {
        return actedOnBeHalfOfTaskList;
    }

    public void setActedOnBeHalfOfTaskList(List<ActedOnBeHalfOfTask> actedOnBeHalfOfTaskList) {
        this.actedOnBeHalfOfTaskList = actedOnBeHalfOfTaskList;
    }

    @XmlTransient
    public List<ActedOnBeHalfOfTask> getActedOnBeHalfOfTaskList1() {
        return actedOnBeHalfOfTaskList1;
    }

    public void setActedOnBeHalfOfTaskList1(List<ActedOnBeHalfOfTask> actedOnBeHalfOfTaskList1) {
        this.actedOnBeHalfOfTaskList1 = actedOnBeHalfOfTaskList1;
    }

    public Workflow getWorkflowidWorkflow() {
        return workflowidWorkflow;
    }

    public void setWorkflowidWorkflow(Workflow workflowidWorkflow) {
        this.workflowidWorkflow = workflowidWorkflow;
    }

    @XmlTransient
    public List<WasInformedBy> getWasInformedByList() {
        return wasInformedByList;
    }

    public void setWasInformedByList(List<WasInformedBy> wasInformedByList) {
        this.wasInformedByList = wasInformedByList;
    }

    @XmlTransient
    public List<WasStartedByTaskActivity> getWasStartedByTaskActivityList() {
        return wasStartedByTaskActivityList;
    }

    public void setWasStartedByTaskActivityList(List<WasStartedByTaskActivity> wasStartedByTaskActivityList) {
        this.wasStartedByTaskActivityList = wasStartedByTaskActivityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTask != null ? idTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.idTask == null && other.idTask != null) || (this.idTask != null && !this.idTask.equals(other.idTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.model.Task[ idTask=" + idTask + " ]";
    }
    
}

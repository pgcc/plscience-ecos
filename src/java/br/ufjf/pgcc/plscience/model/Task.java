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
    @JoinColumn(name = "Workflow_idWorkflow", referencedColumnName = "idWorkflow")
    @ManyToOne(optional = false)
    private Workflow workflowidWorkflow;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<Port> portList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<WasInformedBy> wasInformedByList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<Used> usedList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<WasEndedBy> wasEndedByList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<WasStartedBy> wasStartedByList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskfather")
    private List<ActedOnBeHalfOf> actedOnBeHalfOfList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskchildren")
    private List<ActedOnBeHalfOf> actedOnBeHalfOfList1;

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

    public Workflow getWorkflowidWorkflow() {
        return workflowidWorkflow;
    }

    public void setWorkflowidWorkflow(Workflow workflowidWorkflow) {
        this.workflowidWorkflow = workflowidWorkflow;
    }

    @XmlTransient
    public List<Port> getPortList() {
        return portList;
    }

    public void setPortList(List<Port> portList) {
        this.portList = portList;
    }

    @XmlTransient
    public List<WasInformedBy> getWasInformedByList() {
        return wasInformedByList;
    }

    public void setWasInformedByList(List<WasInformedBy> wasInformedByList) {
        this.wasInformedByList = wasInformedByList;
    }

    @XmlTransient
    public List<Used> getUsedList() {
        return usedList;
    }

    public void setUsedList(List<Used> usedList) {
        this.usedList = usedList;
    }

    @XmlTransient
    public List<WasEndedBy> getWasEndedByList() {
        return wasEndedByList;
    }

    public void setWasEndedByList(List<WasEndedBy> wasEndedByList) {
        this.wasEndedByList = wasEndedByList;
    }

    @XmlTransient
    public List<WasStartedBy> getWasStartedByList() {
        return wasStartedByList;
    }

    public void setWasStartedByList(List<WasStartedBy> wasStartedByList) {
        this.wasStartedByList = wasStartedByList;
    }

    @XmlTransient
    public List<ActedOnBeHalfOf> getActedOnBeHalfOfList() {
        return actedOnBeHalfOfList;
    }

    public void setActedOnBeHalfOfList(List<ActedOnBeHalfOf> actedOnBeHalfOfList) {
        this.actedOnBeHalfOfList = actedOnBeHalfOfList;
    }

    @XmlTransient
    public List<ActedOnBeHalfOf> getActedOnBeHalfOfList1() {
        return actedOnBeHalfOfList1;
    }

    public void setActedOnBeHalfOfList1(List<ActedOnBeHalfOf> actedOnBeHalfOfList1) {
        this.actedOnBeHalfOfList1 = actedOnBeHalfOfList1;
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
        return "br.ufjf.pgcc.plscience.model.Task[ idTask=" + idTask + " ]";
    }
    
}

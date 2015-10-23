/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
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
    @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description")})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTask")
    private Integer idTask;
    @Column(name = "Name")
    private String name;
    @Column(name = "Type")
    private String type;
    @Column(name = "Description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<OutputPort> outputPortList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<WasEndedByWT> wasEndedByWTList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<InputPort> inputPortList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<WasInformedBy> wasInformedByList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<WasStartedByWT> wasStartedByWTList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<Used> usedList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<WasEndedBy> wasEndedByList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskidTask")
    private List<WasStartedBy> wasStartedByList;

    public Task() {
    }

    public Task(Integer idTask) {
        this.idTask = idTask;
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

    @XmlTransient
    public List<OutputPort> getOutputPortList() {
        return outputPortList;
    }

    public void setOutputPortList(List<OutputPort> outputPortList) {
        this.outputPortList = outputPortList;
    }

    @XmlTransient
    public List<WasEndedByWT> getWasEndedByWTList() {
        return wasEndedByWTList;
    }

    public void setWasEndedByWTList(List<WasEndedByWT> wasEndedByWTList) {
        this.wasEndedByWTList = wasEndedByWTList;
    }

    @XmlTransient
    public List<InputPort> getInputPortList() {
        return inputPortList;
    }

    public void setInputPortList(List<InputPort> inputPortList) {
        this.inputPortList = inputPortList;
    }

    @XmlTransient
    public List<WasInformedBy> getWasInformedByList() {
        return wasInformedByList;
    }

    public void setWasInformedByList(List<WasInformedBy> wasInformedByList) {
        this.wasInformedByList = wasInformedByList;
    }

    @XmlTransient
    public List<WasStartedByWT> getWasStartedByWTList() {
        return wasStartedByWTList;
    }

    public void setWasStartedByWTList(List<WasStartedByWT> wasStartedByWTList) {
        this.wasStartedByWTList = wasStartedByWTList;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tassio
 */
@Entity
@Table(name = "WasStartedByWT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasStartedByWT.findAll", query = "SELECT w FROM WasStartedByWT w"),
    @NamedQuery(name = "WasStartedByWT.findByIdWasStartedByWT", query = "SELECT w FROM WasStartedByWT w WHERE w.idWasStartedByWT = :idWasStartedByWT"),
    @NamedQuery(name = "WasStartedByWT.findByStarted", query = "SELECT w FROM WasStartedByWT w WHERE w.started = :started")})
public class WasStartedByWT implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasStartedByWT")
    private Integer idWasStartedByWT;
    @Basic(optional = false)
    @Column(name = "Started")
    @Temporal(TemporalType.TIMESTAMP)
    private Date started;
    @JoinColumn(name = "Workflow_idWorkflow", referencedColumnName = "idWorkflow")
    @ManyToOne(optional = false)
    private Workflow workflowidWorkflow;
    @JoinColumn(name = "Task_idTask", referencedColumnName = "idTask")
    @ManyToOne(optional = false)
    private Task taskidTask;

    public WasStartedByWT() {
    }

    public WasStartedByWT(Integer idWasStartedByWT) {
        this.idWasStartedByWT = idWasStartedByWT;
    }

    public WasStartedByWT(Integer idWasStartedByWT, Date started) {
        this.idWasStartedByWT = idWasStartedByWT;
        this.started = started;
    }

    public Integer getIdWasStartedByWT() {
        return idWasStartedByWT;
    }

    public void setIdWasStartedByWT(Integer idWasStartedByWT) {
        this.idWasStartedByWT = idWasStartedByWT;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Workflow getWorkflowidWorkflow() {
        return workflowidWorkflow;
    }

    public void setWorkflowidWorkflow(Workflow workflowidWorkflow) {
        this.workflowidWorkflow = workflowidWorkflow;
    }

    public Task getTaskidTask() {
        return taskidTask;
    }

    public void setTaskidTask(Task taskidTask) {
        this.taskidTask = taskidTask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasStartedByWT != null ? idWasStartedByWT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasStartedByWT)) {
            return false;
        }
        WasStartedByWT other = (WasStartedByWT) object;
        if ((this.idWasStartedByWT == null && other.idWasStartedByWT != null) || (this.idWasStartedByWT != null && !this.idWasStartedByWT.equals(other.idWasStartedByWT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.WasStartedByWT[ idWasStartedByWT=" + idWasStartedByWT + " ]";
    }
    
}

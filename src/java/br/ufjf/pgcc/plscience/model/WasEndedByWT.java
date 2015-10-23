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
@Table(name = "WasEndedByWT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasEndedByWT.findAll", query = "SELECT w FROM WasEndedByWT w"),
    @NamedQuery(name = "WasEndedByWT.findByIdWasEndedByWT", query = "SELECT w FROM WasEndedByWT w WHERE w.idWasEndedByWT = :idWasEndedByWT"),
    @NamedQuery(name = "WasEndedByWT.findByEnded", query = "SELECT w FROM WasEndedByWT w WHERE w.ended = :ended")})
public class WasEndedByWT implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasEndedByWT")
    private Integer idWasEndedByWT;
    @Column(name = "Ended")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ended;
    @JoinColumn(name = "Workflow_idWorkflow", referencedColumnName = "idWorkflow")
    @ManyToOne(optional = false)
    private Workflow workflowidWorkflow;
    @JoinColumn(name = "Task_idTask", referencedColumnName = "idTask")
    @ManyToOne(optional = false)
    private Task taskidTask;

    public WasEndedByWT() {
    }

    public WasEndedByWT(Integer idWasEndedByWT) {
        this.idWasEndedByWT = idWasEndedByWT;
    }

    public Integer getIdWasEndedByWT() {
        return idWasEndedByWT;
    }

    public void setIdWasEndedByWT(Integer idWasEndedByWT) {
        this.idWasEndedByWT = idWasEndedByWT;
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

    public Task getTaskidTask() {
        return taskidTask;
    }

    public void setTaskidTask(Task taskidTask) {
        this.taskidTask = taskidTask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasEndedByWT != null ? idWasEndedByWT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasEndedByWT)) {
            return false;
        }
        WasEndedByWT other = (WasEndedByWT) object;
        if ((this.idWasEndedByWT == null && other.idWasEndedByWT != null) || (this.idWasEndedByWT != null && !this.idWasEndedByWT.equals(other.idWasEndedByWT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.WasEndedByWT[ idWasEndedByWT=" + idWasEndedByWT + " ]";
    }
    
}

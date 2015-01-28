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
@Table(name = "WasEndedBy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasEndedBy.findAll", query = "SELECT w FROM WasEndedBy w"),
    @NamedQuery(name = "WasEndedBy.findByIdWasEndedBy", query = "SELECT w FROM WasEndedBy w WHERE w.idWasEndedBy = :idWasEndedBy"),
    @NamedQuery(name = "WasEndedBy.findByDateEnded", query = "SELECT w FROM WasEndedBy w WHERE w.dateEnded = :dateEnded"),
    @NamedQuery(name = "WasEndedBy.findByDescription", query = "SELECT w FROM WasEndedBy w WHERE w.description = :description")})
public class WasEndedBy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasEndedBy")
    private Integer idWasEndedBy;
    @Column(name = "DateEnded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnded;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity agentidAgent;
    @JoinColumn(name = "Task_idTask", referencedColumnName = "idTask")
    @ManyToOne(optional = false)
    private Task taskidTask;

    public WasEndedBy() {
    }

    public WasEndedBy(Integer idWasEndedBy) {
        this.idWasEndedBy = idWasEndedBy;
    }

    public Integer getIdWasEndedBy() {
        return idWasEndedBy;
    }

    public void setIdWasEndedBy(Integer idWasEndedBy) {
        this.idWasEndedBy = idWasEndedBy;
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

    public Activity getAgentidAgent() {
        return agentidAgent;
    }

    public void setAgentidAgent(Activity agentidAgent) {
        this.agentidAgent = agentidAgent;
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
        hash += (idWasEndedBy != null ? idWasEndedBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasEndedBy)) {
            return false;
        }
        WasEndedBy other = (WasEndedBy) object;
        if ((this.idWasEndedBy == null && other.idWasEndedBy != null) || (this.idWasEndedBy != null && !this.idWasEndedBy.equals(other.idWasEndedBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.WasEndedBy[ idWasEndedBy=" + idWasEndedBy + " ]";
    }
    
}

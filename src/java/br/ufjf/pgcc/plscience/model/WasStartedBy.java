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
@Table(name = "WasStartedBy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasStartedBy.findAll", query = "SELECT w FROM WasStartedBy w"),
    @NamedQuery(name = "WasStartedBy.findByIdWasStartedBy", query = "SELECT w FROM WasStartedBy w WHERE w.idWasStartedBy = :idWasStartedBy"),
    @NamedQuery(name = "WasStartedBy.findByDateStarted", query = "SELECT w FROM WasStartedBy w WHERE w.dateStarted = :dateStarted"),
    @NamedQuery(name = "WasStartedBy.findByDescription", query = "SELECT w FROM WasStartedBy w WHERE w.description = :description")})
public class WasStartedBy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasStartedBy")
    private Integer idWasStartedBy;
    @Column(name = "DateStarted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStarted;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity activityidActivity;
    @JoinColumn(name = "Task_idTask", referencedColumnName = "idTask")
    @ManyToOne(optional = false)
    private Task taskidTask;

    public WasStartedBy() {
    }

    public WasStartedBy(Integer idWasStartedBy) {
        this.idWasStartedBy = idWasStartedBy;
    }

    public Integer getIdWasStartedBy() {
        return idWasStartedBy;
    }

    public void setIdWasStartedBy(Integer idWasStartedBy) {
        this.idWasStartedBy = idWasStartedBy;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Activity getActivityidActivity() {
        return activityidActivity;
    }

    public void setActivityidActivity(Activity activityidActivity) {
        this.activityidActivity = activityidActivity;
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
        hash += (idWasStartedBy != null ? idWasStartedBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasStartedBy)) {
            return false;
        }
        WasStartedBy other = (WasStartedBy) object;
        if ((this.idWasStartedBy == null && other.idWasStartedBy != null) || (this.idWasStartedBy != null && !this.idWasStartedBy.equals(other.idWasStartedBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.WasStartedBy[ idWasStartedBy=" + idWasStartedBy + " ]";
    }
    
}

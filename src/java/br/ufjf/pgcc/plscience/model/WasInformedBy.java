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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "WasInformedBy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasInformedBy.findAll", query = "SELECT w FROM WasInformedBy w"),
    @NamedQuery(name = "WasInformedBy.findByIdWasInformedBy", query = "SELECT w FROM WasInformedBy w WHERE w.idWasInformedBy = :idWasInformedBy"),
    @NamedQuery(name = "WasInformedBy.findByDescription", query = "SELECT w FROM WasInformedBy w WHERE w.description = :description")})
public class WasInformedBy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasInformedBy")
    private Integer idWasInformedBy;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Task_idTask", referencedColumnName = "idTask")
    @ManyToOne(optional = false)
    private Task taskidTask;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity activityidActivity;

    public WasInformedBy() {
    }

    public WasInformedBy(Integer idWasInformedBy) {
        this.idWasInformedBy = idWasInformedBy;
    }

    public Integer getIdWasInformedBy() {
        return idWasInformedBy;
    }

    public void setIdWasInformedBy(Integer idWasInformedBy) {
        this.idWasInformedBy = idWasInformedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getTaskidTask() {
        return taskidTask;
    }

    public void setTaskidTask(Task taskidTask) {
        this.taskidTask = taskidTask;
    }

    public Activity getActivityidActivity() {
        return activityidActivity;
    }

    public void setActivityidActivity(Activity activityidActivity) {
        this.activityidActivity = activityidActivity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasInformedBy != null ? idWasInformedBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasInformedBy)) {
            return false;
        }
        WasInformedBy other = (WasInformedBy) object;
        if ((this.idWasInformedBy == null && other.idWasInformedBy != null) || (this.idWasInformedBy != null && !this.idWasInformedBy.equals(other.idWasInformedBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.WasInformedBy[ idWasInformedBy=" + idWasInformedBy + " ]";
    }
    
}

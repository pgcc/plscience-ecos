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
@Table(name = "Used")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Used.findAll", query = "SELECT u FROM Used u"),
    @NamedQuery(name = "Used.findByIdUsed", query = "SELECT u FROM Used u WHERE u.idUsed = :idUsed"),
    @NamedQuery(name = "Used.findByDescription", query = "SELECT u FROM Used u WHERE u.description = :description")})
public class Used implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsed")
    private Integer idUsed;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Task_idTask", referencedColumnName = "idTask")
    @ManyToOne(optional = false)
    private Task taskidTask;
    @JoinColumn(name = "Workflow_idWorkflow", referencedColumnName = "idWorkflow")
    @ManyToOne(optional = false)
    private Workflow workflowidWorkflow;

    public Used() {
    }

    public Used(Integer idUsed) {
        this.idUsed = idUsed;
    }

    public Integer getIdUsed() {
        return idUsed;
    }

    public void setIdUsed(Integer idUsed) {
        this.idUsed = idUsed;
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

    public Workflow getWorkflowidWorkflow() {
        return workflowidWorkflow;
    }

    public void setWorkflowidWorkflow(Workflow workflowidWorkflow) {
        this.workflowidWorkflow = workflowidWorkflow;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsed != null ? idUsed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Used)) {
            return false;
        }
        Used other = (Used) object;
        if ((this.idUsed == null && other.idUsed != null) || (this.idUsed != null && !this.idUsed.equals(other.idUsed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.Used[ idUsed=" + idUsed + " ]";
    }
    
}

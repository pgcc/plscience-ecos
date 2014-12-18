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
@Table(name = "ActedOnBeHalfOf_Task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActedOnBeHalfOfTask.findAll", query = "SELECT a FROM ActedOnBeHalfOfTask a"),
    @NamedQuery(name = "ActedOnBeHalfOfTask.findByIdActedOnBeHalfOf", query = "SELECT a FROM ActedOnBeHalfOfTask a WHERE a.idActedOnBeHalfOf = :idActedOnBeHalfOf")})
public class ActedOnBeHalfOfTask implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActedOnBeHalfOf")
    private Integer idActedOnBeHalfOf;
    @JoinColumn(name = "Task_children", referencedColumnName = "idTask")
    @ManyToOne(optional = false)
    private Task taskchildren;
    @JoinColumn(name = "Task_father", referencedColumnName = "idTask")
    @ManyToOne(optional = false)
    private Task taskfather;

    public ActedOnBeHalfOfTask() {
    }

    public ActedOnBeHalfOfTask(Integer idActedOnBeHalfOf) {
        this.idActedOnBeHalfOf = idActedOnBeHalfOf;
    }

    public Integer getIdActedOnBeHalfOf() {
        return idActedOnBeHalfOf;
    }

    public void setIdActedOnBeHalfOf(Integer idActedOnBeHalfOf) {
        this.idActedOnBeHalfOf = idActedOnBeHalfOf;
    }

    public Task getTaskchildren() {
        return taskchildren;
    }

    public void setTaskchildren(Task taskchildren) {
        this.taskchildren = taskchildren;
    }

    public Task getTaskfather() {
        return taskfather;
    }

    public void setTaskfather(Task taskfather) {
        this.taskfather = taskfather;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActedOnBeHalfOf != null ? idActedOnBeHalfOf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActedOnBeHalfOfTask)) {
            return false;
        }
        ActedOnBeHalfOfTask other = (ActedOnBeHalfOfTask) object;
        if ((this.idActedOnBeHalfOf == null && other.idActedOnBeHalfOf != null) || (this.idActedOnBeHalfOf != null && !this.idActedOnBeHalfOf.equals(other.idActedOnBeHalfOf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.model.ActedOnBeHalfOfTask[ idActedOnBeHalfOf=" + idActedOnBeHalfOf + " ]";
    }
    
}

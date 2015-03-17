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
@Table(name = "Port")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Port.findAll", query = "SELECT p FROM Port p"),
    @NamedQuery(name = "Port.findByIdPort", query = "SELECT p FROM Port p WHERE p.idPort = :idPort"),
    @NamedQuery(name = "Port.findByName", query = "SELECT p FROM Port p WHERE p.name = :name"),
    @NamedQuery(name = "Port.findByType", query = "SELECT p FROM Port p WHERE p.type = :type"),
    @NamedQuery(name = "Port.findByDescription", query = "SELECT p FROM Port p WHERE p.description = :description")})
public class Port implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPort")
    private Integer idPort;
    @Column(name = "Name")
    private String name;
    @Column(name = "Type")
    private String type;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Task_idTask", referencedColumnName = "idTask")
    @ManyToOne(optional = false)
    private Task taskidTask;

    public Port() {
    }

    public Port(Integer idPort) {
        this.idPort = idPort;
    }

    public Integer getIdPort() {
        return idPort;
    }

    public void setIdPort(Integer idPort) {
        this.idPort = idPort;
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

    public Task getTaskidTask() {
        return taskidTask;
    }

    public void setTaskidTask(Task taskidTask) {
        this.taskidTask = taskidTask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPort != null ? idPort.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Port)) {
            return false;
        }
        Port other = (Port) object;
        if ((this.idPort == null && other.idPort != null) || (this.idPort != null && !this.idPort.equals(other.idPort))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.Port[ idPort=" + idPort + " ]";
    }
    
}

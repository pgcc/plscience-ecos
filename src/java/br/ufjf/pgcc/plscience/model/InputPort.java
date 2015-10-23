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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "InputPort")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InputPort.findAll", query = "SELECT i FROM InputPort i"),
    @NamedQuery(name = "InputPort.findByIdPort", query = "SELECT i FROM InputPort i WHERE i.idPort = :idPort"),
    @NamedQuery(name = "InputPort.findByName", query = "SELECT i FROM InputPort i WHERE i.name = :name"),
    @NamedQuery(name = "InputPort.findByDescription", query = "SELECT i FROM InputPort i WHERE i.description = :description"),
    @NamedQuery(name = "InputPort.findByValue", query = "SELECT i FROM InputPort i WHERE i.value = :value"),
    @NamedQuery(name = "InputPort.findByWf", query = "SELECT i FROM InputPort i WHERE i.wf = :wf")})
public class InputPort implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inputPortidPort")
    private List<ActedOnBehalfOf> actedOnBehalfOfList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPort")
    private Integer idPort;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "Value")
    private String value;
    @Basic(optional = false)
    @Column(name = "Wf")
    private int wf;
    @JoinColumn(name = "Task_idTask", referencedColumnName = "idTask")
    @ManyToOne(optional = false)
    private Task taskidTask;

    public InputPort() {
    }

    public InputPort(Integer idPort) {
        this.idPort = idPort;
    }

    public InputPort(Integer idPort, int wf) {
        this.idPort = idPort;
        this.wf = wf;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getWf() {
        return wf;
    }

    public void setWf(int wf) {
        this.wf = wf;
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
        if (!(object instanceof InputPort)) {
            return false;
        }
        InputPort other = (InputPort) object;
        if ((this.idPort == null && other.idPort != null) || (this.idPort != null && !this.idPort.equals(other.idPort))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.InputPort[ idPort=" + idPort + " ]";
    }

    @XmlTransient
    public List<ActedOnBehalfOf> getActedOnBehalfOfList() {
        return actedOnBehalfOfList;
    }

    public void setActedOnBehalfOfList(List<ActedOnBehalfOf> actedOnBehalfOfList) {
        this.actedOnBehalfOfList = actedOnBehalfOfList;
    }
    
}

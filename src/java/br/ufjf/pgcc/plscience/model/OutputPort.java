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
@Table(name = "OutputPort")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OutputPort.findAll", query = "SELECT o FROM OutputPort o"),
    @NamedQuery(name = "OutputPort.findByIdPort", query = "SELECT o FROM OutputPort o WHERE o.idPort = :idPort"),
    @NamedQuery(name = "OutputPort.findByName", query = "SELECT o FROM OutputPort o WHERE o.name = :name"),
    @NamedQuery(name = "OutputPort.findByDescription", query = "SELECT o FROM OutputPort o WHERE o.description = :description"),
    @NamedQuery(name = "OutputPort.findByValue", query = "SELECT o FROM OutputPort o WHERE o.value = :value"),
    @NamedQuery(name = "OutputPort.findByWf", query = "SELECT o FROM OutputPort o WHERE o.wf = :wf")})
public class OutputPort implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "outputPortidPort")
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

    public OutputPort() {
    }

    public OutputPort(Integer idPort) {
        this.idPort = idPort;
    }

    public OutputPort(Integer idPort, int wf) {
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
        if (!(object instanceof OutputPort)) {
            return false;
        }
        OutputPort other = (OutputPort) object;
        if ((this.idPort == null && other.idPort != null) || (this.idPort != null && !this.idPort.equals(other.idPort))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.OutputPort[ idPort=" + idPort + " ]";
    }

    @XmlTransient
    public List<ActedOnBehalfOf> getActedOnBehalfOfList() {
        return actedOnBehalfOfList;
    }

    public void setActedOnBehalfOfList(List<ActedOnBehalfOf> actedOnBehalfOfList) {
        this.actedOnBehalfOfList = actedOnBehalfOfList;
    }
    
}

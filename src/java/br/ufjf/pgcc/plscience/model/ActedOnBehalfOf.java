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
@Table(name = "ActedOnBehalfOf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActedOnBehalfOf.findAll", query = "SELECT a FROM ActedOnBehalfOf a"),
    @NamedQuery(name = "ActedOnBehalfOf.findByIdActedOnBehalfOf", query = "SELECT a FROM ActedOnBehalfOf a WHERE a.idActedOnBehalfOf = :idActedOnBehalfOf"),
    @NamedQuery(name = "ActedOnBehalfOf.findByDescription", query = "SELECT a FROM ActedOnBehalfOf a WHERE a.description = :description"),
    @NamedQuery(name = "ActedOnBehalfOf.findByWf", query = "SELECT a FROM ActedOnBehalfOf a WHERE a.wf = :wf")})
public class ActedOnBehalfOf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActedOnBehalfOf")
    private Integer idActedOnBehalfOf;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Wf")
    private int wf;
    @JoinColumn(name = "InputPort_idPort", referencedColumnName = "idPort")
    @ManyToOne(optional = false)
    private InputPort inputPortidPort;
    @JoinColumn(name = "OutputPort_idPort", referencedColumnName = "idPort")
    @ManyToOne(optional = false)
    private OutputPort outputPortidPort;

    public ActedOnBehalfOf() {
    }

    public ActedOnBehalfOf(Integer idActedOnBehalfOf) {
        this.idActedOnBehalfOf = idActedOnBehalfOf;
    }

    public ActedOnBehalfOf(Integer idActedOnBehalfOf, int wf) {
        this.idActedOnBehalfOf = idActedOnBehalfOf;
        this.wf = wf;
    }

    public Integer getIdActedOnBehalfOf() {
        return idActedOnBehalfOf;
    }

    public void setIdActedOnBehalfOf(Integer idActedOnBehalfOf) {
        this.idActedOnBehalfOf = idActedOnBehalfOf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWf() {
        return wf;
    }

    public void setWf(int wf) {
        this.wf = wf;
    }

    public InputPort getInputPortidPort() {
        return inputPortidPort;
    }

    public void setInputPortidPort(InputPort inputPortidPort) {
        this.inputPortidPort = inputPortidPort;
    }

    public OutputPort getOutputPortidPort() {
        return outputPortidPort;
    }

    public void setOutputPortidPort(OutputPort outputPortidPort) {
        this.outputPortidPort = outputPortidPort;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActedOnBehalfOf != null ? idActedOnBehalfOf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActedOnBehalfOf)) {
            return false;
        }
        ActedOnBehalfOf other = (ActedOnBehalfOf) object;
        if ((this.idActedOnBehalfOf == null && other.idActedOnBehalfOf != null) || (this.idActedOnBehalfOf != null && !this.idActedOnBehalfOf.equals(other.idActedOnBehalfOf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.ActedOnBehalfOf[ idActedOnBehalfOf=" + idActedOnBehalfOf + " ]";
    }
    
}

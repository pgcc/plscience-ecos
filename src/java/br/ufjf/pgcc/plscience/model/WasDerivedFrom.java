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
@Table(name = "WasDerivedFrom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasDerivedFrom.findAll", query = "SELECT w FROM WasDerivedFrom w"),
    @NamedQuery(name = "WasDerivedFrom.findByIdWasDerivedFrom", query = "SELECT w FROM WasDerivedFrom w WHERE w.idWasDerivedFrom = :idWasDerivedFrom"),
    @NamedQuery(name = "WasDerivedFrom.findByType", query = "SELECT w FROM WasDerivedFrom w WHERE w.type = :type")})
public class WasDerivedFrom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasDerivedFrom")
    private Integer idWasDerivedFrom;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @JoinColumn(name = "DerivedTo", referencedColumnName = "idWorkflow")
    @ManyToOne(optional = false)
    private Workflow derivedTo;
    @JoinColumn(name = "DerivedOf", referencedColumnName = "idWorkflow")
    @ManyToOne(optional = false)
    private Workflow derivedOf;

    public WasDerivedFrom() {
    }

    public WasDerivedFrom(Integer idWasDerivedFrom) {
        this.idWasDerivedFrom = idWasDerivedFrom;
    }

    public WasDerivedFrom(Integer idWasDerivedFrom, String type) {
        this.idWasDerivedFrom = idWasDerivedFrom;
        this.type = type;
    }

    public Integer getIdWasDerivedFrom() {
        return idWasDerivedFrom;
    }

    public void setIdWasDerivedFrom(Integer idWasDerivedFrom) {
        this.idWasDerivedFrom = idWasDerivedFrom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Workflow getDerivedTo() {
        return derivedTo;
    }

    public void setDerivedTo(Workflow derivedTo) {
        this.derivedTo = derivedTo;
    }

    public Workflow getDerivedOf() {
        return derivedOf;
    }

    public void setDerivedOf(Workflow derivedOf) {
        this.derivedOf = derivedOf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasDerivedFrom != null ? idWasDerivedFrom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasDerivedFrom)) {
            return false;
        }
        WasDerivedFrom other = (WasDerivedFrom) object;
        if ((this.idWasDerivedFrom == null && other.idWasDerivedFrom != null) || (this.idWasDerivedFrom != null && !this.idWasDerivedFrom.equals(other.idWasDerivedFrom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.model.WasDerivedFrom[ idWasDerivedFrom=" + idWasDerivedFrom + " ]";
    }
    
}

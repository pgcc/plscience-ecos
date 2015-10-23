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
@Table(name = "WasRevisionOf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasRevisionOf.findAll", query = "SELECT w FROM WasRevisionOf w"),
    @NamedQuery(name = "WasRevisionOf.findByIdWasRevisionOf", query = "SELECT w FROM WasRevisionOf w WHERE w.idWasRevisionOf = :idWasRevisionOf"),
    @NamedQuery(name = "WasRevisionOf.findByType", query = "SELECT w FROM WasRevisionOf w WHERE w.type = :type")})
public class WasRevisionOf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasRevisionOf")
    private Integer idWasRevisionOf;
    @Column(name = "Type")
    private String type;
    @JoinColumn(name = "RevisionOf", referencedColumnName = "idWorkflow")
    @ManyToOne(optional = false)
    private Workflow revisionOf;
    @JoinColumn(name = "RevisionTo", referencedColumnName = "idWorkflow")
    @ManyToOne(optional = false)
    private Workflow revisionTo;

    public WasRevisionOf() {
    }

    public WasRevisionOf(Integer idWasRevisionOf) {
        this.idWasRevisionOf = idWasRevisionOf;
    }

    public Integer getIdWasRevisionOf() {
        return idWasRevisionOf;
    }

    public void setIdWasRevisionOf(Integer idWasRevisionOf) {
        this.idWasRevisionOf = idWasRevisionOf;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Workflow getRevisionOf() {
        return revisionOf;
    }

    public void setRevisionOf(Workflow revisionOf) {
        this.revisionOf = revisionOf;
    }

    public Workflow getRevisionTo() {
        return revisionTo;
    }

    public void setRevisionTo(Workflow revisionTo) {
        this.revisionTo = revisionTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasRevisionOf != null ? idWasRevisionOf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasRevisionOf)) {
            return false;
        }
        WasRevisionOf other = (WasRevisionOf) object;
        if ((this.idWasRevisionOf == null && other.idWasRevisionOf != null) || (this.idWasRevisionOf != null && !this.idWasRevisionOf.equals(other.idWasRevisionOf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.WasRevisionOf[ idWasRevisionOf=" + idWasRevisionOf + " ]";
    }
    
}

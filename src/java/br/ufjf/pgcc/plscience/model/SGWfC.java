/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "SGWfC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SGWfC.findAll", query = "SELECT s FROM SGWfC s"),
    @NamedQuery(name = "SGWfC.findByIdSGWfC", query = "SELECT s FROM SGWfC s WHERE s.idSGWfC = :idSGWfC"),
    @NamedQuery(name = "SGWfC.findByName", query = "SELECT s FROM SGWfC s WHERE s.name = :name"),
    @NamedQuery(name = "SGWfC.findByDescription", query = "SELECT s FROM SGWfC s WHERE s.description = :description")})
public class SGWfC implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSGWfC")
    private Integer idSGWfC;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @OneToMany(mappedBy = "sGWfCidSGWfC")
    private List<Workflow> workflowList;

    public SGWfC() {
    }

    public SGWfC(Integer idSGWfC) {
        this.idSGWfC = idSGWfC;
    }

    public Integer getIdSGWfC() {
        return idSGWfC;
    }

    public void setIdSGWfC(Integer idSGWfC) {
        this.idSGWfC = idSGWfC;
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

    @XmlTransient
    public List<Workflow> getWorkflowList() {
        return workflowList;
    }

    public void setWorkflowList(List<Workflow> workflowList) {
        this.workflowList = workflowList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSGWfC != null ? idSGWfC.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SGWfC)) {
            return false;
        }
        SGWfC other = (SGWfC) object;
        if ((this.idSGWfC == null && other.idSGWfC != null) || (this.idSGWfC != null && !this.idSGWfC.equals(other.idSGWfC))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.SGWfC[ idSGWfC=" + idSGWfC + " ]";
    }
    
}

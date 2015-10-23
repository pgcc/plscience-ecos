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
@Table(name = "IsPartOf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IsPartOf.findAll", query = "SELECT i FROM IsPartOf i"),
    @NamedQuery(name = "IsPartOf.findByIdIsPartOf", query = "SELECT i FROM IsPartOf i WHERE i.idIsPartOf = :idIsPartOf"),
    @NamedQuery(name = "IsPartOf.findByDescription", query = "SELECT i FROM IsPartOf i WHERE i.description = :description")})
public class IsPartOf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idIsPartOf")
    private Integer idIsPartOf;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idAgent")
    @ManyToOne
    private Agent agentidAgent;
    @JoinColumn(name = "ResearchGroup_idResearchGroup", referencedColumnName = "idResearchGroup")
    @ManyToOne
    private ResearchGroup researchGroupidResearchGroup;

    public IsPartOf() {
    }

    public IsPartOf(Integer idIsPartOf) {
        this.idIsPartOf = idIsPartOf;
    }

    public Integer getIdIsPartOf() {
        return idIsPartOf;
    }

    public void setIdIsPartOf(Integer idIsPartOf) {
        this.idIsPartOf = idIsPartOf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Agent getAgentidAgent() {
        return agentidAgent;
    }

    public void setAgentidAgent(Agent agentidAgent) {
        this.agentidAgent = agentidAgent;
    }

    public ResearchGroup getResearchGroupidResearchGroup() {
        return researchGroupidResearchGroup;
    }

    public void setResearchGroupidResearchGroup(ResearchGroup researchGroupidResearchGroup) {
        this.researchGroupidResearchGroup = researchGroupidResearchGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIsPartOf != null ? idIsPartOf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IsPartOf)) {
            return false;
        }
        IsPartOf other = (IsPartOf) object;
        if ((this.idIsPartOf == null && other.idIsPartOf != null) || (this.idIsPartOf != null && !this.idIsPartOf.equals(other.idIsPartOf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.IsPartOf[ idIsPartOf=" + idIsPartOf + " ]";
    }
    
}

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
@Table(name = "WasAssociatedWith")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasAssociatedWith.findAll", query = "SELECT w FROM WasAssociatedWith w"),
    @NamedQuery(name = "WasAssociatedWith.findByIdWasAssociatedWith", query = "SELECT w FROM WasAssociatedWith w WHERE w.idWasAssociatedWith = :idWasAssociatedWith"),
    @NamedQuery(name = "WasAssociatedWith.findByDescription", query = "SELECT w FROM WasAssociatedWith w WHERE w.description = :description")})
public class WasAssociatedWith implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasAssociatedWith")
    private Integer idWasAssociatedWith;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idAgent")
    @ManyToOne
    private Agent agentidAgent;
    @JoinColumn(name = "ResearchGroup_idResearchGroup", referencedColumnName = "idResearchGroup")
    @ManyToOne
    private ResearchGroup researchGroupidResearchGroup;

    public WasAssociatedWith() {
    }

    public WasAssociatedWith(Integer idWasAssociatedWith) {
        this.idWasAssociatedWith = idWasAssociatedWith;
    }

    public Integer getIdWasAssociatedWith() {
        return idWasAssociatedWith;
    }

    public void setIdWasAssociatedWith(Integer idWasAssociatedWith) {
        this.idWasAssociatedWith = idWasAssociatedWith;
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
        hash += (idWasAssociatedWith != null ? idWasAssociatedWith.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasAssociatedWith)) {
            return false;
        }
        WasAssociatedWith other = (WasAssociatedWith) object;
        if ((this.idWasAssociatedWith == null && other.idWasAssociatedWith != null) || (this.idWasAssociatedWith != null && !this.idWasAssociatedWith.equals(other.idWasAssociatedWith))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.WasAssociatedWith[ idWasAssociatedWith=" + idWasAssociatedWith + " ]";
    }
    
}

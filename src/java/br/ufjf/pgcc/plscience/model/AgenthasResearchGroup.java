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
@Table(name = "Agent_has_ResearchGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AgenthasResearchGroup.findAll", query = "SELECT a FROM AgenthasResearchGroup a"),
    @NamedQuery(name = "AgenthasResearchGroup.findByIdAgenthasResearchGroup", query = "SELECT a FROM AgenthasResearchGroup a WHERE a.idAgenthasResearchGroup = :idAgenthasResearchGroup")})
public class AgenthasResearchGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAgent_has_ResearchGroup")
    private Integer idAgenthasResearchGroup;
    @JoinColumn(name = "ResearchGroup_idResearchGroup", referencedColumnName = "idResearchGroup")
    @ManyToOne(optional = false)
    private ResearchGroup researchGroupidResearchGroup;
    @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idAgent")
    @ManyToOne(optional = false)
    private Agent agentidAgent;

    public AgenthasResearchGroup() {
    }

    public AgenthasResearchGroup(Integer idAgenthasResearchGroup) {
        this.idAgenthasResearchGroup = idAgenthasResearchGroup;
    }

    public Integer getIdAgenthasResearchGroup() {
        return idAgenthasResearchGroup;
    }

    public void setIdAgenthasResearchGroup(Integer idAgenthasResearchGroup) {
        this.idAgenthasResearchGroup = idAgenthasResearchGroup;
    }

    public ResearchGroup getResearchGroupidResearchGroup() {
        return researchGroupidResearchGroup;
    }

    public void setResearchGroupidResearchGroup(ResearchGroup researchGroupidResearchGroup) {
        this.researchGroupidResearchGroup = researchGroupidResearchGroup;
    }

    public Agent getAgentidAgent() {
        return agentidAgent;
    }

    public void setAgentidAgent(Agent agentidAgent) {
        this.agentidAgent = agentidAgent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgenthasResearchGroup != null ? idAgenthasResearchGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgenthasResearchGroup)) {
            return false;
        }
        AgenthasResearchGroup other = (AgenthasResearchGroup) object;
        if ((this.idAgenthasResearchGroup == null && other.idAgenthasResearchGroup != null) || (this.idAgenthasResearchGroup != null && !this.idAgenthasResearchGroup.equals(other.idAgenthasResearchGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.model.AgenthasResearchGroup[ idAgenthasResearchGroup=" + idAgenthasResearchGroup + " ]";
    }
    
}

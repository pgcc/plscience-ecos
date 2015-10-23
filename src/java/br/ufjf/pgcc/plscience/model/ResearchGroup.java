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
@Table(name = "ResearchGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResearchGroup.findAll", query = "SELECT r FROM ResearchGroup r"),
    @NamedQuery(name = "ResearchGroup.findByIdResearchGroup", query = "SELECT r FROM ResearchGroup r WHERE r.idResearchGroup = :idResearchGroup"),
    @NamedQuery(name = "ResearchGroup.findByName", query = "SELECT r FROM ResearchGroup r WHERE r.name = :name"),
    @NamedQuery(name = "ResearchGroup.findByDescription", query = "SELECT r FROM ResearchGroup r WHERE r.description = :description")})
public class ResearchGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idResearchGroup")
    private Integer idResearchGroup;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @OneToMany(mappedBy = "researchGroupidResearchGroup")
    private List<IsPartOf> isPartOfList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "researchGroupidResearchGroup")
    private List<WasGeneratedBy> wasGeneratedByList;
    @JoinColumn(name = "Agent_idAgent_chef", referencedColumnName = "idAgent")
    @ManyToOne
    private Agent agentidAgentchef;

    public ResearchGroup() {
    }

    public ResearchGroup(Integer idResearchGroup) {
        this.idResearchGroup = idResearchGroup;
    }

    public ResearchGroup(Integer idResearchGroup, String name) {
        this.idResearchGroup = idResearchGroup;
        this.name = name;
    }

    public Integer getIdResearchGroup() {
        return idResearchGroup;
    }

    public void setIdResearchGroup(Integer idResearchGroup) {
        this.idResearchGroup = idResearchGroup;
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
    public List<IsPartOf> getIsPartOfList() {
        return isPartOfList;
    }

    public void setIsPartOfList(List<IsPartOf> isPartOfList) {
        this.isPartOfList = isPartOfList;
    }

    @XmlTransient
    public List<WasGeneratedBy> getWasGeneratedByList() {
        return wasGeneratedByList;
    }

    public void setWasGeneratedByList(List<WasGeneratedBy> wasGeneratedByList) {
        this.wasGeneratedByList = wasGeneratedByList;
    }

    public Agent getAgentidAgentchef() {
        return agentidAgentchef;
    }

    public void setAgentidAgentchef(Agent agentidAgentchef) {
        this.agentidAgentchef = agentidAgentchef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResearchGroup != null ? idResearchGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResearchGroup)) {
            return false;
        }
        ResearchGroup other = (ResearchGroup) object;
        if ((this.idResearchGroup == null && other.idResearchGroup != null) || (this.idResearchGroup != null && !this.idResearchGroup.equals(other.idResearchGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.ResearchGroup[ idResearchGroup=" + idResearchGroup + " ]";
    }
    
}

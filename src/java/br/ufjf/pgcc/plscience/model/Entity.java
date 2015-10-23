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
@javax.persistence.Entity
@Table(name = "Entity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entity.findAll", query = "SELECT e FROM Entity e"),
    @NamedQuery(name = "Entity.findByIdEntity", query = "SELECT e FROM Entity e WHERE e.idEntity = :idEntity"),
    @NamedQuery(name = "Entity.findByName", query = "SELECT e FROM Entity e WHERE e.name = :name"),
    @NamedQuery(name = "Entity.findByAcronym", query = "SELECT e FROM Entity e WHERE e.acronym = :acronym"),
    @NamedQuery(name = "Entity.findByDescription", query = "SELECT e FROM Entity e WHERE e.description = :description")})
public class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEntity")
    private Integer idEntity;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Acronym")
    private String acronym;
    @Column(name = "Description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institution")
    private List<Agent> agentList;
    @OneToMany(mappedBy = "entityidEntity")
    private List<Experiment> experimentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityidEntity")
    private List<Activity> activityList;

    public Entity() {
    }

    public Entity(Integer idEntity) {
        this.idEntity = idEntity;
    }

    public Entity(Integer idEntity, String name) {
        this.idEntity = idEntity;
        this.name = name;
    }

    public Integer getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(Integer idEntity) {
        this.idEntity = idEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Agent> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<Agent> agentList) {
        this.agentList = agentList;
    }

    @XmlTransient
    public List<Experiment> getExperimentList() {
        return experimentList;
    }

    public void setExperimentList(List<Experiment> experimentList) {
        this.experimentList = experimentList;
    }

    @XmlTransient
    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntity != null ? idEntity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entity)) {
            return false;
        }
        Entity other = (Entity) object;
        if ((this.idEntity == null && other.idEntity != null) || (this.idEntity != null && !this.idEntity.equals(other.idEntity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.Entity[ idEntity=" + idEntity + " ]";
    }
    
}

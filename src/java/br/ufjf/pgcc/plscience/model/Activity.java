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
@Table(name = "Activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findByIdActivity", query = "SELECT a FROM Activity a WHERE a.idActivity = :idActivity"),
    @NamedQuery(name = "Activity.findByName", query = "SELECT a FROM Activity a WHERE a.name = :name"),
    @NamedQuery(name = "Activity.findByFunction", query = "SELECT a FROM Activity a WHERE a.function = :function"),
    @NamedQuery(name = "Activity.findByDescription", query = "SELECT a FROM Activity a WHERE a.description = :description")})
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActivity")
    private Integer idActivity;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Function")
    private String function;
    @Column(name = "Description")
    private String description;
    @OneToMany(mappedBy = "activityidActivity")
    private List<Experiment> experimentList;
    @JoinColumn(name = "Entity_idEntity", referencedColumnName = "idEntity")
    @ManyToOne(optional = false)
    private br.ufjf.pgcc.plscience.model.Entity entityidEntity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityidActivity")
    private List<WasControledBy> wasControledByList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityidActivity")
    private List<Used> usedList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityidActivity")
    private List<WasEndedBy> wasEndedByList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityidActivity")
    private List<WasStartedBy> wasStartedByList;

    public Activity() {
    }

    public Activity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public Activity(Integer idActivity, String name) {
        this.idActivity = idActivity;
        this.name = name;
    }

    public Integer getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Experiment> getExperimentList() {
        return experimentList;
    }

    public void setExperimentList(List<Experiment> experimentList) {
        this.experimentList = experimentList;
    }

    public br.ufjf.pgcc.plscience.model.Entity getEntityidEntity() {
        return entityidEntity;
    }

    public void setEntityidEntity(br.ufjf.pgcc.plscience.model.Entity entityidEntity) {
        this.entityidEntity = entityidEntity;
    }

    @XmlTransient
    public List<WasControledBy> getWasControledByList() {
        return wasControledByList;
    }

    public void setWasControledByList(List<WasControledBy> wasControledByList) {
        this.wasControledByList = wasControledByList;
    }

    @XmlTransient
    public List<Used> getUsedList() {
        return usedList;
    }

    public void setUsedList(List<Used> usedList) {
        this.usedList = usedList;
    }

    @XmlTransient
    public List<WasEndedBy> getWasEndedByList() {
        return wasEndedByList;
    }

    public void setWasEndedByList(List<WasEndedBy> wasEndedByList) {
        this.wasEndedByList = wasEndedByList;
    }

    @XmlTransient
    public List<WasStartedBy> getWasStartedByList() {
        return wasStartedByList;
    }

    public void setWasStartedByList(List<WasStartedBy> wasStartedByList) {
        this.wasStartedByList = wasStartedByList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivity != null ? idActivity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.idActivity == null && other.idActivity != null) || (this.idActivity != null && !this.idActivity.equals(other.idActivity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.Activity[ idActivity=" + idActivity + " ]";
    }
    
}

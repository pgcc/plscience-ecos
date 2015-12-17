/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nenc
 */
@Entity
@Table(name = "group_formation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupFormation.findAll", query = "SELECT g FROM GroupFormation g"),
    @NamedQuery(name = "GroupFormation.findById", query = "SELECT g FROM GroupFormation g WHERE g.id = :id"),
    @NamedQuery(name = "GroupFormation.findByGroupName", query = "SELECT g FROM GroupFormation g WHERE g.groupName = :groupName")})
public class GroupFormation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "group_name")
    private String groupName;
    
    @Column(name = "description")
    private String description;
        
    @JoinTable(name = "group_scientist", joinColumns = {
        @JoinColumn(name = "group_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "scientist_id", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Scientist> scientistCollection;

    public GroupFormation() {
    }

    public GroupFormation(Long id) {
        this.id = id;
    }

    public GroupFormation(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Scientist> getScientistCollection() {
        return scientistCollection;
    }

    public void setScientistCollection(Collection<Scientist> scientistCollection) {
        this.scientistCollection = scientistCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupFormation)) {
            return false;
        }
        GroupFormation other = (GroupFormation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.GroupFormation[ id=" + id + " ]";
    }

}

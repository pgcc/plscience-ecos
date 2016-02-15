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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Martins
 */
@Entity
@Table(name = "group_user")
@XmlRootElement
public class GroupUser implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "group_name")
    private String groupName;
    
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "owner_id", referencedColumnName = "idAgent")
    @ManyToOne(optional = false)
    private Agent owner;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "group_participant", 
        joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")}, 
        inverseJoinColumns = {@JoinColumn(name = "participant_id", referencedColumnName = "id")})    
    private List<Agent> groupParticipantList;
    
    public GroupUser() {
    }

    public GroupUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    
    /**
     * @return the owner
     */
    public Agent getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Agent owner) {
        this.owner = owner;
    }

    /**
     * @return the groupParticipantList
     */
    public List<Agent> getGroupParticipantList() {
        return groupParticipantList;
    }

    /**
     * @param groupParticipantList the groupParticipantList to set
     */
    public void setGroupParticipantList(List<Agent> groupParticipantList) {
        this.groupParticipantList = groupParticipantList;
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
        if (!(object instanceof GroupUser)) {
            return false;
        }
        GroupUser other = (GroupUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.Group1[ id=" + id + " ]";
    }

}

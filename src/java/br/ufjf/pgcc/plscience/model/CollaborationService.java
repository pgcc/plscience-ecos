/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "collaboration_service")
public class CollaborationService implements Serializable {
        
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "collab_service_name")
    private String collabServiceName;
    
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "collaborative_service_type_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private CollaborativeServiceType collaborativeServiceTypeId;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "steps_service", 
        joinColumns = {@JoinColumn(name = "collab_service_id", referencedColumnName = "id")}, 
        inverseJoinColumns = {@JoinColumn(name = "step_id", referencedColumnName = "id")})    
    private List<StepsScientificExperimentation> stepsScientificExperimentationList;
    
    @JoinColumn(name = "communication_service_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private CommunicationService communicationServiceId;
    
    @JoinColumn(name = "cooperation_service_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private CooperationService cooperationServiceId;
    
    @JoinColumn(name = "coordination_service_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private CoordinationService coordinationServiceId;
    
    @JoinColumn(name = "group_service_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private GroupService groupServiceId;
    
    @Basic(optional = false)
    @Column(name = "developed")
    private boolean developed;
    
    public CollaborationService() {
    }

    public CollaborationService(Long id) {
        this.id = id;
    }

    public CollaborationService(Long id, String collabServiceName) {
        this.id = id;
        this.collabServiceName = collabServiceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollabServiceName() {
        return collabServiceName;
    }

    public void setCollabServiceName(String collabServiceName) {
        this.collabServiceName = collabServiceName;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public CollaborativeServiceType getCollaborativeServiceType() {
        return collaborativeServiceTypeId;
    }

    public void setCollaborativeServiceType(CollaborativeServiceType collaborativeServiceTypeId) {
        this.collaborativeServiceTypeId = collaborativeServiceTypeId;
    }
    
    @XmlTransient
    public List<StepsScientificExperimentation> getStepsScientificExperimentationList() {
        return stepsScientificExperimentationList;
    }

    public void setStepsScientificExperimentationList(List<StepsScientificExperimentation> stepsScientificExperimentationList) {
        this.stepsScientificExperimentationList = stepsScientificExperimentationList;
    }

    public CommunicationService getCommunicationServiceId() {
        return communicationServiceId;
    }

    public void setCommunicationServiceId(CommunicationService communicationServiceId) {
        this.communicationServiceId = communicationServiceId;
    }

    public CooperationService getCooperationServiceId() {
        return cooperationServiceId;
    }

    public void setCooperationServiceId(CooperationService cooperationServiceId) {
        this.cooperationServiceId = cooperationServiceId;
    }

    public CoordinationService getCoordinationServiceId() {
        return coordinationServiceId;
    }

    public void setCoordinationServiceId(CoordinationService coordinationServiceId) {
        this.coordinationServiceId = coordinationServiceId;
    }

    public GroupService getGroupServiceId() {
        return groupServiceId;
    }

    public void setGroupServiceId(GroupService groupServiceId) {
        this.groupServiceId = groupServiceId;
    }

    /**
     * @return the developed
     */
    public boolean isDeveloped() {
        return developed;
    }

    /**
     * @param developed the developed to set
     */
    public void setDeveloped(boolean developed) {
        this.developed = developed;
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
        if (!(object instanceof CollaborationService)) {
            return false;
        }
        CollaborationService other = (CollaborationService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.CollaborationService[ id=" + id + " ]";
    }

}

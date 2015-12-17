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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "group_service")
@XmlRootElement
public class GroupService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "participant")
    private boolean participant;
    
    @Basic(optional = false)
    @Column(name = "belief")
    private boolean belief;
    
    @Basic(optional = false)
    @Column(name = "confidence")
    private boolean confidence;
    
    @Basic(optional = false)
    @Column(name = "motivation")
    private boolean motivation;
    
    @Basic(optional = false)
    @Column(name = "groupp")
    private boolean groupp;
    
    @Basic(optional = false)
    @Column(name = "competence")
    private boolean competence;
    
    @Basic(optional = false)
    @Column(name = "goal")
    private boolean goal;
    
    @JoinTable(name = "competence_group_service", joinColumns = {
        @JoinColumn(name = "group_service_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "competence_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Competence> competenceList;
        
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "groupServiceId")
    private CollaborationService collaborationService;

    public GroupService() {
    }

    public GroupService(Long id) {
        this.id = id;
    }

    public GroupService(Long id, boolean participant, boolean belief, boolean confidence, boolean motivation, boolean groupp, boolean competence, boolean goal) {
        this.id = id;
        this.participant = participant;
        this.belief = belief;
        this.confidence = confidence;
        this.motivation = motivation;
        this.groupp = groupp;
        this.competence = competence;
        this.goal = goal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getParticipant() {
        return participant;
    }

    public void setParticipant(boolean participant) {
        this.participant = participant;
    }

    public boolean getBelief() {
        return belief;
    }

    public void setBelief(boolean belief) {
        this.belief = belief;
    }

    public boolean getConfidence() {
        return confidence;
    }

    public void setConfidence(boolean confidence) {
        this.confidence = confidence;
    }

    public boolean getMotivation() {
        return motivation;
    }

    public void setMotivation(boolean motivation) {
        this.motivation = motivation;
    }

    public boolean getGroupp() {
        return groupp;
    }

    public void setGroupp(boolean groupp) {
        this.groupp = groupp;
    }

    public boolean getCompetence() {
        return competence;
    }

    public void setCompetence(boolean competence) {
        this.competence = competence;
    }

    public boolean getGoal() {
        return goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    @XmlTransient
    public List<Competence> getCompetenceList() {
        return competenceList;
    }

    public void setCompetenceList(List<Competence> competenceList) {
        this.competenceList = competenceList;
    }
        
    @XmlTransient
    public CollaborationService getCollaborationService() {
        return collaborationService;
    }

    public void setCollaborationService(CollaborationService collaborationService) {
        this.collaborationService = collaborationService;
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
        if (!(object instanceof GroupService)) {
            return false;
        }
        GroupService other = (GroupService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.GroupService[ id=" + id + " ]";
    }
    
}

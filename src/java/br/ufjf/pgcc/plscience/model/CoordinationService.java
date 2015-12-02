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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "coordination_service")
@XmlRootElement
public class CoordinationService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "workPlan")
    private boolean workPlan;
    
    @Basic(optional = false)
    @Column(name = "deadline")
    private boolean deadline;
    
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    
    @Basic(optional = false)
    @Column(name = "role")
    private boolean role;
    
    @Basic(optional = false)
    @Column(name = "policy")
    private boolean policy;
   
    @Basic(optional = false)
    @Column(name = "monitoring")
    private boolean monitoring;
    
    @Basic(optional = false)
    @Column(name = "coupling")
    private boolean coupling;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "coordinationServiceId")
    private CollaborationService collaborationService;

    @JoinTable(name = "role_coordination_service", joinColumns = {
        @JoinColumn(name = "coordination_service_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Roler> rolerList;
    
    public CoordinationService() {
    }

    public CoordinationService(Long id) {
        this.id = id;
    }

    public CoordinationService(Long id, boolean workPlan, boolean deadline, boolean status, boolean role, boolean policy, boolean monitoring, boolean coupling) {
        this.id = id;
        this.workPlan = workPlan;
        this.deadline = deadline;
        this.status = status;
        this.role = role;
        this.policy = policy;
        this.monitoring = monitoring;
        this.coupling = coupling;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(boolean workPlan) {
        this.workPlan = workPlan;
    }

    public boolean getDeadline() {
        return deadline;
    }

    public void setDeadline(boolean deadline) {
        this.deadline = deadline;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean getPolicy() {
        return policy;
    }

    public void setPolicy(boolean policy) {
        this.policy = policy;
    }

    public boolean getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(boolean monitoring) {
        this.monitoring = monitoring;
    }

    public boolean getCoupling() {
        return coupling;
    }

    public void setCoupling(boolean coupling) {
        this.coupling = coupling;
    }
    
    @XmlTransient
    public List<Roler> getRolerList() {
        return rolerList;
    }

    public void setRolerList(List<Roler> rolerList) {
        this.rolerList = rolerList;
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
        if (!(object instanceof CoordinationService)) {
            return false;
        }
        CoordinationService other = (CoordinationService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.CoordinationService[ id=" + id + " ]";
    }
 
}

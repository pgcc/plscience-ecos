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
@Table(name = "Agent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agent.findAll", query = "SELECT a FROM Agent a"),
    @NamedQuery(name = "Agent.findByIdAgent", query = "SELECT a FROM Agent a WHERE a.idAgent = :idAgent"),
    @NamedQuery(name = "Agent.findByLogin", query = "SELECT a FROM Agent a WHERE a.login = :login"),
    @NamedQuery(name = "Agent.findByEmail", query = "SELECT a FROM Agent a WHERE a.email = :email"),
    @NamedQuery(name = "Agent.findByPassword", query = "SELECT a FROM Agent a WHERE a.password = :password"),
    @NamedQuery(name = "Agent.findByName", query = "SELECT a FROM Agent a WHERE a.name = :name"),
    @NamedQuery(name = "Agent.findByRoleName", query = "SELECT a FROM Agent a WHERE a.role.roleName = :role"),
    @NamedQuery(name = "Agent.findByDescription", query = "SELECT a FROM Agent a WHERE a.description = :description")})
public class Agent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAgent")
    private Integer idAgent;
    @Basic(optional = false)
    @Column(name = "Login")
    private String login;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "dblp_name")
    private String dblpName;
    @Basic(optional = false)
    @Column(name = "scholar_name")
    private String scholarName;
    

    @Column(name = "Description")
    private String description;

    @Column(name = "is_logged")
    private boolean isLogged;
    
    @Column(name = "picture_url")
    private String picture;
    
    @JoinColumn(name = "Institution", referencedColumnName = "idEntity")
    @ManyToOne(optional = false)
    private br.ufjf.pgcc.plscience.model.Entity institution;
    @OneToMany(mappedBy = "idAgent")
    private List<Experiment> experimentList;
    @OneToMany(mappedBy = "agentidAgent")
    private List<IsPartOf> isPartOfList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agentidAgent")
    private List<WasControledBy> wasControledByList;
    @OneToMany(mappedBy = "agentidAgentchef")
    private List<ResearchGroup> researchGroupList;

    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private br.ufjf.pgcc.plscience.model.Roler role;
    
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne()
    private br.ufjf.pgcc.plscience.model.Status status;
    
    @JoinColumn(name = "competence_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private br.ufjf.pgcc.plscience.model.Competence competence;
    
    @Basic(optional = false)
    @Column(name = "local_agent",  columnDefinition = "boolean default true")
    private boolean local;
    
    public Agent() {
    }

    public Agent(Integer idAgent) {
        this.idAgent = idAgent;
    }

    public Agent(Integer idAgent, String login, String email, String password, String name) {
        this.idAgent = idAgent;
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Integer getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(Integer idAgent) {
        this.idAgent = idAgent;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public br.ufjf.pgcc.plscience.model.Entity getInstitution() {
        return institution;
    }

    public void setInstitution(br.ufjf.pgcc.plscience.model.Entity institution) {
        this.institution = institution;
    }
    
    public String getScholarName() {
        return this.scholarName;
    }

    public void setScholarName(String scholarName) {
        this.scholarName = scholarName;
    }
    
    public String getDblpName() {
        return this.dblpName;
    }

    public void setDblpName(String dblpName) {
        this.dblpName = dblpName;
    }

    /**
     * @return the role
     */
    public br.ufjf.pgcc.plscience.model.Roler getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(br.ufjf.pgcc.plscience.model.Roler role) {
        this.role = role;
    }

    /**
     * @return the status
     */
    public br.ufjf.pgcc.plscience.model.Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(br.ufjf.pgcc.plscience.model.Status status) {
        this.status = status;
    }

    /**
     * @return the competence
     */
    public br.ufjf.pgcc.plscience.model.Competence getCompetence() {
        return competence;
    }

    /**
     * @param competence the competence to set
     */
    public void setCompetence(br.ufjf.pgcc.plscience.model.Competence competence) {
        this.competence = competence;
    }
    
    @XmlTransient
    public List<Experiment> getExperimentList() {
        return experimentList;
    }

    public void setExperimentList(List<Experiment> experimentList) {
        this.experimentList = experimentList;
    }

    @XmlTransient
    public List<IsPartOf> getIsPartOfList() {
        return isPartOfList;
    }

    public void setIsPartOfList(List<IsPartOf> isPartOfList) {
        this.isPartOfList = isPartOfList;
    }

    @XmlTransient
    public List<WasControledBy> getWasControledByList() {
        return wasControledByList;
    }

    public void setWasControledByList(List<WasControledBy> wasControledByList) {
        this.wasControledByList = wasControledByList;
    }

    @XmlTransient
    public List<ResearchGroup> getResearchGroupList() {
        return researchGroupList;
    }

    public void setResearchGroupList(List<ResearchGroup> researchGroupList) {
        this.researchGroupList = researchGroupList;
    }
    
    /**
     * @return the local
     */
    public boolean isLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(boolean local) {
        this.local = local;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgent != null ? idAgent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agent)) {
            return false;
        }
        Agent other = (Agent) object;
        if ((this.idAgent == null && other.idAgent != null) || (this.idAgent != null && !this.idAgent.equals(other.idAgent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.Agent[ idAgent=" + idAgent + " ]";
    }

    /**
     * @return the loggedIn
     */
    public boolean getLoggedIn() {
        return isLogged;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.isLogged = loggedIn;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
}

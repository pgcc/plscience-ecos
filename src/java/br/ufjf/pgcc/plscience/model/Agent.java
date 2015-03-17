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
    @NamedQuery(name = "Agent.findByFunction", query = "SELECT a FROM Agent a WHERE a.function = :function"),
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
    @Column(name = "Function")
    private String function;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Institution", referencedColumnName = "idEntity")
    @ManyToOne(optional = false)
    private br.ufjf.pgcc.plscience.model.Entity institution;
    @OneToMany(mappedBy = "agentidAgent")
    private List<WasAssociatedWith> wasAssociatedWithList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agentidAgent")
    private List<WasControledBy> wasControledByList;
    @OneToMany(mappedBy = "agentidAgentchef")
    private List<ResearchGroup> researchGroupList;

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

    public br.ufjf.pgcc.plscience.model.Entity getInstitution() {
        return institution;
    }

    public void setInstitution(br.ufjf.pgcc.plscience.model.Entity institution) {
        this.institution = institution;
    }

    @XmlTransient
    public List<WasAssociatedWith> getWasAssociatedWithList() {
        return wasAssociatedWithList;
    }

    public void setWasAssociatedWithList(List<WasAssociatedWith> wasAssociatedWithList) {
        this.wasAssociatedWithList = wasAssociatedWithList;
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
    
}

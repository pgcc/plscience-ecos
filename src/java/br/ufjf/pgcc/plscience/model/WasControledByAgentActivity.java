/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tassio
 */
@Entity
@Table(name = "WasControledBy_Agent_Activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WasControledByAgentActivity.findAll", query = "SELECT w FROM WasControledByAgentActivity w"),
    @NamedQuery(name = "WasControledByAgentActivity.findByIdWasControledBy", query = "SELECT w FROM WasControledByAgentActivity w WHERE w.idWasControledBy = :idWasControledBy"),
    @NamedQuery(name = "WasControledByAgentActivity.findByDescription", query = "SELECT w FROM WasControledByAgentActivity w WHERE w.description = :description")})
public class WasControledByAgentActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWasControledBy")
    private Integer idWasControledBy;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idAgent")
    @ManyToOne(optional = false)
    private Agent agentidAgent;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity activityidActivity;

    public WasControledByAgentActivity() {
    }

    public WasControledByAgentActivity(Integer idWasControledBy) {
        this.idWasControledBy = idWasControledBy;
    }

    public Integer getIdWasControledBy() {
        return idWasControledBy;
    }

    public void setIdWasControledBy(Integer idWasControledBy) {
        this.idWasControledBy = idWasControledBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Agent getAgentidAgent() {
        return agentidAgent;
    }

    public void setAgentidAgent(Agent agentidAgent) {
        this.agentidAgent = agentidAgent;
    }

    public Activity getActivityidActivity() {
        return activityidActivity;
    }

    public void setActivityidActivity(Activity activityidActivity) {
        this.activityidActivity = activityidActivity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWasControledBy != null ? idWasControledBy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WasControledByAgentActivity)) {
            return false;
        }
        WasControledByAgentActivity other = (WasControledByAgentActivity) object;
        if ((this.idWasControledBy == null && other.idWasControledBy != null) || (this.idWasControledBy != null && !this.idWasControledBy.equals(other.idWasControledBy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.model.WasControledByAgentActivity[ idWasControledBy=" + idWasControledBy + " ]";
    }
    
}

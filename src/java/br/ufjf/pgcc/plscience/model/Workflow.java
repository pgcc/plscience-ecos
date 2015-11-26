/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tassio
 */
@Entity
@Table(name = "Workflow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Workflow.findAll", query = "SELECT w FROM Workflow w"),
    @NamedQuery(name = "Workflow.findByIdWorkflow", query = "SELECT w FROM Workflow w WHERE w.idWorkflow = :idWorkflow"),
    @NamedQuery(name = "Workflow.findByName", query = "SELECT w FROM Workflow w WHERE w.name = :name"),
    @NamedQuery(name = "Workflow.findByDescription", query = "SELECT w FROM Workflow w WHERE w.description = :description"),
    @NamedQuery(name = "Workflow.findByVersion", query = "SELECT w FROM Workflow w WHERE w.version = :version"),
    @NamedQuery(name = "Workflow.findByDateVersion", query = "SELECT w FROM Workflow w WHERE w.dateVersion = :dateVersion"),
    @NamedQuery(name = "Workflow.findByNumberStage", query = "SELECT w FROM Workflow w WHERE w.numberStage = :numberStage"),
    @NamedQuery(name = "Workflow.findByLink", query = "SELECT w FROM Workflow w WHERE w.link = :link")})
public class Workflow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWorkflow")
    private Integer idWorkflow;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Name")
    private String name;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;
    @Size(max = 255)
    @Column(name = "Version")
    private String version;
    @Column(name = "DateVersion")
    @Temporal(TemporalType.DATE)
    private Date dateVersion;
    @Column(name = "NumberStage")
    private Integer numberStage;
    @Size(max = 255)
    @Column(name = "link")
    private String link;
    @JoinColumn(name = "SGWfC_idSGWfC", referencedColumnName = "idSGWfC")
    @ManyToOne
    private SGWfC sGWfCidSGWfC;

    public Workflow() {
    }

    public Workflow(Integer idWorkflow) {
        this.idWorkflow = idWorkflow;
    }

    public Workflow(Integer idWorkflow, String name) {
        this.idWorkflow = idWorkflow;
        this.name = name;
    }

    public Integer getIdWorkflow() {
        return idWorkflow;
    }

    public void setIdWorkflow(Integer idWorkflow) {
        this.idWorkflow = idWorkflow;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getDateVersion() {
        return dateVersion;
    }

    public void setDateVersion(Date dateVersion) {
        this.dateVersion = dateVersion;
    }

    public Integer getNumberStage() {
        return numberStage;
    }

    public void setNumberStage(Integer numberStage) {
        this.numberStage = numberStage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public SGWfC getSGWfCidSGWfC() {
        return sGWfCidSGWfC;
    }

    public void setSGWfCidSGWfC(SGWfC sGWfCidSGWfC) {
        this.sGWfCidSGWfC = sGWfCidSGWfC;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWorkflow != null ? idWorkflow.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Workflow)) {
            return false;
        }
        Workflow other = (Workflow) object;
        if ((this.idWorkflow == null && other.idWorkflow != null) || (this.idWorkflow != null && !this.idWorkflow.equals(other.idWorkflow))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.Workflow[ idWorkflow=" + idWorkflow + " ]";
    }
    
}

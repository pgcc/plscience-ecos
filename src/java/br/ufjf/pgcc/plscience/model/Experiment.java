/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tassio
 */
@Entity
@Table(name = "Experiment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experiment.findAll", query = "SELECT e FROM Experiment e"),
    @NamedQuery(name = "Experiment.findByIdExperiment", query = "SELECT e FROM Experiment e WHERE e.idExperiment = :idExperiment"),
    @NamedQuery(name = "Experiment.findByName", query = "SELECT e FROM Experiment e WHERE e.name = :name"),
    @NamedQuery(name = "Experiment.findByDateStarted", query = "SELECT e FROM Experiment e WHERE e.dateStarted = :dateStarted"),
    @NamedQuery(name = "Experiment.findByDateEnded", query = "SELECT e FROM Experiment e WHERE e.dateEnded = :dateEnded"),
    @NamedQuery(name = "Experiment.findByDescription", query = "SELECT e FROM Experiment e WHERE e.description = :description"),
    @NamedQuery(name = "Experiment.findByVersion", query = "SELECT e FROM Experiment e WHERE e.version = :version"),
    @NamedQuery(name = "Experiment.findByNumberStages", query = "SELECT e FROM Experiment e WHERE e.numberStages = :numberStages"),
    @NamedQuery(name = "Experiment.findByParsifalReview", query = "SELECT e FROM Experiment e WHERE e.parsifalReview = :parsifalReview")})
public class Experiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idExperiment")
    private Integer idExperiment;
    @Column(name = "Name")
    private String name;
    @Column(name = "DateStarted")
    @Temporal(TemporalType.DATE)
    private Date dateStarted;
    @Column(name = "DateEnded")
    @Temporal(TemporalType.DATE)
    private Date dateEnded;
    @Column(name = "Description")
    private String description;
    @Column(name = "Version")
    private String version;
    @Column(name = "number_stages")
    private Integer numberStages;
    @Column(name = "parsifal_review")
    private Integer parsifalReview;
    @OneToMany(mappedBy = "experimentExperiment")
    private List<WasAssociatedWith> wasAssociatedWithList;
    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne
    private Activity activityidActivity;
    @JoinColumn(name = "Entity_idEntity", referencedColumnName = "idEntity")
    @ManyToOne
    private br.ufjf.pgcc.plscience.model.Entity entityidEntity;
    @JoinColumn(name = "idAgent", referencedColumnName = "idAgent")
    @ManyToOne
    private Agent idAgent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experimentExperiment")
    private List<WasGeneratedBy> wasGeneratedByList;

    public Experiment() {
    }

    public Experiment(Integer idExperiment) {
        this.idExperiment = idExperiment;
    }

       /**
     * @return the id
     */
    public Integer getId() {
        return idExperiment;
    }

    
    public Integer getIdExperiment() {
        return idExperiment;
    }

    public void setIdExperiment(Integer idExperiment) {
        this.idExperiment = idExperiment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
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

    public Integer getNumberStages() {
        return numberStages;
    }

    public void setNumberStages(Integer numberStages) {
        this.numberStages = numberStages;
    }

    public Integer getParsifalReview() {
        return parsifalReview;
    }

    public void setParsifalReview(Integer parsifalReview) {
        this.parsifalReview = parsifalReview;
    }

    @XmlTransient
    public List<WasAssociatedWith> getWasAssociatedWithList() {
        return wasAssociatedWithList;
    }

    public void setWasAssociatedWithList(List<WasAssociatedWith> wasAssociatedWithList) {
        this.wasAssociatedWithList = wasAssociatedWithList;
    }

    public Activity getActivityidActivity() {
        return activityidActivity;
    }

    public void setActivityidActivity(Activity activityidActivity) {
        this.activityidActivity = activityidActivity;
    }

    public br.ufjf.pgcc.plscience.model.Entity getEntityidEntity() {
        return entityidEntity;
    }

    public void setEntityidEntity(br.ufjf.pgcc.plscience.model.Entity entityidEntity) {
        this.entityidEntity = entityidEntity;
    }

    public Agent getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(Agent idAgent) {
        this.idAgent = idAgent;
    }

    @XmlTransient
    public List<WasGeneratedBy> getWasGeneratedByList() {
        return wasGeneratedByList;
    }

    public void setWasGeneratedByList(List<WasGeneratedBy> wasGeneratedByList) {
        this.wasGeneratedByList = wasGeneratedByList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExperiment != null ? idExperiment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Experiment)) {
            return false;
        }
        Experiment other = (Experiment) object;
        if ((this.idExperiment == null && other.idExperiment != null) || (this.idExperiment != null && !this.idExperiment.equals(other.idExperiment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.Experiment[ idExperiment=" + idExperiment + " ]";
    }
    
}
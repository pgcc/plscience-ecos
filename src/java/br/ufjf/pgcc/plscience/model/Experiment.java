/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vitorfs
 */
@Entity
@Table(name="Experiment")
public class Experiment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idExperiment")
    private Integer idExperiment;
    
    @Column(name="Name")
    private String name;
    
    @Column(name="Description")
    private String description;
    
    @Column(name = "Version")
    private String version;
    
    @Column(name = "DateStarted")
    @Temporal(TemporalType.DATE)
    private Date dateStarted;
    
    @Column(name = "DateEnded")
    @Temporal(TemporalType.DATE)
    private Date dateEnded;
    
    @ManyToOne
    @JoinColumn(name="scientist_id")
    private Scientist scientist;
    
    @Column(name="parsifal_review")
    private Integer parsifalReview;
    
    @Column(name="number_stages")
    private Integer numberStages;

    @JoinColumn(name = "Activity_idActivity", referencedColumnName = "idActivity")
    @ManyToOne
    private Activity activityidActivity;
    
    @JoinColumn(name = "Entity_idEntity", referencedColumnName = "idEntity")
    @ManyToOne
    private br.ufjf.pgcc.plscience.model.Entity entityidEntity;
    
    @OneToMany(mappedBy = "experimentExperiment")
    private List<WasAttributedTo> wasAttributedToList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experimentExperiment")
    private List<WasGeneratedBy> wasGeneratedByList;
    
    public Experiment() {
    }

    public Experiment(Integer idExperiment) {
        this.idExperiment = idExperiment;
    }

    public Experiment(Integer idExperiment, String name) {
        this.idExperiment = idExperiment;
        this.name = name;
    }

    /**
     * @return the idExperiment
     */
    public Integer getIdExperiment() {
        return idExperiment;
    }

    /**
     * @param idExperiment
     */
    public void setIdExperiment(Integer idExperiment) {
        this.idExperiment = idExperiment;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the title to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return the dateStarted
     */
    public Date getDateStarted() {
        return dateStarted;
    }

    /**
     * @param dateStarted the dateStarted to set
     */
    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    /**
     * @return the dateEnded
     */
    public Date getDateEnded() {
        return dateEnded;
    }

    /**
     * @param dateEnded the dateEnded to set
     */
    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    /**
     * @return the scientist
     */
    public Scientist getScientist() {
        return scientist;
    }

    /**
     * @param scientist the scientist to set
     */
    public void setScientist(Scientist scientist) {
        this.scientist = scientist;
    }

    /**
     * @return the parsifalReview
     */
    public Integer getParsifalReview() {
        return parsifalReview;
    }

    /**
     * @param parsifalReview the parsifalReview to set
     */
    public void setParsifalReview(Integer parsifalReview) {
        this.parsifalReview = parsifalReview;
    }

    public Integer getNumberStages() {
        return numberStages;
    }

    public void setNumberStages(Integer numberStages) {
        this.numberStages = numberStages;
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

    @XmlTransient
    public List<WasAttributedTo> getWasAttributedToList() {
        return wasAttributedToList;
    }

    public void setWasAttributedToList(List<WasAttributedTo> wasAttributedToList) {
        this.wasAttributedToList = wasAttributedToList;
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
        return "br.ufjf.pgcc.plscience.model.Experiment[ experiment=" + idExperiment + " ]";
    }

    
}

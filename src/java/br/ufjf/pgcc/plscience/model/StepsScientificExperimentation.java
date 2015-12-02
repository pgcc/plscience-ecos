/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "steps_scientific_experimentation")
public class StepsScientificExperimentation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "number_step")
    private Integer numberStep;   
    
    @Basic(optional = false)
    @Column(name = "name_step")
    private String nameStep;
    
    @Column(name = "description")
    private String description;
    
    public StepsScientificExperimentation() {
    }

    public StepsScientificExperimentation(Long id) {
        this.id = id;
    }

    public StepsScientificExperimentation(Long id, String nameStep) {
        this.id = id;
        this.nameStep = nameStep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameStep() {
        return nameStep;
    }

    public void setNameStep(String nameStep) {
        this.nameStep = nameStep;
    }

    public Integer getNumberStep() {
        return numberStep;
    }

    public void setNumberStep(Integer numberStep) {
        this.numberStep = numberStep;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof StepsScientificExperimentation)) {
            return false;
        }
        StepsScientificExperimentation other = (StepsScientificExperimentation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.StepsScientificExperimentation[ id=" + id + " ]";
    }   

}

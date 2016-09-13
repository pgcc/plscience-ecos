/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phillipe
 */

@javax.persistence.Entity
@Table(name = "mapping_file")
@XmlRootElement
public class MappingFile implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mapping_file_location")   
    private String mappingFileLocation;    
    @JoinColumn(name = "features_model_id", referencedColumnName = "id")
    @ManyToOne
    private FeaturesModel featuresModelId;
    @JoinColumn(name = "ontology_id", referencedColumnName = "id")
    @ManyToOne
    private Ontology ontologyId;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the mappingFileLocation
     */
    public String getMappingFileLocation() {
        return mappingFileLocation;
    }

    /**
     * @param mappingFileLocation the mappingFileLocation to set
     */
    public void setMappingFileLocation(String mappingFileLocation) {
        this.mappingFileLocation = mappingFileLocation;
    }

    /**
     * @return the featuresModelId
     */
    public FeaturesModel getFeaturesModelId() {
        return featuresModelId;
    }

    /**
     * @param featuresModelId the featuresModelId to set
     */
    public void setFeaturesModelId(FeaturesModel featuresModelId) {
        this.featuresModelId = featuresModelId;
    }

    /**
     * @return the ontologyId
     */
    public Ontology getOntologyId() {
        return ontologyId;
    }

    /**
     * @param ontologyId the ontologyId to set
     */
    public void setOntologyId(Ontology ontologyId) {
        this.ontologyId = ontologyId;
    }
}

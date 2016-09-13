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
@Table(name = "ontology")
@XmlRootElement
public class Ontology implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ontology_format")   
    private String ontologyFormat;
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    @ManyToOne
    private Component componentId;    

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
     * @return the ontologyFormat
     */
    public String getOntologyFormat() {
        return ontologyFormat;
    }

    /**
     * @param ontologyFormat the ontologyFormat to set
     */
    public void setOntologyFormat(String ontologyFormat) {
        this.ontologyFormat = ontologyFormat;
    }

    /**
     * @return the componentId
     */
    public Component getComponentId() {
        return componentId;
    }

    /**
     * @param componentId the componentId to set
     */
    public void setComponentId(Component componentId) {
        this.componentId = componentId;
    }

}

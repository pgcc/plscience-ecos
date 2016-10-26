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
@Table(name = "component_type")
@XmlRootElement
public class ComponentType implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomponent_type")
    private Integer id;    
    @Column(name = "component_type_description")   
    private String componentTypeDescription;
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
     * @return the componentTypeDescription
     */
    public String getComponentTypeDescription() {
        return componentTypeDescription;
    }

    /**
     * @param componentTypeDescription the componentTypeDescription to set
     */
    public void setComponentTypeDescription(String componentTypeDescription) {
        this.componentTypeDescription = componentTypeDescription;
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

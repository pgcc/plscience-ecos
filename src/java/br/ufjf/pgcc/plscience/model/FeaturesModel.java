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
@Table(name = "features_model")
@XmlRootElement
public class FeaturesModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "features_modelling_tool")   
    private String featuresModellingTool;
    @Column(name = "features_model_img_location")   
    private String featuresModelImgLocation;    
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
     * @return the featuresModellingTool
     */
    public String getFeaturesModellingTool() {
        return featuresModellingTool;
    }

    /**
     * @param featuresModellingTool the featuresModellingTool to set
     */
    public void setFeaturesModellingTool(String featuresModellingTool) {
        this.featuresModellingTool = featuresModellingTool;
    }

    /**
     * @return the featuresModelImgLocation
     */
    public String getFeaturesModelImgLocation() {
        return featuresModelImgLocation;
    }

    /**
     * @param featuresModelImgLocation the featuresModelImgLocation to set
     */
    public void setFeaturesModelImgLocation(String featuresModelImgLocation) {
        this.featuresModelImgLocation = featuresModelImgLocation;
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

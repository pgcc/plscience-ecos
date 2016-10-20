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
@Table(name = "component")
@XmlRootElement
public class Component implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "component_name")   
    private String componentName;
    @Column(name = "component_description")   
    private String componentDescription;
    @Column(name = "component_file_location")   
    private String componentFileLocation;
    @Column(name = "component_file_extension_format")   
    private String componentFileExtensionFormat;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne
    private Service serviceId;
    @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idAgent")
    @ManyToOne
    private Agent agentidAgent;    
    @JoinColumn(name = "component_type_id", referencedColumnName = "id")
    @ManyToOne
    private ComponentType componentTypeId;      

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
     * @return the componentName
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * @param componentName the componentName to set
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * @return the componentDescription
     */
    public String getComponentDescription() {
        return componentDescription;
    }

    /**
     * @param componentDescription the componentDescription to set
     */
    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    /**
     * @return the componentFileLocation
     */
    public String getComponentFileLocation() {
        return componentFileLocation;
    }

    /**
     * @param componentFileLocation the componentFileLocation to set
     */
    public void setComponentFileLocation(String componentFileLocation) {
        this.componentFileLocation = componentFileLocation;
    }

    /**
     * @return the componentFileExtensionFormat
     */
    public String getComponentFileExtensionFormat() {
        return componentFileExtensionFormat;
    }

    /**
     * @param componentFileExtensionFormat the componentFileExtensionFormat to set
     */
    public void setComponentFileExtensionFormat(String componentFileExtensionFormat) {
        this.componentFileExtensionFormat = componentFileExtensionFormat;
    }

    /**
     * @return the serviceId
     */
    public Service getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * @return the agentidAgent
     */
    public Agent getAgentidAgent() {
        return agentidAgent;
    }

    /**
     * @param agentidAgent the agentidAgent to set
     */
    public void setAgentidAgent(Agent agentidAgent) {
        this.agentidAgent = agentidAgent;
    }

    /**
     * @return the componentTypeId
     */
    public ComponentType getComponentTypeId() {
        return componentTypeId;
    }

    /**
     * @param componentTypeId the componentTypeId to set
     */
    public void setComponentTypeId(ComponentType componentTypeId) {
        this.componentTypeId = componentTypeId;
    }
}

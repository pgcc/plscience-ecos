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
 * @author phillipe
 */

@Entity
@Table(name = "service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findByIdService", query = "SELECT s FROM Service s WHERE s.id = :id"),
    @NamedQuery(name = "Service.findByName", query = "SELECT s FROM Service s WHERE s.serviceName = :serviceName")})
public class Service implements Serializable{
    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "service_description")
    private String serviceDescription;
    @Column(name = "service_file_location")
    private String serviceFileLocation;
    @Column(name = "service_type")
    private String serviceType;
    @Column(name = "service_category")
    private String serviceCategory;
    @Column(name = "service_documentation_url")
    private String serviceDocumentationUrl;
    @Column(name = "service_license_type")
    private String serviceLicenseType;
    @Column(name = "service_cost")
    private String serviceCost;
    @Column(name = "service_usage_conditions")
    private String serviceUsageConditions;
    @Column(name = "service_contact_info_url")
    private String serviceContactInfoUrl;
    @Column(name = "service_how_to_cite")
    private String serviceHowToCite;
    @Column(name = "service_purpose")
    private String servicePurpose;   
    @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idAgent")
    @ManyToOne
    private Agent agentIdAgent;   

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

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
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the serviceDescription
     */
    public String getServiceDescription() {
        return serviceDescription;
    }

    /**
     * @param serviceDescription the serviceDescription to set
     */
    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    /**
     * @return the serviceFileLocation
     */
    public String getServiceFileLocation() {
        return serviceFileLocation;
    }

    /**
     * @param serviceFileLocation the serviceFileLocation to set
     */
    public void setServiceFileLocation(String serviceFileLocation) {
        this.serviceFileLocation = serviceFileLocation;
    }

    /**
     * @return the serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @return the serviceCategory
     */
    public String getServiceCategory() {
        return serviceCategory;
    }

    /**
     * @param serviceCategory the serviceCategory to set
     */
    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    /**
     * @return the serviceDocumentationUrl
     */
    public String getServiceDocumentationUrl() {
        return serviceDocumentationUrl;
    }

    /**
     * @param serviceDocumentationUrl the serviceDocumentationUrl to set
     */
    public void setServiceDocumentationUrl(String serviceDocumentationUrl) {
        this.serviceDocumentationUrl = serviceDocumentationUrl;
    }

    /**
     * @return the serviceLicenseType
     */
    public String getServiceLicenseType() {
        return serviceLicenseType;
    }

    /**
     * @param serviceLicenseType the serviceLicenseType to set
     */
    public void setServiceLicenseType(String serviceLicenseType) {
        this.serviceLicenseType = serviceLicenseType;
    }

    /**
     * @return the serviceCost
     */
    public String getServiceCost() {
        return serviceCost;
    }

    /**
     * @param serviceCost the serviceCost to set
     */
    public void setServiceCost(String serviceCost) {
        this.serviceCost = serviceCost;
    }

    /**
     * @return the serviceUsageConditions
     */
    public String getServiceUsageConditions() {
        return serviceUsageConditions;
    }

    /**
     * @param serviceUsageConditions the serviceUsageConditions to set
     */
    public void setServiceUsageConditions(String serviceUsageConditions) {
        this.serviceUsageConditions = serviceUsageConditions;
    }

    /**
     * @return the serviceContactInfoUrl
     */
    public String getServiceContactInfoUrl() {
        return serviceContactInfoUrl;
    }

    /**
     * @param serviceContactInfoUrl the serviceContactInfoUrl to set
     */
    public void setServiceContactInfoUrl(String serviceContactInfoUrl) {
        this.serviceContactInfoUrl = serviceContactInfoUrl;
    }

    /**
     * @return the serviceHowToCite
     */
    public String getServiceHowToCite() {
        return serviceHowToCite;
    }

    /**
     * @param serviceHowToCite the serviceHowToCite to set
     */
    public void setServiceHowToCite(String serviceHowToCite) {
        this.serviceHowToCite = serviceHowToCite;
    }

    /**
     * @return the servicePurpose
     */
    public String getServicePurpose() {
        return servicePurpose;
    }

    /**
     * @param servicePurpose the servicePurpose to set
     */
    public void setServicePurpose(String servicePurpose) {
        this.servicePurpose = servicePurpose;
    }

    /**
     * @return the agentIdAgent
     */
    public Agent getAgentIdAgent() {
        return agentIdAgent;
    }

    /**
     * @param agentIdAgent the agentIdAgent to set
     */
    public void setAgentIdAgent(Agent agentIdAgent) {
        this.agentIdAgent = agentIdAgent;
    }
}

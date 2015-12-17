/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.integration;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Martins
 */
@Entity
@Table(name = "concept_xml")
@XmlRootElement(name = "concept")
public class ConceptXML implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_concept_xml")
    private Long idConceptXml;
    
    @Basic(optional = false)
    @Column(name = "service")
    private String service;
    
    @Basic(optional = false)
    @Column(name = "concept_service")
    private String conceptService;
    
    @Column(name = "ratio")
    private Double ratio;
    
    @Basic(optional = false)
    @Column(name = "has_concept")
    private boolean hasConcept;
    
    @Basic(optional = false)
    @Column(name = "validity")
    private boolean validity;
    
    @Column(name = "conceptService1")
    private String conceptService1;
    
    @Column(name = "conceptService2")
    private String conceptService2;

    @JoinColumn(name = "id_struct_xml", referencedColumnName = "id_struct_xml")
    @ManyToOne(cascade = CascadeType.ALL)
    private InteroperabilityStructXML idStructXml;
    
    public Long getIdConceptXml() {
        return idConceptXml;
    }

    public void setIdConceptXml(Long idConceptXml) {
        this.idConceptXml = idConceptXml;
    }
    
    /**
     * @return the service
     */
    @XmlAttribute
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the conceptService
     */
    @XmlAttribute
    public String getConceptService() {
        return conceptService;
    }

    /**
     * @param conceptService the conceptService to set
     */
    public void setConceptService(String conceptService) {
        this.conceptService = conceptService;
    }

    /**
     * @return the ratio
     */
    @XmlAttribute
    public Double getRatio() {
        return ratio;
    }

    /**
     * @param ratio the ratio to set
     */
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    /**
     * @return the hasConcept
     */
    @XmlAttribute
    public boolean isHasConcept() {
        return hasConcept;
    }

    /**
     * @param hasConcept the hasConcept to set
     */
    public void setHasConcept(boolean hasConcept) {
        this.hasConcept = hasConcept;
    }

    /**
     * @return the validity
     */
    @XmlAttribute
    public boolean isValidity() {
        return validity;
    }

    /**
     * @param validity the validity to set
     */
    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    /**
     * @return the conceptService1
     */
    @XmlElement
    public String getConceptService1() {
        return conceptService1;
    }

    /**
     * @param conceptService1 the conceptService1 to set
     */
    public void setConceptService1(String conceptService1) {
        this.conceptService1 = conceptService1;
    }

    /**
     * @return the conceptService2
     */
    @XmlElement
    public String getConceptService2() {
        return conceptService2;
    }

    /**
     * @param conceptService2 the conceptService2 to set
     */
    public void setConceptService2(String conceptService2) {
        this.conceptService2 = conceptService2;
    }
    
    public InteroperabilityStructXML getIdStructXml() {
        return idStructXml;
    }

    public void setIdStructXml(InteroperabilityStructXML idStructXml) {
        this.idStructXml = idStructXml;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConceptXml != null ? idConceptXml.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConceptXML)) {
            return false;
        }
        ConceptXML other = (ConceptXML) object;
        if ((this.idConceptXml == null && other.idConceptXml != null) || (this.idConceptXml != null && !this.idConceptXml.equals(other.idConceptXml))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.integration.ConceptXML[ idConceptXml=" + idConceptXml + " ]";
    }
}

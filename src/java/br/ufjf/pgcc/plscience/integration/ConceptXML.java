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
    @Column(name = "group_concept")
    private String groupConcept;
    
    @Basic(optional = false)
    @Column(name = "concept_service")
    private String conceptService;
    
    @Column(name = "ratio")
    private Double ratio;
    
    @Basic(optional = false)
    @Column(name = "has_element")
    private boolean hasElement;
    
    @Basic(optional = false)
    @Column(name = "validity")
    private boolean validity;
    
    @Column(name = "conceptService1")
    private String conceptService1;
    
    @Column(name = "descriptionService1")
    private String descriptionService1;
    
    @Column(name = "conceptService2")
    private String conceptService2;
    
    @Column(name = "descriptionService2")
    private String descriptionService2;

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
    public String getGroupConcept() {
        return groupConcept;
    }

    /**
     * @param groupConcept the service to set
     */
    public void setGroupConcept(String groupConcept) {
        this.groupConcept = groupConcept;
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
     * @return the hasElement
     */
    @XmlAttribute
    public boolean isHasElement() {
        return hasElement;
    }

    /**
     * @param hasElement the hasConcept to set
     */
    public void setHasElement(boolean hasElement) {
        this.hasElement = hasElement;
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
     * @return the descriptionService1
     */
    @XmlElement
    public String getDescriptionService1() {
        return descriptionService1;
    }

    /**
     * @param descriptionService1 the descriptionService1 to set
     */
    public void setDescriptionService1(String descriptionService1) {
        this.descriptionService1 = descriptionService1;
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
    
    /**
     * @return the descriptionService2
     */
    @XmlElement
    public String getDescriptionService2() {
        return descriptionService2;
    }

    /**
     * @param descriptionService2 the descriptionService2 to set
     */
    public void setDescriptionService2(String descriptionService2) {
        this.descriptionService2 = descriptionService2;
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

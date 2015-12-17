/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.integration;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme Martins
 */
@Entity
@Table(name = "interoperability_struct_xml")
@XmlRootElement(name = "interoperabilityStruct")
public class InteroperabilityStructXML implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_struct_xml")
    private Long idStructXml;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStructXml")
    private List<ConceptXML> concepts = new ArrayList<ConceptXML>();
    
    @Basic(optional = false)
    @Column(name = "interoperability_name")
    private String interoperabilityName;
    
    @Basic(optional = false)
    @Column(name = "first_service_id")
    private Long firstServiceID;
    
    @Basic(optional = false)
    @Column(name = "second_service_id")
    private Long secondServiceID;
    
    @Basic(optional = false)
    @Column(name = "agent_id")
    private Long agentID;
    
    @Basic(optional = false)
    @Column(name = "first_type_service")
    private String firstTypeService;
    
    @Basic(optional = false)
    @Column(name = "second_type_service")
    private String secondTypeService;
 
    public Long getIdStructXml() {
        return idStructXml;
    }

    public void setIdStructXml(Long idStructXml) {
        this.idStructXml = idStructXml;
    }
    
    /**
     * @return the concepts
     */
    @XmlElement(name = "concept")
    public List<ConceptXML> getConcepts() {
        return concepts;
    }

    /**
     * @param concepts the concepts to set
     */
    public void setConcepts(List<ConceptXML> concepts) {
        this.concepts = concepts;
    }

    /**
     * @return the name
     */
    public String getInteroperabilityName() {
        return interoperabilityName;
    }

    /**
     * @param interoperabilityName the name to set
     */
    public void setInteroperabilityName(String interoperabilityName) {
        this.interoperabilityName = interoperabilityName;
    }
    
    /**
     * @return the firstService
     */
    @XmlAttribute
    public Long getFirstServiceID() {
        return firstServiceID;
    }

    /**
     * @param firstServiceID the firstService to set
     */
    public void setFirstServiceID(Long firstServiceID) {
        this.firstServiceID = firstServiceID;
    }

    /**
     * @return the secondService
     */
    @XmlAttribute
    public Long getSecondServiceID() {
        return secondServiceID;
    }

    /**
     * @param secondServiceID the secondService to set
     */
    public void setSecondServiceID(Long secondServiceID) {
        this.secondServiceID = secondServiceID;
    }

    /**
     * @return the agentID
     */
    @XmlAttribute
    public Long getAgentID() {
        return agentID;
    }

    /**
     * @param agentID the agentID to set
     */
    public void setAgentID(Long agentID) {
        this.agentID = agentID;
    }
    
    /**
     * @return the firstTypeService
     */
    @XmlAttribute
    public String getFirstTypeService() {
        return firstTypeService;
    }

    /**
     * @param firstTypeService the firstTypeService to set
     */
    public void setFirstTypeService(String firstTypeService) {
        this.firstTypeService = firstTypeService;
    }

    /**
     * @return the secondTypeService
     */
    @XmlAttribute
    public String getSecondTypeService() {
        return secondTypeService;
    }

    /**
     * @param secondTypeService the secondTypeService to set
     */
    public void setSecondTypeService(String secondTypeService) {
        this.secondTypeService = secondTypeService;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStructXml != null ? idStructXml.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InteroperabilityStructXML)) {
            return false;
        }
        InteroperabilityStructXML other = (InteroperabilityStructXML) object;
        if ((this.idStructXml == null && other.idStructXml != null) || (this.idStructXml != null && !this.idStructXml.equals(other.idStructXml))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.integration.InteroperabilityStructXML[ idStructXml=" + idStructXml + " ]";
    }

}

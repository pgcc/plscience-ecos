/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "communication_service")
@XmlRootElement
public class CommunicationService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "message")
    private boolean message;
    
    @Basic(optional = false)
    @Column(name = "issuer")
    private boolean issuer;
    
    @Basic(optional = false)
    @Column(name = "receiver")
    private boolean receiver;
    
    @Basic(optional = false)
    @Column(name = "communicationProtocol")
    private boolean communicationProtocol;
    
    @Basic(optional = false)
    @Column(name = "commonSense")
    private boolean commonSense;
    
    @Basic(optional = false)
    @Column(name = "synchronism")
    private boolean synchronism;
    
    @Basic(optional = false)
    @Column(name = "transmissionMode")
    private boolean transmissionMode;
    
    @Basic(optional = false)
    @Column(name = "compromise")
    private boolean compromise;
    
    @Basic(optional = false)
    @Column(name = "negotiation")
    private boolean negotiation;
    
    @Basic(optional = false)
    @Column(name = "code")
    private boolean code;
    
    @Basic(optional = false)
    @Column(name = "mode")
    private boolean mode;
    
    @Basic(optional = false)
    @Column(name = "interpretation")
    private boolean interpretation;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "communicationServiceId")
    private CollaborationService collaborationService;

    public CommunicationService() {
    }

    public CommunicationService(Long id) {
        this.id = id;
    }

    public CommunicationService(Long id, boolean message, boolean issuer, boolean receiver, boolean communicationProtocol, boolean commonSense, boolean synchronism, boolean transmissionMode, boolean compromise, boolean negotiation, boolean code, boolean mode, boolean interpretation) {
        this.id = id;
        this.message = message;
        this.issuer = issuer;
        this.receiver = receiver;
        this.communicationProtocol = communicationProtocol;
        this.commonSense = commonSense;
        this.synchronism = synchronism;
        this.transmissionMode = transmissionMode;
        this.compromise = compromise;
        this.negotiation = negotiation;
        this.code = code;
        this.mode = mode;
        this.interpretation = interpretation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }

    public boolean getIssuer() {
        return issuer;
    }

    public void setIssuer(boolean issuer) {
        this.issuer = issuer;
    }

    public boolean getReceiver() {
        return receiver;
    }

    public void setReceiver(boolean receiver) {
        this.receiver = receiver;
    }

    public boolean getCommunicationProtocol() {
        return communicationProtocol;
    }

    public void setCommunicationProtocol(boolean communicationProtocol) {
        this.communicationProtocol = communicationProtocol;
    }

    public boolean getCommonSense() {
        return commonSense;
    }

    public void setCommonSense(boolean commonSense) {
        this.commonSense = commonSense;
    }

    public boolean getSynchronism() {
        return synchronism;
    }

    public void setSynchronism(boolean synchronism) {
        this.synchronism = synchronism;
    }

    public boolean getTransmissionMode() {
        return transmissionMode;
    }

    public void setTransmissionMode(boolean transmissionMode) {
        this.transmissionMode = transmissionMode;
    }

    public boolean getCompromise() {
        return compromise;
    }

    public void setCompromise(boolean compromise) {
        this.compromise = compromise;
    }

    public boolean getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(boolean negotiation) {
        this.negotiation = negotiation;
    }

    public boolean getCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public boolean getMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public boolean getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(boolean interpretation) {
        this.interpretation = interpretation;
    }

    @XmlTransient
    public CollaborationService getCollaborationService() {
        return collaborationService;
    }

    public void setCollaborationService(CollaborationService collaborationService) {
        this.collaborationService = collaborationService;
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
        if (!(object instanceof CommunicationService)) {
            return false;
        }
        CommunicationService other = (CommunicationService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.CommunicationService[ id=" + id + " ]";
    }
    
}

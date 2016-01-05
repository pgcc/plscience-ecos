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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Martins
 */
@Entity
@Table(name = "communication_protocol")
public class CommunicationProtocol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "communication_protocol_name")
    private String communicationProtocolName;
    
    @Column(name = "description")
    private String description;

    public CommunicationProtocol() {
    }

    public CommunicationProtocol(Long id) {
        this.id = id;
    }

    public CommunicationProtocol(Long id, String communicationProtocolName) {
        this.id = id;
        this.communicationProtocolName = communicationProtocolName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommunicationProtocolName() {
        return communicationProtocolName;
    }

    public void setCommunicationProtocolName(String communicationProtocolName) {
        this.communicationProtocolName = communicationProtocolName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof CommunicationProtocol)) {
            return false;
        }
        CommunicationProtocol other = (CommunicationProtocol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.CommunicationProtocol[ id=" + id + " ]";
    }
    
}

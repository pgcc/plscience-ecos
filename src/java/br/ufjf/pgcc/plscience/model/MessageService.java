/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Martins
 */
@Entity
@Table(name = "message_service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageService.findAll", query = "SELECT m FROM MessageService m"),
    @NamedQuery(name = "MessageService.findById", query = "SELECT m FROM MessageService m WHERE m.id = :id"),
    @NamedQuery(name = "MessageService.findByMessage", query = "SELECT m FROM MessageService m WHERE m.message = :message"),
    @NamedQuery(name = "MessageService.findByDateMessage", query = "SELECT m FROM MessageService m WHERE m.dateMessage = :dateMessage")})
public class MessageService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "message")
    private String message;
    
    @Basic(optional = false)
    @Column(name = "date_message")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMessage;

    public MessageService() {
    }

    public MessageService(Long id) {
        this.id = id;
    }

    public MessageService(Long id, String message, Date dateMessage) {
        this.id = id;
        this.message = message;
        this.dateMessage = dateMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
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
        if (!(object instanceof MessageService)) {
            return false;
        }
        MessageService other = (MessageService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufjf.pgcc.plscience.model.MessageService[ id=" + id + " ]";
    }
    
}

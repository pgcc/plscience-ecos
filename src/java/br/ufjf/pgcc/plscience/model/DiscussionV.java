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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phillipe
 */

@javax.persistence.Entity
@Table(name = "discussion_v")
@XmlRootElement
public class DiscussionV implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")   
    private Date createdAt;

    @JoinColumn(name = "created_by", referencedColumnName = "idAgent")
    @ManyToOne
    private Agent createdBy;
  
    @JoinColumn(name = "composition_v_id", referencedColumnName = "id")
    @ManyToOne
    private CompositionV compositionVId;    

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
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the createdBy
     */
    public Agent getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(Agent createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the compositionVId
     */
    public CompositionV getCompositionVId() {
        return compositionVId;
    }

    /**
     * @param compositionVId the compositionVId to set
     */
    public void setCompositionVId(CompositionV compositionVId) {
        this.compositionVId = compositionVId;
    }

}

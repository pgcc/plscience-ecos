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
@Table(name = "message_v")
@XmlRootElement
public class MessageV implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")   
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sended_at")   
    private Date sendedAt;

    @JoinColumn(name = "sender", referencedColumnName = "idAgent")
    @ManyToOne
    private Agent sender;
  
    @JoinColumn(name = "discussion_v_id", referencedColumnName = "id")
    @ManyToOne
    private DiscussionV discussion;
   
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the sendedAt
     */
    public Date getSendedAt() {
        return sendedAt;
    }

    /**
     * @param sendedAt the sendedAt to set
     */
    public void setSendedAt(Date sendedAt) {
        this.sendedAt = sendedAt;
    }

    /**
     * @return the sender
     */
    public Agent getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(Agent sender) {
        this.sender = sender;
    }

    /**
     * @return the discussion
     */
    public DiscussionV getDiscussion() {
        return discussion;
    }

    /**
     * @param discussion the discussion to set
     */
    public void setDiscussion(DiscussionV discussion) {
        this.discussion = discussion;
    }
}

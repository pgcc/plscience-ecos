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
@Table(name = "composition_v_has_Agent")
@XmlRootElement
public class CompositionVHasAgent implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;    
    @JoinColumn(name = "composition_v_id", referencedColumnName = "id")
    @ManyToOne
    private CompositionV compositionId;
    @JoinColumn(name = "Agent_idAgent", referencedColumnName = "idAgent")
    @ManyToOne
    private Agent agentId;

    /**
     * @return the compositionId
     */
    public CompositionV getCompositionId() {
        return compositionId;
    }

    /**
     * @param compositionId the compositionId to set
     */
    public void setCompositionId(CompositionV compositionId) {
        this.compositionId = compositionId;
    }

    /**
     * @return the agentId
     */
    public Agent getAgentId() {
        return agentId;
    }

    /**
     * @param agentId the agentId to set
     */
    public void setAgentId(Agent agentId) {
        this.agentId = agentId;
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
}

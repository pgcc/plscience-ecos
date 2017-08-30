/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

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
@Table(name = "relationship_edge")
@XmlRootElement
public class RelationshipEdge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "weight")
    private String weight;
    @Column(name = "relationship_year")
    private String relationshipYear;
    @Column(name = "relationship_type")
    private String relationshipType;
    @JoinColumn(name = "node_source", referencedColumnName = "id")
    @ManyToOne
    private NodeBD nodeSource;
    @JoinColumn(name = "node_target", referencedColumnName = "id")
    @ManyToOne
    private NodeBD nodeTarget;

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
     * @return the weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return the relationshipYear
     */
    public String getRelationshipYear() {
        return relationshipYear;
    }

    /**
     * @param relationshipYear the relationshipYear to set
     */
    public void setRelationshipYear(String relationshipYear) {
        this.relationshipYear = relationshipYear;
    }

    /**
     * @return the relationshipType
     */
    public String getRelationshipType() {
        return relationshipType;
    }

    /**
     * @param relationshipType the relationshipType to set
     */
    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    /**
     * @return the nodeSource
     */
    public NodeBD getNodeSource() {
        return nodeSource;
    }

    /**
     * @param nodeSource the nodeSource to set
     */
    public void setNodeSource(NodeBD nodeSource) {
        this.nodeSource = nodeSource;
    }

    /**
     * @return the nodeTarget
     */
    public NodeBD getNodeTarget() {
        return nodeTarget;
    }

    /**
     * @param nodeTarget the nodeTarget to set
     */
    public void setNodeTarget(NodeBD nodeTarget) {
        this.nodeTarget = nodeTarget;
    }
}

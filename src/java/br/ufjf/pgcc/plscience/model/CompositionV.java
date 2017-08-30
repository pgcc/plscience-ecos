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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phillipe
 */

@javax.persistence.Entity
@Table(name = "composition_v")
@XmlRootElement
public class CompositionV implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "composition_name")   
    private String compositionName;
    @Column(name = "composition_description")   
    private String compositionDescription;
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
     * @return the compositionName
     */
    public String getCompositionName() {
        return compositionName;
    }

    /**
     * @param compositionName the compositionName to set
     */
    public void setCompositionName(String compositionName) {
        this.compositionName = compositionName;
    }

    /**
     * @return the compositionDescription
     */
    public String getCompositionDescription() {
        return compositionDescription;
    }

    /**
     * @param compositionDescription the compositionDescription to set
     */
    public void setCompositionDescription(String compositionDescription) {
        this.compositionDescription = compositionDescription;
    }
}

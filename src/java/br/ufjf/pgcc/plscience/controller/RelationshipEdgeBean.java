/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.RelationshipEdgeDAO;
import br.ufjf.pgcc.plscience.model.RelationshipEdge;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author phillipe
 */

@ManagedBean(name = "relationshipEdgeBean")
@ViewScoped
public class RelationshipEdgeBean implements Serializable{    
 
    private RelationshipEdge relationshipEdge;
    private List relationshipEdgeList = new ArrayList();

    public RelationshipEdgeBean() {
        relationshipEdgeList = new RelationshipEdgeDAO().getAll();
        relationshipEdge = new RelationshipEdge();
    }
    
    public void record(ActionEvent actionEvent) {
        new RelationshipEdgeDAO().save(getRelationshipEdge());
        setRelationshipEdgeList(new RelationshipEdgeDAO().getAll());
    }        

    /**
     * @return the relationshipEdge
     */
    public RelationshipEdge getRelationshipEdge() {
        return relationshipEdge;
    }

    /**
     * @param relationshipEdge the relationshipEdge to set
     */
    public void setRelationshipEdge(RelationshipEdge relationshipEdge) {
        this.relationshipEdge = relationshipEdge;
    }

    /**
     * @return the relationshipEdgeList
     */
    public List getRelationshipEdgeList() {
        return relationshipEdgeList;
    }

    /**
     * @param relationshipEdgeList the relationshipEdgeList to set
     */
    public void setRelationshipEdgeList(List relationshipEdgeList) {
        this.relationshipEdgeList = relationshipEdgeList;
    }
}

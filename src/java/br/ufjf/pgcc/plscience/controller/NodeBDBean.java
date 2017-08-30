/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.NodeDAO;
import br.ufjf.pgcc.plscience.model.NodeBD;
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

@ManagedBean(name = "nodeBDBean")
@ViewScoped
public class NodeBDBean implements Serializable{    
 
    private NodeBD nodeBD;
    private List nodeBDList = new ArrayList();

    public NodeBDBean() {
        nodeBDList = new NodeDAO().getAll();
        nodeBD = new NodeBD();
    }
    
    public void record(ActionEvent actionEvent) {
        new NodeDAO().save(nodeBD);
        nodeBDList = new NodeDAO().getAll();
    }        

    /**
     * @return the nodeBD
     */
    public NodeBD getNodeBD() {
        return nodeBD;
    }

    /**
     * @param nodeBD the nodeBD to set
     */
    public void setNodeBD(NodeBD nodeBD) {
        this.nodeBD = nodeBD;
    }

    /**
     * @return the nodeBDList
     */
    public List getNodeBDList() {
        return nodeBDList;
    }

    /**
     * @param nodeBDList the nodeBDList to set
     */
    public void setNodeBDList(List nodeBDList) {
        this.nodeBDList = nodeBDList;
    }
}

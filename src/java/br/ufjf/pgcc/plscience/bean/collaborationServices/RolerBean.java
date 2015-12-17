/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.RolerDAO;
import br.ufjf.pgcc.plscience.model.Roler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "rolerBean")
@ViewScoped
public class RolerBean implements Serializable {
    
    private Roler roler = new Roler();
    private List rolers = new ArrayList();
    
    public RolerBean() {
        rolers = new RolerDAO().getAll();
        roler = new Roler();
    }

    /**
     * @return the roler
     */
    public Roler getRoler() {
        return roler;
    }

    /**
     * @param roler the roler to set
     */
    public void setRoler(Roler roler) {
        this.roler = roler;
    }

    /**
     * @return the rolers
     */
    public List getRolers() {
        return rolers;
    }

    /**
     * @param rolers the rolers to set
     */
    public void setRolers(List rolers) {
        this.rolers = rolers;
    }

}

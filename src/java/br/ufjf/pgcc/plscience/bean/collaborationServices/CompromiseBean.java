/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CompromiseDAO;
import br.ufjf.pgcc.plscience.model.Compromise;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "compromiseBean")
@ViewScoped
public class CompromiseBean implements Serializable {
    
    private Compromise compromise = new Compromise();
    private List compromises = new ArrayList();
    
    public CompromiseBean() {
        compromises = new CompromiseDAO().getAll();
        compromise = new Compromise();
    }

    /**
     * @return the compromise
     */
    public Compromise getCompromise() {
        return compromise;
    }

    /**
     * @param compromise the compromise to set
     */
    public void setCompromise(Compromise compromise) {
        this.compromise = compromise;
    }

    /**
     * @return the compromises
     */
    public List getCompromises() {
        return compromises;
    }

    /**
     * @param compromises the compromises to set
     */
    public void setCompromises(List compromises) {
        this.compromises = compromises;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.StatusDAO;
import br.ufjf.pgcc.plscience.model.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "statusBean")
@ViewScoped
public class StatusBean implements Serializable {
    
    private Status status = new Status();
    private List statuss = new ArrayList();
    
    public StatusBean() {
        statuss = new StatusDAO().getAll();
        status = new Status();
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the statuss
     */
    public List getStatuss() {
        return statuss;
    }

    /**
     * @param statuss the statuss to set
     */
    public void setStatuss(List statuss) {
        this.statuss = statuss;
    }

}

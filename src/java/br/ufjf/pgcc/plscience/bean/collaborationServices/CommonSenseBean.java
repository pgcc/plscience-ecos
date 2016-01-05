/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CommonSenseDAO;
import br.ufjf.pgcc.plscience.model.CommonSense;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "commonSenseBean")
@ViewScoped
public class CommonSenseBean implements Serializable {
    
    private CommonSense commonSense = new CommonSense();
    private List commonSenses = new ArrayList();
    
    public CommonSenseBean() {
        commonSenses = new CommonSenseDAO().getAll();
        commonSense = new CommonSense();
    }

    /**
     * @return the commonSense
     */
    public CommonSense getCommonSense() {
        return commonSense;
    }

    /**
     * @param commonSense the commonSense to set
     */
    public void setCommonSense(CommonSense commonSense) {
        this.commonSense = commonSense;
    }

    /**
     * @return the commonSenses
     */
    public List getCommonSenses() {
        return commonSenses;
    }

    /**
     * @param commonSenses the commonSenses to set
     */
    public void setCommonSenses(List commonSenses) {
        this.commonSenses = commonSenses;
    }

}

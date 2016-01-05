/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CommonSenseDAO;
import br.ufjf.pgcc.plscience.model.CommonSense;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean()
@ViewScoped
public class NewCommonSense implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private CommonSense commonSense;

    public NewCommonSense() {
        this.commonSense = new CommonSense();
    }
    
    public void save() {    
        try { 
            getCommonSense().setId(null);
            new CommonSenseDAO().save(getCommonSense()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Common Sense saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
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
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CodeDAO;
import br.ufjf.pgcc.plscience.model.Code;
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
public class NewCode implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Code code;

    public NewCode() {
        this.code = new Code();
    }
    
    public void save() {    
        try { 
            getCode().setId(null);
            new CodeDAO().save(getCode()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Product saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }

    /**
     * @return the code
     */
    public Code getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Code code) {
        this.code = code;
    }
        
}

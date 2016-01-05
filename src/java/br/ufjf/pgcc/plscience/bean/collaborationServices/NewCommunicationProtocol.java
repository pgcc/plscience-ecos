/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CommunicationProtocolDAO;
import br.ufjf.pgcc.plscience.model.CommunicationProtocol;
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
public class NewCommunicationProtocol implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private CommunicationProtocol communicationProtocol;

    public NewCommunicationProtocol() {
        this.communicationProtocol = new CommunicationProtocol();
    }
    
    public void save() {    
        try { 
            getCommunicationProtocol().setId(null);
            new CommunicationProtocolDAO().save(getCommunicationProtocol()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Communication Protocol saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }

    /**
     * @return the communicationProtocol
     */
    public CommunicationProtocol getCommunicationProtocol() {
        return communicationProtocol;
    }

    /**
     * @param communicationProtocol the communicationProtocol to set
     */
    public void setCommunicationProtocol(CommunicationProtocol communicationProtocol) {
        this.communicationProtocol = communicationProtocol;
    }
        
}

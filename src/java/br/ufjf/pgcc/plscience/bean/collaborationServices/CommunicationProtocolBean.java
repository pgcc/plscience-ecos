/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CommunicationProtocolDAO;
import br.ufjf.pgcc.plscience.model.CommunicationProtocol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "communicationProtocolBean")
@ViewScoped
public class CommunicationProtocolBean implements Serializable {
    
    private CommunicationProtocol communicationProtocol = new CommunicationProtocol();
    private List communicationProtocols = new ArrayList();
    
    public CommunicationProtocolBean() {
        communicationProtocols = new CommunicationProtocolDAO().getAll();
        communicationProtocol = new CommunicationProtocol();
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

    /**
     * @return the communicationProtocols
     */
    public List getCommunicationProtocols() {
        return communicationProtocols;
    }

    /**
     * @param communicationProtocols the communicationProtocols to set
     */
    public void setCommunicationProtocols(List communicationProtocols) {
        this.communicationProtocols = communicationProtocols;
    }

}

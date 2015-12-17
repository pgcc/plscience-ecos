/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CollaborativeServiceTypeDAO;
import br.ufjf.pgcc.plscience.model.CollaborativeServiceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "collaborativeServiceTypeBean")
@ViewScoped
public class CollaborativeServiceTypeBean implements Serializable {
    
    private CollaborativeServiceType collaborativeServiceType = new CollaborativeServiceType();    
    private List collaborativeServiceTypes = new ArrayList();

    public CollaborativeServiceTypeBean() {
        collaborativeServiceTypes = new CollaborativeServiceTypeDAO().getAll();
        collaborativeServiceType = new CollaborativeServiceType();
    }
    
    /**
     * @return the collaborativeServiceType
     */
    public CollaborativeServiceType getCollaborativeServiceType() {
        return collaborativeServiceType;
    }

    /**
     * @param collaborativeServiceType the collaborativeServiceType to set
     */
    public void setCollaborativeServiceType(CollaborativeServiceType collaborativeServiceType) {
        this.collaborativeServiceType = collaborativeServiceType;
    }

    /**
     * @return the collaborativeServiceTypes
     */
    public List getCollaborativeServiceTypes() {
        return collaborativeServiceTypes;
    }

    /**
     * @param collaborativeServiceTypes the collaborativeServiceTypes to set
     */
    public void setCollaborativeServiceTypes(List collaborativeServiceTypes) {
        this.collaborativeServiceTypes = collaborativeServiceTypes;
    }
    
    
}

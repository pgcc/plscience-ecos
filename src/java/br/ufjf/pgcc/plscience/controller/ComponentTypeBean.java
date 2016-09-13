/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.ComponentTypeDAO;
import br.ufjf.pgcc.plscience.model.ComponentType;
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

@ManagedBean(name = "componentTypeBean")
@ViewScoped
public class ComponentTypeBean implements Serializable{
    
    private ComponentType componentType = new ComponentType();
    private List componentsTypeList = new ArrayList();    

    public ComponentTypeBean() {
        componentsTypeList = new ComponentTypeDAO().getAll();
        componentType = new ComponentType();
    }   
    
    /**
     * Record a component type
     * @param actionEvent 
     */
    public void record(ActionEvent actionEvent) {
        new ComponentTypeDAO().save(getComponentType());
        setComponentsTypeList(new ComponentTypeDAO().getAll());
    }    
    
    /**
     * @return the componentType
     */
    public ComponentType getComponentType() {
        return componentType;
    }

    /**
     * @param componentType the componentType to set
     */
    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    /**
     * @return the componentsTypeList
     */
    public List getComponentsTypeList() {
        return componentsTypeList;
    }

    /**
     * @param componentsTypeList the componentsTypeList to set
     */
    public void setComponentsTypeList(List componentsTypeList) {
        this.componentsTypeList = componentsTypeList;
    }
    
}

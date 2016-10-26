/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.ComponentDAO;
import br.ufjf.pgcc.plscience.model.Component;
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

@ManagedBean(name = "componentBean")
@ViewScoped
public class ComponentBean implements Serializable{
    
    private Component component = new Component();
    private List componentsList = new ArrayList();
    
    public ComponentBean() {
        componentsList = new ComponentDAO().getAll();
        component = new Component();
    }
    
    /**
     * record a new component on the E-SECO platform repository
     * @param actionEvent 
     */
    public void record(ActionEvent actionEvent) {
        new ComponentDAO().save(getComponent());
        setComponentsList(new ComponentDAO().getAll());
    }

    /**
     * @return the component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(Component component) {
        this.component = component;
    }

    /**
     * @return the componentsList
     */
    public List getComponentsList() {
        return componentsList;
    }

    /**
     * @param componentsList the componentsList to set
     */
    public void setComponentsList(List componentsList) {
        this.componentsList = componentsList;
    }
    
}

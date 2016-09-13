/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.HardwareConfigurationsDAO;
import br.ufjf.pgcc.plscience.model.HardwareConfigurations;
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

@ManagedBean(name = "hardwareConfigurationsBean")
@ViewScoped
public class HardwareConfigurationsBean implements Serializable{
    private HardwareConfigurations hardwareConfigurations;
    private List hardwareConfigurationsList = new ArrayList();
    
    public HardwareConfigurationsBean() {
        hardwareConfigurationsList = new HardwareConfigurationsDAO().getAll();
        hardwareConfigurations = new HardwareConfigurations();
    }
    
    public void record(ActionEvent actionEvent) {
        new HardwareConfigurationsDAO().save(getHardwareConfigurations());
        setHardwareConfigurationsList(new HardwareConfigurationsDAO().getAll());
    }    

    /**
     * @return the hardwareConfigurations
     */
    public HardwareConfigurations getHardwareConfigurations() {
        return hardwareConfigurations;
    }

    /**
     * @param hardwareConfigurations the hardwareConfigurations to set
     */
    public void setHardwareConfigurations(HardwareConfigurations hardwareConfigurations) {
        this.hardwareConfigurations = hardwareConfigurations;
    }

    /**
     * @return the hardwareConfigurationsList
     */
    public List getHardwareConfigurationsList() {
        return hardwareConfigurationsList;
    }

    /**
     * @param hardwareConfigurationsList the hardwareConfigurationsList to set
     */
    public void setHardwareConfigurationsList(List hardwareConfigurationsList) {
        this.hardwareConfigurationsList = hardwareConfigurationsList;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.HardwareConfigurationsDAO;
import br.ufjf.pgcc.plscience.dao.ServiceDAO;
import br.ufjf.pgcc.plscience.model.Service;
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

@ManagedBean(name = "serviceBean")
@ViewScoped
public class ServiceBean implements Serializable{

    private Service service = new Service();
    private List servicesList = new ArrayList();

    public ServiceBean() {
        servicesList = new ServiceDAO().getAll();
        service = new Service();
    }    

    public void record(ActionEvent actionEvent) {
        new ServiceDAO().save(service);
        servicesList = new ServiceDAO().getAll();
    }
    
    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * @return the servicesList
     */
    public List getServicesList() {
        return servicesList;
    }

    /**
     * @param servicesList the servicesList to set
     */
    public void setServicesList(List servicesList) {
        this.servicesList = servicesList;
    }
    
}

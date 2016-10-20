/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author phillipe
 */

@ManagedBean
public class ServiceManagerData implements Serializable{
    private ArrayList<String> servicesName;

    /**
     * @return the servicesName
     */
    public ArrayList<String> getServicesName() {
        return servicesName;
    }

    /**
     * @param servicesName the servicesName to set
     */
    public void setServicesName(ArrayList<String> servicesName) {
        this.servicesName = servicesName;
    }


    
}

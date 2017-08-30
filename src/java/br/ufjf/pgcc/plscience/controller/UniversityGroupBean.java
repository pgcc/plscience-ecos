/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.UniversityGroupDAO;
import br.ufjf.pgcc.plscience.model.UniversityGroup;
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

@ManagedBean(name = "universityGroupBean")
@ViewScoped
public class UniversityGroupBean implements Serializable{    
 
    private UniversityGroup universityGroup;
    private List universityGroupList = new ArrayList();

    public UniversityGroupBean() {
        universityGroupList = new UniversityGroupDAO().getAll();
        universityGroup = new UniversityGroup();
    }
    
    public void record(ActionEvent actionEvent) {
        new UniversityGroupDAO().save(getUniversityGroup());
        setUniversityGroupList(new UniversityGroupDAO().getAll());
    }    

    /**
     * @return the universityGroup
     */
    public UniversityGroup getUniversityGroup() {
        return universityGroup;
    }

    /**
     * @param universityGroup the universityGroup to set
     */
    public void setUniversityGroup(UniversityGroup universityGroup) {
        this.universityGroup = universityGroup;
    }

    /**
     * @return the universityGroupList
     */
    public List getUniversityGroupList() {
        return universityGroupList;
    }

    /**
     * @param universityGroupList the universityGroupList to set
     */
    public void setUniversityGroupList(List universityGroupList) {
        this.universityGroupList = universityGroupList;
    }
    
}

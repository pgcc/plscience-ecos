/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.UniversityDAO;
import br.ufjf.pgcc.plscience.model.University;
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

@ManagedBean(name = "universityBean")
@ViewScoped
public class UniversityBean implements Serializable{    
 
    private University university;
    private List universityList = new ArrayList();

    public UniversityBean() {
        universityList = new UniversityDAO().getAll();
        university = new University();
    }
    
    public void record(ActionEvent actionEvent) {
        new UniversityDAO().save(getUniversity());
        setUniversityList(new UniversityDAO().getAll());
    }    

    /**
     * @return the university
     */
    public University getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(University university) {
        this.university = university;
    }

    /**
     * @return the universityList
     */
    public List getUniversityList() {
        return universityList;
    }

    /**
     * @param universityList the universityList to set
     */
    public void setUniversityList(List universityList) {
        this.universityList = universityList;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.FeaturesModelDAO;
import br.ufjf.pgcc.plscience.model.FeaturesModel;
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

@ManagedBean(name = "featuresModelBean")
@ViewScoped
public class FeaturesModelBean implements Serializable{
    private FeaturesModel featuresModel;
    private List featuresModelList = new ArrayList();

    public FeaturesModelBean() {
        featuresModelList = new FeaturesModelDAO().getAll();
        featuresModel = new FeaturesModel();
    }
    
    /**
     * records a features model (SPL Repository)
     * @param actionEvent 
     */
    public void record(ActionEvent actionEvent) {
        new FeaturesModelDAO().save(featuresModel);
        featuresModelList = new FeaturesModelDAO().getAll();
    }
    
    /**
     * @return the featuresModel
     */
    public FeaturesModel getFeaturesModel() {
        return featuresModel;
    }

    /**
     * @param featuresModel the featuresModel to set
     */
    public void setFeaturesModel(FeaturesModel featuresModel) {
        this.featuresModel = featuresModel;
    }

    /**
     * @return the featuresModelList
     */
    public List getFeaturesModelList() {
        return featuresModelList;
    }

    /**
     * @param featuresModelList the featuresModelList to set
     */
    public void setFeaturesModelList(List featuresModelList) {
        this.featuresModelList = featuresModelList;
    }
}

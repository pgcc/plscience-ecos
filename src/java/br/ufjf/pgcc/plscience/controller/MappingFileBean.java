/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.MappingFileDAO;
import br.ufjf.pgcc.plscience.model.MappingFile;
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

@ManagedBean(name = "mappingFileBean")
@ViewScoped
public class MappingFileBean implements Serializable{
    private MappingFile mappingFile;
    private List mappingFileList = new ArrayList();
    
    public MappingFileBean() {
        mappingFileList = new MappingFileDAO().getAll();
        mappingFile = new MappingFile();
    }

    /**
     * record a mapping file (ontology/features model)
     * @param actionEvent 
     */
    public void record(ActionEvent actionEvent) {
        new MappingFileDAO().save(mappingFile);
        mappingFileList = new MappingFileDAO().getAll();
    }    
    
    /**
     * @return the mappingFile
     */
    public MappingFile getMappingFile() {
        return mappingFile;
    }

    /**
     * @param mappingFile the mappingFile to set
     */
    public void setMappingFile(MappingFile mappingFile) {
        this.mappingFile = mappingFile;
    }

    /**
     * @return the mappingFileList
     */
    public List getMappingFileList() {
        return mappingFileList;
    }

    /**
     * @param mappingFileList the mappingFileList to set
     */
    public void setMappingFileList(List mappingFileList) {
        this.mappingFileList = mappingFileList;
    }
}

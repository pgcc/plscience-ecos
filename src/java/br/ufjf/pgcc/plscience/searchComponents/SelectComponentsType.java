/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author phillipe
 */
@ManagedBean
public class SelectComponentsType {
    
    private String[] selectedComponents;
    private String searchTerm;
    
    public String[] getSelectedComponents(){
        return selectedComponents;
    }
    
    public String selectedComponent(){
        for(String component:selectedComponents){
            return component;
        }
        return null;
    }
 
    public void setSelectedComponents(String[] selectedCities) {
        this.selectedComponents = selectedComponents;
    } 

    /**
     * @return the searchTerm
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * @param searchTerm the searchTerm to set
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

}

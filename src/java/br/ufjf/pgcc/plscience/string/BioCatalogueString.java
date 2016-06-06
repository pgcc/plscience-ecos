/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.string;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author phillipe
 */
public class BioCatalogueString {
    
    /**
     * Method used to format a searchQuery to the BioCatalogue Repository API Format
     * @param searchTerm
     * @return 
     */
    public static String formatSearchTerm(String searchTerm){
        searchTerm = searchTerm.replaceAll(" ","+");
        return searchTerm;
    }
}

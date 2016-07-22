/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.string;

/**
 *
 * @author phillipe
 */
public class ExternalRepositoriesString {
    
    /**
     * Method used to format a searchQuery to the BioCatalogue Repository API
     * and myExperiment Repository API Format
     * @param searchTerm
     * @return 
     */
    public static String formatSearchTerm(String searchTerm){
        searchTerm = searchTerm.replaceAll(" ","+");
        return searchTerm;
    }
}

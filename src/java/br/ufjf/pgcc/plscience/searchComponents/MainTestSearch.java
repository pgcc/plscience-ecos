/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

/**
 *
 * @author phillipe
 */
public class MainTestSearch {
    
    public static void main(String[] args){
        SearchComponents sc = new SearchComponents();
        sc.setSearchQuery("Protein");
        sc.search();
        System.out.println("Primeiro Serviço: "+sc.getResults().get(0).getName());
        System.out.println("Segundo Serviço: "+sc.getResults().get(1).getName());
    }
    
}

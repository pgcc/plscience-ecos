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
        int i = 0;
        SearchComponents sc = new SearchComponents();
        sc.setSearchQuery("Protein");
        sc.search();
        
        
        for(i=0;i<sc.getPatternResults().size();i++){
            System.out.println("Nome do Componente: "+sc.getPatternResults().get(i).getName());
            System.out.println("Tipo de Componente: "+sc.getPatternResults().get(i).getComponentType());
            System.out.println("Repositorio: "+sc.getPatternResults().get(i).getRepositoryName());
        }
    }
    
}

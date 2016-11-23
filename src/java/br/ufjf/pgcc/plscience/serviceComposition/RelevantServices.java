/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

/**
 *
 * @author phillipe
 */
public class RelevantServices {
    
    /**
     * It analyzes the semantic compatibility between two concepts and returns
     * the matchmaking degree
     * @param ConceptA
     * @param ConceptB
     * @return matchmaking degree
     */
    public String SemanticCompatibility(String ConceptA,String ConceptB){
        if(ConceptA.equals(ConceptB))
            return "exact";
        if(ConceptA.contains(ConceptB))
            return "plugin";
        if(ConceptB.contains(ConceptA))
            return "subsume";        
        return "fail";
    }
}

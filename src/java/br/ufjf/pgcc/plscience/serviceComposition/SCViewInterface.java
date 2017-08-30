/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

import br.ufjf.pgcc.plscience.socialNetworkAnalysis.GraphSN;

/**
 *
 * @author phillipe
 */
public interface SCViewInterface {
    
    public void generateGraphServiceComposition(String taskName);
    public void generateGraphSocialNetwork(GraphSN graph);
    
}

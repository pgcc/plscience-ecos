/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.experiments.execution;

import br.ufjf.taverna.core.TavernaClient;
import br.ufjf.taverna.model.run.TavernaRun;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class TavernaWorkflow {

    private final TavernaClient client;
    
    public TavernaWorkflow() {
        client = new TavernaClient();
        client.setBaseUri("http://ec2-54-191-44-161.us-west-2.compute.amazonaws.com:8080/TavernaServer-2.5.4/rest");
        client.setAuthorization("taverna", "taverna");   
    }
    
    public ArrayList<TavernaRun> getRuns() {
        try {
            return client.getRuns();
        } catch (Exception e) {
        }
        return new ArrayList<TavernaRun>();
    }
    
}

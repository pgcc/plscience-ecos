/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.workflow;

import br.ufjf.pgcc.plscience.workflow.model.TavernaExpectedInput;
import br.ufjf.pgcc.plscience.workflow.model.TavernaInput;

/**
 *
 * @author vitorfs
 */
public class TavernaSample {
    
    public static void main(String[] args) {
        TavernaClient client = new TavernaClient();
        //client.setBaseUri("http://localhost:8080/TavernaServer-2.5.4/rest");
        client.setBaseUri("http://ec2-54-191-44-161.us-west-2.compute.amazonaws.com:8080/TavernaServer-2.5.4/rest");
        client.setAuthorization("taverna", "taverna");
        
        try {
            
            String uuid = "";
            String status = "";
            TavernaExpectedInput inputs = new TavernaExpectedInput();
            String output = "";
            
            uuid = client.create("/Users/vitorfs/Downloads/Web_Service_example.t2flow");
            System.out.println(uuid);
            
            status = client.getStatus(uuid);
            System.out.println(status);
            
            inputs = client.getExpectedInputs(uuid);
            for (TavernaInput input : inputs.getInputDescription().getInput()) {
                System.out.println(input.getName());
            }
            
            System.out.println(client.setInputValue(uuid, "Country", "Brazil"));
            System.out.println(client.setInputValue(uuid, "City", "Juiz de Fora"));
            
            
            client.start(uuid);

            do {
                status = client.getStatus(uuid);
                System.out.println(status);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            } while (!"Finished".equals(status));
             
            output = client.getOutput(uuid);
            
            System.out.println(output);
            
            client.destroy(uuid);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}

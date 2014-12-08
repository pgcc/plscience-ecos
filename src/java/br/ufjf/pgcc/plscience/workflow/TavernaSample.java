/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.workflow;

import br.ufjf.pgcc.plscience.workflow.model.input.TavernaExpectedInput;
import br.ufjf.pgcc.plscience.workflow.model.input.TavernaInput;
import br.ufjf.pgcc.plscience.workflow.model.output.TavernaOutput;
import br.ufjf.pgcc.plscience.workflow.model.output.TavernaWorkflowOutput;

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
            TavernaExpectedInput tavernaInput = null;
            TavernaWorkflowOutput tavernaOutput = null;
            
            
            uuid = client.create("/Users/vitorfs/Downloads/Web_Service_example.t2flow");
            //uuid = client.create("/Users/vitorfs/Downloads/Workflow_Bruno.t2flow");
            System.out.println(uuid);
            
            /*
            ArrayList<TavernaRun> runs = client.getRuns();
            for (TavernaRun run : runs) {
                //client.destroy(run.getUuid());
                System.out.println(run.getUuid());
            }
            */
            status = client.getStatus(uuid);
            System.out.println(status);
            
            
            tavernaInput = client.getExpectedInputs(uuid);
            if (tavernaInput != null && tavernaInput.getInputDescription() != null && tavernaInput.getInputDescription().getInput() != null) {
                for (TavernaInput input : tavernaInput.getInputDescription().getInput()) {
                    if (input != null) {
                        System.out.println(input.getName());
                    }
                }
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
            
            
            
            tavernaOutput = client.getOutput(uuid);
            for (TavernaOutput output : tavernaOutput.getWorkflowOutputs().getOutput()) {
                System.out.println(output.getName());
            }
            
            
            //client.destroy(uuid);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mindswap.exceptions.ExecutionException;
import org.mindswap.owl.OWLDataValue;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLValue;
import org.mindswap.owls.OWLSFactory;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.Output;
import org.mindswap.owls.process.Perform;
import org.mindswap.owls.process.execution.DefaultProcessMonitor;
import org.mindswap.owls.process.execution.ProcessExecutionEngine;
import org.mindswap.owls.service.Service;
import org.mindswap.owls.process.Process;
import org.mindswap.query.ValueMap;

/**
 *
 * @author phillipe
 */
public class Execution {
    public static void main(String[] args) throws ExecutionException{
        
        //System.out.println("Gene Id: "+returnGeneId("ATRX"));
//        //crete an execution engine
//        ProcessExecutionEngine exec = OWLSFactory.createExecutionEngine();
//        
//        //create a monitor
//        exec.addMonitor(new DefaultProcessMonitor());
//        
//        //create a kb
//        OWLKnowledgeBase kb = OWLFactory.createKB();
//        
//        try {
//            //String uriFile = "http://localhost:8084/plscience-ecos/getEvsData.owl";
//            //String uriFile = "http://localhost:8084/plscience-ecos/geneID.owl";
//            String uriFile = "http://localhost:8084/plscience-ecos/KelvinToFahreinheith.owl";
//            //String uriFile = "http://localhost:8084/plscience-ecos/Add.owl";
//            URI uri = new URI(uriFile);
//            //File file = new File("/home/phillipe/Documentos/VirtualRepository/getEvsData.owl");
//            File file = new File("/home/phillipe/Documentos/VirtualRepository/KelvinToFahreinheith.owl");
//            //File file = new File("/home/phillipe/Documentos/VirtualRepository/Add.owl");
//            InputStream inputStream = new FileInputStream(file);
//            
//            Service service = kb.readService(inputStream, uri);
//            Process abProcess = service.getProcess();
//            
//            ValueMap<Input,OWLValue> inputs = new ValueMap<Input,OWLValue>();
//            
////            //input value
//            //String inValue;
//            String input1;
//            String input2;
//
//            //inValue = JOptionPane.showInputDialog("Input:");
//            //inValue = "139574";
//            input1 = JOptionPane.showInputDialog("Input 1:");
//            //input2 = JOptionPane.showInputDialog("Input 2:");
//            
//            //set value in map
//            //inputs.setValue(abProcess.getInputs().get(0), kb.createDataValue(inValue));
//            inputs.setValue(abProcess.getInputs().get(0), kb.createDataValue(input1));
//            //inputs.setValue(abProcess.getInputs().get(1), kb.createDataValue(input2));
//            
//            System.out.println("Running the process");
//            
//            ValueMap<Output,OWLValue> outputs = exec.execute(abProcess, inputs, kb);
//
//            System.out.println("get Output");
//            //get the output
//            OWLDataValue out = outputs.getDataValue(abProcess.getOutput());
//            
//            //display the value
//            JOptionPane.showMessageDialog(null,"Result "+out.getValue().toString());
//            System.out.println("Result "+out.getValue().toString());
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Execution.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(Execution.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
    }
    
    public static String returnGeneId(String geneName) throws ExecutionException{
        String output = "";

        //create an execution engine
        ProcessExecutionEngine exec = OWLSFactory.createExecutionEngine();
        
        //create a monitor
        exec.addMonitor(new DefaultProcessMonitor());
        
        //create a kb
        OWLKnowledgeBase kb = OWLFactory.createKB();
        
        try {
            String uriFile = "http://localhost:8084/plscience-ecos/geneID.owl";
            URI uri = new URI(uriFile);
            File file = new File("/home/phillipe/Documentos/VirtualRepository3/geneID.owl");
            InputStream inputStream = new FileInputStream(file);
            
            Service service = kb.readService(inputStream, uri);
            Process abProcess = service.getProcess();
            
            ValueMap<Input,OWLValue> inputs = new ValueMap<Input,OWLValue>();
            
            //String input1;

            //input1 = JOptionPane.showInputDialog("Input 1:");
            inputs.setValue(abProcess.getInputs().get(0), kb.createDataValue(geneName));           
            System.out.println("Running the process");
            
            ValueMap<Output,OWLValue> outputs = exec.execute(abProcess, inputs, kb);

            System.out.println("get Output");
            //get the output
            OWLDataValue out = outputs.getDataValue(abProcess.getOutput());
            
            //display the value
            //JOptionPane.showMessageDialog(null,"Result "+out.getValue().toString());
            //System.out.println("Result "+out.getValue().toString());
            output = out.getValue().toString();
            
        } catch (IOException ex) {
            Logger.getLogger(Execution.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Execution.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
}

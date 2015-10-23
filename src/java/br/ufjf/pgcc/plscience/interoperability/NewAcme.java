/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.interoperability;

import br.ufjf.pgcc.framework.parser.Parser;
import br.ufjf.pgcc.framework.sgwfc.taverna.v2_4.parser.TavernaParser;
import br.ufjf.pgcc.framework.utils.PatternLanguageWriter;
import br.ufjf.pgcc.framework.workflow.Workflow;
import br.ufjf.pgcc.framework.workflow.pattern.impl.SequencePattern;
import br.ufjf.pgcc.framework.workflow.port.InputPort;
import br.ufjf.pgcc.framework.workflow.port.OutputPort;
import br.ufjf.pgcc.framework.workflow.task.impl.WebServiceTask;
import java.io.File;



import java.io.File;
/**
 *
 * @author Fran
 */
public class NewAcme {
    
public static void main(String[] args) {
        Workflow wf = new Workflow();
        wf.setName("Blood Type");
        
        WebServiceTask t1 = new WebServiceTask();
        t1.setName("phenotypeTranslationPrimitiveType");
        t1.setWsdl("http://www.nenc.ufjf.br/plscience/rest/application.wadl");
        t1.setOperation("translate");

        WebServiceTask t2 = new WebServiceTask();
        t2.setName("matrixCombinationPhenotypesPrimitiveTypes");
        t2.setWsdl("http://www.nenc.ufjf.br/plscience/rest/application.wadl");
        t2.setOperation("generateMatrix");
        
        {
            

            
            t1.addOutputPort(new OutputPort("return"));
            
        }

        {
            InputPort f = new InputPort("f");           
            t2.addInputPort(f);
            
            SequencePattern sp1 = new SequencePattern();
            sp1.setOrigin(t1.getOutputPortByName("return"));
            sp1.setDestination(f);
           
            
            t2.addOutputPort(new OutputPort("return"));
            wf.addPattern(sp1);
        }
        
        WebServiceTask bloodType = new WebServiceTask();
        bloodType.setName("bloodTypeProbabilityPrimitiveType");
        bloodType.setWsdl("http://www.nenc.ufjf.br/plscience/rest/application.wadl");
        bloodType.setOperation("calculate");
        
        InputPort phenotypes = new InputPort("phenotypes");
       
        
        bloodType.addInputPort(phenotypes);     
        bloodType.addOutputPort(new OutputPort("return"));
        
        SequencePattern sp2 = new SequencePattern();
        sp2.setOrigin(t2.getOutputPortByName("return"));
        sp2.setDestination(phenotypes);
        
      
        
        
        wf.addPattern(sp2);
        
        wf.addTask(t1);
        wf.addTask(t2);
        wf.addTask(bloodType);
        
        try {
            File acme = new File("/var/www/files/output.acme");
            File taverna = new File("/var/www/files/output.t2flow");
            PatternLanguageWriter.writePatternLanguagemWorkflow(wf, acme);
            Parser parser = new TavernaParser();
            parser.fromPatternLanguage(acme, taverna);
        }
        catch (Exception e) {
            System.out.println("deu erro");
            e.printStackTrace();
        }
        
    } 
}

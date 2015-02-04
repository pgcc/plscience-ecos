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


/**
 *
 * @author Fran
 */
public class NewAcme2 {
 public static void main(String[] args) {
        Workflow wf = new Workflow();
        wf.setName("SumRandomNumbers");
        
        WebServiceTask t1 = new WebServiceTask();
        t1.setName("GenerateRandom1");
        t1.setWsdl("http://192.241.177.47:8080/engine-service/RandomService?wsdl");
        t1.setOperation("randomPrimitive");

        WebServiceTask t2 = new WebServiceTask();
        t2.setName("GenerateRandom2");
        t2.setWsdl("http://192.241.177.47:8080/engine-service/RandomService?wsdl");
        t2.setOperation("randomPrimitive");
        
        {
            InputPort min = new InputPort("min");
            min.setValue("1");
            t1.addInputPort(min);

            InputPort max = new InputPort("max");
            max.setValue("1000");
            t1.addInputPort(max);            
            
            t1.addOutputPort(new OutputPort("return"));
            
        }

        {
            InputPort min = new InputPort("min");
            min.setValue("1");
            t2.addInputPort(min);

            InputPort max = new InputPort("max");
            max.setValue("1000");
            t2.addInputPort(max);
            
            t2.addOutputPort(new OutputPort("return"));
        }
        
        WebServiceTask sum = new WebServiceTask();
        sum.setName("Sum");
        sum.setWsdl("http://192.241.177.47:8080/engine-service/RandomService?wsdl");
        sum.setOperation("sumPrimitive");
        
        InputPort a = new InputPort("a");
        InputPort b = new InputPort("b");
        
        sum.addInputPort(a);
        sum.addInputPort(b);
        sum.addOutputPort(new OutputPort("return"));
        
        SequencePattern sp1 = new SequencePattern();
        sp1.setOrigin(t1.getOutputPortByName("return"));
        sp1.setDestination(a);
        
        SequencePattern sp2 = new SequencePattern();
        sp2.setOrigin(t2.getOutputPortByName("return"));
        sp2.setDestination(b);
        
        wf.addPattern(sp1);
        wf.addPattern(sp2);
        
        wf.addTask(t1);
        wf.addTask(t2);
        wf.addTask(sum);
        
        try {
            File acme = new File("C:\\output.acme");
            File taverna = new File("C:\\output.t2flow");
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

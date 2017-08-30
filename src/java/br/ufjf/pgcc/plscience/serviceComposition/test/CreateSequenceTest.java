/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;
import org.mindswap.owl.EntityFactory;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLIndividual;
import org.mindswap.owl.OWLIndividualList;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLOntology;
import static org.mindswap.owl.vocabulary.SWRL.Variable;
import org.mindswap.owls.OWLSFactory;
import org.mindswap.owls.grounding.Grounding;
import org.mindswap.owls.process.AtomicProcess;
import org.mindswap.owls.process.CompositeProcess;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.Output;
import org.mindswap.owls.process.Perform;
import org.mindswap.owls.process.Result;
import org.mindswap.owls.process.Sequence;
import org.mindswap.owls.process.execution.ProcessExecutionEngine;
import org.mindswap.owls.profile.Profile;
import org.mindswap.owls.service.Service;
import org.mindswap.query.ValueMap;
import org.mindswap.utils.URIUtils;
import org.mindswap.utils.Utils;

/**
 * An example to show how service descriptions can be created on the fly, saved and executed.
 * 
 * @author Evren Sirin
 */
public class CreateSequenceTest {

    public static final URI baseURI = URI.create("http://localhost:8084/plscience-ecos/teste.owl#");
    static OWLOntology ont;
    
    public CreateSequenceTest() {        
    }
    
	static Service createSequenceService(List services) {
		Service service = ont.createService(URIUtils.createURI(baseURI, "TestService"));
		CompositeProcess process = ont.createCompositeProcess(URIUtils.createURI(baseURI, "TestProcess"));
		Profile profile = ont.createProfile(URIUtils.createURI(baseURI, "TestProfile"));
		Grounding grounding = ont.createGrounding(URIUtils.createURI(baseURI, "TestGrounding"));
		
		System.out.println(ont.getKB().getServices());
		
		service.setProfile(profile);
		service.setProcess(process);
		service.setGrounding(grounding);
		
		createSequenceProcess(process, services);
		createProfile(profile, process);

                OWLIndividualList<org.mindswap.owls.process.Process> list = process.getComposedOf().getAllProcesses(true);
		for(int i = 0; i < list.size(); i++) {	
			org.mindswap.owls.process.Process pc = list.get(i);
			if(pc instanceof AtomicProcess) {
				grounding.addGrounding(((AtomicProcess)pc).getGrounding());
			}
		}
		
		
		profile.setLabel(createLabel(services));
		profile.setTextDescription(profile.getLabel());
		
		service.setProfile(profile);
		service.setProcess(process);
		service.setGrounding(grounding);
		return service;
	}
        
	static String createLabel(List services) {
		String label = "[";
		
		for(int i = 0; i < services.size(); i++) {	
			Service s = (Service) services.get(i);
			
			if(i > 0) label += " + ";						

			label += s.getLabel();
		}
		label += "]";
		
		return label;
	}

	static Profile createProfile(Profile profile, org.mindswap.owls.process.Process process) {
		for(int i = 0; i < process.getInputs().size(); i++) {
			Input input = process.getInputs().get(i);
			
			profile.addInput(input);
		}
		
		for(int i = 0; i < process.getOutputs().size(); i++) {
			Output output = process.getOutputs().get(i);

			profile.addOutput(output);
		}
		
		return profile;
	}
    
	static CompositeProcess createSequenceProcess(CompositeProcess compositeProcess, List services) {
		Sequence sequence = ont.createSequence();
		compositeProcess.setComposedOf(sequence);
		
		Perform[] performs = new Perform[services.size()];
		for(int i = 0; i < services.size(); i++) {	
			Service s = (Service) services.get(i);
			org.mindswap.owls.process.Process p = s.getProcess();
			
			performs[i] = ont.createPerform();
			performs[i].setProcess(p);
			
			sequence.addComponent(performs[i]);

			if(i > 0) {
				Perform prevPerform = performs[i - 1]; 
				Input input = p.getInputs().get(0);
				Output output = prevPerform.getProcess().getOutputs().get(0);
				
				// the value of 'input' is the value of 'output' from 'prevPerform' 
				performs[i].addBinding(input, prevPerform, output);
			}
		}

		Perform firstPerform = performs[0];
		Perform lastPerform = performs[services.size()-1];
		boolean createInput = firstPerform.getProcess().getInputs().size() > 0;		
		boolean createOutput = lastPerform.getProcess().getOutputs().size() > 0;

		if(createInput) {
			Input input = firstPerform.getProcess().getInputs().get(0);
			Input newInput = ont.createInput(URIUtils.createURI(baseURI, "TestInput"));
			newInput.setLabel(input.getLabel());
			newInput.setParamType(input.getParamType());
			newInput.setProcess(compositeProcess);
			
			// input of the first perform is directly read from the input of the
			// composite process
			performs[0].addBinding(input, Perform.TheParentPerform, newInput);
		}	
		
		if(createOutput) {
			Output output = lastPerform.getProcess().getOutputs().get(0);
			Output newOutput = ont.createOutput(URIUtils.createURI(baseURI, "TestOutput"));
			newOutput.setLabel(output.getLabel());
			newOutput.setParamType(output.getParamType());
			newOutput.setProcess(compositeProcess);

			// the output of the composite process is the output pf last process
			Result result = ont.createResult();
			result.addBinding(newOutput, lastPerform, output);
			
			compositeProcess.setResult(result);
		}	
		
		return compositeProcess;
	}
        
	public static void runTest() throws Exception {
		// create an OWL-S knowledge base
		OWLKnowledgeBase kb = OWLFactory.createKB();

		// create an empty ontology in this KB
		ont = kb.createOntology(URI.create("http://tadeuclasse/Teste#"), URI.create("http://tadeuclasse/Teste2#"), true);
		
		// create an execution engine
		ProcessExecutionEngine exec = OWLSFactory.createExecutionEngine();
		
		// load two services
		Service s1 = kb.readService("https://sites.google.com/site/testwebservicesowls/services/Add.owl#");
		Service s2 = kb.readService("https://sites.google.com/site/testwebservicesowls/services/Multiply.owl#");
		
		// put the services in a list
		List services = new ArrayList();
		services.add(s1);
		services.add(s2);
		
		// create a new service as a sequence of the list
		Service s = createSequenceService(services);
		
		// print the description of new service to standard output
		ont.write(System.out, baseURI);
		System.out.println();
		
		// get the process of the new service
		org.mindswap.owls.process.Process process = s.getProcess();
		// initialize the input values to be empty
		ValueMap values = new ValueMap();
		// get the parameter using the local name
                Integer n = process.getInputs().size();
                System.out.println("Inputs "+n);
                System.out.println("Inputs :"+process.getInputs().get(0));
                
                values.setValue(process.getInputs().get(0), EntityFactory.createDataValue("5"));
                
		
		// execute the service
		System.out.print("Executing...");
		values = exec.execute(process, values, kb);
		System.out.println("done");
		
		// get the output param using the index		
		OWLIndividual outValue = values.getIndividualValue(process.getOutput());
		
		// display the result
		System.out.println("Value = ");
		System.out.println(Utils.formatRDF(outValue.toRDF(true, true)));
		System.out.println();		
	}	
        
        
	public static void main(String[] args) throws Exception {		
//            try {
//               
//                ProcessWSDLInformation processWSDLInformation = new ProcessWSDLInformation();
//                
//                String wsdlURL = "http://www.ebi.ac.uk/ws/services/WSDbfetchDoclit?wsdl";
//                String operacaoNome = "fetchBatch";
//                
//                List<String> in = new ArrayList<>();
//                in.add("db");
//                in.add("format");
//                in.add("ids");
//                //in.add("timeout");
//
//                List<String> out = new ArrayList<>();
//                out.add("fetchBatchReturn");
//                
//                WSDLRecover wSDLRecover = processWSDLInformation.getWSDLInfo(wsdlURL, operacaoNome, in, out);
//                
//                if(wSDLRecover != null){
//                    //SoapMessageGenerator
//                    String envelop = processWSDLInformation.generateEnvelopeSOAP(wSDLRecover, wsdlURL, operacaoNome);
//                    
//                    if(!envelop.equals("")){
//                        //envelop = envelop.replace("<numberOne>?999.99?</numberOne>", "<numberOne>20</numberOne>");
//                        //envelop = envelop.replace("<numberTwo>?999.99?</numberTwo>", "<numberTwo>20</numberTwo>");
//                        envelop = envelop.replace("<ns1:db>?XXX?</ns1:db>", "<ns1:db>genbank</ns1:db>");
//                        envelop = envelop.replace("<ns1:format>?XXX?</ns1:format>", "<ns1:format>fasta</ns1:format>");
//                        envelop = envelop.replace("<ns1:ids>?XXX?</ns1:ids>", "<ns1:ids>j00124</ns1:ids>");
//                        envelop = envelop.replace("?XXX?", "");
//                        
//                        
//                        /*
//                        envelop = envelop.replace("<email>?XXX?</email>", "<email>tadeu.classe@gmail.com</email>");
//                        envelop = envelop.replace("<string>?XXX?</string>", "<string>uniprotkb</string>");
//                        envelop = envelop.replace("<sequence>?XXX?</sequence>", "<sequence>&gt;sp|O43504|HBXIP_HUMAN  Hepatitis  B  virus  X-interacting  protein  OS=Homo  sapiens\n" +
//"GN=HBXIP PE=1 SV=1\n" +
//"MEATLEQHLEDTMKNPSIVGVLCTDSQGLNLGCRGTLSDEHAGVISVLAQQAAKLTSDPT\n" +
//"DIPVVCLESDNGNIMIQKHDGITVAVHKMAS</sequence>");
//                        envelop = envelop.replace("<stype>?XXX?</stype>", "<stype>protein</stype>");
//                        envelop = envelop.replace("<program>?XXX?</program>", "<program>blastp</program>");
//                        
//                        envelop = envelop.replace("?XXX?", "");
//                        envelop = envelop.replace("?999?", "");
//                        envelop = envelop.replace("?true?", "");
//                        */
//                        
//                        SOAPMessage soapMessage = processWSDLInformation.createSOAPMessage(envelop);
//                        
//                        String service = "http://www.ebi.ac.uk/ws/services/WSDbfetchDoclit";
//                        
//                        processWSDLInformation.soapRequest(service, soapMessage, null);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

    runTest();
        
        }
}

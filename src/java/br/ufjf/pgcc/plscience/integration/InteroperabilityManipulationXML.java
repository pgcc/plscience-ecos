/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.integration;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Guilherme Martins
 */
public class InteroperabilityManipulationXML {
    
    public static String createFileInteroperability(String nameXML, InteroperabilityStructXML interoperabilityStructXML){
                  
        String result = "Fail Alignment File Create!";
                
        try {
            
            //CAMINHO LOCAL
            File file = new File("D:/Files/AlignmentFiles"+nameXML+".xml");
            
            //CAMINHO SERVIDOR
            //File file = new File("");
            
            normalizeStructure(interoperabilityStructXML);
            
            JAXBContext jaxbContext = JAXBContext.newInstance(InteroperabilityStructXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(interoperabilityStructXML, file);
            jaxbMarshaller.marshal(interoperabilityStructXML, System.out);
            
            result = "Alignment File Create!";

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static InteroperabilityStructXML readFileInteroperability(String nameXML){
        InteroperabilityStructXML interoperabilityStructXML = null;
        
        try {
            
            //CAMINHO LOCAL
            File file = new File("D:/Files/"+nameXML+".xml");
            
            //CAMINHO SERVIDOR
            //File file = new File("");
            
            JAXBContext jaxbContext = JAXBContext.newInstance(InteroperabilityStructXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            interoperabilityStructXML = (InteroperabilityStructXML) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return interoperabilityStructXML;
    }
    
    public static String showScreen(InteroperabilityStructXML interoperabilityStructXML) {
        String same = "";
        
        same = same + "Scientist ID: " + interoperabilityStructXML.getAgentID() + "\n" +
                "Type Service 1: " + interoperabilityStructXML.getFirstTypeService() + "\n" +
                "Type Service 2: " + interoperabilityStructXML.getSecondTypeService() + "\n\n";
        
        if(interoperabilityStructXML.getConcepts() != null && !interoperabilityStructXML.getConcepts().isEmpty()) {
            
            same = same + "Concepts Analyze:" + "\n\n";
            
            for(ConceptXML c : interoperabilityStructXML.getConcepts()) {
                same = same + " Service: " + c.getGroupConcept() + 
                        " || Concept Service: " + c.getConceptService() +
                        " || Ratio Similarity: " + c.getRatio() + "%" +
                        " || Has Element? " + c.isHasElement();
                
                if(c.isHasElement()) {
                    same = same + 
                            " || Element Service 1: " + c.getConceptService1() +
                            " || Element Service 2: " + c.getConceptService2();
                } 

                same = same + " || Validity? " + c.isValidity() + "\n";
            }
        }
  
        return same;
    }
    
    public static void normalizeStructure(InteroperabilityStructXML interoperabilityStructXML) {
        
        interoperabilityStructXML.setIdStructXml(null);
        for (ConceptXML c: interoperabilityStructXML.getConcepts()) {
            c.setIdStructXml(null);
            c.setIdConceptXml(null);
            if(c.getNote().length() == 0 && c.getNote() != null) {
                c.setNote(null);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
         
        InteroperabilityStructXML t1 = new InteroperabilityStructXML();
        
        //Criando dois ConceptXML
        ConceptXML c1 = new ConceptXML();
        c1.setGroupConcept("coordination");
        c1.setConceptService("Role");
        c1.setRatio(100.00);
        c1.setHasElement(true);
        c1.setValidity(true);
        c1.setConceptService1("Scientist");
        c1.setConceptService2("Researcher");
        
        ConceptXML c2 = new ConceptXML();
        c2.setGroupConcept("group");
        c2.setConceptService("Competence");
        c2.setRatio(0.00);
        c2.setHasElement(true);
        c2.setValidity(false);
        c2.setConceptService1("Competence Alpha");
        c2.setConceptService2("Competence Beta");
        
        ConceptXML c3 = new ConceptXML();
        c3.setGroupConcept("group");
        c3.setConceptService("Group");
        c3.setRatio(100.00);
        c3.setHasElement(false);
        c3.setValidity(true);
        
        t1.getConcepts().add(c1);
        t1.getConcepts().add(c2);
        t1.getConcepts().add(c3);
        
        t1.setFirstServiceID(1L);
        t1.setFirstTypeService("List");
        t1.setSecondServiceID(2L);
        t1.setSecondTypeService("List");
        t1.setAgentID(100L);
        
        String s = createFileInteroperability("testeOne", t1);
        
        System.out.println(s);
        
        InteroperabilityStructXML t2;
        
        t2 = readFileInteroperability("testeOne");
        
        if(t2 != null) {
            System.out.println("Interoperability File Read");
            System.out.println("\n\nTESTE\n\n" +showScreen(t2));
        } else {
            System.out.println("Interoperability File Fail");
        }
        
    }
}

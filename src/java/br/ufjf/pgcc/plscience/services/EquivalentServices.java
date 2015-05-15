/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.services;

import javax.xml.bind.annotation.XmlRootElement;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fran
 */
@XmlRootElement
public class EquivalentServices {
    
    public OntModel model;
    public InfModel modelInf;
    //public String file = "file:///D:/ServiceDescriptionInf.owl"; //CAMINHO LOCAL
    public String file = "file:///opt/tomcat/webapps/plscience-ecos-collab/files/ServiceDescriptionInf.owl"; //CAMINHO SERVIDOR
    public String baseURI="http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl";

    
    public EquivalentServices(){ 
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(file);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        

        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel); 
        modelInf = ModelFactory.createInfModel(ontModelSpec.getReasoner(), model);
        /*Iterator i = model.listStatements();
        while(i.hasNext()){
            System.out.print(i.next());
        }*/
    }
    
}
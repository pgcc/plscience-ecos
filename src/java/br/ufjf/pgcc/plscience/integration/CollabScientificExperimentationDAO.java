/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.integration;

import static com.hp.hpl.jena.assembler.Assembler.ontModel;
import static com.hp.hpl.jena.assembler.JA.reasoner;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Guilherme Martins
 */
public class CollabScientificExperimentationDAO {
    
    //Variáveis Globais da Classe
    OntModel model;
    
    //URI da Ontologia
    String baseURI = "http://www.nenc.ufjf.br/ontologies/collaborationService#";
    
    //Caminho Físico da Ontologia
    String ontology = "file:///D:/Ontologias/collaborationService.owl"; //CAMINHO LOCAL
    //String ontologia = ""; //CAMINHO SERVIDOR
    
    //Caminho Físico da Nova Ontologia
    String newOntology = "file:///D:/Ontologias/collaborationService.owl"; //CAMINHO LOCAL
    //String newontology = ""; //CAMINHO SERVIDOR
    
    /**
     * Função para testar o funcionamento da classe.
     * @return Uma String com um texto pronto. 
     */
    public String testeDAO(){        
        String s = "Buscou na classe DAO corretamente utilizando a classe Bean.";
        return s;
    }
    
    /**
     * Inicializa a Ontologia de Colaboração em um Experimento Científico.
     */
    public void loadCollabOntologyDAO(){

        //Inicia a máquina de inferencia e carrega a ontologia nela.
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontology);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        
        //Ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Loading data from database", "OK"));
        System.out.println("Loading data from database");

        //Prepara os objectsProperties
        ObjectProperty hm = ontModel.createObjectProperty(baseURI + "IsPartOf");
        ObjectProperty Member = ontModel.createObjectProperty(baseURI + "Member");
        ObjectProperty aoboop = ontModel.createObjectProperty(baseURI + "ActedOnBehalfOf");
        ObjectProperty aobho = ontModel.createObjectProperty(baseURI + "actedOnBehalfOf");
        ObjectProperty usedop = ontModel.createObjectProperty(baseURI + "used");
        ObjectProperty wgbop = ontModel.createObjectProperty(baseURI + "wasGeneratedBy");
        ObjectProperty wat = ontModel.createObjectProperty(baseURI + "wasAttributedTo");
        ObjectProperty usedope = ontModel.createObjectProperty(baseURI + "Used");
        ObjectProperty s = ontModel.createObjectProperty(baseURI + "Start");
        ObjectProperty e = ontModel.createObjectProperty(baseURI + "Ended");
        ObjectProperty wawop = ontModel.createObjectProperty(baseURI + "wasAssociatedWith");
        ObjectProperty wdfop = ontModel.createObjectProperty(baseURI + "wasDerivedFrom");
        ObjectProperty wroop = ontModel.createObjectProperty(baseURI + "WasRevisionOf");
        
    }
    
    /**
     * Cria o cabeçalho utilizado em consultas SPARQL.
     * @return O cabeçalho utilizado em consultas SPARQL.
     */
    public String getOntologyHeader(){
        String ontologyHeader = 
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX me: <" + baseURI + ">\n";
        return ontologyHeader;
    }
    
    /**
     * Retorna as Subclasses de uma Classe da ontologia, caso exista.
     * @param nameClass
     * @return String com as Subclasses
     */
    public String getSubClasses(String nameClass) { 
        OntModel ontModel = ModelFactory.createOntologyModel();
        ontModel.read(ontology);

        OntClass classOntology = ontModel.getOntClass(baseURI + nameClass);
        String subClasses = "";

        for(Iterator i = classOntology.listSubClasses(); i.hasNext();)
        {
            try{
                OntClass Class = (OntClass) i.next(); 
                String uriCompleta = Class.getURI();
                String auxName[] = uriCompleta.split("#");
                subClasses = subClasses + auxName[1] + "\n";
            }
            catch(Exception e){
                subClasses = subClasses + "Falha\n";
            }            
        }
        return classOntology.toString() + "\n" + subClasses;
    }
    
    /**
     * Retorna as Subclasses de uma Classe da ontologia, caso exista. 
     * As Subclasses são encontrados através de uma consulta SPARQL.
     * @param nameClass
     * @return String com as Subclasses
     */
    public String getSubClassesForSPARQL(String nameClass) { 
        OntModel ontModel = ModelFactory.createOntologyModel();
        ontModel.read(ontology);
               
        String queryString = getOntologyHeader() +          
            "SELECT DISTINCT ?subClass\n" +
            "WHERE {?subClass  rdfs:subClassOf me:" + nameClass + ".\n" +
            "}\n";

        Query query = QueryFactory.create(queryString);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, ontModel);
        ResultSet results =  qe.execSelect();
        
        // Output query results            
        String listIndividuals = ResultSetFormatter.asText(results);
        
        return listIndividuals;
    }
    
}

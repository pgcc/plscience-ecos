/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.ontology;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import java.nio.file.Paths;
import java.util.List;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 * Classe responsável por controlar o acesso à Ontologia
 *
 * @author Lenita
 */
public class OntologyController {

    //variavel para acesso à ontologia com inferencia
    private final InfModel infModel;

    //variavel para acesso à ontologia sem inferencia
    private final OntModel ontModel;

    //uri da ontologia
    public static final String URI = "http://www.semanticweb.org/lenita/ontologies/2016/7/prov-one-experiment#";

    //uri das ontologias importadas
    public static final String PROV_URI = "http://www.w3.org/ns/prov#";
    public static final String PROVONE_URI = "http://purl.dataone.org/provone/2015/01/15/ontology#";

    //caminho para a ontologia
    public static final String ONTOLOGY = "../../files/ontologies/provone-experiment-instances.owl";

    //caminho para a ontologia carregada
    public static final String ONTOLOGY_LOAD = System.getProperty("user.home") + "\\AppData\\Local\\Temp\\provone-experiment-load.owl";

    //caminho para as ontologias importadas
    public static final String PROV_ONTOLOGY = "../../files/ontologies/prov-o.owl";
    public static final String PROVONE_ONTOLOGY = "../../files/ontologies/provone.owl";

    private static OntologyController instance;

    public static OntologyController getInstance() {
        if (instance == null) {
            instance = new OntologyController();
        }
        return instance;
    }

    private OntologyController() {
        DataHandler.loadDAO();

        Model model = ModelFactory.createDefaultModel();

        model.read(PROV_ONTOLOGY);
        model.read(PROVONE_ONTOLOGY);
        model.read(Paths.get(ONTOLOGY_LOAD).toUri().toString());

        ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
        ontModel.prepare();

        Reasoner reasoner = PelletReasonerFactory.theInstance().create();
        infModel = ModelFactory.createInfModel(reasoner, ontModel);
        infModel.prepare();
    }

    public InfModel getInfModel() {
        return infModel;
    }

    public OntModel getOntModel() {
        return ontModel;
    }

    public static void main(String[] args) {
        InferenceLayer inferenceLayer = new InferenceLayer();
        List<String> sparqlGetPropertiesByIndividualInf = inferenceLayer.sparqlGetPropertiesByIndividualInf("Teste.Similar");
        for (String string : sparqlGetPropertiesByIndividualInf) {
            System.out.println(string);
        }
        System.out.println("OK \\o/ \\o/");
    }
}

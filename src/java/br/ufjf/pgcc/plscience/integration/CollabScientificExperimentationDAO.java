/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.integration;

import br.ufjf.pgcc.plscience.dao.ActivityConceptDAO;
import br.ufjf.pgcc.plscience.dao.ArtifactDAO;
import br.ufjf.pgcc.plscience.dao.CodeDAO;
import br.ufjf.pgcc.plscience.dao.CollaborationServiceDAO;
import br.ufjf.pgcc.plscience.dao.CollaborativeServiceTypeDAO;
import br.ufjf.pgcc.plscience.dao.CommonSenseDAO;
import br.ufjf.pgcc.plscience.dao.CommunicationProtocolDAO;
import br.ufjf.pgcc.plscience.dao.CompetenceDAO;
import br.ufjf.pgcc.plscience.dao.CompromiseDAO;
import br.ufjf.pgcc.plscience.dao.ProductDAO;
import br.ufjf.pgcc.plscience.dao.RolerDAO;
import br.ufjf.pgcc.plscience.dao.StatusDAO;
import br.ufjf.pgcc.plscience.dao.StepsScientificExperimentationDAO;
import br.ufjf.pgcc.plscience.dao.TaskConceptDAO;
import br.ufjf.pgcc.plscience.model.ActivityConcept;
import br.ufjf.pgcc.plscience.model.Artifact;
import br.ufjf.pgcc.plscience.model.Code;
import br.ufjf.pgcc.plscience.model.CollaborationService;
import br.ufjf.pgcc.plscience.model.CollaborativeServiceType;
import br.ufjf.pgcc.plscience.model.CommonSense;
import br.ufjf.pgcc.plscience.model.CommunicationProtocol;
import br.ufjf.pgcc.plscience.model.Competence;
import br.ufjf.pgcc.plscience.model.Compromise;
import br.ufjf.pgcc.plscience.model.Product;
import br.ufjf.pgcc.plscience.model.Roler;
import br.ufjf.pgcc.plscience.model.Status;
import br.ufjf.pgcc.plscience.model.StepsScientificExperimentation;
import br.ufjf.pgcc.plscience.model.TaskConcept;
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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    String baseURI = "http://www.nenc.ufjf.br/ontologies/collaboration/collaborationService#";
    
    //Caminho Físico da Ontologia
    String ontology = "file:///D:/Ontologias/collaborationService.owl"; //CAMINHO LOCAL
    //String ontologia = ""; //CAMINHO SERVIDOR
    
    //Caminho Físico da Nova Ontologia
    String newOntology = "D:/Ontologias/collaborationService2.owl"; //CAMINHO LOCAL
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

        //-----------------------------------------------------------------------
        //Prepara os objectsProperties
        //-----------------------------------------------------------------------
        
        //objectsProperties - Serviços de Colaboração - Comunicação
        ObjectProperty collaborativeServiceCanHaveMessage = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveMessage");
        ObjectProperty collaborativeServiceCanHaveIssuer = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveIssuer");
        ObjectProperty collaborativeServiceCanHaveReceiver = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveReceiver");
        ObjectProperty collaborativeServiceCanHaveCommunicationProtocol = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveCommunicationProtocol");
        ObjectProperty collaborativeServiceCanHaveCommonSense = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveCommonSense");
        ObjectProperty collaborativeServiceCanHaveSynchronism = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveSynchronism");
        ObjectProperty collaborativeServiceCanHaveTransmissionMode = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveTransmissionMode");
        ObjectProperty collaborativeServiceCanHaveCompromise = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveCompromise");
        ObjectProperty collaborativeServiceCanHaveNegotiation = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveNegotiation");
        ObjectProperty collaborativeServiceCanHaveCode = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveCode");
        ObjectProperty collaborativeServiceCanHaveMode = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveMode");
        ObjectProperty collaborativeServiceCanHaveInterpretation = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveInterpretation");
        
        //objectsProperties - Serviços de Colaboração - Coordenação
        ObjectProperty collaborativeServiceCanHaveWorkPlan = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveWorkPlan");
        ObjectProperty collaborativeServiceCanHaveDeadline = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveDeadline");
        ObjectProperty collaborativeServiceCanHaveStatus = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveStatus");
        ObjectProperty collaborativeServiceCanHaveRole = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveRole");
        ObjectProperty collaborativeServiceCanHavePolicy = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHavePolicy");
        ObjectProperty collaborativeServiceCanHaveMonitoring = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveMonitoring");
        ObjectProperty collaborativeServiceCanHaveCoupling = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveCoupling");
        
        //objectsProperties - Serviços de Colaboração - Cooperação
        ObjectProperty collaborativeServiceCanHaveActivity = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveActivity");
        ObjectProperty collaborativeServiceCanHaveTask = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveTask");
        ObjectProperty collaborativeServiceCanHaveProduct = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveProduct");
        ObjectProperty collaborativeServiceCanHaveArtifact = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveArtifact");
        ObjectProperty collaborativeServiceCanHaveSharedSpace = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveSharedSpace");
        ObjectProperty collaborativeServiceCanHaveResource = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveResource");
        ObjectProperty collaborativeServiceCanHaveShare = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveShare");
        
        //objectsProperties - Serviços de Colaboração - Formação de Grupos
        ObjectProperty collaborativeServiceCanHaveParticipant = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveParticipant");
        ObjectProperty collaborativeServiceCanHaveBelief = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveBelief");
        ObjectProperty collaborativeServiceCanHaveConfidence = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveConfidence");
        ObjectProperty collaborativeServiceCanHaveMotivation = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveMotivation");
        ObjectProperty collaborativeServiceCanHaveGroup = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveGroup");
        ObjectProperty collaborativeServiceCanHaveCompetence = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveCompetence");
        ObjectProperty collaborativeServiceCanHaveGoal = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveGoal");
        
        //objectsProperties - Serviços de Colaboração - Percepção
        ObjectProperty collaborativeServiceCanHaveWho = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveWho");
        ObjectProperty collaborativeServiceCanHaveWhat = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveWhat");
        ObjectProperty collaborativeServiceCanHaveWhen = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveWhen");
        ObjectProperty collaborativeServiceCanHaveWhere = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveWhere");
        ObjectProperty collaborativeServiceCanHaveHow = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveHow");
        
        //objectsProperties - Serviços de Colaboração - Domínio e Subdomínio
        ObjectProperty collaborativeServiceHasApplicationDomain = ontModel.createObjectProperty(baseURI + "collaborativeServiceHasApplicationDomain");
        ObjectProperty collaborativeServiceCanHaveApplicationSubDomain = ontModel.createObjectProperty(baseURI + "collaborativeServiceCanHaveApplicationSubDomain");
        
        //objectsProperties - Serviços de Colaboração - Etapas do Ciclo de Vida
        ObjectProperty collaborativeServiceHasLifeCycleStage = ontModel.createObjectProperty(baseURI + "collaborativeServiceHasLifeCycleStage");
        
        //objectsProperties - Colaboração
        ObjectProperty productIsGoal = ontModel.createObjectProperty(baseURI + "productIsGoal");
        ObjectProperty negotiationSetsGoal = ontModel.createObjectProperty(baseURI + "negotiationSetsGoal");
        ObjectProperty groupDoesActivity = ontModel.createObjectProperty(baseURI + "groupDoesActivity");
        ObjectProperty participantAcceptCompromise = ontModel.createObjectProperty(baseURI + "participantAcceptCompromise");
        ObjectProperty participantAcceptTask = ontModel.createObjectProperty(baseURI + "participantAcceptTask");
        ObjectProperty participantDoesNegotiation = ontModel.createObjectProperty(baseURI + "participantDoesNegotiation");
        
        ObjectProperty receiverIsParticipant = ontModel.createObjectProperty(baseURI + "receiverIsParticipant");
        ObjectProperty issuerIsParticipant = ontModel.createObjectProperty(baseURI + "issuerIsParticipant");
        
        ObjectProperty negotiationEstablishWorkPlan = ontModel.createObjectProperty(baseURI + "negotiationEstablishWorkPlan");
        ObjectProperty participantPerformsRole = ontModel.createObjectProperty(baseURI + "participantPerformsRole");
        ObjectProperty workPlanSetsTask = ontModel.createObjectProperty(baseURI + "workPlanSetsTask");
        ObjectProperty workPlanAllocatesResource = ontModel.createObjectProperty(baseURI + "workPlanAllocatesResource");
        ObjectProperty workPlanOrganizesActivity = ontModel.createObjectProperty(baseURI + "workPlanOrganizesActivity");
        ObjectProperty statusParticipant = ontModel.createObjectProperty(baseURI + "statusParticipant");
        ObjectProperty statusTask = ontModel.createObjectProperty(baseURI + "statusTask");
        ObjectProperty deadlineTask = ontModel.createObjectProperty(baseURI + "deadlineTask");
        ObjectProperty couplingActivity = ontModel.createObjectProperty(baseURI + "couplingActivity");
        ObjectProperty roleIsResponsibleByTask = ontModel.createObjectProperty(baseURI + "roleIsResponsibleByTask");
        
        ObjectProperty groupInteractsBySharedSpace = ontModel.createObjectProperty(baseURI + "groupInteractsBySharedSpace");
        
        ObjectProperty whoIsParticipant = ontModel.createObjectProperty(baseURI + "whoIsParticipant");
        ObjectProperty whoIsGroup = ontModel.createObjectProperty(baseURI + "whoIsGroup");
        ObjectProperty whoIsRole = ontModel.createObjectProperty(baseURI + "whoIsRole");
        ObjectProperty whoIsIssuer = ontModel.createObjectProperty(baseURI + "whoIsIssuer");
        ObjectProperty whoIsReceiver = ontModel.createObjectProperty(baseURI + "whoIsReceiver");
        
        ObjectProperty whatIsProduct = ontModel.createObjectProperty(baseURI + "whatIsProduct");
        ObjectProperty whatIsArtifact = ontModel.createObjectProperty(baseURI + "whatIsArtifact");
        ObjectProperty whatIsGoal = ontModel.createObjectProperty(baseURI + "whatIsGoal");
        ObjectProperty whatIsNegotiation = ontModel.createObjectProperty(baseURI + "whatIsNegotiation");
        
        ObjectProperty whenIsDeadline = ontModel.createObjectProperty(baseURI + "whenIsDeadline");
        
        ObjectProperty whereIsTask = ontModel.createObjectProperty(baseURI + "whereIsTask");
        ObjectProperty whereIsActivity = ontModel.createObjectProperty(baseURI + "whereIsActivity");
        ObjectProperty whereIsResource = ontModel.createObjectProperty(baseURI + "whereIsResource");
        ObjectProperty whereIsSharedSpace = ontModel.createObjectProperty(baseURI + "whereIsSharedSpace");
        ObjectProperty whereIsMessage = ontModel.createObjectProperty(baseURI + "whereIsMessage");
        
        ObjectProperty howIsWorkPlan = ontModel.createObjectProperty(baseURI + "howIsWorkPlan");
        ObjectProperty howIsCompromise = ontModel.createObjectProperty(baseURI + "howIsCompromise");
        
        //objectsProperties - Comunicação
        ObjectProperty messageEstablishCompromise = ontModel.createObjectProperty(baseURI + "messageEstablishCompromise");
        ObjectProperty negotiationResultInCompromise = ontModel.createObjectProperty(baseURI + "negotiationResultInCompromise");
        ObjectProperty messageMode = ontModel.createObjectProperty(baseURI + "messageMode");
        ObjectProperty negotiationPerformedPerMessage = ontModel.createObjectProperty(baseURI + "negotiationPerformedPerMessage");
        ObjectProperty codeGeneratesMessage = ontModel.createObjectProperty(baseURI + "codeGeneratesMessage");
        ObjectProperty issuerGeneratesCode = ontModel.createObjectProperty(baseURI + "issuerGeneratesCode");
        ObjectProperty issuerSendMessage = ontModel.createObjectProperty(baseURI + "issuerSendMessage");
        ObjectProperty issuerShareCommonSense = ontModel.createObjectProperty(baseURI + "issuerShareCommonSense");
        ObjectProperty issuerUsesProtocol = ontModel.createObjectProperty(baseURI + "issuerUsesProtocol");
        ObjectProperty receiverShareCommonSense = ontModel.createObjectProperty(baseURI + "receiverShareCommonSense");
        ObjectProperty receiverUsesProtocol = ontModel.createObjectProperty(baseURI + "receiverUsesProtocol");
        ObjectProperty receiverGetMessage = ontModel.createObjectProperty(baseURI + "receiverGetMessage");
        ObjectProperty messageFollowProtocol = ontModel.createObjectProperty(baseURI + "messageFollowProtocol");
        ObjectProperty messageTransmissionMode = ontModel.createObjectProperty(baseURI + "messageTransmissionMode");
        ObjectProperty synchronismDeterminesTransmission = ontModel.createObjectProperty(baseURI + "synchronismDeterminesTransmission");
        ObjectProperty messageGeneratesInterpretation = ontModel.createObjectProperty(baseURI + "messageGeneratesInterpretation");
        ObjectProperty receiverGeneratesInterpretation = ontModel.createObjectProperty(baseURI + "receiverGeneratesInterpretation");
        ObjectProperty transmissionRestrictsMode = ontModel.createObjectProperty(baseURI + "transmissionRestrictsMode");
        
        //objectsProperties - Coordenação
        ObjectProperty workPlanSetsPolicy = ontModel.createObjectProperty(baseURI + "workPlanSetsPolicy");
        ObjectProperty monitoringFollowsWorkPlan = ontModel.createObjectProperty(baseURI + "monitoringFollowsWorkPlan");
        ObjectProperty monitoringControlsDeadline = ontModel.createObjectProperty(baseURI + "monitoringControlsDeadline");
        ObjectProperty monitoringControlsStatus = ontModel.createObjectProperty(baseURI + "monitoringControlsStatus");
        ObjectProperty workPlanSetsDeadline = ontModel.createObjectProperty(baseURI + "workPlanSetsDeadline");
        ObjectProperty workPlanSetsRole = ontModel.createObjectProperty(baseURI + "workPlanSetsRole");
        ObjectProperty workPlanSetsCoupling = ontModel.createObjectProperty(baseURI + "workPlanSetsCoupling");
        
        //objectsProperties - Cooperação
        ObjectProperty shareTask = ontModel.createObjectProperty(baseURI + "shareTask");
        ObjectProperty taskActivity = ontModel.createObjectProperty(baseURI + "taskActivity");
        ObjectProperty taskGeneratesArtifact = ontModel.createObjectProperty(baseURI + "taskGeneratesArtifact");
        ObjectProperty taskRequiresResource = ontModel.createObjectProperty(baseURI + "taskRequiresResource");
        ObjectProperty activityGeneratesProduct = ontModel.createObjectProperty(baseURI + "activityGeneratesProduct");
        ObjectProperty resourceSharedSpace = ontModel.createObjectProperty(baseURI + "resourceSharedSpace");
        ObjectProperty artifactSharedSpace = ontModel.createObjectProperty(baseURI + "artifactSharedSpace");
        ObjectProperty activityMadeInSharedSpace = ontModel.createObjectProperty(baseURI + "activityMadeInSharedSpace");
        ObjectProperty artifactProduct = ontModel.createObjectProperty(baseURI + "artifactProduct");
        
        //objectsProperties - Formação de Grupo
        ObjectProperty beliefParticipant = ontModel.createObjectProperty(baseURI + "beliefParticipant");
        ObjectProperty confidenceParticipant = ontModel.createObjectProperty(baseURI + "confidenceParticipant");
        ObjectProperty competenceParticipant = ontModel.createObjectProperty(baseURI + "competenceParticipant");
        ObjectProperty participantHasMotivation = ontModel.createObjectProperty(baseURI + "participantHasMotivation");
        ObjectProperty participantGroup = ontModel.createObjectProperty(baseURI + "participantGroup");
        ObjectProperty participantHasGoal = ontModel.createObjectProperty(baseURI + "participantHasGoal");
        ObjectProperty groupHasGoal = ontModel.createObjectProperty(baseURI + "groupHasGoal");
        
        //-----------------------------------------------------------------------
        //Carrega Indivíduos na Ontologia
        //-----------------------------------------------------------------------
        
        //Carrega os Code (Communication) na ontologia apartir do banco de dados
        Resource resourcecode = model.getResource(baseURI + "Code");
        Code code = new Code();
        List codes = new ArrayList();
        codes = new CodeDAO().getAll();
        for (Object c : codes) {
            code = (Code) c;
            model.createIndividual(baseURI + code.getId() + "." + code.getCodeName().replace(" ", "."), resourcecode);
        }

        //Carrega os CommonSense (Communication) na ontologia apartir do banco de dados
        Resource resourcecommonsense = model.getResource(baseURI + "CommonSense");
        CommonSense commonSense = new CommonSense();
        List commonSenses = new ArrayList();
        codes = new CommonSenseDAO().getAll();
        for (Object c : commonSenses) {
            commonSense = (CommonSense) c;
            model.createIndividual(baseURI + commonSense.getId() + "." + commonSense.getCommonSenseName().replace(" ", "."), resourcecommonsense);
        }

        //Carrega os CommunicationProtocol (Communication) na ontologia apartir do banco de dados
        Resource resourcecommunicationprotocol = model.getResource(baseURI + "CommunicationProtocol");
        CommunicationProtocol communicationProtocol = new CommunicationProtocol();
        List communicationProtocols = new ArrayList();
        communicationProtocols = new CommunicationProtocolDAO().getAll();
        for (Object c : communicationProtocols) {
            communicationProtocol = (CommunicationProtocol) c;
            model.createIndividual(baseURI + communicationProtocol.getId() + "." + communicationProtocol.getCommunicationProtocolName().replace(" ", "."), resourcecommunicationprotocol);
        }
        
        //Carrega os Compromise (Communication) na ontologia apartir do banco de dados
        Resource resourcecompromise = model.getResource(baseURI + "Compromise");
        Compromise compromise = new Compromise();
        List compromises = new ArrayList();
        compromises = new CompromiseDAO().getAll();
        for (Object c : compromises) {
            compromise = (Compromise) c;
            model.createIndividual(baseURI + compromise.getId() + "." + compromise.getCompromiseName().replace(" ", "."), resourcecompromise);
        }
        
        
        
        //Carrega os Role (Coordination) na ontologia apartir do banco de dados
        Resource resourceroler = model.getResource(baseURI + "Role");
        Roler roler = new Roler();
        List roles = new ArrayList();
        roles = new RolerDAO().getAll();
        for (Object r : roles) {
            roler = (Roler) r;
            model.createIndividual(baseURI + roler.getId() + "." + roler.getRoleName().replace(" ", "."), resourceroler);
        }
        
        //Carrega os Status (Coordination) na ontologia apartir do banco de dados
        Resource resourcestatus = model.getResource(baseURI + "Status");
        Status status = new Status();
        List statuss = new ArrayList();
        statuss = new StatusDAO().getAll();
        for (Object s : statuss) {
            status = (Status) s;
            model.createIndividual(baseURI + status.getId() + "." + status.getStatusName().replace(" ", "."), resourcestatus);
        }
        
        
        
        //Carrega os Activity (Cooperation) na ontologia apartir do banco de dados
        Resource resourceactivity = model.getResource(baseURI + "Activity");
        ActivityConcept activity = new ActivityConcept();
        List activities = new ArrayList();
        activities = new ActivityConceptDAO().getAll();
        for (Object a : activities) {
            activity = (ActivityConcept) a;
            model.createIndividual(baseURI + activity.getId() + "." + activity.getActivityName().replace(" ", "."), resourceactivity);
        }
        
        //Carrega os Artifact (Cooperation) na ontologia apartir do banco de dados
        Resource resourceartifact = model.getResource(baseURI + "Artifact");
        Artifact artifact = new Artifact();
        List artifacts = new ArrayList();
        artifacts = new ArtifactDAO().getAll();
        for (Object a : artifacts) {
            artifact = (Artifact) a;
            model.createIndividual(baseURI + artifact.getId() + "." + artifact.getArtifactName().replace(" ", "."), resourceartifact);
        }
        
        //Carrega os Product (Cooperation) na ontologia apartir do banco de dados
        Resource resourceproduct = model.getResource(baseURI + "Product");
        Product product = new Product();
        List products = new ArrayList();
        products = new ProductDAO().getAll();
        for (Object p : products) {
            product = (Product) p;
            model.createIndividual(baseURI + product.getId() + "." + product.getProductName().replace(" ", "."), resourceproduct);
        }
        
        //Carrega os Task (Cooperation) na ontologia apartir do banco de dados
        Resource resourcetask = model.getResource(baseURI + "Task");
        TaskConcept task = new TaskConcept();
        List tasks = new ArrayList();
        tasks = new TaskConceptDAO().getAll();
        for (Object t : tasks) {
            task = (TaskConcept) t;
            model.createIndividual(baseURI + task.getId() + "." + task.getTaskName().replace(" ", "."), resourcetask);
        }
        
        
        
        
        
        //Carrega os Competence (GroupFormation) na ontologia apartir do banco de dados
        Resource resourcecompetence = model.getResource(baseURI + "Competence");
        Competence competence = new Competence();
        List competences = new ArrayList();
        competences = new CompetenceDAO().getAll();
        for (Object c : competences) {
            competence = (Competence) c;
            model.createIndividual(baseURI + competence.getId() + "." + competence.getCompetenceName().replace(" ", "."), resourcecompetence);
        }      
        
        
        
        
        
        
        
        Resource resourcelifecyclestage = model.getResource(baseURI + "LifeCycleStage");
        StepsScientificExperimentation stepsScientificExperimentation = new StepsScientificExperimentation();
        List stepsScientificExperimentations = new ArrayList();
        stepsScientificExperimentations = new StepsScientificExperimentationDAO().getAll();
        for (Object s : stepsScientificExperimentations) {
            stepsScientificExperimentation = (StepsScientificExperimentation) s;
            model.createIndividual(baseURI + stepsScientificExperimentation.getId() + "." + stepsScientificExperimentation.getNumberStep()+ "." + stepsScientificExperimentation.getNameStep().replace(" ", "."), resourcelifecyclestage);
        }
        
        Resource resourcecollabservicetype = model.getResource(baseURI + "CollaborativeServiceType");
        CollaborativeServiceType collaborativeServiceType = new CollaborativeServiceType();
        List collaborativeServiceTypes = new ArrayList();
        collaborativeServiceTypes = new CollaborativeServiceTypeDAO().getAll();
        for (Object c : collaborativeServiceTypes) {
            collaborativeServiceType = (CollaborativeServiceType) c;
            model.createIndividual(baseURI + collaborativeServiceType.getId() + "." + collaborativeServiceType.getNameServiceType().replace(" ", "."), resourcecollabservicetype);
        }
        
        Resource resourcecollabservice = model.getResource(baseURI + "CollaborativeService");
        CollaborationService collaborationService = new CollaborationService();
        List collaborationServices = new ArrayList();
        collaborationServices = new CollaborationServiceDAO().getAllCollaborationService();
        for (Object c : collaborationServices) {
            collaborationService = (CollaborationService) c;
            model.createIndividual(baseURI + collaborationService.getId() + "." + collaborationService.getCollabServiceName().replace(" ", "."), resourcecollabservice);
        }
                
        //-----------------------------------------------------------------------
        //Cria os relacionamentos entre os Indivíduos na Ontologia
        //-----------------------------------------------------------------------
        
        
        
        //Relaciona Etapas do Ciclo de Vida ao Serviço de Colaboração
        for (Object c : collaborationServices) {
            collaborationService = (CollaborationService) c;            
            Individual collabService = model.getIndividual(baseURI + collaborationService.getId() + "." + collaborationService.getCollabServiceName().replace(" ", "."));
            List steps = new ArrayList();
            steps = new CollaborationServiceDAO().getStepsScientificExperimentationByServiceID(collaborationService.getId());
            for (Object s : steps) {
                stepsScientificExperimentation = (StepsScientificExperimentation) s;
                Individual step = model.getIndividual(baseURI + stepsScientificExperimentation.getId() + "." + stepsScientificExperimentation.getNumberStep()+ "." + stepsScientificExperimentation.getNameStep().replace(" ", "."));
                collabService.addProperty(collaborativeServiceHasLifeCycleStage, step);
            }
        }
        
        //-----------------------------------------------------------------------
        //Salva a "newOntology" com os dados extraídos do Banco de Dados
        //-----------------------------------------------------------------------
        
        //Gerar o novo arquivo com os dados do banco na nova ontologia
        FileWriter arquivo = null;
        try {
            //caminho para o novo arquivo de ontologia
            arquivo = new FileWriter(newOntology);
            //se não existir arquivo, o mesmo será criado, se não, será reescrito
        } catch (IOException ex) {
            ex.printStackTrace(); //verificando problemas
        }
        //determinando que o fluxo de saida vai para o arquivo e não para a tela            
        BufferedWriter out = new BufferedWriter(arquivo);
        //utilizar RDF/XML-ABBREV, so RDF/XML da erro no protege!        
        model.write(out, "RDF/XML-ABBREV");

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ontology successfully loaded", "OK"));
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
        ontModel.read(this.ontology);

        OntClass classOntology = ontModel.getOntClass(this.baseURI + nameClass);
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

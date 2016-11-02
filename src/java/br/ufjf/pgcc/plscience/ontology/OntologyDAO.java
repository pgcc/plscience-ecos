/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.ontology;

import br.ufjf.pgcc.plscience.dao.ActedOnBehalfOfDAO;
import br.ufjf.pgcc.plscience.dao.ActivityDAO;
import br.ufjf.pgcc.plscience.dao.AgentDAO;
import br.ufjf.pgcc.plscience.dao.EntityDAO;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import br.ufjf.pgcc.plscience.dao.InputPortDAO;
import br.ufjf.pgcc.plscience.dao.IsPartOfDAO;
import br.ufjf.pgcc.plscience.dao.OutputPortDAO;
import br.ufjf.pgcc.plscience.dao.ResearchGroupDAO;
import br.ufjf.pgcc.plscience.dao.SGWfCDAO;
import br.ufjf.pgcc.plscience.dao.TaskDAO;
import br.ufjf.pgcc.plscience.dao.UsedDAO;
import br.ufjf.pgcc.plscience.dao.WasAssociatedWithDAO;
import br.ufjf.pgcc.plscience.dao.WasDerivedFromDAO;
import br.ufjf.pgcc.plscience.dao.WasEndedByDAO;
import br.ufjf.pgcc.plscience.dao.WasEndedByWTDAO;
import br.ufjf.pgcc.plscience.dao.WasGeneratedByDAO;
import br.ufjf.pgcc.plscience.dao.WasRevisionOfDAO;
import br.ufjf.pgcc.plscience.dao.WasStartedByDAO;
import br.ufjf.pgcc.plscience.dao.WasStartedByWTDAO;
import br.ufjf.pgcc.plscience.dao.WorkflowDAO;
import br.ufjf.pgcc.plscience.model.ActedOnBehalfOf;
import br.ufjf.pgcc.plscience.model.Activity;
import br.ufjf.pgcc.plscience.model.Agent;
import br.ufjf.pgcc.plscience.model.Entity;
import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.model.InputPort;
import br.ufjf.pgcc.plscience.model.IsPartOf;
import br.ufjf.pgcc.plscience.model.OutputPort;
import br.ufjf.pgcc.plscience.model.ResearchGroup;
import br.ufjf.pgcc.plscience.model.SGWfC;
import br.ufjf.pgcc.plscience.model.Task;
import br.ufjf.pgcc.plscience.model.Used;
import br.ufjf.pgcc.plscience.model.WasAssociatedWith;
import br.ufjf.pgcc.plscience.model.WasDerivedFrom;
import br.ufjf.pgcc.plscience.model.WasEndedBy;
import br.ufjf.pgcc.plscience.model.WasEndedByWT;
import br.ufjf.pgcc.plscience.model.WasGeneratedBy;
import br.ufjf.pgcc.plscience.model.WasRevisionOf;
import br.ufjf.pgcc.plscience.model.WasStartedBy;
import br.ufjf.pgcc.plscience.model.WasStartedByWT;
import br.ufjf.pgcc.plscience.model.Workflow;
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
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
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
 * @author tassio
 */
public class OntologyDAO {

    private final String ontologia = "../../files/ontologies/prov-oext.owl";
    private final String newontology = "../../files/ontologies/prov-oextload.owl";
    
    public void loadDAO() {

        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM_TRANS_INF;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Loading data from database", "OK"));
        //System.out.println("Loading data from database");

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

        //Carrega os Entitys na ontologia apartir do banco de dados
        Resource resourceorg = model.getResource(baseURI + "Organization");
        Entity entity = new Entity();
        List entitys = new ArrayList();
        entitys = new EntityDAO().buscarTodas();
        for (Object entity1 : entitys) {
            entity = (Entity) entity1;
            model.createIndividual(baseURI + entity.getAcronym().replace(" ", "."), resourceorg);
        }

        //Carrega os ResearchGroup na ontologia apartir do banco de dados
        Resource resourcerg = model.getResource(baseURI + "ResearchGroup");
        ResearchGroup researchGroup = new ResearchGroup();
        List researchGroups = new ArrayList();
        researchGroups = new ResearchGroupDAO().buscarTodas();
        for (Object researchGroup1 : researchGroups) {
            researchGroup = (ResearchGroup) researchGroup1;
            model.createIndividual(baseURI + researchGroup.getName().replace(" ", "."), resourcerg);
        }

        //Carrega os Agents na ontologia apartir do banco de dados
        Resource resourceperson = model.getResource(baseURI + "Person");
        Agent agent = new Agent();
        List agents = new ArrayList();
        agents = new AgentDAO().buscarTodas();
        for (Object agent1 : agents) {
            agent = (Agent) agent1;
            model.createIndividual(baseURI + agent.getName().replace(" ", "."), resourceperson);
        }

        //Define que cada grupo de pesquisa pertence a uma instituição
        IsPartOf ipo = new IsPartOf();
        List ipos = new ArrayList();
        ipos = new IsPartOfDAO().buscarTodas();
        for (Object ipo1 : ipos) {
            ipo = (IsPartOf) ipo1;
            Individual rg = model.getIndividual(baseURI + ipo.getResearchGroupidResearchGroup().getName().replace(" ", "."));
            Individual ispart = model.getIndividual(baseURI + ipo.getAgentidAgent().getInstitution().getAcronym().replace(" ", "."));
            rg.addProperty(hm, ispart);
        }

        //defini que cada agente atua em um grupo de pesquisa
        IsPartOf ipo2 = new IsPartOf();
        List ipo2s = new ArrayList();
        ipo2s = new IsPartOfDAO().buscarTodas();
        for (Object ipo21 : ipo2s) {
            ipo2 = (IsPartOf) ipo21;
            Individual p = model.getIndividual(baseURI + ipo2.getAgentidAgent().getName().replace(" ", "."));
            Individual g = model.getIndividual(baseURI + ipo2.getResearchGroupidResearchGroup().getName().replace(" ", "."));
            p.addProperty(Member, g);
        }

        //Carrega os Experiments na ontologia apartir do banco de dados, associando aos agents e as entidades
        Resource resourceexp = model.getResource(baseURI + "Experiment");
        Experiment experiment = new Experiment();
        List experiments = new ArrayList();
        experiments = new ExperimentDAO().getAll();
        for (Object experiment1 : experiments) {
            experiment = (Experiment) experiment1;
            Individual exp = model.createIndividual(baseURI + experiment.getName().replace(" ", "."), resourceexp);
            Individual person = model.getIndividual(baseURI + experiment.getIdAgent().getName().replace(" ", "."));
            Individual ent = model.getIndividual(baseURI + experiment.getEntityidEntity().getAcronym().replace(" ", "."));
            exp.addProperty(aobho, person);
            exp.addProperty(usedop, ent);

        }

        //Carrega a associação do experimento com o grupo de pesquisa na ontologia apartir do banco de dados
        WasGeneratedBy wgb = new WasGeneratedBy();
        List wgbs = new ArrayList();
        wgbs = new WasGeneratedByDAO().buscarTodas();
        for (Object wgb1 : wgbs) {
            wgb = (WasGeneratedBy) wgb1;
            Individual exp = model.getIndividual(baseURI + wgb.getExperimentExperiment().getName().replace(" ", "."));
            Individual rg = model.getIndividual(baseURI + wgb.getResearchGroupidResearchGroup().getName().replace(" ", "."));
            exp.addProperty(wgbop, rg);
        }

        //Carrega os Activitys na ontologia apartir do banco de dados
        Resource resourceact = model.getResource(baseURI + "Activity");
        Activity activity = new Activity();
        List activitys = new ArrayList();
        activitys = new ActivityDAO().buscarTodas();
        for (Object activity1 : activitys) {
            activity = (Activity) activity1;
            model.createIndividual(baseURI + activity.getName().replace(" ", "."), resourceact);
        }

        //Carrega os SGWfC na ontologia apartir do banco de dados
        Resource resourcesa = model.getResource(baseURI + "SoftwareAgent");
        SGWfC sGWfC = new SGWfC();
        List sGWfCs = new ArrayList();
        sGWfCs = new SGWfCDAO().buscarTodas();
        for (Object sGWfC1 : sGWfCs) {
            sGWfC = (SGWfC) sGWfC1;
            model.createIndividual(baseURI + sGWfC.getName().replace(" ", "."), resourcesa);
        }

        //Carrega os Workflow na ontologia apartir do banco de dados
        Resource resourcewf = model.getResource(baseURI + "Workflow");
        Workflow workflow = new Workflow();
        List workflows = new ArrayList();
        workflows = new WorkflowDAO().buscarTodas();
        for (Object workflow1 : workflows) {
            workflow = (Workflow) workflow1;
            Individual wf = model.createIndividual(baseURI + workflow.getName().replace(" ", "."), resourcewf);
            Individual sg = model.getIndividual(baseURI + workflow.getSGWfCidSGWfC().getName().replace(" ", "."));
            wf.addProperty(wat, sg);
        }

        //Carrega os Task na ontologia apartir do banco de dados
        Resource resourcetask = model.getResource(baseURI + "Task");
        Task task = new Task();
        List tasks = new ArrayList();
        tasks = new TaskDAO().buscarTodas();
        for (Object task1 : tasks) {
            task = (Task) task1;
            model.createIndividual(baseURI + task.getName().replace(" ", "."), resourcetask);
        }

        //Carrega os dados do Used na ontologia apartir do banco de dados
        Used used = new Used();
        List useds = new ArrayList();
        useds = new UsedDAO().buscarTodas();
        for (Object used1 : useds) {
            used = (Used) used1;
            Individual t = model.getIndividual(baseURI + used.getTaskidTask().getName().replace(" ", "."));
            Individual w = model.getIndividual(baseURI + used.getWorkflowidWorkflow().getName().replace(" ", "."));
            w.addProperty(usedope, t);
        }

        //Carrega os dados do WasStartedByWT na ontologia apartir do banco de dados
        WasStartedByWT wsbtwt = new WasStartedByWT();
        List wsbtwts = new ArrayList();
        wsbtwts = new WasStartedByWTDAO().buscarTodas();
        for (Object wsbtwt1 : wsbtwts) {
            wsbtwt = (WasStartedByWT) wsbtwt1;
            Individual t = model.getIndividual(baseURI + wsbtwt.getTaskidTask().getName().replace(" ", "."));
            Individual w = model.getIndividual(baseURI + wsbtwt.getWorkflowidWorkflow().getName().replace(" ", "."));
            w.addProperty(s, t);
        }

        //Carrega os dados do WasEndedByWT na ontologia apartir do banco de dados
        WasEndedByWT webtwt = new WasEndedByWT();
        List webtwts = new ArrayList();
        webtwts = new WasEndedByWTDAO().buscarTodas();
        for (Object webtwt1 : webtwts) {
            webtwt = (WasEndedByWT) webtwt1;
            Individual t = model.getIndividual(baseURI + webtwt.getTaskidTask().getName().replace(" ", "."));
            Individual w = model.getIndividual(baseURI + webtwt.getWorkflowidWorkflow().getName().replace(" ", "."));
            w.addProperty(e, t);
        }

        //Carrega os dados do WasStartedBy na ontologia apartir do banco de dados
        WasStartedBy wsbt = new WasStartedBy();
        List wsbts = new ArrayList();
        wsbts = new WasStartedByDAO().buscarTodas();
        for (Object wsbt1 : wsbts) {
            wsbt = (WasStartedBy) wsbt1;
            Individual t = model.getIndividual(baseURI + wsbt.getTaskidTask().getName().replace(" ", "."));
            Individual a = model.getIndividual(baseURI + wsbt.getActivityidActivity().getName().replace(" ", "."));
            t.addProperty(s, a);
        }

        //Carrega os dados do WasEndedBy na ontologia apartir do banco de dados
        WasEndedBy webt = new WasEndedBy();
        List webts = new ArrayList();
        webts = new WasEndedByDAO().buscarTodas();
        for (Object webt1 : webts) {
            webt = (WasEndedBy) webt1;
            Individual t = model.getIndividual(baseURI + webt.getTaskidTask().getName().replace(" ", "."));
            Individual a = model.getIndividual(baseURI + webt.getActivityidActivity().getName().replace(" ", "."));
            t.addProperty(e, a);
        }

        // faz a associação entre workflow e experiemnto
        WasAssociatedWith waw = new WasAssociatedWith();
        List waws = new ArrayList();
        waws = new WasAssociatedWithDAO().buscarTodas();
        for (Object waw1 : waws) {
            waw = (WasAssociatedWith) waw1;
            Individual w = model.getIndividual(baseURI + waw.getWorkflowidWorkflow().getName().replace(" ", "."));
            Individual exp = model.getIndividual(baseURI + waw.getExperimentExperiment().getName().replace(" ", "."));
            exp.addProperty(wawop, w);
        }

        //Cria a linha de evolução baseada na correção
        WasRevisionOf wro = new WasRevisionOf();
        List wros = new ArrayList();
        wros = new WasRevisionOfDAO().buscarTodas();
        for (Object wro1 : wros) {
            wro = (WasRevisionOf) wro1;
            Individual of = model.getIndividual(baseURI + wro.getRevisionOf().getName().replace(" ", "."));
            Individual to = model.getIndividual(baseURI + wro.getRevisionTo().getName().replace(" ", "."));
            to.addProperty(wroop, of);
        }

        //Cria a linha de evolução
        WasDerivedFrom wdf = new WasDerivedFrom();
        List wdfs = new ArrayList();
        wdfs = new WasDerivedFromDAO().buscarTodas();
        for (Object wdf1 : wdfs) {
            wdf = (WasDerivedFrom) wdf1;
            Individual of = model.getIndividual(baseURI + wdf.getDerivedOf().getName().replace(" ", "."));
            Individual to = model.getIndividual(baseURI + wdf.getDerivedTo().getName().replace(" ", "."));
            to.addProperty(wdfop, of);
        }

        //Cria as portas de comunicação do workflow na ontologia
        Resource resourceip = model.getResource(baseURI + "InputPort");
        InputPort ip = new InputPort();
        List ips = new ArrayList();
        ips = new InputPortDAO().buscarTodas();
        for (Object ip1 : ips) {
            ip = (InputPort) ip1;
            Individual i = model.createIndividual(baseURI + ip.getName().replace(" ", "."), resourceip);
            Individual t = model.getIndividual(baseURI + ip.getTaskidTask().getName().replace(" ", "."));
            i.addProperty(wawop, t);
        }

        Resource resourceop = model.getResource(baseURI + "OutputPort");
        OutputPort op = new OutputPort();
        List ops = new ArrayList();
        ops = new OutputPortDAO().buscarTodas();
        for (Object op1 : ops) {
            op = (OutputPort) op1;
            Individual o = model.createIndividual(baseURI + op.getName().replace(" ", "."), resourceop);
            Individual t = model.getIndividual(baseURI + op.getTaskidTask().getName().replace(" ", "."));
            o.addProperty(wawop, t);
        }

        ActedOnBehalfOf aobo = new ActedOnBehalfOf();
        List aobos = new ArrayList();
        aobos = new ActedOnBehalfOfDAO().buscarTodas();
        for (Object aobo1 : aobos) {
            aobo = (ActedOnBehalfOf) aobo1;
            Individual i = model.createIndividual(baseURI + aobo.getInputPortidPort().getName().replace(" ", "."), resourceop);
            Individual o = model.getIndividual(baseURI + aobo.getOutputPortidPort().getName().replace(" ", "."));
            o.addProperty(aoboop, i);
        }

        //validar a nova ontologia a ser criada
        //System.out.println("Validating the ontology");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validating the ontology", "Ok"));
        InfModel modelInf = ModelFactory.createInfModel(reasoner, model);
        ValidityReport vrp1 = modelInf.validate();
        if (vrp1.isValid()) {
            //System.out.println("Valid OWL");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Valid OWL", "Yes"));
        } else {
            //System.out.println("Not valid OWL");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not valid OWL", "Sorry, a failure occurred"));
            for (Iterator i = vrp1.getReports(); i.hasNext();) {
                //System.out.println(" - " + i.next());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(" - " + i.next()));
            }
        }

        //Gerar o novo arquivo com os dados do banco na nova ontologia
        FileWriter arquivo = null;
        try {
            //caminho para o novo arquivo de ontologia
            arquivo = new FileWriter(newontology);
            //se não existir arquivo, o mesmo será criado, se não, será reescrito
        } catch (IOException ex) {
            ex.printStackTrace(); //verificando problemas
        }
        //determinando que o fluxo de saida vai para o arquivo e não para a tela            
        BufferedWriter out = new BufferedWriter(arquivo);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, model);
        //utilizar RDF/XML-ABBREV, so RDF/XML da erro no protege!        
        model.write(out, "RDF/XML-ABBREV");

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ontology successfully loaded", "OK"));

    }

    public List<String> buscartodos() {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = newontology;

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);

        List ontologys = new ArrayList();
        OntClass equipe = model.getOntClass(baseURI + "Workflow");
        OntProperty nome = model.getOntProperty(baseURI + "Used");
        String temp1;
        String temp2;
        for (ExtendedIterator<? extends OntResource> instances = equipe.listInstances(); instances.hasNext();) {
            OntResource equipeInstance = instances.next();
            ontologys.add(equipeInstance.getProperty(nome).toString().replace(baseURI, ""));
            //System.out.println("Equipe instance: " + equipeInstance.getProperty(nome).toString().replace("http://www.w3.org/ns/prov#", ""));

            // find out the resources that link to the instance
            for (StmtIterator stmts = model.listStatements(null, null, equipeInstance); stmts.hasNext();) {
                Individual ind = stmts.next().getSubject().as(Individual.class);
                // show the properties of this individual
                //System.out.println("  " + ind.getURI().toString().replace("http://www.w3.org/ns/prov#", ""));
                ontologys.add(ind.getURI().toString().replace(baseURI, ""));

                for (StmtIterator j = ind.listProperties(); j.hasNext();) {
                    Statement s = j.next();
                    //System.out.print("    " + s.getPredicate().getLocalName().toString().replace("http://www.w3.org/ns/prov#", "") + " -> ");
                    temp1 = (s.getPredicate().getLocalName().toString().replace(baseURI, "") + " -> ");
                    if (s.getObject().isLiteral()) {
                        //System.out.println(s.getLiteral().getLexicalForm().replace("http://www.w3.org/ns/prov#", ""));
                        ontologys.add(s.getLiteral().getLexicalForm().replace(baseURI, ""));
                    } else {
                        //System.out.println(s.getObject().toString().replace("http://www.w3.org/ns/prov#", ""));
                        temp2 = s.getObject().toString().replace(baseURI, "");
                        ontologys.add(temp1 + temp2);
                    }
                }
            }
        }
        return ontologys;
    }

    public List<String> buscarSPARQL(String sql) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = newontology;

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);

        if (sql.equals("")) {
            sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                    + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                    + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                    + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                    + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                    + "\n"
                    + "SELECT ?subject ?object\n"
                    + "	WHERE { ?subject prov:Used <http://www.w3.org/ns/prov#Sum>}";
        }
        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        List resultslist = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next = results.next();
            String result = null;
            result = next.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist.add(result.replace("> )", ""));
        }

        return resultslist;
    }
    
    public List<String> buscarEvolutionTo(String workflow) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = newontology;

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "\n"
                + "SELECT ?subject ?object\n"
                + "	WHERE { ?subject prov:EvolutionOf <http://www.w3.org/ns/prov#" + workflow + ">}";
        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        List resultslist = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next = results.next();
            String result = null;
            result = next.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist.add(result.replace("> )", ""));
        }

        return resultslist;
    }

    /**
     * Search a Evolution To a Task
     * 
     * @param task
     * @return 
     */
    public List<String> buscarEvolutionToTask(String task) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = newontology;

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "\n"
                + "SELECT ?subject ?object\n"
                + "	WHERE { ?subject prov:Activity <http://www.w3.org/ns/prov#" + task + ">}";

        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        List resultslist = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next = results.next();
            String result = null;
            result = next.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist.add(result.replace("> )", ""));
        }
        return resultslist;
    }

    public List<String> buscarEvolutionOf(String workflow) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = newontology;

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "\n"
                + "SELECT ?subject ?object\n"
                + "	WHERE { ?subject prov:EvolutionTo <http://www.w3.org/ns/prov#" + workflow + ">}";
        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        List resultslist = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next = results.next();
            String result = null;
            result = next.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist.add(result.replace("> )", ""));
        }

        return resultslist;
    }

    public List<String> Similar(int workflow) {
        //variavel global
        OntModel model;
        //uri da ontologia
        String baseURI = "http://www.w3.org/ns/prov#";
        //caminho fisico da ontologia
        String ontologia = newontology;

        //inicia a maquina de inferencia e carrega a ontologia nela
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(ontologia);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(ontModel);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
        ontModelSpec.setReasoner(reasoner);
        //ontologia carregada na máquina de inferencia
        model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        Used used = new Used();
        List useds = new ArrayList();
        useds = UsedDAO.getInstance().buscar(workflow);
        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX prov: <http://www.w3.org/ns/prov#>\n"
                + "\n"
                + "SELECT ?subject\n"
                + "	WHERE { \n"
                + "{";

        String sql2 = "";
        for (Object u : useds) {
            used = (Used) u;

            sql2 = sql2 + " ?subject prov:Used <http://www.w3.org/ns/prov#" + used.getTaskidTask().getName() + ">.";
        }

        sql = sql + sql2 + "}}";

        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        List resultslist = new ArrayList();
        while (results.hasNext()) {
            QuerySolution next = results.next();
            String result = null;
            result = next.toString().replace("( ?subject = <http://www.w3.org/ns/prov#", "");
            resultslist.add(result.replace("> )", ""));
        }

        return resultslist;
    }
}

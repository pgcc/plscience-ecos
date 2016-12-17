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
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

/**
 * Classe responsável pelo tratamento das informações recebidas via serviço ou
 * via banco de dados para incluí-las na ontologia
 *
 * @author Lenita
 */
public class DataHandler {

    private final OntologyController controller;

    public DataHandler() {
        controller = OntologyController.getInstance();
    }

    /**
     * Classe para a inclusão de Triplas RDF na ontologia. Permite a inclusão de
     * novos indivíduos, propriedades ou relacionamentos.
     *
     * @param subject
     * @param predicate
     * @param object
     * @return
     */
    public boolean addTriple(String subject, String predicate, String object) {
        Resource sbj = controller.getInfModel().getResource(subject);
        if (sbj == null) {
            sbj = controller.getInfModel().createResource(subject);
        }

        Property prop = controller.getInfModel().getProperty(predicate);
        if (prop == null) {
            prop = ResourceFactory.createProperty(predicate);
        }

        Resource obj = controller.getInfModel().getResource(object);
        if (obj != null) {
            sbj.addProperty(prop, obj);
            return true;
        }
        return false;
    }

    public static void loadDAO() {

        Model model = ModelFactory.createDefaultModel();

        model.read(OntologyController.PROV_ONTOLOGY);
        model.read(OntologyController.PROVONE_ONTOLOGY);
        model.read(OntologyController.ONTOLOGY);

        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, model);
        ontModel.prepare();

        //Prepara as objectsProperties
        ObjectProperty actedOnBehalfOf = ontModel.getObjectProperty(OntologyController.PROV_URI + "actedOnBehalfOf");
        ObjectProperty wasAttributedTo = ontModel.getObjectProperty(OntologyController.PROV_URI + "wasAttributedTo");
        ObjectProperty wasInfluencedBy = ontModel.getObjectProperty(OntologyController.PROV_URI + "wasInfluencedBy");
        ObjectProperty hasSubProgram = ontModel.getObjectProperty(OntologyController.PROVONE_URI + "hasSubProgram");
        ObjectProperty hadPlan = ontModel.getObjectProperty(OntologyController.PROV_URI + "hadPlan");
        ObjectProperty hadMember = ontModel.getObjectProperty(OntologyController.PROV_URI + "hadMember");
        ObjectProperty hasInPort = ontModel.getObjectProperty(OntologyController.PROVONE_URI + "hasInPort");
        ObjectProperty hasOutPort = ontModel.getObjectProperty(OntologyController.PROVONE_URI + "hasOutPort");
        ObjectProperty wasDerivedFrom = ontModel.getObjectProperty(OntologyController.PROV_URI + "wasDerivedFrom");

        //Prepara as dataProperties
        DatatypeProperty startedAtTime = ontModel.getDatatypeProperty(OntologyController.PROV_URI + "startedAtTime");
        DatatypeProperty endedAtTime = ontModel.getDatatypeProperty(OntologyController.PROV_URI + "endedAtTime");

        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Loading data from database", "OK"));
        System.out.println("Loading data from database");

        //Carrega as Entidades na ontologia apartir do banco de dados
        Resource resourceorg = ontModel.getResource(OntologyController.PROV_URI + "Organization");
        List<Entity> entitys = new EntityDAO().buscarTodas();
        for (Entity entity : entitys) {
            ontModel.createIndividual(OntologyController.URI + entity.getAcronym().replace(" ", "."), resourceorg);
        }

        //Carrega os Grupos de pesquisa na ontologia apartir do banco de dados
        Resource resourcerg = ontModel.getResource(OntologyController.URI + "ResearchGroup");
        List<ResearchGroup> researchGroups = new ResearchGroupDAO().buscarTodas();
        for (ResearchGroup researchGroup : researchGroups) {
            ontModel.createIndividual(OntologyController.URI + researchGroup.getName().replace(" ", "."), resourcerg);
        }

        //Carrega os Agentes na ontologia apartir do banco de dados
        Resource resourceperson = ontModel.getResource(OntologyController.PROVONE_URI + "User");
        List<Agent> agents = new AgentDAO().buscarTodas();
        for (Agent agent : agents) {
            ontModel.createIndividual(OntologyController.URI + agent.getName().replace(" ", "."), resourceperson);
        }

        //Define que cada grupo de pesquisa pertence a uma instituição
        List<IsPartOf> ipos = new IsPartOfDAO().buscarTodas();
        for (IsPartOf ipo : ipos) {
            Individual rg = ontModel.getIndividual(OntologyController.URI + ipo.getResearchGroupidResearchGroup().getName().replace(" ", "."));
            Individual inst = ontModel.getIndividual(OntologyController.URI + ipo.getAgentidAgent().getInstitution().getAcronym().replace(" ", "."));
            rg.addProperty(actedOnBehalfOf, inst);
        }

        //define que cada agente atua em um grupo de pesquisa
        List<IsPartOf> ipo2s = new IsPartOfDAO().buscarTodas();
        for (IsPartOf ipo : ipo2s) {
            Individual p = ontModel.getIndividual(OntologyController.URI + ipo.getAgentidAgent().getName().replace(" ", "."));
            Individual g = ontModel.getIndividual(OntologyController.URI + ipo.getResearchGroupidResearchGroup().getName().replace(" ", "."));
            g.addProperty(actedOnBehalfOf, p);
        }

        //Carrega os Experimentos na ontologia apartir do banco de dados, associando aos agentes e as entidades
        Resource resourceexp = ontModel.getResource(OntologyController.URI + "Experiment");
        List<Experiment> experiments = new ExperimentDAO().getAll();
        for (Experiment experiment : experiments) {
            Individual exp = ontModel.createIndividual(OntologyController.URI + experiment.getName().replace(" ", "."), resourceexp);
            if (experiment.getIdAgent() != null) {
                Individual person = ontModel.getIndividual(OntologyController.URI + experiment.getIdAgent().getName().replace(" ", "."));
                exp.addProperty(wasAttributedTo, person);
            }
            if (experiment.getEntityidEntity() != null) {
                Individual ent = ontModel.getIndividual(OntologyController.URI + experiment.getEntityidEntity().getAcronym().replace(" ", "."));
                exp.addProperty(wasInfluencedBy, ent);
            }

        }

        //Carrega a associação do experimento com o grupo de pesquisa na ontologia apartir do banco de dados
        List<WasGeneratedBy> wgbs = new WasGeneratedByDAO().buscarTodas();
        for (WasGeneratedBy wgb : wgbs) {
            if (wgb.getExperimentExperiment() != null && wgb.getResearchGroupidResearchGroup() != null) {
                Individual exp = ontModel.getIndividual(OntologyController.URI + wgb.getExperimentExperiment().getName().replace(" ", "."));
                Individual rg = ontModel.getIndividual(OntologyController.URI + wgb.getResearchGroupidResearchGroup().getName().replace(" ", "."));
                exp.addProperty(wasAttributedTo, rg);
            }
        }

        //Carrega as Atividades na ontologia apartir do banco de dados
        Resource resourceact = ontModel.getResource(OntologyController.PROV_URI + "Activity");
        List<Activity> activitys = new ActivityDAO().buscarTodas();
        for (Activity activity : activitys) {
            ontModel.createIndividual(OntologyController.URI + activity.getName().replace(" ", "."), resourceact);
        }

        //Carrega os SGWfC na ontologia apartir do banco de dados
        Resource resourcesa = ontModel.getResource(OntologyController.URI + "Wfms");
        List<SGWfC> sGWfCs = new SGWfCDAO().buscarTodas();
        for (SGWfC sGWfC : sGWfCs) {
            ontModel.createIndividual(OntologyController.URI + sGWfC.getName().replace(" ", "."), resourcesa);
        }

        //Carrega os Workflows na ontologia apartir do banco de dados
        Resource resourcewf = ontModel.getResource(OntologyController.PROVONE_URI + "Workflow");
        List<Workflow> workflows = new WorkflowDAO().buscarTodas();
        for (Workflow workflow : workflows) {
            if (workflow.getSGWfCidSGWfC() != null) {
                Individual wf = ontModel.createIndividual(OntologyController.URI + workflow.getName().replace(" ", "."), resourcewf);
                Individual sg = ontModel.getIndividual(OntologyController.URI + workflow.getSGWfCidSGWfC().getName().replace(" ", "."));
                wf.addProperty(wasAttributedTo, sg);
            }
        }

        //Carrega as Tarefas na ontologia apartir do banco de dados
        Resource resourcetask = ontModel.getResource(OntologyController.PROVONE_URI + "Program");
        Resource resourceexec = ontModel.getResource(OntologyController.PROVONE_URI + "Execution");
        List<Task> tasks = new TaskDAO().buscarTodas();
        for (Task task : tasks) {
            ontModel.createIndividual(OntologyController.URI + task.getName().replace(" ", "."), resourcetask);
            ontModel.createIndividual(OntologyController.URI + task.getName().replace(" ", ".") + "_Exec", resourceexec);
        }

        //Carrega os dados do Used na ontologia apartir do banco de dados
        List<Used> useds = new UsedDAO().buscarTodas();
        for (Used used : useds) {
            Individual t = ontModel.getIndividual(OntologyController.URI + used.getTaskidTask().getName().replace(" ", "."));
            Individual e = ontModel.getIndividual(OntologyController.URI + used.getTaskidTask().getName().replace(" ", ".") + "_Exec");
            Individual w = ontModel.getIndividual(OntologyController.URI + used.getWorkflowidWorkflow().getName().replace(" ", "."));
            w.addProperty(hasSubProgram, t);
            e.addProperty(hadPlan, w);
        }

        //Carrega os dados do WasStartedByWT na ontologia apartir do banco de dados
        List<WasStartedByWT> wsbtwts = new WasStartedByWTDAO().buscarTodas();
        for (WasStartedByWT wsbtwt : wsbtwts) {
            Individual t = ontModel.getIndividual(OntologyController.URI + wsbtwt.getTaskidTask().getName().replace(" ", "."));
            Individual e = ontModel.getIndividual(OntologyController.URI + wsbtwt.getTaskidTask().getName().replace(" ", ".") + "_Exec");
            Individual w = ontModel.getIndividual(OntologyController.URI + wsbtwt.getWorkflowidWorkflow().getName().replace(" ", "."));
            w.addProperty(hasSubProgram, t);
            e.addProperty(startedAtTime, wsbtwt.getStarted().toString());
            e.addProperty(hadPlan, w);
        }

        //Carrega os dados do WasEndedByWT na ontologia apartir do banco de dados
        List<WasEndedByWT> webtwts = new WasEndedByWTDAO().buscarTodas();
        for (WasEndedByWT webtwt : webtwts) {
            Individual t = ontModel.getIndividual(OntologyController.URI + webtwt.getTaskidTask().getName().replace(" ", "."));
            Individual e = ontModel.getIndividual(OntologyController.URI + webtwt.getTaskidTask().getName().replace(" ", ".") + "_Exec");
            Individual w = ontModel.getIndividual(OntologyController.URI + webtwt.getWorkflowidWorkflow().getName().replace(" ", "."));
            w.addProperty(hasSubProgram, t);
            e.addProperty(endedAtTime, webtwt.getEnded().toString());
            e.addProperty(hadPlan, w);
        }

        //Carrega os dados do WasStartedBy na ontologia apartir do banco de dados
        List<WasStartedBy> wsbts = new WasStartedByDAO().buscarTodas();
        for (WasStartedBy wsbt : wsbts) {
            Individual t = ontModel.getIndividual(OntologyController.URI + wsbt.getTaskidTask().getName().replace(" ", "."));
            Individual e = ontModel.getIndividual(OntologyController.URI + wsbt.getTaskidTask().getName().replace(" ", ".") + "_Exec");
            Individual a = ontModel.getIndividual(OntologyController.URI + wsbt.getActivityidActivity().getName().replace(" ", "."));
            Calendar c = new GregorianCalendar();
            c.setTime(wsbt.getDateStarted());
            Literal value = model.createTypedLiteral(c);
            e.addProperty(startedAtTime, value);
        }

        //Carrega os dados do WasEndedBy na ontologia apartir do banco de dados
        List<WasEndedBy> webts = new WasEndedByDAO().buscarTodas();
        for (WasEndedBy webt : webts) {
            Individual t = ontModel.getIndividual(OntologyController.URI + webt.getTaskidTask().getName().replace(" ", "."));
            Individual e = ontModel.getIndividual(OntologyController.URI + webt.getTaskidTask().getName().replace(" ", ".") + "_Exec");
            Individual a = ontModel.getIndividual(OntologyController.URI + webt.getActivityidActivity().getName().replace(" ", "."));
            Calendar c = new GregorianCalendar();
            c.setTime(webt.getDateEnded());
            Literal value = model.createTypedLiteral(c);
            e.addProperty(endedAtTime, value);
        }

        // Faz a associação entre workflow e experiemnto
        List<WasAssociatedWith> waws = new WasAssociatedWithDAO().buscarTodas();
        for (WasAssociatedWith waw : waws) {
            Individual w = ontModel.getIndividual(OntologyController.URI + waw.getWorkflowidWorkflow().getName().replace(" ", "."));
            Individual exp = ontModel.getIndividual(OntologyController.URI + waw.getExperimentExperiment().getName().replace(" ", "."));
            exp.addProperty(hadMember, w);
        }

        //Cria a linha de evolução baseada na correção
        List<WasRevisionOf> wros = new WasRevisionOfDAO().buscarTodas();
        for (WasRevisionOf wro : wros) {
            Individual of = ontModel.getIndividual(OntologyController.URI + wro.getRevisionOf().getName().replace(" ", "."));
            Individual to = ontModel.getIndividual(OntologyController.URI + wro.getRevisionTo().getName().replace(" ", "."));
            to.addProperty(wasDerivedFrom, of);
        }

        //Cria a linha de evolução
        List<WasDerivedFrom> wdfs = new WasDerivedFromDAO().buscarTodas();
        for (WasDerivedFrom wdf : wdfs) {
            Individual of = ontModel.getIndividual(OntologyController.URI + wdf.getDerivedOf().getName().replace(" ", "."));
            Individual to = ontModel.getIndividual(OntologyController.URI + wdf.getDerivedTo().getName().replace(" ", "."));
            to.addProperty(wasDerivedFrom, of);
        }
        //Cria as portas de comunicação do workflow na ontologia
        Resource resourceip = ontModel.getResource(OntologyController.PROVONE_URI + "Port");
        List<InputPort> ips = new InputPortDAO().buscarTodas();
        for (InputPort ip : ips) {
            Individual i = ontModel.createIndividual(OntologyController.URI + ip.getName().replace(" ", "."), resourceip);
            Individual t = ontModel.getIndividual(OntologyController.URI + ip.getTaskidTask().getName().replace(" ", "."));
            i.addProperty(hasInPort, t);
        }

        Resource resourceop = ontModel.getResource(OntologyController.PROVONE_URI + "Port");
        List<OutputPort> ops = new OutputPortDAO().buscarTodas();
        for (OutputPort op : ops) {
            Individual o = ontModel.createIndividual(OntologyController.URI + op.getName().replace(" ", "."), resourceop);
            Individual t = ontModel.getIndividual(OntologyController.URI + op.getTaskidTask().getName().replace(" ", "."));
            o.addProperty(hasOutPort, t);
        }

        List<ActedOnBehalfOf> aobos = new ActedOnBehalfOfDAO().buscarTodas();
        for (ActedOnBehalfOf aobo : aobos) {
            Individual i = ontModel.createIndividual(OntologyController.URI + aobo.getInputPortidPort().getName().replace(" ", "."), resourceop);
            Individual o = ontModel.getIndividual(OntologyController.URI + aobo.getOutputPortidPort().getName().replace(" ", "."));
            o.addProperty(actedOnBehalfOf, i);
        }

        //validar a nova ontologia a ser criada
        System.out.println("Validating the loadOntology");
        //    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validating the ontology", "Ok"));

        //Gerar o novo arquivo com os dados do banco na nova ontologia
        FileWriter arquivo = null;
        try {
            //caminho para o novo arquivo de ontologia
            arquivo = new FileWriter(OntologyController.ONTOLOGY_LOAD);
            //se não existir arquivo, o mesmo será criado, se não, será reescrito
        } catch (IOException ex) {
            ex.printStackTrace(); //verificando problemas
        }
        //determinando que o fluxo de saida vai para o arquivo e não para a tela            
        BufferedWriter out = new BufferedWriter(arquivo);
        //ontologia carregada
        ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ontModel);
        //utilizar RDF/XML-ABBREV, so RDF/XML da erro no protege!        
        ontModel.write(out);
        //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ontology successfully loaded", "OK"));

    }

}

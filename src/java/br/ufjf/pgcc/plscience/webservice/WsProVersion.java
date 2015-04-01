/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.webservice;

import br.ufjf.pgcc.plscience.dao.ActivityDAO;
import br.ufjf.pgcc.plscience.dao.AgentDAO;
import br.ufjf.pgcc.plscience.dao.EntityDAO;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import br.ufjf.pgcc.plscience.dao.TaskDAO;
import br.ufjf.pgcc.plscience.dao.WorkflowDAO;
import br.ufjf.pgcc.plscience.model.Activity;
import br.ufjf.pgcc.plscience.model.Agent;
import br.ufjf.pgcc.plscience.model.Entity;
import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.model.Task;
import br.ufjf.pgcc.plscience.model.Workflow;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author tassio
 */
@WebService(serviceName = "WsProVersion")
public class WsProVersion {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Oi " + txt + ", seja bem vindo!";
    }

    @WebMethod(operationName = "workflowPersist")
    public String workflowPersist(@WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "version") String version,
            @WebParam(name = "date") Date date) {

        Workflow wf = new Workflow();
        wf.setName(name);
        wf.setDescription(description);
        wf.setVersion(version);
        wf.setDateVersion(date);
        wf = WorkflowDAO.getInstance().persistir(wf);

        return "Registro inserido com sucesso...";
    }

    @WebMethod(operationName = "agentPersist")
    public String agentPersist(@WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "function") String function) {

        Agent ag = new Agent();
        ag.setName(name);
        ag.setDescription(description);
        ag.setFunction(function);
        ag = AgentDAO.getInstance().persistir(ag);

        return "Registro inserido com sucesso...";
    }

    @WebMethod(operationName = "entityPersist")
    public String entityPersist(@WebParam(name = "name") String name) {

        Entity en = new Entity();
        en.setName(name);
        en = EntityDAO.getInstance().persistir(en);

        return "Registro inserido com sucesso...";
    }

    @WebMethod(operationName = "activityPersist")
    public String activityPersist(@WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "function") String function,
            @WebParam(name = "identity") Entity identity) {

        Activity ac = new Activity();
        ac.setName(name);
        ac.setDescription(description);
        ac.setFunction(function);
        ac.setEntityidEntity(identity);
        ac = ActivityDAO.getInstance().persistir(ac);

        return "Registro inserido com sucesso...";
    }

    @WebMethod(operationName = "taskPersist")
    public String taskPersist(@WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "type") String type,
            @WebParam(name = "idworkflow") Workflow idWorkflow) {

        Task tk = new Task();
        tk.setName(name);
        tk.setDescription(description);
        tk.setType(type);
        tk.setWorkflowidWorkflow(idWorkflow);
        tk = TaskDAO.getInstance().persistir(tk);

        return "Registro inserido com sucesso...";
    }

//    @WebMethod(operationName = "expirimentPersist")
//    public String expirimentPersist(@WebParam(name = "name") String name,
//            @WebParam(name = "description") String description,
//            @WebParam(name = "started") Date started,
//            @WebParam(name = "ended") Date ended,
//            @WebParam(name = "version") String version,
//            @WebParam(name = "identity") Entity identity,
//            @WebParam(name = "idagent") Agent idagent,
//            @WebParam(name = "idactivity") Activity activity
//    ) 
//    {
//
//        Experiment ex = new Experiment();
//        ex.setName(name);
//        ex.setDescription(description);
//        ex.setDateStarted(started);
//        ex.setDateEnded(ended);
//        ex.setVersion(version);
//        ex.setEntityidEntity(identity);
//        ex.setActivityidActivity(activity);
//        ex = ExperimentDAO.getInstance().persistir(ex);
//
//        return "Registro inserido com sucesso...";
//    }

}

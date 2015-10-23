package br.ufjf.pgcc.plscience.webservice;

import br.ufjf.pgcc.plscience.dao.ActedOnBehalfOfDAO;
import br.ufjf.pgcc.plscience.dao.ActivityDAO;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import br.ufjf.pgcc.plscience.dao.InputPortDAO;
import br.ufjf.pgcc.plscience.dao.OutputPortDAO;
import br.ufjf.pgcc.plscience.dao.TaskDAO;
import br.ufjf.pgcc.plscience.dao.UsedDAO;
import br.ufjf.pgcc.plscience.dao.WasAssociatedWithDAO;
import br.ufjf.pgcc.plscience.dao.WasDerivedFromDAO;
import br.ufjf.pgcc.plscience.dao.WasEndedByDAO;
import br.ufjf.pgcc.plscience.dao.WasInformedByDAO;
import br.ufjf.pgcc.plscience.dao.WasRevisionOfDAO;
import br.ufjf.pgcc.plscience.dao.WasStartedByDAO;
import br.ufjf.pgcc.plscience.dao.WorkflowDAO;
import br.ufjf.pgcc.plscience.model.ActedOnBehalfOf;
import br.ufjf.pgcc.plscience.model.Activity;
import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.model.InputPort;
import br.ufjf.pgcc.plscience.model.OutputPort;
import br.ufjf.pgcc.plscience.model.Task;
import br.ufjf.pgcc.plscience.model.Used;
import br.ufjf.pgcc.plscience.model.WasAssociatedWith;
import br.ufjf.pgcc.plscience.model.WasDerivedFrom;
import br.ufjf.pgcc.plscience.model.WasEndedBy;
import br.ufjf.pgcc.plscience.model.WasInformedBy;
import br.ufjf.pgcc.plscience.model.WasRevisionOf;
import br.ufjf.pgcc.plscience.model.WasStartedBy;
import br.ufjf.pgcc.plscience.model.WasStartedByWT;
import br.ufjf.pgcc.plscience.model.Workflow;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author tassio
 */
@WebService(serviceName = "WsProVersion")
public class WsProVersion {

    @WebMethod(operationName = "InitialConfiguration")
    public void InitialConfiguration(@WebParam(name = "experiment") int experiment,
            @WebParam(name = "workflow") int workflow,
            @WebParam(name = "taskstarted") int taskstarted,
            @WebParam(name = "valueinput") String valueinput,
            @WebParam(name = "activity") int activity,
            @WebParam(name = "workflowderivedfrom") int workflowderivedfrom,
            @WebParam(name = "typederived") String typederived,
            @WebParam(name = "workflowrevisionof") int workflowrevisionof,
            @WebParam(name = "typerevision") String typerevision
    ) {

        Task t = new Task();
        t = TaskDAO.getInstance().buscarid(taskstarted);

        Workflow wf = new Workflow();
        wf = WorkflowDAO.getInstance().buscar(workflow);

        Workflow wfdf = new Workflow();
        wfdf = WorkflowDAO.getInstance().buscar(workflowderivedfrom);

        Workflow wfro = new Workflow();
        wfro = WorkflowDAO.getInstance().buscar(workflowrevisionof);

        Experiment exp = new Experiment();
        exp = ExperimentDAO.getInstance().getExperimentById(experiment);

        Activity act = new Activity();
        act = ActivityDAO.getInstance().buscar(activity);

        //Table WasStartedBy
        if (taskstarted != 0 && activity != 0) {
            WasStartedBy ws = new WasStartedBy();
            try {
                ws.setTaskidTask(t);
                ws.setActivityidActivity(act);

                //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Calendar calendar = Calendar.getInstance();
                Timestamp timestamp = new Timestamp(calendar.getTime().getTime());

                ws.setDateStarted(timestamp);
                ws.setDescription("Task " + t.getName() + " started to activity " + act.getName());
                WasStartedByDAO.getInstance().persistir(ws);
            } catch (Exception e) {
                System.err.println("Failure in WasStartedBy of " + t.getName() + " with " + act.getName());
            }
            ws = null;
        }

        //Table InputPort
        if (taskstarted != 0 && valueinput != null) {
            InputPort ip = new InputPort();
            try {
                ip.setTaskidTask(t);
                ip.setValue(valueinput);
                ip.setName("Starting port workflow to " + t.getName());
                ip.setDescription("Port of task with valeu " + valueinput);
                ip.setWf(workflow);
                InputPortDAO.getInstance().persistir(ip);
            } catch (Exception e) {
                System.err.println("Failure in InputPort of " + t.getName() + " with " + valueinput);
            }
        }

        //Table Used
        if (taskstarted != 0 && workflow != 0) {
            Used u = new Used();
            try {
                u.setWorkflowidWorkflow(wf);
                u.setTaskidTask(t);
                u.setDescription("Task " + t.getName() + " was used in workflow " + wf.getName());
                UsedDAO.getInstance().persistir(u);
            } catch (Exception e) {
                System.err.println("Failure in Used to " + wf.getName() + " with " + t.getName());
            }

            // tabela WasStartedByWT
            WasStartedByWT wsbwt = new WasStartedByWT();

            try {
                wsbwt.setTaskidTask(t);
                wsbwt.setWorkflowidWorkflow(wf);
                //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Calendar calendar = Calendar.getInstance();
                Timestamp timestamp = new Timestamp(calendar.getTime().getTime());
                wsbwt.setStarted(timestamp);
            } catch (Exception e) {
                System.err.println("Failure in WasStartedByWT to " + wf.getName() + " with " + t.getName());
            }

        }

        //Table WasDerivedFrom
        if (workflowderivedfrom != 0 && workflow != 0) {
            WasDerivedFrom wdf = new WasDerivedFrom();
            try {
                wdf.setDerivedOf(wfdf);
                wdf.setDerivedTo(wf);
                wdf.setType(typederived);
                WasDerivedFromDAO.getInstance().persistir(wdf);
            } catch (Exception e) {
                System.err.println("Failure in WasDerivedFrom to " + wf.getName() + " with " + wfdf.getName());
            }
        }

        //Table WasAssociatedWith
        if (experiment != 0 && workflow != 0) {
            WasAssociatedWith wat = new WasAssociatedWith();
            try {
                wat.setExperimentExperiment(exp);
                wat.setWorkflowidWorkflow(wf);
                wat.setDescription("Workflow " +wf.getName()+ " was attributed to experimento " + exp.getName());
                WasAssociatedWithDAO.getInstance().persistir(wat);
            } catch (Exception e) {
                System.err.println("Failure in WasAttributedTo " + wf.getName() +" with "+ exp.getName());
            }
        }

        //Table WasRevisionOf
        if (workflowrevisionof != 0 && workflow != 0) {
            WasRevisionOf wro = new WasRevisionOf();
            try {
                wro.setRevisionTo(wf);
                wro.setRevisionOf(wfro);
                wro.setType(typerevision);
                WasRevisionOfDAO.getInstance().persistir(wro);
            } catch (Exception e) {
                System.err.println("Failure in WasRevisionOf " + wf.getName() +" with "+ wfro.getName());
            }
        }
    }

    @WebMethod(operationName = "Process")
    public void Process(@WebParam(name = "workflow") int workflow,
            @WebParam(name = "taskoutput") int taskoutput,
            @WebParam(name = "taskinput") int taskinput,
            @WebParam(name = "activity") int activity,
            @WebParam(name = "valueoutput") String valueoutput,
            @WebParam(name = "valueinput") String valueinput
    ) {

        Workflow wf = new Workflow();
        wf = WorkflowDAO.getInstance().buscar(workflow);

        Task to = new Task();
        to = TaskDAO.getInstance().buscarid(taskoutput);

        Task ti = new Task();
        ti = TaskDAO.getInstance().buscarid(taskinput);

        Activity act = new Activity();
        act = ActivityDAO.getInstance().buscar(activity);

        //Table ActedOnBehalfOf
        if (valueoutput != null && taskoutput != 0 && taskinput != 0 && valueinput != null) {
            OutputPort op = new OutputPort();
            try {
                op.setTaskidTask(to);
                op.setValue(valueoutput);
                op.setName("Ended Port Task " + to.getName());
                op.setDescription("Task " +to.getName()+ " output with valueoutput " + valueoutput);
                op.setWf(workflow);
                op = OutputPortDAO.getInstance().persistir(op);
            } catch (Exception e) {
                System.err.println("Failure in OutputPort " + to.getName());
            }

            InputPort ip = new InputPort();
            try {
                ip.setTaskidTask(ti);
                ip.setValue(valueinput);
                ip.setName("Starting port task " + ti.getName());
                ip.setDescription("Task " +ti.getName() + " input with valueinput " + valueinput);
                ip.setWf(workflow);
                ip = InputPortDAO.getInstance().persistir(ip);
            } catch (Exception e) {
                System.err.println("Failure in InputPort " + ti.getName());
            }

            //Table ActedOnBehalfOf
            ActedOnBehalfOf waw = new ActedOnBehalfOf();
            try {
                waw.setInputPortidPort(ip);
                waw.setOutputPortidPort(op);
                waw.setDescription("Task " + to.getName() + " acted on behalf of task " + ti.getName());
                waw.setWf(workflow);
                ActedOnBehalfOfDAO.getInstance().persistir(waw);
            } catch (Exception e) {
                System.err.println("Failure in WasAssociatedWith " + to.getName() + " with " + ti.getName());
            }

            op = null;
            ip = null;
            waw = null;
        }

        //Table InputPort
        if (valueinput != null && taskinput != 0) {

            InputPort ip = new InputPort();
            try {
                ip.setTaskidTask(ti);
                ip.setValue(valueinput);
                ip.setName("Starting port task " + ti.getName());
                ip.setDescription("Task " +ti.getName() + " input with valueinput " + valueinput);
                ip.setWf(workflow);
                ip = InputPortDAO.getInstance().persistir(ip);
            } catch (Exception e) {
                System.err.println("Failure in InputPort " + ti.getName());
            }

            ip = null;

        }

        //Table OutputPort
        if (valueoutput != null && taskoutput != 0) {
            OutputPort op = new OutputPort();
            try {
                op.setTaskidTask(to);
                op.setValue(valueoutput);
                op.setName("Ended Port Task " + to.getName());
                op.setDescription("Task " + to.getName() + " output with valueoutput " + valueoutput);
                op.setWf(workflow);
                op = OutputPortDAO.getInstance().persistir(op);
            } catch (Exception e) {
                System.err.println("Failure in Output " + to.getName());
            }

            op = null;
        }

        //Table Used
        if (taskinput != 0 && workflow != 0) {
            Used u = new Used();
            try {
                u.setWorkflowidWorkflow(wf);
                u.setTaskidTask(ti);
                u.setDescription("Task " + ti.getName() + " was used in workflow " + wf.getName());
                UsedDAO.getInstance().persistir(u);
            } catch (Exception e) {
                System.err.println("Failure in Used "+ ti.getName() +" with "+ wf.getName());
            }
            u = null;
        }

        //Table WasStartedBy
        if (taskinput != 0 && activity != 0) {
            WasStartedBy ws = new WasStartedBy();
            try {
                ws.setTaskidTask(ti);
                ws.setActivityidActivity(act);

                //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Calendar calendar = Calendar.getInstance();
                Timestamp timestamp = new Timestamp(calendar.getTime().getTime());

                ws.setDateStarted(timestamp);
                ws.setDescription("Task " + ti.getName() + " started to activity " + act.getName());
                WasStartedByDAO.getInstance().persistir(ws);
            } catch (Exception e) {
                System.err.println("Failure in WasStartedBy " + ti.getName() + " with " + act.getName());
            }
            ws = null;
        }

        //Table WasEndedBy
        if (taskoutput != 0 && activity != 0) {
            WasEndedBy we = new WasEndedBy();
            try {
                we.setTaskidTask(to);
                we.setActivityidActivity(act);

                //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Calendar calendar = Calendar.getInstance();
                Timestamp timestamp = new Timestamp(calendar.getTime().getTime());

                we.setDateEnded(timestamp);
                we.setDescription("Task " + to.getName() + " ended to activity " + act.getName());
                WasEndedByDAO.getInstance().persistir(we);
            } catch (Exception e) {
                System.err.println("Failure in WasEndedBy " + to.getName() + " with " + act.getName());
            }
            we = null;
        }

        //Table WasInformedBy
        if (taskoutput != 0 && activity != 0 && valueoutput != null) {
            WasInformedBy wi = new WasInformedBy();
            try {
                wi.setTaskidTask(to);
                wi.setActivityidActivity(act);

                wi.setDescription("Task " + to.getName() + " was successful for activity " + act.getName());
                WasInformedByDAO.getInstance().persistir(wi);
            } catch (Exception e) {
                System.err.println("Failure in WasInformedBy " + to.getName() + " with " + act.getName());
            }
            wi = null;
        } else if (taskoutput != 0 && activity != 0) {
            WasInformedBy wi = new WasInformedBy();
            try {
                wi.setTaskidTask(to);
                wi.setActivityidActivity(act);

                wi.setDescription("Task " + to.getName() + " was unsuccessful for activity " + act.getName());
                WasInformedByDAO.getInstance().persistir(wi);
            } catch (Exception e) {
                System.err.println("Failure in WasInformedBy " + to.getName() + " with " + act.getName());
            }
            wi = null;
        } else {
            System.err.println("Failure in WasInformedBy Global");
        }

    }

}

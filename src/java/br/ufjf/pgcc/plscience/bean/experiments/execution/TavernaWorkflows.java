/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.pgcc.plscience.bean.experiments.execution;

import br.ufjf.pgcc.plscience.bean.experiments.Workspace;
import br.ufjf.pgcc.plscience.dao.TavernaWorkflowDAO;
import br.ufjf.pgcc.plscience.dao.TavernaWorkflowInputDAO;
import br.ufjf.pgcc.plscience.dao.TavernaWorkflowRunDAO;
import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.model.TavernaWorkflow;
import br.ufjf.pgcc.plscience.model.TavernaWorkflowInput;
import br.ufjf.pgcc.plscience.model.TavernaWorkflowRun;
import br.ufjf.pgcc.plscience.util.BeanUtil;
import br.ufjf.pgcc.plscience.util.StringUtil;
import br.ufjf.taverna.core.TavernaClient;
import br.ufjf.taverna.model.input.TavernaInput;
import br.ufjf.taverna.model.run.TavernaRun;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class TavernaWorkflows implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final TavernaClient client;
    private final Workspace workspace;
    private Experiment experiment;
    private List<TavernaWorkflow> workflows;
    private TavernaWorkflow selectedWorkflow;
    
    private List<TavernaRun> tavernaServerRuns;
    private TavernaRun selectedTavernaServerRun;
    
    public TavernaWorkflows() {
        client = new TavernaClient();
        client.setBaseUri("http://ec2-54-191-44-161.us-west-2.compute.amazonaws.com:8080/TavernaServer-2.5.4/rest");
        client.setAuthorization("taverna", "taverna");   
        
        workspace = (Workspace) BeanUtil.getManagedBean("workspace");
        if (workspace != null) {
            experiment = workspace.getExperiment();
        }
    }
    
    public void newRun() {
        TavernaWorkflowRun run = new TavernaWorkflowRun();
        run.setTavernaWorkflow(selectedWorkflow);
        try {
            File t2flow = new File("tmp.t2flow");
            FileUtils.writeStringToFile(t2flow, selectedWorkflow.getT2flow());
            String uuid = client.create("tmp.t2flow");
            t2flow.delete();
            run.setUuid(uuid);
            run.setStatus(client.getStatus(uuid));
            run = new TavernaWorkflowRunDAO().save(run);
            workspace.setTavernaRun(run);
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/experiments/execution/taverna/run.xhtml?tab=3");
        } catch (Exception e) {
        }
    }
    
    public void startRun() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) context.getRequestParameterMap();
        for (String key : parameterMap.keySet()) {
            System.out.println(key);
        }
    }
    
    public void destroyRun() {
        try {
            client.destroy(selectedTavernaServerRun.getUuid());
            FacesMessage message = new FacesMessage("Succesful", "Taverna Run " + selectedTavernaServerRun.getUuid() + " destroyed with success.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void handleT2flowUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        TavernaWorkflow workflow = new TavernaWorkflow();
        try {
            String t2flowContent = StringUtil.InputStreamToString(file.getInputstream());
            workflow.setT2flow(t2flowContent);
            workflow.setName(file.getFileName());
            workflow.setExperiment(experiment);
            workflow.setCreatedAt(new Date());
            workflow = new TavernaWorkflowDAO().save(workflow);
            
            File t2flow = new File("tmp.t2flow");
            FileUtils.writeStringToFile(t2flow, workflow.getT2flow());
            String uuid = client.create("tmp.t2flow");
            t2flow.delete();
            
            List<TavernaInput> inputs = client.getExpectedInputs(uuid);
            
            for (TavernaInput input : inputs) {
                TavernaWorkflowInput workflowInput = new TavernaWorkflowInput();
                workflowInput.setTavernaWorkflow(workflow);
                workflowInput.setName(input.getName());
                workflowInput = new TavernaWorkflowInputDAO().save(workflowInput);
                workflow.getInputs().add(workflowInput);
            }
            
            client.destroy(uuid);
            
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/experiments/execution/taverna/workflows.xhtml?tab=3");
            
            FacesMessage message = new FacesMessage("Succesful", "Workflow files uploaded successfully.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
        }
            
    }

    /**
     * @return the workflows
     */
    public List<TavernaWorkflow> getWorkflows() {
        workflows = new TavernaWorkflowDAO().getExperimentWorkflows(experiment.getId());
        return workflows;
    }

    /**
     * @param workflows the workflows to set
     */
    public void setWorkflows(List<TavernaWorkflow> workflows) {
        this.workflows = workflows;
    }

    /**
     * @return the selectedWorkflow
     */
    public TavernaWorkflow getSelectedWorkflow() {
        return selectedWorkflow;
    }

    /**
     * @param selectedWorkflow the selectedWorkflow to set
     */
    public void setSelectedWorkflow(TavernaWorkflow selectedWorkflow) {
        this.selectedWorkflow = selectedWorkflow;
    }

    /**
     * @return the experiment
     */
    public Experiment getExperiment() {
        return experiment;
    }

    /**
     * @param experiment the experiment to set
     */
    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    /**
     * @return the tavernaServerRuns
     */
    public List<TavernaRun> getTavernaServerRuns() {
        try {
            tavernaServerRuns = client.getRuns();
        } catch (Exception e) {
        }
        return tavernaServerRuns;
    }

    /**
     * @param tavernaServerRuns the tavernaServerRuns to set
     */
    public void setTavernaServerRuns(List<TavernaRun> tavernaServerRuns) {
        this.tavernaServerRuns = tavernaServerRuns;
    }

    /**
     * @return the selectedTavernaServerRun
     */
    public TavernaRun getSelectedTavernaServerRun() {
        return selectedTavernaServerRun;
    }

    /**
     * @param selectedTavernaServerRun the selectedTavernaServerRun to set
     */
    public void setSelectedTavernaServerRun(TavernaRun selectedTavernaServerRun) {
        this.selectedTavernaServerRun = selectedTavernaServerRun;
    }
    
}

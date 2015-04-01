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
package br.ufjf.pgcc.plscience.bean.experiments;

import br.ufjf.pgcc.plscience.bean.experiments.prototyping.ExperimentPrototyping;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.model.ExperimentServices;
import br.ufjf.pgcc.plscience.model.TavernaWorkflowRun;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@SessionScoped
public class Workspace implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Experiment experiment;
    private TavernaWorkflowRun tavernaRun;
    private List<ExperimentServices> exServ;
    private String numberStages;

    /**
     * @return the experiment
     */
    public Experiment getExperiment() {
        exServ = new ArrayList<ExperimentServices>();
        List<ExperimentServices> allExServ = new ExperimentDAO().getAllExperimentServices();
        for(ExperimentServices es: allExServ){
            if(null!=es.getExperiment() && null!=es.getExperiment().getIdExperiment() && es.getExperiment().getIdExperiment().equals(experiment.getIdExperiment())){
                exServ.add(es);
            }
        }
        Collections.sort(exServ);
        numberStages = String.valueOf(experiment.getNumberStages());
        return experiment;
    }

    /**
     * @param experiment the experiment to set
     */
    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    /**
     * @return the tavernaRun
     */
    public TavernaWorkflowRun getTavernaRun() {
        return tavernaRun;
    }

    /**
     * @param tavernaRun the tavernaRun to set
     */
    public void setTavernaRun(TavernaWorkflowRun tavernaRun) {
        this.tavernaRun = tavernaRun;
    }

    public List<ExperimentServices> getExServ() {
        return exServ;
    }

    public void setExServ(List<ExperimentServices> exServ) {
        this.exServ = exServ;
    }

    public String getNumberStages() {
        return numberStages;
    }

    public void setNumberStages(String numberStages) {
        this.numberStages = numberStages;
    }
    
    public void drawStages(String nStages){
        numberStages = nStages;
        for(int i=0;i<Integer.valueOf(nStages);i++){
            ExperimentServices exS = new ExperimentServices();
            exS.setId((long) 0);
            exS.setStage(i);
            exServ.add(exS);
        }
        ExperimentDAO exDao = new ExperimentDAO();
        exDao.updateNumberStages(experiment.getIdExperiment(), Integer.parseInt(nStages));
            
    }
    
     public void update(){
        try {
            new ExperimentDAO().save(experiment);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Experiment updated with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }
}

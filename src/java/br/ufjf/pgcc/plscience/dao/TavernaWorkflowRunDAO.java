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
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.TavernaWorkflowRun;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author vitorfs
 */
public class TavernaWorkflowRunDAO extends GenericDAO {
    
    public TavernaWorkflowRun save(TavernaWorkflowRun tavernaWorkflowRun) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(tavernaWorkflowRun);
        getEntityManager().getTransaction().commit();
        finish();    
        return tavernaWorkflowRun;
    }
    
    public TavernaWorkflowRun update(TavernaWorkflowRun tavernaWorkflowRun) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(tavernaWorkflowRun);
        getEntityManager().getTransaction().commit();
        finish();    
        return tavernaWorkflowRun;        
    }
    
    public List<TavernaWorkflowRun> getExperimentWorkflowRuns(Long experimentId) {
        Query query = getEntityManager().createQuery("SELECT t FROM TavernaWorkflowRun t WHERE t.tavernaWorkflow.experiment.id = :p_experiment_id");
        query.setParameter("p_experiment_id", experimentId);
        List<TavernaWorkflowRun> workflowRuns = query.getResultList();
        finish();
        return workflowRuns;
    }
}

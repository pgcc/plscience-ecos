/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WorkflowhasExperiment;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WorkflowhasExperimentDAO {
    
    
    public static WorkflowhasExperimentDAO workflowhasExperimentDAO;

    public static WorkflowhasExperimentDAO getInstance() {
        if (workflowhasExperimentDAO == null) {
            workflowhasExperimentDAO = new WorkflowhasExperimentDAO();
        }
        return workflowhasExperimentDAO;
    }

    /**
     * Busca uma WorkflowhasExperiment especifica
     *
     * @param name
     * @return
     */
    public WorkflowhasExperiment buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WorkflowhasExperiment as a where  upper(a.name)=:workflowhasExperiment");
        query.setParameter("workflowhasExperiment", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WorkflowhasExperiment> WorkflowhasExperiment = query.getResultList();
        if (WorkflowhasExperiment != null && WorkflowhasExperiment.size() > 0) {
            return WorkflowhasExperiment.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WorkflowhasExperiments
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<WorkflowhasExperiment> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WorkflowhasExperiment As a");
        return query.getResultList();
    }

    /**
     * Remove uma WorkflowhasExperiment
     *
     * @param idWorkflowhasExperiment
     */
    public void remover(WorkflowhasExperiment idWorkflowhasExperiment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWorkflowhasExperiment = em.merge(idWorkflowhasExperiment);
        em.remove(idWorkflowhasExperiment);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WorkflowhasExperiment
     *
     * @param workflowhasExperiment
     * @return
     */
    public WorkflowhasExperiment persistir(WorkflowhasExperiment workflowhasExperiment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            workflowhasExperiment = em.merge(workflowhasExperiment);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return workflowhasExperiment;
    }

    
}

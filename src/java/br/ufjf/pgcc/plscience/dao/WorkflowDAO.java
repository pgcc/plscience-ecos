/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Workflow;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WorkflowDAO {
    
    
    public static WorkflowDAO workflowDAO;

    public static WorkflowDAO getInstance() {
        if (workflowDAO == null) {
            workflowDAO = new WorkflowDAO();
        }
        return workflowDAO;
    }

    /**
     * Busca uma Workflow especifica
     *
     * @param name
     * @return
     */
    public Workflow buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from Workflow as a where  upper(a.name)=:workflow");
        query.setParameter("workflow", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<Workflow> Workflow = query.getResultList();
        if (Workflow != null && Workflow.size() > 0) {
            return Workflow.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Workflows
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Workflow> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Workflow As a");
        return query.getResultList();
    }

    /**
     * Remove uma Workflow
     *
     * @param idWorkflow
     */
    public void remover(Workflow idWorkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWorkflow = em.merge(idWorkflow);
        em.remove(idWorkflow);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Workflow
     *
     * @param workflow
     * @return
     */
    public Workflow persistir(Workflow workflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            workflow = em.merge(workflow);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return workflow;
    }

    
}

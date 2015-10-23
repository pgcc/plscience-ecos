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
public class WorkflowDAO extends PersistenceUtil{

    public static WorkflowDAO workflowDAO;

    public static WorkflowDAO getInstance() {
        if (workflowDAO == null) {
            workflowDAO = new WorkflowDAO();
        }
        return workflowDAO;
    }

    public Workflow buscar(int id) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from Workflow as a where  a.idWorkflow= :workflow");
        query.setParameter("workflow", id);

        @SuppressWarnings("unchecked")
        List<Workflow> Workflow = query.getResultList();
        if (Workflow != null && Workflow.size() > 0) {
            return Workflow.get(0);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Workflow> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Workflow As a");
        return query.getResultList();
    }

    public void remover(Workflow idWorkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
         em.getTransaction().begin();
        try {
            idWorkflow = em.merge(idWorkflow);
            em.remove(idWorkflow);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public Workflow persistir(Workflow workflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            workflow = em.merge(workflow);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return workflow;
    }

}

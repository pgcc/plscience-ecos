package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Used;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class UsedDAO extends PersistenceUtil{

    public static UsedDAO usedDAO;

    public static UsedDAO getInstance() {
        if (usedDAO == null) {
            usedDAO = new UsedDAO();
        }
        return usedDAO;
    }

    public List<Used> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("SELECT DISTINCT u FROM Used u WHERE u.workflowidWorkflow.idWorkflow = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }
    
    /**
     * search provenance data by a task id
     * @param idTask
     * @return 
     */
    public List<Used> searchTask(int idTask) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("SELECT DISTINCT u FROM Used u WHERE u.taskidTask.idTask = :id");
        query.setParameter("id", idTask);
        return query.getResultList();
    }
    
      public List<Used> buscarporexperimento(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("SELECT DISTINCT u FROM Used u JOIN WasAssociatedWith waw WHERE u.workflowidWorkflow.idWorkflow = waw.workflowidWorkflow.idWorkflow and waw.experimentExperiment.idExperiment = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }

    public List<Used> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Used As a");
        return query.getResultList();
    }

    public void remover(Used idUsed) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idUsed = em.merge(idUsed);
            em.remove(idUsed);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public Used persistir(Used used) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            used = em.merge(used);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return used;
    }

}

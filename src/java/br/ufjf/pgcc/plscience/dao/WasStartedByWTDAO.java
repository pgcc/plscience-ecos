package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasStartedByWT;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasStartedByWTDAO extends PersistenceUtil{

    public static WasStartedByWTDAO wasStartedByWTDAO;

    public static WasStartedByWTDAO getInstance() {
        if (wasStartedByWTDAO == null) {
            wasStartedByWTDAO = new WasStartedByWTDAO();
        }
        return wasStartedByWTDAO;
    }

     public List<WasStartedByWT> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("SELECT DISTINCT wib FROM WasStartedByWT wib JOIN Used used Where wib.taskidTask.idTask = used.taskidTask.idTask AND wib.taskidTask.idTask = SOME (SELECT used.taskidTask.idTask FROM Used used WHERE used.workflowidWorkflow.idWorkflow = :id)");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }

    public List<WasStartedByWT> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasStartedByWT As a");
        return query.getResultList();
    }

    public void remover(WasStartedByWT idWasStartedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idWasStartedBy = em.merge(idWasStartedBy);
            em.remove(idWasStartedBy);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public WasStartedByWT persistir(WasStartedByWT wasStartedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasStartedBy = em.merge(wasStartedBy);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return wasStartedBy;
    }

}

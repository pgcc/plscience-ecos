package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasStartedBy;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasStartedByDAO extends PersistenceUtil{

    public static WasStartedByDAO wasStartedByDAO;

    public static WasStartedByDAO getInstance() {
        if (wasStartedByDAO == null) {
            wasStartedByDAO = new WasStartedByDAO();
        }
        return wasStartedByDAO;
    }

     public List<WasStartedBy> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("SELECT DISTINCT wib FROM WasStartedBy wib JOIN Used used Where wib.taskidTask.idTask = used.taskidTask.idTask AND used.workflowidWorkflow.idWorkflow = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }

    public List<WasStartedBy> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasStartedBy As a");
        return query.getResultList();
    }

    public void remover(WasStartedBy idWasStartedBy) {
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

    public WasStartedBy persistir(WasStartedBy wasStartedBy) {
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

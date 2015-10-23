package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasEndedBy;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasEndedByDAO extends PersistenceUtil{

    public static WasEndedByDAO wasEndedByDAO;

    public static WasEndedByDAO getInstance() {
        if (wasEndedByDAO == null) {
            wasEndedByDAO = new WasEndedByDAO();
        }
        return wasEndedByDAO;
    }

  public List<WasEndedBy> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("SELECT DISTINCT wib FROM WasEndedBy wib JOIN Used used Where wib.taskidTask.idTask = used.taskidTask.idTask AND used.workflowidWorkflow.idWorkflow = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }

    public List<WasEndedBy> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasEndedBy As a");
        return query.getResultList();
    }

    public void remover(WasEndedBy idWasEndedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idWasEndedBy = em.merge(idWasEndedBy);
            em.remove(idWasEndedBy);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public WasEndedBy persistir(WasEndedBy wasEndedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasEndedBy = em.merge(wasEndedBy);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return wasEndedBy;
    }

}

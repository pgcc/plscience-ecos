package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasEndedByWT;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasEndedByWTDAO extends PersistenceUtil{

    public static WasEndedByWTDAO wasEndedByWTDAO;

    public static WasEndedByWTDAO getInstance() {
        if (wasEndedByWTDAO == null) {
            wasEndedByWTDAO = new WasEndedByWTDAO();
        }
        return wasEndedByWTDAO;
    }

  public List<WasEndedByWT> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("SELECT DISTINCT wib FROM WasEndedByWT wib JOIN Used used Where wib.taskidTask.idTask = used.taskidTask.idTask AND wib.taskidTask.idTask = SOME (SELECT used.taskidTask.idTask FROM Used used WHERE used.workflowidWorkflow.idWorkflow = :id)");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }

    public List<WasEndedByWT> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasEndedByWT As a");
        return query.getResultList();
    }

    public void remover(WasEndedByWT idWasEndedByWT) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idWasEndedByWT = em.merge(idWasEndedByWT);
            em.remove(idWasEndedByWT);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public WasEndedByWT persistir(WasEndedByWT wasEndedBy) {
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

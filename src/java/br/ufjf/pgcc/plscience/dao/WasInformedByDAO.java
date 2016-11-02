package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasInformedBy;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasInformedByDAO extends PersistenceUtil {

    public static WasInformedByDAO wasInformedByPortEntityDAO;

    public static WasInformedByDAO getInstance() {
        if (wasInformedByPortEntityDAO == null) {
            wasInformedByPortEntityDAO = new WasInformedByDAO();
        }
        return wasInformedByPortEntityDAO;
    }

    public List<WasInformedBy> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("SELECT DISTINCT wib FROM WasInformedBy wib JOIN Used used Where wib.taskidTask.idTask = used.taskidTask.idTask AND wib.taskidTask.idTask = SOME (SELECT used.taskidTask.idTask FROM Used used WHERE used.workflowidWorkflow.idWorkflow = :id)");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }
    
    /**
     * search was informed by provenance data related to a task id
     * @param idTask
     * @return 
     */
    public List<WasInformedBy> searchTask(int idTask){
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("SELECT DISTINCT wib FROM WasInformedBy wib WHERE wib.taskidTask.idTask = :id");
        query.setParameter("id", idTask);
        return query.getResultList();
    }

    public List<WasInformedBy> buscarcomproblema(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("SELECT DISTINCT wib FROM WasInformedBy wib JOIN Used used Where wib.taskidTask.idTask = used.taskidTask.idTask AND wib.taskidTask.description like '%Failure%' AND wib.taskidTask.idTask = SOME (SELECT used.taskidTask.idTask FROM Used used WHERE used.workflowidWorkflow.idWorkflow = :id)");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }
    
    public List<WasInformedBy> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasInformedBy As a");
        return query.getResultList();
    }

    public void remover(WasInformedBy idWasInformedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idWasInformedBy = em.merge(idWasInformedBy);
            em.remove(idWasInformedBy);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public WasInformedBy persistir(WasInformedBy wasInformedByPortEntity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasInformedByPortEntity = em.merge(wasInformedByPortEntity);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return wasInformedByPortEntity;
    }

}

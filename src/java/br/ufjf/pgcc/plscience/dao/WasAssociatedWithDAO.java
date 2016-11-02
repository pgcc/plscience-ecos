package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasAssociatedWith;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasAssociatedWithDAO extends PersistenceUtil{

    public static WasAssociatedWithDAO wasAssociatedWithDAO;

    public static WasAssociatedWithDAO getInstance() {
        if (wasAssociatedWithDAO == null) {
            wasAssociatedWithDAO = new WasAssociatedWithDAO();
        }
        return wasAssociatedWithDAO;
    }

    public List<WasAssociatedWith> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("SELECT DISTINCT wib FROM WasAssociatedWith wib Where wib.experimentExperiment.idExperiment = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }
    
        public List<WasAssociatedWith> buscarWorkflow(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("SELECT DISTINCT waw FROM WasAssociatedWith waw WHERE waw.workflowidWorkflow.idWorkflow = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }
    
    /**
     * Busca associacoes pela id do workflow
     * @param idworkflow
     * @return 
     */
    public List<WasAssociatedWith> buscarAssociacoes(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("SELECT DISTINCT wib FROM WasAssociatedWith wib Where wib.workflowidWorkflow.idWorkflow = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }
    
    public List<WasAssociatedWith> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasAssociatedWith As a");
        return query.getResultList();
    }

    public void remover(WasAssociatedWith idWasAssociatedWith) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
        idWasAssociatedWith = em.merge(idWasAssociatedWith);
        em.remove(idWasAssociatedWith);
        em.getTransaction().commit();
         } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public WasAssociatedWith persistir(WasAssociatedWith wasAssociatedWith) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasAssociatedWith = em.merge(wasAssociatedWith);
            em.getTransaction().commit();
      } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return wasAssociatedWith;
    }

}

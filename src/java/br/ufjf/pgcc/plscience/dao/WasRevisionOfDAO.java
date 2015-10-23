package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasRevisionOf;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasRevisionOfDAO extends PersistenceUtil{

    public static WasRevisionOfDAO wasRevisionOfDAO;

    public static WasRevisionOfDAO getInstance() {
        if (wasRevisionOfDAO == null) {
            wasRevisionOfDAO = new WasRevisionOfDAO();
        }
        return wasRevisionOfDAO;
    }

       public List<WasRevisionOf> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("SELECT DISTINCT wro FROM WasRevisionOf wro WHERE wro.revisionTo.idWorkflow = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }

    public List<WasRevisionOf> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasRevisionOf As a");
        return query.getResultList();
    }

    public void remover(WasRevisionOf idWasRevisionOf) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idWasRevisionOf = em.merge(idWasRevisionOf);
            em.remove(idWasRevisionOf);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public WasRevisionOf persistir(WasRevisionOf wasRevisionOf) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasRevisionOf = em.merge(wasRevisionOf);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return wasRevisionOf;
    }

}

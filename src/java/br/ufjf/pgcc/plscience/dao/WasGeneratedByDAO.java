package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasGeneratedBy;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasGeneratedByDAO extends PersistenceUtil{

    public static WasGeneratedByDAO wasGeneratedByDAO;

    public static WasGeneratedByDAO getInstance() {
        if (wasGeneratedByDAO == null) {
            wasGeneratedByDAO = new WasGeneratedByDAO();
        }
        return wasGeneratedByDAO;
    }

    public WasGeneratedBy buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select DISTINCT a from WasGeneratedBy as a where  upper(a.name)=:wasGeneratedBy");
        query.setParameter("wasGeneratedBy", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasGeneratedBy> WasGeneratedBy = query.getResultList();
        if (WasGeneratedBy != null && WasGeneratedBy.size() > 0) {
            return WasGeneratedBy.get(0);
        }

        return null;
    }

    public List<WasGeneratedBy> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasGeneratedBy As a");
        return query.getResultList();
    }

    public void remover(WasGeneratedBy idWasGeneratedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idWasGeneratedBy = em.merge(idWasGeneratedBy);
            em.remove(idWasGeneratedBy);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } 
        closeEntityManager();
    }

    public WasGeneratedBy persistir(WasGeneratedBy wasGeneratedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasGeneratedBy = em.merge(wasGeneratedBy);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return wasGeneratedBy;
    }

}

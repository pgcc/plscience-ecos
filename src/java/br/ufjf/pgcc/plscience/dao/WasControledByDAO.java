package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasControledBy;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasControledByDAO extends PersistenceUtil {

    public static WasControledByDAO wasControledByDAO;

    public static WasControledByDAO getInstance() {
        if (wasControledByDAO == null) {
            wasControledByDAO = new WasControledByDAO();
        }
        return wasControledByDAO;
    }

    public WasControledBy buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select DISTINCT a from WasControledBy as a where  upper(a.name)=:wasControledBy");
        query.setParameter("wasControledBy", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasControledBy> WasControledBy = query.getResultList();
        if (WasControledBy != null && WasControledBy.size() > 0) {
            return WasControledBy.get(0);
        }

        return null;
    }

    public List<WasControledBy> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasControledBy As a");
        return query.getResultList();
    }

    public void remover(WasControledBy idWasControledBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idWasControledBy = em.merge(idWasControledBy);
            em.remove(idWasControledBy);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public WasControledBy persistir(WasControledBy wasControledBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasControledBy = em.merge(wasControledBy);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return wasControledBy;
    }

}

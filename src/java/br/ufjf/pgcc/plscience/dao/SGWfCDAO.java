package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.SGWfC;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class SGWfCDAO extends PersistenceUtil{

    public static SGWfCDAO sGWfCDAO;

    public static SGWfCDAO getInstance() {
        if (sGWfCDAO == null) {
            sGWfCDAO = new SGWfCDAO();
        }
        return sGWfCDAO;
    }

    public SGWfC buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from SGWfC as a where  upper(a.name)=:name");
        query.setParameter("name", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<SGWfC> SGWfC = query.getResultList();
        if (SGWfC != null && SGWfC.size() > 0) {
            return SGWfC.get(0);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<SGWfC> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from SGWfC As a");
        return query.getResultList();
    }

    public void remover(SGWfC idSGWfC) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            idSGWfC = em.merge(idSGWfC);
            em.remove(idSGWfC);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public SGWfC persistir(SGWfC sGWfC) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            sGWfC = em.merge(sGWfC);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return sGWfC;
    }

}

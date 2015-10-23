package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.IsPartOf;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class IsPartOfDAO extends PersistenceUtil{

    public static IsPartOfDAO isPartOfDAO;

    public static IsPartOfDAO getInstance() {
        if (isPartOfDAO == null) {
            isPartOfDAO = new IsPartOfDAO();
        }
        return isPartOfDAO;
    }

    public IsPartOf buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from IsPartOf as a where  upper(a.name)=:name");
        query.setParameter("name", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<IsPartOf> IsPartOf = query.getResultList();
        if (IsPartOf != null && IsPartOf.size() > 0) {
            return IsPartOf.get(0);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<IsPartOf> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from IsPartOf As a");
        return query.getResultList();
    }

    public void remover(IsPartOf idIsPartOf) {
        EntityManager em = PersistenceUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            idIsPartOf = em.merge(idIsPartOf);
            em.remove(idIsPartOf);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public IsPartOf persistir(IsPartOf sGWfC) {
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

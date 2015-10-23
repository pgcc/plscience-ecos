package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.ActedOnBehalfOf;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class ActedOnBehalfOfDAO extends PersistenceUtil {

    public static ActedOnBehalfOfDAO actedOnBeHalfOfDAO;

    public static ActedOnBehalfOfDAO getInstance() {
        if (actedOnBeHalfOfDAO == null) {
            actedOnBeHalfOfDAO = new ActedOnBehalfOfDAO();
        }
        return actedOnBeHalfOfDAO;
    }

    public List<ActedOnBehalfOf> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("SELECT wib FROM ActedOnBehalfOf wib Where wib.wf = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }

    public List<ActedOnBehalfOf> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from ActedOnBehalfOf As a");
        return query.getResultList();
    }

    public void remover(ActedOnBehalfOf idActedOnBehalfOf) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idActedOnBehalfOf = em.merge(idActedOnBehalfOf);
            em.remove(idActedOnBehalfOf);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public ActedOnBehalfOf persistir(ActedOnBehalfOf actedOnBeHalfOf) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            actedOnBeHalfOf = em.merge(actedOnBeHalfOf);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return actedOnBeHalfOf;
    }

}

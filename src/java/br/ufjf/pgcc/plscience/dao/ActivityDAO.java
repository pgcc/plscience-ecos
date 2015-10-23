package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Activity;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class ActivityDAO extends PersistenceUtil {

    public static ActivityDAO activityDAO;

    public static ActivityDAO getInstance() {
        if (activityDAO == null) {
            activityDAO = new ActivityDAO();
        }
        return activityDAO;
    }

    public Activity buscar(int id) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from Activity as a where  a.idActivity=:activity");
        query.setParameter("activity", id);

        @SuppressWarnings("unchecked")
        List<Activity> Activity = query.getResultList();
        if (Activity != null && Activity.size() > 0) {
            return Activity.get(0);
        }

        return null;
    }

    public List<Activity> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Activity As a");
        return query.getResultList();
    }

    public void remover(Activity idActivity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idActivity = em.merge(idActivity);
            em.remove(idActivity);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public Activity persistir(Activity activity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            activity = em.merge(activity);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return activity;
    }

}

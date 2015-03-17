/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ActivityDAO {

    public static ActivityDAO activityDAO;

    public static ActivityDAO getInstance() {
        if (activityDAO == null) {
            activityDAO = new ActivityDAO();
        }
        return activityDAO;
    }

    /**
     * Busca uma Activity especifica
     *
     * @param name
     * @return
     */
    public Activity buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from Activity as a where  upper(a.name)=:activity");
        query.setParameter("activity", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<Activity> Activity = query.getResultList();
        if (Activity != null && Activity.size() > 0) {
            return Activity.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Activitys
     *
     * @return
     */
    public List<Activity> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Activity As a");
        return query.getResultList();
    }

    /**
     * Remove uma Activity
     *
     * @param idActivity
     */
    public void remover(Activity idActivity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idActivity = em.merge(idActivity);
        em.remove(idActivity);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Activity
     *
     * @param activity
     * @return
     */
    public Activity persistir(Activity activity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            activity = em.merge(activity);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return activity;
    }

}

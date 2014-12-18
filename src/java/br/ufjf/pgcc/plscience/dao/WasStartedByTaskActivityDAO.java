/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasStartedByTaskActivity;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasStartedByTaskActivityDAO {
    
    
    public static WasStartedByTaskActivityDAO wasStartedByTaskActivityDAO;

    public static WasStartedByTaskActivityDAO getInstance() {
        if (wasStartedByTaskActivityDAO == null) {
            wasStartedByTaskActivityDAO = new WasStartedByTaskActivityDAO();
        }
        return wasStartedByTaskActivityDAO;
    }

    /**
     * Busca uma WasStartedByTaskActivity especifica
     *
     * @param name
     * @return
     */
    public WasStartedByTaskActivity buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasStartedBy_Task_Activity as a where  upper(a.name)=:wasStartedByTaskActivity");
        query.setParameter("wasStartedByTaskActivity", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasStartedByTaskActivity> WasStartedByTaskActivity = query.getResultList();
        if (WasStartedByTaskActivity != null && WasStartedByTaskActivity.size() > 0) {
            return WasStartedByTaskActivity.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasStartedByTaskActivitys
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<WasStartedByTaskActivity> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasStartedBy_Task_Activity As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasStartedByTaskActivity
     *
     * @param idWasStartedByTaskActivity
     */
    public void remover(WasStartedByTaskActivity idWasStartedByTaskActivity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasStartedByTaskActivity = em.merge(idWasStartedByTaskActivity);
        em.remove(idWasStartedByTaskActivity);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasStartedByTaskActivity
     *
     * @param wasStartedByTaskActivity
     * @return
     */
    public WasStartedByTaskActivity persistir(WasStartedByTaskActivity wasStartedByTaskActivity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasStartedByTaskActivity = em.merge(wasStartedByTaskActivity);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasStartedByTaskActivity;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasControledByAgentActivity;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasControledByAgentActivityDAO {
    
    
    public static WasControledByAgentActivityDAO wasControledByAgentActivityDAO;

    public static WasControledByAgentActivityDAO getInstance() {
        if (wasControledByAgentActivityDAO == null) {
            wasControledByAgentActivityDAO = new WasControledByAgentActivityDAO();
        }
        return wasControledByAgentActivityDAO;
    }

    /**
     * Busca uma WasControledByAgentActivity especifica
     *
     * @param name
     * @return
     */
    public WasControledByAgentActivity buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasControledBy_Agent_Activity as a where  upper(a.name)=:wasControledByAgentActivity");
        query.setParameter("wasControledByAgentActivity", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasControledByAgentActivity> WasControledByAgentActivity = query.getResultList();
        if (WasControledByAgentActivity != null && WasControledByAgentActivity.size() > 0) {
            return WasControledByAgentActivity.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasControledByAgentActivitys
     *
     * @return
     */
    public List<WasControledByAgentActivity> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasControledBy_Agent_Activity As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasControledByAgentActivity
     *
     * @param idWasControledByAgentActivity
     */
    public void remover(WasControledByAgentActivity idWasControledByAgentActivity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasControledByAgentActivity = em.merge(idWasControledByAgentActivity);
        em.remove(idWasControledByAgentActivity);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasControledByAgentActivity
     *
     * @param wasControledByAgentActivity
     * @return
     */
    public WasControledByAgentActivity persistir(WasControledByAgentActivity wasControledByAgentActivity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasControledByAgentActivity = em.merge(wasControledByAgentActivity);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasControledByAgentActivity;
    }

}

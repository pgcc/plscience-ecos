/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasStartedBy;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasStartedByDAO {

    public static WasStartedByDAO wasStartedByDAO;

    public static WasStartedByDAO getInstance() {
        if (wasStartedByDAO == null) {
            wasStartedByDAO = new WasStartedByDAO();
        }
        return wasStartedByDAO;
    }

    /**
     * Busca uma WasStartedBy especifica
     *
     * @param name
     * @return
     */
    public WasStartedBy buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasStartedBy as a where  upper(a.name)=:wasStartedBy");
        query.setParameter("wasStartedBy", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasStartedBy> WasStartedBy = query.getResultList();
        if (WasStartedBy != null && WasStartedBy.size() > 0) {
            return WasStartedBy.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasStartedBys
     *
     * @return
     */
    public List<WasStartedBy> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasStartedBy As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasStartedBy
     *
     * @param idWasStartedBy
     */
    public void remover(WasStartedBy idWasStartedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasStartedBy = em.merge(idWasStartedBy);
        em.remove(idWasStartedBy);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasStartedBy
     *
     * @param wasStartedBy
     * @return
     */
    public WasStartedBy persistir(WasStartedBy wasStartedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasStartedBy = em.merge(wasStartedBy);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasStartedBy;
    }

}

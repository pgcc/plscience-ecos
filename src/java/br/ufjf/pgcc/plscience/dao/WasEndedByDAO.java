/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasEndedBy;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasEndedByDAO {

    public static WasEndedByDAO wasEndedByDAO;

    public static WasEndedByDAO getInstance() {
        if (wasEndedByDAO == null) {
            wasEndedByDAO = new WasEndedByDAO();
        }
        return wasEndedByDAO;
    }

    /**
     * Busca uma WasEndedBy especifica
     *
     * @param name
     * @return
     */
    public WasEndedBy buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasEndedBy as a where  upper(a.name)=:wasEndedBy");
        query.setParameter("wasEndedBy", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasEndedBy> WasEndedBy = query.getResultList();
        if (WasEndedBy != null && WasEndedBy.size() > 0) {
            return WasEndedBy.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasEndedBys
     *
     * @return
     */
    public List<WasEndedBy> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasEndedBy As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasEndedBy
     *
     * @param idWasEndedBy
     */
    public void remover(WasEndedBy idWasEndedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasEndedBy = em.merge(idWasEndedBy);
        em.remove(idWasEndedBy);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasEndedBy
     *
     * @param wasEndedBy
     * @return
     */
    public WasEndedBy persistir(WasEndedBy wasEndedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasEndedBy = em.merge(wasEndedBy);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasEndedBy;
    }

}

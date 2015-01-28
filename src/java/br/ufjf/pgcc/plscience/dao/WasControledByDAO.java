/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class WasControledByDAO {

    public static WasControledByDAO wasControledByDAO;

    public static WasControledByDAO getInstance() {
        if (wasControledByDAO == null) {
            wasControledByDAO = new WasControledByDAO();
        }
        return wasControledByDAO;
    }

    /**
     * Busca uma WasControledBy especifica
     *
     * @param name
     * @return
     */
    public WasControledBy buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasControledBy as a where  upper(a.name)=:wasControledBy");
        query.setParameter("wasControledBy", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasControledBy> WasControledBy = query.getResultList();
        if (WasControledBy != null && WasControledBy.size() > 0) {
            return WasControledBy.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasControledBys
     *
     * @return
     */
    public List<WasControledBy> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasControledBy As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasControledBy
     *
     * @param idWasControledBy
     */
    public void remover(WasControledBy idWasControledBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasControledBy = em.merge(idWasControledBy);
        em.remove(idWasControledBy);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasControledBy
     *
     * @param wasControledBy
     * @return
     */
    public WasControledBy persistir(WasControledBy wasControledBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasControledBy = em.merge(wasControledBy);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasControledBy;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasGeneratedBy;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasGeneratedByDAO {

    public static WasGeneratedByDAO wasGeneratedByDAO;

    public static WasGeneratedByDAO getInstance() {
        if (wasGeneratedByDAO == null) {
            wasGeneratedByDAO = new WasGeneratedByDAO();
        }
        return wasGeneratedByDAO;
    }

    /**
     * Busca uma WasGeneratedBy especifica
     *
     * @param name
     * @return
     */
    public WasGeneratedBy buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasGeneratedBy as a where  upper(a.name)=:wasGeneratedBy");
        query.setParameter("wasGeneratedBy", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasGeneratedBy> WasGeneratedBy = query.getResultList();
        if (WasGeneratedBy != null && WasGeneratedBy.size() > 0) {
            return WasGeneratedBy.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasGeneratedBys
     *
     * @return
     */
    public List<WasGeneratedBy> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasGeneratedBy As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasGeneratedBy
     *
     * @param idWasGeneratedBy
     */
    public void remover(WasGeneratedBy idWasGeneratedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasGeneratedBy = em.merge(idWasGeneratedBy);
        em.remove(idWasGeneratedBy);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasGeneratedBy
     *
     * @param wasGeneratedBy
     * @return
     */
    public WasGeneratedBy persistir(WasGeneratedBy wasGeneratedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasGeneratedBy = em.merge(wasGeneratedBy);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasGeneratedBy;
    }

}

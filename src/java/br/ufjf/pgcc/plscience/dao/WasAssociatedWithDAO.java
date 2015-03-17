/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasAssociatedWith;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasAssociatedWithDAO {

    public static WasAssociatedWithDAO wasAssociatedWithDAO;

    public static WasAssociatedWithDAO getInstance() {
        if (wasAssociatedWithDAO == null) {
            wasAssociatedWithDAO = new WasAssociatedWithDAO();
        }
        return wasAssociatedWithDAO;
    }

    /**
     * Busca uma WasAssociatedWith especifica
     *
     * @param name
     * @return
     */
    public WasAssociatedWith buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasAssociatedWith as a where  upper(a.name)=:wasAssociatedWith");
        query.setParameter("wasAssociatedWith", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasAssociatedWith> WasAssociatedWith = query.getResultList();
        if (WasAssociatedWith != null && WasAssociatedWith.size() > 0) {
            return WasAssociatedWith.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasAssociatedWiths
     *
     * @return
     */
    public List<WasAssociatedWith> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasAssociatedWith As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasAssociatedWith
     *
     * @param idWasAssociatedWith
     */
    public void remover(WasAssociatedWith idWasAssociatedWith) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasAssociatedWith = em.merge(idWasAssociatedWith);
        em.remove(idWasAssociatedWith);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasAssociatedWith
     *
     * @param wasAssociatedWith
     * @return
     */
    public WasAssociatedWith persistir(WasAssociatedWith wasAssociatedWith) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasAssociatedWith = em.merge(wasAssociatedWith);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasAssociatedWith;
    }

}

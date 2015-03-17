/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasAttributedTo;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasAttributedToDAO {

    public static WasAttributedToDAO wasAttributedToDAO;

    public static WasAttributedToDAO getInstance() {
        if (wasAttributedToDAO == null) {
            wasAttributedToDAO = new WasAttributedToDAO();
        }
        return wasAttributedToDAO;
    }

    /**
     * Busca uma WasAttributedTo especifica
     *
     * @param name
     * @return
     */
    public WasAttributedTo buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasAttributedTo as a where  upper(a.name)=:wasAttributedTo");
        query.setParameter("wasAttributedTo", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasAttributedTo> WasAttributedTo = query.getResultList();
        if (WasAttributedTo != null && WasAttributedTo.size() > 0) {
            return WasAttributedTo.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasAttributedTos
     *
     * @return
     */
    public List<WasAttributedTo> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasAttributedTo As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasAttributedTo
     *
     * @param idWasAttributedTo
     */
    public void remover(WasAttributedTo idWasAttributedTo) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasAttributedTo = em.merge(idWasAttributedTo);
        em.remove(idWasAttributedTo);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasAttributedTo
     *
     * @param wasAttributedTo
     * @return
     */
    public WasAttributedTo persistir(WasAttributedTo wasAttributedTo) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasAttributedTo = em.merge(wasAttributedTo);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasAttributedTo;
    }

}

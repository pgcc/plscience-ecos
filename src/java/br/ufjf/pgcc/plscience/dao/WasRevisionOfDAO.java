/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasRevisionOf;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasRevisionOfDAO {

    public static WasRevisionOfDAO wasRevisionOfDAO;

    public static WasRevisionOfDAO getInstance() {
        if (wasRevisionOfDAO == null) {
            wasRevisionOfDAO = new WasRevisionOfDAO();
        }
        return wasRevisionOfDAO;
    }

    /**
     * Busca uma WasRevisionOf especifica
     *
     * @param name
     * @return
     */
    public WasRevisionOf buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasRevisionOf as a where  upper(a.name)=:wasRevisionOf");
        query.setParameter("wasRevisionOf", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasRevisionOf> WasRevisionOf = query.getResultList();
        if (WasRevisionOf != null && WasRevisionOf.size() > 0) {
            return WasRevisionOf.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasRevisionOfs
     *
     * @return
     */
    public List<WasRevisionOf> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasRevisionOf As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasRevisionOf
     *
     * @param idWasRevisionOf
     */
    public void remover(WasRevisionOf idWasRevisionOf) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasRevisionOf = em.merge(idWasRevisionOf);
        em.remove(idWasRevisionOf);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasRevisionOf
     *
     * @param wasRevisionOf
     * @return
     */
    public WasRevisionOf persistir(WasRevisionOf wasRevisionOf) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasRevisionOf = em.merge(wasRevisionOf);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasRevisionOf;
    }

}

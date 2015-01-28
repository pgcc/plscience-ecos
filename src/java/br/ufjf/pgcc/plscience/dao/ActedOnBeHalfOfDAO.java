/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.ActedOnBeHalfOf;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class ActedOnBeHalfOfDAO {

    public static ActedOnBeHalfOfDAO actedOnBeHalfOfDAO;

    public static ActedOnBeHalfOfDAO getInstance() {
        if (actedOnBeHalfOfDAO == null) {
            actedOnBeHalfOfDAO = new ActedOnBeHalfOfDAO();
        }
        return actedOnBeHalfOfDAO;
    }

    /**
     * Busca uma ActedOnBeHalfOf especifica
     *
     * @param name
     * @return
     */
    public ActedOnBeHalfOf buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from ActedOnBeHalfOf as a where  upper(a.name)=:actedOnBeHalfOf");
        query.setParameter("actedOnBeHalfOf", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<ActedOnBeHalfOf> ActedOnBeHalfOf = query.getResultList();
        if (ActedOnBeHalfOf != null && ActedOnBeHalfOf.size() > 0) {
            return ActedOnBeHalfOf.get(0);
        }

        return null;
    }

    /**
     * Busca todas as ActedOnBeHalfOfs
     *
     * @return
     */
    public List<ActedOnBeHalfOf> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from ActedOnBeHalfOf As a");
        return query.getResultList();
    }

    /**
     * Remove uma ActedOnBeHalfOf
     *
     * @param idActedOnBeHalfOf
     */
    public void remover(ActedOnBeHalfOf idActedOnBeHalfOf) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idActedOnBeHalfOf = em.merge(idActedOnBeHalfOf);
        em.remove(idActedOnBeHalfOf);
        em.getTransaction().commit();
    }

    /**
     * Persite uma ActedOnBeHalfOf
     *
     * @param actedOnBeHalfOf
     * @return
     */
    public ActedOnBeHalfOf persistir(ActedOnBeHalfOf actedOnBeHalfOf) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            actedOnBeHalfOf = em.merge(actedOnBeHalfOf);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return actedOnBeHalfOf;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Used;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class UsedDAO {

    public static UsedDAO usedDAO;

    public static UsedDAO getInstance() {
        if (usedDAO == null) {
            usedDAO = new UsedDAO();
        }
        return usedDAO;
    }

    /**
     * Busca uma Used especifica
     *
     * @param name
     * @return
     */
    public Used buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from Used as a where  upper(a.name)=:used");
        query.setParameter("used", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<Used> Used = query.getResultList();
        if (Used != null && Used.size() > 0) {
            return Used.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Useds
     *
     * @return
     */
    public List<Used> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Used As a");
        return query.getResultList();
    }

    /**
     * Remove uma Used
     *
     * @param idUsed
     */
    public void remover(Used idUsed) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idUsed = em.merge(idUsed);
        em.remove(idUsed);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Used
     *
     * @param used
     * @return
     */
    public Used persistir(Used used) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            used = em.merge(used);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return used;
    }

}

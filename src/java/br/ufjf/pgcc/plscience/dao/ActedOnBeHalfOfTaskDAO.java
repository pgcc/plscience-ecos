/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.ActedOnBeHalfOfTask;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class ActedOnBeHalfOfTaskDAO {
    
    public static ActedOnBeHalfOfTaskDAO actedOnBeHalfOfTaskDAO;

    public static ActedOnBeHalfOfTaskDAO getInstance() {
        if (actedOnBeHalfOfTaskDAO == null) {
            actedOnBeHalfOfTaskDAO = new ActedOnBeHalfOfTaskDAO();
        }
        return actedOnBeHalfOfTaskDAO;
    }

    /**
     * Busca uma especifica
     *
     * @param nome
     * @return
     */
    public ActedOnBeHalfOfTask buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from ActedOnBeHalfOfTask As a where a.descriptyon =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<ActedOnBeHalfOfTask> actedOnBeHalfOfTasks = query.getResultList();
        if (actedOnBeHalfOfTasks != null && actedOnBeHalfOfTasks.size() > 0) {
            return actedOnBeHalfOfTasks.get(0);
        }

        return null;
    }

    /**
     * Busca todas
     *
     * @return
     */
    public List<ActedOnBeHalfOfTask> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from ActedOnBeHalfOfTask As a");
        return query.getResultList();
    }

    /**
     * Remove uma actedOnBeHalfOfTask
     *
     * @param actedOnBeHalfOfTask
     */
    public void remover(ActedOnBeHalfOfTask actedOnBeHalfOfTask) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        actedOnBeHalfOfTask = em.merge(actedOnBeHalfOfTask);
        em.remove(actedOnBeHalfOfTask);
        em.getTransaction().commit();
    }

    /**
     * Persite uma ActedOnBeHalfOfTask 
     *
     * @param actedOnBeHalfOfTask 
     * @return
     */
    public ActedOnBeHalfOfTask persistir(ActedOnBeHalfOfTask actedOnBeHalfOfTask) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            actedOnBeHalfOfTask = em.merge(actedOnBeHalfOfTask);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actedOnBeHalfOfTask;
    }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class ExperimentDAO {
    
    public static ExperimentDAO expirimentDAO;

    public static ExperimentDAO getInstance() {
        if (expirimentDAO == null) {
            expirimentDAO = new ExperimentDAO();
        }
        return expirimentDAO;
    }

    /**
     * Busca uma especifica
     *
     * @param nome
     * @return
     */
    public Experiment buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Experiment As a where a.descriptyon =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Experiment> expiriments = query.getResultList();
        if (expiriments != null && expiriments.size() > 0) {
            return expiriments.get(0);
        }

        return null;
    }

    /**
     * Busca todas
     *
     * @return
     */
    public List<Experiment> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Experiment As a");
        return query.getResultList();
    }

    /**
     * Remove uma expiriment
     *
     * @param expiriment
     */
    public void remover(Experiment expiriment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        expiriment = em.merge(expiriment);
        em.remove(expiriment);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Experiment 
     *
     * @param expiriment 
     * @return
     */
    public Experiment persistir(Experiment expiriment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            expiriment = em.merge(expiriment);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expiriment;
    }
    
    
}

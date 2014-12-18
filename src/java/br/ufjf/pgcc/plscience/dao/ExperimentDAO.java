/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Experiment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author vitorfs
 */
public class ExperimentDAO extends GenericDAO {
    
    public Long save(Experiment experiment) {
        EntityManager em = getEntityManager();
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        if (experiment.getId() == null) {
            em.persist(experiment);
        } else {
            experiment = em.merge(experiment);
        }
        em.getTransaction().commit();
        return experiment.getId();
    }
    
    public List<Experiment> getAll() {
        EntityManager em = getEntityManager();
        String sql = "SELECT e FROM Experiment e";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
}

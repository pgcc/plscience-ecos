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
    
    public Long persist(Experiment experiment) {
        EntityManager em = getEntityManager();
        em.persist(experiment);
        return experiment.getId();    
    }
    
    public List<Experiment> getAll() {
        EntityManager em = getEntityManager();
        String sql = "SELECT e FROM Experiment e";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.ActivityConcept;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class ActivityConceptDAO extends GenericDAO {
    
    public void save(ActivityConcept activity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(activity);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(ActivityConcept activity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(activity);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<ActivityConcept> getAll() {
        Query query = getEntityManager().createQuery("SELECT a FROM ActivityConcept AS a");
        List<ActivityConcept> activities = query.getResultList();
        finish();
        return activities;
    }
    
    public ActivityConcept getActivityConceptById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT a FROM ActivityConcept AS a WHERE a.id = :id");
        query.setParameter("id", id);

        List<ActivityConcept> activities = query.getResultList();
        finish();
        if (activities != null && activities.size() > 0) {
            return activities.get(0);
        }
        return null;
    }
    
    public ActivityConcept getActivityConceptByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT a FROM ActivityConcept AS a WHERE a.activityName = :activityName");
        query.setParameter("activityName", name);

        List<ActivityConcept> activities = query.getResultList();
        finish();
        if (activities != null && activities.size() > 0) {
            return activities.get(0);
        }
        return null;
    }
    
}

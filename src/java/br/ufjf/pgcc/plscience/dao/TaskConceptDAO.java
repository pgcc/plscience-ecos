/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.TaskConcept;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class TaskConceptDAO extends GenericDAO {
    
    public void save(TaskConcept task) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(task);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(TaskConcept task) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(task);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<TaskConcept> getAll() {
        Query query = getEntityManager().createQuery("SELECT t FROM TaskConcept AS t");
        List<TaskConcept> tasks = query.getResultList();
        finish();
        return tasks;
    }
    
    public TaskConcept getTaskConceptById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT t FROM TaskConcept AS t WHERE t.id = :id");
        query.setParameter("id", id);

        List<TaskConcept> tasks = query.getResultList();
        finish();
        if (tasks != null && tasks.size() > 0) {
            return tasks.get(0);
        }
        return null;
    }
    
    public TaskConcept getTaskConceptByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT t FROM TaskConcept t WHERE t.taskName = :taskName");
        query.setParameter("taskName", name);

        List<TaskConcept> tasks = query.getResultList();
        finish();
        if (tasks != null && tasks.size() > 0) {
            return tasks.get(0);
        }
        return null;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Status;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class StatusDAO extends GenericDAO {
    
    public void save(Status status) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(status);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(Status status) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(status);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<Status> getAll() {
        Query query = getEntityManager().createQuery("SELECT s FROM Status AS s");
        List<Status> status = query.getResultList();
        finish();
        return status;
    }
    
    public Status getStatusById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT s FROM Status AS s WHERE s.id = :id");
        query.setParameter("id", id);

        List<Status> status = query.getResultList();
        finish();
        if (status != null && status.size() > 0) {
            return status.get(0);
        }
        return null;
    }
    
    public Status getStatusByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT s FROM Status AS s WHERE s.statusName = :statusName");
        query.setParameter("statusName", name);

        List<Status> status = query.getResultList();
        finish();
        if (status != null && status.size() > 0) {
            return status.get(0);
        }
        return null;
    }
    
}

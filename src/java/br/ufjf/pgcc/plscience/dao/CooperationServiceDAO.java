/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.CooperationService;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Nenc
 */
public class CooperationServiceDAO extends GenericDAO {
    
    public void save(CooperationService cooperationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(cooperationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(CooperationService cooperationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(cooperationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<CooperationService> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM CollaborativeServiceType c");
        List<CooperationService> cooperationServices = query.getResultList();
        finish();
        return cooperationServices;
    }
    
    public CooperationService getCooperationServiceByID(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CooperationService c WHERE c.id = :id");
        query.setParameter("id", id);
        List<CooperationService> cooperationServices = query.getResultList();
        finish();

        if(cooperationServices != null && cooperationServices.size() > 0) {
            return cooperationServices.get(0);
        }
        
        return null;
    }
}

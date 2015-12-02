/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.CollaborativeServiceType;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class CollaborativeServiceTypeDAO extends GenericDAO {
    
    public void save(CollaborativeServiceType collaborativeServiceType) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(collaborativeServiceType);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(CollaborativeServiceType collaborativeServiceType) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(collaborativeServiceType);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<CollaborativeServiceType> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM CollaborativeServiceType c");
        List<CollaborativeServiceType> collaborativeServiceTypes = query.getResultList();
        finish();
        return collaborativeServiceTypes;
    }
    
    public List<CollaborativeServiceType> getAllOrderByName() {
        Query query = getEntityManager().createQuery("SELECT c FROM CollaborativeServiceType c ORDER BY c.nameServiceType");
        List<CollaborativeServiceType> collaborativeServiceTypes = query.getResultList();
        finish();
        return collaborativeServiceTypes;
    }
    
    public CollaborativeServiceType getCollaborativeServiceTypeById(Long id) {
        
        Query query = getEntityManager().createQuery("select c from CollaborativeServiceType As c where c.id =:id ");
        query.setParameter("id", id);

        List<CollaborativeServiceType> collaborativeServiceTypes = query.getResultList();
        finish();
        if (collaborativeServiceTypes != null && collaborativeServiceTypes.size() > 0) {
            return collaborativeServiceTypes.get(0);
        }
        return null;
    }
    
    public CollaborativeServiceType getCollaborativeServiceTypeByName(String name) {
        
        Query query = getEntityManager().createQuery("select c from CollaborativeServiceType As c where c.nameServiceType =:nameServiceType ");
        query.setParameter("nameServiceType", name);

        List<CollaborativeServiceType> CollaborativeServiceTypes = query.getResultList();
        finish();
        if (CollaborativeServiceTypes != null && CollaborativeServiceTypes.size() > 0) {
            return CollaborativeServiceTypes.get(0);
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.CommunicationService;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Nenc
 */
public class CommunicationServiceDAO extends GenericDAO {
    
    public void save(CommunicationService communicationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(communicationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(CommunicationService communicationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(communicationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<CommunicationService> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM CommunicationService c");
        List<CommunicationService> communicationServices = query.getResultList();
        finish();
        return communicationServices;
    }
    
    public CommunicationService getCommunicationServiceByID(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CommunicationService c WHERE c.id = :id");
        query.setParameter("id", id);
        List<CommunicationService> communicationServices = query.getResultList();
        finish();

        if(communicationServices != null && communicationServices.size() > 0) {
            return communicationServices.get(0);
        }
        
        return null;
    }

}

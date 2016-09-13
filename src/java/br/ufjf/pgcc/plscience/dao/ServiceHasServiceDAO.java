/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.ServiceHasService;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class ServiceHasServiceDAO extends GenericDAO{
    public static ServiceHasServiceDAO serviceHasServiceDAO;
    
    public static ServiceHasServiceDAO getInstance(){
        if(serviceHasServiceDAO == null){
            serviceHasServiceDAO = new ServiceHasServiceDAO();            
        }
        return serviceHasServiceDAO;
    }
    
    /**
     * save
     * @param serviceHasService 
     */
    public void save(ServiceHasService serviceHasService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(serviceHasService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * update
     * @param serviceHasService 
     */
    public void update(ServiceHasService serviceHasService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(serviceHasService);
        getEntityManager().getTransaction().commit();
        finish();
    }          

    /**
     * get all
     * @return 
     */
   public List<ServiceHasService> getAll() {
        Query query = getEntityManager().createQuery("SELECT s FROM ServiceHasService AS s");
        List<ServiceHasService> serviceHasService = query.getResultList();
        finish();
        return serviceHasService;
    }
   
    /**
     * getServiceHasServiceById
     * @param id
     * @return 
     */
    public ServiceHasService getServiceHasServiceById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT s FROM ServiceHasService AS s WHERE s.id = :id");
        query.setParameter("id", id);

        List<ServiceHasService> serviceHasService = query.getResultList();
        finish();
        if (serviceHasService != null && serviceHasService.size() > 0) {
            return serviceHasService.get(0);
        }
        return null;
    }
    
}

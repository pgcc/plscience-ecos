/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Service;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class ServiceDAO extends GenericDAO{
    
    public static ServiceDAO serviceDAO;
    
    public static ServiceDAO getInstance(){
        if(serviceDAO == null){
            serviceDAO = new ServiceDAO();            
        }
        return serviceDAO;
    }
    
    /**
     * Save a service on the E-SECO platform repository
     * @param service 
     */
    public void save(Service service) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(service);
        getEntityManager().getTransaction().commit();
        finish();
    }       
        
    /**
     * Update a service on the E-SECO platform repository
     * @param service 
     */
    public void update(Service service) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(service);
        getEntityManager().getTransaction().commit();
        finish();
    }        
    
    /**
     * get all services
     * @return 
     */
    public List<Service> getAll() {
        Query query = getEntityManager().createQuery("SELECT s FROM Service AS s");
        List<Service> service = query.getResultList();
        finish();
        return service;
    }
    
    /**
     * get the last service id
     * @return 
     */
    public Integer getLastServiceId(){
        Integer lastServiceId;
        ServiceDAO serviceDAO = new ServiceDAO();
        List<Service> servicesList = serviceDAO.getAll();
        if(servicesList != null){
            Service lastService = servicesList.get(servicesList.size()-1);
            lastServiceId = lastService.getId();
            return lastServiceId;
        }
        return null;
    }
    
    /**
     * Get a service on the E-SECO platform repository by id
     * @param id
     * @return 
     */
    public Service getServiceById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT s FROM Service AS s WHERE s.id = :id");
        query.setParameter("id", id);

        List<Service> service = query.getResultList();
        finish();
        if (service != null && service.size() > 0) {
            return service.get(0);
        }
        return null;
    }
    
    /**
     * Get a service on the E-SECO platform repository by service name
     * @param name
     * @return 
     */
    public Service getServiceByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT s FROM Service AS s WHERE s.serviceName = :serviceName");
        query.setParameter("serviceName", name);

        List<Service> service = query.getResultList();
        finish();
        if (service != null && service.size() > 0) {
            return service.get(0);
        }
        return null;
    }
    
//    /**
//     * persist a service
//     * @param service
//     * @return 
//     */
//        public Service persist(Service service) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            service = em.merge(service);
//            tx.commit();
//        } catch (RuntimeException e) {
//            tx.rollback();
//            throw e;
//        }
//        closeEntityManager();
//        return service;
//    }
}

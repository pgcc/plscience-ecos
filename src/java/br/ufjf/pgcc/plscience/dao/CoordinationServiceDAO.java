/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.CoordinationService;
import br.ufjf.pgcc.plscience.model.Roler;
import br.ufjf.pgcc.plscience.model.Status;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Nenc
 */
public class CoordinationServiceDAO extends GenericDAO {
    
    public void save(CoordinationService coordinationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(coordinationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(CoordinationService coordinationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(coordinationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<CoordinationService> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM CoordinationService c");
        List<CoordinationService> coordinationServices = query.getResultList();
        finish();
        return coordinationServices;
    }
    
    public CoordinationService getCooperationServiceByID(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CoordinationService c WHERE c.id = :id");
        query.setParameter("id", id);
        List<CoordinationService> coordinationServices = query.getResultList();
        finish();

        if(coordinationServices != null && coordinationServices.size() > 0) {
            return coordinationServices.get(0);
        }
        
        return null;
    }
    
    public List<Long> getListIdRolers(Long coordinationServiceID) {
        
        String q = "SELECT DISTINCT rcs.role_id FROM role_coordination_service AS rcs, coordination_service AS cs " +
                    "WHERE rcs.coordination_service_id  = " + coordinationServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> roles = query.getResultList();
        finish();
        if (roles != null && roles.size() > 0) {
            
            List<Long> rolesID = new ArrayList<Long>();
            
            for(Long bi : roles){
                rolesID.add(bi);
            }
            
            return rolesID;
        }
        return null;
    }
    
    public List<Roler> getListRolers(Long coordinationServiceID) {
       
        List<Roler> roles = new ArrayList<Roler>();
        
        List<Long> rolesIdList = getListIdRolers(coordinationServiceID);
        
        if(rolesIdList != null && rolesIdList.size() > 0) {
            for(Long i : rolesIdList){
               Roler r = new RolerDAO().getRoleById(i);
               if(r != null) {
                   roles.add(r);
               }               
            }     
        }
        
        if (roles != null && roles.size() > 0) {
            return roles;
        }
        return null;
    }
    
    public List<Long> getListIdStatus(Long coordinationServiceID) {
        
        String q = "SELECT DISTINCT scs.status_id FROM status_coordination_service AS scs, coordination_service AS cs " +
                    "WHERE scs.coordination_service_id  = " + coordinationServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> status = query.getResultList();
        finish();
        if (status != null && status.size() > 0) {
            
            List<Long> statusID = new ArrayList<Long>();
            
            for(Long bi : status){
                statusID.add(bi);
            }
            
            return statusID;
        }
        return null;
    }
    
    public List<Status> getListStatus(Long coordinationServiceID) {
       
        List<Status> status = new ArrayList<Status>();
        
        List<Long> statusIdList = getListIdRolers(coordinationServiceID);
        
        if(statusIdList != null && statusIdList.size() > 0) {
            for(Long i : statusIdList){
               Status s = new StatusDAO().getStatusById(i);
               if(s != null) {
                   status.add(s);
               }               
            }     
        }
        
        if (status != null && status.size() > 0) {
            return status;
        }
        return null;
    }
}

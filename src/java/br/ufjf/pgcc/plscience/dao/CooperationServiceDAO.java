/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.ActivityConcept;
import br.ufjf.pgcc.plscience.model.Artifact;
import br.ufjf.pgcc.plscience.model.CooperationService;
import br.ufjf.pgcc.plscience.model.Product;
import br.ufjf.pgcc.plscience.model.TaskConcept;
import java.util.ArrayList;
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
    
    public List<Long> getListIdActivityConcept(Long cooperationServiceID) {
        
        String q = "SELECT DISTINCT acs.activity_id FROM activity_cooperation_service AS acs, cooperation_service AS cs " +
                    "WHERE acs.cooperation_service_id  = " + cooperationServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> activity = query.getResultList();
        finish();
        if (activity != null && activity.size() > 0) {
            
            List<Long> activityID = new ArrayList<Long>();
            
            for(Long bi : activity){
                activityID.add(bi);
            }
            
            return activityID;
        }
        return null;
    }
    
    public List<ActivityConcept> getListActivityConcept(Long cooperationServiceID) {
       
        List<ActivityConcept> activityConcept = new ArrayList<ActivityConcept>();
        
        List<Long> activityConceptIdList = getListIdActivityConcept(cooperationServiceID);
        
        if(activityConceptIdList != null && activityConceptIdList.size() > 0) {
            for(Long i : activityConceptIdList){
               ActivityConcept ac = new ActivityConceptDAO().getActivityConceptById(i);
               if(ac != null) {
                   activityConcept.add(ac);
               }               
            }     
        }
        
        if (activityConcept != null && activityConcept.size() > 0) {
            return activityConcept;
        }
        return null;
    }
    
    public List<Long> getListIdArtifact(Long cooperationServiceID) {
        
        String q = "SELECT DISTINCT acs.artifact_id FROM artifact_cooperation_service AS acs, cooperation_service AS cs " +
                    "WHERE acs.cooperation_service_id  = " + cooperationServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> artifact = query.getResultList();
        finish();
        if (artifact != null && artifact.size() > 0) {
            
            List<Long> artifactID = new ArrayList<Long>();
            
            for(Long bi : artifact){
                artifactID.add(bi);
            }
            
            return artifactID;
        }
        return null;
    }
    
    public List<Artifact> getListArtifact(Long cooperationServiceID) {
       
        List<Artifact> artifact = new ArrayList<Artifact>();
        
        List<Long> artifactConceptIdList = getListIdArtifact(cooperationServiceID);
        
        if(artifactConceptIdList != null && artifactConceptIdList.size() > 0) {
            for(Long i : artifactConceptIdList){
               Artifact a = new ArtifactDAO().getArtifactById(i);
               if(a != null) {
                   artifact.add(a);
               }               
            }     
        }
        
        if (artifact != null && artifact.size() > 0) {
            return artifact;
        }
        return null;
    }
    
    public List<Long> getListIdProduct(Long cooperationServiceID) {
        
        String q = "SELECT DISTINCT pcs.product_id FROM product_cooperation_service AS pcs, cooperation_service AS cs " +
                    "WHERE pcs.cooperation_service_id  = " + cooperationServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> product = query.getResultList();
        finish();
        if (product != null && product.size() > 0) {
            
            List<Long> productID = new ArrayList<Long>();
            
            for(Long bi : product){
                productID.add(bi);
            }
            
            return productID;
        }
        return null;
    }
    
    public List<Product> getListProduct(Long cooperationServiceID) {
       
        List<Product> product = new ArrayList<Product>();
        
        List<Long> productIdList = getListIdProduct(cooperationServiceID);
        
        if(productIdList != null && productIdList.size() > 0) {
            for(Long i : productIdList){
               Product p = new ProductDAO().getProductById(i);
               if(p != null) {
                   product.add(p);
               }               
            }     
        }
        
        if (product != null && product.size() > 0) {
            return product;
        }
        return null;
    }
    
    public List<Long> getListIdTaskConcept(Long cooperationServiceID) {
        
        String q = "SELECT DISTINCT tcs.task_id FROM task_cooperation_service AS tcs, cooperation_service AS cs " +
                    "WHERE tcs.cooperation_service_id  = " + cooperationServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> task = query.getResultList();
        finish();
        if (task != null && task.size() > 0) {
            
            List<Long> taskID = new ArrayList<Long>();
            
            for(Long bi : task){
                taskID.add(bi);
            }
            
            return taskID;
        }
        return null;
    }
    
    public List<TaskConcept> getListTaskConcept(Long cooperationServiceID) {
       
        List<TaskConcept> taskConcept = new ArrayList<TaskConcept>();
        
        List<Long> taskConceptIdList = getListIdProduct(cooperationServiceID);
        
        if(taskConceptIdList != null && taskConceptIdList.size() > 0) {
            for(Long i : taskConceptIdList){
               TaskConcept tc = new TaskConceptDAO().getTaskConceptById(i);
               if(tc != null) {
                   taskConcept.add(tc);
               }               
            }     
        }
        
        if (taskConcept != null && taskConcept.size() > 0) {
            return taskConcept;
        }
        return null;
    }
}

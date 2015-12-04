/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Competence;
import br.ufjf.pgcc.plscience.model.GroupService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme
 */
public class GroupServiceDAO extends GenericDAO {
    
    public void save(GroupService groupService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(groupService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(GroupService groupService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(groupService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<GroupService> getAll() {
        Query query = getEntityManager().createQuery("SELECT g FROM GroupService g");
        List<GroupService> groupServices = query.getResultList();
        finish();
        return groupServices;
    }
    
    public GroupService getGroupServiceByID(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT g FROM GroupService g WHERE g.id = :id");
        query.setParameter("id", id);
        List<GroupService> groupServices = query.getResultList();
        finish();

        if(groupServices != null && groupServices.size() > 0) {
            return groupServices.get(0);
        }
        
        return null;
    }
    
    public List<Long> getListIdCompetences(Long groupServiceID) {
        
        String q = "SELECT DISTINCT cgs.competence_id FROM competence_group_service AS cgs "
                + "WHERE cgs.group_service_id = " + groupServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> competences = query.getResultList();
        finish();
        if (competences != null && competences.size() > 0) {
            
            List<Long> competencesID = new ArrayList<Long>();
            
            for(Long bi : competences){
                competencesID.add(bi);
            }
            
            return competencesID;
        }
        return null;
    }
    
    public List<Competence> getListCompetences(Long groupServiceID) {
       
        List<Competence> competences = new ArrayList<Competence>();
        
        List<Long> competencesIdList = getListIdCompetences(groupServiceID);
        
        for(Long i : competencesIdList){
            Competence c = new CompetenceDAO().getCompetenceById(i);
            if(c != null) {
                competences.add(c);
            }               
        }        
        
        if (competences != null && competences.size() > 0) {
            return competences;
        }
        return null;
    }
}

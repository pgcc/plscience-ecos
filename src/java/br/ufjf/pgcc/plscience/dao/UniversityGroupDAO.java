/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.University;
import br.ufjf.pgcc.plscience.model.UniversityGroup;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class UniversityGroupDAO extends GenericDAO {
    
    public static UniversityGroupDAO universityGroupDAO;
    
        public static UniversityGroupDAO getInstance() {
        if (universityGroupDAO == null) {
            universityGroupDAO = new UniversityGroupDAO();
        }
        return universityGroupDAO;
    }
        
    public void save(UniversityGroup ug) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(ug);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(UniversityGroup ug) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(ug);
        getEntityManager().getTransaction().commit();
        finish();
    }
        
    /**
     * get all university groups
     * @return 
     */    
    public List<UniversityGroup> getAll() {
        Query query = getEntityManager().createQuery("SELECT u FROM UniversityGroup AS u");
        List<UniversityGroup> universityGroupList = query.getResultList();
        finish();
        return universityGroupList;
    }
    
    public UniversityGroup getUniversityGroupById(Long id) {
        Query query = getEntityManager().createQuery("SELECT u FROM UniversityGroup AS u WHERE u.id = :id");
        query.setParameter("id", id);

        List<UniversityGroup> universityGroupList = query.getResultList();
        finish();
        if (universityGroupList != null && universityGroupList.size() > 0) {
            return universityGroupList.get(0);
        }
        return null;
    }    

    public UniversityGroup getUniversityGroupByName(String name) {
       Query query = getEntityManager().createQuery("SELECT u FROM UniversityGroup AS u WHERE u.groupName like :name");
       query.setParameter("name", name);

        List<UniversityGroup> universityGroupList = query.getResultList();
        finish();
        if (universityGroupList != null && universityGroupList.size() > 0) {
            return universityGroupList.get(0);
        }
        return null;
    }    

    
}

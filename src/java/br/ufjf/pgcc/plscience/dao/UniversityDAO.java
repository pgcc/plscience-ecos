/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.University;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class UniversityDAO extends GenericDAO {
    
    public static UniversityDAO universityDAO;
    
        public static UniversityDAO getInstance() {
        if (universityDAO == null) {
            universityDAO = new UniversityDAO();
        }
        return universityDAO;
    }
        
    public void save(University u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(u);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(University u) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(u);
        getEntityManager().getTransaction().commit();
        finish();
    }
        
    /**
     * get all universities
     * @return 
     */    
    public List<University> getAll() {
        Query query = getEntityManager().createQuery("SELECT u FROM University AS u");
        List<University> universityList = query.getResultList();
        finish();
        return universityList;
    }
    
    /**
     * return a university by an id
     * @param id
     * @return 
     */
    public University getUniversityById(Long id) {

        Query query = getEntityManager().createQuery("SELECT u FROM University AS u WHERE u.id = :id");
        query.setParameter("id", id);

        List<University> universityList = query.getResultList();
        finish();
        if (universityList != null && universityList.size() > 0) {
            return universityList.get(0);
        }
        return null;
    }
    
    /**
     * return a university by an abbreviation name
     * @param abb
     * @return 
     */
    public University getUniversityByAbbName(String abb) {

        Query query = getEntityManager().createQuery("SELECT u FROM University AS u WHERE u.universityAbbreviationName like :abb");
        query.setParameter("abb", abb);

        List<University> universityList = query.getResultList();
        finish();
        if (universityList != null && universityList.size() > 0) {
            return universityList.get(0);
        }
        return null;
    }
    
   
}

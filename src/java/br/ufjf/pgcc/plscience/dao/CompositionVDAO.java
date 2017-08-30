/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.CompositionV;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class CompositionVDAO extends GenericDAO{
    
    public static CompositionVDAO discussionVDAO;
    
    public static CompositionVDAO getInstance(){
        if(discussionVDAO == null){
            discussionVDAO = new CompositionVDAO();            
        }
        return discussionVDAO;
    }
    
    /**
     * save a composition discussion
     * @param composition
     */
    public void save(CompositionV composition) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(composition);
        getEntityManager().getTransaction().commit();
        finish();
    }
        
    /**
     * Update a composition discussion
     * @param composition 
     */
    public void update(CompositionV composition) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(composition);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * Get all compositions
     * @return 
     */
    public List<CompositionV> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM CompositionV AS c");
        List<CompositionV> compositionList = query.getResultList();
        finish();
        return compositionList;
    }
    
    /**
     * Get a comoposition by id
     * @param id
     * @return 
     */
    public CompositionV getCompositionById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CompositionV AS c WHERE c.id = :id");
        query.setParameter("id", id);

        List<CompositionV> compositionList = query.getResultList();
        finish();
        if (compositionList != null && compositionList.size() > 0) {
            return compositionList.get(0);
        }
        return null;
    }    
    
    public CompositionV getCompositionByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CompositionV AS c WHERE c.compositionName = :name");
        query.setParameter("name", name);

        List<CompositionV> compositionList = query.getResultList();
        finish();
        if (compositionList != null && compositionList.size() > 0) {
            return compositionList.get(0);
        }
        return null;
    }     
}

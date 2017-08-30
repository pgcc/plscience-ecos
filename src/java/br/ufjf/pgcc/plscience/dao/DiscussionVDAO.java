/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.DiscussionV;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class DiscussionVDAO extends GenericDAO{
    
    public static DiscussionVDAO discussionVDAO;
    
    public static DiscussionVDAO getInstance(){
        if(discussionVDAO == null){
            discussionVDAO = new DiscussionVDAO();            
        }
        return discussionVDAO;
    }
    
    /**
     * save a discussion
     * @param discussion
     */
    public void save(DiscussionV discussion) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(discussion);
        getEntityManager().getTransaction().commit();
        finish();
    }
        
    /**
     * Update a discussion
     * @param discussion 
     */
    public void update(DiscussionV discussion) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(discussion);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * Get all discussions
     * @return 
     */
    public List<DiscussionV> getAll() {
        Query query = getEntityManager().createQuery("SELECT d FROM DiscussionV AS d");
        List<DiscussionV> discussionVList = query.getResultList();
        finish();
        return discussionVList;
    }
    
    /**
     * Get a discussion by id
     * @param id
     * @return 
     */
    public DiscussionV getDiscussionById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT d FROM DiscussionV AS d WHERE d.id = :id");
        query.setParameter("id", id);

        List<DiscussionV> discussionList = query.getResultList();
        finish();
        if (discussionList != null && discussionList.size() > 0) {
            return discussionList.get(0);
        }
        return null;
    }

    /**
     * get a discussion based in a composition name
     * @param name
     * @return 
     */
    public DiscussionV getDiscussionByCompositionName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT d FROM DiscussionV AS d WHERE d.compositionVId.compositionName = :name");
        query.setParameter("name", name);

        List<DiscussionV> discussionList = query.getResultList();
        finish();
        if (discussionList != null && discussionList.size() > 0) {
            return discussionList.get(0);
        }
        return null;
    }    
}

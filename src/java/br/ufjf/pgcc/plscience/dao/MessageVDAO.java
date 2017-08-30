/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.MessageV;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class MessageVDAO extends GenericDAO{
    
    public static MessageVDAO messageVDAO;
    
    public static MessageVDAO getInstance(){
        if(messageVDAO == null){
            messageVDAO = new MessageVDAO();            
        }
        return messageVDAO;
    }
    
    /**
     * save a message in a discussion
     * @param message 
     */
    public void save(MessageV message) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(message);
        getEntityManager().getTransaction().commit();
        finish();
    }
        
    /**
     * Update a message
     * @param message 
     */
    public void update(MessageV message) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(message);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * Get all messages
     * @return 
     */
    public List<MessageV> getAll() {
        Query query = getEntityManager().createQuery("SELECT m FROM MessageV AS m");
        List<MessageV> messageList = query.getResultList();
        finish();
        return messageList;
    }
    
    /**
     * Get a message by id
     * @param id
     * @return 
     */
    public MessageV getMessageById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT m FROM MessageV AS m WHERE m.id = :id");
        query.setParameter("id", id);

        List<MessageV> messageList = query.getResultList();
        finish();
        if (messageList != null && messageList.size() > 0) {
            return messageList.get(0);
        }
        return null;
    }    
    
    public List<MessageV> getMessageByDiscussionId(Integer id) {
        
        Query query = getEntityManager().createQuery("SELECT m FROM MessageV AS m WHERE m.discussion.id = :id");
        query.setParameter("id", id);

        List<MessageV> messageList = query.getResultList();
        finish();
        return messageList;
    }     
}

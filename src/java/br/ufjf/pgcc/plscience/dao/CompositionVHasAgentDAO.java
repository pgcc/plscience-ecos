/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.CompositionVHasAgent;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class CompositionVHasAgentDAO extends GenericDAO{
    
    public static CompositionVHasAgentDAO compositionHasAgentDAO;
    
        public static CompositionVHasAgentDAO getInstance(){
        if(compositionHasAgentDAO == null){
            compositionHasAgentDAO = new CompositionVHasAgentDAO();            
        }
        return compositionHasAgentDAO;
    }
        
        /**
     * save a compositionvhasagent
     * @param compositionVHasAgent
     */
    public void save(CompositionVHasAgent compositionVHasAgent) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(compositionVHasAgent);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * Get all compositions has agent
     * @return 
     */
    public List<CompositionVHasAgent> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM CompositionVHasAgent AS c");
        List<CompositionVHasAgent> compositionVHasAgentList = query.getResultList();
        finish();
        return compositionVHasAgentList;
    }    
    
}

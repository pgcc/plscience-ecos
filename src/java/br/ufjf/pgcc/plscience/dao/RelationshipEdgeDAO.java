/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.RelationshipEdge;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class RelationshipEdgeDAO  extends GenericDAO {
    
    public static RelationshipEdgeDAO relationshipEdgeDAO;
    
        public static RelationshipEdgeDAO getInstance() {
        if (relationshipEdgeDAO == null) {
            relationshipEdgeDAO = new RelationshipEdgeDAO();
        }
        return relationshipEdgeDAO;
    }
        
    public void save(RelationshipEdge re) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(re);
        getEntityManager().getTransaction().commit();
        finish();
    }        
    
        public void update(RelationshipEdgeDAO re) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(re);
        getEntityManager().getTransaction().commit();
        finish();
    }
        
    public List<RelationshipEdge> getAll() {
        Query query = getEntityManager().createQuery("SELECT r FROM RelationshipEdge AS r");
        List<RelationshipEdge> universityList = query.getResultList();
        finish();
        return universityList;
    }
    
    public RelationshipEdge getRelationshipEdgeById(Long id) {

        Query query = getEntityManager().createQuery("SELECT r FROM RelationshipEdge AS r WHERE r.id = :id");
        query.setParameter("id", id);

        List<RelationshipEdge> relationshipEdgeList = query.getResultList();
        finish();
        if (relationshipEdgeList != null && relationshipEdgeList.size() > 0) {
            return relationshipEdgeList.get(0);
        }
        return null;
    }
    
    /**
     * it returns an edge that contains an node id as source or target
     * @param id
     * @return 
     */
    public List getRelationshipEdgeByNodeId(String id) {

        Query query = getEntityManager().createQuery("SELECT r FROM RelationshipEdge AS r WHERE r.nodeSource.idNetwork = :id or r.nodeTarget.idNetwork LIKE :id");
        query.setParameter("id", id);

        List<RelationshipEdge> relationshipEdgeList = query.getResultList();
        finish();
        if (relationshipEdgeList != null && relationshipEdgeList.size() > 0) {
            return relationshipEdgeList;
        }
        return null;
    }    
    
        
    
}

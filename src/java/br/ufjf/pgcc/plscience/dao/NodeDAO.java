/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.NodeBD;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class NodeDAO extends GenericDAO {
    
    public static NodeDAO nodeDAO;
    
        public static NodeDAO getInstance() {
        if (nodeDAO == null) {
            nodeDAO = new NodeDAO();
        }
        return nodeDAO;
    }
        
    public void save(NodeBD n) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(n);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(NodeDAO n) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(n);
        getEntityManager().getTransaction().commit();
        finish();
    }
        
    /**
     * get all nodes
     * @return 
     */    
    public List<NodeBD> getAll() {
        Query query = getEntityManager().createQuery("SELECT n FROM NodeBD AS n");
        List<NodeBD> nodeList = query.getResultList();
        finish();
        return nodeList;
    }
    
    /**
     * return a node by an id
     * @param id
     * @return 
     */
    public NodeBD getNodeById(Long id) {

        Query query = getEntityManager().createQuery("SELECT n FROM NodeBD AS n WHERE n.id = :id");
        query.setParameter("id", id);

        List<NodeBD> nodeList = query.getResultList();
        finish();
        if (nodeList != null && nodeList.size() > 0) {
            return nodeList.get(0);
        }
        return null;
    }

    /**
     * return a node by an id in the scientific social network
     * @param idNetwork
     * @return 
     */
    public NodeBD getNodeByIdNetwork(String idNetwork) {

        Query query = getEntityManager().createQuery("SELECT n FROM NodeBD AS n WHERE n.idNetwork like :id_network");
        query.setParameter("id_network", idNetwork);

        List<NodeBD> nodeList = query.getResultList();
        finish();
        if (nodeList != null && nodeList.size() > 0) {
            return nodeList.get(0);
        }
        return null;
    }    
    
    /**
     * it returns a node by researcger name
     * @param name
     * @return 
     */
    public NodeBD getNodeByResearcherName(String name) {

        Query query = getEntityManager().createQuery("SELECT n FROM NodeBD AS n WHERE n.fullName like :name");
        query.setParameter("name", name);

        List<NodeBD> nodeList = query.getResultList();
        finish();
        if (nodeList != null && nodeList.size() > 0) {
            return nodeList.get(0);
        }
        return null;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Ontology;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class OntologyDAO extends GenericDAO {

    public static OntologyDAO ontologyDAO;

    public static OntologyDAO getInstance() {
        if (ontologyDAO == null) {
            ontologyDAO = new OntologyDAO();
        }
        return ontologyDAO;
    }

    /**
     * save an ontology on the E-SECO software product line repository
     * @param on 
     */
    public void save(Ontology on) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(on);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * update an ontology on the E-SECO software product line repository
     * @param on 
     */
    public void update(Ontology on) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(on);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * get all ontology
     * @return 
     */
    public List<Ontology> getAll() {
        Query query = getEntityManager().createQuery("SELECT o FROM Ontology AS o");
        List<Ontology> ontologyList = query.getResultList();
        finish();
        return ontologyList;
    }
    
    /**
     * get ontology by id
     * @param id
     * @return 
     */
    public Ontology getOntologyById(Long id) {

        Query query = getEntityManager().createQuery("SELECT o FROM Ontology AS o WHERE o.id = :id");
        query.setParameter("id", id);

        List<Ontology> ontologyList = query.getResultList();
        finish();
        if (ontologyList != null && ontologyList.size() > 0) {
            return ontologyList.get(0);
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.integration;

import br.ufjf.pgcc.plscience.dao.GenericDAO;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author Guilherme Martins
 */
public class InteroperabilityStructXMLDAO extends GenericDAO {
    
    public void save(InteroperabilityStructXML interoperabilityStructXML) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(interoperabilityStructXML);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(InteroperabilityStructXML interoperabilityStructXML) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(interoperabilityStructXML);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void remove(InteroperabilityStructXML interoperabilityStructXML) {
        interoperabilityStructXML = getEntityManager().contains(interoperabilityStructXML) ? interoperabilityStructXML : getEntityManager().merge(interoperabilityStructXML);
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(interoperabilityStructXML);
        getEntityManager().getTransaction().commit();
        finish();        
    }
    
    public List<InteroperabilityStructXML> getAll() {
        Query query = getEntityManager().createQuery("SELECT i FROM InteroperabilityStructXML i");
        List<InteroperabilityStructXML> interoperabilityStructXMLs = query.getResultList();
        finish();
        return interoperabilityStructXMLs;
    }
    
    public InteroperabilityStructXML getInteroperabilityStructXMLById(Long idStructXml) {
        
        Query query = getEntityManager().createQuery("SELECT i FROM InteroperabilityStructXML i WHERE i.idStructXml = :idStructXml");
        query.setParameter("idStructXml", idStructXml);

        List<InteroperabilityStructXML> interoperabilityStructXMLs = query.getResultList();
        finish();
        if (interoperabilityStructXMLs != null && interoperabilityStructXMLs.size() > 0) {
            return interoperabilityStructXMLs.get(0);
        }
        return null;
    }
    
    public Long getLastInteroperabilityStructXMLID () {
        
        Query query = getEntityManager().createQuery("SELECT i FROM InteroperabilityStructXML AS i ORDER BY i.idStructXml DESC ");

        List<InteroperabilityStructXML> interoperabilityStructXMLs = query.getResultList();
        finish();
        if (interoperabilityStructXMLs != null && interoperabilityStructXMLs.size() > 0) {
            return interoperabilityStructXMLs.get(0).getIdStructXml();
        }
        return 0L;     
    }

}

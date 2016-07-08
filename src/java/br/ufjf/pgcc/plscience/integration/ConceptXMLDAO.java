/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.integration;

import br.ufjf.pgcc.plscience.dao.GenericDAO;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class ConceptXMLDAO extends GenericDAO {
    
    public void save(ConceptXML conceptXML) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(conceptXML);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(ConceptXML conceptXML) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(conceptXML);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void remove(ConceptXML conceptXML) {
        conceptXML = getEntityManager().contains(conceptXML) ? conceptXML : getEntityManager().merge(conceptXML);
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(conceptXML);
        getEntityManager().getTransaction().commit();
        finish();        
    }
    
    public List<ConceptXML> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM ConceptXML c");
        List<ConceptXML> conceptXMLs = query.getResultList();
        finish();
        return conceptXMLs;
    }
    
    public ConceptXML getConceptXMLById(Long idConceptXml) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM ConceptXML c WHERE c.idConceptXml = :idConceptXml");
        query.setParameter("idConceptXml", idConceptXml);

        List<ConceptXML> ConceptXMLs = query.getResultList();
        finish();
        if (ConceptXMLs != null && ConceptXMLs.size() > 0) {
            return ConceptXMLs.get(0);
        }
        return null;
    }
    
    public List<ConceptXML> getAllLastX(int X) {
        Query query = getEntityManager().createQuery("SELECT c FROM ConceptXML c ORDER BY c.idConceptXml DESC");
        query.setFirstResult(0);
        query.setMaxResults(X);
        List<ConceptXML> conceptXMLs = query.getResultList();
        finish();
        
        if(conceptXMLs != null && conceptXMLs.size() > 0) {
            Collections.sort(conceptXMLs, new Comparator(){
                @Override
                public int compare(Object o1, Object o2) {  
                    ConceptXML p1 = (ConceptXML) o1;  
                    ConceptXML p2 = (ConceptXML) o2;  
                    return p1.getIdConceptXml() < p2.getIdConceptXml() ? -1 : (p1.getIdConceptXml() > p2.getIdConceptXml() ? +1 : 0);  
                } 
            });
        }
                
        return conceptXMLs;        
    }
    
    public List<ConceptXML> getConceptXMLByIdStructXML(Long idStructXML) {
        
        List<Long> ConceptXMLlist = getListConceptsXML(idStructXML);
        
        List<ConceptXML> ConceptXMLs = new ArrayList<ConceptXML>();
        
        if(ConceptXMLlist != null && ConceptXMLlist.size() > 0) {
            for(int i = 0; i < ConceptXMLlist.size(); i++) {
                Long l = ConceptXMLlist.get(i).longValue();
                ConceptXMLs.add(getConceptXMLById(l));
            }            
        }

        return ConceptXMLs;
    }
    
    /**
     * (Consulta Nativa)
     * Recupera os IDs dos 'ConceptXMLs' associados a um 'StructXML'.
     * @param idStructXML
     * @return
     */
    public List<Long> getListConceptsXML(Long idStructXML) {
        
        String q = "SELECT DISTINCT c.id_concept_xml FROM concept_XML AS c WHERE c.id_struct_xml = " + idStructXML;
        
        Query query = getEntityManager().createNativeQuery(q);
        //Query query = getEntityManager().createQuery(q);

        List<Long> concepts = query.getResultList();
        finish();
        if (concepts != null && concepts.size() > 0) {
            return concepts;
        }
        return null;
    }
}

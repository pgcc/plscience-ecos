/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.CommunicationProtocol;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class CommunicationProtocolDAO extends GenericDAO {
    
    public void save(CommunicationProtocol communicationProtocol) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(communicationProtocol);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(CommunicationProtocol communicationProtocol) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(communicationProtocol);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<CommunicationProtocol> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM CommunicationProtocol AS c");
        List<CommunicationProtocol> communicationProtocols = query.getResultList();
        finish();
        return communicationProtocols;
    }
    
    public CommunicationProtocol getCommunicationProtocolById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CommunicationProtocol AS c WHERE c.id = :id");
        query.setParameter("id", id);

        List<CommunicationProtocol> communicationProtocols = query.getResultList();
        finish();
        if (communicationProtocols != null && communicationProtocols.size() > 0) {
            return communicationProtocols.get(0);
        }
        return null;
    }
    
    public CommunicationProtocol getCommunicationProtocolByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CommunicationProtocol AS c WHERE c.communicationProtocolName = :communicationProtocolName");
        query.setParameter("communicationProtocolName", name);

        List<CommunicationProtocol> communicationProtocols = query.getResultList();
        finish();
        if (communicationProtocols != null && communicationProtocols.size() > 0) {
            return communicationProtocols.get(0);
        }
        return null;
    }
    
}

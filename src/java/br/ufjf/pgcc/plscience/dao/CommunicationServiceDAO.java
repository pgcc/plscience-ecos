/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Code;
import br.ufjf.pgcc.plscience.model.CommonSense;
import br.ufjf.pgcc.plscience.model.CommunicationProtocol;
import br.ufjf.pgcc.plscience.model.CommunicationService;
import br.ufjf.pgcc.plscience.model.Compromise;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Nenc
 */
public class CommunicationServiceDAO extends GenericDAO {
    
    public void save(CommunicationService communicationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(communicationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(CommunicationService communicationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(communicationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<CommunicationService> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM CommunicationService c");
        List<CommunicationService> communicationServices = query.getResultList();
        finish();
        return communicationServices;
    }
    
    public CommunicationService getCommunicationServiceByID(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CommunicationService c WHERE c.id = :id");
        query.setParameter("id", id);
        List<CommunicationService> communicationServices = query.getResultList();
        finish();

        if(communicationServices != null && communicationServices.size() > 0) {
            return communicationServices.get(0);
        }
        
        return null;
    }

    public List<Long> getListIdCode(Long communicationServiceID) {
        
        String q = "SELECT DISTINCT ccs.code_id FROM code_communication_service AS ccs, communication_service AS cs " +
                    "WHERE ccs.cooperation_service_id  = " + communicationServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> code = query.getResultList();
        finish();
        if (code != null && code.size() > 0) {
            
            List<Long> codeID = new ArrayList<Long>();
            
            for(Long bi : code){
                codeID.add(bi);
            }
            
            return codeID;
        }
        return null;
    }
    
    public List<Code> getListCode(Long communicationServiceID) {
       
        List<Code> code = new ArrayList<Code>();
        
        List<Long> codeIdList = getListIdCode(communicationServiceID);
        
        if(codeIdList != null && codeIdList.size() > 0) {
            for(Long i : codeIdList){
               Code c = new CodeDAO().getCodeById(i);
               if(c != null) {
                   code.add(c);
               }               
            }     
        }
        
        if (code != null && code.size() > 0) {
            return code;
        }
        return null;
    }
    
    public List<Long> getListIdCommonSense(Long communicationServiceID) {
        
        String q = "SELECT DISTINCT cscs.common_sense_id FROM common_sense_communication_service AS cscs, communication_service AS cs " +
                    "WHERE cscs.cooperation_service_id  = " + communicationServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> commonSense = query.getResultList();
        finish();
        if (commonSense != null && commonSense.size() > 0) {
            
            List<Long> commonSenseID = new ArrayList<Long>();
            
            for(Long bi : commonSense){
                commonSenseID.add(bi);
            }
            
            return commonSenseID;
        }
        return null;
    }
    
    public List<CommonSense> getListCommonSense(Long communicationServiceID) {
       
        List<CommonSense> commonSense = new ArrayList<CommonSense>();
        
        List<Long> commonSenseIdList = getListIdCode(communicationServiceID);
        
        if(commonSenseIdList != null && commonSenseIdList.size() > 0) {
            for(Long i : commonSenseIdList){
               CommonSense cs = new CommonSenseDAO().getCommonSenseById(i);
               if(cs != null) {
                   commonSense.add(cs);
               }               
            }     
        }
        
        if (commonSense != null && commonSense.size() > 0) {
            return commonSense;
        }
        return null;
    }
    
    public List<Long> getListIdCommunicationProtocol(Long communicationServiceID) {
        
        String q = "SELECT DISTINCT cpcs.communication_protocol_id FROM communication_protocol_communication_service AS cpcs, communication_service AS cs " +
                    "WHERE cpcs.cooperation_service_id  = " + communicationServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> communicationProtocol = query.getResultList();
        finish();
        if (communicationProtocol != null && communicationProtocol.size() > 0) {
            
            List<Long> communicationProtocolID = new ArrayList<Long>();
            
            for(Long bi : communicationProtocol){
                communicationProtocolID.add(bi);
            }
            
            return communicationProtocolID;
        }
        return null;
    }
    
    public List<CommunicationProtocol> getListCommunicationProtocol(Long communicationServiceID) {
       
        List<CommunicationProtocol> communicationProtocol = new ArrayList<CommunicationProtocol>();
        
        List<Long> communicationProtocolIdList = getListIdCommunicationProtocol(communicationServiceID);
        
        if(communicationProtocolIdList != null && communicationProtocolIdList.size() > 0) {
            for(Long i : communicationProtocolIdList){
               CommunicationProtocol cp = new CommunicationProtocolDAO().getCommunicationProtocolById(i);
               if(cp != null) {
                   communicationProtocol.add(cp);
               }               
            }     
        }
        
        if (communicationProtocol != null && communicationProtocol.size() > 0) {
            return communicationProtocol;
        }
        return null;
    }
    
    public List<Long> getListIdCompromise(Long communicationServiceID) {
        
        String q = "SELECT DISTINCT cscs.common_sense_id FROM common_sense_communication_service AS cscs, communication_service AS cs " +
                    "WHERE cscs.cooperation_service_id  = " + communicationServiceID;
        
        Query query = getEntityManager().createNativeQuery(q);

        List<Long> compromise = query.getResultList();
        finish();
        if (compromise != null && compromise.size() > 0) {
            
            List<Long> compromiseID = new ArrayList<Long>();
            
            for(Long bi : compromise){
                compromiseID.add(bi);
            }
            
            return compromiseID;
        }
        return null;
    }
    
    public List<Compromise> getListCompromise(Long communicationServiceID) {
       
        List<Compromise> compromise = new ArrayList<Compromise>();
        
        List<Long> compromiseIdList = getListIdCommunicationProtocol(communicationServiceID);
        
        if(compromiseIdList != null && compromiseIdList.size() > 0) {
            for(Long i : compromiseIdList){
               Compromise c = new CompromiseDAO().getCompromiseById(i);
               if(c != null) {
                   compromise.add(c);
               }               
            }     
        }
        
        if (compromise != null && compromise.size() > 0) {
            return compromise;
        }
        return null;
    }
}

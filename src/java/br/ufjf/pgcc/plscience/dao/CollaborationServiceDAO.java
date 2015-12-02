/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.pgcc.plscience.dao;


import br.ufjf.pgcc.plscience.model.CollaborationService;
import br.ufjf.pgcc.plscience.model.CommunicationService;
import br.ufjf.pgcc.plscience.model.CooperationService;
import br.ufjf.pgcc.plscience.model.CoordinationService;
import br.ufjf.pgcc.plscience.model.GroupService;
import java.util.List;
import javax.persistence.Query;


/**
 *
 * @author Guilherme Martins
 */
public class CollaborationServiceDAO extends GenericDAO {
    
    /* ------------------------------------------------------------------- */
    /* ---------------------- Collaboration Service ---------------------- */
    /* ------------------------------------------------------------------- */

    /**
     * Salva um Serviço de Colaboração.
     * @param collaborationService
     */
    public void saveCollaborationService(CollaborationService collaborationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(collaborationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     *
     * @param collaborationService
     */
    public void updateCollaborationService(CollaborationService collaborationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(collaborationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<CollaborationService> getAllCollaborationService() {
        
        Query query = getEntityManager().createQuery("select c from CollaborationService As c");
        List<CollaborationService> collaborationServices = query.getResultList();
        finish();
        return collaborationServices;
    }
        
    /**
     *
     * @param id
     * @return
     */
    public CollaborationService getCollaborationServiceById(Long id) {
        
        Query query = getEntityManager().createQuery("select c from CollaborationService As c where c.id =:id ");
        query.setParameter("id", id);

        List<CollaborationService> collaborationServices = query.getResultList();
        finish();
        if (collaborationServices != null && collaborationServices.size() > 0) {
            return collaborationServices.get(0);
        }
        return null;
    }
    
    public CollaborationService getCollaborationServiceByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CollaborationService c WHERE c.collabServiceName = :collabServiceName");
        query.setParameter("collabServiceName", name);

        List<CollaborationService> collaborationServices = query.getResultList();
        finish();
        if (collaborationServices != null && collaborationServices.size() > 0) {
            return collaborationServices.get(0);
        }
        return null;
    }
    
    public CollaborationService getLastCollaborationService() {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CollaborationService AS c ORDER BY c.id DESC ");

        List<CollaborationService> collaborationServices = query.getResultList();
        finish();
        if (collaborationServices != null && collaborationServices.size() > 0) {
            return collaborationServices.get(0);
        }
        return null;
    }
    
    public Long getLastIdCollaborationService() {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CollaborationService AS c ORDER BY c.id DESC ");

        List<CollaborationService> collaborationServices = query.getResultList();
        finish();
        if (collaborationServices != null && collaborationServices.size() > 0) {
            return collaborationServices.get(0).getId();
        }
        return 0L;
    }
    
    public List<Long> getListIdSteps(Long serviceID) {
        
        String q = "SELECT DISTINCT ss.step_id FROM steps_service AS ss, collaboration_service AS cs " +
                        "WHERE ss.collab_service_id = " + serviceID;
        
        Query query = getEntityManager().createNativeQuery(q);
        //Query query = getEntityManager().createQuery(q);

        List<Long> steps = query.getResultList();
        finish();
        if (steps != null && steps.size() > 0) {
            return steps;
        }
        return null;
    }
    
    /* ------------------------------------------------------------------- */
    /* ---------------------- Communication Service ---------------------- */
    /* ------------------------------------------------------------------- */

    /**
     * Salva as características de "Comunicação" que um Serviço de Colaboração possui.
     * @param communicationService
     */
    public void saveCommunicationService(CommunicationService communicationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(communicationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     *
     * @param communicationService
     */
    public void updateCommunicationService(CommunicationService communicationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(communicationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<CommunicationService> getAllCommunicationService() {
        
        Query query = getEntityManager().createQuery("select c from CommunicationService As c");
        List<CommunicationService> communicationServices = query.getResultList();
        finish();
        return communicationServices;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public CommunicationService getCommunicationServiceById(Long id) {
        
        Query query = getEntityManager().createQuery("select c from CommunicationService As c where c.id =:id ");
        query.setParameter("id", id);

        List<CommunicationService> communicationServices = query.getResultList();
        finish();
        if (communicationServices != null && communicationServices.size() > 0) {
            return communicationServices.get(0);
        }
        return null;
    }
    
    public CommunicationService getLastCommunicationService() {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CommunicationService As c ORDER BY c.id DESC ");

        List<CommunicationService> communicationServices = query.getResultList();
        finish();
        if (communicationServices != null && communicationServices.size() > 0) {
            return communicationServices.get(0);
        }
        return null;
    }

    /* ------------------------------------------------------------------- */
    /* ----------------------- Coordination Service ---------------------- */
    /* ------------------------------------------------------------------- */
     
    /**
     * Salva as características de "Coordenação" que um Serviço de Colaboração possui.
     * @param coordinationService
     */
    public void saveCoordinationService(CoordinationService coordinationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(coordinationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     *
     * @param coordinationService
     */
    public void updateCoordinationService(CoordinationService coordinationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(coordinationService);
        getEntityManager().getTransaction().commit();
        finish();
    }   

    public List<CoordinationService> getAllCoordinationService() {
        
        Query query = getEntityManager().createQuery("select c from CoordinationService As c");
        List<CoordinationService> coordinationServices = query.getResultList();
        finish();
        return coordinationServices;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public CoordinationService getCoordinationServiceById(Long id) {
        
        Query query = getEntityManager().createQuery("select c from CoordinationService As c where c.id =:id ");
        query.setParameter("id", id);

        List<CoordinationService> coordinationServices = query.getResultList();
        finish();
        if (coordinationServices != null && coordinationServices.size() > 0) {
            return coordinationServices.get(0);
        }
        return null;
    }
    
    public CoordinationService getLastCoordinationService() {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CoordinationService As c ORDER BY c.id DESC ");

        List<CoordinationService> coordinationServices = query.getResultList();
        finish();
        if (coordinationServices != null && coordinationServices.size() > 0) {
            return coordinationServices.get(0);
        }
        return null;
    }
    
    /* ------------------------------------------------------------------- */
    /* ----------------------- Cooperation Service ----------------------- */
    /* ------------------------------------------------------------------- */
    
    /**
     * Salva as características de "Cooperação" que um Serviço de Colaboração possui.
     * @param cooperationService
     */
    public void saveCooperationService(CooperationService cooperationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(cooperationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     *
     * @param cooperationService
     */
    public void updateCooperationService(CooperationService cooperationService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(cooperationService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<CooperationService> getAllCooperationService() {
        
        Query query = getEntityManager().createQuery("select c from CooperationService As c");
        List<CooperationService> cooperationServices = query.getResultList();
        finish();
        return cooperationServices;
    }
    
    public CooperationService getCooperationServiceById(Long id) {
        
        Query query = getEntityManager().createQuery("select c from CooperationService As c where c.id =:id ");
        query.setParameter("id", id);

        List<CooperationService> cooperationServices = query.getResultList();
        finish();
        if (cooperationServices != null && cooperationServices.size() > 0) {
            return cooperationServices.get(0);
        }
        return null;
    }
    
     public CooperationService getLastCooperationService() {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CooperationService As c ORDER BY c.id DESC  ");

        List<CooperationService> cooperationServices = query.getResultList();
        finish();
        if (cooperationServices != null && cooperationServices.size() > 0) {
            return cooperationServices.get(0);
        }
        return null;
    }
    
    /* ------------------------------------------------------------------- */
    /* -------------------------- Group Service -------------------------- */
    /* ------------------------------------------------------------------- */
    
    /**
     * Salva as características de "Grupo" que um Serviço de Grupo possui.
     * @param groupService
     */
    public void saveGroupService(GroupService groupService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(groupService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     *
     * @param groupService
     */
    public void updateGroupService(GroupService groupService) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(groupService);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<GroupService> getAllGroupService() {
        
        Query query = getEntityManager().createQuery("select g from GroupService As g");
        List<GroupService> groupServices = query.getResultList();
        finish();
        return groupServices;
    }
    
    public GroupService getGroupServiceById(Long id) {
        
        Query query = getEntityManager().createQuery("select g from GroupService As g where g.id =:id ");
        query.setParameter("id", id);

        List<GroupService> groupServices = query.getResultList();
        finish();
        if (groupServices != null && groupServices.size() > 0) {
            return groupServices.get(0);
        }
        return null;
    }
    
    public GroupService getLastGroupService() {
        
        Query query = getEntityManager().createQuery("SELECT g FROM GroupService As g ORDER BY g.id DESC ");

        List<GroupService> groupServices = query.getResultList();
        finish();
        if (groupServices != null && groupServices.size() > 0) {
            return groupServices.get(0);
        }
        return null;
    }
}

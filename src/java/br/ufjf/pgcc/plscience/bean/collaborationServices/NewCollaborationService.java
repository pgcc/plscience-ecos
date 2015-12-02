/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CollaborationServiceDAO;
import br.ufjf.pgcc.plscience.model.CollaborationService;
import br.ufjf.pgcc.plscience.model.CollaborativeServiceType;
import br.ufjf.pgcc.plscience.model.CommunicationService;
import br.ufjf.pgcc.plscience.model.CooperationService;
import br.ufjf.pgcc.plscience.model.CoordinationService;
import br.ufjf.pgcc.plscience.model.GroupService;
import br.ufjf.pgcc.plscience.model.StepsScientificExperimentation;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Guilherme MArtins
 */
@ManagedBean()
@ViewScoped
public class NewCollaborationService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private CollaborationService collaborationService;
    private CommunicationService communicationService;
    private CooperationService cooperationService;
    private CoordinationService coordinationService;
    private GroupService groupService;
    
    private CollaborativeServiceType collaborativeServiceType;
    private List<StepsScientificExperimentation> stepsScientificExperimentation;
    
    public NewCollaborationService() {
        this.collaborationService = new CollaborationService();
        this.communicationService = new CommunicationService();
        this.cooperationService = new CooperationService();
        this.coordinationService = new CoordinationService();
        this.groupService = new GroupService();
        this.stepsScientificExperimentation = new ArrayList<StepsScientificExperimentation>() ;
    }

    /**
     * Salva um serviço de colaboração.
     */
    public void saveCollaborationService() {
        try {
            
            System.out.println("\n\n" + "TESTE - INICIO" + "\n\n");
            
            //Salva Características de Comunicação
            getCommunicationService().setId(null);
            new CollaborationServiceDAO().saveCommunicationService(getCommunicationService());
            
            //Salva Características de Cooperação
            getCooperationService().setId(null);
            new CollaborationServiceDAO().saveCooperationService(getCooperationService());
            
            //Salva Características de Coodernação
            getCoordinationService().setId(null);
            getCoordinationService().setRolerList(null);
            new CollaborationServiceDAO().saveCoordinationService(getCoordinationService());
            
            //Salva Características de Formação de Grupo.
            getGroupService().setId(null);
            getGroupService().setCompetenceList(null);
            new CollaborationServiceDAO().saveGroupService(getGroupService());
            
            //Salva Características de Colaboração.
            getCollaborationService().setId(new CollaborationServiceDAO().getLastIdCollaborationService() + 1);
            
            //Adiciona as etapas do ciclo de vida.
            getCollaborationService().setStepsScientificExperimentationList(getStepsScientificExperimentation());
            
            getCollaborationService().setCollaborativeServiceType(getCollaborativeServiceType());
            
            getCollaborationService().setCommunicationServiceId(new CollaborationServiceDAO().getLastCommunicationService());            
            getCollaborationService().setCooperationServiceId(new CollaborationServiceDAO().getLastCooperationService());            
            getCollaborationService().setCoordinationServiceId(new CollaborationServiceDAO().getLastCoordinationService());
            getCollaborationService().setGroupServiceId(new CollaborationServiceDAO().getLastGroupService());
            
            //APAGAR!
            System.out.println("\n\n" + "TESTE - INICIO" + "\n\n");
            System.out.println(getCollaborationService().getId() + "\n");
            System.out.println(getCollaborationService().getCollabServiceName() + "\n");
            System.out.println(getCollaborationService().getDescription()+ "\n");
            System.out.println(getCollaborationService().getCommunicationServiceId().getId().toString() + "\n");
            System.out.println(getCollaborationService().getCooperationServiceId().getId().toString() + "\n");
            System.out.println(getCollaborationService().getCoordinationServiceId().getId().toString() + "\n");
            System.out.println(getCollaborationService().getGroupServiceId().getId().toString() + "\n");
            System.out.println(getCollaborationService().getStepsScientificExperimentationList().size()+ "\n");
            System.out.println("\n" + "TESTE - FIM" + "\n\n");
                        
            new CollaborationServiceDAO().updateCollaborationService(getCollaborationService());
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Collaboration Service saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }

    /**
     * @return the collaborationService
     */
    public CollaborationService getCollaborationService() {
        return collaborationService;
    }

    /**
     * @param collaborationService the collaborationService to set
     */
    public void setCollaborationService(CollaborationService collaborationService) {
        this.collaborationService = collaborationService;
    }

    /**
     * @return the communicationService
     */
    public CommunicationService getCommunicationService() {
        return communicationService;
    }

    /**
     * @param communicationService the communicationService to set
     */
    public void setCommunicationService(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    /**
     * @return the cooperationService
     */
    public CooperationService getCooperationService() {
        return cooperationService;
    }

    /**
     * @param cooperationService the cooperationService to set
     */
    public void setCooperationService(CooperationService cooperationService) {
        this.cooperationService = cooperationService;
    }

    /**
     * @return the coordinationService
     */
    public CoordinationService getCoordinationService() {
        return coordinationService;
    }

    /**
     * @param coordinationService the coordinationService to set
     */
    public void setCoordinationService(CoordinationService coordinationService) {
        this.coordinationService = coordinationService;
    }

    /**
     * @return the collaborativeServiceType
     */
    public CollaborativeServiceType getCollaborativeServiceType() {
        return collaborativeServiceType;
    }

    /**
     * @return the groupService
     */
    public GroupService getGroupService() {
        return groupService;
    }

    /**
     * @param groupService the groupService to set
     */
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
    
    /**
     * @param collaborativeServiceType the collaborativeServiceType to set
     */
    public void setCollaborativeServiceType(CollaborativeServiceType collaborativeServiceType) {
        this.collaborativeServiceType = collaborativeServiceType;
    }

    /**
     * @return the stepsScientificExperimentation
     */
    public List<StepsScientificExperimentation> getStepsScientificExperimentation() {
        return stepsScientificExperimentation;
    }

    /**
     * @param stepsScientificExperimentation the stepsScientificExperimentation to set
     */
    public void setStepsScientificExperimentation(List<StepsScientificExperimentation> stepsScientificExperimentation) {
        this.stepsScientificExperimentation = stepsScientificExperimentation;
    }
    
}

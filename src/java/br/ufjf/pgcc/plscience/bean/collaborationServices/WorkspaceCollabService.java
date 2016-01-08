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
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.bean.collaborationServices.*;
import br.ufjf.pgcc.plscience.dao.CollaborationServiceDAO;
import br.ufjf.pgcc.plscience.dao.CommunicationServiceDAO;
import br.ufjf.pgcc.plscience.dao.CooperationServiceDAO;
import br.ufjf.pgcc.plscience.dao.CoordinationServiceDAO;
import br.ufjf.pgcc.plscience.dao.GroupServiceDAO;
import br.ufjf.pgcc.plscience.model.ActivityConcept;
import br.ufjf.pgcc.plscience.model.Artifact;
import br.ufjf.pgcc.plscience.model.Code;
import br.ufjf.pgcc.plscience.model.CollaborationService;
import br.ufjf.pgcc.plscience.model.CommonSense;
import br.ufjf.pgcc.plscience.model.CommunicationProtocol;
import br.ufjf.pgcc.plscience.model.Competence;
import br.ufjf.pgcc.plscience.model.Compromise;
import br.ufjf.pgcc.plscience.model.Product;
import br.ufjf.pgcc.plscience.model.Roler;
import br.ufjf.pgcc.plscience.model.Status;
import br.ufjf.pgcc.plscience.model.TaskConcept;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@SessionScoped
public class WorkspaceCollabService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private CollaborationService collaborationService;
    
    //Características de Coordenação
    private List<Roler> rolerList;
    private List<Status> statusList;
    
    //Características de Cooperação
    private List<ActivityConcept> activityConceptList;
    private List<Artifact> artifactList;
    private List<Product> productList;
    private List<TaskConcept> taskConceptList;
    
    //Características de Comunicação
    private List<Code> codeList;
    private List<CommonSense> commonSenseList;
    private List<CommunicationProtocol> communicationProtocolList;
    private List<Compromise> compromiseList;

    //Características de Formação de Grupo
    private List<Competence> competenceList;
    
    public void updateCollaborationService(){
        try {
            
            getCollaborationService().getCoordinationServiceId().setRolerList(getRolerList());
            getCollaborationService().getGroupServiceId().setCompetenceList(getCompetenceList());

            new CollaborationServiceDAO().updateCollaborationService(getCollaborationService());
            new CollaborationServiceDAO().updateCoordinationService(getCollaborationService().getCoordinationServiceId());
            new CollaborationServiceDAO().updateGroupService(getCollaborationService().getGroupServiceId());
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Features added with success!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }  
    }
    
    public void recoverFeatures() {
        
        //Coordenação
        rolerList.clear();               
        statusList.clear();
        rolerList = new CoordinationServiceDAO().getListRolers(collaborationService.getId());
        statusList = new CoordinationServiceDAO().getListStatus(collaborationService.getId());
        
        //Cooperação
        activityConceptList.clear();        
        artifactList.clear();
        productList.clear();
        taskConceptList.clear();
        activityConceptList = new CooperationServiceDAO().getListActivityConcept(collaborationService.getId());
        artifactList = new CooperationServiceDAO().getListArtifact(collaborationService.getId());
        productList = new CooperationServiceDAO().getListProduct(collaborationService.getId());
        taskConceptList = new CooperationServiceDAO().getListTaskConcept(collaborationService.getId());
                
        //Comunicação
        codeList.clear();
        commonSenseList.clear();
        communicationProtocolList.clear();
        compromiseList.clear();
        codeList = new CommunicationServiceDAO().getListCode(collaborationService.getId());
        commonSenseList = new CommunicationServiceDAO().getListCommonSense(collaborationService.getId());
        communicationProtocolList = new CommunicationServiceDAO().getListCommunicationProtocol(collaborationService.getId());
        compromiseList = new CommunicationServiceDAO().getListCompromise(collaborationService.getId());
                
        //Formação de Grupos
        competenceList.clear();
        competenceList = new GroupServiceDAO().getListCompetences(collaborationService.getId());
        
        
    }
    
    public boolean checkRole() {       
        return collaborationService.getCoordinationServiceId().getRole();
    }
    
    public boolean checkStatus() {       
        return collaborationService.getCoordinationServiceId().getStatus();
    }
    
    public boolean checkActivityConcept() {       
        return collaborationService.getCooperationServiceId().getActivity();
    }
    
    public boolean checkArtifact() {       
        return collaborationService.getCooperationServiceId().getArtifact();
    }
    
    public boolean checkProduct() {       
        return collaborationService.getCooperationServiceId().getProduct();
    }
    
    public boolean checkTaskConcept() {       
        return collaborationService.getCooperationServiceId().getTask();
    }
    
    public boolean checkCode() {       
        return collaborationService.getCommunicationServiceId().getCode();
    }
    
    public boolean checkCommonSense() {       
        return collaborationService.getCommunicationServiceId().getCommonSense();
    }
    
    public boolean checkCommunicationProtocol() {       
        return collaborationService.getCommunicationServiceId().getCommunicationProtocol();
    }
    
    public boolean checkCompromise() {       
        return collaborationService.getCommunicationServiceId().getCompromise();
    }
    
    public boolean checkCompetence() {
        return collaborationService.getGroupServiceId().getCompetence();
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
     * @return the rolerList
     */
    public List<Roler> getRolerList() {
        return rolerList;
    }

    /**
     * @param rolerList the rolerList to set
     */
    public void setRolerList(List<Roler> rolerList) {
        this.rolerList = rolerList;
    }

    /**
     * @return the statusList
     */
    public List<Status> getStatusList() {
        return statusList;
    }

    /**
     * @param statusList the statusList to set
     */
    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }
    
    /**
     * @return the activituConceptList
     */
    public List<ActivityConcept> getActivityConceptList() {
        return activityConceptList;
    }

    /**
     * @param activityConceptList the activituConceptList to set
     */
    public void setActivityConceptList(List<ActivityConcept> activityConceptList) {
        this.activityConceptList = activityConceptList;
    }

    /**
     * @return the artifactList
     */
    public List<Artifact> getArtifactList() {
        return artifactList;
    }

    /**
     * @param artifactList the artifactList to set
     */
    public void setArtifactList(List<Artifact> artifactList) {
        this.artifactList = artifactList;
    }

    /**
     * @return the productList
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * @param productList the productList to set
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    /**
     * @return the taskConceptList
     */
    public List<TaskConcept> getTaskConceptList() {
        return taskConceptList;
    }

    /**
     * @param taskConceptList the taskConceptList to set
     */
    public void setTaskConceptList(List<TaskConcept> taskConceptList) {
        this.taskConceptList = taskConceptList;
    }
    
    /**
     * @return the codeList
     */
    public List<Code> getCodeList() {
        return codeList;
    }

    /**
     * @param codeList the codeList to set
     */
    public void setCodeList(List<Code> codeList) {
        this.codeList = codeList;
    }

    /**
     * @return the commonSenseList
     */
    public List<CommonSense> getCommonSenseList() {
        return commonSenseList;
    }

    /**
     * @param commonSenseList the commonSenseList to set
     */
    public void setCommonSenseList(List<CommonSense> commonSenseList) {
        this.commonSenseList = commonSenseList;
    }

    /**
     * @return the communicationProtocolList
     */
    public List<CommunicationProtocol> getCommunicationProtocolList() {
        return communicationProtocolList;
    }

    /**
     * @param communicationProtocolList the communicationProtocolList to set
     */
    public void setCommunicationProtocolList(List<CommunicationProtocol> communicationProtocolList) {
        this.communicationProtocolList = communicationProtocolList;
    }

    /**
     * @return the compromiseList
     */
    public List<Compromise> getCompromiseList() {
        return compromiseList;
    }

    /**
     * @param compromiseList the compromiseList to set
     */
    public void setCompromiseList(List<Compromise> compromiseList) {
        this.compromiseList = compromiseList;
    }

    
    
    /**
     * @return the competenceList
     */
    public List<Competence> getCompetenceList() {
        return competenceList;
        //return competenceList;
    }

    /**
     * @param competenceList the competenceList to set
     */
    public void setCompetenceList(List<Competence> competenceList) {
        this.competenceList = competenceList;
    }  
}

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
import br.ufjf.pgcc.plscience.dao.CoordinationServiceDAO;
import br.ufjf.pgcc.plscience.dao.GroupServiceDAO;
import br.ufjf.pgcc.plscience.model.CollaborationService;
import br.ufjf.pgcc.plscience.model.Competence;
import br.ufjf.pgcc.plscience.model.Roler;
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
        
        rolerList.clear();
        rolerList = new CoordinationServiceDAO().getListRolers(collaborationService.getId());
        
        competenceList.clear();
        competenceList = new GroupServiceDAO().getListCompetences(collaborationService.getId());
        
        
    }
    
    public boolean checkRole() {
        if(rolerList != null && !rolerList.isEmpty()) {
            rolerList.clear();
        }        
        return collaborationService.getCoordinationServiceId().getRole();
    }
    
    public boolean checkCompetence() {
        if(competenceList != null && !competenceList.isEmpty()) {
            competenceList.clear();
        }
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

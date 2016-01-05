/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.ArtifactDAO;
import br.ufjf.pgcc.plscience.model.Artifact;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean()
@ViewScoped
public class NewArtifact implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Artifact artifact;

    public NewArtifact() {
        this.artifact = new Artifact();
    }
    
    public void save() {    
        try { 
            getArtifact().setId(null);
            new ArtifactDAO().save(getArtifact()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Artifact saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }

    /**
     * @return the artifact
     */
    public Artifact getArtifact() {
        return artifact;
    }

    /**
     * @param artifact the artifact to set
     */
    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.ArtifactDAO;
import br.ufjf.pgcc.plscience.model.Artifact;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "artifactBean")
@ViewScoped
public class ArtifactBean implements Serializable {
    
    private Artifact artifact = new Artifact();
    private List artifacts = new ArrayList();
    
    public ArtifactBean() {
        artifacts = new ArtifactDAO().getAll();
        artifact = new Artifact();
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

    /**
     * @return the artifacts
     */
    public List getArtifacts() {
        return artifacts;
    }

    /**
     * @param artifacts the artifacts to set
     */
    public void setArtifacts(List artifacts) {
        this.artifacts = artifacts;
    }

    
}

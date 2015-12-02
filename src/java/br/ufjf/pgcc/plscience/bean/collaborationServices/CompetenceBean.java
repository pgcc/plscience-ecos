/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CompetenceDAO;
import br.ufjf.pgcc.plscience.model.Competence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "competenceBean")
@ViewScoped
public class CompetenceBean implements Serializable {
    
    private Competence competence = new Competence();
    private List competences = new ArrayList();
    
    public CompetenceBean() {
        competences = new CompetenceDAO().getAll();
        competence = new Competence();
    }

    /**
     * @return the competence
     */
    public Competence getCompetence() {
        return competence;
    }

    /**
     * @param competence the competence to set
     */
    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    /**
     * @return the competences
     */
    public List getCompetences() {
        return competences;
    }

    /**
     * @param competences the competences to set
     */
    public void setCompetences(List competences) {
        this.competences = competences;
    }
    
}

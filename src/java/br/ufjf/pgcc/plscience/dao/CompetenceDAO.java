/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Competence;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class CompetenceDAO extends GenericDAO {
    
    public void save(Competence competence) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(competence);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(Competence competence) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(competence);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<Competence> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM Competence c");
        List<Competence> competences = query.getResultList();
        finish();
        return competences;
    }
    
    public Competence getCompetenceById(Long id) {
        
        Query query = getEntityManager().createQuery("select c from Competence As c where c.id =:id ");
        query.setParameter("id", id);

        List<Competence> competences = query.getResultList();
        finish();
        if (competences != null && competences.size() > 0) {
            return competences.get(0);
        }
        return null;
    }
    
    public Competence getCompetenceByName(String name) {
        
        Query query = getEntityManager().createQuery("select c from Competence As c where c.competenceName =:competenceName ");
        query.setParameter("competenceName", name);

        List<Competence> competences = query.getResultList();
        finish();
        if (competences != null && competences.size() > 0) {
            return competences.get(0);
        }
        return null;
    }

}

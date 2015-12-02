/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.StepsScientificExperimentation;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class StepsScientificExperimentationDAO extends GenericDAO {
    
     public void save(StepsScientificExperimentation stepsScientificExperimentation) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(stepsScientificExperimentation);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(StepsScientificExperimentation stepsScientificExperimentation) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(stepsScientificExperimentation);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<StepsScientificExperimentation> getAll() {
        Query query = getEntityManager().createQuery("SELECT s FROM StepsScientificExperimentation s");
        List<StepsScientificExperimentation> experiments = query.getResultList();
        finish();
        return experiments;
    }
    
    public StepsScientificExperimentation getStepsScientificExperimentationById(Long id) {
        
        Query query = getEntityManager().createQuery("select s from StepsScientificExperimentation As s where s.id =:id ");
        query.setParameter("id", id);

        List<StepsScientificExperimentation> stepsScientificExperimentations = query.getResultList();
        finish();
        if (stepsScientificExperimentations != null && stepsScientificExperimentations.size() > 0) {
            return stepsScientificExperimentations.get(0);
        }
        return null;
    }
    
    public StepsScientificExperimentation getStepsScientificExperimentationByName(String name) {
        
        Query query = getEntityManager().createQuery("select s from StepsScientificExperimentation As s where s.nameStep =:nameStep ");
        query.setParameter("nameStep", name);

        List<StepsScientificExperimentation> stepsScientificExperimentations = query.getResultList();
        finish();
        if (stepsScientificExperimentations != null && stepsScientificExperimentations.size() > 0) {
            return stepsScientificExperimentations.get(0);
        }
        return null;
    }
    
    public StepsScientificExperimentation getCollaborativeServiceTypeByLevel(Integer numberStep) {
        
        Query query = getEntityManager().createQuery("select s from StepsScientificExperimentation As s where s.numberStep =:numberStep ");
        query.setParameter("hierarchyLevel", numberStep);

        List<StepsScientificExperimentation> stepsScientificExperimentations = query.getResultList();
        finish();
        if (stepsScientificExperimentations != null && stepsScientificExperimentations.size() > 0) {
            return stepsScientificExperimentations.get(0);
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Artifact;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class ArtifactDAO extends GenericDAO {
    
    public void save(Artifact artifact) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(artifact);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(Artifact artifact) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(artifact);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<Artifact> getAll() {
        Query query = getEntityManager().createQuery("SELECT a FROM Artifact AS a");
        List<Artifact> artifacts = query.getResultList();
        finish();
        return artifacts;
    }
    
    public Artifact getArtifactById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT a FROM Artifact AS a WHERE a.id = :id");
        query.setParameter("id", id);

        List<Artifact> artifacts = query.getResultList();
        finish();
        if (artifacts != null && artifacts.size() > 0) {
            return artifacts.get(0);
        }
        return null;
    }
    
    public Artifact getArtifactByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT a FROM Artifact AS a WHERE a.artifactName = :artifactName");
        query.setParameter("artifactName", name);

        List<Artifact> artifacts = query.getResultList();
        finish();
        if (artifacts != null && artifacts.size() > 0) {
            return artifacts.get(0);
        }
        return null;
    }
    
}

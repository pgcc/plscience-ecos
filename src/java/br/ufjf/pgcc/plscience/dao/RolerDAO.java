/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Roler;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class RolerDAO extends GenericDAO {
    
    public void save(Roler role) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(role);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(Roler role) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(role);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<Roler> getAll() {
        Query query = getEntityManager().createQuery("SELECT r FROM Roler AS r");
        List<Roler> roles = query.getResultList();
        finish();
        return roles;
    }
    
    public Roler getRoleById(Long id) {
        
        Query query = getEntityManager().createQuery("select r from Roler As r where r.id =:id ");
        query.setParameter("id", id);

        List<Roler> roles = query.getResultList();
        finish();
        if (roles != null && roles.size() > 0) {
            return roles.get(0);
        }
        return null;
    }
    
    public Roler getRoleByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT r FROM Roler r WHERE r.roleName = :roleName");
        query.setParameter("roleName", name);

        List<Roler> roles = query.getResultList();
        finish();
        if (roles != null && roles.size() > 0) {
            return roles.get(0);
        }
        return null;
    }
    
    public Roler getRoleByHierarchyLevel(Integer hierarchyLevel) {
        
        Query query = getEntityManager().createQuery("SELECT r FROM Roler r WHERE r.hierarchyLevel = :hierarchyLevel");
        query.setParameter("hierarchyLevel", hierarchyLevel);

        List<Roler> roles = query.getResultList();
        finish();
        if (roles != null && roles.size() > 0) {
            return roles.get(0);
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.GroupUser;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class GroupUserDAO extends GenericDAO {
    
    public void save(GroupUser groupUser) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(groupUser);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(GroupUser groupUser) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(groupUser);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<GroupUser> getAll() {
        Query query = getEntityManager().createQuery("SELECT g FROM GroupUser AS g");
        List<GroupUser> groups = query.getResultList();
        finish();
        return groups;
    }
    
    public GroupUser getGroupUserById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT g FROM GroupUser AS g WHERE g.id = :id");
        query.setParameter("id", id);

        List<GroupUser> groups = query.getResultList();
        finish();
        if (groups != null && groups.size() > 0) {
            return groups.get(0);
        }
        return null;
    }
    
    public GroupUser getGroupUserByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT g FROM GroupUser g WHERE g.groupName = :groupName");
        query.setParameter("groupName", name);

        List<GroupUser> roles = query.getResultList();
        finish();
        if (roles != null && roles.size() > 0) {
            return roles.get(0);
        }
        return null;
    }
    
}

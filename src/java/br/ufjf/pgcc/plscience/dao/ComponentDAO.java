/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Component;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class ComponentDAO extends GenericDAO{
    
    public static ComponentDAO componentDAO;
    
    public static ComponentDAO getInstance(){
        if(componentDAO == null){
            componentDAO = new ComponentDAO();            
        }
        return componentDAO;
    }
    
    /**
     * Save a component on the E-SECO platform repository
     * @param component 
     */
    public void save(Component component) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(component);
        getEntityManager().getTransaction().commit();
        finish();
    }
        
    /**
     * Update a service on the E-SECO platform repository
     * @param component 
     */
    public void update(Component component) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(component);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * Get all components
     * @return 
     */
    public List<Component> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM Component AS c");
        List<Component> componentsList = query.getResultList();
        finish();
        return componentsList;
    }
    
    /**
     * Get a component on the E-SECO platform repository by id
     * @param id
     * @return 
     */
    public Component getComponentById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM Component AS c WHERE c.id = :id");
        query.setParameter("id", id);

        List<Component> componentsList = query.getResultList();
        finish();
        if (componentsList != null && componentsList.size() > 0) {
            return componentsList.get(0);
        }
        return null;
    }

    /**
     * Get a component on the E-SECO platform repository by component name
     * @param name
     * @return 
     */
    public Component getComponentByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM Component AS c WHERE c.componentName = :componentName");
        query.setParameter("componentName", name);

        List<Component> component = query.getResultList();
        finish();
        if (component != null && component.size() > 0) {
            return component.get(0);
        }
        return null;
    }
    
}

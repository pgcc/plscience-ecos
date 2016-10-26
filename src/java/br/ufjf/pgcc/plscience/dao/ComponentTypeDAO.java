/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.ComponentType;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class ComponentTypeDAO extends GenericDAO{
 
    public static ComponentTypeDAO componentTypeDAO;
    
    public static ComponentTypeDAO getInstance(){
        if(componentTypeDAO == null){
            componentTypeDAO = new ComponentTypeDAO();            
        }
        return componentTypeDAO;
    }
    
    public void save(ComponentType componentType) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(componentType);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(ComponentType componentType) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(componentType);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * get all components type (artifacts on the e-seco platform)
     * @return 
     */
    public List<ComponentType> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM ComponentType AS c");
        List<ComponentType> componentsTypeList = query.getResultList();
        finish();
        return componentsTypeList;
    }
    
    /**
     * get a component type by an id
     * @param id
     * @return 
     */
    public ComponentType getComponentTypeById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM ComponentType AS c WHERE c.id = :id");
        query.setParameter("id", id);

        List<ComponentType> componentsTypeList = query.getResultList();
        finish();
        if (componentsTypeList != null && componentsTypeList.size() > 0) {
            return componentsTypeList.get(0);
        }
        return null;
    }    
    
    /**
     * get a component type by a description
     * @param description
     * @return 
     */
    public ComponentType getComponentTypeByDescription(String description) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM ComponentType AS c WHERE c.componentTypeDescription = :componentTypeDescription");
        query.setParameter("componentTypeDescription", description);

        List<ComponentType> componentsTypeList = query.getResultList();
        finish();
        if (componentsTypeList != null && componentsTypeList.size() > 0) {
            return componentsTypeList.get(0);
        }
        return null;
    }    
    
}

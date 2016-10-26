/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.HardwareConfigurations;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class HardwareConfigurationsDAO extends GenericDAO{
 
    public static HardwareConfigurationsDAO hardwareConfigurationsDAO;
    
    public static HardwareConfigurationsDAO getInstance(){
        if(hardwareConfigurationsDAO == null){
            hardwareConfigurationsDAO = new HardwareConfigurationsDAO();            
        }
        return hardwareConfigurationsDAO;
    }
    
    /**
     * Save the hardware configurations of a service
     * @param hc 
     */
    public void save(HardwareConfigurations hc) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(hc);
        getEntityManager().getTransaction().commit();
        finish();
    }

    /**
     * Update the hardware configurations of a service
     * @param hc 
     */
    public void update(HardwareConfigurations hc) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(hc);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * Get all hardware configurations
     * @return 
     */
    public List<HardwareConfigurations> getAll() {
        Query query = getEntityManager().createQuery("SELECT h FROM HardwareConfigurations AS h");
        List<HardwareConfigurations> HardwareConfigurationsList = query.getResultList();
        finish();
        return HardwareConfigurationsList;
    }
    
    /**
     * get the Hardware Configurations by Id
     * @param id
     * @return 
     */
    public HardwareConfigurations getHardwareConfigurationsById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT h FROM HardwareConfigurations AS h WHERE h.id = :id");
        query.setParameter("id", id);

        List<HardwareConfigurations> HardwareConfigurationsList = query.getResultList();
        finish();
        if (HardwareConfigurationsList != null && HardwareConfigurationsList.size() > 0) {
            return HardwareConfigurationsList.get(0);
        }
        return null;
    }    
    
}

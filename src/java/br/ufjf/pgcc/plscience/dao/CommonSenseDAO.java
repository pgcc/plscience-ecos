/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.CommonSense;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class CommonSenseDAO extends GenericDAO {
    
    public void save(CommonSense commonSense) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(commonSense);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(CommonSense commonSense) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(commonSense);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<CommonSense> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM CommonSense AS c");
        List<CommonSense> commonSenses = query.getResultList();
        finish();
        return commonSenses;
    }
    
    public CommonSense getCommonSenseById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CommonSense AS c WHERE c.id = :id");
        query.setParameter("id", id);

        List<CommonSense> commonSenses = query.getResultList();
        finish();
        if (commonSenses != null && commonSenses.size() > 0) {
            return commonSenses.get(0);
        }
        return null;
    }
    
    public CommonSense getCommonSenseByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CommonSense AS c WHERE c.commonSenseName = :commonSenseName");
        query.setParameter("commonSenseName", name);

        List<CommonSense> commonSenses = query.getResultList();
        finish();
        if (commonSenses != null && commonSenses.size() > 0) {
            return commonSenses.get(0);
        }
        return null;
    }
    
}

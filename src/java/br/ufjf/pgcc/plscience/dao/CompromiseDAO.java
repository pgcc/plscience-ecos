/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Compromise;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class CompromiseDAO extends GenericDAO {
    
    public void save(Compromise compromise) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(compromise);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(Compromise compromise) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(compromise);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<Compromise> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM Compromise AS c");
        List<Compromise> compromises = query.getResultList();
        finish();
        return compromises;
    }
    
    public Compromise getCompromiseById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM Compromise AS c WHERE c.id = :id");
        query.setParameter("id", id);

        List<Compromise> compromises = query.getResultList();
        finish();
        if (compromises != null && compromises.size() > 0) {
            return compromises.get(0);
        }
        return null;
    }
    
    public Compromise getCompromiseByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM Compromise AS c WHERE c.compromiseName = :compromiseName");
        query.setParameter("compromiseName", name);

        List<Compromise> compromises = query.getResultList();
        finish();
        if (compromises != null && compromises.size() > 0) {
            return compromises.get(0);
        }
        return null;
    }
    
}

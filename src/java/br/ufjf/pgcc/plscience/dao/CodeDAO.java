/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Code;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class CodeDAO extends GenericDAO {
    
    public void save(Code code) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(code);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(Code code) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(code);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<Code> getAll() {
        Query query = getEntityManager().createQuery("SELECT c FROM Code AS c");
        List<Code> codes = query.getResultList();
        finish();
        return codes;
    }
    
    public Code getCodeById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM Code AS c WHERE c.id = :id");
        query.setParameter("id", id);

        List<Code> codes = query.getResultList();
        finish();
        if (codes != null && codes.size() > 0) {
            return codes.get(0);
        }
        return null;
    }
    
    public Code getCodeByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM Code AS c WHERE c.codeName = :codeName");
        query.setParameter("codeName", name);

        List<Code> codes = query.getResultList();
        finish();
        if (codes != null && codes.size() > 0) {
            return codes.get(0);
        }
        return null;
    }
    
}

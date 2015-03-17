/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasDerivedFrom;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasDerivedFromDAO {
    
    
    public static WasDerivedFromDAO wasDerivedFromDAO;

    public static WasDerivedFromDAO getInstance() {
        if (wasDerivedFromDAO == null) {
            wasDerivedFromDAO = new WasDerivedFromDAO();
        }
        return wasDerivedFromDAO;
    }

    /**
     * Busca uma WasDerivedFrom especifica
     *
     * @param name
     * @return
     */
    public WasDerivedFrom buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasDerivedFrom as a where  upper(a.name)=:wasDerivedFrom");
        query.setParameter("wasDerivedFrom", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasDerivedFrom> WasDerivedFrom = query.getResultList();
        if (WasDerivedFrom != null && WasDerivedFrom.size() > 0) {
            return WasDerivedFrom.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasDerivedFroms
     *
     * @return
     */
    public List<WasDerivedFrom> buscarTodas() {
     EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasDerivedFrom As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasDerivedFrom
     *
     * @param idWasDerivedFrom
     */
    public void remover(WasDerivedFrom idWasDerivedFrom) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasDerivedFrom = em.merge(idWasDerivedFrom);
        em.remove(idWasDerivedFrom);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasDerivedFrom
     *
     * @param wasDerivedFrom
     * @return
     */
    public WasDerivedFrom persistir(WasDerivedFrom wasDerivedFrom) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasDerivedFrom = em.merge(wasDerivedFrom);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasDerivedFrom;
    }

    
}

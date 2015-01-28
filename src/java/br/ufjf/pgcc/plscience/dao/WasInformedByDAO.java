/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasInformedBy;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasInformedByDAO {

    public static WasInformedByDAO wasInformedByPortEntityDAO;

    public static WasInformedByDAO getInstance() {
        if (wasInformedByPortEntityDAO == null) {
            wasInformedByPortEntityDAO = new WasInformedByDAO();
        }
        return wasInformedByPortEntityDAO;
    }

    /**
     * Busca uma WasInformedBy especifica
     *
     * @param name
     * @return
     */
    public WasInformedBy buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from WasInformedBy as a where  upper(a.name)=:wasInformedByPortEntity");
        query.setParameter("wasInformedByPortEntity", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<WasInformedBy> WasInformedBy = query.getResultList();
        if (WasInformedBy != null && WasInformedBy.size() > 0) {
            return WasInformedBy.get(0);
        }

        return null;
    }

    /**
     * Busca todas as WasInformedBys
     *
     * @return
     */
    public List<WasInformedBy> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasInformedBy As a");
        return query.getResultList();
    }

    /**
     * Remove uma WasInformedBy
     *
     * @param idWasInformedBy
     */
    public void remover(WasInformedBy idWasInformedBy) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idWasInformedBy = em.merge(idWasInformedBy);
        em.remove(idWasInformedBy);
        em.getTransaction().commit();
    }

    /**
     * Persite uma WasInformedBy
     *
     * @param wasInformedByPortEntity
     * @return
     */
    public WasInformedBy persistir(WasInformedBy wasInformedByPortEntity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasInformedByPortEntity = em.merge(wasInformedByPortEntity);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return wasInformedByPortEntity;
    }

}

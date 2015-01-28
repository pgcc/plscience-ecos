/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.ResearchGroup;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class ResearchGroupDAO {
    
     public static ResearchGroupDAO researchGroupDAO;

    public static ResearchGroupDAO getInstance() {
        if (researchGroupDAO == null) {
            researchGroupDAO = new ResearchGroupDAO();
        }
        return researchGroupDAO;
    }

    /**
     * Busca uma ResearchGroup especifica
     *
     * @param name
     * @return
     */
    public ResearchGroup buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from ResearchGroup as a where  upper(a.name)=:researchGroup");
        query.setParameter("researchGroup", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<ResearchGroup> ResearchGroup = query.getResultList();
        if (ResearchGroup != null && ResearchGroup.size() > 0) {
            return ResearchGroup.get(0);
        }

        return null;
    }

    /**
     * Busca todas as ResearchGroups
     *
     * @return
     */
    public List<ResearchGroup> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from ResearchGroup As a");
        return query.getResultList();
    }

    /**
     * Remove uma ResearchGroup
     *
     * @param idResearchGroup
     */
    public void remover(ResearchGroup idResearchGroup) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idResearchGroup = em.merge(idResearchGroup);
        em.remove(idResearchGroup);
        em.getTransaction().commit();
    }

    /**
     * Persite uma ResearchGroup
     *
     * @param researchGroup
     * @return
     */
    public ResearchGroup persistir(ResearchGroup researchGroup) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            researchGroup = em.merge(researchGroup);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return researchGroup;
    }

    
}

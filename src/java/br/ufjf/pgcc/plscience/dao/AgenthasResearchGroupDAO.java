/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.AgenthasResearchGroup;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class AgenthasResearchGroupDAO {

    public static AgenthasResearchGroupDAO agenthasResearchGroupDAO;

    public static AgenthasResearchGroupDAO getInstance() {
        if (agenthasResearchGroupDAO == null) {
            agenthasResearchGroupDAO = new AgenthasResearchGroupDAO();
        }
        return agenthasResearchGroupDAO;
    }

    /**
     * Busca uma especifica
     *
     * @param nome
     * @return
     */
    public AgenthasResearchGroup buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from AgenthasResearchGroup As a where a.descriptyon =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<AgenthasResearchGroup> agenthasResearchGroups = query.getResultList();
        if (agenthasResearchGroups != null && agenthasResearchGroups.size() > 0) {
            return agenthasResearchGroups.get(0);
        }

        return null;
    }

    /**
     * Busca todas
     *
     * @return
     */
    public List<AgenthasResearchGroup> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from AgenthasResearchGroup As a");
        return query.getResultList();
    }

    /**
     * Remove uma agenthasResearchGroup
     *
     * @param agenthasResearchGroup
     */
    public void remover(AgenthasResearchGroup agenthasResearchGroup) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        agenthasResearchGroup = em.merge(agenthasResearchGroup);
        em.remove(agenthasResearchGroup);
        em.getTransaction().commit();
    }

    /**
     * Persite uma AgenthasResearchGroup
     *
     * @param agenthasResearchGroup
     * @return
     */
    public AgenthasResearchGroup persistir(AgenthasResearchGroup agenthasResearchGroup) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            agenthasResearchGroup = em.merge(agenthasResearchGroup);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agenthasResearchGroup;
    }

}

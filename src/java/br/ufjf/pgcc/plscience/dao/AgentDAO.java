package br.ufjf.pgcc.plscience.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.ufjf.pgcc.plscience.model.Agent;
import br.ufjf.pgcc.plscience.util.EncryptPasswordUtil;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;

public class AgentDAO {

    public static AgentDAO agentDAO;

    public static AgentDAO getInstance() {
        if (agentDAO == null) {
            agentDAO = new AgentDAO();
        }
        return agentDAO;
    }

    /**
     * Busca um Agent
     *
     * @param nome
     * @return
     */
    public Agent buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Agent As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Agent> agents = query.getResultList();
        if (agents != null && agents.size() > 0) {
            return agents.get(0);
        }

        return null;
    }

    /**
     * Busca todos os Agent
     *
     * @return
     */
    public List<Agent> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Agent As a");
        return query.getResultList();
    }

    /**
     * Remove uma agent
     *
     * @param agent
     */
    public void remover(Agent agent) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        agent = em.merge(agent);
        em.remove(agent);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Agent 
     *
     * @param agent 
     * @return
     */
    public Agent persistir(Agent agent) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            agent = em.merge(agent);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agent;
    }
    
    public Agent validityLogin(String login, String password){
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Agent As a where a.login = :login");
        query.setParameter("login", login.toUpperCase());

        List<Agent> agents = query.getResultList();
        if(!agents.isEmpty()){
            
            String passwordMD5 = EncryptPasswordUtil.md5(password);
            if(agents.get(0).getPassword().equals(passwordMD5)){
                Agent agent = (Agent) agents.get(0);
                return agent;
            }
        }   
        return null;
    }

}

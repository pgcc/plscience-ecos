package br.ufjf.pgcc.plscience.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.ufjf.pgcc.plscience.model.Agent;
import br.ufjf.pgcc.plscience.util.EncryptPasswordUtil;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;

/**
 *
 * @author tassio
 */
public class AgentDAO extends PersistenceUtil {

    public static AgentDAO agentDAO;

    public static AgentDAO getInstance() {
        if (agentDAO == null) {
            agentDAO = new AgentDAO();
        }
        return agentDAO;
    }

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

    public List<Agent> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Agent As a");
        return query.getResultList();
    }

    public void remover(Agent agent) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            agent = em.merge(agent);
            em.remove(agent);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public Agent persistir(Agent agent) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            agent = em.merge(agent);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return agent;
    }

    public Agent validityLogin(String login, String password) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Agent As a where a.login = :login");
        query.setParameter("login", login.toUpperCase());

        List<Agent> agents = query.getResultList();
        if (!agents.isEmpty()) {

            String passwordMD5 = EncryptPasswordUtil.md5(password);
            if (agents.get(0).getPassword().equals(passwordMD5)) {
                Agent agent = (Agent) agents.get(0);
                return agent;
            }
        }
        return null;
    }

}

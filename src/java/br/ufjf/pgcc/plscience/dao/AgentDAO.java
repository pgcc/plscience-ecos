package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.controller.UserLoginBean;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.ufjf.pgcc.plscience.model.Agent;
import br.ufjf.pgcc.plscience.util.EncryptPasswordUtil;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
    
    public List<Agent> buscarTodasMenosLogada() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
        HttpSession session = (HttpSession) externalContext.getSession(true);  
        UserLoginBean userLoginBean = (UserLoginBean) session.getAttribute("userLoginBean");
        
        int id = 0;
        if(userLoginBean.getAgentLog() != null) {
            id = userLoginBean.getAgentLog().getIdAgent();
        }
        
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Agent As a where a.idAgent != :id");
        query.setParameter("id", id);
        
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

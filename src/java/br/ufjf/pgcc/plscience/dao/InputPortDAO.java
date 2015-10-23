package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.InputPort;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class InputPortDAO extends PersistenceUtil{

    public static InputPortDAO portDAO;

    public static InputPortDAO getInstance() {
        if (portDAO == null) {
            portDAO = new InputPortDAO();
        }
        return portDAO;
    }

   public List<InputPort> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("SELECT ip FROM InputPort ip Where ip.wf = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }

    public List<InputPort> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from InputPort As a");
        return query.getResultList();
    }

    public void remover(InputPort idInputPort) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idInputPort = em.merge(idInputPort);
            em.remove(idInputPort);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } 
        closeEntityManager();
    }

    public InputPort persistir(InputPort port) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            port = em.merge(port);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } 
        closeEntityManager();
        return port;
    }

}

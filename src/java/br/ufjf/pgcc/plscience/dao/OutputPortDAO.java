package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.OutputPort;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class OutputPortDAO extends PersistenceUtil {

    public static OutputPortDAO portDAO;

    public static OutputPortDAO getInstance() {
        if (portDAO == null) {
            portDAO = new OutputPortDAO();
        }
        return portDAO;
    }

   public List<OutputPort> buscar(int idworkflow) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("SELECT op FROM OutputPort op  Where op.wf = :id");
        query.setParameter("id", idworkflow);
        return query.getResultList();
    }

    public List<OutputPort> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from OutputPort As a");
        return query.getResultList();
    }

    public void remover(OutputPort idOutputPort) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idOutputPort = em.merge(idOutputPort);
            em.remove(idOutputPort);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public OutputPort persistir(OutputPort port) {
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

package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Task;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class TaskDAO extends PersistenceUtil{

    public static TaskDAO taskDAO;

    public static TaskDAO getInstance() {
        if (taskDAO == null) {
            taskDAO = new TaskDAO();
        }
        return taskDAO;
    }

    public Task buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from Task as a where  upper(a.name)=:task");
        query.setParameter("task", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<Task> Task = query.getResultList();
        if (Task != null && Task.size() > 0) {
            return Task.get(0);
        }

        return null;
    }
    
        public Task buscarid(int id) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from Task as a where  a.idTask=:task");
        query.setParameter("task", id);

        @SuppressWarnings("unchecked")
        List<Task> Task = query.getResultList();
        if (Task != null && Task.size() > 0) {
            return Task.get(0);
        }

        return null;
    }

    public List<Task> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Task As a");
        return query.getResultList();
    }

    public void remover(Task idTask) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idTask = em.merge(idTask);
            em.remove(idTask);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public Task persistir(Task task) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            task = em.merge(task);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return task;
    }

}

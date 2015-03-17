/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class TaskDAO {

    public static TaskDAO taskDAO;

    public static TaskDAO getInstance() {
        if (taskDAO == null) {
            taskDAO = new TaskDAO();
        }
        return taskDAO;
    }

    /**
     * Busca uma Task especifica
     *
     * @param name
     * @return
     */
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

    /**
     * Busca todas as Tasks
     *
     * @return
     */
    public List<Task> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Task As a");
        return query.getResultList();
    }

    /**
     * Remove uma Task
     *
     * @param idTask
     */
    public void remover(Task idTask) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idTask = em.merge(idTask);
        em.remove(idTask);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Task
     *
     * @param task
     * @return
     */
    public Task persistir(Task task) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            task = em.merge(task);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return task;
    }

}

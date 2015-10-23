package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Entity;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class EntityDAO extends PersistenceUtil{

    public static EntityDAO entityDAO;

    public static EntityDAO getInstance() {
        if (entityDAO == null) {
            entityDAO = new EntityDAO();
        }
        return entityDAO;
    }

    public Entity buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Entity As a where a.name =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Entity> entitys = query.getResultList();
        if (entitys != null && entitys.size() > 0) {
            return entitys.get(0);
        }

        return null;
    }

    public Entity buscarPorID(Integer idEntity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select e from Entity As e where e.idEntity =:idEntity ");
        query.setParameter("nome", idEntity);

        List<Entity> entitys = query.getResultList();
        if (entitys != null && entitys.size() > 0) {
            return entitys.get(0);
        }

        return null;
    }

    public List<Entity> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Entity As a");
        return query.getResultList();
    }

    public void remover(Entity entity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            entity = em.merge(entity);
            em.remove(entity);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } 
        closeEntityManager();
    }

    public Entity persistir(Entity entity) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            entity = em.merge(entity);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return entity;
    }

}

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
public class ResearchGroupDAO extends PersistenceUtil {

    public static ResearchGroupDAO researchGroupDAO;

    public static ResearchGroupDAO getInstance() {
        if (researchGroupDAO == null) {
            researchGroupDAO = new ResearchGroupDAO();
        }
        return researchGroupDAO;
    }

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

    public List<ResearchGroup> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from ResearchGroup As a");
        return query.getResultList();
    }

    public void remover(ResearchGroup idResearchGroup) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idResearchGroup = em.merge(idResearchGroup);
            em.remove(idResearchGroup);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public ResearchGroup persistir(ResearchGroup researchGroup) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            researchGroup = em.merge(researchGroup);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return researchGroup;
    }

}

package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.WasDerivedFrom;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class WasDerivedFromDAO extends PersistenceUtil {

    public static WasDerivedFromDAO wasDerivedFromDAO;

    public static WasDerivedFromDAO getInstance() {
        if (wasDerivedFromDAO == null) {
            wasDerivedFromDAO = new WasDerivedFromDAO();
        }
        return wasDerivedFromDAO;
    }

    public List<WasDerivedFrom> buscar(int idworkflow, String type) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("SELECT DISTINCT wdf FROM WasDerivedFrom wdf WHERE wdf.derivedTo.idWorkflow = :id AND wdf.type= :stype");
        query.setParameter("id", idworkflow);
        query.setParameter("stype", type);
        return query.getResultList();
    }

    public List<WasDerivedFrom> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from WasDerivedFrom As a");
        return query.getResultList();
    }

    public void remover(WasDerivedFrom idWasDerivedFrom) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            idWasDerivedFrom = em.merge(idWasDerivedFrom);
            em.remove(idWasDerivedFrom);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
    }

    public WasDerivedFrom persistir(WasDerivedFrom wasDerivedFrom) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            wasDerivedFrom = em.merge(wasDerivedFrom);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        closeEntityManager();
        return wasDerivedFrom;
    }

}

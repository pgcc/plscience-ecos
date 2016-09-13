/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.FeaturesModel;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class FeaturesModelDAO extends GenericDAO {

    public static FeaturesModelDAO featuresModelDAO;

    public static FeaturesModelDAO getInstance() {
        if (featuresModelDAO == null) {
            featuresModelDAO = new FeaturesModelDAO();
        }
        return featuresModelDAO;
    }

    /**
     * Save a feature model on the E-SECO software product line repository
     *
     * @param fm
     */
    public void save(FeaturesModel fm) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(fm);
        getEntityManager().getTransaction().commit();
        finish();
    }

    /**
     * Update a feature model
     *
     * @param fm
     */
    public void update(FeaturesModel fm) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(fm);
        getEntityManager().getTransaction().commit();
        finish();
    }

    /**
     * get all features model on the E-SECO platform repository (SPL)
     *
     * @return
     */
    public List<FeaturesModel> getAll() {
        Query query = getEntityManager().createQuery("SELECT fm FROM FeaturesModel AS fm");
        List<FeaturesModel> featuresModelList = query.getResultList();
        finish();
        return featuresModelList;
    }

    /**
     * get a Features Model by Id
     *
     * @param id
     * @return
     */
    public FeaturesModel getFeaturesModelById(Long id) {

        Query query = getEntityManager().createQuery("SELECT fm FROM FeaturesModel AS fm WHERE fm.id = :id");
        query.setParameter("id", id);

        List<FeaturesModel> FeaturesModelList = query.getResultList();
        finish();
        if (FeaturesModelList != null && FeaturesModelList.size() > 0) {
            return FeaturesModelList.get(0);
        }
        return null;
    }
}

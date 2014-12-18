/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author vitorfs
 */
public class GenericDAO {

    private EntityManager entityManager = null;

    public GenericDAO() {

    }

    protected void createEntityManager() {
        entityManager = Persistence.createEntityManagerFactory("PLSciencePU").createEntityManager();
    }

    public EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void finalize() throws Throwable {
        if (this.entityManager != null && this.entityManager.isOpen()) {
            this.entityManager.close();
            this.entityManager = null;
        }
        super.finalize();
    }
}

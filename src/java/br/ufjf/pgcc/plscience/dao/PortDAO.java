/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Port;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassio
 */
public class PortDAO {

    public static PortDAO portDAO;

    public static PortDAO getInstance() {
        if (portDAO == null) {
            portDAO = new PortDAO();
        }
        return portDAO;
    }

    /**
     * Busca uma Port especifica
     *
     * @param name
     * @return
     */
    public Port buscar(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from Port as a where  upper(a.name)=:port");
        query.setParameter("port", name.toUpperCase());

        @SuppressWarnings("unchecked")
        List<Port> Port = query.getResultList();
        if (Port != null && Port.size() > 0) {
            return Port.get(0);
        }

        return null;
    }

    /**
     * Busca todas as Ports
     *
     * @return
     */
    public List<Port> buscarTodas() {
       EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Port As a");
        return query.getResultList();
    }

    /**
     * Remove uma Port
     *
     * @param idPort
     */
    public void remover(Port idPort) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        idPort = em.merge(idPort);
        em.remove(idPort);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Port
     *
     * @param port
     * @return
     */
    public Port persistir(Port port) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            port = em.merge(port);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
        }
        return port;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Product;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme Martins
 */
public class ProductDAO extends GenericDAO {
    
    public void save(Product product) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(product);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(Product product) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(product);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public List<Product> getAll() {
        Query query = getEntityManager().createQuery("SELECT p FROM Product AS p");
        List<Product> products = query.getResultList();
        finish();
        return products;
    }
    
    public Product getProductById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT p FROM Product AS p WHERE p.id = :id");
        query.setParameter("id", id);

        List<Product> products = query.getResultList();
        finish();
        if (products != null && products.size() > 0) {
            return products.get(0);
        }
        return null;
    }
    
    public Product getProductByName(String name) {
        
        Query query = getEntityManager().createQuery("SELECT p FROM Product AS p WHERE p.productName = :productName");
        query.setParameter("productName", name);

        List<Product> products = query.getResultList();
        finish();
        if (products != null && products.size() > 0) {
            return products.get(0);
        }
        return null;
    }
    
}

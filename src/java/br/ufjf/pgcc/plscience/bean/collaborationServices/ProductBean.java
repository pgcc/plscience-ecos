/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.ProductDAO;
import br.ufjf.pgcc.plscience.model.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "productBean")
@ViewScoped
public class ProductBean implements Serializable {
    
    private Product product = new Product();
    private List products = new ArrayList();
    
    public ProductBean() {
        products = new ProductDAO().getAll();
        product = new Product();
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the products
     */
    public List getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List products) {
        this.products = products;
    }


}

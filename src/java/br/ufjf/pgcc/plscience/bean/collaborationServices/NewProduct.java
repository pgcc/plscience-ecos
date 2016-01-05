/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.ProductDAO;
import br.ufjf.pgcc.plscience.model.Product;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean()
@ViewScoped
public class NewProduct implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Product product;

    public NewProduct() {
        this.product = new Product();
    }
    
    public void save() {    
        try { 
            getProduct().setId(null);
            new ProductDAO().save(getProduct()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Product saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
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
        
}

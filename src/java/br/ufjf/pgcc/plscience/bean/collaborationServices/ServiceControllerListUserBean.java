/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "serviceControllerListUserBean")
@SessionScoped
public class ServiceControllerListUserBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    //Serviços de Lista de Usuário
    private boolean listUserA = false;
    private boolean listUserB = true;
    
    /**
     * @return the listUserA
     */
    public boolean isListUserA() {
        return listUserA;
    }

    /**
     * @param listUserA the listUserA to set
     */
    public void setListUserA(boolean listUserA) {
        this.listUserA = listUserA;
    }

    /**
     * @return the listUserB
     */
    public boolean isListUserB() {
        return listUserB;
    }

    /**
     * @param listUserB the listUserB to set
     */
    public void setListUserB(boolean listUserB) {
        this.listUserB = listUserB;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CodeDAO;
import br.ufjf.pgcc.plscience.model.Code;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "myCodes")
@ViewScoped
public class MyCodes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public List<Code> getCodes() {  
        return new CodeDAO().getAll();
    }
}

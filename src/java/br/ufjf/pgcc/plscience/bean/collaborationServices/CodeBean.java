/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CodeDAO;
import br.ufjf.pgcc.plscience.model.Code;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "codeBean")
@ViewScoped
public class CodeBean implements Serializable {
    
    private Code code = new Code();
    private List codes = new ArrayList();
    
    public CodeBean() {
        codes = new CodeDAO().getAll();
        code = new Code();
    }

    /**
     * @return the code
     */
    public Code getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Code code) {
        this.code = code;
    }

    /**
     * @return the codes
     */
    public List getCodes() {
        return codes;
    }

    /**
     * @param codes the codes to set
     */
    public void setCodes(List codes) {
        this.codes = codes;
    }

}

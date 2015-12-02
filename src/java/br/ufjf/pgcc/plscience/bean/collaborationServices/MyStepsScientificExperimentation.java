/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.StepsScientificExperimentationDAO;
import br.ufjf.pgcc.plscience.model.StepsScientificExperimentation;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "myStepsScientificExperimentation")
@ViewScoped
public class MyStepsScientificExperimentation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public List<StepsScientificExperimentation> getStepsScientificExperimentation() {  
        return new StepsScientificExperimentationDAO().getAll();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.message;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author phillipe
 */
public class WarningMessage {    
     
    /**
     * This method prints a message that the components were not selected
     */
    public static void warnComponentsNotSelected() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please, select the desired components"));
    }   
    
}

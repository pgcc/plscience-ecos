/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.converter;

import br.ufjf.pgcc.plscience.model.Workflow;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Tassio
 */
@FacesConverter("workflowConverter")
@ManagedBean
public class WorkflowConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (Workflow) uic.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Workflow) {
            Workflow pergunta = (Workflow) value;
            if (pergunta != null && pergunta instanceof Workflow && pergunta.getIdWorkflow() != null) {
                uic.getAttributes().put( pergunta.getIdWorkflow().toString(), pergunta);
                return pergunta.getIdWorkflow().toString();
            }
        }
        return "";
    }
}
    

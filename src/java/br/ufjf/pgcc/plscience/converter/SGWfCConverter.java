/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.converter;

import br.ufjf.pgcc.plscience.model.SGWfC;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Tassio
 */
@FacesConverter("sgwfcConverter")
@ManagedBean
public class SGWfCConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (SGWfC) uic.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof SGWfC) {
            SGWfC pergunta = (SGWfC) value;
            if (pergunta != null && pergunta instanceof SGWfC && pergunta.getIdSGWfC() != null) {
                uic.getAttributes().put( pergunta.getIdSGWfC().toString(), pergunta);
                return pergunta.getIdSGWfC().toString();
            }
        }
        return "";
    }
}
    

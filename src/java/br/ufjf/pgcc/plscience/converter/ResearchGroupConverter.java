/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.converter;

import br.ufjf.pgcc.plscience.model.ResearchGroup;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Tassio
 */
@FacesConverter("researchGroupConverter")
@ManagedBean
public class ResearchGroupConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (ResearchGroup) uic.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof ResearchGroup) {
            ResearchGroup pergunta = (ResearchGroup) value;
            if (pergunta != null && pergunta instanceof ResearchGroup && pergunta.getIdResearchGroup() != null) {
                uic.getAttributes().put( pergunta.getIdResearchGroup().toString(), pergunta);
                return pergunta.getIdResearchGroup().toString();
            }
        }
        return "";
    }
}
    

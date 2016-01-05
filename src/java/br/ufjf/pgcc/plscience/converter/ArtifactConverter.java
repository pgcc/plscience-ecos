/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.converter;

import br.ufjf.pgcc.plscience.model.Artifact;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Guilherme Martins
 */
@FacesConverter("artifactConverter")
@ManagedBean
public class ArtifactConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (Artifact) uic.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Artifact) {
            Artifact pergunta = (Artifact) value;
            if (pergunta != null && pergunta instanceof Artifact && pergunta.getId() != null) {
                uic.getAttributes().put( pergunta.getId().toString(), pergunta);
                return pergunta.getId().toString();
            }
        }
        return "";
    }
}

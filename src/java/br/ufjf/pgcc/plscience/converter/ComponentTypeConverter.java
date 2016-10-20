/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.converter;

import br.ufjf.pgcc.plscience.model.ComponentType;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author phillipe
 */

@FacesConverter("componentTypeConverter")
@ManagedBean
public class ComponentTypeConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (ComponentType) uic.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof ComponentType) {
            ComponentType componentType = (ComponentType) value;
            if (componentType != null && componentType instanceof ComponentType && componentType.getId()!= null) {
                uic.getAttributes().put( componentType.getId().toString(), componentType);
                return componentType.getId().toString();
            }
        }
        return "";
    }
    
}

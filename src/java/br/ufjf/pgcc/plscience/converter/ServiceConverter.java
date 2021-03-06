/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.converter;

import br.ufjf.pgcc.plscience.model.Service;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * 
 * @author lesimoes
 */
@FacesConverter("serviceConverter")
@ManagedBean
public class ServiceConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (Service) uic.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Service) {
            Service service = (Service) value;
            if (service != null && service instanceof Service && service.getId() != null) {
                uic.getAttributes().put( service.getId().toString(), service);
                return service.getId().toString();
            }
        }
        return "";
    }

}

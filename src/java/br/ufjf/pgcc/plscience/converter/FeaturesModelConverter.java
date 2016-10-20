/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.converter;

import br.ufjf.pgcc.plscience.model.FeaturesModel;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author phillipe
 */

@FacesConverter("featuresModelConverter")
@ManagedBean
public class FeaturesModelConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (FeaturesModel) uic.getAttributes().get(value);
        }
        return null;    
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof FeaturesModel) {
            FeaturesModel featuresModel = (FeaturesModel) value;
            if (featuresModel != null && featuresModel instanceof FeaturesModel && featuresModel.getId()!= null) {
                uic.getAttributes().put( featuresModel.getId().toString(), featuresModel);
                return featuresModel.getId().toString();
            }
        }
        return "";

    }
    
}

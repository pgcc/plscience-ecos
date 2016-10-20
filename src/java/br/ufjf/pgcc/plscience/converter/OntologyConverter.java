/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.converter;

import br.ufjf.pgcc.plscience.model.Ontology;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author phillipe
 */

@FacesConverter("ontologyConverter")
@ManagedBean
public class OntologyConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (Ontology) uic.getAttributes().get(value);
        }
        return null;        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Ontology) {
            Ontology ontology = (Ontology) value;
            if (ontology != null && ontology instanceof Ontology && ontology.getId()!= null) {
                uic.getAttributes().put( ontology.getId().toString(), ontology);
                return ontology.getId().toString();
            }
        }
        return "";    
    }
    
}

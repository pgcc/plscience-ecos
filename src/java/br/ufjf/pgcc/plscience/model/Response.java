/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;


import br.ufjf.pgcc.plscience.ontology.OntologyController;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe padrão para respostas em XML
 *
 * @author Lenita
 */
@XmlRootElement()
public class Response {

    private String value = null;

    public Response() {
    }

    public Response(String value) {
        value = value.replace(OntologyController.URI, "provseo:");
        value = value.replace(OntologyController.PROVONE_URI, "provone:");
        value = value.replace(OntologyController.PROV_URI, "prov:");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

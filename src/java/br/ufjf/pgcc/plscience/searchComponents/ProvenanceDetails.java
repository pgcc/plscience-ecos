/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.mindswap.wsdl.WSDLOperation;
import org.mindswap.wsdl.WSDLParameter;

/**
 * This class is used to present the provenance details of a service or workflow
 * previously used in a experiment
 *
 * @author phillipe
 */
@ManagedBean(name = "serpd")
@ViewScoped
public class ProvenanceDetails {

    private ResultsPatternFormat resPatF;
    private List<WSDLOperation> serviceOperations;
    private List<String> used;
    private List<String> wasAssociatedWith;
    private List<String> wasInformedBy;
    private List<String> wasEndedBy;
    private List<String> actedBehalfOf;
    private List<String> evolutionTaskOntology;

    /**
     * It makes a semantic annotation to the found tasks
     *
     * @param wsdlOperation
     * @param details
     * @throws IOException
     * @throws FileNotFoundException
     * @throws URISyntaxException
     */
    public void semanticAnnotation(WSDLOperation wsdlOperation,ProvenanceDetails details) throws IOException, FileNotFoundException, URISyntaxException {
        ServiceManager sm = new ServiceManager();
        sm.OWLSGenerator(wsdlOperation,details);
    }

/**
 * It generates a String to Semantic Annotation with provenance Data
 * @param provenanceDetails
 * @return 
 */
    public String generatesString(ProvenanceDetails provenanceDetails) {
        String provenanceData = "";
        if (provenanceDetails != null) {
            for (String s : provenanceDetails.getUsed()) {
                provenanceData += s + " ";
            }
            for (String s : provenanceDetails.getWasInformedBy()) {
                provenanceData += s + " ";
            }
            for (String s : provenanceDetails.getWasAssociatedWith()) {
                provenanceData += s + " ";
            }
            for (String s : provenanceDetails.getActedBehalfOf()) {
                provenanceData += s + " ";
            }
            for (String s : provenanceDetails.getWasEndedBy()) {
                provenanceData += s + " ";
            }
        }
        return provenanceData;
    }

    /**
     * Returns a short name for a parameter
     *
     * @param parameter
     * @return
     */
    public String getShortNameParameter(WSDLParameter parameter) {
        String shortName = ServiceManager.getShortName(parameter);
        return shortName;
    }

    /**
     * Returns a short type for a parameter
     *
     * @param parameter
     * @return
     */
    public String getShortTypeParameter(WSDLParameter parameter) {
        ServiceManager sm = new ServiceManager();
        String shortType = sm.getShortType(parameter);
        return shortType;
    }

    /**
     * @return the used
     */
    public List<String> getUsed() {
        return used;
    }

    /**
     * @param used the used to set
     */
    public void setUsed(List<String> used) {
        this.used = used;
    }

    /**
     * @return the wasAssociatedWith
     */
    public List<String> getWasAssociatedWith() {
        return wasAssociatedWith;
    }

    /**
     * @param wasAssociatedWith the wasAssociatedWith to set
     */
    public void setWasAssociatedWith(List<String> wasAssociatedWith) {
        this.wasAssociatedWith = wasAssociatedWith;
    }

    /**
     * @return the wasInformedBy
     */
    public List<String> getWasInformedBy() {
        return wasInformedBy;
    }

    /**
     * @param wasInformedBy the wasInformedBy to set
     */
    public void setWasInformedBy(List<String> wasInformedBy) {
        this.wasInformedBy = wasInformedBy;
    }

    /**
     * @return the wasEndedBy
     */
    public List<String> getWasEndedBy() {
        return wasEndedBy;
    }

    /**
     * @param wasEndedBy the wasEndedBy to set
     */
    public void setWasEndedBy(List<String> wasEndedBy) {
        this.wasEndedBy = wasEndedBy;
    }

    /**
     * @return the actedBehalfOf
     */
    public List<String> getActedBehalfOf() {
        return actedBehalfOf;
    }

    /**
     * @param actedBehalfOf the actedBehalfOf to set
     */
    public void setActedBehalfOf(List<String> actedBehalfOf) {
        this.actedBehalfOf = actedBehalfOf;
    }

    /**
     * @return the serviceOperations
     */
    public List<WSDLOperation> getServiceOperations() {
        return serviceOperations;
    }

    /**
     * @param serviceOperations the serviceOperations to set
     */
    public void setServiceOperations(List<WSDLOperation> serviceOperations) {
        this.serviceOperations = serviceOperations;
    }

    /**
     * @return the evolutionTaskOntology
     */
    public List<String> getEvolutionTaskOntology() {
        return evolutionTaskOntology;
    }

    /**
     * @param evolutionTaskOntology the evolutionTaskOntology to set
     */
    public void setEvolutionTaskOntology(List<String> evolutionTaskOntology) {
        this.evolutionTaskOntology = evolutionTaskOntology;
    }

    /**
     * @return the resPatF
     */
    public ResultsPatternFormat getResPatF() {
        return resPatF;
    }

    /**
     * @param resPatF the resPatF to set
     */
    public void setResPatF(ResultsPatternFormat resPatF) {
        this.resPatF = resPatF;
    }
}

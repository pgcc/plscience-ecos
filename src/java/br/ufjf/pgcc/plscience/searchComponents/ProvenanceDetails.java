/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.mindswap.wsdl.WSDLOperation;
import org.mindswap.wsdl.WSDLParameter;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
     * @throws javax.xml.transform.TransformerException
     */
    public void semanticAnnotation(WSDLOperation wsdlOperation, ProvenanceDetails details) throws IOException, FileNotFoundException, URISyntaxException, TransformerException {
        ServiceManager sm = new ServiceManager();
        sm.OWLSGenerator(wsdlOperation, details);
        generatesXMLFileToServiceDescription(wsdlOperation, details);
    }

    /**
     * It generates a xml file to service description
     *
     * @param wsdlOperation
     * @param details
     */
    public void generatesXMLFileToServiceDescription(WSDLOperation wsdlOperation, ProvenanceDetails details) throws TransformerException {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("service");

            // staff elements
            Element serviceData = doc.createElement("serviceData");
            rootElement.appendChild(serviceData);
            
            // set attribute to staff element
            //Attr attr = doc.createAttribute("id");
            //attr.setValue("1");
            //serviceData.setAttributeNode(attr);
            // shorten way
            // staff.setAttribute("id", "1");
            // firstname elements
            String serviceName = null;
            String serviceDescription = null;
            String operationName;
            String serviceType = "atomic";
            String repositoryName = "E-SECO";//local service
            String operationDescription;
            String serviceOwner;
            String serviceReturnPrimeSin = "";
            String serviceReturnPrimeSem = "";
            String serviceReceptionPrimeSem = "";
            String serviceRepresentationPrimeSem = "";
            String serviceFunctionalRequirementPrimeSem = "";
            String serviceNonFunctionalRequirementPrimePra = "";
            String serviceArtifactPrimePra = "";
            String serviceDomainPrimePra = "";
            String servicePurposePrimePra = "";
            String serviceProviderPrimePra = "";
            String serviceRestrictionPrimePra = "";
            String serviceLicenseTypePrimePra = "";
            String serviceHardwareCPUPrimePra = "";
            String serviceHardwareOperationalSystemPrimePra = "";
            String serviceHardwareRAMPrimePra = "";
            String serviceHardwareROMPrimePra = "";
            String containsProvenanceData;
            String provenanceData;
            String inputs = "";
            String outputs = "";
            String dependencies = "";

            if (wsdlOperation.getInputs() != null) {
                Vector<WSDLParameter> ins = wsdlOperation.getInputs();
                for (int i = 0; i < ins.size(); i++) {
                    String inputName;
                    String inputType;

                    if (!(ins.get(i).getName().equals("")) && !(ins.get(i).getType().getLocalPart().equals(""))) {
                        inputName = ins.get(i).getName();
                        inputType = ins.get(i).getType().getLocalPart();
                        inputs = inputs + inputName + "," + inputType + "-";
                    }
                }
            }

            if (wsdlOperation.getOutputs() != null) {
                Vector<WSDLParameter> out = wsdlOperation.getOutputs();
                for (int i = 0; i < out.size(); i++) {
                    String outputName;
                    String outputType;

                    if (!(out.get(i).getName().equals("")) && !(out.get(i).getType().getLocalPart().equals(""))) {
                        outputName = out.get(i).getName();
                        outputType = out.get(i).getType().getLocalPart();
                        outputs = outputs + outputName + "," + outputType + "-";
                    }
                }
            }

            if (wsdlOperation.getName() != null && !wsdlOperation.getName().isEmpty()) {
                operationName = wsdlOperation.getName();
            } else {
                operationName = "service";
            }

            if (details.getResPatF() != null) {
                if (details.getResPatF().getName() == null) {
                    serviceName = "";
                } else {
                    serviceName = details.getResPatF().getName();
                }
                if (details.getResPatF().getDescription() == null) {
                    serviceDescription = "";
                } else {
                    serviceDescription = details.getResPatF().getDescription();
                }
            }

            if (details.getUsed() != null) {
                if (details.getUsed().size() > 0) {
                    containsProvenanceData = details.getUsed().get(0);
                    if (containsProvenanceData.contains(operationName)) {
                        provenanceData = details.generatesString(details);
                    } else {
                        provenanceData = "";
                    }
                } else {
                    provenanceData = "";
                }
            } else {
                provenanceData = "";
            }

            if (details.getResPatF() != null && details.getResPatF().getRepositoryName() != null
                    && details.getResPatF().getRepositoryName().length() > 3) {
                repositoryName = details.getResPatF().getRepositoryName();
            }

            if (details.getResPatF() != null && details.getResPatF().getDescription() != null
                    && details.getResPatF().getDescription().length() > 3) {
                operationDescription = details.getResPatF().getDescription();
                operationDescription = serviceName + " " + serviceDescription + " "
                        + operationName + " " + operationDescription + " "
                        + " " + provenanceData;

            } else {
                operationDescription = "";
            }

            if (details.getResPatF() != null && details.getResPatF().getOwner() != null) {
                serviceOwner = details.getResPatF().getOwner();
            } else {
                serviceOwner = "";
            }

            if (details.getResPatF() != null && details.getResPatF().getLicenseType() != null) {
                serviceLicenseTypePrimePra = details.getResPatF().getLicenseType();
            } else {
                serviceLicenseTypePrimePra = "";
            }

            if (details.getResPatF() != null && details.getResPatF().getReturnPrimeSin() != null) {
                serviceReturnPrimeSin = details.getResPatF().getReturnPrimeSin();
            }

            if (details.getResPatF() != null && details.getResPatF().getReturnPrimeSem() != null) {
                serviceReturnPrimeSem = details.getResPatF().getReturnPrimeSem();
            }

            if (details.getResPatF() != null && details.getResPatF().getReceptionPrimeSem() != null) {
                serviceReceptionPrimeSem = details.getResPatF().getReceptionPrimeSem();
            }

            if (details.getResPatF() != null && details.getResPatF().getFunctionalRequirementPrimeSem() != null) {
                serviceFunctionalRequirementPrimeSem = details.getResPatF().getFunctionalRequirementPrimeSem();
            }

            if (details.getResPatF() != null && details.getResPatF().getArtifactPrimePra() != null) {
                serviceArtifactPrimePra = details.getResPatF().getArtifactPrimePra();
            }

            if (details.getResPatF() != null && details.getResPatF().getDomainPrimePra() != null) {
                serviceDomainPrimePra = details.getResPatF().getDomainPrimePra();
            }

            if (details.getResPatF() != null && details.getResPatF().getPurposePrimePra() != null) {
                servicePurposePrimePra = details.getResPatF().getPurposePrimePra();
            }

            if (details.getResPatF() != null && details.getResPatF().getNonFunctionalRequirementPrimePra() != null) {
                serviceNonFunctionalRequirementPrimePra = details.getResPatF().getNonFunctionalRequirementPrimePra();
            }

            if (details.getResPatF() != null && details.getResPatF().getProviderPrimePra() != null) {
                serviceProviderPrimePra = details.getResPatF().getProviderPrimePra();
            }

            if (details.getResPatF() != null && details.getResPatF().getRestrictionPrimePra() != null) {
                serviceRestrictionPrimePra = details.getResPatF().getRestrictionPrimePra();
            }

            if (details.getResPatF() != null && details.getResPatF().getHardwareCPUPrimePra() != null) {
                serviceHardwareCPUPrimePra = details.getResPatF().getHardwareCPUPrimePra();
            }

            if (details.getResPatF() != null && details.getResPatF().getHardwareOperationalSystemPrimePra() != null) {
                serviceHardwareOperationalSystemPrimePra = details.getResPatF().getHardwareOperationalSystemPrimePra();
            }

            if (details.getResPatF() != null && details.getResPatF().getHardwareRAMPrimePra() != null) {
                serviceHardwareRAMPrimePra = details.getResPatF().getHardwareRAMPrimePra();
            }

            if (details.getResPatF() != null && details.getResPatF().getHardwareROMPrimePra() != null) {
                serviceHardwareROMPrimePra = details.getResPatF().getHardwareROMPrimePra();
            }

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(operationName));
            serviceData.appendChild(name);

            //type elements
            Element type = doc.createElement("type");
            type.appendChild(doc.createTextNode(serviceType));
            serviceData.appendChild(type);
            
            //inputs
            Element inputsElement = doc.createElement("inputs");
            inputsElement.appendChild(doc.createTextNode(inputs));
            serviceData.appendChild(inputsElement);
            
            //outputs
            Element outputsElement = doc.createElement("outputs");
            outputsElement.appendChild(doc.createTextNode(outputs));
            serviceData.appendChild(outputsElement);
            
            //dependencies
            Element depElement = doc.createElement("dependencies");
            depElement.appendChild(doc.createTextNode(dependencies));
            serviceData.appendChild(depElement);            

            // repository elements
            Element repository = doc.createElement("repository");
            repository.appendChild(doc.createTextNode(repositoryName));
            serviceData.appendChild(repository);

            // description elements
            Element description = doc.createElement("description");
            description.appendChild(doc.createTextNode(operationDescription));
            serviceData.appendChild(description);

            // owner elements
            Element owner = doc.createElement("owner");
            owner.appendChild(doc.createTextNode(serviceOwner));
            serviceData.appendChild(owner);

            //license elements
            Element license = doc.createElement("licenseType");
            license.appendChild(doc.createTextNode(serviceLicenseTypePrimePra));
            serviceData.appendChild(license);

            //primeElements
            //prime Syntatic
            //returnPrimeSyn
            Element returnPrimeSyn = doc.createElement("returnPrimeSyn");
            returnPrimeSyn.appendChild(doc.createTextNode(serviceReturnPrimeSin));
            serviceData.appendChild(returnPrimeSyn);

            //prime Semantic
            //returnPrimeSem
            Element returnPrimeSem = doc.createElement("returnPrimeSem");
            returnPrimeSem.appendChild(doc.createTextNode(serviceReturnPrimeSem));
            serviceData.appendChild(returnPrimeSem);

            //receptionPrimeSem
            Element receptionPrimeSem = doc.createElement("receptionPrimeSem");
            receptionPrimeSem.appendChild(doc.createTextNode(serviceReceptionPrimeSem));
            serviceData.appendChild(receptionPrimeSem);

            //representationPrimeSem
            Element representationPrimeSem = doc.createElement("representationPrimeSem");
            representationPrimeSem.appendChild(doc.createTextNode(serviceRepresentationPrimeSem));
            serviceData.appendChild(representationPrimeSem);

            //functionalRequirementPrimeSem
            Element functionalRequirementPrimeSem = doc.createElement("functionalRequirementPrimeSem");
            functionalRequirementPrimeSem.appendChild(doc.createTextNode(serviceFunctionalRequirementPrimeSem));
            serviceData.appendChild(functionalRequirementPrimeSem);

            //prime pragmatic
            //nonFunctionalRequirementPrimePra
            Element nonFunctionalRequirementPrimePra = doc.createElement("nonFunctionalRequirementPrimePra");
            nonFunctionalRequirementPrimePra.appendChild(doc.createTextNode(serviceNonFunctionalRequirementPrimePra));
            serviceData.appendChild(nonFunctionalRequirementPrimePra);

            //artifactPrimePra
            Element artifactPrimePra = doc.createElement("artifactPrimePra");
            artifactPrimePra.appendChild(doc.createTextNode(serviceArtifactPrimePra));
            serviceData.appendChild(artifactPrimePra);

            //domainPrimePra
            Element domainPrimePra = doc.createElement("domainPrimePra");
            domainPrimePra.appendChild(doc.createTextNode(serviceDomainPrimePra));
            serviceData.appendChild(domainPrimePra);

            //purposePrimePra
            Element purposePrimePra = doc.createElement("purposePrimePra");
            purposePrimePra.appendChild(doc.createTextNode(servicePurposePrimePra));
            serviceData.appendChild(purposePrimePra);

            //providerPrimePra
            Element providerPrimePra = doc.createElement("providerPrimePra");
            providerPrimePra.appendChild(doc.createTextNode(serviceProviderPrimePra));
            serviceData.appendChild(providerPrimePra);

            //restrictionPrimePra
            Element restrictionPrimePra = doc.createElement("restrictionPrimePra");
            restrictionPrimePra.appendChild(doc.createTextNode(serviceRestrictionPrimePra));
            serviceData.appendChild(restrictionPrimePra);

            //hardwareCPUPrimePra
            Element hardwareCPUPrimePra = doc.createElement("hardwareCPUPrimePra");
            hardwareCPUPrimePra.appendChild(doc.createTextNode(serviceHardwareCPUPrimePra));
            serviceData.appendChild(hardwareCPUPrimePra);

            //hardwareOperationalSystem
            Element hardwareOperationalSystem = doc.createElement("hardwareOperationalSystem");
            hardwareOperationalSystem.appendChild(doc.createTextNode(serviceHardwareOperationalSystemPrimePra));
            serviceData.appendChild(hardwareOperationalSystem);

            //hardwareRAMPrimePra
            Element hardwareRAMPrimePra = doc.createElement("hardwareRAMPrimePra");
            hardwareRAMPrimePra.appendChild(doc.createTextNode(serviceHardwareRAMPrimePra));
            serviceData.appendChild(hardwareRAMPrimePra);

            //hardwareROMPrimePra
            Element hardwareROMPrimePra = doc.createElement("hardwareROMPrimePra");
            hardwareROMPrimePra.appendChild(doc.createTextNode(serviceHardwareROMPrimePra));
            serviceData.appendChild(hardwareROMPrimePra);

            doc.appendChild(rootElement);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            String path = System.getProperty("user.home")+File.separatorChar+"VirtualRepository"+File.separatorChar+operationName+".xml";
            StreamResult result = new StreamResult(new File(path));
            //StreamResult result = new StreamResult(new File("/home/phillipe/Documentos/VirtualRepository/" + operationName + ".xml"));
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            //System.out.println("Service saved!");
        } catch (ParserConfigurationException pce) {
            System.out.printf("Error in generatesXMLFileToServiceDescription method - Class ProvenanceDetails");
            pce.printStackTrace();
        } catch (TransformerConfigurationException ex) {
            System.out.printf("Error in generatesXMLFileToServiceDescription method - Class ProvenanceDetails");
            Logger.getLogger(ProvenanceDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            System.out.printf("Error in generatesXMLFileToServiceDescription method - Class ProvenanceDetails");
            Logger.getLogger(ProvenanceDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * It generates a String to Semantic Annotation with provenance Data
     *
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.mindswap.owl.vocabulary.OWL;
import org.mindswap.owl.vocabulary.XSD;
import org.mindswap.utils.QNameProvider;
import org.mindswap.utils.URIUtils;
import org.mindswap.wsdl.WSDLConsts;
import org.mindswap.wsdl.WSDLOperation;
import org.mindswap.wsdl.WSDLParameter;
import org.mindswap.wsdl.WSDLService;
import org.mindswap.wsdl.WSDLTranslator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author phillipe
 */
@ManagedBean()
@ViewScoped
public class ServiceManager implements Serializable {

    private String repositoryURL = "/home/phillipe/Documentos/VirtualRepository";
    //private static String fileURL = "http://npd.hgu.mrc.ac.uk/soap/npd.wsdl";
    private static String fileURL = "http://localhost:8084/plscience-ecos/WsUserList?wsdl";
    private ServiceManagerData serviceInfo;
    private List<WSDLOperation> serviceOperations;
    private final QNameProvider qNames = new QNameProvider();

    //Vector<WSDLParameter> inputParameters;
    public List<WSDLOperation> generatesServiceInformation(String fileURL) {
        setFileURL(fileToURI(fileURL).replaceAll(" ", "%20"));
        WSDLService service;
        try {
            service = WSDLService.createService(URIUtils.createURI(getFileURL()));
            serviceOperations = service.getOperations();
        } catch (Exception ex) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serviceOperations;
    }

    /**
     * generates Service Information
     */
    public void generatesServiceInformation() {
        setFileURL(fileToURI(getFileURL()).replaceAll(" ", "%20"));
        WSDLService service;
        try {
            service = WSDLService.createService(URIUtils.createURI(getFileURL()));
            serviceOperations = service.getOperations();

        } catch (Exception ex) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * It converts a long parameter name in a short parameter name
     *
     * @param parameter a wsdl parameter
     * @return a short name
     */
    public static String getShortName(WSDLParameter parameter) {
        String shortName;
        shortName = URIUtils.getLocalName(parameter.getName());
        return shortName;
    }

    /**
     * It converts a long parameter type in a short parameter type
     *
     * @param parameter
     * @return
     */
    public String getShortType(WSDLParameter parameter) {
        String shortType;
        QName paramType = (parameter.getType() == null) ? new QName(WSDLConsts.xsdURI, "any") : parameter.getType();
        String wsdlType = paramType.getNamespaceURI() + "#" + paramType.getLocalPart();
        shortType = qNames.shortForm(wsdlType);
        return shortType;
    }

    /**
     * It generates an OWL-S file with the service name, the service
     * description, service operation name, service operation description and
     * provenance data
     *
     * @param wsdlOperation
     * @param provenanceDetails
     * @throws FileNotFoundException
     * @throws IOException
     * @throws URISyntaxException
     */
    public void OWLSGenerator(WSDLOperation wsdlOperation, ProvenanceDetails provenanceDetails) throws FileNotFoundException, IOException, URISyntaxException {
        String operationName = wsdlOperation.getName();
        String name = operationName.replaceAll(" ", "_");
        String finalDescription; //serv. description + operation description + provenance data
        String operationDescription;
        String provenanceData;
        String serviceName;
        String serviceDescription;
        String containsProvenanceData;

        if (provenanceDetails.getUsed() != null) {
            
            if(provenanceDetails.getUsed().size() > 0){
                containsProvenanceData = provenanceDetails.getUsed().get(0);
            if (containsProvenanceData.contains(operationName)) {
                provenanceData = provenanceDetails.generatesString(provenanceDetails);
            }else
                provenanceData = "";
            }else
                provenanceData = "";
        }else{
            provenanceData = "";
        }

        if (provenanceDetails.getResPatF().getName() == null) {
            serviceName = "";
        } else {
            serviceName = provenanceDetails.getResPatF().getName();
        }

        if (provenanceDetails.getResPatF().getDescription() == null) {
            serviceDescription = "";
        } else {
            serviceDescription = provenanceDetails.getResPatF().getDescription();
        }

        if (wsdlOperation.getDocumentation() == null) {
            operationDescription = "Generated from " + fileURL;
        } else {
            operationDescription = wsdlOperation.getDocumentation();
        }

        finalDescription = serviceName + " " + serviceDescription + " "
                + name + " " + operationDescription + " " + provenanceData;

        String logicalURI = "http://localhost:8084/plscience-ecos/" + name + ".owl";
        WSDLTranslator translator = new WSDLTranslator(wsdlOperation, logicalURI, name);
        translator.setServiceName(name);
        translator.setTextDescription(finalDescription);

        Vector<WSDLParameter> inpParameters = wsdlOperation.getInputs();
        Vector<WSDLParameter> outParameters = wsdlOperation.getOutputs();

        Set usedNames = new HashSet();
        if (!generateOWLsInputs(inpParameters, translator, wsdlOperation, usedNames)) {
            System.out.println("ERROR");
        }
        if (!generateOWLsOutputs(outParameters, translator, wsdlOperation, usedNames)) {
            System.out.println("ERROR");
        }

        String owlsDir = File.separatorChar + "home" + File.separatorChar + "phillipe" + File.separatorChar + "Documentos"
                + File.separatorChar + "VirtualRepository";
        if (repositoryURL != null) {
            owlsDir = repositoryURL;
            owlsDir = owlsDir.replaceAll("/", Matcher.quoteReplacement(File.separator));
            System.out.println("repository URL is not null");
        }

        String owls = owlsDir + File.separatorChar + operationName + ".owl";

        File owlsFile = new File(owls);
        if (owlsFile.exists()) {
            owlsFile.delete();
            try {
                owlsFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(owlsFile)) {
            translator.writeOWLS(fos);
        }
    }
    
    public void generateOWLSAndXMLFile(WSDLOperation wsdlOperation) throws IOException, FileNotFoundException, URISyntaxException, TransformerException, ParserConfigurationException{
        OWLSGenerator(wsdlOperation);
        generatesXMLFileToServiceDescription(wsdlOperation);
    }
    
    public void generatesXMLFileToServiceDescription(WSDLOperation wsdlOperation) throws ParserConfigurationException, TransformerException{
        
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("service");

            // staff elements
            Element serviceData = doc.createElement("serviceData");
            rootElement.appendChild(serviceData);

            String serviceName = "";
            String serviceDescription = "";
            String operationName;
            String serviceType = "atomic";
            String repositoryName = "E-SECO";//local service
            String operationDescription = "";
            String serviceOwner = "";
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

            StreamResult result = new StreamResult(new File("/home/phillipe/Documentos/VirtualRepository/" + operationName + ".xml"));
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
    }
            
        


    /**
     * It generates an OWL-S file
     *
     * @param wsdlOperation
     * @throws FileNotFoundException
     * @throws IOException
     * @throws URISyntaxException
     */
    public void OWLSGenerator(WSDLOperation wsdlOperation) throws FileNotFoundException, IOException, URISyntaxException {
        String serviceName = wsdlOperation.getName();
        String name = serviceName.replaceAll(" ", "_");
        String serviceDescription;

        if (wsdlOperation.getDocumentation() == null) {
            serviceDescription = "Generated from " + fileURL;
        } else {
            serviceDescription = wsdlOperation.getDocumentation();
        }

        String logicalURI = "http://localhost:8084/plscience-ecos/" + name + ".owl";
        WSDLTranslator translator = new WSDLTranslator(wsdlOperation, logicalURI, name);
        translator.setServiceName(name);
        translator.setTextDescription(serviceDescription);

        Vector<WSDLParameter> inpParameters = wsdlOperation.getInputs();
        Vector<WSDLParameter> outParameters = wsdlOperation.getOutputs();

        Set usedNames = new HashSet();
        if (!generateOWLsInputs(inpParameters, translator, wsdlOperation, usedNames)) {
            System.out.println("ERROR");
        }
        if (!generateOWLsOutputs(outParameters, translator, wsdlOperation, usedNames)) {
            System.out.println("ERROR");
        }

        String owlsDir = File.separatorChar + "home" + File.separatorChar + "phillipe" + File.separatorChar + "Documentos"
                + File.separatorChar + "VirtualRepository";
        if (repositoryURL != null) {
            owlsDir = repositoryURL;
            owlsDir = owlsDir.replaceAll("/", Matcher.quoteReplacement(File.separator));
            System.out.println("repository URL is not null");
        }

        String owls = owlsDir + File.separatorChar + serviceName + ".owl";

        File owlsFile = new File(owls);
        if (owlsFile.exists()) {
            owlsFile.delete();
            try {
                owlsFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(owlsFile)) {
            translator.writeOWLS(fos);
        }
    }

    /**
     * generates OWLs Outputs
     *
     * @param outputParams
     * @param translator
     * @param wsdlOperation
     * @param usedNames
     * @param qNames
     */
    private boolean generateOWLsOutputs(Vector<WSDLParameter> outputParams,
            WSDLTranslator translator,
            WSDLOperation wsdlOperation,
            Set usedNames) throws URISyntaxException {

        for (int i = 0; i < outputParams.size(); i++) {
            WSDLParameter param = wsdlOperation.getOutput(i);
            String paramName = getShortName(outputParams.get(i));
            String paramType = getShortType(outputParams.get(i));

            String prefix = paramName;
            for (int count = 1; usedNames.contains(paramName); count++) {
                paramName = prefix + count;
            }
            usedNames.add(paramName);
            URI paramTypeURI;
            try {
                paramType = qNames.longForm(paramType);
                paramTypeURI = new URI(paramType);
            } catch (Exception e) {
                return false;
            }
            translator.addOutput(param, paramName, paramTypeURI, null);
        }
        return true;
    }

    /**
     * generates OWL-s Inputs
     *
     * @param inputParams
     * @param translator
     * @param wsdlOperation
     * @param usedNames
     * @param qNames
     */
    private boolean generateOWLsInputs(Vector<WSDLParameter> inputParams,
            WSDLTranslator translator,
            WSDLOperation wsdlOperation,
            Set usedNames) throws URISyntaxException {

        for (int i = 0; i < inputParams.size(); i++) {
            WSDLParameter param = wsdlOperation.getInput(i);
            String paramName = getShortName(inputParams.get(i));
            String paramType = getShortType(inputParams.get(i));
            String prefix = paramName;
            for (int count = 1; usedNames.contains(paramName); count++) {
                paramName = prefix + count;
            }
            usedNames.add(paramName);
            URI paramTypeURI;
            try {
                paramType = qNames.longForm(paramType);
                paramTypeURI = new URI(paramType);
            } catch (Exception e) {
                return false;
            }
            translator.addInput(param, paramName, paramTypeURI, null);
        }
        return true;
    }

    /**
     * File To URI
     *
     * @param fileName
     * @return
     */
    private static String fileToURI(String fileName) {
        try {
            if ((!fileName.toLowerCase().contains("file:/")) && (!fileName.toLowerCase().contains("http:"))) {
                File file = new File(fileName);

                return file.toURI().toURL().toExternalForm();
            } else {
                return fileName;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @return the repositoryURL
     */
    public String getRepositoryURL() {
        return repositoryURL;
    }

    /**
     * @param aRepositoryURL the repositoryURL to set
     */
    public void setRepositoryURL(String repositoryURL) {
        this.repositoryURL = repositoryURL;
    }

    /**
     * @return the fileURL
     */
    public String getFileURL() {
        return fileURL;
    }

    /**
     * @param fileURL the fileURL to set
     */
    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    /**
     * @return the serviceInfo
     */
    public ServiceManagerData getServiceInfo() {
        return serviceInfo;
    }

    /**
     * @param serviceInfo the serviceInfo to set
     */
    public void setServiceInfo(ServiceManagerData serviceInfo) {
        this.serviceInfo = serviceInfo;
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
}

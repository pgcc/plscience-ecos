/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import java.io.File;
import java.io.IOException;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author phillipe
 */
@ManagedBean()
@ViewScoped
public class PrimeData {

    private String repositoryURL;
    private String fileName;
    private ResultsPatternFormat patternFormat;

    public PrimeData() throws IOException {
        repositoryURL = File.separatorChar + "home" + File.separatorChar + "phillipe" + File.separatorChar + "Documentos"
                + File.separatorChar + "VirtualRepository";
        fileName = "geneID";
        patternFormat = new ResultsPatternFormat();
    }

    public void saveFile() throws TransformerConfigurationException, TransformerException, ParserConfigurationException, SAXException, IOException {

        try {
            File inputFile = new File(repositoryURL + File.separatorChar + fileName + ".xml");
            DocumentBuilderFactory docFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder
                    = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
            Node service = doc.getFirstChild();
            Node serviceData = doc.getElementsByTagName("serviceData").item(0);

            // update attribute
            //NamedNodeMap attr = serviceData.getAttributes();
            // loop the supercar child node
            NodeList list = serviceData.getChildNodes();
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
//                    if ("type".equals(eElement.getNodeName())) {
//                        if ("atomic".equals(eElement.getTextContent())) {
//                            eElement.setTextContent("composed");
//                        }
//                    }
                    if ("licenseType".equals(eElement.getNodeName())) {
                        if (!patternFormat.getLicenseType().equals("")) {
                            eElement.setTextContent(patternFormat.getLicenseType());
                        }
                    }
                    if ("returnPrimeSyn".equals(eElement.getNodeName())) {
                        if (!patternFormat.getReturnPrimeSin().equals("")) {
                            eElement.setTextContent(patternFormat.getReturnPrimeSin());
                        }
                    }
                    if ("returnPrimeSem".equals(eElement.getNodeName())) {
                        if (!patternFormat.getReturnPrimeSin().equals("")) {
                            eElement.setTextContent(patternFormat.getReturnPrimeSem());
                        }
                    }
                    if ("receptionPrimeSem".equals(eElement.getNodeName())) {
                        if (!patternFormat.getReceptionPrimeSem().equals("")) {
                            eElement.setTextContent(patternFormat.getReceptionPrimeSem());
                        }
                    }
                    if ("representationPrimeSem".equals(eElement.getNodeName())) {
                        if (!patternFormat.getRepresentationPrimeSem().equals("")) {
                            eElement.setTextContent(patternFormat.getRepresentationPrimeSem());
                        }
                    }
                    if ("functionalRequirementPrimeSem".equals(eElement.getNodeName())) {
                        if (!patternFormat.getFunctionalRequirementPrimeSem().equals("")) {
                            eElement.setTextContent(patternFormat.getFunctionalRequirementPrimeSem());
                        }
                    }
                    if ("nonFunctionalRequirementPrimePra".equals(eElement.getNodeName())) {
                        if (!patternFormat.getNonFunctionalRequirementPrimePra().equals("")) {
                            eElement.setTextContent(patternFormat.getNonFunctionalRequirementPrimePra());
                        }
                    }
                    if ("artifactPrimePra".equals(eElement.getNodeName())) {
                        if (!patternFormat.getArtifactPrimePra().equals("")) {
                            eElement.setTextContent(patternFormat.getArtifactPrimePra());
                        }
                    }
                    if ("domainPrimePra".equals(eElement.getNodeName())) {
                        if (!patternFormat.getDomainPrimePra().equals("")) {
                            eElement.setTextContent(patternFormat.getDomainPrimePra());
                        }
                    }
                    if ("purposePrimePra".equals(eElement.getNodeName())) {
                        if (!patternFormat.getPurposePrimePra().equals("")) {
                            eElement.setTextContent(patternFormat.getPurposePrimePra());
                        }
                    }
                    if ("providerPrimePra".equals(eElement.getNodeName())) {
                        if (!patternFormat.getProviderPrimePra().equals("")) {
                            eElement.setTextContent(patternFormat.getProviderPrimePra());
                        }
                    }
                    if ("restrictionPrimePra".equals(eElement.getNodeName())) {
                        if (!patternFormat.getProviderPrimePra().equals("")) {
                            eElement.setTextContent(patternFormat.getProviderPrimePra());
                        }
                    }
                    if ("hardwareCPUPrimePra".equals(eElement.getNodeName())) {
                        if (!patternFormat.getHardwareCPUPrimePra().equals("")) {
                            eElement.setTextContent(patternFormat.getHardwareCPUPrimePra());
                        }
                    }
                    if ("hardwareOperationalSystem".equals(eElement.getNodeName())) {
                        if (!patternFormat.getHardwareOperationalSystemPrimePra().equals("")) {
                            eElement.setTextContent(patternFormat.getHardwareOperationalSystemPrimePra());
                        }
                    }
                    if ("hardwareRAMPrimePra".equals(eElement.getNodeName())) {
                        if (!patternFormat.getHardwareRAMPrimePra().equals("")) {
                            eElement.setTextContent(patternFormat.getHardwareRAMPrimePra());
                        }
                    }
                    
                    if ("hardwareROMPrimePra".equals(eElement.getNodeName())) {
                        if (!patternFormat.getHardwareROMPrimePra().equals("")) {
                            eElement.setTextContent(patternFormat.getHardwareROMPrimePra());
                        }
                    }                    

                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(repositoryURL + File.separatorChar + fileName + ".xml"));
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param aFileName the fileName to set
     */
    public void setFileName(String aFileName) {
        fileName = aFileName;
    }

    /**
     * @return the repositoryURL
     */
    public String getRepositoryURL() {
        return repositoryURL;
    }

    /**
     * @param repositoryURL the repositoryURL to set
     */
    public void setRepositoryURL(String repositoryURL) {
        this.repositoryURL = repositoryURL;
    }

    /**
     * @return the patternFormat
     */
    public ResultsPatternFormat getPatternFormat() {
        return patternFormat;
    }

    /**
     * @param patternFormat the patternFormat to set
     */
    public void setPatternFormat(ResultsPatternFormat patternFormat) {
        this.patternFormat = patternFormat;
    }

}

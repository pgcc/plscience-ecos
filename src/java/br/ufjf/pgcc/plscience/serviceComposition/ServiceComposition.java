/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

import br.ufjf.pgcc.plscience.interoperability.SimilarityCalculation1;
import br.ufjf.pgcc.plscience.message.InfoMessage;
import br.ufjf.pgcc.plscience.relevantServices.StopWordsRemoval;
import static br.ufjf.pgcc.plscience.serviceComposition.WordNetDictionary.loadDictionary;
import br.ufjf.pgcc.plscience.serviceComposition.metrics.CompositionMetrics;
import br.ufjf.pgcc.plscience.serviceComposition.test.TaskOutput;
import br.ufjf.pgcc.plscience.serviceComposition.test.TaskInput;
import br.ufjf.pgcc.plscience.serviceCompositionGraph.CompositionGraph;
import br.ufjf.pgcc.plscience.serviceCompositionGraph.DependsOfEdge;
import br.ufjf.pgcc.plscience.serviceCompositionGraph.GraphNode;
import br.ufjf.pgcc.plscience.serviceCompositionGraph.InteroperatesWithEdge;
import br.ufjf.pgcc.plscience.vo.ContextVO;
import br.ufjf.pgcc.plscience.vo.HardwareVO;
import br.ufjf.pgcc.plscience.vo.PragmaticVO;
import br.ufjf.pgcc.plscience.vo.RankingVO;
import br.ufjf.pgcc.plscience.vo.SemanticVO;
import br.ufjf.pgcc.plscience.vo.ServiceDescriptionVO;
import br.ufjf.pgcc.plscience.vo.SyntacticVO;
import edu.mit.jwi.IDictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FilenameUtils;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLIndividualList;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLType;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.Output;
import org.mindswap.owls.service.Service;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
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
public class ServiceComposition implements Serializable {

    private List<UploadedFile> servicesFile;
    private String requestInpParameterNameForm;
    private String requestInpParameterTypeForm;
    private String requestOutParameterNameForm;
    private String requestOutParameterTypeForm;
    private String keywords;
    private ServiceRequest serviceRequest;
    private List<TasksCompositionData> tasksCompositionData;
    private List<ServiceFromVR> servicesFromVR;
    private List<ServiceDescriptionVO> servicesPrime;
    private Integer substituteServices;
    private final HashMap<ServiceDescriptionVO, ServiceFromVR> servicesHashMap = new HashMap<>();

    public ServiceComposition() throws IOException {
        servicesFile = new ArrayList<>();
        serviceRequest = new ServiceRequest();
        servicesFromVR = new ArrayList<>();
        servicesPrime = new ArrayList<>();
        substituteServices = 0;
    }

    /**
     * Search all semantic relevant services
     *
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     */
    public void searchRelevantServices() throws IOException, URISyntaxException {
        servicesFromVR = new ArrayList<>();
        servicesPrime = new ArrayList<>();
        addParametersToServiceRequest();
        readFilesToSearchTerms();
        listRelevantServices();
        //generateGraph();
        //sampleRelevantServicesList();// apenas modelo de lista
    }

    /**
     * Files Handler - Services OWL-S
     *
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        FacesMessage message = new FacesMessage("Files Succesful Uploaded!");
        FacesContext.getCurrentInstance().addMessage(null, message);
        if (event.getFile() != null) {
            servicesFile.add(event.getFile());

            Path folder = Paths.get("/home/phillipe/Documentos/VirtualRepository");
            String filename = FilenameUtils.getBaseName(event.getFile().getFileName());
            //String extension = FilenameUtils.getExtension(ue.getFileName());
            String extension = "owl";
            Path file = Files.createTempFile(folder, filename, "." + extension);

            try (InputStream input = event.getFile().getInputstream()) {
                Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
            }

        }
        //testUpload();
    }

    /**
     * It just tests if the files was uploaded
     */
    public void testUpload() {
        System.out.println("Total de Arquivos: " + servicesFile.size());
        for (UploadedFile ue : servicesFile) {
            System.out.println("Service name: " + ue.getFileName());
            System.out.println("Service content: " + ue.getContentType());
        }
    }

    /**
     * it generates and plots a graph for composed services
     *
     * @param taskName
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    public void generateGraph(String taskName) throws SAXException, IOException {
        System.out.println("Initializing graph for the service " + taskName);
        String script;
        //script = linkuriousSampleScriptGenerator();
        script = linkuriousScriptGenerator(taskName);
        if (!script.equals("")) {
            RequestContext.getCurrentInstance().execute(script);
        }
    }

    public ServiceFromVR findServiceFromVR(String taskName) {
        for (ServiceFromVR sfvr : servicesFromVR) {
            if (sfvr.getName().contains(taskName)) {
                return sfvr;
            }
        }
        return null;
    }

    /**
     * it returns a list of services atomics (dependencies) to a composed
     * service
     *
     * @param searched
     * @return
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    public List<ServiceFromVR> dependsOf(ServiceFromVR searched) throws SAXException, IOException {
        List<ServiceFromVR> listDependencies = new ArrayList<>();
        if (searched.getType().contains("comp")) {
            System.out.println(searched.getName() + " is composed");

            File fOwlFile = new File("/home/phillipe/Documentos/VirtualRepository/"
                    + searched.getName() + ".owl");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            try {
                docBuilder = dbFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(fOwlFile);
                doc.getDocumentElement().normalize();
                Node owlOntology = doc.getElementsByTagName("owl:Ontology").item(0);
                NodeList children = owlOntology.getChildNodes();

                for (int temp = 0; temp < children.getLength(); temp++) {
                    Node nNode = children.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        if ("owl:imports".equals(eElement.getNodeName())) {
                            String attributeResource = eElement.getAttribute("rdf:resource");
                            //System.out.println("resource: "+attributeResource);
                            String[] parts = attributeResource.split("/");
                            Integer tam = parts.length;
                            if (tam > 0) {
                                String serviceName = parts[tam - 1];
                                serviceName = serviceName.replace(".owl", "");
                                System.out.println("ServiceName: " + serviceName);
                                ServiceFromVR sfvr = findServiceFromVR(serviceName);
                                if (sfvr != null) {
                                    listDependencies.add(sfvr);
                                }
                            }
                        }
                    }
                }

            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ServiceComposition.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listDependencies;
    }

    /**
     * it returns a list of services that interoperates with a searched service
     *
     * @param searched
     * @return
     */
    public List<ServiceFromVR> interoperatesWith(ServiceFromVR searched) {
        List<ServiceFromVR> list = new ArrayList();
        boolean interoperates;
        for (ServiceFromVR s : servicesFromVR) {
            interoperates = true;
            if (searched.getName().equals(s.getName())) {
                interoperates = false;
            }
            Integer inpSize = s.getInputs().size();
            Integer outSize = s.getOutputs().size();
            if (searched.getInputs().size() == s.getInputs().size()) {
                if (searched.getOutputs().size() == s.getOutputs().size()) {
                    for (int i = 0; i < inpSize; i++) {
                        if (!searched.getInputsType().get(i).equals(s.getInputsType().get(i))) {
                            interoperates = false;
                            break;
                        }
                    }
                    for (int i = 0; i < outSize; i++) {
                        if (!searched.getOutputsType().get(i).equals(s.getOutputsType().get(i))) {
                            interoperates = false;
                            break;
                        }
                    }
                } else {
                    interoperates = false;
                }
            } else {
                interoperates = false;
            }
            if (interoperates) {
                list.add(s);
            }
        }
        return list;
    }

    /**
     * It returns the node if the graph contains the service
     *
     * @param g a composition graph
     * @param s a service from the repository
     * @return
     */
    public GraphNode graphContainsService(CompositionGraph g, ServiceFromVR s) {
        if (s == null) {
            return null;
        }
        if (s.getName() == null) {
            return null;
        }
        if (s.getName().equals("")) {
            return null;
        }
        String serviceName = s.getName();
        if (g.getServicesNodes() == null) {
            return null;
        }
        for (int i = 0; i < g.getServicesNodes().size(); i++) {
            if (g.getServicesNodes().get(i).getServiceName().contains(serviceName)) {
                return g.getServicesNodes().get(i);
            }
        }
        return null;
    }

    /**
     * It returns if a graph contains an edge
     *
     * @param serviceName1
     * @param serviceName2
     * @param edgesList list of edges
     * @param directed verify if the graph is directed
     * @return
     */
    public boolean graphContainsEdge(String serviceName1, String serviceName2,
            List<InteroperatesWithEdge> edgesList, boolean directed) {
        if (edgesList == null || edgesList.isEmpty()) {
            return false;
        }
        for (int i = 0; i < edgesList.size(); i++) {
            if (edgesList.get(i) != null && edgesList.get(i).getSource() != null
                    && edgesList.get(i).getTarget() != null) {
                if (edgesList.get(i).getSource().contains(serviceName1)) {
                    if (edgesList.get(i).getTarget().contains(serviceName2)) {
                        return true;
                    }
                }
                if (!directed) {
                    if (edgesList.get(i).getSource().contains(serviceName2)) {
                        if (edgesList.get(i).getTarget().contains(serviceName1)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * search a service in the graph
     *
     * @param cg
     * @param serviceName
     * @return
     */
    public GraphNode searchServiceGraph(CompositionGraph cg, String serviceName) {
        for (GraphNode n : cg.getServicesNodes()) {
            if (n.getServiceName().contains(serviceName)) {
                return n;
            }
        }
        return null;
    }

    /**
     * sets a graph node with ServiceFromVR parameters
     *
     * @param s
     * @return
     */
    public GraphNode addDataToGraphNode(ServiceFromVR s) {

        GraphNode n = new GraphNode();
        if (s != null) {
            if (s.getName() != null) {
                n.setServiceName(s.getName());
            }
            if (s.getRepositoryName() != null) {
                n.setRepositoryName(s.getRepositoryName());
            }
            if (s.getOwner() != null) {
                n.setOwner(s.getOwner());
            }
            if (s.getType() != null) {
                n.setType(s.getType());
            }
            n.setId(s.getName());
            n.setLabel(s.getName());
            if ((n.getRepositoryName() != null && !n.getRepositoryName().equals(""))
                    && (n.getType() != null && !n.getType().equals(""))) {
                n.setColor(n.getColor(n.getRepositoryName(), n.getType()));
            }
        }
        return n;
    }

    /**
     * search a serviceFromVR in the graph, return a node or null
     *
     * @param service
     * @param servicesNodes
     * @return
     */
    public GraphNode searchServiceFromVRInTheGraph(ServiceFromVR service, List<GraphNode> servicesNodes) {
        if (servicesNodes != null && servicesNodes.size() > 0) {
            int numberOfNodes = servicesNodes.size();
            for (int i = 0; i < numberOfNodes; i++) {
                GraphNode g = servicesNodes.get(i);
                if (g.getServiceName().equals(service.getName())) {
                    return g;
                }
            }
        }
        return null;
    }

    /**
     * it generates a javascript code to the linkurious library
     *
     * @param taskName
     * @return
     */
    public String linkuriousScriptGenerator(String taskName) throws SAXException, IOException {

        String script = "";
        CompositionGraph graph = new CompositionGraph();
        List<GraphNode> servicesNodes = new ArrayList<>();
        List<InteroperatesWithEdge> interoperatesWithEdgesList = new ArrayList<>();
        List<DependsOfEdge> dependsOfEdgesList = new ArrayList<>();
        List<ServiceFromVR> interopServicesList;
        List<ServiceFromVR> depServicesList = new ArrayList<>();

        ServiceFromVR sfvr = findServiceFromVR(taskName);
        if (sfvr != null) {
            GraphNode gNode = new GraphNode();
            gNode.setServiceName(taskName);
            if (sfvr.getRepositoryName() != null) {
                gNode.setRepositoryName(sfvr.getRepositoryName());
            }
            if (sfvr.getType() != null) {
                gNode.setType(sfvr.getType());
            }
            if (sfvr.getOwner() != null) {
                gNode.setOwner(sfvr.getOwner());
            }
            gNode.setColor(gNode.getColor(gNode.getRepositoryName(), gNode.getType()));
            gNode.setId(sfvr.getName());
            gNode.setLabel(sfvr.getName());
            gNode.setInteroperatesWithNodeList(new ArrayList());
            gNode.setDependsOfNodeList(new ArrayList<>());
            servicesNodes.add(gNode);

            interopServicesList = interoperatesWith(sfvr);
            System.out.println(sfvr.getName() + " interoperates with : " + interopServicesList.size() + " services");

            if (interopServicesList.size() > 0) {
                for (ServiceFromVR s : interopServicesList) {
                    InteroperatesWithEdge edgeInteroperation = new InteroperatesWithEdge();
                    GraphNode n = new GraphNode();
                    n.setServiceName(s.getName());
                    if (s.getRepositoryName() != null) {
                        n.setRepositoryName(s.getRepositoryName());
                    }
                    if (s.getOwner() != null) {
                        n.setOwner(s.getOwner());
                    }
                    if (s.getType() != null) {
                        n.setType(s.getType());
                    }
                    n.setId(s.getName());
                    n.setLabel(s.getName());
                    if ((n.getRepositoryName() != null && !n.getRepositoryName().equals(""))
                            && (n.getType() != null && !n.getType().equals(""))) {
                        n.setColor(n.getColor(n.getRepositoryName(), n.getType()));
                    }

                    //adding nodes and edges that interoperates with secondary services
                    List<ServiceFromVR> interopServicesListSecondaryNodes;
                    interopServicesListSecondaryNodes = interoperatesWith(s);
                    if (interopServicesListSecondaryNodes.size() > 0) {
                        n.setInteroperatesWithNodeList(interopServicesListSecondaryNodes);
                    }

                    //adding this node to the graph
                    servicesNodes.add(n);
                    //adding nodes that interoperates with gNode to the services Nodes
                    servicesNodes.get(0).getInteroperatesWithNodeList().add(s);
                    edgeInteroperation.getInteroperatesWith().setDirected(false);
                    edgeInteroperation.getInteroperatesWith().setFrom(gNode);
                    edgeInteroperation.getInteroperatesWith().setTo(n);
                    edgeInteroperation.setSource(gNode.getServiceName());
                    edgeInteroperation.setTarget(n.getServiceName());

                    //adding neighbors
                    if (!gNode.getNeighbors().contains(n)) {
                        gNode.getNeighbors().add(n);
                    }
                    if (!n.getNeighbors().contains(gNode)) {
                        n.getNeighbors().add(gNode);
                    }

                    interoperatesWithEdgesList.add(edgeInteroperation);
                }

                //adding primary dependencies (selected service only)
                if (gNode.getType().toLowerCase().contains("comp")) {
                    depServicesList = dependsOf(sfvr);
                }

                if (depServicesList.size() > 0) {

                    //setting fan-out metric (number of services that gNode uses)
                    gNode.setFanOut(depServicesList.size());

                    for (ServiceFromVR s : depServicesList) {
                        DependsOfEdge edgeDependency = new DependsOfEdge();
                        GraphNode n = new GraphNode();
                        boolean serviceExistsInTheGraph = false;
                        for (GraphNode gn : servicesNodes) {
                            if (gn.getServiceName().equals(s.getName())) {
                                serviceExistsInTheGraph = true;
                                n = gn;
                            }
                        }
                        if (!serviceExistsInTheGraph) {
                            n = addDataToGraphNode(s);
                            //System.out.println("Adding the service " + n.getServiceName() + " as a dependency");
                            servicesNodes.add(n);
                        }

                        n.setFanIn(n.getFanIn() + 1);
                        servicesNodes.get(0).getDependsOfNodeList().add(s);
                        edgeDependency.getDependsOf().setDirected(true);
                        edgeDependency.getDependsOf().setFrom(servicesNodes.get(0));
                        edgeDependency.getDependsOf().setTo(n);
                        edgeDependency.setSource(gNode.getServiceName());
                        edgeDependency.setTarget(n.getServiceName());

                        //adding neighbors
                        if (!servicesNodes.get(0).getNeighbors().contains(n)) {
                            servicesNodes.get(0).getNeighbors().add(n);
                        }
                        
                        if (!n.getNeighbors().contains(servicesNodes.get(0))) {
                            n.getNeighbors().add(servicesNodes.get(0));
                        }
                        dependsOfEdgesList.add(edgeDependency);
                    }
                }

                //adding secondary Dependencies (another composed services)
                if (interopServicesList.size() > 0) {
                    for (ServiceFromVR interopService : interopServicesList) {
                        /*verify if a services that interoperates
                        with the selected service is composed
                         */

                        //if the service is composed, add the edges
                        if (interopService.getType().toLowerCase().contains("comp")) {
                            //get the composedNode in the graph
                            GraphNode composedN = searchServiceFromVRInTheGraph(interopService, servicesNodes);

                            /*
                            if(composedN != null)
                                System.out.println("compN name: "+composedN.getServiceName());
                             */
                            List<ServiceFromVR> secondaryDependencies = new ArrayList<>();
                            if (!depServicesList.contains(interopService)) {
                                secondaryDependencies = dependsOf(interopService);
                                int numberOfDep = secondaryDependencies.size();
//                                System.out.println("Number Of dep to the service "+interopService.getName()+":"
//                                        + " "+numberOfDep);
                                if (numberOfDep > 0) {

                                    //setting fan-out metric
                                    composedN.setFanOut(composedN.getFanOut() + numberOfDep);
                                    for (int i = 0; i < numberOfDep; i++) {
                                        ServiceFromVR serviceD = secondaryDependencies.get(i);
                                        GraphNode dep = searchServiceFromVRInTheGraph(serviceD, servicesNodes);
                                        if (dep == null) {
                                            //node doesn't exists
                                            //create node
                                            dep = new GraphNode();
                                            dep.setServiceName(serviceD.getName());
                                            if (serviceD.getRepositoryName() != null) {
                                                dep.setRepositoryName(serviceD.getRepositoryName());
                                            }
                                            if (serviceD.getType() != null) {
                                                dep.setType(serviceD.getType());
                                            }
                                            if (serviceD.getOwner() != null) {
                                                dep.setOwner(serviceD.getOwner());
                                            }
                                            dep.setColor(dep.getColor(dep.getRepositoryName(), dep.getType()));
                                            dep.setId(serviceD.getName());
                                            dep.setLabel(serviceD.getName());
                                            dep.setInteroperatesWithNodeList(new ArrayList());
                                            dep.setDependsOfNodeList(new ArrayList<>());
                                            servicesNodes.add(dep);
                                        }

                                        dep.setFanIn(dep.getFanIn() + 1);

                                        //creating dependencies edges
                                        DependsOfEdge edgeDependency = new DependsOfEdge();
                                        edgeDependency.getDependsOf().setDirected(true);
                                        edgeDependency.getDependsOf().setFrom(composedN);
                                        edgeDependency.getDependsOf().setTo(dep);
                                        edgeDependency.setSource(interopService.getName());
                                        edgeDependency.setTarget(secondaryDependencies.get(i).getName());

                                        //adding neighbors
                                        if (!composedN.getNeighbors().contains(dep)) {
                                            composedN.getNeighbors().add(dep);
                                        }
                                        if (!dep.getNeighbors().contains(composedN)) {
                                            dep.getNeighbors().add(composedN);
                                        }

                                        dependsOfEdgesList.add(edgeDependency);
                                    }
                                }
                            }
                        }
                    }
                }

                graph.setServicesNodes(servicesNodes);
                graph.setDependsOfList(dependsOfEdgesList);

                //verify the existence of all interoperability edges (not considering the first node)
                List<GraphNode> nodes = graph.getServicesNodes();
                int interopSize = interoperatesWithEdgesList.size();
                for (int i = 1; i < interopSize; i++) {
                    GraphNode node = nodes.get(i);
                    List<ServiceFromVR> interopServices = node.getInteroperatesWithNodeList();
                    for (int j = 0; j < interopServices.size(); j++) {
                        ServiceFromVR s = interopServices.get(j);
                        if (!graphContainsEdge(node.getServiceName(), s.getName(), interoperatesWithEdgesList, false)) {
                            GraphNode gn = searchServiceGraph(graph, s.getName());
                            InteroperatesWithEdge edge = new InteroperatesWithEdge();
                            edge.getInteroperatesWith().setDirected(false);
                            edge.getInteroperatesWith().setFrom(node);
                            edge.getInteroperatesWith().setTo(gn);
                            edge.setSource(node.getServiceName());
                            edge.setTarget(s.getName());

                            //adding neighbors
                            if (!node.getNeighbors().contains(gn)) {
                                node.getNeighbors().add(gn);
                            }
                            if (!gn.getNeighbors().contains(node)) {
                                gn.getNeighbors().add(node);
                            }

                            interoperatesWithEdgesList.add(edge);
                        }
                    }
                }
                graph.setInteroperatesWithList(interoperatesWithEdgesList);

                int size = graph.getServicesNodes().size();
                System.out.println("Number of nodes: " + size);
                int i = 0;
                
                //calculate closeness metric for all nodes
                CompositionMetrics.closenessCentrality(graph);
                
                //calculate betweneess metric for all nodes
                CompositionMetrics.betweennessCentrality(graph);
                
                CompositionMetrics.SetEdgeSizeValue(graph);
                
//                for(GraphNode node:graph.getServicesNodes()){
//                    System.out.println("Service name: "+node.getServiceName());
//                    System.out.println("Closeness value: "+node.getClosenessValue());
//                }

                //creating the script
                script = "var s,"
                        + "g = {nodes: [],edges: []};\n"
                        + "g.nodes.push(\n";

                for (GraphNode n : graph.getServicesNodes()) {
                    script += "{";
                    script += "      id: '" + n.getId() + "',\n";
                    script += "      label: '" + n.getLabel() + "',\n";
                    script += "      x: Math.random(),\n"
                            + "      y: Math.random(),\n";
                    script += "      size: " + n.getSize() + ",\n";
                    script += "      color: '" + n.getColor() + "',\n";
                    script += "      data: {\n";
                    script += "        name: '" + n.getServiceName() + "',\n";
                    script += "        value: '" + n.getType() + "',\n";
                    script += "        type: '" + n.getType() + "',\n";
                    script += "        fanin: '" + n.getFanIn() + "',\n";
                    script += "        fanout: '" + n.getFanOut() + "',\n";
                    script += "        closeness: '" + n.getClosenessValue()+ "',\n";
                    script += "        betweenness: '" + n.getBetweennessValue()+ "',\n";
                    script += "        repository: '" + n.getRepositoryName() + "',\n";
                    //this condition presents the possible substitute services for the current service
                    if (n.getInteroperatesWithNodeList() != null && n.getInteroperatesWithNodeList().size() > 0) {
                        int intoList = n.getInteroperatesWithNodeList().size();
                        for (int count = 0; count < intoList; count++) {
                            String serviceName = n.getInteroperatesWithNodeList().get(count).getName();
                            int soma = count + 1;
                            script += "        sub" + soma + ": '" + serviceName + "',\n";
                            substituteServices = count + 1;
                        }
                    }
                    script += "        owner: '" + n.getOwner() + "'\n";
                    script += "      }\n";
                    i++;
                    if (i == size) {
                        script += "}\n";
                    } else {
                        script += "},\n";
                    }
                }
                script += ");\n";
                script += "g.edges.push(";
                int edgeId = 0;
                int numberOfInteropEdges = graph.getInteroperatesWithList().size();
                int numberOfDependEdges = graph.getDependsOfList().size();
                for (InteroperatesWithEdge edge : graph.getInteroperatesWithList()) {
                    script += "{\n";
                    script += "      id: 'e" + edgeId + "',\n";
                    script += "      label: '" + edge.getLabel() + "',\n";
                    script += "      source: '" + edge.getSource() + "',\n";
                    script += "      target: '" + edge.getTarget() + "',\n";
                    script += "      color: '" + edge.getColor() + "',\n";
                    script += "      hover_color: '" + edge.getHoverColor() + "',\n";
                    script += "      type: '" + edge.getType() + "',\n";
                    script += "      size: " + edge.getSize() + "\n";
                    edgeId++;
                    if (edgeId == numberOfInteropEdges
                            && numberOfDependEdges == 0) {
                        script += "}\n";
                    } else {
                        script += "},\n";
                    }
                }

                int positionOfDepEdge = 0;
                for (DependsOfEdge edge : graph.getDependsOfList()) {
                    script += "{\n";
                    script += "      id: 'e" + edgeId + "',\n";
                    script += "      label: '" + edge.getLabel() + "',\n";
                    script += "      source: '" + edge.getSource() + "',\n";
                    script += "      target: '" + edge.getTarget() + "',\n";
                    script += "      color: '" + edge.getColor() + "',\n";
                    script += "      hover_color: '" + edge.getHoverColor() + "',\n";
                    script += "      type: '" + edge.getType() + "',\n";
                    script += "      size: " + edge.getSize() + "\n";
                    edgeId++;
                    positionOfDepEdge++;
                    if (positionOfDepEdge == numberOfDependEdges) {
                        script += "}\n";
                    } else {
                        script += "},\n";
                    }
                }

                script += ");\n";
                script += renderizeScript();
            } else {
                InfoMessage.graphNotGenerated();
            }
        }
        return script;
    }

    /**
     * Generates a Sample for a Linkurious Script
     *
     * @return
     */
    public String linkuriousSampleScriptGenerator() {
        String script = "var s,"
                + "    g = {nodes: [],edges: []};"
                + "g.nodes.push({"
                + "      id: 'w1',"
                + "      label: 'tokenToString',"
                + "      x: Math.random(),"
                + "      y: Math.random(),"
                + "      size: 20,"
                + "      color: '#0F0',"
                + "      data: {"
                + "        name: 'tokenToString',"
                + "        value: 'atomic'"
                + "      }"
                + "},"
                + "{"
                + "      id: 'w2',"
                + "      label: 'geneID',"
                + "      x: Math.random(),"
                + "      y: Math.random(),"
                + "      size: 20,"
                + "      color: '#000',"
                + "      data: {"
                + "        name: 'geneID',"
                + "        value: 'atomic'"
                + "      }      "
                + "    },"
                + "	{"
                + "      id: 'w3',"
                + "      label: 'process',"
                + "      x: Math.random(),"
                + "      y: Math.random(),"
                + "      size: 20,"
                + "      color: '#0F0',"
                + "      data: {"
                + "        name: 'process',"
                + "        value: 'atomic'"
                + "      }"
                + "	},"
                + "   	{"
                + "      id: 'w5',"
                + "      label: 'gidSchema',"
                + "      x: Math.random(),"
                + "      y: Math.random(),"
                + "      size: 20,"
                + "      color: '#F00',"
                + "      data: {"
                + "        name: 'gidSchema',"
                + "        value: 'composed'"
                + "     }"
                + "    },"
                + "    {"
                + "      id: 'w8',"
                + "      label: 'compsearch',"
                + "      x: Math.random(),"
                + "      y: Math.random(),"
                + "      size: 20,"
                + "      color: '#F00',"
                + "      data: {"
                + "        name: 'compsearch',"
                + "        value: 'composed'"
                + "      }"
                + "    },"
                + "    {"
                + "      id: 'w9',"
                + "      label: 'search',"
                + "      x: Math.random(),"
                + "      y: Math.random(),"
                + "      size: 20,"
                + "      color: '#000',"
                + "      data: {"
                + "        name: 'search',"
                + "        value: 'atomic'"
                + "      }"
                + "    },"
                + "    {"
                + "      id: 'w10',"
                + "      label: 'schema',"
                + "      x: Math.random(),"
                + "      y: Math.random(),"
                + "      size: 20,"
                + "      color: '#000',"
                + "      data: {"
                + "        name: 'schema',"
                + "        type: 'atomic',"
                + "        repository: 'bioCatalogue',"
                + "        owner: 'mark'"
                + "      }"
                + "    }"
                + ");"
                + "    g.edges.push({"
                + "      id: 'e0',"
                + "      label: 'Interoperates with',"
                + "      source: 'w10',"
                + "      target: 'w9',"
                + "      color: '#000',"
                + "      hover_color: '#FC0',"
                + "      type: 'dashed',"
                + "      size: 1"
                + "    },"
                + "    {"
                + "      id: 'e1',"
                + "      label: 'Interoperates with',\n"
                + "      source: 'w10',\n"
                + "      target: 'w2',\n"
                + "      color: '#000',\n"
                + "      hover_color: '#FC0',\n"
                + "      type: 'dashed',\n"
                + "      size: 1\n"
                + "    },\n"
                + "    {\n"
                + "      id: 'e2',\n"
                + "      label: 'Interoperates with',\n"
                + "      source: 'w1',\n"
                + "      target: 'w2',\n"
                + "      color: '#000',\n"
                + "      hover_color: '#FC0',\n"
                + "      type: 'dashed',\n"
                + "      size: 1\n"
                + "    },\n"
                + "    {\n"
                + "      id: 'e3',\n"
                + "      label: 'Interoperates with',\n"
                + "      source: 'w1',\n"
                + "      target: 'w9',\n"
                + "      color: '#000',\n"
                + "      hover_color: '#FC0',\n"
                + "      type: 'dashed',\n"
                + "      size: 1\n"
                + "    },\n"
                + "    {\n"
                + "      id: 'e4',\n"
                + "      label: 'Interoperates with',\n"
                + "      source: 'w3',\n"
                + "      target: 'w10',\n"
                + "      color: '#000',\n"
                + "      hover_color: '#FC0',\n"
                + "      type: 'dashed',\n"
                + "      size: 1\n"
                + "    },\n"
                + "    {\n"
                + "      id: 'e5',\n"
                + "      label: 'Interoperates with',\n"
                + "      source: 'w3',\n"
                + "      target: 'w1',\n"
                + "      color: '#000',\n"
                + "      hover_color: '#FC0',\n"
                + "      type: 'dashed',\n"
                + "      size: 1\n"
                + "    },\n"
                + "    {    \n"
                + "     id: 'e7',\n"
                + "      label: 'Depends of',\n"
                + "      source: 'w8',\n"
                + "      target: 'w10',\n"
                + "      color: '#F00',\n"
                + "      type: 'curvedArrow',\n"
                + "      hover_color: '#FC0',\n"
                + "      size: 1\n"
                + "    },    \n"
                + "    {    \n"
                + "     id: 'e8',\n"
                + "      label: 'Depends of',\n"
                + "      source: 'w8',\n"
                + "      target: 'w9',\n"
                + "      color: '#F00',\n"
                + "      type: 'curvedArrow',\n"
                + "      hover_color: '#FC0',\n"
                + "      size: 1\n"
                + "    },    \n"
                + "    {    \n"
                + "     id: 'e9',\n"
                + "      label: 'Depends of',\n"
                + "      source: 'w5',\n"
                + "      target: 'w2',\n"
                + "      color: '#F00',\n"
                + "      type: 'curvedArrow',\n"
                + "      hover_color: '#FC0',\n"
                + "      size: 1\n"
                + "    },    \n"
                + "    {    \n"
                + "     id: 'e10',\n"
                + "      label: 'Depends of',\n"
                + "      source: 'w5',\n"
                + "      target: 'w10',\n"
                + "      color: '#F00',\n"
                + "      type: 'curvedArrow',\n"
                + "      hover_color: '#FC0',\n"
                + "      size: 1\n"
                + "    },    \n"
                + "    {    \n"
                + "     id: 'e11',\n"
                + "      label: 'Depends of',\n"
                + "      source: 'w8',\n"
                + "      target: 'w9',\n"
                + "      color: '#F00',\n"
                + "      type: 'curvedArrow',\n"
                + "      hover_color: '#FC0',\n"
                + "      size: 1\n"
                + "    }\n"
                + "    );\n";
        script += renderizeScript();
        return script;
    }

    /**
     * It renderizes the javascript (linkurious library)
     *
     * @return
     */
    public String renderizeScript() {
        String rend
                = "sigma.renderers.def = sigma.renderers.canvas;\n"
                + "\n"
                + "// Instantiate sigma:\n"
                + "s = new sigma({\n"
                + "  graph: g,\n"
                + "  renderer: {\n"
                + "    container: document.getElementById('graph-container'),\n"
                + "    type: 'canvas'\n"
                + "  },\n"
                + "  settings: {\n"
                + "    minNodeSize: 20,\n"
                + "    maxNodeSize: 20,\n"
                + "    minEdgeSize: 3,\n"
                + "    maxEdgeSize: 10,\n"
                + "    dragNodeStickiness: 0.01,\n"
                + "    nodeBorderSize: 2,\n"
                + "    defaultNodeBorderColor: '#000',\n"
                + "    enableEdgeHovering: true,\n"
                + "    edgeHoverColor: 'edge',\n"
                + "    defaultEdgeHoverColor: '#000',\n"
                + "    edgeHoverSizeRatio: 1,\n"
                + "    edgeHoverExtremities: true,\n"
                + "    autoCurveRatio: 2,\n"
                + "    edgeHoverHighlightNodes: 'circle',\n"
                + "  }\n"
                + "});\n"
                + "\n"
                + "s.refresh();\n"
                + "\n"
                + "\n"
                + "// Instanciate the ActiveState plugin:\n"
                + "var activeState = sigma.plugins.activeState(s);\n"
                + "\n"
                + "// Initialize the dragNodes plugin:\n"
                + "var dragListener = sigma.plugins.dragNodes(s, s.renderers[0], activeState);\n"
                + "\n"
                + "// Initialize the Select plugin:\n"
                + "var select = sigma.plugins.select(s, activeState);\n"
                + "\n"
                + "// Initialize the Keyboard plugin:\n"
                + "var keyboard = sigma.plugins.keyboard(s, s.renderers[0]);\n"
                + "var config = {"
                + "  node: [{"
                + "    show: 'hovers',\n"
                + "    hide: 'hovers',\n"
                + "    cssClass: 'sigma-tooltip',    \n"
                + "    position: 'top',\n"
                + "    autoadjust: true,\n"
                + "    template:\n"
                + "    '<div class=\"arrow\"></div>' +\n"
                + "    ' <div class=\"sigma-tooltip-header\">{{label}}</div>' +\n"
                + "    '  <div class=\"sigma-tooltip-body\">' +\n"
                + "    '    <table>' +\n"
                + "    '      <tr><th>Name</th> <td>{{data.name}}</td></tr>' +\n"
                + "    '      <tr><th>Type</th> <td>{{data.type}}</td></tr>' +\n"
                + "	'      <tr><th>Repository Name</th> <td>{{data.repository}}</td></tr>' +    \n"
                + "	'      <tr><th>Owner</th> <td>{{data.owner}}</td></tr>' +\n"
                + "	'      <tr><th>Fan-in</th> <td>{{data.fanin}}</td></tr>' +\n"
                + "	'      <tr><th>Fan-out</th> <td>{{data.fanout}}</td></tr>' +\n"
                + "	'      <tr><th>Closeness</th> <td>{{data.closeness}}</td></tr>' +\n"                
                + "	'      <tr><th>Betweenness</th> <td>{{data.betweenness}}</td></tr>' +\n"                
                + "    '    </table>' +\n"
                + "    '  </div>' +\n"
                + "    '  <div class=\"sigma-tooltip-footer\">Number of connections: {{degree}}</div>',\n"
                + "    renderer: function(node, template) {\n"
                + "      // The function context is s.graph\n"
                + "      node.degree = this.degree(node.id);\n"
                + "\n"
                + "      // Returns an HTML string:\n"
                + "      return Mustache.render(template, node);\n"
                + "\n"
                + "      // Returns a DOM Element:\n"
                + "      //var el = document.createElement('div');\n"
                + "      //return el.innerHTML = Mustache.render(template, node);\n"
                + "    }\n"
                + "  }, {\n"
                + "    show: 'rightClickNode',\n"
                + "    position: 'right',\n"
                + "    template:\n"
                + "    '<div class=\"arrow\"></div>' +\n"
                + "    ' <div class=\"sigma-tooltip-header\">{{label}}</div>' +\n"
                + "    '  <div class=\"sigma-tooltip-body2\">' +\n"
                + "    '   <p> Possible alternative services: </p>' +\n";
        if (substituteServices > 0) {
            for (int i = 0; i < substituteServices; i++) {
                if(i < 4)    
                    rend += "    '   <p> Service " + (i + 1) + ": {{data.sub" + (i + 1) + "}} </p>' +\n";
            }
        }
        rend += "    '  </div>' +\n"
                + "    ' <div class=\"sigma-tooltip-footer\">Number of connections: {{degree}}</div>',\n"
                + "    renderer: function(node, template) {\n"
                + "      node.degree = this.degree(node.id);\n"
                + "      return Mustache.render(template, node);\n"
                + "    }\n"
                + "  }],\n"
                + "  stage: {\n"
                + "    template:\n"
                + "    '<div class=\"arrow\"></div>' +\n"
                + "    '<div class=\"sigma-tooltip-header\"> Services Analysis </div>'\n"
                + "  }\n"
                + "};\n"
                + "\n"
                + "// Instanciate the tooltips plugin with a Mustache renderer for node tooltips:\n"
                + "var tooltips = sigma.plugins.tooltips(s, s.renderers[0], config);\n"
                + "\n"
                + "tooltips.bind('shown', function(event) {\n"
                + "  console.log('tooltip shown', event);\n"
                + "});\n"
                + "\n"
                + "tooltips.bind('hidden', function(event) {\n"
                + "  console.log('tooltip hidden', event);\n"
                + "});\n"
                + "\n"
                + "// Configure the DAG layout:\n"
                + "var dagreListener = sigma.layouts.dagre.configure(s, {\n"
                + "  directed: true, // take edge direction into account\n"
                + "  rankdir: 'TB',  // Direction for rank nodes. Can be TB, BT, LR, or RL,\n"
                + "                  // where T = top, B = bottom, L = left, and R = right.\n"
                + "  easing: 'quadraticInOut', // animation transition function\n"
                + "  duration: 800,   // animation duration\n"
                + "  // nodes : s.graph.nodes().slice(0,30), // subset of nodes\n"
                + "  // boundingBox: {minX: 10, maxX: 20, minY: 10, maxY:20} // constrain layout bounds ; object or true (all current positions of the given nodes)\n"
                + "});\n"
                + "\n"
                + "// Bind the events:\n"
                + "dagreListener.bind('start stop interpolate', function(e) {\n"
                + "  console.log(e.type);\n"
                + "});\n"
                + "\n"
                + "// Start the DAG layout:\n"
                + "sigma.layouts.dagre.start(s);\n"
                + "\n"
                + "// Bind the Keyboard plugin to the Select plugin:\n"
                + "select.bindKeyboard(keyboard);\n"
                + "\n"
                + "// Curve parallel edges:\n"
                + "sigma.canvas.edges.autoCurve(s);\n"
                + "\n"
                + "dragListener.bind('startdrag', function(event) {\n"
                + "  console.log(event);\n"
                + "});\n"
                + "dragListener.bind('drag', function(event) {\n"
                + "  console.log(event);\n"
                + "});\n"
                + "dragListener.bind('drop', function(event) {\n"
                + "  console.log(event);\n"
                + "});\n"
                + "dragListener.bind('dragend', function(event) {\n"
                + "  console.log(event);\n"
                + "});\n"
                + "\n"
                + "// Bind the events:\n"
                + "s.bind('clickNode doubleClickNode rightClickNode', function(e) {\n"
                + "  console.log(e.type, e.data.node.label, e.data.captor);\n"
                + "});\n"
                + "s.bind('clickEdge doubleClickEdge rightClickEdge', function(e) {\n"
                + "  console.log(e.type, e.data.edge, e.data.captor);\n"
                + "});\n"
                + "s.bind('clickStage doubleClickStage rightClickStage', function(e) {\n"
                + "  console.log(e.type, e.data.captor);\n"
                + "});\n"
                + "s.bind('hovers', function(e) {\n"
                + "  console.log(e.type, e.data.captor, e.data);\n"
                + "});";
        return rend;
    }

    /**
     * it generates a dialog with a social network
     */
    public void viewSocialNetwork() {
        RequestContext.getCurrentInstance().execute("PF('dlg').show()");
    }

    /**
     * Read the services file from the virtual repository
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    public void readFilesToSearchTerms() throws IOException, URISyntaxException {

        File f = new File("/home/phillipe/Documentos/VirtualRepository");
        File[] serviceFiles = f.listFiles();

//        String[] servicesFileNames = f.list();
        String extension;
        for (File file : serviceFiles) {
            if (file != null) {
                extension = FilenameUtils.getExtension(file.getAbsolutePath());

                if (extension.equals("owl")) {
                    ServiceFromVR sfvr = new ServiceFromVR();
                    //it gets the inputStream
                    InputStream inputStream = new FileInputStream(file);

                    //create a kb
                    OWLKnowledgeBase kb = OWLFactory.createKB();

                    String uriFile = "http://localhost:8084/plscience-ecos/" + file.getName();
                    System.out.println("URI FILE: " + uriFile);

                    URI uri = new URI(uriFile);

                    System.out.println("InputStream: " + inputStream);
                    System.out.println("uri: " + uri.toString());
                    Service service = kb.readService(inputStream, uri);

                    System.out.println("Process Type: " + service.getProcess().getType());
                    if (service.getName() == null) {
                        //System.out.println("Service name is null");
                        sfvr.setName("-");
                    } else {
                        System.out.println("Service name: " + service.getName());
                        sfvr.setName(service.getName());
                    }

                    if (service.getProfile() != null && service.getProfile().getTextDescription() != null) {
                        String description = service.getProfile().getTextDescription();
                        sfvr.setDescription(sfvr.getName() + " " + description);

                    } else if (service.getProfile() != null) {
                        sfvr.setDescription("-");
                    }

                    if (service.getProfile() != null) {

                        OWLIndividualList owlIndListInputs = service.getProfile().getInputs();
                        OWLIndividualList owlIndListOutputs = service.getProfile().getOutputs();

                        List<String> inputs = new ArrayList<>();
                        List<String> inputsType = new ArrayList<>();
                        List<String> outputs = new ArrayList<>();
                        List<String> outputsType = new ArrayList<>();

                        //get inputs from ontology
                        for (Object input : owlIndListInputs) {
                            Input inp = (Input) input;
                            if (inp.getLabel() != null) {
                                //System.out.println("Input: "+inp.getLabel());
                                inputs.add(inp.getLabel());
                            }
                            OWLType owlType = inp.getParamType();
                            String type = owlType.toString();
                            inputsType.add(type);
                            //System.out.println("Type: "+type);
                        }

                        //get outputs from ontology
                        for (Object output : owlIndListOutputs) {
                            Output out = (Output) output;
                            if (out.getLabel() != null) {
                                outputs.add(out.getLabel());
                            }
                            OWLType owlType = out.getParamType();
                            String type = owlType.toString();
                            outputsType.add(type);
                        }

                        sfvr.setInputs(inputs);
                        sfvr.setInputsType(inputsType);
                        sfvr.setOutputs(outputs);
                        sfvr.setOutputsType(outputsType);
                    }
                    servicesFromVR.add(sfvr);
                }
            } else {
                System.out.println("file is null");
            }
        }
    }

    /**
     * Process a service request adding data from PRIME approach (pragmatic
     * search)
     *
     * @param serviceReq
     * @return
     */
    public ServiceDescriptionVO processServiceRequest(ServiceDescriptionVO serviceReq) {
        SyntacticVO sync = new SyntacticVO();
        serviceReq.setIncludesSyntactic(sync);
        SemanticVO sem = new SemanticVO();
        serviceReq.setIncludesSemantic(sem);
        PragmaticVO prag = new PragmaticVO();
        serviceReq.setIncludesPragmatic(prag);
        ArrayList<String> funcs = new ArrayList();
        serviceReq.getIncludesSemantic().setHasFunctionalRequirements(funcs);
        ContextVO con = new ContextVO();
        serviceReq.getIncludesPragmatic().setIncludesContext(con);
        HardwareVO hard = new HardwareVO();
        serviceReq.getIncludesPragmatic().setIncludesHardware(hard);

        if (serviceRequest.getPatternRequest().getReturnPrimeSin() != null) {
            String returnSynt = serviceRequest.getPatternRequest().getReturnPrimeSin();
            serviceReq.getIncludesSyntactic().setHasReturn(returnSynt);
        }

        if (serviceRequest.getPatternRequest().getReturnPrimeSem() != null) {
            String returnSem = serviceRequest.getPatternRequest().getReturnPrimeSem();
            serviceReq.getIncludesSemantic().setHasSemanticReturn(returnSem);
        }

        if (serviceRequest.getPatternRequest().getReceptionPrimeSem() != null) {
            String receptionSem = serviceRequest.getPatternRequest().getReceptionPrimeSem();
            serviceReq.getIncludesSemantic().setHasSemanticReception(receptionSem);
        }

        if (serviceRequest.getPatternRequest().getRepresentationPrimeSem() != null) {
            String representation = serviceRequest.getPatternRequest().getRepresentationPrimeSem();
            serviceReq.getIncludesSemantic().setHasSemanticRepresentation(representation);
        }

        if (serviceRequest.getPatternRequest().getFunctionalRequirementPrimeSem() != null) {
            String funcReq = serviceRequest.getPatternRequest().getFunctionalRequirementPrimeSem();
            serviceReq.getIncludesSemantic().setHasFunctionalRequirement(funcReq);
        }

        if (serviceRequest.getPatternRequest().getNonFunctionalRequirementPrimePra() != null) {
            String nonFuncReq = serviceRequest.getPatternRequest().getNonFunctionalRequirementPrimePra();
            serviceReq.getIncludesPragmatic().setHasNonFunctionalReq(nonFuncReq);
        }

        if (serviceRequest.getPatternRequest().getNonFunctionalRequirementPrimePra() != null) {
            String nonFuncReq = serviceRequest.getPatternRequest().getNonFunctionalRequirementPrimePra();
            serviceReq.getIncludesPragmatic().setHasNonFunctionalReq(nonFuncReq);
        }

        if (serviceRequest.getPatternRequest().getArtifactPrimePra() != null) {
            String artifact = serviceRequest.getPatternRequest().getArtifactPrimePra();
            serviceReq.getIncludesPragmatic().getIncludesContext().setHasArtifact(artifact);
        }

        if (serviceRequest.getPatternRequest().getDomainPrimePra() != null) {
            String domain = serviceRequest.getPatternRequest().getDomainPrimePra();
            serviceReq.getIncludesPragmatic().getIncludesContext().setHasDomain(domain);
        }

        if (serviceRequest.getPatternRequest().getLicenseType() != null) {
            String license = serviceRequest.getPatternRequest().getLicenseType();
            serviceReq.getIncludesPragmatic().getIncludesContext().setHasLicense(license);
        }

        if (serviceRequest.getPatternRequest().getDescription() != null) {
            String comments = serviceRequest.getPatternRequest().getDescription();
            serviceReq.getIncludesPragmatic().getIncludesContext().setHasComments(comments);
        }

        if (serviceRequest.getPatternRequest().getPurposePrimePra() != null) {
            String purpose = serviceRequest.getPatternRequest().getPurposePrimePra();
            serviceReq.getIncludesPragmatic().getIncludesContext().setHow(purpose);
        }

        if (serviceRequest.getPatternRequest().getProviderPrimePra() != null) {
            String provider = serviceRequest.getPatternRequest().getProviderPrimePra();
            serviceReq.getIncludesPragmatic().getIncludesContext().setWhere(provider);
        }

        if (serviceRequest.getPatternRequest().getArchivedAt() != null) {
            String publicationData = serviceRequest.getPatternRequest().getArchivedAt();
            serviceReq.getIncludesPragmatic().getIncludesContext().setWhen(publicationData);
        }

        if (serviceRequest.getPatternRequest().getOwner() != null) {
            String owner = serviceRequest.getPatternRequest().getOwner();
            serviceReq.getIncludesPragmatic().getIncludesContext().setWho(owner);
        }

        if (serviceRequest.getPatternRequest().getRestrictionPrimePra() != null) {
            String rest = serviceRequest.getPatternRequest().getRestrictionPrimePra();
            serviceReq.getIncludesPragmatic().getIncludesContext().setHasRestriction(rest);
        }

        if (serviceRequest.getPatternRequest().getHardwareCPUPrimePra() != null) {
            String cpu = serviceRequest.getPatternRequest().getHardwareCPUPrimePra();
            serviceReq.getIncludesPragmatic().getIncludesHardware().setHasCPU(cpu);
        }

        if (serviceRequest.getPatternRequest().getHardwareOperationalSystemPrimePra() != null) {
            String os = serviceRequest.getPatternRequest().getHardwareOperationalSystemPrimePra();
            serviceReq.getIncludesPragmatic().getIncludesHardware().setHasOperationalSystem(os);
        }

        if (serviceRequest.getPatternRequest().getHardwareRAMPrimePra() != null) {
            String ram = serviceRequest.getPatternRequest().getHardwareRAMPrimePra();
            serviceReq.getIncludesPragmatic().getIncludesHardware().setHasRAM(ram);
        }

        if (serviceRequest.getPatternRequest().getHardwareROMPrimePra() != null) {
            String rom = serviceRequest.getPatternRequest().getHardwareROMPrimePra();
            serviceReq.getIncludesPragmatic().getIncludesHardware().setHasROM(rom);
        }

        if (keywords.contains("interoperability")) {
            serviceReq.getIncludesPragmatic().setHasNonFunctionalReq("interoperability");
        }
        if (keywords.contains("linux")) {
            serviceReq.getIncludesPragmatic().getIncludesHardware().setHasOperationalSystem("linux");
        }

        return serviceReq;
    }

    /**
     * List the relevant services using PRIME
     */
    public void listRelevantServices() {

        SimilarityCalculation1 similarityCalc = new SimilarityCalculation1();
        ArrayList<RankingVO> rankingServices = new ArrayList<>();
        List<ServiceDescriptionVO> servicesRankingSorted = new ArrayList<>();

        System.out.println("Listing Relevant Services");

        //setting request in prime format
        String type = "commas";
        keywords = StopWordsRemoval.removeStopWords(keywords, type);

        ServiceDescriptionVO serviceReq = new ServiceDescriptionVO();

        try {
            if (serviceRequest.getInputParametersName() != null) {
                servicesFromVR = semanticRelevantsFilter(servicesFromVR);
            }
        } catch (IOException ex) {
            //Logger.getLogger(ServiceComposition.class.getName()).log(Level.SEVERE, null, ex);
        }

        //service request to prime format
        serviceReq = processServiceRequest(serviceReq);

        for (ServiceFromVR sfvr : servicesFromVR) {
            String description = sfvr.getDescription();
            type = "space";
            description = StopWordsRemoval.removeStopWords(description, type);

            //prime format
            ServiceDescriptionVO serviceDescriptionVO;
            serviceDescriptionVO = new ServiceDescriptionVO();
            SyntacticVO syn = new SyntacticVO();
            serviceDescriptionVO.setIncludesSyntactic(syn);
            SemanticVO se = new SemanticVO();
            serviceDescriptionVO.setIncludesSemantic(se);
            PragmaticVO pra = new PragmaticVO();
            serviceDescriptionVO.setIncludesPragmatic(pra);
            ArrayList<String> funcss = new ArrayList();
            serviceDescriptionVO.getIncludesSemantic().setHasFunctionalRequirements(funcss);
            ContextVO cont = new ContextVO();
            serviceDescriptionVO.getIncludesPragmatic().setIncludesContext(cont);
            HardwareVO hardw = new HardwareVO();
            serviceDescriptionVO.getIncludesPragmatic().setIncludesHardware(hardw);

            addServiceToPrimeFormat(sfvr, serviceDescriptionVO, description);
        }

        for (ServiceDescriptionVO sVo : servicesPrime) {
            RankingVO rVo = new RankingVO();
            rVo.setServiceRecovery(sVo);
            rVo.setServiceComparison(serviceReq);
            rVo.setSimilarity(similarityCalc.calculate(serviceReq, sVo, 1, 1, 1));
            rankingServices.add(rVo);
        }

        System.out.println("Sorting services using PRIME!");
        Collections.sort(rankingServices);

        for (RankingVO rank : rankingServices) {
            servicesRankingSorted.add(rank.getServiceRecovery());
            System.out.println("Nome Serv: " + rank.getServiceRecovery().getName() + " Simil: " + rank.getSimilarity());
        }

        showServicesRanking(rankingServices);
    }

    /**
     * Filter to semantic relevants services
     *
     * @param services
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public List semanticRelevantsFilter(List services) throws MalformedURLException, IOException {
        if (serviceRequest.getInputParametersName() == null
                && serviceRequest.getOutputParametersName() == null) {
            return services;
        }

        System.out.println("Opening WordNet Dictionary");
        WordNetDictionary wnd = new WordNetDictionary();
        IDictionary dict = loadDictionary();
        dict.open();

        List inpParametersRequest = serviceRequest.getInputParametersName();
        List outParametersRequest = serviceRequest.getOutputParametersName();
        List<ServiceFromVR> newServicesList = new ArrayList();

        boolean containsSemanticInput = false;
        boolean containsSemanticOutput = false;
        boolean isExactOrPluginCase = false;

        for (Object se : services) {

            if (inpParametersRequest != null && inpParametersRequest.size() > 0) {
                for (Object o : inpParametersRequest) {
                    String par = (String) o;
                    //getAllSynonymsInput
                    List listSInp = wnd.getSynonyms(dict, par);
                    ServiceFromVR s = (ServiceFromVR) se;

                    //verify semantic inputs
                    for (int i = 0; i < s.getInputs().size(); i++) {
                        for (int j = 0; j < listSInp.size(); j++) {
                            if (s.getInputs().get(i).equals(listSInp.get(j))) {
                                containsSemanticInput = true;
                            }
                        }
                        if (containsSemanticInput) {
                            newServicesList.add(s);
                        }
                        containsSemanticInput = false;
                    }
                }
            }

            if (outParametersRequest != null && outParametersRequest.size() > 0) {
                for (Object o2 : outParametersRequest) {
                    String outputName = (String) o2;
                    //getAllSynonymsOutput
                    List listSOut = wnd.getSynonyms(dict, outputName);
                    List listHOut = wnd.getSynonyms(dict, outputName);
                    ServiceFromVR s = (ServiceFromVR) se;

                    //verify semantic outputs
                    for (int i = 0; i < s.getOutputs().size(); i++) {
                        for (int j = 0; j < listSOut.size(); j++) {
                            //verify semantic outputs
                            if (s.getOutputs().get(i).equals(listSOut.get(j))) {
                                containsSemanticOutput = true;
                            }
                        }
                        if (containsSemanticOutput && !newServicesList.contains(s)) {
                            newServicesList.add(s);
                        }
                        containsSemanticOutput = false;
                    }

                    //verify plugin and exact cases
                    for (int i = 0; i < s.getInputs().size(); i++) {
                        for (int numHyp = 0; numHyp < listHOut.size(); numHyp++) {
                            if (s.getInputs().get(i).equals(listHOut.get(numHyp))) {
                                isExactOrPluginCase = true;//plugin case ok
                                //output of request plugin input of service
                            }
                            if (s.getInputs().get(i).equals(outputName)) {
                                isExactOrPluginCase = true;//exact case ok
                                //output of request equal input of service
                            }

                        }
                        if (isExactOrPluginCase && !newServicesList.contains(s)) {
                            newServicesList.add(s);
                        }
                        isExactOrPluginCase = false;
                    }
                }
            }
        }
        return newServicesList;
    }

    /**
     * add services to PRIME format
     *
     * @param sfvr
     * @param serviceDescriptionVO
     * @param description
     */
    public void addServiceToPrimeFormat(ServiceFromVR sfvr, ServiceDescriptionVO serviceDescriptionVO,
            String description) {

        ArrayList<String> parametersInp = new ArrayList<>();
        ArrayList<String> parametersOut = new ArrayList<>();
        parametersInp = (ArrayList<String>) (sfvr.getInputs());
        parametersOut = (ArrayList<String>) (sfvr.getOutputs());

        ArrayList<String> parameters = new ArrayList<>();

        for (int i = 0; i < parametersInp.size(); i++) {
            if (parametersOut.size() > i) {
                String pin = (String) parametersInp.get(i);
                String pout = (String) parametersOut.get(i);
                parameters.add(pin);
                parameters.add(pout);
            } else {
                String pin = (String) parametersInp.get(i);
                parameters.add(pin);
            }
        }

        serviceDescriptionVO.getIncludesSyntactic().setHasParameters(parameters);
        String servName = sfvr.getName();

        serviceDescriptionVO = addServiceDescriptionVO(sfvr, serviceDescriptionVO, servName, description);
        serviceDescriptionVO.setName(servName);
        servicesHashMap.put(serviceDescriptionVO, sfvr);
        servicesPrime.add(serviceDescriptionVO);
    }

    /**
     * add a ServiceDescriptionV0 using a string
     *
     * @param sfvr
     * @param serviceDescriptionVO
     * @param servName
     * @param description
     * @return
     */
    public ServiceDescriptionVO addServiceDescriptionVO(ServiceFromVR sfvr, ServiceDescriptionVO serviceDescriptionVO,
            String servName, String description) {

        if (servName == null || servName.equals("")) {
            return serviceDescriptionVO;
        }

        String repositoryURL;
        repositoryURL = File.separatorChar + "home" + File.separatorChar + "phillipe"
                + File.separatorChar + "Documentos" + File.separatorChar + "VirtualRepository";
        try {
            File inputFile = new File(repositoryURL + File.separatorChar + servName + ".xml");
            DocumentBuilderFactory docFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder
                    = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
            //Node service = doc.getFirstChild();
            Node serviceData = doc.getElementsByTagName("serviceData").item(0);

            NodeList list = serviceData.getChildNodes();
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    if (eElement.getTextContent() != null && !eElement.getTextContent().equals("")) {
                        String content = eElement.getTextContent();

                        if ("type".equals(eElement.getNodeName())) {
                            sfvr.setType(content);
                        }
                        if ("repository".equals(eElement.getNodeName())) {
                            sfvr.setRepositoryName(content);
                        }
                        if ("owner".equals(eElement.getNodeName())) {
                            sfvr.setOwner(content);
                        }
                        if ("returnPrimeSyn".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesSyntactic().setHasReturn(content);
                        }
                        if ("returnPrimeSem".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesSemantic().setHasSemanticReturn(content);
                        }
                        if ("receptionPrimeSem".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesSemantic().setHasSemanticReception(content);
                        }
                        if ("representationPrimeSem".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesSemantic().setHasSemanticRepresentation(content);
                        }
                        if ("functionalRequirementPrimeSem".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesSemantic().setHasFunctionalRequirement(content);
                        }
                        if ("nonFunctionalRequirementPrimePra".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().setHasNonFunctionalReq(content);
                        }
                        if ("artifactPrimePra".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHasArtifact(content);
                        }
                        if ("domainPrimePra".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHasDomain(content);
                        }
                        if ("purposePrimePra".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHow(content);
                        }
                        if ("providerPrimePra".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setWhere(content);
                        }
                        if ("owner".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setWho(content);
                        }
                        if ("description".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHasComments(content);
                        }
                        if ("restrictionPrimePra".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHasRestriction(content);
                        }
                        if ("hardwareCPUPrimePra".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().setHasCPU(content);
                        }
                        if ("hardwareOperationalSystem".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().setHasOperationalSystem(content);
                        }
                        if ("hardwareRAMPrimePra".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().setHasRAM(content);
                        }
                        if ("hardwareROMPrimePra".equals(eElement.getNodeName())) {
                            serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().setHasROM(content);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceDescriptionVO;
    }

    /**
     * Used to show the ranked services in a web interface
     *
     * @param rankingServices
     */
    public void showServicesRanking(ArrayList<RankingVO> rankingServices) {
        tasksCompositionData = new ArrayList<>();
        //System.out.println("Ranking tamanho: " + rankingServices.size());
        for (RankingVO rank : rankingServices) {
            System.out.println("Serv NAME: " + rank.getServiceRecovery().getName());
            TasksCompositionData tcd = new TasksCompositionData();
            List<TaskInput> tinptList = new ArrayList<>();
            List<TaskOutput> toutList = new ArrayList<>();
            tcd.setTaskName(rank.getServiceRecovery().getName());

            ServiceFromVR sfvr = servicesHashMap.get(rank.getServiceRecovery());
            if (sfvr.getInputs() != null && sfvr.getInputsType() != null
                    && sfvr.getInputs().size() == sfvr.getInputsType().size()) {
                for (int i = 0; i < sfvr.getInputs().size(); i++) {
                    TaskInput ti = new TaskInput();
                    ti.setInputParameter(sfvr.getInputs().get(i));
                    ti.setInputType(sfvr.getInputsType().get(i));
                    tinptList.add(ti);
                }
            }
            if (sfvr.getOutputs() != null && sfvr.getOutputsType() != null
                    && sfvr.getOutputs().size() == sfvr.getOutputsType().size()) {
                for (int i = 0; i < sfvr.getOutputs().size(); i++) {
                    TaskOutput o1 = new TaskOutput();
                    o1.setOutputParameter(sfvr.getOutputs().get(i));
                    o1.setOutputType(sfvr.getOutputsType().get(i));
                    toutList.add(o1);
                }
            }

            tcd.setTaskInputs(tinptList);
            tcd.setTaskOutputs(toutList);
            tasksCompositionData.add(tcd);
        }
    }

    /**
     * Lists a sample list of relevant services
     */
    public void sampleRelevantServicesList() {
        tasksCompositionData = new ArrayList<>();
        //task1
        TasksCompositionData t1 = new TasksCompositionData();
        List<TaskInput> tinpt1 = new ArrayList<>();
        List<TaskOutput> toutt1 = new ArrayList<>();
        TaskInput i1 = new TaskInput();
        i1.setInputParameter("input");
        i1.setInputType("xsd:string");
        tinpt1.add(i1);
        TaskOutput o1 = new TaskOutput();
        o1.setOutputParameter("result");
        o1.setOutputType("npd:SQLqueryResult");
        toutt1.add(o1);

        t1.setTaskName("search");
        t1.setTaskInputs(tinpt1);
        t1.setTaskOutputs(toutt1);
        tasksCompositionData.add(t1);

        //task 2
        TasksCompositionData t2 = new TasksCompositionData();
        List<TaskInput> tinpt2 = new ArrayList<>();
        List<TaskOutput> toutt2 = new ArrayList<>();
        TaskInput i2 = new TaskInput();
        i2.setInputParameter("input");
        i2.setInputType("xsd:string");
        tinpt2.add(i2);
        TaskOutput o2 = new TaskOutput();
        o2.setOutputParameter("result");
        o2.setOutputType("npd:geneIDResult");
        toutt2.add(o2);

        t2.setTaskName("geneId");
        t2.setTaskInputs(tinpt2);
        t2.setTaskOutputs(toutt2);
        tasksCompositionData.add(t2);

        //task 3
        TasksCompositionData t3 = new TasksCompositionData();
        List<TaskInput> tinpt3 = new ArrayList<>();
        List<TaskOutput> toutt3 = new ArrayList<>();
        TaskInput i3 = new TaskInput();
        i3.setInputParameter("input");
        i3.setInputType("xsd:string");
        tinpt3.add(i3);
        TaskOutput o3 = new TaskOutput();
        o3.setOutputParameter("result");
        o3.setOutputType("xsd:string");
        toutt3.add(o3);

        t3.setTaskName("schema");
        t3.setTaskInputs(tinpt3);
        t3.setTaskOutputs(toutt3);
        tasksCompositionData.add(t3);
    }

    public void addParametersToServiceRequest() {
        if (requestInpParameterNameForm != null && !requestInpParameterNameForm.equals("")) {
            addInputParametersNameReq(requestInpParameterNameForm);
        }
        if (requestInpParameterTypeForm != null && !requestInpParameterTypeForm.equals("")) {
            addInputParametersTypeReq(requestInpParameterTypeForm);
        }
        if (requestOutParameterNameForm != null && !requestOutParameterNameForm.equals("")) {
            addOutputParametersNameReq(requestOutParameterNameForm);
        }
        if (requestOutParameterTypeForm != null && !requestOutParameterTypeForm.equals("")) {
            addOutputParametersTypeReq(requestOutParameterTypeForm);
        }
        if (keywords != null && !keywords.equals("")) {
            addKeywords(keywords);
        }
    }

    /**
     * it converts a string from the in a list of strings
     *
     * @param string
     * @return
     */
    public List<String> StringFormToList(String string) {
        String[] input;
        List<String> listP = new ArrayList<>();
        if (string.contains(",")) {
            input = string.split(",");
            listP.addAll(Arrays.asList(input));//adicionando os inputs na lista
        } else {
            listP.add(string);
        }
        return listP;
    }

    /**
     * add the input parameters name to the service request
     *
     * @param inputParametersName
     */
    public void addInputParametersNameReq(String inputParametersName) {
        System.out.println("InpParName: " + inputParametersName);
        List<String> listP = StringFormToList(inputParametersName);
        serviceRequest.setInputParametersName(listP);

        if (listP == null) {
            System.out.println("List is null ");
        } else {
            System.out.println("List is not null ");
        }

        for (String teste : serviceRequest.getInputParametersName()) {
            System.out.println("Teste vale: " + teste);
        }
    }

    /**
     * add the input parameters type to the service request
     *
     * @param inputParametersType
     */
    public void addInputParametersTypeReq(String inputParametersType) {
        List<String> listP = StringFormToList(inputParametersType);
        serviceRequest.setInputParametersType(listP);
    }

    /**
     * add the output parameters name to the service request
     *
     * @param outputParametersName
     */
    public void addOutputParametersNameReq(String outputParametersName) {
        List<String> listP = StringFormToList(outputParametersName);
        serviceRequest.setOutputParametersName(listP);
    }

    /**
     * add the output parameters type to the service request
     *
     * @param outputParametersType
     */
    public void addOutputParametersTypeReq(String outputParametersType) {
        List<String> listP = StringFormToList(outputParametersType);
        serviceRequest.setOutputParametersType(listP);
    }

    /**
     * add keywords in a list
     *
     * @param keywords
     */
    public void addKeywords(String keywords) {
        List<String> listKey = StringFormToList(keywords);
        serviceRequest.setKeywords(listKey);
    }

    /**
     * @return the serviceRequest
     */
    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    /**
     * @param serviceRequest the serviceRequest to set
     */
    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    /**
     * @return the requestInpParameterNameForm
     */
    public String getRequestInpParameterNameForm() {
        return requestInpParameterNameForm;
    }

    /**
     * @param requestInpParameterNameForm the requestInpParameterNameForm to set
     */
    public void setRequestInpParameterNameForm(String requestInpParameterNameForm) {
        this.requestInpParameterNameForm = requestInpParameterNameForm;
    }

    /**
     * @return the requestInpParameterTypeForm
     */
    public String getRequestInpParameterTypeForm() {
        return requestInpParameterTypeForm;
    }

    /**
     * @param requestInpParameterTypeForm the requestInpParameterTypeForm to set
     */
    public void setRequestInpParameterTypeForm(String requestInpParameterTypeForm) {
        this.requestInpParameterTypeForm = requestInpParameterTypeForm;
    }

    /**
     * @return the requestOutParameterNameForm
     */
    public String getRequestOutParameterNameForm() {
        return requestOutParameterNameForm;
    }

    /**
     * @param requestOutParameterNameForm the requestOutParameterNameForm to set
     */
    public void setRequestOutParameterNameForm(String requestOutParameterNameForm) {
        this.requestOutParameterNameForm = requestOutParameterNameForm;
    }

    /**
     * @return the requestOutParameterTypeForm
     */
    public String getRequestOutParameterTypeForm() {
        return requestOutParameterTypeForm;
    }

    /**
     * @param requestOutParameterTypeForm the requestOutParameterTypeForm to set
     */
    public void setRequestOutParameterTypeForm(String requestOutParameterTypeForm) {
        this.requestOutParameterTypeForm = requestOutParameterTypeForm;
    }

    /**
     * @return the keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * @return the servicesFile
     */
    public List<UploadedFile> getServicesFile() {
        return servicesFile;
    }

    /**
     * @param servicesFile the servicesFile to set
     */
    public void setServicesFile(List<UploadedFile> servicesFile) {
        this.servicesFile = servicesFile;
    }

    /**
     * @return the tasksCompositionData
     */
    public List<TasksCompositionData> getTasksCompositionData() {
        return tasksCompositionData;
    }

    /**
     * @param tasksCompositionData the tasksCompositionData to set
     */
    public void setTasksCompositionData(List<TasksCompositionData> tasksCompositionData) {
        this.tasksCompositionData = tasksCompositionData;
    }

    /**
     * @return the servicesFromVR
     */
    public List<ServiceFromVR> getServicesFromVR() {
        return servicesFromVR;
    }

    /**
     * @param servicesFromVR the servicesFromVR to set
     */
    public void setServicesFromVR(List<ServiceFromVR> servicesFromVR) {
        this.servicesFromVR = servicesFromVR;
    }

    /**
     * @return the substituteServices
     */
    public Integer getSubstituteServices() {
        return substituteServices;
    }

    /**
     * @param substituteServices the substituteServices to set
     */
    public void setSubstituteServices(Integer substituteServices) {
        this.substituteServices = substituteServices;
    }
}

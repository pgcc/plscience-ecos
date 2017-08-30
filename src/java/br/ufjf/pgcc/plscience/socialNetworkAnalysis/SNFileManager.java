/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.socialNetworkAnalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author phillipe
 */
public class SNFileManager {

    private static String FILEPATH = "/home/phillipe/NetBeansProjects/plscience-ecos/web/files/socialNetwork/rede_social.xml";
    private static GraphSN graph;
    private static String visualizationLevel;
    private static List<NodeSN> researcherNodes;
    private static List<NodeSN> groupNodes;
    private static List<NodeSN> universityNodes;
    private static List<EdgeSN> researcherEdges;
    private static List<EdgeSN> groupEdges;
    private static List<EdgeSN> universityEdges;
    private static List<String> researcherEdgeExistence;
    private static List<String> groupEdgeExistence;
    private static List<String> universityEdgeExistence;
    private static int minYearEvolution;
    private static int maxYearEvolution;

    /**
     * read the xml file to get data and build the graph based in a
     * visualizationLevel
     *
     * @param level
     * @param minYear
     * @param maxYear
     * @return
     */
    public static GraphSN readXMLBuildGraph(String level,int minYear,int maxYear) {
        minYearEvolution = minYear;
        maxYearEvolution = maxYear;
        visualizationLevel = level;

        setGraph(new GraphSN());
        setResearcherNodes(new ArrayList<>());
        setGroupNodes(new ArrayList<>());
        setUniversityNodes(new ArrayList<>());
        setResearcherEdges(new ArrayList<>());
        groupEdges = new ArrayList<>();
        universityEdges = new ArrayList<>();
        setResearcherEdgeExistence(new ArrayList<>());
        groupEdgeExistence = new ArrayList<>();
        universityEdgeExistence = new ArrayList<>();

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(getFILEPATH());

            NodeList nList = doc.getElementsByTagName("graphml");

            for (int i = 0; i < nList.getLength(); i++) {//tag graphml
                Node node = nList.item(i);
                Element element = (Element) node;
                //print graphml
                //System.out.println("Element node name: "+element.getNodeName());

                NodeList graphTag = element.getElementsByTagName("graph");
                for (int j = 0; j < graphTag.getLength(); j++) {//tag graph
                    Node nodeGraphTag = graphTag.item(j);
                    Element elementGraphTag = (Element) nodeGraphTag;
                    //print graph
                    //System.out.println("Element node name: "+elementGraphTag.getNodeName());

                    NodeList listOfNodes = elementGraphTag.getElementsByTagName("node");
                    System.out.println("Iniciando leitura dos nos");
                    ListNodes(listOfNodes);

                    NodeList listOfEgdes = elementGraphTag.getElementsByTagName("edge");
                    System.out.println("Iniciando leitura das arestas");
                    ListEdges(listOfEgdes);

                    if(level.equals("3")){
                        researcherNodes = setResearcherNodesWithoutConnections(researcherNodes,researcherEdges);
                    }else if(level.equals("2")){
                        groupNodes = setGroupNodesWithoutConnections(groupNodes,groupEdges);
                    }
                    
                    graph.setResearcherNodes(researcherNodes);
                    graph.setGroupNodes(groupNodes);
                    graph.setUniversityNodes(universityNodes);
                    graph.setResearcherEdges(researcherEdges);
                    graph.setGroupEdges(groupEdges);
                    graph.setUniversityEdges(universityEdges);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error reading file");
        }
        return getGraph();
    }
    
    public static List<NodeSN> setGroupNodesWithoutConnections(List<NodeSN> nodes,List<EdgeSN> edges){
        for(NodeSN n:nodes){
            String id = n.getId();
            for(EdgeSN e:edges){
                String source = e.getSource();
                String target = e.getTarget();
                if(source.equals(id) || target.equals(id)){
                    n.setWithoutConnection(false);
                }
            }
        }
        return nodes;
    }
    
    public static List<NodeSN> setResearcherNodesWithoutConnections(List<NodeSN> nodes,List<EdgeSN> edges){
        for(NodeSN n:nodes){
            String id = n.getId();
            for(EdgeSN e:edges){
                String source = e.getSource();
                String target = e.getTarget();
                if(source.equals(id) || target.equals(id)){
                    n.setWithoutConnection(false);
                }
            }
        }
        return nodes;
    }

    /**
     * get all elements of node tag
     *
     * @param listOfNodes
     */
    public static void ListNodes(NodeList listOfNodes) {
        for (int k = 0; k < listOfNodes.getLength(); k++) {//tag node
            NodeSN node = new NodeSN();
            Node nodeNodeTag = listOfNodes.item(k);
            Element elementNodeTag = (Element) nodeNodeTag;
            node = addDataToNode(elementNodeTag, node);

            switch (node.getLevel()) {
                case "1":
                    universityNodes.add(node);
                    break;
                case "2":
                    groupNodes.add(node);
                    break;
                case "3":
                    researcherNodes.add(node);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * add data into node
     *
     * @param elementNodeTag
     * @param node
     * @return
     */
    public static NodeSN addDataToNode(Element elementNodeTag, NodeSN node) {
        node.setId(elementNodeTag.getAttribute("id"));
        node.setLabel(node.getId());

        NodeList dataList = elementNodeTag.getElementsByTagName("data");
        for (int l = 0; l < dataList.getLength(); l++) {
            Element nodeData = (Element) dataList.item(l);
            switch (nodeData.getAttribute("key")) {
                case "nome":
                    node.setName(nodeData.getTextContent());
                    break;
                case "nomeCompleto":
                    node.setFullName(nodeData.getTextContent());
                    break;
                case "nomeCompletoComEnter":
                    node.setNameWithEnter(nodeData.getTextContent());
                    break;
                case "anoNaRede":
                    node.setYearInNetwork(nodeData.getTextContent());
                    break;
                case "agregador":
                    node.setAggregator(nodeData.getTextContent());
                    break;
                case "nivel":
                    node.setLevel(nodeData.getTextContent());
                    break;
                case "image":
                    node.setImage(nodeData.getTextContent());
                    break;
                case "centralidadeLocal":
                    node.setLocalCentrality(nodeData.getTextContent());
                    break;
                case "centralidadeGlobal":
                    node.setGlobalCentrality(nodeData.getTextContent());
                    break;
                case "group":
                    node.setGroup(nodeData.getTextContent());
                    break;
                case "universidade":
                    node.setUniversity(nodeData.getTextContent());
                    break;
                case "gruposUniversidade":
                    node.setUniversityGroup(nodeData.getTextContent());
                    break;
                default:
                    break;
            }
        }
        return node;
    }

    /**
     * get all elements of edge tag
     *
     * @param listOfEgdes
     */
    public static void ListEdges(NodeList listOfEgdes) {
        for (int k = 0; k < listOfEgdes.getLength(); k++) {//tag edge
            EdgeSN edge = new EdgeSN();
            Node nodeEdgeTag = listOfEgdes.item(k);
            Element elementNodeTag = (Element) nodeEdgeTag;

            edge = addDataToEdge(elementNodeTag, edge);
            switch (visualizationLevel) {
                case "1":
                    addUniversityEdge(edge);
                    break;
                case "2":
                    addGroupEdge(edge);
                    break;
                case "3":
                    if (!edgeResearcherExist(edge) && edgeBetweenRangeYear(edge,minYearEvolution,maxYearEvolution)) {
                        researcherEdges.add(edge);
                    }
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * 
     * @param edge
     * @return 
     */
    public static boolean edgeBetweenRangeYear(EdgeSN edge,int minYearEvolution, int maxYearEvolution){
        
            String edgeYear = edge.getRelationshipYear();
            Integer edgeYearInt = -1;
            if (!edgeYear.equals("")) {
                edgeYearInt = Integer.parseInt(edgeYear);
            }

        return ((edgeYearInt != -1) && (edgeYearInt >= minYearEvolution) && (edgeYearInt <= maxYearEvolution));
    }

    /**
     * search a node using a id and return the group
     *
     * @param id
     * @return
     */
    public static String searchNodeGroup(String id) {
        for (NodeSN n : researcherNodes) {
            if (n.getId().equals(id)) {
                return n.getAggregator();
            }
        }
        return "";
    }

    /**
     * search a node using a id and return the university
     *
     * @param id
     * @return
     */
    public static String searchNodeUniversity(String id) {
        for (NodeSN n : researcherNodes) {
            if (n.getId().equals(id)) {
                return n.getUniversity();
            }
        }
        return "";
    }

    /**
     * add a group edge
     *
     * @param edge
     */
    public static void addGroupEdge(EdgeSN edge) {
        String source = edge.getSource();
        String target = edge.getTarget();

        String newSource = searchNodeGroup(source);
        String newTarget = searchNodeGroup(target);

        edge.setSource(newSource);
        edge.setTarget(newTarget);

        String year = edge.getRelationshipYear();
        String relationship = edge.getRelationshipType();
        
        if (!edgeGroupExist(newSource, newTarget,year,relationship) && edgeBetweenRangeYear(edge,minYearEvolution,maxYearEvolution)) {
            groupEdges.add(edge);
        }
    }

    /**
     * add a university edge
     *
     * @param edge
     */
    public static void addUniversityEdge(EdgeSN edge) {
        String source = edge.getSource();
        String target = edge.getTarget();

        String newSource = searchNodeUniversity(source);
        String newTarget = searchNodeUniversity(target);

        //source and target need to be different
        if(newSource.equals(newTarget)){
            return;
        }
        
        edge.setSource(newSource);
        edge.setTarget(newTarget);

        if (!edgeUniversityExist(newSource, newTarget,edge.getRelationshipYear(),edge.getRelationshipType())) {
            universityEdges.add(edge);
        }
    }

    /**
     * Verify if a researcher edge exists
     * @param edge
     * @return 
     */
    public static boolean edgeResearcherExist(EdgeSN edge) {

        String source = edge.getSource();
        String target = edge.getTarget();
        String year = edge.getRelationshipYear();
        String relationship = edge.getRelationshipType();

        String sourceTarget = source + "-" + target+"-"+year+"-"+relationship;
        String sourceTarget2 = target + "-" + source+"-"+year+"-"+relationship;
        if (getResearcherEdges().isEmpty()) {
            getResearcherEdgeExistence().add(sourceTarget);
            getResearcherEdgeExistence().add(sourceTarget2);
            //System.out.println("Edge "+sourceTarget+" e "+sourceTarget2+" nao existe");
            return false;
        } else if (getResearcherEdgeExistence().contains(sourceTarget) || getResearcherEdgeExistence().contains(sourceTarget2)) {
            //System.out.println("Edge "+sourceTarget+" e "+sourceTarget2+" existe");
            return true;
        } else {
            //System.out.println("Edge "+sourceTarget+" e "+sourceTarget2+" nao existe");
            getResearcherEdgeExistence().add(sourceTarget);
            getResearcherEdgeExistence().add(sourceTarget2);
            return false;
        }
    }

    /**
     * return true if a group edge exists
     *
     * @param source
     * @param target
     * @param year
     * @param relationship
     * @return
     */
    public static boolean edgeGroupExist(String source, String target, String year,String relationship) {

        String sourceTarget = source + "-" + target+"-"+year+"-"+relationship;
        String sourceTarget2 = target + "-" + source+"-"+year+"-"+relationship;

        if (groupEdges.isEmpty()) {
            groupEdgeExistence.add(sourceTarget);
            groupEdgeExistence.add(sourceTarget2);
            return false;
        } else if (groupEdgeExistence.contains(sourceTarget) || groupEdgeExistence.contains(sourceTarget2)) {
            return true;
        } else {
            groupEdgeExistence.add(sourceTarget);
            groupEdgeExistence.add(sourceTarget2);
            return false;
        }
    }

    /**
     * return true if a university-edge exists
     *
     * @param source
     * @param target
     * @param year
     * @param relationship
     * @return
     */
    public static boolean edgeUniversityExist(String source, String target,String year,String relationship) {

        String sourceTarget = source + "-" + target+"-"+year+"-"+relationship;
        String sourceTarget2 = target + "-" + source+"-"+year+"-"+relationship;

        if (universityEdges.isEmpty()) {
            universityEdgeExistence.add(sourceTarget);
            universityEdgeExistence.add(sourceTarget2);
            return false;
        } else if (universityEdgeExistence.contains(sourceTarget) || universityEdgeExistence.contains(sourceTarget2)) {
            return true;
        } else {
            universityEdgeExistence.add(sourceTarget);
            universityEdgeExistence.add(sourceTarget2);
            return false;
        }
    }

    /**
     * add data to an edge based on xml file
     *
     * @param elementNodeTag
     * @param edge
     * @return
     */
    public static EdgeSN addDataToEdge(Element elementNodeTag, EdgeSN edge) {

        edge.setSource(elementNodeTag.getAttribute("source"));
        edge.setTarget(elementNodeTag.getAttribute("target"));

        NodeList dataList = elementNodeTag.getElementsByTagName("data");
        for (int l = 0; l < dataList.getLength(); l++) {
            Element nodeData = (Element) dataList.item(l);
            switch (nodeData.getAttribute("key")) {
                case "weight":
                    edge.setWeight(nodeData.getTextContent());
                    break;
                case "anoRelacionamento":
                    edge.setRelationshipYear(nodeData.getTextContent());
                    break;
                case "tipoRelacionamento":
                    edge.setRelationshipType(nodeData.getTextContent());
                    break;
                default:
                    break;
            }
        }
        return edge;
    }

//    public static void main(String argv[]) {
//        readXMLBuildGraph();
//    }
    /**
     * @return the FILEPATH
     */
    public static String getFILEPATH() {
        return FILEPATH;
    }

    /**
     * @param aFILEPATH the FILEPATH to set
     */
    public static void setFILEPATH(String aFILEPATH) {
        FILEPATH = aFILEPATH;
    }

    /**
     * @return the graph
     */
    public static GraphSN getGraph() {
        return graph;
    }

    /**
     * @param aGraph the graph to set
     */
    public static void setGraph(GraphSN aGraph) {
        graph = aGraph;
    }

    /**
     * @return the researcherNodes
     */
    public static List<NodeSN> getResearcherNodes() {
        return researcherNodes;
    }

    /**
     * @param aResearcherNodes the researcherNodes to set
     */
    public static void setResearcherNodes(List<NodeSN> aResearcherNodes) {
        researcherNodes = aResearcherNodes;
    }

    /**
     * @return the groupNodes
     */
    public static List<NodeSN> getGroupNodes() {
        return groupNodes;
    }

    /**
     * @param aGroupNodes the groupNodes to set
     */
    public static void setGroupNodes(List<NodeSN> aGroupNodes) {
        groupNodes = aGroupNodes;
    }

    /**
     * @return the universityNodes
     */
    public static List<NodeSN> getUniversityNodes() {
        return universityNodes;
    }

    /**
     * @param aUniversityNodes the universityNodes to set
     */
    public static void setUniversityNodes(List<NodeSN> aUniversityNodes) {
        universityNodes = aUniversityNodes;
    }

    /**
     * @return the researcherEdges
     */
    public static List<EdgeSN> getResearcherEdges() {
        return researcherEdges;
    }

    /**
     * @param aResearcherEdges the researcherEdges to set
     */
    public static void setResearcherEdges(List<EdgeSN> aResearcherEdges) {
        researcherEdges = aResearcherEdges;
    }

    /**
     * @return the groupEdges
     */
    public static List<EdgeSN> getGroupEdges() {
        return groupEdges;
    }

    /**
     * @param aGroupEdges the groupEdges to set
     */
    public static void setGroupEdges(List<EdgeSN> aGroupEdges) {
        groupEdges = aGroupEdges;
    }

    /**
     * @return the universityEdges
     */
    public static List<EdgeSN> getUniversityEdges() {
        return universityEdges;
    }

    /**
     * @param aUniversityEdges the universityEdges to set
     */
    public static void setUniversityEdges(List<EdgeSN> aUniversityEdges) {
        universityEdges = aUniversityEdges;
    }

    /**
     * @return the researcherEdgeExistence
     */
    public static List<String> getResearcherEdgeExistence() {
        return researcherEdgeExistence;
    }

    /**
     * @param aResearcherEdgeExistence the researcherEdgeExistence to set
     */
    public static void setResearcherEdgeExistence(List<String> aResearcherEdgeExistence) {
        researcherEdgeExistence = aResearcherEdgeExistence;
    }

    /**
     * @return the groupEdgeExistence
     */
    public static List<String> getGroupEdgeExistence() {
        return groupEdgeExistence;
    }

    /**
     * @param aGroupEdgeExistence the groupEdgeExistence to set
     */
    public static void setGroupEdgeExistence(List<String> aGroupEdgeExistence) {
        groupEdgeExistence = aGroupEdgeExistence;
    }

    /**
     * @return the universityEdgeExistence
     */
    public static List<String> getUniversityEdgeExistence() {
        return universityEdgeExistence;
    }

    /**
     * @param aUniversityEdgeExistence the universityEdgeExistence to set
     */
    public static void setUniversityEdgeExistence(List<String> aUniversityEdgeExistence) {
        universityEdgeExistence = aUniversityEdgeExistence;
    }

    /**
     * @return the visualizationLevel
     */
    public String getVisualizationLevel() {
        return visualizationLevel;
    }

    /**
     * @param visualizationLevel the visualizationLevel to set
     */
    public void setVisualizationLevel(String visualizationLevel) {
        this.visualizationLevel = visualizationLevel;
    }
}

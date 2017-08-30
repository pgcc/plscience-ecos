/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.socialNetworkAnalysis;

import br.ufjf.pgcc.plscience.dao.NodeDAO;
import br.ufjf.pgcc.plscience.dao.RelationshipEdgeDAO;
import br.ufjf.pgcc.plscience.dao.UniversityDAO;
import br.ufjf.pgcc.plscience.dao.UniversityGroupDAO;
import br.ufjf.pgcc.plscience.model.NodeBD;
import br.ufjf.pgcc.plscience.model.RelationshipEdge;
import br.ufjf.pgcc.plscience.model.University;
import br.ufjf.pgcc.plscience.model.UniversityGroup;
import static br.ufjf.pgcc.plscience.socialNetworkAnalysis.SNFileManager.setGroupNodesWithoutConnections;
import static br.ufjf.pgcc.plscience.socialNetworkAnalysis.SNFileManager.setResearcherNodesWithoutConnections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phillipe
 */
public class GraphSN {

    private List<EdgeSN> researcherEdges;
    private List<EdgeSN> groupEdges;
    private List<EdgeSN> universityEdges;
    private List<NodeSN> researcherNodes;
    private List<NodeSN> groupNodes;
    private List<NodeSN> universityNodes;
    private Map<String, String> universityColor;
    private Map<String, NodeSNCoordinates> universityCoordinates;

    public GraphSN() {
        researcherEdges = new ArrayList<>();
        researcherNodes = new ArrayList<>();
        universityColor = new HashMap<>();
        universityCoordinates = new HashMap<>();
    }

    /**
     * build a new graph using datas from database
     *
     * @param level
     * @param minYear
     * @param maxYear
     * @return
     */
    public static GraphSN buildGraphUsingDatabase(String level, int minYear, int maxYear) {
        GraphSN graph = new GraphSN();
        List<NodeSN> researcherNodes = new ArrayList<>();
        List<NodeSN> groupNodes = new ArrayList<>();
        List<NodeSN> universityNodes = new ArrayList<>();
        List<EdgeSN> researcherEdges = new ArrayList<>();
        List<EdgeSN> groupEdges = new ArrayList<>();
        List<EdgeSN> universityEdges = new ArrayList<>();
        List<String> researcherEdgeExistence = new ArrayList<>();
        List<String> groupEdgeExistence = new ArrayList<>();
        List<String> universityEdgeExistence = new ArrayList<>();

        //Iniciando leitura dos nos
        NodeDAO nodeDAO = new NodeDAO();
        List<NodeBD> nodesList = nodeDAO.getAll();

        for (NodeBD nodebd : nodesList) {
            NodeSN nodesn = GraphSN.convertNodeBDInNodeSN(nodebd);

            if (nodebd.getLevel().equals("3")) {
                researcherNodes.add(nodesn);
            } else if (nodebd.getLevel().equals("2")) {
                groupNodes.add(nodesn);
            } else if (nodebd.getLevel().equals("1")) {
                universityNodes.add(nodesn);
            }
        }

        //iniciando leitura das arestas
        RelationshipEdgeDAO eDAO = new RelationshipEdgeDAO();
        List<RelationshipEdge> edgesList = eDAO.getAll();

        for (RelationshipEdge e : edgesList) {
            EdgeSN edge = new EdgeSN();
            edge.setSource(e.getNodeSource().getIdNetwork());
            edge.setTarget(e.getNodeTarget().getIdNetwork());
            edge.setWeight(e.getWeight());
            edge.setRelationshipType(e.getRelationshipType());
            edge.setRelationshipYear(e.getRelationshipYear());

            if (level.equals("3")) {
                if (!graph.edgeResearcherExist(edge, researcherEdgeExistence) && SNFileManager.edgeBetweenRangeYear(edge, minYear, maxYear)) {
                    researcherEdges.add(edge);
                }
            } else if (level.equals("2")) {
                groupEdges = EdgeSN.addGroupEdge(edge, researcherNodes, groupEdges,
                        minYear, maxYear, groupEdgeExistence);
            } else if (level.equals("1")) {
                universityEdges = EdgeSN.addUniversityEdge(edge, researcherNodes, universityEdges, minYear, maxYear, universityEdgeExistence);
            }
        }

        if (level.equals("3")) {
            researcherNodes = setResearcherNodesWithoutConnections(researcherNodes, researcherEdges);
        } else if (level.equals("2")) {
            groupNodes = setGroupNodesWithoutConnections(groupNodes, groupEdges);
        }

        graph.setResearcherNodes(researcherNodes);
        graph.setGroupNodes(groupNodes);
        graph.setUniversityNodes(universityNodes);
        graph.setResearcherEdges(researcherEdges);
        graph.setGroupEdges(groupEdges);
        graph.setUniversityEdges(universityEdges);

        return graph;
    }

    /**
     * convert a NodeBD in a NodeSN
     *
     * @param nodebd
     * @return
     */
    public static NodeSN convertNodeBDInNodeSN(NodeBD nodebd) {
        NodeSN nodesn = new NodeSN();
        if (nodebd != null) {

            if (nodebd.getIdNetwork() != null) {
                nodesn.setId(nodebd.getIdNetwork());
            }

            String groupName = "";

            if (nodebd.getUniversityGroupId() != null) {
                if (nodebd.getUniversityGroupId().getGroupName() != null) {
                    groupName = nodebd.getUniversityGroupId().getGroupName();
                }
            }

            if (!groupName.isEmpty() && groupName.contains(";")) {
                String[] text;
                text = groupName.split(";");
                if (text.length > 1) {
                    nodesn.setName(nodebd.getIdNetwork() + " (" + text[1] + ")");
                    nodesn.setGroup(text[1]);
                }
            }

            if (nodebd.getFullName() != null) {
                nodesn.setFullName(nodebd.getFullName());
            }
            
            if (nodebd.getYearInNetwork() != null) {
                nodesn.setYearInNetwork(nodebd.getYearInNetwork());
            }

            nodesn.setAggregator(groupName);
            nodesn.setLevel(nodebd.getLevel());
            if (nodebd.getImage() != null) {
                nodesn.setImage(nodebd.getImage());
            }

            if (nodesn.getLocalCentrality() != null) {
                nodesn.setLocalCentrality(nodebd.getLocalCentrality());
            }

            if (nodesn.getGlobalCentrality() != null) {
                nodesn.setGlobalCentrality(nodebd.getGlobalCentrality());
            }

            if (nodebd.getUniversityId() != null && nodebd.getUniversityId().getUniversityAbbreviationName() != null) {
                nodesn.setUniversity(nodebd.getUniversityId().getUniversityAbbreviationName());
            }

            if (nodebd.getUniversityGroupId() != null && nodebd.getUniversityGroupId().getGroupName() != null) {
                nodesn.setUniversityGroup(nodebd.getUniversityGroupId().getGroupName());
            }

            //String urlImageLocal = "images/social/default.png";
            String urlImageLocal = "https://c5.rgstatic.net/m/437738464651637/images/template/default/profile/profile_default_l.jpg";
            
            if(nodebd.getHasPictureFile() != null){
                //String idNetwork = nodebd.getIdNetwork();
                //if(idNetwork != null && !idNetwork.equals("")){
                    
                    //use local file
                    //urlImageLocal = "images/social/"+idNetwork+".png";
                    
                    //use lattes service (it never works!)
                    urlImageLocal = nodebd.getImage(); 
                //}                
            }
            nodesn.setLocalImageURL(urlImageLocal);
        }
        return nodesn;
    }

    /**
     * verify edge existence
     *
     * @param edge
     * @param edgeExistence
     * @return
     */
    public boolean edgeResearcherExist(EdgeSN edge, List edgeExistence) {

        String source = edge.getSource();
        String target = edge.getTarget();
        String year = edge.getRelationshipYear();
        String relationship = edge.getRelationshipType();

        String sourceTarget = source + "-" + target + "-" + year + "-" + relationship;
        String sourceTarget2 = target + "-" + source + "-" + year + "-" + relationship;
        if (researcherEdges.isEmpty()) {
            edgeExistence.add(sourceTarget);
            edgeExistence.add(sourceTarget2);
            //System.out.println("Edge "+sourceTarget+" e "+sourceTarget2+" nao existe");
            return false;
        } else if (edgeExistence.contains(sourceTarget) || edgeExistence.contains(sourceTarget2)) {
            //System.out.println("Edge "+sourceTarget+" e "+sourceTarget2+" existe");
            return true;
        } else {
            //System.out.println("Edge "+sourceTarget+" e "+sourceTarget2+" nao existe");
            edgeExistence.add(sourceTarget);
            edgeExistence.add(sourceTarget2);
            return false;
        }
    }

    /**
     * Save the graph data to a database
     *
     * @param graph
     */
    public static void graphToDatabase(GraphSN graph) {
        if (graph == null) {
            System.out.println("Graph is null");
            return;
        }
        if (graph.getResearcherNodes() == null || graph.getResearcherNodes().isEmpty()) {
            System.out.println("The graph does not contain nodes");
            return;
        }

        //university data
        for (NodeSN node : graph.getUniversityNodes()) {
            University u = new University();
            UniversityDAO univDAO = new UniversityDAO();
            u.setUniversityAbbreviationName(node.getUniversity());
            System.out.println("Adding university: " + u.getUniversityAbbreviationName());
            univDAO.save(u);
        }

        //group data
        for (NodeSN node : graph.getGroupNodes()) {
            UniversityGroup ug = new UniversityGroup();
            UniversityGroupDAO ugDAO = new UniversityGroupDAO();
            ug.setGroupName(node.getUniversityGroup());
            System.out.println("Adding university group: " + ug.getGroupName());
            ugDAO.save(ug);
        }

        //researcher nodes data
        for (NodeSN node : graph.getResearcherNodes()) {
            addNewNodeBD(node);
        }

        //groups nodes data
        for (NodeSN node : graph.getGroupNodes()) {
            addNewNodeBD(node);
        }

        //universities nodes data
        for (NodeSN node : graph.getUniversityNodes()) {
            addNewNodeBD(node);
        }

        //adding edges
        for (EdgeSN edge : graph.getResearcherEdges()) {
            RelationshipEdge re = new RelationshipEdge();
            RelationshipEdgeDAO reDAO = new RelationshipEdgeDAO();
            NodeDAO nDAO = new NodeDAO();
            re.setRelationshipType(edge.getRelationshipType());
            re.setRelationshipYear(edge.getRelationshipYear());
            re.setWeight(edge.getWeight());

//            Long nodeIdSource = Long.parseLong(edge.getSource());
//            Long nodeIdTarget = Long.parseLong(edge.getTarget());
//            NodeBD source = nDAO.getNodeById(nodeIdSource);
//            NodeBD target = nDAO.getNodeById(nodeIdTarget);
            System.out.println("Source vale " + edge.getSource());
            System.out.println("Target vale " + edge.getTarget());

            NodeBD source = nDAO.getNodeByIdNetwork(edge.getSource());
            NodeBD target = nDAO.getNodeByIdNetwork(edge.getTarget());

            re.setNodeSource(source);
            re.setNodeTarget(target);

            System.out.println("Adding edge :" + re.getNodeSource().getId() + " - " + re.getNodeTarget().getId());
            reDAO.save(re);
        }
    }

    public static void addNewNodeBD(NodeSN node) {
        NodeBD n = new NodeBD();
        NodeDAO nDAO = new NodeDAO();
        //Integer idNetwork = Integer.parseInt(node.getId());//case int
        //n.setId(node.getId());
        n.setIdNetwork(node.getId());
        n.setFullName(node.getFullName());
        n.setYearInNetwork(node.getYearInNetwork());
        n.setLevel(node.getLevel());
        n.setImage(node.getImage());
        n.setLocalCentrality(node.getLocalCentrality());
        n.setGlobalCentrality(node.getGlobalCentrality());

        University u;
        UniversityDAO uDAO = new UniversityDAO();
        u = uDAO.getUniversityByAbbName(node.getUniversity());
        if (u != null) {
            n.setUniversityId(u);
        }

        UniversityGroup ug;
        UniversityGroupDAO ugDAO = new UniversityGroupDAO();
        ug = ugDAO.getUniversityGroupByName(node.getAggregator());
        if (ug != null) {
            n.setUniversityGroupId(ug);
        }

        System.out.println("Adding node: " + n.getFullName());
        nDAO.save(n);
    }

    /**
     * it returns the number of universities
     *
     * @param graph
     * @return
     */
    public int numberOfUniversities(GraphSN graph) {
        return graph.getUniversityNodes().size();
    }

    public GraphSN setNodesClusters(int numberOfUniversities, GraphSN graph) {
        if (graph.universityNodes != null && graph.universityNodes.size() == 1) {
            NodeSNCoordinates nC = new NodeSNCoordinates();
            nC.setYmin(0);
            nC.setYmax(100);
            nC.setxMin(0);
            nC.setxMax(100);
            String universityName = graph.universityNodes.get(0).getUniversity();
            graph.universityCoordinates.put(universityName, nC);
        } else if (graph.universityNodes != null && graph.universityNodes.size() == 2) {
            graph = setClustersTwoNodes(graph);
        } else if (graph.universityNodes != null && graph.universityNodes.size() == 3) {
            graph = setClustersThreeNodes(graph);
        } else if (graph.universityNodes != null && graph.universityNodes.size() == 4) {
            graph = setClustersFourNodes(graph);
        } else if (graph.universityNodes != null && graph.universityNodes.size() == 5) {
            graph = setClustersFiveNodes(graph);
        } else if (graph.universityNodes != null && graph.universityNodes.size() == 6) {
            graph = setClustersSixNodes(graph);
        }
        return graph;
    }

    /**
     * set the cluster positions to 2 universities
     *
     * @param graph
     * @return
     */
    public GraphSN setClustersTwoNodes(GraphSN graph) {
        int min = 0;
        int max = 50;
        for (NodeSN node : graph.universityNodes) {
            NodeSNCoordinates nC = new NodeSNCoordinates();
            nC.setYmin(0);
            nC.setYmax(50);
            nC.setxMin(min);
            nC.setxMax(max);
            min += 50;
            max += 50;
            String universityName = node.getUniversity();
            graph.universityCoordinates.put(universityName, nC);
        }
        return graph;
    }

    /**
     * set the cluster positions to 3 universities
     *
     * @param graph
     * @return
     */
    public GraphSN setClustersThreeNodes(GraphSN graph) {
        int min = 0;
        int max = 33;
        for (NodeSN node : graph.universityNodes) {
            NodeSNCoordinates nC = new NodeSNCoordinates();
            nC.setYmin(0);
            nC.setYmax(33);
            nC.setxMin(min);
            nC.setxMax(max);
            min += 34;
            max += 33;
            String universityName = node.getUniversity();
            graph.universityCoordinates.put(universityName, nC);
        }
        return graph;
    }

    /**
     * set the cluster positions to 4 universities
     *
     * @param graph
     * @return
     */
    public GraphSN setClustersFourNodes(GraphSN graph) {
        for (int i = 0; i < graph.universityNodes.size(); i++) {
            switch (i) {
                case 0: {
                    NodeSNCoordinates nC = new NodeSNCoordinates();
                    nC.setYmin(0);
                    nC.setYmax(50);
                    nC.setxMin(0);
                    nC.setxMax(50);
                    String universityName = graph.universityNodes.get(0).getUniversity();
                    graph.universityCoordinates.put(universityName, nC);
                    break;
                }
                case 1: {
                    NodeSNCoordinates nC = new NodeSNCoordinates();
                    nC.setYmin(0);
                    nC.setYmax(50);
                    nC.setxMin(51);
                    nC.setxMax(100);
                    String universityName = graph.universityNodes.get(0).getUniversity();
                    graph.universityCoordinates.put(universityName, nC);
                    break;
                }
                case 2: {
                    NodeSNCoordinates nC = new NodeSNCoordinates();
                    nC.setYmin(51);
                    nC.setYmax(100);
                    nC.setxMin(0);
                    nC.setxMax(50);
                    String universityName = graph.universityNodes.get(0).getUniversity();
                    graph.universityCoordinates.put(universityName, nC);
                    break;
                }
                case 3: {
                    NodeSNCoordinates nC = new NodeSNCoordinates();
                    nC.setYmin(51);
                    nC.setYmax(100);
                    nC.setxMin(51);
                    nC.setxMax(100);
                    String universityName = graph.universityNodes.get(0).getUniversity();
                    graph.universityCoordinates.put(universityName, nC);
                    break;
                }
                default:
                    break;
            }
        }
        return graph;
    }

    /**
     * set the cluster positions to 5 universities
     *
     * @param graph
     * @return
     */
    public GraphSN setClustersFiveNodes(GraphSN graph) {
        int min = 0;
        int max = 33;
        for (int i = 0; i < graph.getUniversityNodes().size(); i++) {
            if (i < 3) {
                NodeSNCoordinates nC = new NodeSNCoordinates();
                nC.setYmin(min);
                nC.setYmax(max);
                nC.setxMin(min);
                nC.setxMax(max);
                min += 34;
                max += 33;
                String universityName = graph.universityNodes.get(i).getUniversity();
                graph.universityCoordinates.put(universityName, nC);
            }
            if (i == 3) {
                NodeSNCoordinates nC = new NodeSNCoordinates();
                nC.setYmin(0);
                nC.setYmax(33);
                nC.setxMin(67);
                nC.setxMax(100);
                String universityName = graph.universityNodes.get(i).getUniversity();
                graph.universityCoordinates.put(universityName, nC);
            }
            if (i == 4) {
                NodeSNCoordinates nC = new NodeSNCoordinates();
                nC.setYmin(67);
                nC.setYmax(100);
                nC.setxMin(0);
                nC.setxMax(33);
                String universityName = graph.universityNodes.get(i).getUniversity();
                graph.universityCoordinates.put(universityName, nC);
            }
        }
        return graph;
    }

    /**
     * set clusters to six clusters
     *
     * @param graph
     * @return
     */
    public GraphSN setClustersSixNodes(GraphSN graph) {

        int min = 0;
        int max = 33;
        for (int i = 0; i < graph.getUniversityNodes().size(); i++) {
            if (i < 3) {
                NodeSNCoordinates nC = new NodeSNCoordinates();
                nC.setYmin(0);
                nC.setYmax(50);
                nC.setxMin(min);
                nC.setxMax(max);
                min += 34;
                max += 33;
                String universityName = graph.universityNodes.get(i).getUniversity();
                graph.universityCoordinates.put(universityName, nC);
            }
            if (i == 3) {
                min = 0;
                max = 33;
            }
            if (i >= 3) {
                NodeSNCoordinates nC = new NodeSNCoordinates();
                nC.setYmin(51);
                nC.setYmax(100);
                nC.setxMin(min);
                nC.setxMax(max);
                min += 34;
                max += 33;
                String universityName = graph.universityNodes.get(i).getUniversity();
                graph.universityCoordinates.put(universityName, nC);
            }
        }

        return graph;
    }

    public String getColor(String university) {
        String color = "";
        return color;
    }

    /**
     * @return the universityColor
     */
    public Map<String, String> getUniversityColor() {
        return universityColor;
    }

    /**
     * @param universityColor the universityColor to set
     */
    public void setUniversityColor(Map<String, String> universityColor) {
        this.universityColor = universityColor;
    }

    /**
     * @return the researcherNodes
     */
    public List<NodeSN> getResearcherNodes() {
        return researcherNodes;
    }

    /**
     * @param researcherNodes the researcherNodes to set
     */
    public void setResearcherNodes(List<NodeSN> researcherNodes) {
        this.researcherNodes = researcherNodes;
    }

    /**
     * @return the groupNodes
     */
    public List<NodeSN> getGroupNodes() {
        return groupNodes;
    }

    /**
     * @param groupNodes the groupNodes to set
     */
    public void setGroupNodes(List<NodeSN> groupNodes) {
        this.groupNodes = groupNodes;
    }

    /**
     * @return the universityNodes
     */
    public List<NodeSN> getUniversityNodes() {
        return universityNodes;
    }

    /**
     * @param universityNodes the universityNodes to set
     */
    public void setUniversityNodes(List<NodeSN> universityNodes) {
        this.universityNodes = universityNodes;
    }

    /**
     * @return the researcherEdges
     */
    public List<EdgeSN> getResearcherEdges() {
        return researcherEdges;
    }

    /**
     * @param researcherEdges the researcherEdges to set
     */
    public void setResearcherEdges(List<EdgeSN> researcherEdges) {
        this.researcherEdges = researcherEdges;
    }

    /**
     * @return the groupEdges
     */
    public List<EdgeSN> getGroupEdges() {
        return groupEdges;
    }

    /**
     * @param groupEdges the groupEdges to set
     */
    public void setGroupEdges(List<EdgeSN> groupEdges) {
        this.groupEdges = groupEdges;
    }

    /**
     * @return the universityEdges
     */
    public List<EdgeSN> getUniversityEdges() {
        return universityEdges;
    }

    /**
     * @param universityEdges the universityEdges to set
     */
    public void setUniversityEdges(List<EdgeSN> universityEdges) {
        this.universityEdges = universityEdges;
    }

    /**
     * @return the universityCoordinates
     */
    public Map<String, NodeSNCoordinates> getUniversityCoordinates() {
        return universityCoordinates;
    }

    /**
     * @param universityCoordinates the universityCoordinates to set
     */
    public void setUniversityCoordinates(Map<String, NodeSNCoordinates> universityCoordinates) {
        this.universityCoordinates = universityCoordinates;
    }
}

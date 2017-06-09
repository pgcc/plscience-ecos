/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.socialNetworkAnalysis;

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
        }
        else if (graph.universityNodes != null && graph.universityNodes.size() == 5) {
            graph = setClustersFiveNodes(graph);
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
            if(i == 3){
                NodeSNCoordinates nC = new NodeSNCoordinates();
                nC.setYmin(0);
                nC.setYmax(33);
                nC.setxMin(67);
                nC.setxMax(100);
                String universityName = graph.universityNodes.get(i).getUniversity();
                graph.universityCoordinates.put(universityName, nC);                
            }
            if(i == 4){
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

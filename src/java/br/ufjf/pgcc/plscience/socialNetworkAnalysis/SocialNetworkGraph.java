/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.socialNetworkAnalysis;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SlideEndEvent;

/**
 *
 * @author phillipe
 */
@ManagedBean()
@ViewScoped
public class SocialNetworkGraph implements Serializable {

    private String[] selectedRelationships = {"bib", "gui", "pro", "tec"};
    private String visualizationLevel;
    private int minYearEvolution = 1950;
    private int maxYearEvolution = 2017;
    private boolean forceLink;
    private boolean showWeight;

    public SocialNetworkGraph() {
        visualizationLevel = "3";
        forceLink = false;
        showWeight = false;
    }

    public boolean edgeRelationshipTypeSelected(EdgeSN e) {
        for (String relatType : selectedRelationships) {
            if (e.getRelationshipType().contains("PROD") && relatType.equals("bib")) {
                return true;
            } else if (e.getRelationshipType().contains("ORIE") && relatType.equals("gui")) {
                return true;
            } else if (e.getRelationshipType().contains("PROJ") && relatType.equals("pro")) {
                return true;
            } else if (e.getRelationshipType().contains("TECN") && relatType.equals("tec")) {
                return true;
            }
        }
        return false;
    }

    /**
     * it generates a graph
     */
    public void generateGraph() {
        String script;

        //building the graph
        GraphSN graph = SNFileManager.readXMLBuildGraph(visualizationLevel, minYearEvolution, maxYearEvolution);

        //generation Stript
        script = linkuriousScriptGenerator(graph);
        if (!script.equals("")) {
            RequestContext.getCurrentInstance().execute(script);
        }
    }

    /**
     * it generates a script based in a graph
     *
     * @param graph
     * @return
     */
    public String linkuriousScriptGenerator(GraphSN graph) {

        int numberOfUniversities = graph.numberOfUniversities(graph);
        graph = graph.setNodesClusters(numberOfUniversities,graph);
        
        System.out.println("Generating Script");

        String script = "var s1,"
                + "g1 = {nodes: [],edges: []};\n"
                + "g1.nodes.push(\n";

        List<NodeSN> allNodes = getNodesByVisualizationLevel(graph);
        for (NodeSN n : allNodes) {

            //setting local centrality and global centrality with only two decimal places
            String newLCentrality = "";
            String newGCentrality = "";

            DecimalFormat df = new DecimalFormat("0.##");
            if (!n.getLocalCentrality().equals("")) {
                Double w = Double.parseDouble(n.getLocalCentrality());
                newLCentrality = df.format(w);
            }

            if (!n.getGlobalCentrality().equals("")) {
                Double w = Double.parseDouble(n.getGlobalCentrality());
                newGCentrality = df.format(w);
            }

            if (graph.getUniversityColor().isEmpty() || !(graph.getUniversityColor().containsKey(n.getUniversity()))) {
                String color;
                color = NodeSN.randomHexadecimalColor(NodeSN.randomColor());
                String university = n.getUniversity();
                graph.getUniversityColor().put(university, color);
            }

            if (!n.getUniversity().equals("")) {
                n.setColor(graph.getUniversityColor().get(n.getUniversity()));
            }
            
            if(numberOfUniversities < 6){
                NodeSNCoordinates ncoordinates = graph.getUniversityCoordinates().get(n.getUniversity());
                int xMin = ncoordinates.getxMin();
                int xMax = ncoordinates.getxMax();
                int yMin = ncoordinates.getYmin();
                int yMax = ncoordinates.getYmax();

                Integer x = NodeSN.randomValueRange(xMin, xMax);
                Integer y = NodeSN.randomValueRange(yMin, yMax);
                
                n.setX(x.toString());
                n.setY(y.toString());
                
            }else{
                forceLink = true;
            }

            if (!n.isWithoutConnection() || visualizationLevel.equals("1")) {
                //level 1 contains nodes without connections
                script += "\n{";
                script += "      id: '" + n.getId() + "',\n";
                script += "      label: '" + n.getLabel() + "',\n";
                script += "      x: "+n.getX()+",\n"
                        + "      y: "+n.getY()+",\n";
                script += "      size: " + n.getSize() + ",\n";
                script += "      color: '" + n.getColor() + "',\n";
                script += "      data: {\n";
                script += "        name: '" + n.getName() + "',\n";
                script += "        fullName: '" + n.getFullName() + "',\n";
                script += "        yearNetwork: '" + n.getYearInNetwork() + "',\n";
                script += "        aggregator: '" + n.getAggregator() + "',\n";
                script += "        level: '" + n.getLevel() + "',\n";
                script += "        image: '" + n.getImage() + "',\n";
                script += "        localCentrality: '" + newLCentrality + "',\n";
                script += "        globalCentrality: '" + newGCentrality + "',\n";
                script += "        group: '" + n.getGroup() + "',\n";
                script += "        university: '" + n.getUniversity() + "',\n";
                script += "        universityGroup: '" + n.getUniversityGroup() + "'\n";
                script += "      }\n";
                script += "},";
            }
        }

        script = script.substring(0, script.length() - 1);//remove last commas
        script += "\n);\n";

        script += "g1.edges.push(";
        int edgeId = 0;

        List<EdgeSN> allEdges = getEdgesByVisualizationLevel(graph);
        for (EdgeSN edge : allEdges) {

            //setting weight with only two decimal places
            DecimalFormat df = new DecimalFormat("0.##");
            Double w = Double.parseDouble(edge.getWeight());
            String newWeight = df.format(w);

            String label = edge.getRelationshipType() + " - " + edge.getRelationshipYear()
                    + " - " + newWeight;
            if (visualizationLevel.equals("1")) {
                edge.setLabel(label);
            } else if (isShowWeight()) {
                edge.setLabel(newWeight);
            } else {
                edge.setLabel("");
            }

            String edgeYear = edge.getRelationshipYear();
            Integer edgeYearInt = -1;
            if (!edgeYear.equals("")) {
                edgeYearInt = Integer.parseInt(edgeYear);
            }

            //setting edge color
            if (!edge.getRelationshipType().equals("")) {
                String newColor = EdgeSN.getEdgeColorByRelationShipType(edge.getRelationshipType());
                edge.setColor(newColor);
            }
            
            //setting edge weight
            if(!edge.getWeight().equals("")){
                String size = EdgeSN.getEdgeSizeByWeight(edge.getWeight());
                edge.setSize(size);
            }

            if ((edgeYearInt != -1) && (edgeYearInt >= minYearEvolution) && (edgeYearInt <= maxYearEvolution)
                    && !(edge.getSource().equals(edge.getTarget())) && (edgeRelationshipTypeSelected(edge))) {
                script += "\n{\n";
                script += "      id: 'e" + edgeId + "',\n";
                script += "      label: '" + edge.getLabel() + "',\n";
                script += "      source: '" + edge.getSource() + "',\n";
                script += "      target: '" + edge.getTarget() + "',\n";
                script += "      color: '" + edge.getColor() + "',\n";
                script += "      hover_color: '" + edge.getHoverColor() + "',\n";
                script += "      type: '" + edge.getType() + "',\n";
                script += "      size: " + edge.getSize() + "\n";
                edgeId++;
                script += "},";
            }
        }

        script = script.substring(0, script.length() - 1);//remove last commas
        script += "\n);\n";

        String nodesSize = getNodesSizeToAVisLevel(visualizationLevel);

        script += renderizeScript(nodesSize);

        System.out.println("Printing Script");
        System.out.println(script);
        return script;
    }

    /**
     * return the size of nodes to a visualization level
     *
     * @param levelVis
     * @return
     */
    public static String getNodesSizeToAVisLevel(String levelVis) {
        String nodesSize = "5";
        switch (levelVis) {
            case "1":
                nodesSize = "20";
                break;
            case "2":
                nodesSize = "10";
                break;
            case "3":
                nodesSize = "5";
                break;
            default:
                break;
        }
        return nodesSize;
    }

    /**
     * return all nodes to a visualizationLevel
     *
     * @param graph
     * @return
     */
    public List<NodeSN> getNodesByVisualizationLevel(GraphSN graph) {
        List<NodeSN> allNodes = graph.getResearcherNodes();
        switch (visualizationLevel) {
            case "1":
                allNodes = graph.getUniversityNodes();
                break;
            case "2":
                allNodes = graph.getGroupNodes();
                break;
            case "3":
                allNodes = graph.getResearcherNodes();
                break;
            default:
                break;
        }
        return allNodes;
    }

    /**
     * get edges by visualization level
     *
     * @param graph
     * @return
     */
    public List<EdgeSN> getEdgesByVisualizationLevel(GraphSN graph) {
        List<EdgeSN> allEdges = graph.getResearcherEdges();
        switch (visualizationLevel) {
            case "1":
                allEdges = graph.getUniversityEdges();
                break;
            case "2":
                allEdges = graph.getGroupEdges();
                break;
            case "3":
                allEdges = graph.getResearcherEdges();
                break;
            default:
                break;
        }
        return allEdges;
    }

    public String renderizeScript(String nodesSize) {
        String rend = "sigma.renderers.def = sigma.renderers.canvas;\n"
                + "\n"
                + "// Instantiate sigma:\n"
                + "s1 = new sigma({\n"
                + "  graph: g1,\n"
                + "  renderer: {\n"
                + "    container: document.getElementById('graph-container2'),\n"
                + "    type: 'canvas'\n"
                + "  },\n"
                + "  settings: {\n"
                + "    minNodeSize: " + nodesSize + ",\n"
                + "    maxNodeSize: " + nodesSize + ",\n"
                + "    minEdgeSize: 1,\n"
                + "    maxEdgeSize: 5,\n"
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
                + "s1.refresh();\n"
                + "\n"
                + "\n"
                + "// Instanciate the ActiveState plugin:\n"
                + "var activeState = sigma.plugins.activeState(s1);\n"
                + "\n"
                + "// Initialize the dragNodes plugin:\n"
                + "var dragListener = sigma.plugins.dragNodes(s1, s1.renderers[0], activeState);\n"
                + "\n"
                + "// Initialize the Select plugin:\n"
                + "var select = sigma.plugins.select(s1, activeState);\n"
                + "\n"
                + "// Initialize the Keyboard plugin:\n"
                + "var keyboard = sigma.plugins.keyboard(s1, s1.renderers[0]);\n"
                + "\n"
                + "var config = {\n"
                + "  node: [{\n"
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
                + "    '      <tr><th>Full Name</th> <td>{{data.fullName}}</td></tr>' +\n"
                + "    '      <tr><th>Year (Network)</th> <td>{{data.yearNetwork}}</td></tr>' +\n"
                + "	'      <tr><th>Local Centrality</th> <td>{{data.localCentrality}}</td></tr>' +\n"
                + "	'      <tr><th>Global Centrality</th> <td>{{data.globalCentrality}}</td></tr>' +	\n"
                + "	'      <tr><th>University</th> <td>{{data.university}}</td></tr>' +\n"
                + "    '    </table>' +\n"
                + "    '  </div>' +\n"
                + "    '  <div class=\"sigma-tooltip-footer\">Number of connections: {{degree}}</div>',\n"
                + "    renderer: function(node, template) {\n"
                + "      // The function context is s1.graph\n"
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
                + "    '  <div class=\"sigma-tooltip-body\">' +\n"
                + "    '   <p> Context menu for {{data.name}} </p>' +\n"
                + "    '  </div>' +\n"
                + "    ' <div class=\"sigma-tooltip-footer\">Number of connections: {{degree}}</div>',\n"
                + "    renderer: function(node, template) {\n"
                + "      node.degree = this.degree(node.id);\n"
                + "      return Mustache.render(template, node);\n"
                + "    }\n"
                + "  }],\n"
                + "  stage: {\n"
                + "    template:\n"
                + "    '<div class=\"arrow\"></div>' +\n"
                + "    '<div class=\"sigma-tooltip-header\"> Social Network Analysis </div>'\n"
                + "  }\n"
                + "};\n"
                + "\n";
        if (isForceLink()) {
            rend += useForceLink();
        }
        rend += "\n"
                + "// Instanciate the tooltips plugin with a Mustache renderer for node tooltips:\n"
                + "var tooltips = sigma.plugins.tooltips(s1, s1.renderers[0], config);\n"
                + "\n"
                + "tooltips.bind('shown', function(event) {\n"
                + "  console.log('tooltip shown', event);\n"
                + "});\n"
                + "\n"
                + "tooltips.bind('hidden', function(event) {\n"
                + "  console.log('tooltip hidden', event);\n"
                + "});\n"
                + "\n"
                + "// Bind the Keyboard plugin to the Select plugin:\n"
                + "select.bindKeyboard(keyboard);\n"
                + "\n"
                + "// Curve parallel edges:\n"
                + "sigma.canvas.edges.autoCurve(s1);\n"
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
                + "s1.bind('clickNode doubleClickNode rightClickNode', function(e) {\n"
                + "  console.log(e.type, e.data.node.label, e.data.captor);\n"
                + "});\n"
                + "s1.bind('clickEdge doubleClickEdge rightClickEdge', function(e) {\n"
                + "  console.log(e.type, e.data.edge, e.data.captor);\n"
                + "});\n"
                + "s1.bind('clickStage doubleClickStage rightClickStage', function(e) {\n"
                + "  console.log(e.type, e.data.captor);\n"
                + "});\n"
                + "s1.bind('hovers', function(e) {\n"
                + "  console.log(e.type, e.data.captor, e.data);\n"
                + "});";
        return rend;
    }

    public String useForceLink() {
        String rend = "// Configure the ForceLink algorithm:\n"
                + "var fa = sigma.layouts.configForceLink(s, {\n"
                + "  worker: true,\n"
                + "  barnesHutOptimize: false,\n"
                + "  autoStop: true,\n"
                + "  background: true,\n"
                + "  easing: 'cubicInOut',\n"
                + "  alignNodeSiblings: true,\n"
                + "  nodeSiblingsScale: 1,\n"
                + "  nodeSiblingsAngleMin: 0.3\n"
                + "});\n"
                + "\n"
                + "// Bind the events:\n"
                + "fa.bind('start interpolate stop', function(e) {\n"
                + "  console.log(e.type);\n"
                + "  var el = document.getElementById('notice');\n"
                + "  if (e.type === 'start') {\n"
                + "    el.className = '';\n"
                + "  }\n"
                + "  else if (e.type === 'interpolate') {\n"
                + "    el.className = 'hidden';\n"
                + "  }\n"
                + "});\n"
                + "\n"
                + "// Start the ForceLink algorithm:\n"
                + "sigma.layouts.startForceLink();";
        return rend;
    }

    /**
     * @return the selectedRelationships
     */
    public String[] getSelectedRelationships() {
        return selectedRelationships;
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

    /**
     * @return the minYearEvolution
     */
    public int getMinYearEvolution() {
        return minYearEvolution;
    }

    /**
     * @param minYearEvolution the minYearEvolution to set
     */
    public void setMinYearEvolution(int minYearEvolution) {
        this.minYearEvolution = minYearEvolution;
    }

    /**
     * @return the maxYearEvolution
     */
    public int getMaxYearEvolution() {
        return maxYearEvolution;
    }

    /**
     * @param maxYearEvolution the maxYearEvolution to set
     */
    public void setMaxYearEvolution(int maxYearEvolution) {
        this.maxYearEvolution = maxYearEvolution;
    }

    public void onSlideEnd(SlideEndEvent event) {
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessage() {
        String summary = isForceLink() ? "Use Force Link" : "Force Link Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void addMessageWeight() {
        String summary = isShowWeight() ? "Add Weight" : "Weight Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    /**
     * @return the forceLink
     */
    public boolean isForceLink() {
        return forceLink;
    }

    /**
     * @param forceLink the forceLink to set
     */
    public void setForceLink(boolean forceLink) {
        this.forceLink = forceLink;
    }

    /**
     * @return the showWeight
     */
    public boolean isShowWeight() {
        return showWeight;
    }

    /**
     * @param showWeight the showWeight to set
     */
    public void setShowWeight(boolean showWeight) {
        this.showWeight = showWeight;
    }

    /**
     * @param selectedRelationships the selectedRelationships to set
     */
    public void setSelectedRelationships(String[] selectedRelationships) {
        this.selectedRelationships = selectedRelationships;
    }
}

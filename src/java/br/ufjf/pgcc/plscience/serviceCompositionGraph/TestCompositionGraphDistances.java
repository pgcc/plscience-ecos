/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceCompositionGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phillipe
 */
public class TestCompositionGraphDistances {
  public static void main(String[] args){
      CompositionGraph cg = new CompositionGraph();
      
      GraphNode a = new GraphNode();
      a.setLabel("a");
      GraphNode b = new GraphNode();
      b.setLabel("b");
      GraphNode c = new GraphNode();
      c.setLabel("c");
      GraphNode d = new GraphNode();
      d.setLabel("d");
      GraphNode e = new GraphNode();
      e.setLabel("e");
      GraphNode f = new GraphNode();
      f.setLabel("f");
      GraphNode g = new GraphNode();
      g.setLabel("g");
      GraphNode h = new GraphNode();
      h.setLabel("h");

      List<GraphNode> aNeig = new ArrayList<>();
      aNeig.add(b);
      aNeig.add(d);
      
      List<GraphNode> bNeig = new ArrayList<>();
      bNeig.add(a);
      bNeig.add(c);
      bNeig.add(h);
      
      List<GraphNode> cNeig = new ArrayList<>();
      cNeig.add(b);
      cNeig.add(e);
      
      List<GraphNode> dNeig = new ArrayList<>();
      dNeig.add(a);
      dNeig.add(e);
      dNeig.add(f);

      List<GraphNode> eNeig = new ArrayList<>();
      eNeig.add(c);
      eNeig.add(d);
      eNeig.add(f);
      eNeig.add(g);
      
      List<GraphNode> fNeig = new ArrayList<>();
      fNeig.add(d);
      fNeig.add(e);

      List<GraphNode> gNeig = new ArrayList<>();
      gNeig.add(e);
      gNeig.add(h);
      
      List<GraphNode> hNeig = new ArrayList<>();
      hNeig.add(b);
      hNeig.add(g);

      a.setNeighbors(aNeig);
      b.setNeighbors(bNeig);
      c.setNeighbors(cNeig);
      d.setNeighbors(dNeig);
      e.setNeighbors(eNeig);
      f.setNeighbors(fNeig);
      g.setNeighbors(gNeig);
      h.setNeighbors(hNeig);
      
      List<GraphNode> nodes = new ArrayList<>();
      
      nodes.add(a);
      nodes.add(b);
      nodes.add(c);
      nodes.add(d);
      nodes.add(e);
      nodes.add(f);
      nodes.add(g);
      nodes.add(h);
     
      cg.setServicesNodes(nodes);
      
      Map<GraphNode, Integer> distanceToSource = cg.calculateShortestDistanceGraph(cg,a);
      Integer distA = distanceToSource.get(a);
      Integer distB = distanceToSource.get(b);
      Integer distC = distanceToSource.get(c);
      Integer distD = distanceToSource.get(d);
      Integer distE = distanceToSource.get(e);
      Integer distF = distanceToSource.get(f);
      Integer distG = distanceToSource.get(g);
      Integer distH = distanceToSource.get(h);
      
      System.out.println("Distancia de a:"+distA);
      System.out.println("Distancia de b:"+distB);
      System.out.println("Distancia de c:"+distC);
      System.out.println("Distancia de d:"+distD);
      System.out.println("Distancia de e:"+distE);
      System.out.println("Distancia de f:"+distF);
      System.out.println("Distancia de g:"+distG);
      System.out.println("Distancia de h:"+distH);
  }  
}

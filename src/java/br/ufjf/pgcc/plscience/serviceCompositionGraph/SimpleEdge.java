/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceCompositionGraph;

/**
 *
 * @author phillipe
 */
public class SimpleEdge {
    private GraphNode from;
    private GraphNode to;
    private boolean directed;
    
    SimpleEdge(){
        from = new GraphNode();
        to = new GraphNode();
        directed = false;
    }

    /**
     * @return the from
     */
    public GraphNode getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(GraphNode from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public GraphNode getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(GraphNode to) {
        this.to = to;
    }

    /**
     * @return the directed
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     * @param directed the directed to set
     */
    public void setDirected(boolean directed) {
        this.directed = directed;
    }
}

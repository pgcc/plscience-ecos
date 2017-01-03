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
public class ComposedByEdge {
    private SimpleEdge composedBy;

    /**
     * @return the composedBy
     */
    public SimpleEdge getComposedBy() {
        return composedBy;
    }

    /**
     * @param composedBy the composedBy to set
     */
    public void setComposedBy(SimpleEdge composedBy) {
        this.composedBy = composedBy;
    }
}

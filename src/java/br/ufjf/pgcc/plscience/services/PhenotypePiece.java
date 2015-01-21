/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.services;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fran
 */
@XmlRootElement
public class PhenotypePiece {
    private String phenotypePiece;

    public PhenotypePiece() {
    }

    public String getPhenotypePiece() {
        return phenotypePiece;
    }

    public void setPhenotypePiece(String phenotypePiece) {
        this.phenotypePiece = phenotypePiece;
    }
    
    
    
}

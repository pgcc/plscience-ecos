/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.services;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fran
 */
@Path("/matrixCombinationPhenotypes")
@Consumes(MediaType.APPLICATION_JSON)
public class MatrixCombinationPhenotypes {        
    
        @POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<PhenotypePiece> generateMatrix(List<PhenotypeRelation> f) {
     
            String[] s1 = f.get(0).getPhenotype().split(";");
            String[] s2 = f.get(1).getPhenotype().split(";");
            ArrayList<String> phenoPieces1 = new ArrayList<String>();
            ArrayList<String> phenoPieces2 = new ArrayList<String>();
            ArrayList<PhenotypePiece> phenoPiecesCombine = new ArrayList<PhenotypePiece>();
            
            for(int i=0; i<s1.length;i++){
                String[] temp = s1[i].split("-");
                for(int j=0; j<temp.length;j++){
                   
                    phenoPieces1.add(temp[j]);
                    
                }                
            }
            
            for(int i=0; i<s2.length;i++){
                String[] temp = s2[i].split("-");
                for(int j=0; j<temp.length;j++){
                    phenoPieces2.add(temp[j]);
                }                
            } 
            
            for(String ph1: phenoPieces1){
                PhenotypePiece pp1= new PhenotypePiece();
                pp1.setPhenotypePiece(ph1);
                for(String ph2: phenoPieces2){
                   PhenotypePiece pp2= new PhenotypePiece();
                   pp2.setPhenotypePiece(ph2);
                   phenoPiecesCombine.add(pp1);
                   phenoPiecesCombine.add(pp2);
                }
            
            }
            return phenoPiecesCombine;
	}
    
}

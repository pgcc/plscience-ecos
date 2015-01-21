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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Fran
 */
@Path("/matrixCombinationPhenotypesPrimitiveTypes")
@Consumes(MediaType.APPLICATION_JSON)
public class MatrixCombinationPhenotypesPrimitiveTypes {
  @POST
	@Produces(MediaType.APPLICATION_JSON)
	public String generateMatrix(String f) {
                   
        int ini = f.indexOf("\"phenotype\":");
        int fim = f.indexOf(",\"type\"");
        String pheno1= f.substring(ini, fim);
        
        System.out.println(pheno1);
        
        String f2 = f.substring(fim); 
        f2 = f2.substring(f2.indexOf("\"phenotype\":"));
        
        int ini2 = f2.indexOf("\"phenotype\":");
        int fim2 = f2.indexOf(",\"type\"");
        String pheno2= f2.substring(ini2, fim2); 
        
        System.out.println(pheno2);
           
        pheno1 = pheno1.substring((pheno1.indexOf(":"))+1);
        pheno2 = pheno2.substring((pheno2.indexOf(":"))+1);
        pheno1 = pheno1.replaceAll("[\"\']","").replaceAll("\\\\","");    
        pheno2 = pheno2.replaceAll("[\"\']","").replaceAll("\\\\","");        
         pheno1 = pheno1.replaceAll("[\"\']","");
        pheno2 = pheno2.replaceAll("[\"\']","");  
            
            String[] s1 = pheno1.split(";");
            String[] s2 = pheno2.split(";");
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
            return toString(phenoPiecesCombine);
	}
        
        private String toString(ArrayList<PhenotypePiece> phenoPiecesCombine){
            /*{"phenotypePiece":[{"phenotypePiece":"i"},{"phenotypePiece":"IA"},
            {"phenotypePiece":"i"},{"phenotypePiece":"i"},{"phenotypePiece":"i"},
            {"phenotypePiece":"IA"},{"phenotypePiece":"i"},{"phenotypePiece":"IA"},
            {"phenotypePiece":"i"},{"phenotypePiece":"IA"},{"phenotypePiece":"i"},
            {"phenotypePiece":"i"},{"phenotypePiece":"i"},{"phenotypePiece":"IA"},
            {"phenotypePiece":"i"},{"phenotypePiece":"IA"}]} */
            
            StringBuilder result = new StringBuilder();
            int control =0;
            result.append("{\"phenotypePiece\":[");
            for(PhenotypePiece pr: phenoPiecesCombine){
                if(control>0){
                    result.append(",");
                }
                result.append("{\"phenotypePiece\":\"").append(pr.getPhenotypePiece())
                        .append("\"}");
                control++;
            }
            result.append("]}");

            return result.toString();
        }
    
       
}
 

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
@Path("/bloodTypeProbabilityPrimitiveType")
@Consumes(MediaType.APPLICATION_JSON)
public class BloodTypeProbabilityPrimitiveType {
    
@POST
    @Produces(MediaType.APPLICATION_JSON)
    public String calculate(String phenotypes) {
        
        int ini = phenotypes.indexOf("[");
       
        
        String f2 = phenotypes.substring(ini); 
        f2 = f2.replace("{", "").replace("}", "").replace("[","").replace("]", "");
        String[] structures = f2.split(",");
        List<PhenotypePiece> phenoPieces = new ArrayList<PhenotypePiece>();
        
        for(int i=0; i<structures.length;i++){
            String[] piece = structures[i].split(":");
            PhenotypePiece p = new PhenotypePiece();
            piece[1] = piece[1].replaceAll("[\"\']","").replaceAll("\\\\","");    
            piece[1] = piece[1].replaceAll("[\"\']","");
        
            p.setPhenotypePiece(piece[1]);
            phenoPieces.add(p);
        }
        
	float total = phenoPieces.size()/2;
        float A =0, AB = 0, B = 0, O = 0;
        for(int i=0;i<phenoPieces.size();i+=2){
            String type = phenoPieces.get(i).getPhenotypePiece().concat(phenoPieces.get(i+1).getPhenotypePiece());
            if(type.equalsIgnoreCase("IAi")|| type.equalsIgnoreCase("iIA")||type.equalsIgnoreCase("IAIA")){
                A++;
            }
            if(type.equalsIgnoreCase("IBi")|| type.equalsIgnoreCase("iIB")||type.equalsIgnoreCase("IBIB")){
                B++;
            }
             if(type.equalsIgnoreCase("IAIB")|| type.equalsIgnoreCase("IBIA")){
                AB++;
            }
              if(type.equalsIgnoreCase("ii")){
                O++;
            }
        
        }
        
        BloodTypeProbabilitiesTable bTable = new BloodTypeProbabilitiesTable();       
        bTable.setPercentageA(String.valueOf((A/total)*100));
        bTable.setPercentageB(String.valueOf((B/total)*100));
        bTable.setPercentageAB(String.valueOf((AB/total)*100));
        bTable.setPercentageO(String.valueOf((O/total)*100));
        
        return toString(bTable);
    }
    
    private String toString(BloodTypeProbabilitiesTable bTable){
        //{"percentageA":"0.0","percentageAB":"0.0","percentageB":"75.0","percentageO":"25.0"}
        StringBuilder result = new StringBuilder();
            
            result.append("{\"percentageA\":\"").append(bTable.getPercentageA())
                        .append("\",\"percentageAB\":\"").append(bTable.getPercentageAB())
                        .append("\",\"percentageB\":\"").append(bTable.getPercentageB())
                        .append("\",\"percentageO\":\"").append(bTable.getPercentageO());
              
            result.append("\"}");

            return result.toString();
    }
    
}
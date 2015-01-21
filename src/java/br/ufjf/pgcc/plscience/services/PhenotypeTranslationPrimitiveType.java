/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fran
 */
@Path("/phenotypeTranslationPrimitiveType")
@XmlRootElement
public class PhenotypeTranslationPrimitiveType {
static private Map<String, String> fenotiposMap;
    static { fenotiposMap = new HashMap<String, String>();  
   
    fenotiposMap.put("A", "IA-i;IA-IA");
    fenotiposMap.put("B", "IB-i;IB-IB");
    fenotiposMap.put("O", "i-i");
    fenotiposMap.put("AB", "IA-IB");
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{fenotipos}")
    public String translate(@PathParam("fenotipos") String fenotipos) {
        BloodType father = new BloodType();
        BloodType mother = new BloodType();
               
        String[] f = fenotipos.split("&");
        father.setType(f[0]);
        mother.setType(f[1]);
        
        List<PhenotypeRelation> types= new ArrayList<PhenotypeRelation>();
        PhenotypeRelation f1 = new PhenotypeRelation();
        f1.setType(father.getType());
        f1.setPhenotype(fenotiposMap.get(father.getType()));
        
        PhenotypeRelation f2 = new PhenotypeRelation();
        f2.setType(mother.getType());
        f2.setPhenotype(fenotiposMap.get(mother.getType()));
        
        types.add(f1);
        types.add(f2);
	return toString(types);
    }
    
    private String toString( List<PhenotypeRelation> types){
       // {"phenotypeRelation":[{"phenotype":"i-i","type":"O"},{"phenotype":"i-i","type":"O"}]}
        StringBuilder result = new StringBuilder();
        int control =0;
        result.append("{\"phenotypeRelation\":[");
        for(PhenotypeRelation pr: types){
            if(control>0){
                result.append(",");
            }
            result.append("{\"phenotype\":\"").append(pr.getPhenotype()).append("\",\"type\":\"").append(pr.getType())
                    .append("\"}");
            control++;
        }
        result.append("]}");
        
        return result.toString();
    }
}

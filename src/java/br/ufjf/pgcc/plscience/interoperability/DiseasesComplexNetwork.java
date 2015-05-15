/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.interoperability;

import br.ufjf.pgcc.plscience.util.RandomAccessFilePlus;
import br.ufjf.pgcc.plscience.vo.ServiceVO;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class DiseasesComplexNetwork {
    
    public List<String> loadConcepts(){
        List<String> diseases = new ArrayList();
         try {
           
            // CAMINHO LOCAL
            //File file = new File("C:\\diseasome.gexf.xml");
             
            //CAMINHO NO SERVIDOR
            File file = new File("/opt/tomcat/webapps/plscience/files/diseasome.gexf.xml");
            RandomAccessFilePlus rafp = new RandomAccessFilePlus(new RandomAccessFile(file, "rw"));
            
            String line;
            
            while (!(line = rafp.readLine()).equals("</gexf>")){
                if(line.contains("label=")){
                    diseases.add(line.substring(line.indexOf("label=\"")+7, line.indexOf("\" pid=")));
                }
               
            }
     
            // Fechando arquivo
            rafp.close();
        } catch (IOException ex) {
            Logger.getLogger(ServiceRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diseases;
    }
    
    public List<String> relatedConcepts(String concept){
         List<String> relatedConcepts = new ArrayList();
         List<String> ids = new ArrayList();
         StringBuilder  sb = new StringBuilder();
        try {
            
           // CAMINHO LOCAL
           //File file = new File("C:\\diseasome.gexf.xml");
           
           //CAMINHO NO SERVIDOR
            File file = new File("/opt/tomcat/webapps/plscience/files/diseasome.gexf.xml");
            RandomAccessFilePlus rafp = new RandomAccessFilePlus(new RandomAccessFile(file, "rw"));
            
            String line;
            String id="";
            while (!(line = rafp.readLine()).equals("</gexf>")){
                sb.append(line);
                if(line.contains("label=\""+concept+"\"")){
                    id= line.substring(line.indexOf("id=\"")+4, line.indexOf("\" label="));
                }
                if(line.contains("source=\""+id+"\"")){
                    ids.add(line.substring(line.indexOf("target=\"")+8, line.indexOf("\" />")));
                }
            }
            rafp.close();
            
            RandomAccessFilePlus rafp2 = new RandomAccessFilePlus(new RandomAccessFile(file, "rw"));
           
            while (!(line = rafp2.readLine()).equals("</gexf>")){
                sb.append(line);
                if(line.contains("id=")){
                    for(String s: ids){
                        if(line.contains("id=\""+s)){
                            relatedConcepts.add(line.substring(line.indexOf("label=\"")+7, line.indexOf("\" pid="))); 
                        }
                    }
                    
                }
              
            }
            
     
            // Fechando arquivo
            rafp.close();
        } catch (IOException ex) {
            Logger.getLogger(ServiceRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return relatedConcepts;
    }
    
    public static void main(String[] args) throws IOException {
         DiseasesComplexNetwork dcn = new DiseasesComplexNetwork();
      
        //List<String> diseases = dcn.loadConcepts();
        List<String> concepts = dcn.relatedConcepts("Blood group");
        //for(String s: diseases){
          // System.out.println(s);
        //}
        for(String s: concepts){
           System.out.println(s);
        }
    }
    
}

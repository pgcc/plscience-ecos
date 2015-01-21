/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.services;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 *
 * @author Fran
 */
@Path("/equivalentServices")
public class EquivalentServicesResource {
   
   
    private List<String> montarListaDeIndividuos(ResultSet results, String entidade)
    {
        List<String> listaEntidades = new ArrayList<String>();
        List<String> lista = new ArrayList<String>();
        while (results.hasNext()) {
            QuerySolution qs = results.next();
            Resource individuo = qs.getResource("?"+entidade+"");
            lista.add(individuo.getLocalName());
        }
        
        Iterator i = lista.iterator();
        while(i.hasNext())
        {             
             try
             {
                  String ind = (String) i.next();
                  if(!ind.equals("null"))
                  {
                      listaEntidades.add(ind);
                  }                  
             }catch(NullPointerException e){}          
        }
        return listaEntidades;
    }
    
    
    private List<String> montarListaDeClasses(ResultSet results, String entidade)
    {
        List<String> listaEntidades = new ArrayList<String>();
        List<String> lista = new ArrayList<String>();
        while (results.hasNext()) {
            QuerySolution qs = results.next();
            String uriCompleta = (String)qs.toString();
            if(uriCompleta.contains("http:"))
            {
                String temp[] = uriCompleta.split("#");
                String nomeClasse[] = temp[1].split(">");
                if(!nomeClasse[0].equals(entidade))
                {
                    lista.add(nomeClasse[0]);
                }
            }
        }
        
        Iterator i = lista.iterator();
        while(i.hasNext())
        {             
             try
             {
                  String ind = (String) i.next();
                  if(!ind.equals("null"))
                  {
                      listaEntidades.add(ind);
                  }                  
             }catch(NullPointerException e){}          
        }
        return listaEntidades;
    }
    
    @Path("/syntactic/{servico}")
    @GET
    @Produces("text/plain")
    public String getSyntacticallyEquivalentServices(@PathParam("servico") String servico)
    {   
        EquivalentServices equiv = new EquivalentServices();
        equiv.modelInf.read( equiv.file, "RDF/XML" );
        // Create a new query        
        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "PREFIX bc: <"+equiv.baseURI+">\n" +
        "SELECT * WHERE { ?serv1 <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#syntacticallyEquivalent> <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#"+""+servico+">}\n ";
        //"SELECT ?serv1 WHERE { ?serv1 rdfs:subPropertyOf <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#syntacticallyEquivalent>}";
                
                //"SELECT ?x WHERE { ?x <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#hasCompleteName> \"Marcio Werner\"}\n";
                
                //"SELECT ?Service  WHERE { ?Service bc:pertenceA <"+ uri +""+entidade+">}\n ";
        
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, equiv.model);
        ResultSet results = qe.execSelect();
        List<String> listIndividuos = new ArrayList<String>();
        listIndividuos = montarListaDeClasses(results, "Service");                
        qe.close();
        
        StringBuilder sb = new StringBuilder();
        sb.append("Syntactically equivalent services with ").append(servico).append(": \n");
        for (String string : listIndividuos) {
           sb.append(string).append("\n");
        }
        return sb.toString();    
    }
    
    @Path("/semantic/{servico}")
    @GET
    @Produces("text/plain")
    public String getSemanticallyEquivalentServices(@PathParam("servico") String servico)
    {   
        EquivalentServices equiv = new EquivalentServices();
        equiv.modelInf.read( equiv.file, "RDF/XML" );
        // Create a new query        
        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "PREFIX bc: <"+equiv.baseURI+">\n" +
        "SELECT * WHERE { ?serv1 <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#semanticallyEquivalent> <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#"+""+servico+">}\n ";
        //"SELECT ?serv1 WHERE { ?serv1 rdfs:subPropertyOf <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#syntacticallyEquivalent>}";
                
                //"SELECT ?x WHERE { ?x <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#hasCompleteName> \"Marcio Werner\"}\n";
                
                //"SELECT ?Service  WHERE { ?Service bc:pertenceA <"+ uri +""+entidade+">}\n ";
        
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, equiv.model);
        ResultSet results = qe.execSelect();
        List<String> listIndividuos = new ArrayList<String>();
        listIndividuos = montarListaDeClasses(results, "Service");                
        qe.close();
        
        StringBuilder sb = new StringBuilder();
        sb.append("Semantically equivalent services with ").append(servico).append(": \n");
        for (String string : listIndividuos) {
           sb.append(string).append("\n");
        }
        return sb.toString();    
    }
    
    @Path("/pragmatic/{servico}")
    @GET
    @Produces("text/plain")
    public String getPragmaticallyEquivalentServices(@PathParam("servico") String servico)
    {   
        EquivalentServices equiv = new EquivalentServices();
        equiv.modelInf.read( equiv.file, "RDF/XML" );
        // Create a new query        
        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "PREFIX bc: <"+equiv.baseURI+">\n" +
        "SELECT * WHERE { ?serv1 <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#pragmaticallyEquivalent> <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#"+""+servico+">}\n ";
        //"SELECT ?serv1 WHERE { ?serv1 rdfs:subPropertyOf <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#syntacticallyEquivalent>}";
                
                //"SELECT ?x WHERE { ?x <http://www.semanticweb.org/fran/ontologies/2014/6/PLScienceServiceDescription.owl#hasCompleteName> \"Marcio Werner\"}\n";
                
                //"SELECT ?Service  WHERE { ?Service bc:pertenceA <"+ uri +""+entidade+">}\n ";
        
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, equiv.model);
        ResultSet results = qe.execSelect();
        List<String> listIndividuos = new ArrayList<String>();
        listIndividuos = montarListaDeClasses(results, "Service");                
        qe.close();
        
        StringBuilder sb = new StringBuilder();
        sb.append("Pragmatically equivalent services with ").append(servico).append(": \n");
        for (String string : listIndividuos) {
           sb.append(string).append("\n");
        }
        return sb.toString();    
    }
}


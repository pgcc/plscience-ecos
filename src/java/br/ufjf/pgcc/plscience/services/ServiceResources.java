/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.services;

import br.ufjf.pgcc.plscience.ontology.DataHandler;
import br.ufjf.pgcc.plscience.ontology.InferenceLayer;
import br.ufjf.pgcc.plscience.model.Response;
import br.ufjf.pgcc.plscience.model.Triple;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Classe de implementação dos recussos REST fornecidos pelo Web Service
 *
 * @author Lenita
 */
@Path("/inference")
public class ServiceResources {

    @Context
    private UriInfo context;
    private final InferenceLayer infLayer;
    private final DataHandler dataHandler;

    /**
     * Creates a new instance of ServiceResources
     */
    public ServiceResources() {
        infLayer = new InferenceLayer();
        dataHandler = new DataHandler();
    }

    /**
     * Retrieves representation of an instance of
     * com.pos.java.provse.service.ServiceResources
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ServiceResources
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Path("addtriple")
    public void postAddTriple(List<Triple> content) {
        for (Triple triple : content) {
            if (!dataHandler.addTriple(triple.getSubject(), triple.getPredicate(), triple.getObject())) {
                throw new NotSupportedException("Object " + triple.getObject() + " not found. Please insert the object first.");
            }
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("individualsbyclass")
    public List<Response> getIndividualsByClass(@QueryParam("prefix") String prefix, @QueryParam("name") String name) {
        ArrayList<Response> response = new ArrayList<>();
        for (String s : infLayer.jenaGetIndividualsByClass(prefix, name)) {
            response.add(new Response(s));
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("individualsbyclassinf")
    public List<Response> getIndividualsByClassInf(@QueryParam("prefix") String prefix, @QueryParam("name") String name) {
        ArrayList<Response> response = new ArrayList<>();
        for (String s : infLayer.sparqlGetIndividualsByClassInf(prefix, name)) {
            response.add(new Response(s));
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("propertiesbyclass")
    public List<Response> getPropertiesByClass(@QueryParam("prefix") String prefix, @QueryParam("name") String name) {
        ArrayList<Response> response = new ArrayList<>();
        for (String s : infLayer.jenaGetPropertiesByClass(prefix, name)) {
            response.add(new Response(s));
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("opbyindividualinf")
    public List<Response> getOPAssertionsByIndividualInf(@QueryParam("iname") String iname, @QueryParam("opprefix") String opprefix, @QueryParam("opname") String opname) {
        ArrayList<Response> response = new ArrayList<>();
        for (String s : infLayer.jenaGetOPAssertionsByIndividualInf(iname, opprefix, opname)) {
            response.add(new Response(s));
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("usedwfmsinf")
    public List<Response> getUsedWfmsInf(@QueryParam("name") String name) {
        ArrayList<Response> response = new ArrayList<>();
        for (String s : infLayer.jenaGetUsedWfmsInf(name)) {
            response.add(new Response(s));
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("executeinf")
    public List<Response> getExecuteInf(@QueryParam("name") String name) {
        ArrayList<Response> response = new ArrayList<>();
        for (String s : infLayer.jenaGetExecuteInf(name)) {
            response.add(new Response(s));
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("executedininf")
    public List<Response> getExecutedInInf(@QueryParam("name") String name) {
        ArrayList<Response> response = new ArrayList<>();
        for (String s : infLayer.jenaGetExecutedInInf(name)) {
            response.add(new Response(s));
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("issimilarinf")
    public List<Response> getIsSimilarInf(@QueryParam("name") String name) {
        ArrayList<Response> response = new ArrayList<>();
        for (String s : infLayer.jenaGetIsSimilarInf(name)) {
            response.add(new Response(s));
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("wasinfluencedbyinf")
    public List<Response> getWasInfluencedByInf(@QueryParam("name") String name) {
        ArrayList<Response> response = new ArrayList<>();
        for (String s : infLayer.jenaGetWasInfluencedByInf(name)) {
            response.add(new Response(s));
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Path("propertiesbyindividualinf")
    public List<Response> getPropertiesByIndividualInf(@QueryParam("name") String name) {
        ArrayList<Response> response = new ArrayList<>();
        for (String s : infLayer.sparqlGetPropertiesByIndividualInf(name)) {
            response.add(new Response(s));
        }
        return response;
    }

}

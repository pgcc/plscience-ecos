/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Fran
 */

@Path("/teste")
@Consumes(MediaType.APPLICATION_JSON)
public class teste {
        
    
        @POST
	@Produces(MediaType.TEXT_PLAIN)
	public String generateMatrix(PhenotypeRelation f) {
		return f.getPhenotype();
	}
    
}
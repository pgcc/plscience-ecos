/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.social.apli.client;

import br.ufjf.pgcc.plscience.social.api.model.dblp.Coauthor;
import br.ufjf.pgcc.plscience.social.api.model.dblp.DblpAuthor;
import br.ufjf.pgcc.plscience.social.api.model.dblp.Publication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class DblpClient {
    public static DblpAuthor getAuthor(String authorName, String host) {
        try {
            String authorPath = "/dblp/author/";
            String publications = "/publications/";
            String coauthors = "/coauthors";
            String protocol = "http://";
            String port = ":8080";
            
            authorName = authorName.replaceAll(" ", "%20");

            
            Client c = Client.create();
            WebResource wr = c.resource(protocol + host + port + authorPath + authorName);
            String json = wr.get(String.class);
            Gson gson = new Gson();
            
            DblpAuthor author = gson.fromJson(json, new TypeToken<DblpAuthor>(){}.getType());
            
            wr = c.resource(protocol + host + port + authorPath + authorName + publications);
            json = wr.get(String.class);
            
            Type listType = new TypeToken<List<Publication>>() {}.getType();
            List<Publication> pubs = gson.fromJson(json, listType);
            author.setPublications(pubs);
            
            wr = c.resource(protocol + host + port + authorPath + authorName + coauthors);
            json = wr.get(String.class);
            
            listType = new TypeToken<List<Coauthor>>() {}.getType();
            List<Coauthor> coauths = gson.fromJson(json, listType);
            
            author.setCoauthors(coauths);
            
            return author;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

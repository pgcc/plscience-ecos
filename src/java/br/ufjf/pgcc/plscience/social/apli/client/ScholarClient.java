/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.social.apli.client;

import br.ufjf.pgcc.plscience.social.api.model.scholar.ScholarAuthor;
import br.ufjf.pgcc.plscience.social.api.model.scholar.Publication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.lang.reflect.Type;
import java.util.List;

public class ScholarClient {
    public static ScholarAuthor getAuthor(String authorName, String host) {
        try {
            String authorPath = "/scholar/author/";
            String publications = "/publications/";
            String protocol = "http://";
            String port = ":8080";
            
            authorName = authorName.replaceAll(" ", "%20");

            
            Client c = Client.create();
            WebResource wr = c.resource(protocol + host + port + authorPath + authorName);
            String json = wr.get(String.class);
            Gson gson = new Gson();
            
            ScholarAuthor author = gson.fromJson(json, new TypeToken<ScholarAuthor>(){}.getType());
            
            wr = c.resource(protocol + host + port + authorPath + authorName + publications);
            json = wr.get(String.class);
            
            Type listType = new TypeToken<List<Publication>>() {}.getType();
            List<Publication> pubs = gson.fromJson(json, listType);


            author.setPublications(pubs);
            return author;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
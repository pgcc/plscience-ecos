/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.experiments;

import br.ufjf.biocatalogue.core.BioCatalogueClient;
import br.ufjf.biocatalogue.model.Result;
import br.ufjf.biocatalogue.model.Search;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class DiscoverBioCatalogue {
    
    private BioCatalogueClient client;
    private String searchQuery;
    private String scope;
    private ArrayList<Result> results;
    private Result selectedResult;
    
    public DiscoverBioCatalogue() {
        client = new BioCatalogueClient();
        client.setBaseUri("https://www.biocatalogue.org");
    }
    
    public void search() {
        try {
            String query = searchQuery;
            if (scope != null && !scope.isEmpty()) {
                query += "&scope=" + scope;
            }
            Search result = client.search(query);
            if (result != null) {
                results = result.getResults();
            }
        } catch (Exception e) {
        }
    }

    /**
     * @return the client
     */
    public BioCatalogueClient getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(BioCatalogueClient client) {
        this.client = client;
    }

    /**
     * @return the searchQuery
     */
    public String getSearchQuery() {
        return searchQuery;
    }

    /**
     * @param searchQuery the searchQuery to set
     */
    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * @return the results
     */
    public ArrayList<Result> getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    /**
     * @return the selectedResult
     */
    public Result getSelectedResult() {
        return selectedResult;
    }

    /**
     * @param selectedResult the selectedResult to set
     */
    public void setSelectedResult(Result selectedResult) {
        this.selectedResult = selectedResult;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
    }
    
}

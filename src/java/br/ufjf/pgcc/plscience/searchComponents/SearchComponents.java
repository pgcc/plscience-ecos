/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import br.ufjf.biocatalogue.core.BioCatalogueClient;
import br.ufjf.biocatalogue.exception.BioCatalogueException;
import br.ufjf.biocatalogue.model.Result;
import br.ufjf.pgcc.plscience.bean.experiments.prototyping.SearchBioCatalogue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author phillipe
 */

@ManagedBean()
@ViewScoped
public class SearchComponents implements Serializable{
    
    private static long serialVersionUID = 1L;
    private BioCatalogueClient bioClient;
    private String searchQuery;
    private String scope;
    private ArrayList<Result> results;
    private ArrayList<ResultsPatternFormat> patternResults;
    private Result selectedResult;
    
    public SearchComponents() {
        patternResults = new ArrayList<>();
        bioClient = new BioCatalogueClient();
        bioClient.setBaseUri("https://www.biocatalogue.org");
    }
    
    public void search(){
        //search in BioCatalogue
        bioSearch();
    }
    
    public void bioSearch(){
        scope = "services";
        SearchBioCatalogue searchBio = new SearchBioCatalogue();
        results = searchBio.search(searchQuery, scope, results, bioClient);
        
        for(Result result:results){
            ResultsPatternFormat rpf = new ResultsPatternFormat();
            if(result.getName() != null){
                rpf.setName(result.getName());
            }else{
                rpf.setName("---");
            }                
            if(result.getDescription() != null){
                rpf.setDescription(result.getDescription());
            }else{
                rpf.setName("---");
            }
            if(result.getSubmitter() != null){
                rpf.setOwner(result.getSubmitter());
            }else{
                rpf.setOwner("---");
            }
            rpf.setComponentType("Service");
            rpf.setRepositoryName("BioCatalogue");
            getPatternResults().add(rpf);
        }
    }

        /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }    
    
    /**
     * @return the bioClient
     */
    public BioCatalogueClient getBioClient() {
        return bioClient;
    }

    /**
     * @param bioClient the bioClient to set
     */
    public void setBioClient(BioCatalogueClient bioClient) {
        this.bioClient = bioClient;
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
     * @return the patternResults
     */
    public ArrayList<ResultsPatternFormat> getPatternResults() {
        return patternResults;
    }

    /**
     * @param patternResults the patternResults to set
     */
    public void setPatternResults(ArrayList<ResultsPatternFormat> patternResults) {
        this.patternResults = patternResults;
    }

    
}
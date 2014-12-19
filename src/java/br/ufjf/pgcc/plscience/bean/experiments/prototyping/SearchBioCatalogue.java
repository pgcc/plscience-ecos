/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.pgcc.plscience.bean.experiments.prototyping;

import br.ufjf.biocatalogue.core.BioCatalogueClient;
import br.ufjf.biocatalogue.model.Result;
import br.ufjf.biocatalogue.model.Search;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class SearchBioCatalogue implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final BioCatalogueClient client;
    private String searchQuery;
    private String scope;
    private ArrayList<Result> results;
    private Result selectedResult;
    
    public SearchBioCatalogue() {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import br.ufjf.biocatalogue.core.BioCatalogueClient;
import br.ufjf.biocatalogue.exception.BioCatalogueException;
import br.ufjf.biocatalogue.model.Result;
import br.ufjf.myexperiment.model.Search;
import br.ufjf.myexperiment.core.MyExperimentClient;
import br.ufjf.myexperiment.exception.MyExperimentException;
import br.ufjf.myexperiment.model.File;
import br.ufjf.myexperiment.model.Pack;
import br.ufjf.myexperiment.model.Workflow;
import br.ufjf.pgcc.plscience.bean.experiments.prototyping.SearchBioCatalogue;
import br.ufjf.pgcc.plscience.bean.experiments.prototyping.SearchMyExperiment;
import br.ufjf.pgcc.plscience.message.InfoMessage;
import br.ufjf.pgcc.plscience.message.WarningMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author phillipe
 */
@ManagedBean()
@ViewScoped
public class SearchComponents implements Serializable {

    private static long serialVersionUID = 1L;
    private BioCatalogueClient bioClient;
    private MyExperimentClient myExpClient;
    private String searchQuery;
    private String[] selectedComponents;
    private String scope;
    private ArrayList<Result> results;
    private ArrayList<ResultsPatternFormat> patternResults;
    private Result selectedResult;

    public SearchComponents() {
        bioClient = new BioCatalogueClient();
        bioClient.setBaseUri("https://www.biocatalogue.org");
        myExpClient = new MyExperimentClient();
        myExpClient.setBaseUri("http://www.myexperiment.org");
    }

    /**
     * Search in all integrated repositories
     *
     * @throws BioCatalogueException
     * @throws MyExperimentException
     */
    public void search() throws BioCatalogueException, MyExperimentException {
        patternResults = new ArrayList<>();
        if (selectedComponents.length != 0) {
            if (componentWasSelected("service")) {
                bioSearch();
            }
            if (componentWasSelected("workflow") || componentWasSelected("file")
                    || componentWasSelected("pack")) {
                myExperimentSearch();
            }
        } else {
            WarningMessage.warnComponentsNotSelected();
        }
    }

    /**
     * Search in myExperiment Repository
     *
     * @throws br.ufjf.myexperiment.exception.MyExperimentException
     */
    public void myExperimentSearch() throws MyExperimentException {
        Search myExpResult = SearchMyExperiment.searchAllComponents(searchQuery, myExpClient);
        List<Workflow> workflows = myExpResult.getWorkflow();
        List<File> files = myExpResult.getFile();
        List<Pack> packs = myExpResult.getPack();

        if (componentWasSelected("workflow")) {
            if (workflows == null) {
                InfoMessage.infoWorkflowsNotFound();
            } else {
                for (Workflow w : workflows) {
                    ResultsPatternFormat rpf = new ResultsPatternFormat();
                    if (w.getDescription() != null) {
                        rpf.setDescription(w.getDescription());
                    } else {
                        rpf.setDescription("---");
                    }
                    if (w.getTitle() != null && !w.getTitle().equals("")) {
                        rpf.setName(w.getTitle());
                    } else {
                        rpf.setName("---");
                    }
                    if (w.getUploader() != null && !w.getUploader().equals("")) {
                        rpf.setOwner(w.getUploader());
                    } else {
                        rpf.setOwner("---");
                    }
                    rpf.setComponentType("Workflow");
                    rpf.setRepositoryName("myExperiment");
                    getPatternResults().add(rpf);
                }

            }
        }

        if (componentWasSelected("file")) {
            if (files == null) {
                InfoMessage.infoFilesNotFound();
            } else {
                for (File f : files) {
                    ResultsPatternFormat rpf = new ResultsPatternFormat();
                    if (f.getDescription() != null) {
                        rpf.setDescription(f.getDescription());
                    } else {
                        rpf.setDescription("---");
                    }
                    if (f.getTitle() != null && !f.getTitle().equals("")) {
                        rpf.setName(f.getTitle());
                    } else {
                        rpf.setName("---");
                    }
                    if (f.getUploader() != null && !f.getUploader().equals("")) {
                        rpf.setOwner(f.getUploader());
                    } else {
                        rpf.setOwner("---");
                    }
                    rpf.setComponentType("File");
                    rpf.setRepositoryName("myExperiment");
                    getPatternResults().add(rpf);
                }
            }

        }

        if (componentWasSelected("pack")) {
            if (packs == null) {
                InfoMessage.infoPacksNotFound();
            } else {
                for (Pack p : packs) {
                    ResultsPatternFormat rpf = new ResultsPatternFormat();
                    if (p.getDescription() != null) {
                        rpf.setDescription(p.getDescription());
                    } else {
                        rpf.setDescription("---");
                    }
                    if (p.getTitle() != null && !p.getTitle().equals("")) {
                        rpf.setName(p.getTitle());
                    } else {
                        rpf.setName("---");
                    }
                    if (p.getUploader() != null && !p.getUploader().equals("")) {
                        rpf.setOwner(p.getUploader());
                    } else {
                        rpf.setOwner("---");
                    }
                    rpf.setComponentType("Pack");
                    rpf.setRepositoryName("myExperiment");
                    getPatternResults().add(rpf);
                }

            }
        }
    }

    /**
     * Search services in BioCatalogue Repository
     */
    public void bioSearch() {
        scope = "services";
        SearchBioCatalogue searchBio = new SearchBioCatalogue();
        results = searchBio.search(searchQuery, scope, results, bioClient);

        if (results == null) {
            InfoMessage.infoServicesNotFound();
        } else {
            for (Result result : results) {
                ResultsPatternFormat rpf = new ResultsPatternFormat();
                if (result.getName() != null) {
                    rpf.setName(result.getName());
                } else {
                    rpf.setName("---");
                }
                if (result.getDescription() != null) {
                    rpf.setDescription(result.getDescription());
                } else {
                    rpf.setName("---");
                }
                if (result.getSubmitter() != null) {
                    rpf.setOwner(result.getSubmitter());
                } else {
                    rpf.setOwner("---");
                }
                rpf.setComponentType("Service");
                rpf.setRepositoryName("BioCatalogue");
                getPatternResults().add(rpf);
            }
        }
    }

    /**
     * Checks if a component is selected
     *
     * @param term Component name used to search
     * @return
     */
    public boolean componentWasSelected(String term) {
        for (String componentS : selectedComponents) {
            if (componentS.contains(term)) {
                return true;
            }
        }
        return false;
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

    /**
     * @return the myExpClient
     */
    public MyExperimentClient getMyExpClient() {
        return myExpClient;
    }

    /**
     * @param myExpClient the myExpClient to set
     */
    public void setMyExpClient(MyExperimentClient myExpClient) {
        this.myExpClient = myExpClient;
    }

    /**
     * @return the selectedComponents
     */
    public String[] getSelectedComponents() {
        return selectedComponents;
    }

    /**
     * @param selectedComponents the selectedComponents to set
     */
    public void setSelectedComponents(String[] selectedComponents) {
        this.selectedComponents = selectedComponents;
    }

}

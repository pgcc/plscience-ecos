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

import br.ufjf.myexperiment.core.MyExperimentClient;
import br.ufjf.myexperiment.exception.MyExperimentException;
import br.ufjf.myexperiment.model.File;
import br.ufjf.myexperiment.model.Group;
import br.ufjf.myexperiment.model.Pack;
import br.ufjf.myexperiment.model.Search;
import br.ufjf.myexperiment.model.User;
import br.ufjf.myexperiment.model.Workflow;
import br.ufjf.pgcc.plscience.controller.UsedBean;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import br.ufjf.pgcc.plscience.dao.UsedDAO;
import br.ufjf.pgcc.plscience.model.Used;
import br.ufjf.pgcc.plscience.recos.IntegrationModule;
import br.ufjf.pgcc.plscience.recos.MidPoint;
import br.ufjf.pgcc.plscience.recos.calculateFactors;
import br.ufjf.pgcc.plscience.searchComponents.SearchComponents;
import br.ufjf.pgcc.plscience.string.ExternalRepositoriesString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class SearchMyExperiment implements Serializable {

    private static final long serialVersionUID = 1L;

    private final MyExperimentClient client;
    private String searchQuery;
    private String type;

    private List<Workflow> workflows;
    private List<Pack> packs;
    private List<User> users;
    private List<Group> groups;
    private List<File> files;

    public SearchMyExperiment() {
        client = new MyExperimentClient();
        client.setBaseUri("http://www.myexperiment.org");
    }

    public void search() {
        try {
            String query = searchQuery;
            if (type != null && !type.isEmpty()) {
                query += "&type=" + type;
            }
            Search result = client.search(query);
            workflows = result.getWorkflow();
            packs = result.getPack();
            users = result.getUser();
            groups = result.getGroup();
            files = result.getFile();

//            List experiments = new ArrayList();
//            experiments = new ExperimentDAO().getFrequencyService("protein_protein_interactions.xml");

            IntegrationModule.midPointModel();
            IntegrationModule.setWorkflows(workflows);

        } catch (Exception e) {
        }
    }

    public void equivalentsearch(int workflow) {
        try {

            String query = "";
            Used used = new Used();
            List useds = new ArrayList();
            useds = UsedDAO.getInstance().buscar(workflow);
            for (Object u : useds) {
                used = (Used) u;
                query += used.getTaskidTask().getName() + "+and+";
            }
            //query += "search_field+and+Document_index+and+CountProteins+and+CountDiseases+and+CountDiseasesPerProtein+and+Flatten_and_make_unique+and+Link_proteins_to_diseases+and+Retrieve_documents+and+Discover_RatHumanMouseUniProt_proteins+and+";
            query += "&type=workflow";
            Search result = client.search(query);
            workflows = result.getWorkflow();
            
            IntegrationModule.setWorkflows(workflows);

        } catch (Exception e) {
        }
    }
    
    /**
     * Search All Components in the myExperiment Repository
     * @param searchQuery
     * @param myExpClient
     * @return
     * @throws MyExperimentException 
     */
    public static Search searchAllComponents(String searchQuery, MyExperimentClient myExpClient) throws MyExperimentException{
        String query;
        String scope = "workflow,file,pack,user";
        String elements = "title,created-at,updated-at,resource,id,uri,statistics,"
                + "uploader,description,type,preview,thumbnail,thumbnail-big,svg,license-type,content-uri,"
                + "content-type,content,tags,filename,name,email,avatar,city,country";
        SearchMyExperiment smy = new SearchMyExperiment();
        if (searchQuery != null && !searchQuery.isEmpty()) {
            smy.setSearchQuery(searchQuery);
        }
        query = ExternalRepositoriesString.formatSearchTerm(searchQuery);
        if (scope != null && !scope.isEmpty()) {
            query += "&type=" + scope + "&elements=" + elements;
        }
        if (scope != null && !scope.isEmpty()) {
            smy.setType(scope);
        }
        
        Search myExpResult = myExpClient.searchWithoutElements(query);
        return myExpResult;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the workflows
     */
    public List<Workflow> getWorkflows() {
        return workflows;
    }

    /**
     * @param workflows the workflows to set
     */
    public void setWorkflows(List<Workflow> workflows) {
        this.workflows = workflows;
    }

    /**
     * @return the packs
     */
    public List<Pack> getPacks() {
        return packs;
    }

    /**
     * @param packs the packs to set
     */
    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return the groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     * @return the files
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(List<File> files) {
        this.files = files;
    }
}

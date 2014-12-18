/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.experiments.prototyping;

import br.ufjf.myexperiment.core.MyExperimentClient;
import br.ufjf.myexperiment.model.File;
import br.ufjf.myexperiment.model.Group;
import br.ufjf.myexperiment.model.Pack;
import br.ufjf.myexperiment.model.Search;
import br.ufjf.myexperiment.model.User;
import br.ufjf.myexperiment.model.Workflow;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class SearchMyExperiment {
    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.recos;

/**
 * 
 * @author lesimoes
 */
public class RecosService {

    private int id;
    private String resource;
    private String title;
    private String created_At;
    private String updated_At;
    private String latestTime_used;
    private int frequencyService;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the created_At
     */
    public String getCreated_At() {
        return created_At;
    }

    /**
     * @param created_At the created_At to set
     */
    public void setCreated_At(String created_At) {
        this.created_At = created_At;
    }

    /**
     * @return the updated_At
     */
    public String getUpdated_At() {
        return updated_At;
    }

    /**
     * @param updated_At the updated_At to set
     */
    public void setUpdated_At(String updated_At) {
        this.updated_At = updated_At;
    }

    /**
     * @return the latestTime_used
     */
    public String getLatestTime_used() {
        return latestTime_used;
    }

    /**
     * @param latestTime_used the latestTime_used to set
     */
    public void setLatestTime_used(String latestTime_used) {
        this.latestTime_used = latestTime_used;
    }

    /**
     * @return the frequencyService
     */
    public int getFrequencyService() {
        return frequencyService;
    }

    /**
     * @param frequencyService the frequencyService to set
     */
    public void setFrequencyService(int frequencyService) {
        this.frequencyService = frequencyService;
    }
    
    
}

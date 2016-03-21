

package br.ufjf.pgcc.plscience.recos;

import java.util.Comparator;
import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 * 
 * @author lesimoes
 */
public class RecosService{

    private int id;
    private String resource;
    private String title;
    private String created_At;
    private String updated_At;
    private String latestTime_used;
    private int frequencyService;
    private double rating;
    private double time;
    private double ranking;
    
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
        this.created_At = created_At.substring(0, 10);
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
        this.updated_At = updated_At.substring(0, 10);
    }

    /**
     * @return the latestTime_used
     */
    public String getLatestTime_used() {
        if(latestTime_used == "[]")
            return "0000-00-00";
        else
        return latestTime_used.substring(1, 11);
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

    /**
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @return the time
     */
    public double getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(double time) {
        this.time = time;
    }

    public double getRanking() {
        return ranking;
    }

    public void setRanking(double ranking) {
        this.ranking = ranking;
    }




    
    
}

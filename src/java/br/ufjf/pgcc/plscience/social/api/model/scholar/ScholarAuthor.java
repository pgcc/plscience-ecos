/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.social.api.model.scholar;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScholarAuthor {

    @SerializedName("_filled")
    @Expose
    private Boolean filled;
    @SerializedName("affiliation")
    @Expose
    private String affiliation;
    @SerializedName("citationIndices")
    @Expose
    private List<String> citationIndices = null;
    @SerializedName("citedby")
    @Expose
    private Integer citedby;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("interests")
    @Expose
    private List<String> interests = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url_picture")
    @Expose
    private String urlPicture;
    private transient List<Publication> publications;

    public Boolean getFilled() {
        return filled;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public List<String> getCitationIndices() {
        return citationIndices;
    }


    public Integer getCitedby() {
        return citedby;
    }


    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public List<String> getInterests() {
        return interests;
    }

    public String getName() {
        return name;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public List<Publication> getPublications(){
        return publications;
    }

    public void setPublications(List<Publication> publications){
        this.publications = publications;
    }
}


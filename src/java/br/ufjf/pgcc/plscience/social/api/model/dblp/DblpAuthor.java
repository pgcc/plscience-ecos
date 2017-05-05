/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.social.api.model.dblp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DblpAuthor {

    @SerializedName("_filled")
    @Expose
    private Boolean filled;
    @SerializedName("coauthCount")
    @Expose
    private Integer coauthCount;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pubCount")
    @Expose
    private Integer pubCount;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("urlPt")
    @Expose
    private String urlPt;

    private transient List<Publication> publications;
    private transient List<Coauthor> coauthors;

    public Boolean getFilled() {
        return filled;
    }

    public Integer getCoauthCount() {
        return coauthCount;
    }

    public String getName() {
        return name;
    }

    public Integer getPubCount() {
        return pubCount;
    }


    public String getText() {
        return text;
    }

    public String getUrlPt() {
        return urlPt;
    }

    public List<Publication> getPublications(){
        return publications;
    }

    public void setPublications(List<Publication> publications){
        this.publications = publications;
    }

    public List<Coauthor> getCoauthors(){
        return coauthors;
    }

    public void setCoauthors(List<Coauthor> coauthors){
        this.coauthors = coauthors;
    }
}

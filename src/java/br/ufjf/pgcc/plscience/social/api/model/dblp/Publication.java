/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.social.api.model.dblp;

import java.util.List;import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Publication {

    @SerializedName("authors")
    @Expose
    private List<String> authors = null;
    @SerializedName("booktitle")
    @Expose
    private String booktitle;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("journal")
    @Expose
    private Object journal;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("pages")
    @Expose
    private String pages;
    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("volume")
    @Expose
    private Object volume;
    @SerializedName("year")
    @Expose
    private String year;

    public List<String> getAuthors() {
        return authors;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public String getDate() {
        return date;
    }

    public Object getJournal() {
        return journal;
    }


    public String getKey() {
        return key;
    }

    public String getPages() {
        return pages;
    }

    public Object getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Object getVolume() {
        return volume;
    }

    public void setVolume(Object volume) {
        this.volume = volume;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
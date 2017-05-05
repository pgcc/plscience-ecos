/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.social.api.model.dblp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coauthor {

    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("urlpt")
    @Expose
    private String urlpt;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlpt() {
        return urlpt;
    }

    public void setUrlpt(String urlpt) {
        this.urlpt = urlpt;
    }

}
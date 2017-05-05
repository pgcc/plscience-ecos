/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.social.api.model.scholar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bib {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("year")
    @Expose
    private Integer year;

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }
}
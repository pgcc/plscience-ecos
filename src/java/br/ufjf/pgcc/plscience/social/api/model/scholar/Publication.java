/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.social.api.model.scholar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Publication {

    @SerializedName("_filled")
    @Expose
    private Boolean filled;
    @SerializedName("bib")
    @Expose
    private Bib bib;
    @SerializedName("citedby")
    @Expose
    private Integer citedby;
    @SerializedName("id_citations")
    @Expose
    private String idCitations;

    public Boolean getFilled() {
        return filled;
    }

    public Bib getBib() {
        return bib;
    }

    public Integer getCitedby() {
        return citedby;
    }

    public String getIdCitations() {
        return idCitations;
    }

}
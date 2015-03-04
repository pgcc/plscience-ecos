/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.vo;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Fran
 */
@ManagedBean()
@ViewScoped
public class ContextVO implements Serializable{
    private String what;
    private String hasLicense;
    private String hasComments;
    private String hasRestriction;
    private String where;
    private String hasReputation;
    private String when;
    private String hasArtifact;
    private String hasDomain;
    private String how;
    private String who;
    private ArrayList<ScientistVO> hasInvolved;

    public ContextVO() {
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getHasLicense() {
        return hasLicense;
    }

    public void setHasLicense(String hasLicense) {
        this.hasLicense = hasLicense;
    }

    public String getHasComments() {
        return hasComments;
    }

    public void setHasComments(String hasComments) {
        this.hasComments = hasComments;
    }

    public String getHasRestriction() {
        return hasRestriction;
    }

    public void setHasRestriction(String hasRestriction) {
        this.hasRestriction = hasRestriction;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getHasReputation() {
        return hasReputation;
    }

    public void setHasReputation(String hasReputation) {
        this.hasReputation = hasReputation;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getHasArtifact() {
        return hasArtifact;
    }

    public void setHasArtifact(String hasArtifact) {
        this.hasArtifact = hasArtifact;
    }

    public String getHasDomain() {
        return hasDomain;
    }

    public void setHasDomain(String hasDomain) {
        this.hasDomain = hasDomain;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public ArrayList<ScientistVO> getHasInvolved() {
        return hasInvolved;
    }

    public void setHasInvolved(ArrayList<ScientistVO> hasInvolved) {
        this.hasInvolved = hasInvolved;
    }
    
    
    
}

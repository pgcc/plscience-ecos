/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.workflow.model.output;

/**
 *
 * @author vitorfs
 */
public class TavernaOutput {
    private String name;
    private String absent;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the absent
     */
    public String getAbsent() {
        return absent;
    }

    /**
     * @param absent the absent to set
     */
    public void setAbsent(String absent) {
        this.absent = absent;
    }
}

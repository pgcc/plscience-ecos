/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.workflow.model.runs;

import java.util.ArrayList;

/**
 *
 * @author vitorfs
 */
public class TavernaRunList {
    private ArrayList<TavernaRun> run;
    private TavernaRun singleRun;

    /**
     * @return the run
     */
    public ArrayList<TavernaRun> getRun() {
        if (run == null && singleRun != null) {
            run = new ArrayList<TavernaRun>();
            run.add(singleRun);
        }
        return run;
    }

    /**
     * @param run the run to set
     */
    public void setRun(ArrayList<TavernaRun> run) {
        this.run = run;
    }

    /**
     * @return the singleRun
     */
    public TavernaRun getSingleRun() {
        return singleRun;
    }

    /**
     * @param singleRun the singleRun to set
     */
    public void setSingleRun(TavernaRun singleRun) {
        this.singleRun = singleRun;
    }

}

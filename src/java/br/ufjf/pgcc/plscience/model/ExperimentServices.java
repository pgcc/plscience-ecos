/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author Fran
 * @autor lesimoes
 */
@Entity
@Table(name="experiment_services")
public class ExperimentServices implements Serializable, Comparable<ExperimentServices> {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    @Column(name="stage")
    private Integer stage;
    
    @Column(name="service_name")
    private String service_name;
    
    @Temporal(DATE)
    @Column(name="latestTime_used")
    private Date latestTime;
      
    @ManyToOne
    @JoinColumn(name="idExperiment")
    private Experiment experiment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
        setLatestTime();
    }
    
    public int compareTo(ExperimentServices experimentService) {
        if(this.stage > experimentService.getStage()){
            return 1;
        }     
            return -1;
               
    }

    /**
     * @return the latestTime
     */
    public Date getLatestTime() {
        return latestTime;
    }

    /**
     * @param latestTime the latestTime to set
     */
    public void setLatestTime() {
        this.latestTime = new Date(System.currentTimeMillis());
        System.out.println("Latest Time: " + latestTime.toString());
    }
 
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.vo;


import java.io.Serializable;

/**
 *
 * @author Fran
 */
public class RankingVO implements Serializable, Comparable<RankingVO> {
    private ServiceDescriptionVO serviceRecovery;
    private ServiceDescriptionVO serviceComparison;
    private double similarity;

    public RankingVO() {
    }

    public ServiceDescriptionVO getServiceRecovery() {
        return serviceRecovery;
    }

    public void setServiceRecovery(ServiceDescriptionVO serviceRecovery) {
        this.serviceRecovery = serviceRecovery;
    }

    public ServiceDescriptionVO getServiceComparison() {
        return serviceComparison;
    }

    public void setServiceComparison(ServiceDescriptionVO serviceComparison) {
        this.serviceComparison = serviceComparison;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

   
    @Override
    public int compareTo(RankingVO t) {
        if(this.similarity > t.getSimilarity()){
            return 1;
        }     
            return -1;
    }
    
    
}

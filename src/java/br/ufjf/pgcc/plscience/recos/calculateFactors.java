/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.recos;

import br.ufjf.biocatalogue.model.Result;
import br.ufjf.myexperiment.model.Workflow;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author lesimoes
 */
public class calculateFactors {
    
    private static List<RecosService> servicesList;
    private static List<Workflow> workflowsList;
    private static List<Result> bioList;
    private static int total;
    
    
    public static void setList(List<RecosService> list, int totalService){
        servicesList = list;
        total = totalService;
    }
    
    public static void setList(List<RecosService> list,List<Workflow> workflows ,int totalService){
        setList(list, totalService);
        workflowsList = workflows;
    }
    
    public static void setListBio(List<RecosService> list,List<Result> workflows ,int totalService){
        setList(list, totalService);
        bioList = workflows;
    }
    
    
    
    public static void calculator(){
        for(int i = 0 ; i < servicesList.size() ; i ++){
            ratingCalculator(servicesList.get(i));
            timeCalculator(servicesList.get(i));
        }
    }
    
    

    
    private static void ratingCalculator(RecosService service){
        double rating = ((double) service.getFrequencyService() / (double) total);
        service.setRating(10 * rating);
        
    }
    
    private static void timeCalculator(RecosService service){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


       LocalDate createdAt = LocalDate.parse(service.getCreated_At(), formatter);
       LocalDate updatedAt = LocalDate.parse(service.getUpdated_At(), formatter);
       LocalDate latestUsed;
       
       if(service.getLatestTime_used() == "0000-00-00")
           latestUsed = null;
       else 
           latestUsed =LocalDate.parse(service.getLatestTime_used());
       LocalDate today = LocalDate.now();
      
        
        long lifeTime = ChronoUnit.DAYS.between(today, createdAt);
        long updateTime =  ChronoUnit.DAYS.between(updatedAt, createdAt);
        
        if(updateTime == lifeTime)
            updateTime = 0;
        
        long biasTime = 0;
        if(latestUsed == null)
            biasTime = 0;
        else
           biasTime  = (ChronoUnit.DAYS.between(today, latestUsed));
        double bias = biasTime/(double)lifeTime;
        
       double fresh = (lifeTime + updateTime)/ (double)lifeTime;
        
       double time = fresh + bias;
        
        //System.out.println("Lifetime: " + lifeTime + " biasTime: " + biasTime + " uptadeTime: " + updateTime);
        service.setTime(time);
        
    }
    
    
}

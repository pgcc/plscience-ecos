/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.recos;

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
    private static int total;
    
    
    public static void setList(List<RecosService> list, int totalService){
        servicesList = list;
        total = totalService;
    }
    
    public static void calculator(){
        for(int i = 0 ; i < servicesList.size() ; i ++){
            ratingCalculator(servicesList.get(i));
            timeCalculator(servicesList.get(i));
        }
    }
    
    
    private static void ratingCalculator(RecosService service){
        double rating = ((double) service.getFrequencyService() / (double) total);
        service.setRating(rating);
        
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
      
        
        
        long lifeTime = ChronoUnit.DAYS.between(createdAt, today);
        long biasTime = 0;
        
        if(latestUsed == null)
            biasTime = 0;
        else
           biasTime  = ChronoUnit.DAYS.between(latestUsed, today);
        
        long updateTime =  ChronoUnit.DAYS.between(updatedAt, today);
        double time = ( (double) (lifeTime - biasTime)/updateTime);
        
        service.setTime(time);
        
    }
    

}

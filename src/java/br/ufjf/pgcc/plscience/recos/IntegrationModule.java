/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.recos;

import br.ufjf.biocatalogue.exception.BioCatalogueException;
import br.ufjf.biocatalogue.model.Result;
import br.ufjf.myexperiment.exception.MyExperimentException;
import br.ufjf.myexperiment.model.Workflow;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import br.ufjf.pgcc.plscience.model.ExperimentServices;
import com.hp.hpl.jena.vocabulary.DCTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author lesimoes
 */
public class IntegrationModule {

    private static List<RecosService> servicesList;
    private static ExperimentServices expService;
    private static int totalService;
    private static MidPoint midPoint;
    private static List<Workflow> myExpWorkflows;
    private static List<Result> bioWorkflows;
    
    public static void midPointModel() throws MyExperimentException, BioCatalogueException{
        midPoint = new MidPoint();
        showMidPoint();
    }
    
    public static void showMidPoint(){
        System.out.println("Ponto médio: " + midPoint.toString());
    }
    
    //myExperiment search
    public static void setWorkflows(List<Workflow> workflowList){
        servicesList = new ArrayList<RecosService>();
        myExpWorkflows = workflowList;
        
        
        for(int i = 0 ; i < workflowList.size() ; i ++){
            
           RecosService recosService = new RecosService();
          
           recosService.setId(workflowList.get(i).getId());
           recosService.setResource(workflowList.get(i).getResource());
           recosService.setCreated_At(workflowList.get(i).getCreatedAt());
           recosService.setUpdated_At(workflowList.get(i).getUpdatedAt());
           recosService.setTitle(workflowList.get(i).getDescription());
           
            
          
          
           List frequencyService = new ExperimentDAO().getFrequencyService(workflowList.get(i).getDescription());
           int frequency = Integer.parseInt(frequencyService.get(0).toString());
           recosService.setFrequencyService(frequency);
           
           List latestTime = new ExperimentDAO().getLatestTimeUsed(workflowList.get(i).getDescription());
           String latest = latestTime.toString();
           recosService.setLatestTime_used(latest);
           
           servicesList.add(i, recosService);
           
           
        }
        
        setTotalService();
        calculateFactors(servicesList, totalService);
        saveFactors(servicesList, workflowList);
        showRecos(servicesList);
        
    }

    //BioCatalog search
    public static void setResults(ArrayList<Result> resultsList){
        
        servicesList = new ArrayList<RecosService>();
        
        //Please, put the iterator here...
        for(int i = 0 ; i < resultsList.size() ; i ++){
            RecosService recosService = new RecosService();
            
            recosService.setResource(resultsList.get(i).getResource());
            recosService.setCreated_At(resultsList.get(i).getCreated_at());
            recosService.setUpdated_At(resultsList.get(i).getCreated_at());
            recosService.setTitle(resultsList.get(i).getName());
            
            
           List frequencyService = new ExperimentDAO().getFrequencyService(resultsList.get(i).getName());
           int frequency = Integer.parseInt(frequencyService.get(0).toString());
           recosService.setFrequencyService(frequency);
     
           List latestTime = new ExperimentDAO().getLatestTimeUsed(resultsList.get(i).getDescription());
           String latest = latestTime.toString();
           recosService.setLatestTime_used(latest);
           
           servicesList.add(i, recosService);
           
        }
        
        setTotalService();
        calculateFactors(servicesList, totalService);
        saveFactorsBio(servicesList, resultsList);
        showRecos(servicesList);
    }
    
    
    public static void showRecos(List<RecosService> list){
        
        for(int i = 0 ; i < list.size() ; i ++){
            
            System.out.println("Id: " + list.get(i).getId()+" - Title: " + list.get(i).getTitle() + "\nCreated At: " + list.get(i).getCreated_At()+
                   "\nUpdated At: " + list.get(i).getUpdated_At() + "\nLatest Time Used: " + list.get(i).getLatestTime_used() +
               "\nFrequency: " + list.get(i).getFrequencyService() + "\nTotal Services:" + totalService + 
                    "\nRating: " + list.get(i).getRating() + "\nTime: " + list.get(i).getTime() + 
                    "\nDistance: "  + list.get(i).getRanking() + " \n-----------------------------");
        
        }
        
       
    }
    
    private static void calculateFactors(List<RecosService> list , int totalService){
        calculateFactors.setList(list, totalService);
        calculateFactors.calculator();
    }
    
    private static void saveFactors(List<RecosService> list, List<Workflow> workflowList){
        recommenderModule.setMidPoint(midPoint);
        for(int i = 0 ; i < list.size() ; i ++){
            workflowList.get(i).setRating(list.get(i).getRating());
            workflowList.get(i).setTime(list.get(i).getTime());
            recommenderModule.setPoint(list.get(i));
            double distance = recommenderModule.calculateDistance();
            list.get(i).setRanking(distance);
            workflowList.get(i).setRanking(distance);
        }
        
        orderRanking(list, workflowList);
    }
    
      private static void saveFactorsBio(List<RecosService> list, List<Result> bioList){
        recommenderModule.setMidPoint(midPoint);
        for(int i = 0 ; i < list.size() ; i ++){
            bioList.get(i).setRating(list.get(i).getRating());
            bioList.get(i).setTime(list.get(i).getTime());
            recommenderModule.setPoint(list.get(i));
            double distance = recommenderModule.calculateDistance();

            list.get(i).setRanking(distance);
            bioList.get(i).setRanking(distance);
        }
        
        orderRankingBio(list, bioList);
    }
    
    private static void orderRanking(List<RecosService> list, List<Workflow> workflowList){
        Collections.sort(list, new Comparator<RecosService>(){

            @Override
            public int compare(RecosService o1, RecosService o2) {
              
              if(o1.getRanking() > o2.getRanking())
                  return 1;
              if(o1.getRanking() < o2.getRanking())
                  return -1;
              if(o1.getRanking() == o2.getRanking()){
                  if(o1.getTime() > o2.getTime())
                      return -1;
                  if(o1.getTime() < o2.getTime())
                      return 1;
                      }
              return 0;
              
            }
        
    });
        
        Collections.sort(list, new Comparator<RecosService>() {

            @Override
            public int compare(RecosService o1, RecosService o2) {
                double time = o1.getTime();
                double rating = o1.getRating();
             
                if((time > midPoint.getTime()) && (rating > midPoint.getRating()) ){
                    if(o1.getTime()> o2.getTime()){
                        return 1;
                    }
                    return -1;
                } 
                    
               if(rating > midPoint.getRating())
                        return -1;
               return 0;
            }
        });
        
        Collections.sort(workflowList, new Comparator<Workflow>(){
            
            
            @Override
            public int compare(Workflow o1, Workflow o2) {
              if(o1.getRanking() > o2.getRanking())
                  return 1;
              if(o1.getRanking() < o2.getRanking())
                  return -1;
              if(o1.getRanking() == o2.getRanking()){
                  if(o1.getTime() > o2.getTime())
                      return -1;
                  if(o1.getTime() < o2.getTime())
                      return 1;
                      }
              return 0;
              
            }
        });
        
        Collections.sort(workflowList, new Comparator<Workflow>() {

            @Override
            public int compare(Workflow o1, Workflow o2) {
                double time = o1.getTime();
                double rating = o1.getRating();
             
                if((time > midPoint.getTime()) && (rating > midPoint.getRating()) ){
                    if(o1.getRanking() > o2.getRanking()){
                        
                        return -1;
                    }
                    return 0;
                }
                    
                return 0;
            }
        });
    }
        
        
       private static void orderRankingBio(List<RecosService> list, List<Result> bioList){
        Collections.sort(list, new Comparator<RecosService>(){

            @Override
            public int compare(RecosService o1, RecosService o2) {
              if(o1.getRanking() > o2.getRanking())
                  return 1;
              if(o1.getRanking() < o2.getRanking())
                  return -1;
              if(o1.getRanking() == o2.getRanking()){
                  if(o1.getTime() > o2.getTime())
                      return -1;
                  if(o1.getTime() < o2.getTime())
                      return 1;
                      }
              return 0;
              
            }
        
    });
        
        Collections.sort(list, new Comparator<RecosService>() {

            @Override
            public int compare(RecosService o1, RecosService o2) {
                double time = o1.getTime();
                double rating = o1.getRating();
             
                if((time > midPoint.getTime()) && (rating > midPoint.getRating()) ){
                    if(o1.getRanking() > o2.getRanking()){
                        return 1;
                    }
                    
                }
                    
               if(rating > midPoint.getRating())
                        return -1;
               return 0;
            }
        });
        
        Collections.sort(bioList, new Comparator<Result>(){
            
            
            @Override
            public int compare(Result o1, Result o2) {
              if(o1.getRanking() > o2.getRanking())
                  return 1;
              if(o1.getRanking() < o2.getRanking())
                  return -1;
              if(o1.getRanking() == o2.getRanking()){
                  if(o1.getTime() > o2.getTime())
                      return -1;
                  if(o1.getTime() < o2.getTime())
                      return 1;
                      }
              return 0;
              
            }
        });
        
        Collections.sort(bioList, new Comparator<Result>() {

            @Override
            public int compare(Result o1, Result o2) {
                double time = o1.getTime();
                double rating = o1.getRating();
             
                if((time > midPoint.getTime()) && (rating > midPoint.getRating()) ){
                    if(o1.getRanking() > o2.getRanking()){
                        
                        return -1;
                    }
                    return 0;
                }
                    
                return 0;
            }
        });
        
        
    }
    
    public static void setTotalService(){
        List totalServices = new ExperimentDAO().getTotalService();
        totalService = Integer.parseInt(totalServices.get(0).toString());
    }
    
    public static int getTotalService(){
        return totalService;
    }
    
    public static List getWorkflows(){
        return myExpWorkflows;
    }
    
    

    
}

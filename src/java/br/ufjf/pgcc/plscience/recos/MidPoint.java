/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.recos;

import br.ufjf.biocatalogue.core.BioCatalogueClient;
import br.ufjf.biocatalogue.exception.BioCatalogueException;
import br.ufjf.biocatalogue.model.Result;
import br.ufjf.myexperiment.core.MyExperimentClient;
import br.ufjf.myexperiment.exception.MyExperimentException;
import br.ufjf.myexperiment.model.Search;
import br.ufjf.myexperiment.model.Workflow;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author lesimoes
 */
public class MidPoint {
    
    private double rating;
    private double time;
    private static MyExperimentClient client;
    private static BioCatalogueClient bioClient;
    private List<Workflow> workflows;
    private static List<RecosService> recosList;
    private ArrayList<Result> results;
    private static List<Double> timeFactor;
    
    public MidPoint() throws MyExperimentException, BioCatalogueException{
        setRating();
        setTime();
    }
    
    public void setRating(){
        calculateRating();
    }
    
    public double getRating(){
        return this.rating;
    }
    
    public void setTime() throws MyExperimentException, BioCatalogueException{
        calculateTime();
    }
    
    
    public double getTime(){
        return this.time;
    }
    
    @Override
    public String toString(){
        return "Rating: " + String.valueOf(this.getRating()) + "\nTime: " + String.valueOf(this.getTime());
    }
    
    
    private void calculateRating(){
        
        List servicesRatings = new ExperimentDAO().getServicesRatings();
        double geometricMean = 1;
        for(int i = 0 ; i < servicesRatings.size() ; i ++){
            geometricMean = geometricMean * Double.parseDouble(servicesRatings.get(i).toString());
        }
        
        this.rating = Math.pow(geometricMean, 1 / (double) servicesRatings.size());

    }
    
    private void calculateTime() throws MyExperimentException, BioCatalogueException{
        recosList = new ArrayList<RecosService>();
        timeFactor = new ArrayList<Double>();
        double geometricMean = 1;
        List servicesNames = new ExperimentDAO().getServicesNames();
        for(int i = 0 ; i < servicesNames.size() ; i ++){
              searchMyExperiment(servicesNames, i);
              searchBioCatalog(servicesNames, i);
              timeFactor(recosList.get(i));
              geometricMean = geometricMean *  timeFactor.get(i);
        }
            
           this.time = Math.pow(geometricMean, 1/(double)servicesNames.size());
//        Collections.sort(timeFactor);
//        
//        this.time = ( (double) timeFactor.get(0) + timeFactor.get(timeFactor.size() -1)) / 2;
//      
        

    }
    

    
    private void searchMyExperiment(List servicesNames, int index) throws MyExperimentException{
        client = new MyExperimentClient();
        client.setBaseUri("http://www.myexperiment.org");
        String query = servicesNames.get(index).toString();
        Search result = client.search(query);
       
        if(result.getWorkflow()!= null){
            
            workflows = result.getWorkflow();
            
            RecosService recosService = new RecosService();

               recosService.setId(workflows.get(0).getId());
               recosService.setResource(workflows.get(0).getResource());
               recosService.setCreated_At(workflows.get(0).getCreatedAt());
               recosService.setUpdated_At(workflows.get(0).getUpdatedAt());
               recosService.setTitle(workflows.get(0).getDescription());

               List latestTime = new ExperimentDAO().getLatestTimeUsed(workflows.get(0).getDescription());
               String latest = latestTime.toString();
               recosService.setLatestTime_used(latest);

               recosList.add(recosService);

        }
           
    }
    
    private void searchBioCatalog(List servicesNames, int index) throws BioCatalogueException{
        bioClient = new BioCatalogueClient();
        bioClient.setBaseUri("https://www.biocatalogue.org");
        String query = servicesNames.get(index).toString();
       
        br.ufjf.biocatalogue.model.Search result = bioClient.search(query);
        //System.out.print("RESULTTT " + result.getTotal() + " " + result.getSearch_query());
        if (result.getTotal() != 0) {
                results = result.getResults();
            
        
        RecosService recosService = new RecosService();
        
         recosService.setResource(results.get(0).getResource());
         recosService.setCreated_At(results.get(0).getCreated_at());
         recosService.setUpdated_At(results.get(0).getCreated_at());
         recosService.setTitle(results.get(0).getName());
        
         List latestTime = new ExperimentDAO().getLatestTimeUsed(results.get(0).getDescription());
         String latest = latestTime.toString();
         recosService.setLatestTime_used(latest);
         
         recosList.add(recosService);
         
        }
         
    }
    
    private void timeFactor(RecosService service){
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
       
       timeFactor.add(time);
        //System.out.println("Lifetime: " + lifeTime + " biasTime: " + biasTime + " uptadeTime: " + updateTime);
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.recos;

import br.ufjf.myexperiment.model.Workflow;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import br.ufjf.pgcc.plscience.model.ExperimentServices;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author lesimoes
 */
public class IntegrationModule {

    private static List<RecosService> servicesList;
    private static ExperimentServices expService;
    private static int totalService;
    
    
    public static void setWorkflows(List<Workflow> workflowList){
        servicesList = new ArrayList<RecosService>();
       
        
        
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
        showRecos(servicesList);
        
    }
    
    public static void showRecos(List<RecosService> list){
        for(int i = 0 ; i < list.size() ; i ++){
            System.out.println("Id: " + list.get(i).getId()+" - Title: " + list.get(i).getTitle() + "\nCreated At: " + list.get(i).getCreated_At()+
                   "\nUpdated At: " + list.get(i).getUpdated_At() + "\nLatest Time Used: " + list.get(i).getLatestTime_used() +
               "\nFrequency: " + list.get(i).getFrequencyService() + "\nTotal Services:" + totalService + 
                    "\nRating: " + list.get(i).getRating() + "\nTime: " + list.get(i).getTime() + " \n-----------------------------");
        
        }
        
       
    }
    
    private static void calculateFactors(List<RecosService> list, int totalService){
        calculateFactors.setList(list, totalService);
        calculateFactors.calculator();
    }
    
    public static void setTotalService(){
        List totalServices = new ExperimentDAO().getTotalService();
        totalService = Integer.parseInt(totalServices.get(0).toString());
    }
    
    public static int getTotalService(){
        return totalService;
    }
    
}

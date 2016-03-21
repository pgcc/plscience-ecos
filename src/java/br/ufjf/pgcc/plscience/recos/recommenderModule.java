/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.recos;


import java.util.List;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
/**
 * 
 * @author lesimoes
 */

public abstract class recommenderModule {
    
    private static List<RecosService> servicesList;
    private static MidPoint modelPoint;
    private static double[] model;
    private static double[] point;
    
    public static void setServicesList(List<RecosService> servicesList){
        servicesList = servicesList;
   
    }
    
    public static void setMidPoint(MidPoint midPoint){
        modelPoint = midPoint;
        setModel();
    }
    
    private static void setModel(){
        model = new double[2];
        model[0] = (double) modelPoint.getTime();
        model[1] = (double) modelPoint.getRating();
    }
    
    
    public static void setPoint(RecosService pointService){
        point = new double[2];
        point[0] = (double) pointService.getRating();
        point[1] = (double) pointService.getRating();
    }
    
    public static double calculateDistance(){
        EuclideanDistance distance = new EuclideanDistance();
        return distance.compute(model, point);
    }
    
}

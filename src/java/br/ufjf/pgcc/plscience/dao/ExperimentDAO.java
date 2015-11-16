/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.model.ExperimentServices;
import br.ufjf.pgcc.plscience.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author vitorfs
 * @author lesimoes
 */
public class ExperimentDAO extends GenericDAO {

    public static ExperimentDAO expirimentDAO;

    public static ExperimentDAO getInstance() {
        if (expirimentDAO == null) {
            expirimentDAO = new ExperimentDAO();
        }
        return expirimentDAO;
    }

//    public Experiment getExperimentByName(String nome) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        Query query = em.createQuery("select a from Experiment As a where a.name =:nome ");
//        query.setParameter("nome", nome.toUpperCase());
//
//        List<Experiment> expiriments = query.getResultList();
//        if (expiriments != null && expiriments.size() > 0) {
//            return expiriments.get(0);
//        }
//
//        return null;
//    }
    
    public void remove(Experiment expiriment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            expiriment = em.merge(expiriment);
            em.remove(expiriment);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
        finish();
    }
    
    public void save(Experiment experiment) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(experiment);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void updateExperimentServices(ExperimentServices experimentServ) {
        
        //Recuperando Experiment pelo id
        Experiment ex = getExperimentById(experimentServ.getExperiment().getIdExperiment());
        //Experiment ex = getId(experimentServ.getExperiment().getId());
        experimentServ.setExperiment(ex);
        
        //Verificar se experimentServ � novo ou n�o
        if(experimentServ.getId().equals((long) 0)){
            //Salvando experiment services
            experimentServ.setId(null);
//            getEntityManager().getTransaction().begin();
//            getEntityManager().persist(experimentServ);
//            getEntityManager().getTransaction().commit();
//            finish();
            saveExperimentService(experimentServ);
        }else{
            //Recuperando id
            ExperimentServices exServ = getEntityManager().find(ExperimentServices.class,experimentServ.getId());
            
//            getEntityManager().getTransaction().begin();
            exServ.setService_name(experimentServ.getService_name());
            exServ.setStage(experimentServ.getStage());
            exServ.setLatestTime();
//           
//            getEntityManager().getTransaction().commit();
//            finish();
            saveExperimentService(exServ);
        }
    }
    
    public void recreateExperimentServices(ExperimentServices experimentServ) {
        
        //Recuperando Experiment pelo id
        Experiment ex = getId(experimentServ.getExperiment().getIdExperiment());
        experimentServ.setExperiment(ex);
        
        //Verificar se experimentServ � novo ou n�o
        if(experimentServ.getId().equals((long) 0)){
            //Salvando experiment services
            experimentServ.setId(null);
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(experimentServ);
            getEntityManager().getTransaction().commit();
            finish();
        }else{
            //Recuperando id
            ExperimentServices exServ = getEntityManager().find(ExperimentServices.class,experimentServ.getId());
            
            getEntityManager().getTransaction().begin();
            exServ.setService_name(experimentServ.getService_name());
            exServ.setStage(experimentServ.getStage());
            getEntityManager().remove(exServ);
            getEntityManager().getTransaction().commit();
            finish(); 
        }
        
        
        //Recuperando id
       /* ExperimentServices exServ = ExperimentServicesGetbyNameandStage(experiment.getExperimentServices().getService_name(),experiment.getExperimentServices().getStage());
        
        getEntityManager().getTransaction().begin();
        Experiment ex = getEntityManager().find(Experiment.class, experiment.getId());
        ex.setExperimentServices(exServ);
        //getEntityManager().merge(experiment);
        getEntityManager().getTransaction().commit();
        finish();    */
    }
    
    public void updateNumberStages(int id, Integer n){
        //Recuperando id
        Experiment ex = getEntityManager().find(Experiment.class,id);
        
        getEntityManager().getTransaction().begin();
        ex.setNumberStages(n);
        //getEntityManager().merge(experiment);
        getEntityManager().getTransaction().commit();
        finish();    
        
    }
    
    public void update(Experiment experiment){
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(experiment);
        getEntityManager().getTransaction().commit();
        finish();
        
    }
    
    
        public List getFrequencyService(String service_name){
        String consult = "SELECT COUNT(*) FROM experiment_services WHERE service_name LIKE ?service_name";
        Query query = getEntityManager().createNativeQuery(consult);
        query.setParameter("service_name", "%" + service_name + "%");
        
        List frequencyService = query.getResultList();
//        System.out.println("Service " + service_name + " FREQUENCY " + frequencyService.toString());
       // ExperimentServices expService = new ExperimentServices();
        
        finish();
        return frequencyService;
    }
        
        public List getTotalService(){
            String consult = "SELECT COUNT(*) FROM experiment_services";
            Query query = getEntityManager().createNativeQuery(consult);
            
            List totalService = query.getResultList();
           
            finish();
            return totalService;
        }
        
        
        public List getLatestTimeUsed(String service_name){
            String consult = "SELECT latestTime_used FROM experiment_services WHERE service_name LIKE ?service_name ORDER BY latestTime_used DESC LIMIT 1" ;
            Query query = getEntityManager().createNativeQuery(consult);
            query.setParameter("service_name", "%" + service_name + "%");
            List latestTime = query.getResultList();
            finish();
            return latestTime;
        }
    
    
    public List<Experiment> getAll() {
        Query query = getEntityManager().createQuery("SELECT e FROM Experiment e");
        List<Experiment> experiments = query.getResultList();
        finish();
        return experiments;
    }
    
     public List<ExperimentServices> getAllExperimentServices() {
        Query query = getEntityManager().createQuery("SELECT e FROM ExperimentServices e");
        List<ExperimentServices> experimentServices = query.getResultList();
        finish();
        return experimentServices;
    }
    
    public Experiment getId(int id) {
        Query query = getEntityManager().createQuery("SELECT e FROM Experiment e  where e.idExperiment =:id");
        query.setParameter("id", id);
        List<Experiment> experiments = query.getResultList();
        Experiment experiment = new Experiment();
        for(Experiment e: experiments){
            if(e.getIdExperiment().equals(id)){
                experiment = e;
            }
        }
        finish();
        return experiment;
    }
    
    public ExperimentServices ExperimentServicesGetbyNameandStage(String name, Integer stage){
        Query query = getEntityManager().createQuery("SELECT e FROM ExperimentServices e");
        List<ExperimentServices> experiments = query.getResultList();
        ExperimentServices experimentService = new ExperimentServices();
        for(ExperimentServices e: experiments){
            if(e.getService_name().equals(name) && e.getStage().equals(stage)){
                experimentService = e;
            }
        }
        finish();
        return experimentService;
    }
    
    /**
     * Persite uma ExperimentServices
     * 
     * @param experimentServices
     * @return
     */
    public ExperimentServices saveExperimentService(ExperimentServices experimentServices) {
        
        getEntityManager().getTransaction().begin();
        try {
            experimentServices = getEntityManager().merge(experimentServices);
            getEntityManager().getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
        return experimentServices;
    }
    
    public ExperimentServices findServices(Integer stage, Long id) {
        
        Query query = getEntityManager().createQuery("select a from ExperimentServices As a where a.stage =:stage and a.experiment.id =:id ");
        query.setParameter("stage", stage);
        query.setParameter("id", id);

        List<ExperimentServices> experiments = query.getResultList();
        if (experiments != null && experiments.size() > 0) {
            return experiments.get(0);
        }

        return null;
    }
    
    /**
     * Busca uma especifica
     *
     * @param id
     * @return
     */
    public Experiment getExperimentById(int id) {
        
        Query query = getEntityManager().createQuery("select a from Experiment As a where a.idExperiment =:id ");
        query.setParameter("id", id);

        List<Experiment> expiriments = query.getResultList();
        if (expiriments != null && expiriments.size() > 0) {
            return expiriments.get(0);
        }

        return null;
    }
}
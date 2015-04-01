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
 */
public class ExperimentDAO extends GenericDAO {
    
//    public void save(Experiment experiment) {
//        getEntityManager().getTransaction().begin();
//        getEntityManager().persist(experiment);
//        getEntityManager().getTransaction().commit();
//        finish();
//    }
//    
//    /**
//     * Remove uma expiriment
//     *
//     * @param experiment
//     */
//    public void remove(Experiment experiment) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        em.getTransaction().begin();
//        experiment = em.merge(experiment);
//        em.remove(experiment);
//        em.getTransaction().commit();
//        em.close();
//    }
//    
//    public void updateExperimentServices(ExperimentServices experimentServ) {
//        
//        //Recuperando Experiment pelo id
//        Experiment ex = getExperiment(experimentServ.getExperiment().getIdExperiment());
//        //Experiment ex = getId(experimentServ.getExperiment().getId());
//        experimentServ.setExperiment(ex);
//        
//        //Verificar se experimentServ é novo ou não
//        if(experimentServ.getId().equals((long) 0)){
//            //Salvando experiment services
//            experimentServ.setId(null);
//            getEntityManager().getTransaction().begin();
//            getEntityManager().persist(experimentServ);
//            getEntityManager().getTransaction().commit();
//            finish();
//        }else{
//            //Recuperando id
//            ExperimentServices exServ = getEntityManager().find(ExperimentServices.class,experimentServ.getId());
//            
//            getEntityManager().getTransaction().begin();
//            exServ.setService_name(experimentServ.getService_name());
//            exServ.setStage(experimentServ.getStage());
//           
//            getEntityManager().getTransaction().commit();
//            finish(); 
//        }
//        
//        
//        //Recuperando id
//       /* ExperimentServices exServ = ExperimentServicesGetbyNameandStage(experiment.getExperimentServices().getService_name(),experiment.getExperimentServices().getStage());
//        
//        getEntityManager().getTransaction().begin();
//        Experiment ex = getEntityManager().find(Experiment.class, experiment.getId());
//        ex.setExperimentServices(exServ);
//        //getEntityManager().merge(experiment);
//        getEntityManager().getTransaction().commit();
//        finish();    */
//    }
//    
//    public void updateNumberStages(Integer id, Integer n){
//        //Recuperando id
//        Experiment ex = getEntityManager().find(Experiment.class,id);
//        
//        getEntityManager().getTransaction().begin();
//        ex.setNumberStages(n);
//        //getEntityManager().merge(experiment);
//        getEntityManager().getTransaction().commit();
//        finish();    
//        
//    }
//    
//    public void update(Experiment experiment){
//        getEntityManager().getTransaction().begin();
//        getEntityManager().merge(experiment);
//        getEntityManager().getTransaction().commit();
//        finish();
//        
//    }
//    
//    public List<Experiment> getAll() {
//        Query query = getEntityManager().createNativeQuery("SELECT e FROM Experiment e");
//        List<Experiment> experiments = query.getResultList();
//        finish();
//        return experiments;
//    }
//    
//     public List<ExperimentServices> getAllExperimentServices() {
//        Query query = getEntityManager().createQuery("SELECT e FROM ExperimentServices e");
//        List<ExperimentServices> experimentServices = query.getResultList();
//        finish();
//        return experimentServices;
//    }
//    
//    public Experiment getExperiment(Integer idExperiment) {
//        Query query = getEntityManager().createQuery("SELECT e FROM Experiment e");
//        List<Experiment> experiments = query.getResultList();
//        Experiment experiment = new Experiment();
//        for(Experiment e: experiments){
//            if(e.getIdExperiment().equals(idExperiment)){
//                experiment = e;
//            }
//        }
//        finish();
//        return experiment;
//    }
//    
//    public ExperimentServices ExperimentServicesGetbyNameandStage(String name, Integer stage){
//        Query query = getEntityManager().createQuery("SELECT e FROM ExperimentServices e");
//        List<ExperimentServices> experiments = query.getResultList();
//        ExperimentServices experimentService = new ExperimentServices();
//        for(ExperimentServices e: experiments){
//            if(e.getService_name().equals(name) && e.getStage().equals(stage)){
//                experimentService = e;
//            }
//        }
//        finish();
//        return experimentService;
//    }
//    
//    //search by name
//     /**
//     * Busca uma especifica
//     *
//     * @param name
//     * @return
//     */
//    public Experiment searchByName(String name) {        
//        EntityManager em = PersistenceUtil.getEntityManager();
//        Query query = em.createQuery("select a from Experiment As a where a.description = :name ");
//        query.setParameter("name", name.toUpperCase());
//        List<Experiment> expiriments = query.getResultList();
//        em.close();
//        if (expiriments != null && expiriments.size() > 0) {
//            return expiriments.get(0);
//        }
//        return null;
//    }
    
    public static ExperimentDAO expirimentDAO;

    public static ExperimentDAO getInstance() {
        if (expirimentDAO == null) {
            expirimentDAO = new ExperimentDAO();
        }
        return expirimentDAO;
    }

    /**
     * Busca uma especifica
     *
     * @param nome
     * @return
     */
    public Experiment getExperimentByName(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Experiment As a where a.description =:nome ");
        query.setParameter("nome", nome.toUpperCase());

        List<Experiment> expiriments = query.getResultList();
        if (expiriments != null && expiriments.size() > 0) {
            return expiriments.get(0);
        }

        return null;
    }

    /**
     * Busca todas
     *
     * @return
     */
    public List<Experiment> getAll() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Experiment As a");
        return (List<Experiment>) query.getResultList();
    }

    /**
     * Remove uma experiment
     *
     * @param expiriment
     */
    public void remove(Experiment expiriment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        expiriment = em.merge(expiriment);
        em.remove(expiriment);
        em.getTransaction().commit();
    }

    /**
     * Persite uma Experiment 
     *
     * @param experiment 
     * @return
     */
    public Experiment save(Experiment experiment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            experiment = em.merge(experiment);
            em.getTransaction().commit();
            System.out.println("Registro gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return experiment;
    }
    
    /**
     * Busca uma especifica
     *
     * @param nome
     * @return
     */
    public Experiment getExperimentById(Integer idExperiment) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Experiment As a where a.idExperiment =:idExperiment ");
        query.setParameter("idExperiment", idExperiment);

        List<Experiment> expiriments = query.getResultList();
        if (expiriments != null && expiriments.size() > 0) {
            return expiriments.get(0);
        }

        return null;
    }
    
    public void updateExperimentServices(ExperimentServices experimentServ) {
        
        //Recuperando Experiment pelo id
        Experiment ex = getExperimentById(experimentServ.getExperiment().getIdExperiment());
        //Experiment ex = getId(experimentServ.getExperiment().getId());
        experimentServ.setExperiment(ex);
        
        //Verificar se experimentServ é novo ou não
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
    
    public List<ExperimentServices> getAllExperimentServices() {
        Query query = getEntityManager().createQuery("SELECT e FROM ExperimentServices AS e");
        List<ExperimentServices> experimentServices = query.getResultList();
        finish();
        return experimentServices;
    }
    
    public void updateNumberStages(Integer id, Integer n){
        //Recuperando id
        Experiment ex = getEntityManager().find(Experiment.class,id);
        
        getEntityManager().getTransaction().begin();
        ex.setNumberStages(n);
        //getEntityManager().merge(experiment);
        getEntityManager().getTransaction().commit();
        finish();    
        
    }
}

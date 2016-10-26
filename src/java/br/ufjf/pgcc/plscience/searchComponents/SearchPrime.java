/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import br.ufjf.pgcc.plscience.dao.CollaborationServiceDAO;
import br.ufjf.pgcc.plscience.interoperability.ServiceRecovery;
import br.ufjf.pgcc.plscience.interoperability.SimilarityCalculation1;
import br.ufjf.pgcc.plscience.model.CollaborationService;
import br.ufjf.pgcc.plscience.vo.RankingVO;
import br.ufjf.pgcc.plscience.vo.ServiceDescriptionVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author phillipe
 */

@ManagedBean(name = "searchPrime")
@ViewScoped
public class SearchPrime {
   
    //PRIME 
    private ServiceDescriptionVO serviceUsed;
    private ServiceDescriptionVO serviceDescriptionVO;//form data
    private List<ServiceDescriptionVO> services;
    

    public void searchServices(){
        ServiceRecovery sr = new ServiceRecovery();
        List<ServiceDescriptionVO> servicesRankingSorted = new ArrayList<ServiceDescriptionVO>();
        List<ServiceDescriptionVO> servicesCollabSorted = new ArrayList<ServiceDescriptionVO>();
        List<ServiceDescriptionVO> servicesRecovery;
        servicesRecovery = sr.RecoveryPrimeSearch();
        SimilarityCalculation1 sc1 = new SimilarityCalculation1();
        ArrayList<RankingVO> rankingServices = new ArrayList<>();
        
        for(ServiceDescriptionVO sVo: servicesRecovery){
            RankingVO rVo = new RankingVO();
            rVo.setServiceRecovery(sVo);
            rVo.setServiceComparison(getServiceDescriptionVO());
            
            rVo.setSimilarity(sc1.calculate(getServiceDescriptionVO(), sVo, 1, 1, 1));
            rankingServices.add(rVo);
        }
        Collections.sort(rankingServices);
        
        for(RankingVO rank: rankingServices){
            servicesRankingSorted.add(rank.getServiceRecovery());
            System.out.println("Nome Serv: " + rank.getServiceRecovery().getName() +" Simil: " + rank.getSimilarity());
        }
        
        //Recupera os Serviços de Colaboração.
        for(ServiceDescriptionVO serviceCollab: servicesRankingSorted) {
            if(serviceCollab.getIncludesPragmatic().getIncludesContext().getHasDomain().equalsIgnoreCase("Servico de Colaboracao") ||
            serviceCollab.getIncludesPragmatic().getIncludesContext().getHasDomain().equalsIgnoreCase("Collaborative Service") ||
            serviceCollab.getIncludesPragmatic().getIncludesContext().getHasDomain().equalsIgnoreCase("Collaboration Service")) {
                servicesCollabSorted.add(serviceCollab);
            }            
        }

        setServices(servicesCollabSorted);                      
    }    

    /**
     * @return the serviceUsed
     */
    public ServiceDescriptionVO getServiceUsed() {
        return serviceUsed;
    }

    /**
     * @param serviceUsed the serviceUsed to set
     */
    public void setServiceUsed(ServiceDescriptionVO serviceUsed) {
        this.serviceUsed = serviceUsed;
    }

    /**
     * @return the serviceDescriptionVO
     */
    public ServiceDescriptionVO getServiceDescriptionVO() {
        return serviceDescriptionVO;
    }

    /**
     * @param serviceDescriptionVO the serviceDescriptionVO to set
     */
    public void setServiceDescriptionVO(ServiceDescriptionVO serviceDescriptionVO) {
        this.serviceDescriptionVO = serviceDescriptionVO;
    }

    /**
     * @return the services
     */
    public List<ServiceDescriptionVO> getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(List<ServiceDescriptionVO> services) {
        this.services = services;
    }
    }
    



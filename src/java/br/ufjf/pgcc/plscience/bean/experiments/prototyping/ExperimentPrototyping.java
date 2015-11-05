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
package br.ufjf.pgcc.plscience.bean.experiments.prototyping;


import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import br.ufjf.pgcc.plscience.interoperability.ServiceRecovery;
import br.ufjf.pgcc.plscience.interoperability.ServiceRegistration;
import br.ufjf.pgcc.plscience.interoperability.SimilarityCalculation1;
import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.model.ExperimentServices;
import br.ufjf.pgcc.plscience.services.EquivalentServicesResource;
import br.ufjf.pgcc.plscience.vo.ContextVO;
import br.ufjf.pgcc.plscience.vo.HardwareVO;
import br.ufjf.pgcc.plscience.vo.PragmaticVO;
import br.ufjf.pgcc.plscience.vo.RankingVO;
import br.ufjf.pgcc.plscience.vo.SemanticVO;
import br.ufjf.pgcc.plscience.vo.ServiceDescriptionVO;
import br.ufjf.pgcc.plscience.vo.SyntacticVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class ExperimentPrototyping implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String numberStages;
    private String draw;
    private ArrayList<String> stages;
    private ServiceDescriptionVO serviceDescriptionVO;
    private List<ServiceDescriptionVO> services;
    private Experiment experiment;
    private String serviceName;
    private String equivalencesResult;
    
    


    public ExperimentPrototyping() {
        experiment =new Experiment();
        serviceDescriptionVO = new ServiceDescriptionVO();
        SyntacticVO sync = new SyntacticVO();
        serviceDescriptionVO.setIncludesSyntactic(sync);
        SemanticVO sem = new SemanticVO();
        serviceDescriptionVO.setIncludesSemantic(sem);
        PragmaticVO prag = new PragmaticVO();
        serviceDescriptionVO.setIncludesPragmatic(prag);
        ArrayList<String> funcs = new ArrayList();
        serviceDescriptionVO.getIncludesSemantic().setHasFunctionalRequirements(funcs);
        ContextVO con = new ContextVO();
        serviceDescriptionVO.getIncludesPragmatic().setIncludesContext(con);
        HardwareVO hard = new HardwareVO();
        serviceDescriptionVO.getIncludesPragmatic().setIncludesHardware(hard);
        services = new ArrayList<ServiceDescriptionVO>();
    }
    
    public void drawStages(String nStages){
        numberStages = nStages;
        stages = new ArrayList<String>();
        for(int i=0;i<Integer.valueOf(nStages);i++){
            stages.add("Stage "+i);
        }
            
    }

    public String getNumberStages() {
        return numberStages;
    }

    public void setNumberStages(String numberStages) {
        this.numberStages = numberStages;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public ArrayList<String> getStages() {
        return stages;
    }

    public void setStages(ArrayList<String> stages) {
        this.stages = stages;
    }
    
    public ServiceDescriptionVO getServiceDescriptionVO() {
        return serviceDescriptionVO;
    }

    public void setServiceDescriptionVO(ServiceDescriptionVO serviceDescriptionVO) {
        this.serviceDescriptionVO = serviceDescriptionVO;
    }

    public List<ServiceDescriptionVO> getServices() {
        return services;
    }

    public void setServices(List<ServiceDescriptionVO> services) {
        this.services = services;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String service_name) {
        this.serviceName = service_name;
    }

    public String getEquivalencesResult() {
        return equivalencesResult;
    }

    public void setEquivalencesResult(String equivalencesResult) {
        this.equivalencesResult = equivalencesResult;
    }
    
    

    public void searchServices(){
        
        System.out.println(serviceDescriptionVO.getIncludesSyntactic().getHasAddress());
        System.out.println(serviceDescriptionVO.getIncludesSyntactic().getHasReturn());
        System.out.println(serviceDescriptionVO.getIncludesSemantic().getHasSemanticReturn());
        System.out.println(serviceDescriptionVO.getIncludesSemantic().getHasSemanticReception());
        System.out.println(serviceDescriptionVO.getIncludesSemantic().getHasSemanticRepresentation());
        System.out.println(serviceDescriptionVO.getIncludesSemantic().getHasFunctionalRequirement());
        ArrayList<String> s = new ArrayList();
        s.add(serviceDescriptionVO.getIncludesSemantic().getHasFunctionalRequirement());
        serviceDescriptionVO.getIncludesSemantic().setHasFunctionalRequirements(s);
        System.out.println(serviceDescriptionVO.getIncludesSemantic().getHasFunctionalRequirements().get(0));
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getHasNonFunctionalReq());
        ArrayList<String> s2 = new ArrayList();
        s2.add(serviceDescriptionVO.getIncludesPragmatic().getHasNonFunctionalReq());
        serviceDescriptionVO.getIncludesPragmatic().setHasNonFunctionalRequirement(s2);
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getHasNonFunctionalRequirement().get(0));
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasArtifact());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasComments());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasDomain());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasLicense());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasReputation());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasRestriction());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHow());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getWhen());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getWhere());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getWho());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasCPU());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasOperationalSystem());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasOperationalSystem());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasRAM());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasROM());
        
        ServiceRecovery sr = new ServiceRecovery();
        List<ServiceDescriptionVO> servicesRecovery;
        List<ServiceDescriptionVO> servicesRankingSorted = new ArrayList<ServiceDescriptionVO>();
        servicesRecovery = sr.Recovery();
        SimilarityCalculation1 sc1 = new SimilarityCalculation1();
        ArrayList<RankingVO> rankingServices = new ArrayList<RankingVO>();
        
        for(ServiceDescriptionVO sVo: servicesRecovery){
            RankingVO rVo = new RankingVO();
            rVo.setServiceRecovery(sVo);
            rVo.setServiceComparison(serviceDescriptionVO);
            
            rVo.setSimilarity(sc1.calculate(serviceDescriptionVO, sVo, 1, 1, 1));
            rankingServices.add(rVo);
        }
        Collections.sort(rankingServices);
        
        for(RankingVO rank: rankingServices){
            servicesRankingSorted.add(rank.getServiceRecovery());
            System.out.println("Nome Serv: " + rank.getServiceRecovery().getName() +" Simil: " + rank.getSimilarity());
        }
        //setServices(sr.Recovery());
        setServices(servicesRankingSorted);
                       
    }
    
     public void searchServicesAll(){
        ServiceRecovery sr = new ServiceRecovery();
        setServices(sr.Recovery());        
    }
   
    public void updates(String input, String id, String stage, String idExServ, String numberSt){
       String[] s= stage.split(" ");
       System.out.println("Estou passando "+ input +" iD "+ id + " Stage " +s[1] );
      
       
       try {
           ExperimentDAO exDao = new ExperimentDAO();
           Experiment ex = new Experiment();
           ExperimentServices exServ = new ExperimentServices();
           exServ.setService_name(input);
           exServ.setStage(Integer.parseInt(s[1]));
           exServ.setId(Long.parseLong(idExServ));
           ex.setIdExperiment(Integer.parseInt(id));
           exServ.setExperiment(ex);
           System.out.println("O ID do ExServi e esse aqui galera: "+idExServ);
           exDao.updateExperimentServices(exServ);
           
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Experiment updated with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }
    
 
    
    public void registerServices(){
        System.out.println("Registreiii");
        if(null==serviceDescriptionVO.getIncludesSyntactic().getHasAddress()){
            serviceDescriptionVO.getIncludesSyntactic().setHasAddress("");
        }
        if(null==serviceDescriptionVO.getIncludesSyntactic().getHasReturn()){
            serviceDescriptionVO.getIncludesSyntactic().setHasReturn("");
        }
        if(null==serviceDescriptionVO.getIncludesSemantic().getHasSemanticReturn()){
            serviceDescriptionVO.getIncludesSemantic().setHasSemanticReturn("");
        }
        if(null==serviceDescriptionVO.getIncludesSemantic().getHasSemanticReception()){
           serviceDescriptionVO.getIncludesSemantic().setHasSemanticReception("");
        }
        if(null==serviceDescriptionVO.getIncludesSemantic().getHasSemanticRepresentation()){
           serviceDescriptionVO.getIncludesSemantic().setHasSemanticRepresentation("");
        }
        if(null==serviceDescriptionVO.getIncludesSemantic().getHasFunctionalRequirement()){
           serviceDescriptionVO.getIncludesSemantic().setHasFunctionalRequirement("");
        }        
        ArrayList<String> s = new ArrayList();
        s.add(serviceDescriptionVO.getIncludesSemantic().getHasFunctionalRequirement());
        serviceDescriptionVO.getIncludesSemantic().setHasFunctionalRequirements(s);
        
        if(null==serviceDescriptionVO.getIncludesPragmatic().getHasNonFunctionalReq()){
           serviceDescriptionVO.getIncludesPragmatic().setHasNonFunctionalReq("");
        }
        ArrayList<String> s2 = new ArrayList();
        s2.add(serviceDescriptionVO.getIncludesPragmatic().getHasNonFunctionalReq());
        serviceDescriptionVO.getIncludesPragmatic().setHasNonFunctionalRequirement(s2);
        
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasArtifact()){
           serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHasArtifact("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasComments()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHasComments("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasDomain()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHasDomain("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasLicense()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHasLicense("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasReputation()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHasReputation("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasRestriction()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHasRestriction("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHow()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setHow("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getWhen()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setWhen("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getWhere()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setWhere("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getWho()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().setWho("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasCPU()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().setHasCPU("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasOperationalSystem()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().setHasOperationalSystem("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasRAM()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().setHasRAM("");
        }
        if(null==serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasRAM()){
          serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().setHasROM("");
        }
        
        ServiceRegistration sr = new ServiceRegistration();
        sr.Register(serviceDescriptionVO);
        
    }
    
    public void searchEquivalentServices(String serviceName){
       EquivalentServicesResource er = new EquivalentServicesResource();
       StringBuilder sb = new StringBuilder();
       sb.append(er.getSyntacticallyEquivalentServices(serviceName))
               .append(er.getSemanticallyEquivalentServices(serviceName))
               .append(er.getPragmaticallyEquivalentServices(serviceName));
       setEquivalencesResult(sb.toString());
       
    }
    
    
}

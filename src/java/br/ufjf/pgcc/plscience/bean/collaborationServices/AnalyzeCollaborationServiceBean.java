/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.dao.CollaborationServiceDAO;
import br.ufjf.pgcc.plscience.dao.CoordinationServiceDAO;
import br.ufjf.pgcc.plscience.dao.GroupServiceDAO;
import br.ufjf.pgcc.plscience.integration.ConceptXML;
import br.ufjf.pgcc.plscience.integration.ConceptXMLDAO;
import static br.ufjf.pgcc.plscience.integration.InteroperabilityManipulationXML.createFileInteroperability;
import static br.ufjf.pgcc.plscience.integration.InteroperabilityManipulationXML.showScreen;
import br.ufjf.pgcc.plscience.integration.InteroperabilityServices;
import br.ufjf.pgcc.plscience.integration.InteroperabilityStructXMLDAO;
import br.ufjf.pgcc.plscience.interoperability.LevenshteinDistance;
import br.ufjf.pgcc.plscience.interoperability.ServiceRecovery;
import br.ufjf.pgcc.plscience.interoperability.SimilarityCalculation1;
import br.ufjf.pgcc.plscience.interoperability.WordNetHandler;
import br.ufjf.pgcc.plscience.model.CollaborationService;
import br.ufjf.pgcc.plscience.model.Competence;
import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.model.Roler;
import br.ufjf.pgcc.plscience.model.Status;
import br.ufjf.pgcc.plscience.model.StepsScientificExperimentation;
import br.ufjf.pgcc.plscience.util.DecimalUtil;
import static br.ufjf.pgcc.plscience.util.StringUtil.removeList;
import br.ufjf.pgcc.plscience.vo.ContextVO;
import br.ufjf.pgcc.plscience.vo.HardwareVO;
import br.ufjf.pgcc.plscience.vo.PragmaticVO;
import br.ufjf.pgcc.plscience.vo.RankingVO;
import br.ufjf.pgcc.plscience.vo.SemanticVO;
import br.ufjf.pgcc.plscience.vo.ServiceDescriptionVO;
import br.ufjf.pgcc.plscience.vo.SyntacticVO;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.hibernate.HibernateException;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean(name = "analyzeCollaborationServiceBean")
@ViewScoped
public class AnalyzeCollaborationServiceBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    //SAVE
    private String statusCompare;
     
    private List<CollaborationService> collaborationServiceList;
    private CollaborationService collaborationService1;
    private CollaborationService collaborationService2;
    
    private InteroperabilityServices interoperabilityServices;
    
    private ConceptXML conceptXML;
    
    //PRIME
    private ServiceDescriptionVO serviceUsed;
    private ServiceDescriptionVO serviceDescriptionVO;
    private List<ServiceDescriptionVO> services;
    private String serviceName;
    private String equivalencesResult;
    
    //Rederização de Componentes
    private boolean show; 
    
    public AnalyzeCollaborationServiceBean(){
        
        //SAVE
        collaborationServiceList = new CollaborationServiceDAO().getAllDevelopedCollaborationService();
        interoperabilityServices = new InteroperabilityServices();
        conceptXML = new ConceptXML();
        
        //PRIME
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
        
        //Componentes
        show = true;
    }
    
    public String test() {
        String same = "TESTE";
        
        
        return same;
    }
    
    public void analyzeCollabServices() throws IOException{
       
        interoperabilityServices.cleanInteroperabilityServices();
        
        interoperabilityServices.setCollabService1(getCollaborationService1());
        interoperabilityServices.setCollabService2(getCollaborationService2());
        
        interoperabilityServices.getInteroperabilityStructXML().setFirstServiceID(getCollaborationService1().getId());
        interoperabilityServices.getInteroperabilityStructXML().setFirstTypeService(getCollaborationService1().getCollaborativeServiceType().getNameServiceType());
        interoperabilityServices.getInteroperabilityStructXML().setSecondServiceID(getCollaborationService2().getId());
        interoperabilityServices.getInteroperabilityStructXML().setSecondTypeService(getCollaborationService2().getCollaborativeServiceType().getNameServiceType());
        interoperabilityServices.getInteroperabilityStructXML().setAgentID(100L); //MUDAR PARA O ID DA PESSOA LOGADA
        
        setShow(false);
        
        //Inicia a comparação
        compareService(); 
        
        //Cálculo da Interoperabilidade do documento em geral.
        calculateOverallRatio();
        
        //String impressa na tela para o usuário.
        String same = "\n";
        
        //Dados introdutórios sobre os serviços analizados.
        /*
        same = same + "Compare: \n" +
                        interoperabilityServices.getCollabService1().getId() + " - " +
                        interoperabilityServices.getCollabService1().getCollabServiceName() + "\n" +
                        interoperabilityServices.getCollabService2().getId() + " - " +
                        interoperabilityServices.getCollabService2().getCollabServiceName() + "\n\n";
        */
        same = same + "<b>Compared Services:</b> \n" +
                        "<b>" + interoperabilityServices.getCollabService1().getCollabServiceName() + "</b>" +
                        " with " +
                        "<b>" + interoperabilityServices.getCollabService2().getCollabServiceName() + "</b>\n\n";
                  
        //Cálculo da porcentagem de similaridade entre os conceitos existentes.
        double d = ((double)interoperabilityServices.getSameConcept().size()/
                ((double)interoperabilityServices.getSameConcept().size() + 
                (double)interoperabilityServices.getDifferentConcept().size()))*100;       
        d = Double.parseDouble(new DecimalUtil().getDecimalFormatParser().format(d).replaceAll("\\.","").replace(",","."));
        
        interoperabilityServices.setConceptRatio(d);
        
        same = same + "<b>Similarity Rate Among Concepts: </b>" + interoperabilityServices.getConceptRatio() + "%" + "\n\n";
        
        //Salva a comparação no banco de dados.
        String name;
        name = interoperabilityServices.getCollabService1().getId() + "-" +
                        interoperabilityServices.getCollabService1().getCollabServiceName() + "-" +
                        interoperabilityServices.getCollabService2().getId() + "-" +
                        interoperabilityServices.getCollabService2().getCollabServiceName() + "-" + 
                        interoperabilityServices.getInteroperabilityStructXML().getAgentID();
        
        interoperabilityServices.getInteroperabilityStructXML().setInteroperabilityName(name);
        
        //Salva o "InteroperabilityStructXML" no banco de dados.
        saveInteroperabilityStructXML();        
        
        //Transforma a String para o formato que será exibido em tela.
        statusCompare = same.replaceAll("\n", "<BR/>");
        
    }
    
    public void analyzeServices(){
        String same = "\n";
        
        same = same + "Compare: \n" +
                        getCollaborationService1().getId() + " - " +
                        getCollaborationService1().getCollabServiceName() + "\n" +
                        getCollaborationService2().getId() + " - " +
                        getCollaborationService2().getCollabServiceName() + "\n" +
                        " Same Services: " + 
                        compareService(getCollaborationService1(), getCollaborationService2()) + "\n" +
                        " Same Steps: " +
                        compareSteps(getCollaborationService1(), getCollaborationService2()) + "\n";
        
        statusCompare = same.replaceAll("\n", "<BR/>");
        
    }
    
    public String compareAll() {
        String same = "";
        
        List<CollaborationService> collaborationList = new CollaborationServiceDAO().getAllCollaborationService();
        
        int lim = collaborationList.size();
        int cont = 1;
        
        for(int i = 0; i < lim-1; i++){
            for(int j = i+1; j < lim; j++){
                same = same + "Comparação " + cont + "\n" +
                        collaborationList.get(i).getId() + " - " +
                        collaborationList.get(i).getCollabServiceName() + "\n" +
                        collaborationList.get(j).getId() + " - " +
                        collaborationList.get(j).getCollabServiceName() + "\n" +
                        " Serviços Iguais? " + 
                        compareService(collaborationList.get(i), collaborationList.get(j)) + "\n" +
                        " Etapas Iguais? " +
                        compareSteps(collaborationList.get(i), collaborationList.get(j)) + "\n" +
                        "\n\n";     
                cont++;
            }
        }
        
        same = same.replaceAll("\n", "<BR/>");
        return same;
    }
    
    public double analyseLevenshteinDistance(String s1, String s2) {

        LevenshteinDistance ld = new LevenshteinDistance();
        
        double dist = Double.parseDouble(new DecimalUtil().getDecimalFormatParser().format(ld.computeLevenshteinDistance(s1, s2)).replaceAll("\\.","").replace(",","."));
        
        double tam_s1, tam_s2, bigger;
        tam_s1 = (double)s1.length();
        tam_s2 = (double)s2.length();
        
        if(tam_s1 >= tam_s2) {
            bigger = tam_s1;
        } else {
            bigger = tam_s2;
        }
        
        double result = ((bigger - dist)/bigger) * 100;
        
        result = Double.parseDouble(new DecimalUtil().getDecimalFormatParser().format(result).replaceAll("\\.","").replace(",","."));
        return result;
    }
    
    /**
     * Realiza a comparação entre dois serviços selecionados pelo usuário na tela.
     * Essa conparação considera os conceitos semelhantes entre os serviços. 
     * Caso os serviços possuam conceitos semelhantes, seus elementos são comparados e 
     * para cada elemento é gerado um nível de relacionamento entre eles. 
     * O cálculo de similaridade é obtido através da comparação dos nomes dos elementos com o WordNet e 
     * das suas descrições com a distância de Levenshtein.
     * @throws IOException
     */
    public void compareService() throws IOException {
                
        //Group Service - Belief
        if(interoperabilityServices.getCollabService1().getGroupServiceId().getBelief() == 
                interoperabilityServices.getCollabService2().getGroupServiceId().getBelief()){
            if(interoperabilityServices.getCollabService1().getGroupServiceId().getBelief() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Group) Belief");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Group");
                    concept.setConceptService("Belief");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }            
        } else {
            interoperabilityServices.getDifferentConcept().add("(Group) Belief");
        } 
        
        //Group Service - Competence
        if(interoperabilityServices.getCollabService1().getGroupServiceId().getCompetence() == 
                interoperabilityServices.getCollabService2().getGroupServiceId().getCompetence()){
            if(interoperabilityServices.getCollabService1().getGroupServiceId().getCompetence() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Group) Competence");
                
                //Analiza as competencias associadas aos serviços.
                List<Competence> competence1 = new 
                    GroupServiceDAO().getListCompetences(interoperabilityServices.getCollabService1().getGroupServiceId().getId());
                List<Competence> competence2 = new 
                    GroupServiceDAO().getListCompetences(interoperabilityServices.getCollabService2().getGroupServiceId().getId());
                                               
                boolean sameConcept;
                
                if(competence1 != null && competence2 != null) {
                    for(int i = 0; i < competence1.size(); i++) {
                        for(int j = 0; j < competence2.size(); j++) {
                            WordNetHandler wc1 = new WordNetHandler();
                            WordNetHandler wc2 = new WordNetHandler();
                            
                            wc1.handleWordNetConcepts(wc1.getWordNetConceptsNoun(competence1.get(i).getCompetenceName()));
                            wc2.handleWordNetConcepts(wc2.getWordNetConceptsNoun(competence2.get(j).getCompetenceName()));

                            sameConcept = false;
                            
                            //Remove sinônimos e hiperônimos desnecessários.
                            removeList(wc1.getListaSinonimos());
                            removeList(wc2.getListaSinonimos());
                            removeList(wc1.getListaHiperonimos());
                            removeList(wc2.getListaHiperonimos());
                            
                            //Criação de um ConceptXML.
                            ConceptXML concept = new ConceptXML();
                            concept.setGroupConcept("Group");
                            concept.setConceptService("Competence");
                            
                            for(int k = 0; k < wc1.getListaSinonimos().size(); k++) {
                                for(int l = 0; l < wc2.getListaSinonimos().size(); l++) {
                                    if(wc1.getListaSinonimos().get(k).equalsIgnoreCase(wc2.getListaSinonimos().get(l))){
                                        
                                        //Conceito igual encontrado.
                                        sameConcept = true;
                                        
                                        //Acrescenta informações à variável "concept".
                                        concept.setHasElement(true);
                                        concept.setConceptService1(competence1.get(i).getCompetenceName());
                                        concept.setDescriptionService1(competence1.get(i).getDescription());
                                        concept.setConceptService2(competence2.get(j).getCompetenceName());
                                        concept.setDescriptionService2(competence2.get(j).getDescription());
                                        concept.setValidity(false);
                                        
                                        //Calculo da similaridade com a partir da descrição do conceito.
                                        double dist = analyseLevenshteinDistance(competence1.get(i).getDescription(), competence2.get(j).getDescription());                 
                                        concept.setRatio(dist);
                                        
                                        //Adição do conceito à list de conceitos.
                                        interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                                    }
                                    if(sameConcept) {
                                        break;
                                    }
                                }
                                if(sameConcept) {
                                    break;
                                } else {
                                    for(int x = 0; x < wc1.getListaSinonimos().size(); x++) {
                                        for(int y = 0; y < wc2.getListaHiperonimos().size(); y++) {
                                            if(wc1.getListaSinonimos().get(x).equalsIgnoreCase(wc2.getListaHiperonimos().get(y))){

                                                //Conceito igual encontrado.
                                                sameConcept = true;

                                                //Acrescenta informações à variável "concept".
                                                concept.setHasElement(true);
                                                concept.setConceptService1(competence1.get(i).getCompetenceName());
                                                concept.setDescriptionService1(competence1.get(i).getDescription());
                                                concept.setConceptService2(competence2.get(j).getCompetenceName());
                                                concept.setDescriptionService2(competence2.get(j).getDescription());
                                                concept.setValidity(false);

                                                //Calculo da similaridade com a partir da descrição do conceito.
                                                double dist = analyseLevenshteinDistance(competence1.get(i).getDescription(), competence2.get(j).getDescription());                 
                                                concept.setRatio(dist);
                                                
                                                //Adição do conceito à list de conceitos.
                                                interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                                            }
                                            if(sameConcept) {
                                                break;
                                            }
                                        }
                                    }
                                }
                                if(sameConcept) {
                                    break;
                                } else {
                                    for(int w = 0; w < wc1.getListaHiperonimos().size(); w++) {
                                        for(int v = 0; v < wc2.getListaSinonimos().size(); v++) {
                                            if(wc1.getListaHiperonimos().get(w).equalsIgnoreCase(wc2.getListaSinonimos().get(v))){

                                                //Conceito igual encontrado.
                                                sameConcept = true;

                                                //Acrescenta informações à variável "concept".
                                                concept.setHasElement(true);
                                                concept.setConceptService1(competence1.get(i).getCompetenceName());
                                                concept.setDescriptionService1(competence1.get(i).getDescription());
                                                concept.setConceptService2(competence2.get(j).getCompetenceName());
                                                concept.setDescriptionService2(competence2.get(j).getDescription());
                                                concept.setValidity(false);

                                                //Calculo da similaridade com a partir da descrição do conceito.
                                                double dist = analyseLevenshteinDistance(competence1.get(i).getDescription(), competence2.get(j).getDescription());                 
                                                concept.setRatio(dist);
                                                
                                                //Adição do conceito à list de conceitos.
                                                interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                                            }
                                            if(sameConcept) {
                                                break;
                                            }
                                        }
                                    }
                                }  
                                if(sameConcept) {
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Group");
                    concept.setConceptService("Competence");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }                
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Group) Competence");
        }
        
        //Group Service - Confidence
        if(interoperabilityServices.getCollabService1().getGroupServiceId().getConfidence() == 
                interoperabilityServices.getCollabService2().getGroupServiceId().getConfidence()){
            if(interoperabilityServices.getCollabService1().getGroupServiceId().getConfidence() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Group) Confidence");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Group");
                    concept.setConceptService("Confidence");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Group) Confidence");
        }
            
        //Group Service - Goal
        if(interoperabilityServices.getCollabService1().getGroupServiceId().getGoal() == 
                interoperabilityServices.getCollabService2().getGroupServiceId().getGoal()){
            if(interoperabilityServices.getCollabService1().getGroupServiceId().getGoal() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Group) Goal");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Group");
                    concept.setConceptService("Goal");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Group) Goal");
        }
        
        //Group Service - Group
        if(interoperabilityServices.getCollabService1().getGroupServiceId().getGroupp() == 
                interoperabilityServices.getCollabService2().getGroupServiceId().getGroupp()){
            if(interoperabilityServices.getCollabService1().getGroupServiceId().getGroupp() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Group) Group");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Group");
                    concept.setConceptService("Group");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Group) Group");
        }
        
        //Group Service - Motivation
        if(interoperabilityServices.getCollabService1().getGroupServiceId().getMotivation() == 
                interoperabilityServices.getCollabService2().getGroupServiceId().getMotivation()){
            if(interoperabilityServices.getCollabService1().getGroupServiceId().getMotivation() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Group) Motivation");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Group");
                    concept.setConceptService("Motivation");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            } 
        } else {
            interoperabilityServices.getDifferentConcept().add("(Group) Motivation");
        }
        
        //Group Service - Participant
        if(interoperabilityServices.getCollabService1().getGroupServiceId().getParticipant() == 
                interoperabilityServices.getCollabService2().getGroupServiceId().getParticipant()){
            if(interoperabilityServices.getCollabService1().getGroupServiceId().getParticipant() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Group) Participant");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Group");
                    concept.setConceptService("Participant");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Group) Participant");
        }
        
        //Communication - Code
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getCode() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getCode()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getCode() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Code");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication Service");
                    concept.setConceptService("Code");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Code");
        }
        
        //Communication - Common Sense
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getCommonSense() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getCommonSense()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getCommonSense() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Common Sense");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Common Sense");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Common Sense");
        }
        
        //Communication - Communication Protocol
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getCommunicationProtocol() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getCommunicationProtocol()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getCommunicationProtocol() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Communication Protocol");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Communication Protocol");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Communication Protocol");
        }
        
        //Communication - Compromise
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getCompromise() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getCompromise()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getCompromise() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Compromise");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Compromise");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Compromise");
        }
        
        //Communication - Interpretation
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getInterpretation() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getInterpretation()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getInterpretation() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Interpretation");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Interpretation");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Interpretation");
        }
        
        //Communication - Issuer
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getIssuer() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getIssuer()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getIssuer() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Issuer");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Issuer");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Issuer");
        }
        
        //Communication - Message
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getMessage() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getMessage()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getMessage() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Message");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Message");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Message");
        }
        
        //Communication - Mode
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getMode() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getMode()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getMode() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Mode");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Mode");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Mode");
        }
        
        //Communication - Negotiation
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getNegotiation() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getNegotiation()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getNegotiation() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Negotiation");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Negotiation");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Negotiation");
        }
        
        //Communication - Receiver
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getReceiver() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getReceiver()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getReceiver() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Receiver");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Receiver");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Receiver");
        }
        
        //Communication - Synchronism
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getSynchronism() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getSynchronism()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getSynchronism() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Synchronism");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Synchronism");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Synchronism");
        }
        
        //Communication - Transmission Mode
        if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getTransmissionMode() == 
                interoperabilityServices.getCollabService2().getCommunicationServiceId().getTransmissionMode()) {
            if(interoperabilityServices.getCollabService1().getCommunicationServiceId().getTransmissionMode() == true){
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Communication) Transmission Mode");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Communication");
                    concept.setConceptService("Transmission Mode");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Communication) Transmission Mode");
        }
        
        //Cooperation - Activity
        if(interoperabilityServices.getCollabService1().getCooperationServiceId().getActivity() == 
                interoperabilityServices.getCollabService2().getCooperationServiceId().getActivity()) {
            if(interoperabilityServices.getCollabService1().getCooperationServiceId().getActivity() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Cooperation) Activity");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Cooperation");
                    concept.setConceptService("Activity");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Cooperation) Activity");
        }
        
        //Cooperation - Artifact
        if(interoperabilityServices.getCollabService1().getCooperationServiceId().getArtifact() == 
                interoperabilityServices.getCollabService2().getCooperationServiceId().getArtifact()) {
            if(interoperabilityServices.getCollabService1().getCooperationServiceId().getArtifact() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Cooperation) Artifact");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Cooperation");
                    concept.setConceptService("Artifact");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Cooperation) Artifact");
        }
        
        //Cooperation - Product
        if(interoperabilityServices.getCollabService1().getCooperationServiceId().getProduct() == 
                interoperabilityServices.getCollabService2().getCooperationServiceId().getProduct()) {
            if(interoperabilityServices.getCollabService1().getCooperationServiceId().getProduct() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Cooperation) Product");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Cooperation");
                    concept.setConceptService("Product");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Cooperation) Product");
        }
        
        //Cooperation - Resource
        if(interoperabilityServices.getCollabService1().getCooperationServiceId().getResource() == 
                interoperabilityServices.getCollabService2().getCooperationServiceId().getResource()) {
            if(interoperabilityServices.getCollabService1().getCooperationServiceId().getResource() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Cooperation) Resource");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Cooperation");
                    concept.setConceptService("Resource");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Cooperation) Resource");
        }
        
        //Cooperation - Share
        if(interoperabilityServices.getCollabService1().getCooperationServiceId().getShare() == 
                interoperabilityServices.getCollabService2().getCooperationServiceId().getShare()) {
            if(interoperabilityServices.getCollabService1().getCooperationServiceId().getShare() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Cooperation) Share");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Cooperation");
                    concept.setConceptService("Share");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Cooperation) Share");
        }
        
        //Cooperation - Shared Space
        if(interoperabilityServices.getCollabService1().getCooperationServiceId().getSharedSpace() == 
                interoperabilityServices.getCollabService2().getCooperationServiceId().getSharedSpace()) {
            if(interoperabilityServices.getCollabService1().getCooperationServiceId().getSharedSpace() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Cooperation) Shared Space");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Cooperation");
                    concept.setConceptService("Share Space");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Cooperation) Shared Space");
        }
        
        //Cooperation - Task
        if(interoperabilityServices.getCollabService1().getCooperationServiceId().getTask() == 
                interoperabilityServices.getCollabService2().getCooperationServiceId().getTask()){
            if(interoperabilityServices.getCollabService1().getCooperationServiceId().getTask() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Cooperation) Task");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Cooperation");
                    concept.setConceptService("Task");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Cooperation) Task");
        }
        
        // Coordination - Coupling
        if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getCoupling() == 
                interoperabilityServices.getCollabService2().getCoordinationServiceId().getCoupling()) {
            if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getCoupling() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Coordination) Coupling");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Coordination");
                    concept.setConceptService("Coupling");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Coordination) Coupling");
        }
            
        // Coordination - Deadline
        if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getDeadline() == 
                interoperabilityServices.getCollabService2().getCoordinationServiceId().getDeadline()) {
            if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getDeadline() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Coordination) Deadline");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Coordination");
                    concept.setConceptService("Deadline");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Coordination) Deadline");
        }
        
        // Coordination - Monitoring
        if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getMonitoring() == 
                interoperabilityServices.getCollabService2().getCoordinationServiceId().getMonitoring()) {
            if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getMonitoring() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Coordination) Monitoring");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Coordination");
                    concept.setConceptService("Monitoring");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Coordination) Monitoring");
        }
        
        // Coordination - Policy
        if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getPolicy() == 
                interoperabilityServices.getCollabService2().getCoordinationServiceId().getPolicy()) {
            if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getPolicy() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Coordination) Policy");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Coordination");
                    concept.setConceptService("Policy");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Coordination) Policy");
        }
        
        // Coordination - Role
        if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getRole() == 
                interoperabilityServices.getCollabService2().getCoordinationServiceId().getRole()) {
            if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getRole() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Coordination) Role");
                
                //Analiza as competencias associadas aos serviços.
                List<Roler> role1 = new 
                    CoordinationServiceDAO().getListRolers(interoperabilityServices.getCollabService1().getCoordinationServiceId().getId());
                List<Roler> role2 = new 
                    CoordinationServiceDAO().getListRolers(interoperabilityServices.getCollabService2().getCoordinationServiceId().getId());             
                              
                boolean sameConcept;
                
                if(role1 != null && role2 != null) {
                    for(int i = 0; i < role1.size(); i++) {
                        for(int j = 0; j < role2.size(); j++) {
                            WordNetHandler wc1 = new WordNetHandler();
                            WordNetHandler wc2 = new WordNetHandler();
                            
                            wc1.handleWordNetConcepts(wc1.getWordNetConceptsNoun(role1.get(i).getRoleName()));
                            wc2.handleWordNetConcepts(wc2.getWordNetConceptsNoun(role2.get(j).getRoleName()));

                            sameConcept = false;
                            
                            //Remove sinônimos e hiperônimos desnecessários.
                            removeList(wc1.getListaSinonimos());
                            removeList(wc2.getListaSinonimos());
                            removeList(wc1.getListaHiperonimos());
                            removeList(wc2.getListaHiperonimos());                            
                            
                            //Criação de um ConceptXML.
                            ConceptXML concept = new ConceptXML();
                            concept.setGroupConcept("Coordination");
                            concept.setConceptService("Role");
                            
                            for(int k = 0; k < wc1.getListaSinonimos().size(); k++) {
                                for(int l = 0; l < wc2.getListaSinonimos().size(); l++) {
                                    if(wc1.getListaSinonimos().get(k).equalsIgnoreCase(wc2.getListaSinonimos().get(l))){
                                        
                                        //Conceito igual encontrado.
                                        sameConcept = true;
                                        
                                        //Acrescenta informações à variável "concept".
                                        concept.setHasElement(true);
                                        concept.setConceptService1(role1.get(i).getRoleName());
                                        concept.setDescriptionService1(role1.get(i).getDescription());
                                        concept.setConceptService2(role2.get(j).getRoleName());
                                        concept.setDescriptionService2(role2.get(j).getDescription());
                                        concept.setValidity(false);
                                        
                                        //Calculo da similaridade com a partir da descrição do conceito.
                                        double dist = analyseLevenshteinDistance(role1.get(i).getDescription(), role2.get(j).getDescription());                 
                                        concept.setRatio(dist);
                                        
                                        //Adição do conceito à list de conceitos.
                                        interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                                    }
                                    if(sameConcept) {
                                        break;
                                    }
                                }
                                if(sameConcept) {
                                    break;
                                } else {
                                    for(int x = 0; x < wc1.getListaSinonimos().size(); x++) {
                                        for(int y = 0; y < wc2.getListaHiperonimos().size(); y++) {
                                            if(wc1.getListaSinonimos().get(x).equalsIgnoreCase(wc2.getListaHiperonimos().get(y))){

                                                //Conceito igual encontrado.
                                                sameConcept = true;

                                                //Acrescenta informações à variável "concept".
                                                concept.setHasElement(true);
                                                concept.setConceptService1(role1.get(i).getRoleName());
                                                concept.setDescriptionService1(role1.get(i).getDescription());
                                                concept.setConceptService2(role2.get(j).getRoleName());
                                                concept.setDescriptionService2(role2.get(j).getDescription());
                                                concept.setValidity(false);

                                                //Calculo da similaridade com a partir da descrição do conceito.
                                                double dist = analyseLevenshteinDistance(role1.get(i).getDescription(), role2.get(j).getDescription());                 
                                                concept.setRatio(dist);
                                                
                                                //Adição do conceito à list de conceitos.
                                                interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                                            }
                                            if(sameConcept) {
                                                break;
                                            }
                                        }
                                    }
                                }
                                if(sameConcept) {
                                    break;
                                } else {
                                    for(int w = 0; w < wc1.getListaHiperonimos().size(); w++) {
                                        for(int v = 0; v < wc2.getListaSinonimos().size(); v++) {
                                            if(wc1.getListaHiperonimos().get(w).equalsIgnoreCase(wc2.getListaSinonimos().get(v))){

                                                //Conceito igual encontrado.
                                                sameConcept = true;

                                                //Acrescenta informações à variável "concept".
                                                concept.setHasElement(true);
                                                concept.setConceptService1(role1.get(i).getRoleName());
                                                concept.setDescriptionService1(role1.get(i).getDescription());
                                                concept.setConceptService2(role2.get(j).getRoleName());
                                                concept.setDescriptionService2(role2.get(j).getDescription());
                                                concept.setValidity(false);

                                                //Calculo da similaridade com a partir da descrição do conceito.
                                                double dist = analyseLevenshteinDistance(role1.get(i).getDescription(), role2.get(j).getDescription());                 
                                                concept.setRatio(dist);
                                                
                                                //Adição do conceito à list de conceitos.
                                                interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                                            }
                                            if(sameConcept) {
                                                break;
                                            }
                                        }
                                    }
                                }  
                                if(sameConcept) {
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Coordination");
                    concept.setConceptService("Role");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }                               
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Coordination) Role");
        }
        
        // Coordination - Status
        if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getStatus() == 
                interoperabilityServices.getCollabService2().getCoordinationServiceId().getStatus()) {
            if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getStatus() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Coordination) Status");
                
                //Analiza as competencias associadas aos serviços.
                List<Status> status1 = new 
                    CoordinationServiceDAO().getListStatus(interoperabilityServices.getCollabService1().getCoordinationServiceId().getId());
                List<Status> status2 = new 
                    CoordinationServiceDAO().getListStatus(interoperabilityServices.getCollabService2().getCoordinationServiceId().getId());
                
                boolean sameConcept;
                
                if(status1 != null && status2 != null) { 
                    for(int i = 0; i < status1.size(); i++) {
                        for(int j = 0; j < status2.size(); j++) {
                            WordNetHandler wc1 = new WordNetHandler();
                            WordNetHandler wc2 = new WordNetHandler();
                            
                            wc1.handleWordNetConcepts(wc1.getWordNetConceptsNoun(status1.get(i).getStatusName()));
                            wc2.handleWordNetConcepts(wc2.getWordNetConceptsNoun(status2.get(j).getStatusName()));

                            sameConcept = false;
                            
                            //Remove sinônimos e hiperônimos desnecessários.
                            removeList(wc1.getListaSinonimos());
                            removeList(wc2.getListaSinonimos());
                            removeList(wc1.getListaHiperonimos());
                            removeList(wc2.getListaHiperonimos());
                            
                            //Criação de um ConceptXML.
                            ConceptXML concept = new ConceptXML();
                            concept.setGroupConcept("Group");
                            concept.setConceptService("Competence");
                            
                            for(int k = 0; k < wc1.getListaSinonimos().size(); k++) {
                                for(int l = 0; l < wc2.getListaSinonimos().size(); l++) {
                                    if(wc1.getListaSinonimos().get(k).equalsIgnoreCase(wc2.getListaSinonimos().get(l))){
                                        
                                        //Conceito igual encontrado.
                                        sameConcept = true;
                                        
                                        //Acrescenta informações à variável "concept".
                                        concept.setHasElement(true);
                                        concept.setConceptService1(status1.get(i).getStatusName());
                                        concept.setDescriptionService1(status1.get(i).getDescription());
                                        concept.setConceptService2(status2.get(j).getStatusName());
                                        concept.setDescriptionService2(status2.get(j).getDescription());
                                        concept.setValidity(false);
                                        
                                        //Calculo da similaridade com a partir da descrição do conceito.
                                        double dist = analyseLevenshteinDistance(status1.get(i).getDescription(), status2.get(j).getDescription());                 
                                        concept.setRatio(dist);
                                        
                                        //Adição do conceito à list de conceitos.
                                        interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                                    }
                                    if(sameConcept) {
                                        break;
                                    }
                                }
                                if(sameConcept) {
                                    break;
                                } else {
                                    for(int x = 0; x < wc1.getListaSinonimos().size(); x++) {
                                        for(int y = 0; y < wc2.getListaHiperonimos().size(); y++) {
                                            if(wc1.getListaSinonimos().get(x).equalsIgnoreCase(wc2.getListaHiperonimos().get(y))){

                                                //Conceito igual encontrado.
                                                sameConcept = true;

                                                //Acrescenta informações à variável "concept".
                                                concept.setHasElement(true);
                                                concept.setConceptService1(status1.get(i).getStatusName());
                                                concept.setDescriptionService1(status1.get(i).getDescription());
                                                concept.setConceptService2(status2.get(j).getStatusName());
                                                concept.setDescriptionService2(status2.get(j).getDescription());
                                                concept.setValidity(false);

                                                //Calculo da similaridade com a partir da descrição do conceito.
                                                double dist = analyseLevenshteinDistance(status1.get(i).getDescription(), status2.get(j).getDescription());                 
                                                concept.setRatio(dist);
                                                
                                                //Adição do conceito à list de conceitos.
                                                interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                                            }
                                            if(sameConcept) {
                                                break;
                                            }
                                        }
                                    }
                                }
                                if(sameConcept) {
                                    break;
                                } else {
                                    for(int w = 0; w < wc1.getListaHiperonimos().size(); w++) {
                                        for(int v = 0; v < wc2.getListaSinonimos().size(); v++) {
                                            if(wc1.getListaHiperonimos().get(w).equalsIgnoreCase(wc2.getListaSinonimos().get(v))){

                                                //Conceito igual encontrado.
                                                sameConcept = true;

                                                //Acrescenta informações à variável "concept".
                                                concept.setHasElement(true);
                                                concept.setConceptService1(status1.get(i).getStatusName());
                                                concept.setDescriptionService1(status1.get(i).getDescription());
                                                concept.setConceptService2(status2.get(j).getStatusName());
                                                concept.setDescriptionService2(status2.get(j).getDescription());
                                                concept.setValidity(false);

                                                //Calculo da similaridade com a partir da descrição do conceito.
                                                double dist = analyseLevenshteinDistance(status1.get(i).getDescription(), status2.get(j).getDescription());                 
                                                concept.setRatio(dist);
                                                
                                                //Adição do conceito à list de conceitos.
                                                interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                                            }
                                            if(sameConcept) {
                                                break;
                                            }
                                        }
                                    }
                                }  
                                if(sameConcept) {
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Coordination");
                    concept.setConceptService("Status");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Coordination) Status");
        }
        
        // Coordination - Work Plan
        if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getWorkPlan() == 
                interoperabilityServices.getCollabService2().getCoordinationServiceId().getWorkPlan()) {
            if(interoperabilityServices.getCollabService1().getCoordinationServiceId().getWorkPlan() == true) {
                //Adiciona à lista de conceitos semelhantes.
                interoperabilityServices.getSameConcept().add("(Coordination) Work Plan");
                
                //Analiza as competencias associadas aos serviços.
                //FALTA!
                
                boolean sameConcept;
                
                if(false) { //FALTA!
                    //FALTA!
                } else {
                    //Criação de um ConceptXML.
                    ConceptXML concept = new ConceptXML();
                    concept.setGroupConcept("Coordination");
                    concept.setConceptService("Work Plan");                  
                    concept.setHasElement(false);
                    //concept.setRatio(100.00);
                    concept.setValidity(false);
                    
                    //Adição do conceito à list de conceitos.
                    interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(concept);
                }
            }
        } else {
            interoperabilityServices.getDifferentConcept().add("(Coordination) Work Plan");
        }
    }

    public boolean compareService(CollaborationService cs1, CollaborationService cs2){
        boolean same = false;
        
        if(cs1.getCollaborativeServiceType().equals(cs2.getCollaborativeServiceType())  &&
            cs1.getGroupServiceId().getBelief() == cs2.getGroupServiceId().getBelief() &&
            cs1.getGroupServiceId().getCompetence() == cs2.getGroupServiceId().getCompetence() &&
            cs1.getGroupServiceId().getConfidence() == cs2.getGroupServiceId().getConfidence() &&
            cs1.getGroupServiceId().getGoal() == cs2.getGroupServiceId().getGoal() &&
            cs1.getGroupServiceId().getGroupp() == cs2.getGroupServiceId().getGroupp() && 
            cs1.getGroupServiceId().getMotivation() == cs2.getGroupServiceId().getMotivation() && 
            cs1.getGroupServiceId().getParticipant() == cs2.getGroupServiceId().getParticipant() && 
            cs1.getCommunicationServiceId().getCode() == cs2.getCommunicationServiceId().getCode() && 
            cs1.getCommunicationServiceId().getCommonSense() == cs2.getCommunicationServiceId().getCommonSense() &&
            cs1.getCommunicationServiceId().getCommunicationProtocol() == cs2.getCommunicationServiceId().getCommunicationProtocol() && 
            cs1.getCommunicationServiceId().getCompromise() == cs2.getCommunicationServiceId().getCompromise() && 
            cs1.getCommunicationServiceId().getInterpretation() == cs2.getCommunicationServiceId().getInterpretation() && 
            cs1.getCommunicationServiceId().getIssuer() == cs2.getCommunicationServiceId().getIssuer() && 
            cs1.getCommunicationServiceId().getMessage() ==  cs2.getCommunicationServiceId().getMessage() && 
            cs1.getCommunicationServiceId().getMode() == cs2.getCommunicationServiceId().getMode() && 
            cs1.getCommunicationServiceId().getNegotiation() == cs2.getCommunicationServiceId().getNegotiation() && 
            cs1.getCommunicationServiceId().getReceiver() == cs2.getCommunicationServiceId().getReceiver() && 
            cs1.getCommunicationServiceId().getSynchronism() == cs2.getCommunicationServiceId().getSynchronism() && 
            cs1.getCommunicationServiceId().getTransmissionMode() == cs2.getCommunicationServiceId().getTransmissionMode() && 
            cs1.getCooperationServiceId().getActivity() == cs2.getCooperationServiceId().getActivity() && 
            cs1.getCooperationServiceId().getArtifact() == cs2.getCooperationServiceId().getArtifact() && 
            cs1.getCooperationServiceId().getProduct() == cs2.getCooperationServiceId().getProduct() && 
            cs1.getCooperationServiceId().getResource() == cs2.getCooperationServiceId().getResource() && 
            cs1.getCooperationServiceId().getShare() == cs2.getCooperationServiceId().getShare() && 
            cs1.getCooperationServiceId().getSharedSpace() == cs2.getCooperationServiceId().getSharedSpace() && 
            cs1.getCooperationServiceId().getTask() == cs2.getCooperationServiceId().getTask() && 
            cs1.getCoordinationServiceId().getCoupling() == cs2.getCoordinationServiceId().getCoupling() &&
            cs1.getCoordinationServiceId().getDeadline() == cs2.getCoordinationServiceId().getDeadline() && 
            cs1.getCoordinationServiceId().getMonitoring() == cs2.getCoordinationServiceId().getMonitoring() && 
            cs1.getCoordinationServiceId().getPolicy() == cs2.getCoordinationServiceId().getPolicy() && 
            cs1.getCoordinationServiceId().getRole() == cs2.getCoordinationServiceId().getRole() && 
            cs1.getCoordinationServiceId().getStatus() == cs2.getCoordinationServiceId().getStatus() && 
            cs1.getCoordinationServiceId().getWorkPlan() == cs2.getCoordinationServiceId().getWorkPlan()){
            
            same = true;
        }
        
        return same;
    }
    
    public double calculateOverallRatio() {
        
        double d = 0.00;
        int count = 0;
        
        for(ConceptXML cxml : interoperabilityServices.getInteroperabilityStructXML().getConcepts()){
            if(cxml.isHasElement()){
                if(cxml.isValidity()) {
                    d = d + cxml.getRatio();
                }            
                count++;
            }
        }
        
        d = d/(double)count;
        d = Double.parseDouble(new DecimalUtil().getDecimalFormatParser().format(d).replaceAll("\\.","").replace(",","."));

        return d;
    }
    
    public boolean compareSteps(CollaborationService cs1, CollaborationService cs2){
        boolean same = false;
        
        List<Long> l1 = new CollaborationServiceDAO().getListIdSteps(cs1.getId());
        List<Long> l2 = new CollaborationServiceDAO().getListIdSteps(cs2.getId());
        
        same = l1.equals(l2);
        
        return same;
    }

    public void validateCollabServices() {
        
        //Apresentar tela com os pares para o usuário valizar a correspondêndia encontrada. FAZER!
        interoperabilityServices = getInteroperabilityServices(); 
    }
    
    public void saveInteroperabilityStructXML() {
        
        //
        Long StructXMLid = new InteroperabilityStructXMLDAO().getLastInteroperabilityStructXMLID();
        int size = interoperabilityServices.getInteroperabilityStructXML().getConcepts().size();
        
        if(interoperabilityServices.getInteroperabilityStructXML().getConcepts().size() > 0 &&
                interoperabilityServices.getInteroperabilityStructXML().getConcepts() != null) {
            for(ConceptXML cxml : interoperabilityServices.getInteroperabilityStructXML().getConcepts()) {
                new ConceptXMLDAO().save(cxml);
            }
            
            interoperabilityServices.getInteroperabilityStructXML().getConcepts().clear();
            
            for(ConceptXML cxml : new ConceptXMLDAO().getAllLastX(size)) {
                interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(cxml);
            }
        }

        interoperabilityServices.getInteroperabilityStructXML().setIdStructXml(StructXMLid + 1);
        for(ConceptXML cxml : interoperabilityServices.getInteroperabilityStructXML().getConcepts()) {
            cxml.setIdStructXml(interoperabilityServices.getInteroperabilityStructXML());
        }
        new InteroperabilityStructXMLDAO().update(interoperabilityServices.getInteroperabilityStructXML());
        
        
        //Cria o arquivo XML com as correspondências.
        //createFileInteroperability("name", interoperabilityServices.getInteroperabilityStructXML());
    }
    
    public void editConcept(ActionEvent actionEvent) {
        
        if(conceptXML.getIdStructXml() == null) {
            conceptXML.setIdStructXml(interoperabilityServices.getInteroperabilityStructXML());
        }
        
        new ConceptXMLDAO().update(conceptXML);
        interoperabilityServices.getInteroperabilityStructXML().getConcepts().clear();
        
        Long idStructXML = interoperabilityServices.getInteroperabilityStructXML().getIdStructXml();
        
        if(idStructXML > 0 && idStructXML != null) {
            for(ConceptXML cxml : new ConceptXMLDAO().getConceptXMLByIdStructXML(idStructXML)) {
                interoperabilityServices.getInteroperabilityStructXML().getConcepts().add(cxml);
            }
        }
        
        conceptXML = new ConceptXML();
    }
    
    public void finish() {
        //Atualiza o "InteroperabilityStructXML" no banco de dados.
        new InteroperabilityStructXMLDAO().update(interoperabilityServices.getInteroperabilityStructXML());
        
        try { 
            //Atualiza o "InteroperabilityStructXML" no banco de dados.            
            interoperabilityServices.getInteroperabilityStructXML().getConcepts().clear();
            
            new InteroperabilityStructXMLDAO().update(interoperabilityServices.getInteroperabilityStructXML()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful: ", "Competence saved with success!"));   
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }
    
    /**
     * @return the collaborationServiceList
     */
    public List<CollaborationService> getCollaborationServiceList() {
        return collaborationServiceList;
    }

    /**
     * @param collaborationServiceList the collaborationServiceList to set
     */
    public void setCollaborationServiceList(List<CollaborationService> collaborationServiceList) {
        this.collaborationServiceList = collaborationServiceList;
    }

    /**
     * @return the collaborationService1
     */
    public CollaborationService getCollaborationService1() {
        return collaborationService1;
    }

    /**
     * @param collaborationService1 the collaborationService1 to set
     */
    public void setCollaborationService1(CollaborationService collaborationService1) {
        this.collaborationService1 = collaborationService1;
    }

    /**
     * @return the collaborationService2
     */
    public CollaborationService getCollaborationService2() {
        return collaborationService2;
    }

    /**
     * @param collaborationService2 the collaborationService2 to set
     */
    public void setCollaborationService2(CollaborationService collaborationService2) {
        this.collaborationService2 = collaborationService2;
    }

    /**
     * @return the statusCompare
     */
    public String getStatusCompare() {
        return statusCompare;
    }

    /**
     * @param statusCompare the statusCompare to set
     */
    public void setStatusCompare(String statusCompare) {
        this.statusCompare = statusCompare;
    }

    /**
     * @return the interoperabilityServices
     */
    public InteroperabilityServices getInteroperabilityServices() {
        return interoperabilityServices;
    }

    /**
     * @param interoperabilityServices the interoperabilityServices to set
     */
    public void setInteroperabilityServices(InteroperabilityServices interoperabilityServices) {
        this.interoperabilityServices = interoperabilityServices;
    }

    /**
     * @return the conceptXML
     */
    public ConceptXML getConceptXML() {
        return conceptXML;
    }

    /**
     * @param conceptXML the conceptXML to set
     */
    public void setConceptXML(ConceptXML conceptXML) {
        this.conceptXML = conceptXML;
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
    public void setGroupConceptDescriptionVO(ServiceDescriptionVO serviceDescriptionVO) {
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

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the equivalencesResult
     */
    public String getEquivalencesResult() {
        return equivalencesResult;
    }

    /**
     * @param equivalencesResult the equivalencesResult to set
     */
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
        List<ServiceDescriptionVO> servicesCollabSorted = new ArrayList<ServiceDescriptionVO>();
        List<ServiceDescriptionVO> servicesCollabIntanceSorted = new ArrayList<ServiceDescriptionVO>();
        List<CollaborationService> collaborationServiceList = new CollaborationServiceDAO().getAllDevelopedCollaborationService();
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
    
    public void chooseService() {
        collaborationService1 = new CollaborationServiceDAO().getCollaborationServiceByName(serviceUsed.getName());
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
     * @return the show
     */
    public boolean isShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public void setShow(boolean show) {
        this.show = show;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.interoperability;

import br.ufjf.pgcc.plscience.vo.ServiceVO;
import java.util.List;

/**
 *
 * @author Fran
 */
public class SiimilarityCalculation {
    
    public float calculate(ServiceVO serviceSearched, ServiceVO serviceRegistered){
        
        NaturalLanguageProcessor nlProcessor = new NaturalLanguageProcessor();
        WordNetHandler wordNet = new WordNetHandler();
        DiseasesComplexNetwork dComplexNet = new DiseasesComplexNetwork();

        float similarity=0;
        // sync.setHasAddress("endereco"); // string simples
        if(serviceSearched.getIncludesSyntactic().getHasAddress() != null){
            if(serviceRegistered.getIncludesSyntactic().getHasAddress() != null){
                similarity = nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesSyntactic().getHasAddress(), serviceRegistered.getIncludesSyntactic().getHasAddress());
            }else{
                similarity = similarity + 5;
            }
            
        }
        // sync.setHasReturn("bollean"); // string simples
         if(serviceSearched.getIncludesSyntactic().getHasReturn() != null){
            if(serviceRegistered.getIncludesSyntactic().getHasReturn() != null){
                similarity = nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesSyntactic().getHasReturn(), serviceRegistered.getIncludesSyntactic().getHasReturn());
            }else{
                similarity = similarity + 5;
            }
            
        }
         //frs.add("cadastrar usuario"); // sentença Decidi que faz mais sentido ter apenas um req. Func. (Mudar de array para string)
         if(!serviceSearched.getIncludesSemantic().getHasFunctionalRequirements().isEmpty()){
            for(String s: serviceSearched.getIncludesSemantic().getHasFunctionalRequirements()){
                   similarity = (float)nlProcessor.sentenceSimilaritySumo(s, serviceRegistered.getIncludesSemantic().getHasFunctionalRequirements().get(0));
            }
         }else{
             similarity = similarity + 5;
         }
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
        
         
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("conceito a"); // conceito
         sem.setHasSemanticRepresentation("conceito b"); // conceito
         sem.setHasSemanticReturn("conceito c"); //conceito
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("joao"); // string simples ou setença
         st1.setHasEmail("joao@couves"); // string simples
         st1.setHasInstitutionFiliation("HU"); // string simples ou setença
         
         ScientistVO st2 = new ScientistVO();
         st2.setHasCompleteName("joana");
         st2.setHasEmail("joana@couves");
         st2.setHasInstitutionFiliation("HU");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);
         sts.add(st2);
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("Quad Core"); // String Simples
         h.setHasOperationalSystem("Linux"); // String simples
         h.setHasRAM("6"); // number
         h.setHasROM("500"); // number
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Prot 100"); // conceito
         c.setHasComments("Blbl"); // Texto longo
         c.setHasDomain("DNA"); // conceito
         c.setHasInvolved(sts); 
         c.setHasLicense("public"); // string simples
         c.setHasReputation("5"); // number 
         c.setHasRestriction("restric"); // sentença
         c.setHow("assim"); // sentença
         c.setWhat("isso"); // sentença
         c.setWhen("aquele dia"); // data
         c.setWhere("la"); // string simples
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("nao pode isso"); // sentença
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("testeFran"); // string simples
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
        
                 
         */
        
        return similarity;
        
    }
    
}

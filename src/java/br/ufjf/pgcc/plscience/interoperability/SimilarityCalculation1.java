/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.interoperability;

import br.ufjf.pgcc.plscience.vo.ContextVO;
import br.ufjf.pgcc.plscience.vo.HardwareVO;
import br.ufjf.pgcc.plscience.vo.PragmaticVO;
import br.ufjf.pgcc.plscience.vo.ScientistVO;
import br.ufjf.pgcc.plscience.vo.SemanticVO;
import br.ufjf.pgcc.plscience.vo.ServiceDescriptionVO;
import br.ufjf.pgcc.plscience.vo.ServiceVO;
import br.ufjf.pgcc.plscience.vo.SyntacticVO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Fran
 */
public class SimilarityCalculation1 {
    
    public double calculate(ServiceDescriptionVO serviceSearched, ServiceDescriptionVO serviceRegistered, double PragmaticWeight, double SemanticWeight, double SyntacticWeight){
        
        NaturalLanguageProcessor nlProcessor = new NaturalLanguageProcessor();
        WordNetHandler wordNet = new WordNetHandler();
        DiseasesComplexNetwork dComplexNet = new DiseasesComplexNetwork();

        double similarity=0; 
        double pragmaticSimilarity=0; //14
        double semanticSimilarity=0; //4
        double syntacticSimilarity=0; //2
        // sync.setHasAddress("endereco"); // string simples
        if(serviceSearched.getIncludesSyntactic().getHasAddress() != null){
            if(serviceRegistered.getIncludesSyntactic().getHasAddress() != null){
                syntacticSimilarity = syntacticSimilarity + nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesSyntactic().getHasAddress(), serviceRegistered.getIncludesSyntactic().getHasAddress());
            }else{
                syntacticSimilarity = syntacticSimilarity + 5;
            }
            
        }
        // sync.setHasReturn("bollean"); // string simples
         if(serviceSearched.getIncludesSyntactic().getHasReturn() != null){
            if(serviceRegistered.getIncludesSyntactic().getHasReturn() != null){
                syntacticSimilarity = syntacticSimilarity + nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesSyntactic().getHasReturn(), serviceRegistered.getIncludesSyntactic().getHasReturn());
            }else{
                syntacticSimilarity = syntacticSimilarity + 5;
            }
            
        }
         //frs.add("cadastrar usuario"); // sentença Decidi que faz mais sentido ter apenas um req. Func. (Mudar de array para string)
          if(!serviceRegistered.getIncludesSemantic().getHasFunctionalRequirements().isEmpty()){
              if(!serviceSearched.getIncludesSemantic().getHasFunctionalRequirements().isEmpty()){
                   for(String s: serviceSearched.getIncludesSemantic().getHasFunctionalRequirements()){
                    semanticSimilarity = semanticSimilarity + nlProcessor.sentenceSimilaritySumo(s, serviceRegistered.getIncludesSemantic().getHasFunctionalRequirements().get(0));
                   }
              }
          }else{
             semanticSimilarity = semanticSimilarity + 5;
         }
         
         //sem.setHasSemanticReception("conceito a"); // conceito
         if(serviceSearched.getIncludesSemantic().getHasSemanticReception()!= null){
            
               if(serviceRegistered.getIncludesSemantic().getHasSemanticReception() != null){
                   // se é igual 
                   if(serviceSearched.getIncludesSemantic().getHasSemanticReception().equalsIgnoreCase(serviceRegistered.getIncludesSemantic().getHasSemanticReception())){
                       semanticSimilarity = semanticSimilarity + 0;
                   }else{
                       for ( String s: dComplexNet.relatedConcepts(serviceSearched.getIncludesSemantic().getHasSemanticReception())){
                           if(s.equals(serviceRegistered.getIncludesSemantic().getHasSemanticReception())){
                               semanticSimilarity = semanticSimilarity + 0.1;
                           }
                       }
                   }
                   
               }else{
                    semanticSimilarity = semanticSimilarity + 5;
               }
         }
         //sem.setHasSemanticRepresentation("conceito b"); // conceito
         if(serviceSearched.getIncludesSemantic().getHasSemanticRepresentation()!= null){
            
               if(serviceRegistered.getIncludesSemantic().getHasSemanticRepresentation() != null){
                   // se é igual 
                   if(serviceSearched.getIncludesSemantic().getHasSemanticRepresentation().equalsIgnoreCase(serviceRegistered.getIncludesSemantic().getHasSemanticRepresentation())){
                       semanticSimilarity = semanticSimilarity + 0;
                   }else{
                       for ( String s: dComplexNet.relatedConcepts(serviceSearched.getIncludesSemantic().getHasSemanticRepresentation())){
                           if(s.equals(serviceRegistered.getIncludesSemantic().getHasSemanticRepresentation())){
                               semanticSimilarity = semanticSimilarity + 0.1;
                           }
                       }
                   }
                   
               }else{
                    semanticSimilarity = semanticSimilarity + 5;
               }
         }
         
         //sem.setHasSemanticReturn("conceito c"); //conceito
          if(serviceSearched.getIncludesSemantic().getHasSemanticReturn()!= null){
            
               if(serviceRegistered.getIncludesSemantic().getHasSemanticReturn() != null){
                   // se é igual 
                   if(serviceSearched.getIncludesSemantic().getHasSemanticReturn().equalsIgnoreCase(serviceRegistered.getIncludesSemantic().getHasSemanticReturn())){
                       semanticSimilarity = semanticSimilarity + 0;
                   }else{
                       for ( String s: dComplexNet.relatedConcepts(serviceSearched.getIncludesSemantic().getHasSemanticReturn())){
                           if(s.equals(serviceRegistered.getIncludesSemantic().getHasSemanticReturn())){
                               semanticSimilarity = semanticSimilarity + 0.1;
                           }
                       }
                   }
                   
               }else{
                    semanticSimilarity = semanticSimilarity + 5;
               }
         }
          //nfrs.add("nao pode isso"); // sentença
          if(!serviceRegistered.getIncludesPragmatic().getHasNonFunctionalRequirement().isEmpty()){
             if(!serviceSearched.getIncludesPragmatic().getHasNonFunctionalRequirement().isEmpty()){
                for(String s: serviceSearched.getIncludesPragmatic().getHasNonFunctionalRequirement()){
                   pragmaticSimilarity = pragmaticSimilarity + nlProcessor.sentenceSimilaritySumo(s, serviceRegistered.getIncludesPragmatic().getHasNonFunctionalRequirement().get(0));
                }
            }
          }else{
             pragmaticSimilarity = pragmaticSimilarity + 5;
         }
          
          //c.setHasArtifact("Prot 100"); // conceito
          if(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasArtifact()!= null){
            
               if(serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasArtifact() != null){
                   // se é igual 
                   if(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasArtifact().equalsIgnoreCase(serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasArtifact())){
                       pragmaticSimilarity = pragmaticSimilarity + 0;
                   }else{
                       for ( String s: dComplexNet.relatedConcepts(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasArtifact())){
                           if(s.equals(serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasArtifact())){
                               pragmaticSimilarity = pragmaticSimilarity + 0.1;
                           }
                       }
                   }
                   
               }else{
                    pragmaticSimilarity = pragmaticSimilarity + 5;
               }
         }
          //c.setHasDomain("DNA"); // conceito
           if(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasDomain()!= null){
            
               if(serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasDomain() != null){
                   // se é igual 
                   if(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasDomain().equalsIgnoreCase(serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasDomain())){
                       pragmaticSimilarity = pragmaticSimilarity + 0;
                   }else{
                       for ( String s: dComplexNet.relatedConcepts(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasDomain())){
                           if(s.equals(serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasDomain())){
                               pragmaticSimilarity = pragmaticSimilarity + 0.1;
                           }
                       }
                   }
                   
               }else{
                    pragmaticSimilarity = pragmaticSimilarity + 5;
               }
         }
           //c.setHasComments("Blbl"); // Texto longo
          if(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasComments() != null){
            if(serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasComments() != null){
                pragmaticSimilarity = pragmaticSimilarity + nlProcessor.textSimilarityLexical(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasComments(), serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasComments());
            }else{
                pragmaticSimilarity = pragmaticSimilarity + 5;
            }
            
        }
          //c.setHasLicense("public"); // string simples
          if(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasLicense() != null){
            if(serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasLicense() != null){
                pragmaticSimilarity = pragmaticSimilarity + nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasLicense(), serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasLicense());
            }else{
                pragmaticSimilarity = pragmaticSimilarity + 5;
            }
            
        }
          //c.setWhere("la"); // string simples
           if(serviceSearched.getIncludesPragmatic().getIncludesContext().getWhere() != null){
            if(serviceRegistered.getIncludesPragmatic().getIncludesContext().getWhere() != null){
                pragmaticSimilarity = pragmaticSimilarity + nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesPragmatic().getIncludesContext().getWhere(), serviceRegistered.getIncludesPragmatic().getIncludesContext().getWhere());
            }else{
                pragmaticSimilarity = pragmaticSimilarity + 5;
            }
            
        }
           // c.setHasRestriction("restric"); // sentença
           if(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasRestriction() != null){
            if(serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasRestriction() != null){
                pragmaticSimilarity = pragmaticSimilarity + nlProcessor.sentenceSimilaritySumo(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasRestriction(), serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasRestriction());
            }else{
                pragmaticSimilarity = pragmaticSimilarity + 5;
            }
            
        }
           //c.setHow("assim"); // sentença
           if(serviceSearched.getIncludesPragmatic().getIncludesContext().getHow() != null){
            if(serviceRegistered.getIncludesPragmatic().getIncludesContext().getHow() != null){
                pragmaticSimilarity = nlProcessor.sentenceSimilaritySumo(serviceSearched.getIncludesPragmatic().getIncludesContext().getHow(), serviceRegistered.getIncludesPragmatic().getIncludesContext().getHow());
            }else{
                pragmaticSimilarity = pragmaticSimilarity + 5;
            }
            
        }
           // c.setWhat("isso"); // sentença
           if(serviceSearched.getIncludesPragmatic().getIncludesContext().getWhat() != null){
            if(serviceRegistered.getIncludesPragmatic().getIncludesContext().getWhat() != null){
                pragmaticSimilarity = nlProcessor.sentenceSimilaritySumo(serviceSearched.getIncludesPragmatic().getIncludesContext().getWhat(), serviceRegistered.getIncludesPragmatic().getIncludesContext().getWhat());
            }else{
                pragmaticSimilarity = pragmaticSimilarity + 5;
            }
            
        }
        double localSimilarityPrevious=0;
        double localSimilarity=0;
        if(!serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasInvolved().isEmpty()){
           if(!serviceSearched.getIncludesPragmatic().getIncludesContext().getHasInvolved().isEmpty()){
            
               for (ScientistVO scien : serviceRegistered.getIncludesPragmatic().getIncludesContext().getHasInvolved()) {
                   //st1.setHasCompleteName("joao"); // string simples ou setença
                   localSimilarity = localSimilarity + nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasInvolved().get(0).getHasCompleteName(), scien.getHasCompleteName());
                   //st1.setHasEmail("joao@couves"); // string simple
                   localSimilarity = localSimilarity + nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasInvolved().get(0).getHasEmail(), scien.getHasEmail());
                   //st1.setHasInstitutionFiliation("HU"); // string simples ou setença
                   localSimilarity = localSimilarity + nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesPragmatic().getIncludesContext().getHasInvolved().get(0).getHasInstitutionFiliation(), scien.getHasInstitutionFiliation());
                   
                   if(localSimilarityPrevious == 0 ||localSimilarity<localSimilarityPrevious ){
                        localSimilarityPrevious = localSimilarity;
                   }
               }
               pragmaticSimilarity = pragmaticSimilarity + localSimilarityPrevious;
            }
        }else{
            pragmaticSimilarity = pragmaticSimilarity + 5;
        }
        // h.setHasCPU("Quad Core"); // String Simples
          if(serviceSearched.getIncludesPragmatic().getIncludesHardware().getHasCPU() != null){
            if(serviceRegistered.getIncludesPragmatic().getIncludesHardware().getHasCPU() != null){
                pragmaticSimilarity = pragmaticSimilarity + nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesPragmatic().getIncludesHardware().getHasCPU(), serviceRegistered.getIncludesPragmatic().getIncludesHardware().getHasCPU());
            }else{
                pragmaticSimilarity = pragmaticSimilarity + 5;
            }
            
        }
          //h.setHasOperationalSystem("Linux");
          if(serviceSearched.getIncludesPragmatic().getIncludesHardware().getHasOperationalSystem() != null){
            if(serviceRegistered.getIncludesPragmatic().getIncludesHardware().getHasOperationalSystem() != null){
                pragmaticSimilarity = pragmaticSimilarity + nlProcessor.stringSimilarityLeveshteinAAndLexicalDistance(serviceSearched.getIncludesPragmatic().getIncludesHardware().getHasOperationalSystem(), serviceRegistered.getIncludesPragmatic().getIncludesHardware().getHasOperationalSystem());
            }else{
                pragmaticSimilarity = pragmaticSimilarity + 5;
            }
            
        }
          // h.setHasRAM("6"); // number
          if(serviceSearched.getIncludesPragmatic().getIncludesHardware().getHasRAM() != null){
            if(serviceRegistered.getIncludesPragmatic().getIncludesHardware().getHasRAM() != null){
                if(serviceSearched.getIncludesPragmatic().getIncludesHardware().getHasRAM().equalsIgnoreCase(serviceRegistered.getIncludesPragmatic().getIncludesHardware().getHasRAM())){
                    pragmaticSimilarity = pragmaticSimilarity + 0;
                }
            }else{
                pragmaticSimilarity = pragmaticSimilarity + 5;
            }
            
        }
        //h.setHasROM("500"); // number 
          if(serviceSearched.getIncludesPragmatic().getIncludesHardware().getHasROM() != null){
            if(serviceRegistered.getIncludesPragmatic().getIncludesHardware().getHasROM() != null){
                if(serviceSearched.getIncludesPragmatic().getIncludesHardware().getHasROM().equalsIgnoreCase(serviceRegistered.getIncludesPragmatic().getIncludesHardware().getHasROM())){
                    pragmaticSimilarity = pragmaticSimilarity + 0;
                }
            }else{
                pragmaticSimilarity = pragmaticSimilarity + 5;
            }
            
        }
        similarity = (SyntacticWeight * (syntacticSimilarity/2)) + (SemanticWeight * (semanticSimilarity/ 4)) + (PragmaticWeight * (pragmaticSimilarity/14));      
        return similarity;
        
    }
    
     public static void main(String[] args) throws IOException {
        
         ServiceDescriptionVO sdesc = new ServiceDescriptionVO();
         ServiceRecovery sr = new ServiceRecovery();
      
         List<ServiceDescriptionVO> services = sr.Recovery();
         
         // Testando registro de serviço
         
         
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("endereco");
         sync.setHasReturn("bollean");
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("cadastrar usuario");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("conceito a");
         sem.setHasSemanticRepresentation("conceito b");
         sem.setHasSemanticReturn("conceito c");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("joao");
         st1.setHasEmail("joao@couves");
         st1.setHasInstitutionFiliation("HU");
         
         ScientistVO st2 = new ScientistVO();
         st2.setHasCompleteName("joana");
         st2.setHasEmail("joana@couves");
         st2.setHasInstitutionFiliation("HU");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);
         sts.add(st2);
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("Quad Core");
         h.setHasOperationalSystem("Linux");
         h.setHasRAM("6");
         h.setHasROM("500");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Prot 100");
         c.setHasComments("Blbl");
         c.setHasDomain("DNA");
         c.setHasInvolved(sts);
         c.setHasLicense("public");
         c.setHasReputation("5");
         c.setHasRestriction("restric");
         c.setHow("assim");
         c.setWhat("isso");
         c.setWhen("aquele dia");
         c.setWhere("la");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("nao pode isso");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("testeFran");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         
         SimilarityCalculation1 sc1 = new SimilarityCalculation1();
         System.out.println(sc1.calculate(sdesc, sdesc, 1, 1, 1));
         System.out.println(sc1.calculate(sdesc, services.get(0), 1, 1, 1));
         System.out.println(sc1.calculate(sdesc, services.get(1), 1, 1, 1));
         
     
         

    }
    
}

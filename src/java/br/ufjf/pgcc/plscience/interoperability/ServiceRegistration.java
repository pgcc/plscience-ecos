/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.interoperability;

import br.ufjf.pgcc.plscience.util.RandomAccessFilePlus;
import br.ufjf.pgcc.plscience.vo.ContextVO;
import br.ufjf.pgcc.plscience.vo.HardwareVO;
import br.ufjf.pgcc.plscience.vo.PragmaticVO;
import br.ufjf.pgcc.plscience.vo.ScientistVO;
import br.ufjf.pgcc.plscience.vo.SemanticVO;
import br.ufjf.pgcc.plscience.vo.ServiceDescriptionVO;
import br.ufjf.pgcc.plscience.vo.SyntacticVO;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Fran
 */
public class ServiceRegistration {
   
    public ServiceRegistration(){ 
        
    }
    
    public void Register(ServiceDescriptionVO serv){
        
        try {
            StringBuilder  sb = new StringBuilder();
            
            //CAMINHO LOCAL
            //File file = new File("D:\\ServiceDescriptionInf.owl");
            
            //CAMINHO SERVIDOR
            File file = new File("/var/www/ontologies/ServiceDescriptionInf.owl");
            
            RandomAccessFilePlus rafp = new RandomAccessFilePlus(new RandomAccessFile(file, "rw"));
            
            String line;
            
            while (!(line = rafp.readLine()).equals("<!-- new individuals -->")){
                System.out.println(line);
                
            }
            
            //Adiciona Sintátic PS: Arrumar os parâmetros na ontologia e incluir aqui!!!!!
            sb.append("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;")
                    .append("Syntatic_").append(serv.getName()).append("\">\n")
                    .append("<rdf:type rdf:resource=\"&PLScienceServiceDescription;Syntactic\"/>\n")
                    .append("<PLScienceServiceDescription:hasReturn>")
                    .append(serv.getIncludesSyntactic().getHasReturn())
                    .append("</PLScienceServiceDescription:hasReturn>\n")
                    .append("<PLScienceServiceDescription:hasName>")
                    .append(serv.getIncludesSyntactic().getHasAddress())
                    .append("</PLScienceServiceDescription:hasName>\n")
                    .append("</owl:NamedIndividual>\n\n");
            
            //Adiciona semântica
            sb.append("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;")
              .append("Semantic_").append(serv.getName()).append("\">\n")
              .append("<rdf:type rdf:resource=\"&PLScienceServiceDescription;Semantic\"/>\n")
              .append("<PLScienceServiceDescription:hasSemanticReception>")
              .append(serv.getIncludesSemantic().getHasSemanticReception())
              .append("</PLScienceServiceDescription:hasSemanticReception>\n")
              .append("<PLScienceServiceDescription:hasSemanticReturn>")
              .append(serv.getIncludesSemantic().getHasSemanticReturn())
              .append("</PLScienceServiceDescription:hasSemanticReturn>\n")
              .append("<PLScienceServiceDescription:hasSemanticRepresentation>")
              .append(serv.getIncludesSemantic().getHasSemanticRepresentation())
              .append("</PLScienceServiceDescription:hasSemanticRepresentation>\n");
            Iterator it = serv.getIncludesSemantic().getHasFunctionalRequirements().iterator();
            while(it.hasNext()){
               Object element = it.next();
               sb.append(" <PLScienceServiceDescription:hasFunctionalRequirements>")
              .append(element)
              .append("</PLScienceServiceDescription:hasFunctionalRequirements>\n");
            }
              
             sb.append("</owl:NamedIndividual>\n\n");
             int i =0;
            //Adiciona cientista
             if(null!=serv.getIncludesPragmatic().getIncludesContext().getHasInvolved()){
                for (ScientistVO s : serv.getIncludesPragmatic().getIncludesContext().getHasInvolved()) {
                   sb.append("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;")
                     .append("Scientist_").append(i).append(serv.getName()).append("\">\n")
                     .append("<rdf:type rdf:resource=\"&PLScienceServiceDescription;Scientist\"/>\n")
                     .append("<PLScienceServiceDescription:hasInstitutionFiliation>")
                     .append(s.getHasInstitutionFiliation())
                     .append("</PLScienceServiceDescription:hasInstitutionFiliation>\n")
                     .append("<PLScienceServiceDescription:hasCompleteName>")
                     .append(s.getHasCompleteName())
                     .append("</PLScienceServiceDescription:hasCompleteName>\n")
                     .append("<PLScienceServiceDescription:hasEmail>")
                     .append(s.getHasEmail())
                     .append("</PLScienceServiceDescription:hasEmail>\n")
                     .append("</owl:NamedIndividual>\n\n");     
                   i++;
               }
            }
             
            // adiciona hardware
             sb.append("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;")
               .append("Hardware_").append(serv.getName()).append("\">\n")
               .append(" <rdf:type rdf:resource=\"&PLScienceServiceDescription;Hardware\"/>\n")
               .append("<rdf:type rdf:resource=\"&PLScienceServiceDescription;Pragmatic\"/>\n")
               .append("<PLScienceServiceDescription:hasCPU>")
               .append(serv.getIncludesPragmatic().getIncludesHardware().getHasCPU())
               .append("</PLScienceServiceDescription:hasCPU>\n")
               .append("<PLScienceServiceDescription:hasROM>")
               .append(serv.getIncludesPragmatic().getIncludesHardware().getHasROM())
               .append("</PLScienceServiceDescription:hasROM>\n")
               .append("<PLScienceServiceDescription:hasRAM>")
               .append(serv.getIncludesPragmatic().getIncludesHardware().getHasRAM())
               .append("</PLScienceServiceDescription:hasRAM>\n")
               .append("<PLScienceServiceDescription:hasOperationalSystem>")
               .append(serv.getIncludesPragmatic().getIncludesHardware().getHasOperationalSystem())
               .append("</PLScienceServiceDescription:hasOperationalSystem>\n")
               .append("</owl:NamedIndividual>\n\n");
             
             //adiciona contexto
              sb.append("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;")
               .append("Context_").append(serv.getName()).append("\">\n")
               .append("<rdf:type rdf:resource=\"&PLScienceServiceDescription;Context\"/>\n")
               .append("<rdf:type rdf:resource=\"&PLScienceServiceDescription;Pragmatic\"/>\n")
               .append("<PLScienceServiceDescription:hasLicense>")
               .append(serv.getIncludesPragmatic().getIncludesContext().getHasLicense())
               .append("</PLScienceServiceDescription:hasLicense>\n")
               .append(" <PLScienceServiceDescription:hasReputation>")
               .append(serv.getIncludesPragmatic().getIncludesContext().getHasReputation())
               .append("</PLScienceServiceDescription:hasReputation>\n")
               .append("<PLScienceServiceDescription:when>")
               .append(serv.getIncludesPragmatic().getIncludesContext().getWhen())
               .append("</PLScienceServiceDescription:when>\n")
               .append("<PLScienceServiceDescription:hasRestriction>")
               .append(serv.getIncludesPragmatic().getIncludesContext().getHasRestriction())
               .append("</PLScienceServiceDescription:hasRestriction>\n")
               .append("<PLScienceServiceDescription:hasComments>")
               .append(serv.getIncludesPragmatic().getIncludesContext().getHasComments())
               .append("</PLScienceServiceDescription:hasComments>\n")
               .append("<PLScienceServiceDescription:what>")
               .append(serv.getIncludesPragmatic().getIncludesContext().getWhat())
               .append("</PLScienceServiceDescription:what>\n")
               .append("<PLScienceServiceDescription:hasArtifact>")
               .append(serv.getIncludesPragmatic().getIncludesContext().getHasArtifact())
               .append("</PLScienceServiceDescription:hasArtifact>\n")
               .append("<PLScienceServiceDescription:how>")
               .append(serv.getIncludesPragmatic().getIncludesContext().getHow())
               .append("</PLScienceServiceDescription:how>\n")
               .append("<PLScienceServiceDescription:where>")
               .append(serv.getIncludesPragmatic().getIncludesContext().getWhere())
               .append("</PLScienceServiceDescription:where>\n")
               .append(" <PLScienceServiceDescription:hasDomain>")
               .append(serv.getIncludesPragmatic().getIncludesContext().getHasDomain())
               .append("</PLScienceServiceDescription:hasDomain>\n");
              
             i =0;
            //Adiciona cientista no contexto
             if(null!=serv.getIncludesPragmatic().getIncludesContext().getHasInvolved()){
                for (ScientistVO s : serv.getIncludesPragmatic().getIncludesContext().getHasInvolved()) {
                    sb.append("<PLScienceServiceDescription:hasInvolved rdf:resource=\"&PLScienceServiceDescription;")
                    .append("Scientist_").append(i).append(serv.getName()).append("\"/>\n"); 
                     i++;
                }
                 sb.append("<PLScienceServiceDescription:who rdf:resource=\"&PLScienceServiceDescription;")
                   .append("Scientist_").append(i--).append(serv.getName()).append("\"/>\n")
                   .append("</owl:NamedIndividual>\n\n");
             }else{
                 sb.append("</owl:NamedIndividual>\n\n");
             } 
            //adiciona pragmática
            sb.append("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;")
              .append("Pragmatic_").append(serv.getName()).append("\">\n")
              .append("<rdf:type rdf:resource=\"&PLScienceServiceDescription;Pragmatic\"/>\n");
              
            for(String nfr : serv.getIncludesPragmatic().getHasNonFunctionalRequirement()){
                sb.append("<PLScienceServiceDescription:hasNonFunctionalRequirement>")
                .append(nfr)
                .append("</PLScienceServiceDescription:hasNonFunctionalRequirement>\n");
            }
            //ARRUMAR OS NOMESSSS!!!
            sb.append("<PLScienceServiceDescription:includesContext rdf:resource=\"&PLScienceServiceDescription;"+"Context_"+serv.getName()+"\"/>")
              .append("<PLScienceServiceDescription:includesHardware rdf:resource=\"&PLScienceServiceDescription;"+"Hardware_"+serv.getName()+"\"/>") 
              .append("</owl:NamedIndividual>\n\n");
            
            
            //adicionar serviço
             sb.append("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;")
               .append("Service_").append(serv.getName()).append("\">\n")
               .append("<rdf:type rdf:resource=\"&PLScienceServiceDescription;Service\"/>\n")
               .append("<PLScienceServiceDescription:includesPragmatic rdf:resource=\"&PLScienceServiceDescription;"+"Pragmatic_"+serv.getName()+"\"/>\n")
               .append("<PLScienceServiceDescription:includesSemantic rdf:resource=\"&PLScienceServiceDescription;"+"Semantic_"+serv.getName()+"\"/>\n")
               .append("<PLScienceServiceDescription:includesSyntactic rdf:resource=\"&PLScienceServiceDescription;"+"Syntatic_"+serv.getName()+"\"/>\n")
               .append("</owl:NamedIndividual>\n\n");
            
  
            rafp.writeBytes(sb.toString());
            // Movendo o ponteiro ao inicio
            rafp.seek(0);
            
            // Fechando arquivo
            rafp.close();
        } catch (IOException ex) {
            Logger.getLogger(ServiceRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    // Fazer serviço geração automática de serviços baseado no main, puxando as opções de escrita de arrays com 
    //os conceitos possíveis, por exemplo. A escolhe o conceito aleatóriamente usando o random do java com o
    // size do array.
    public void automaticGeneration(int numberOfServices){
       //syn
         List<String> syncAddress = new ArrayList<String>();
         syncAddress.add("rua a");syncAddress.add("rua b");syncAddress.add("rua c");
         
         List<String> syncReturn = new ArrayList<String>();
         syncReturn.add("proteina A");syncReturn.add("proteina B");syncReturn.add("DNA");
         
         //sem
         List<String> semFuncReq = new ArrayList<String>();
         semFuncReq.add("extrair");semFuncReq.add("duplicar");semFuncReq.add("adicionar");
         List<String> semReception = new ArrayList<String>();
         semReception.add("proteina");semReception.add("DNA");semReception.add("RNA");
         List<String> semRepresentation = new ArrayList<String>();
         semRepresentation.add("analise proteica");semRepresentation.add("analise DNA");semRepresentation.add("duplicacao RNA");
         List<String> semReturn = new ArrayList<String>();
         semReturn.add("confirmacao");semReturn.add("cadeia proteica");semReturn.add("proteina");
         
         //scientist
         List<String> scieName = new ArrayList<String>();
         scieName.add("joao");scieName.add("jose");scieName.add("maria");
         List<String> scieEmail = new ArrayList<String>();
         scieEmail.add("joao@gmail.com");scieEmail.add("jose@gmail.com");scieEmail.add("maria@gmail.com");
         List<String> scieAffiliation = new ArrayList<String>();
         scieAffiliation.add("UFJF");scieAffiliation.add("UFRJ");scieAffiliation.add("UFF");
         
         //hardware
         List<String> hardCPU = new ArrayList<String>();
         hardCPU.add("Quad Core");hardCPU.add("I5");hardCPU.add("I3");
         List<String> hardOS = new ArrayList<String>();
         hardOS.add("Windows");hardOS.add("Linux");hardOS.add("MAC OS");
         List<String> hardRAM = new ArrayList<String>();
         hardRAM.add("2");hardRAM.add("4");hardRAM.add("5");
         List<String> hardROM = new ArrayList<String>();
         hardROM.add("500");hardROM.add("700");hardROM.add("1024");
         
         //context
         List<String> contArtifact = new ArrayList<String>();
         contArtifact.add("Artefato 1");contArtifact.add("Artefato 2");contArtifact.add("Artefato 3");
         List<String> contComment = new ArrayList<String>();
         contComment.add("Muito bom");contComment.add("Muito lento");contComment.add("Muito rapido");
         List<String> contDomain = new ArrayList<String>();
         contDomain.add("DNA");contDomain.add("RNA");contDomain.add("Proteinas");
         List<String> contInvolved = new ArrayList<String>();
         contInvolved.add("Maria");contInvolved.add("Ana");contInvolved.add("Pedro");
         List<String> contLicense = new ArrayList<String>();
         contLicense.add("Public");contLicense.add("Private");contLicense.add("Free");
         List<String> contReputation = new ArrayList<String>();
         contReputation.add("5");contReputation.add("1");contReputation.add("4");
         List<String> contRestriction = new ArrayList<String>();
         contRestriction.add("Proteina 47");contRestriction.add("Linux");contRestriction.add("Homens");
         List<String> contWhat = new ArrayList<String>();
         contWhat.add("execucao");contWhat.add("reuso");contWhat.add("composicao");
         List<String> contWhen = new ArrayList<String>();
         contWhen.add("12-01-2014");contWhen.add("13-04-2013");contWhen.add("06-06-2014");
         List<String> contWhere = new ArrayList<String>();
         contWhere.add("UFJF");contWhere.add("UFF");contWhere.add("UFRJ");
         List<String> contHow = new ArrayList<String>();
         contHow.add("Composicao com Artefato 2");contHow.add("Composicao com Artefato 1");contHow.add("Composicao com Artefato 3");
         
         //prag
         List<String> pragNonFuncReq = new ArrayList<String>();
         pragNonFuncReq.add("agilidade");pragNonFuncReq.add("escalabilidade");pragNonFuncReq.add("reusabilidade");
       
         for(int i=0; i< numberOfServices; i++){
        
           
         ServiceRegistration sr = new ServiceRegistration();
         ServiceDescriptionVO sdesc = new ServiceDescriptionVO();

         
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         
         Random rand = new Random();
         
         sync.setHasAddress(syncAddress.get(rand.nextInt((syncAddress.size()))));
         sync.setHasReturn(syncReturn.get(rand.nextInt((syncReturn.size()))));
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add(semFuncReq.get(rand.nextInt((semFuncReq.size()))));
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception(semReception.get(rand.nextInt((semReception.size()))));
         sem.setHasSemanticRepresentation(semRepresentation.get(rand.nextInt((semRepresentation.size()))));
         sem.setHasSemanticReturn(semReturn.get(rand.nextInt((semReturn.size()))));
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName(scieName.get(rand.nextInt((scieName.size()))));
         st1.setHasEmail(scieEmail.get(rand.nextInt((scieEmail.size()))));
         st1.setHasInstitutionFiliation(scieAffiliation.get(rand.nextInt((scieAffiliation.size()))));
         
         ScientistVO st2 = new ScientistVO();
         st2.setHasCompleteName(scieName.get(rand.nextInt((scieName.size()))));
         st2.setHasEmail(scieEmail.get(rand.nextInt((scieEmail.size()))));
         st2.setHasInstitutionFiliation(scieAffiliation.get(rand.nextInt((scieAffiliation.size()))));
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);
         sts.add(st2);
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU(hardCPU.get(rand.nextInt((hardCPU.size()))));
         h.setHasOperationalSystem(hardOS.get(rand.nextInt((hardOS.size()))));
         h.setHasRAM(hardRAM.get(rand.nextInt((hardRAM.size()))));
         h.setHasROM(hardROM.get(rand.nextInt((hardROM.size()))));
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact(contArtifact.get(rand.nextInt((contArtifact.size()))));
         c.setHasComments(contComment.get(rand.nextInt((contComment.size()))));
         c.setHasDomain(contDomain.get(rand.nextInt((contDomain.size()))));
         c.setHasInvolved(sts);
         c.setHasLicense(contLicense.get(rand.nextInt((contLicense.size()))));
         c.setHasReputation(contReputation.get(rand.nextInt((contReputation.size()))));
         c.setHasRestriction(contRestriction.get(rand.nextInt((contRestriction.size()))));
         c.setHow(contHow.get(rand.nextInt((contHow.size()))));
         c.setWhat(contWhat.get(rand.nextInt((contWhat.size()))));
         c.setWhen(contWhen.get(rand.nextInt((contWhen.size()))));
         c.setWhere(contWhere.get(rand.nextInt((contWhere.size()))));
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add(pragNonFuncReq.get(rand.nextInt((pragNonFuncReq.size()))));
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("testeFran"+i);
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
           
       } 
    }
    
  
     /*public static void main(String[] args) throws IOException {
         ServiceRegistration sr = new ServiceRegistration();
         ServiceDescriptionVO sdesc = new ServiceDescriptionVO();
         
         // Testando registro de serviço
         
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("endereco");
         sync.setHasReturn("bollean");
         
         //add semántica
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
         sr.Register(sdesc);
         
         //ps olhar todos os comentarios ao longo do texto e concertar
         // nao foi possivel adicionar mais de uma propriedade de requisitos
         // mudar a ontologia ou deixar somente um.
                 
         */
         // Criando serviços verdadeiros - Serviço 1
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("http://localhost:8080/plscience-ecos/rest/phenotypeTranslation");
         sync.setHasReturn("List<Object>");
         
         //add semántica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("traduzir tipos sanguineos em fenotipos");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("Blood Group");
         sem.setHasSemanticRepresentation("Blood Group");
         sem.setHasSemanticReturn("Blood Group");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("Jose M. David");
         st1.setHasEmail("jose@gmail.com");
         st1.setHasInstitutionFiliation("UFJF");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);        
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("Quad Core");
         h.setHasOperationalSystem("Linux");
         h.setHasRAM("6");
         h.setHasROM("500");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Fenotipos");
         c.setHasComments("Servico usado para transformar o grupo sanguineo nos fenotipos correspondentes");
         c.setHasDomain("Bloog Group");
         c.setHasInvolved(sts);
         c.setHasLicense("public");
         c.setHasReputation("5");
         c.setHasRestriction("Humans Blood");
         c.setHow("Composicao de servicos");
         c.setWhat("Servico PhenotypeTranslation");
         c.setWhen("12-01-2009");
         c.setWhere("UFJF");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("Reusabilidade");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("PhenotypeTranslation");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
         
                 
         */
         
        
         // Criando serviços verdadeiros - Serviço 2
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("http://localhost:8080/plscience-ecos/rest/phenotypeTranslationPrimitiveType");
         sync.setHasReturn("String");
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("traduzir tipos sanguineos nos fenotipos correspondentes");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("Blood Group");
         sem.setHasSemanticRepresentation("Blood Group");
         sem.setHasSemanticReturn("Blood Group");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("Jose Maria David");
         st1.setHasEmail("jose@gmail.com");
         st1.setHasInstitutionFiliation("UFJF");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);        
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("Quad Core");
         h.setHasOperationalSystem("Linux");
         h.setHasRAM("6");
         h.setHasROM("500");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Fenotipos");
         c.setHasComments("Transformar grupos sanguineos nos fenotipos correspondentes");
         c.setHasDomain("Bloog Group");
         c.setHasInvolved(sts);
         c.setHasLicense("public");
         c.setHasReputation("5");
         c.setHasRestriction("Humans Blood");
         c.setHow("Composicao de servicos");
         c.setWhat("Servico PhenotypeTranslation");
         c.setWhen("13-01-2009");
         c.setWhere("UFJF");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("Reusabilidade");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("PhenotypeTranslationPrimitiveType");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
         
                 
         */
         
         // Criando serviços verdadeiros - Serviço 3
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("http://localhost:8080/plscience-ecos/rest/matrixCombinationPhenotypes");
         sync.setHasReturn("List<Object>");
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("gerar matriz de combinacao de fenotipos");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("Adult i phenotype");
         sem.setHasSemanticRepresentation("Blood Group");
         sem.setHasSemanticReturn("Adult i phenotype");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("Francila W. Neiva");
         st1.setHasEmail("francila@gmail.com");
         st1.setHasInstitutionFiliation("UFJF");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);        
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("I5");
         h.setHasOperationalSystem("Windows");
         h.setHasRAM("6");
         h.setHasROM("1024");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Fenotipos");
         c.setHasComments("Este serviço gera uma matriz de fenotipos baseado em dois tipos sanguineos");
         c.setHasDomain("Bloog Group");
         c.setHasInvolved(sts);
         c.setHasLicense("public");
         c.setHasReputation("5");
         c.setHasRestriction("Humans Blood");
         c.setHow("Composicao de servicos");
         c.setWhat("Servico MatrixCombinationPhenotypes");
         c.setWhen("15-05-2012");
         c.setWhere("UFJF");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("Composabilidade");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("MatrixCombinationPhenotypes");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
         
                 
         */
         
         // Criando serviços verdadeiros - Serviço 4
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("http://localhost:8080/plscience-ecos/rest/matrixCombinationPhenotypesPrimitiveTypes");
         sync.setHasReturn("String");
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("gerar combinacao dos fenotipos fornecidos");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("Adult i phenotype");
         sem.setHasSemanticRepresentation("Blood Group");
         sem.setHasSemanticReturn("Adult i phenotype");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("Francine Weidt Neiva");
         st1.setHasEmail("francine@gmail.com");
         st1.setHasInstitutionFiliation("UFJF");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);        
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("I5");
         h.setHasOperationalSystem("Windows");
         h.setHasRAM("6");
         h.setHasROM("1024");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Fenotipos");
         c.setHasComments("Gera uma matriz de fenotipos baseado nos tipos sanguineos fornecidos");
         c.setHasDomain("Bloog Group");
         c.setHasInvolved(sts);
         c.setHasLicense("public");
         c.setHasReputation("4");
         c.setHasRestriction("Humans Blood");
         c.setHow("Composicao de servicos");
         c.setWhat("Servico MatrixCombinationPhenotypes");
         c.setWhen("16-05-2012");
         c.setWhere("UFJF");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("Composabilidade");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("MatrixCombinationPhenotypesPrimitiveTypes");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
         
                 
         */
         
         // Criando serviços verdadeiros - Serviço 6
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("http://localhost:8080/plscience-ecos/rest/bloodTypeProbabilityPrimitiveType");
         sync.setHasReturn("String");
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("Deve gerar as probabilidades de herenca para cada grupo sanguineo");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("Adult i phenotype");
         sem.setHasSemanticRepresentation("Blood Group");
         sem.setHasSemanticReturn("Blood Group");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("Lucas M. Silva");
         st1.setHasEmail("lucasilva@gmail.com");
         st1.setHasInstitutionFiliation("UFRJ");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);        
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("I3");
         h.setHasOperationalSystem("Windows");
         h.setHasRAM("8");
         h.setHasROM("1024");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Fenotipos");
         c.setHasComments("Gera as probabilidades de se herdar cada tipo sanguineo");
         c.setHasDomain("Bloog Group");
         c.setHasInvolved(sts);
         c.setHasLicense("public");
         c.setHasReputation("4");
         c.setHasRestriction("Humans Blood");
         c.setHow("Composicao de servicos");
         c.setWhat("Servico BloodTypeProbabilityPrimitiveType");
         c.setWhen("01-06-2013");
         c.setWhere("UFRJ");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("Agilidade");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("BloodTypeProbabilityPrimitiveTtpe");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
         
                 
         */
         
         // Criando serviços verdadeiros - Serviço 5
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("http://localhost:8080/plscience-ecos/rest/bloodTypeProbability");
         sync.setHasReturn("Object");
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("gerar probabilidade de herenca de um grupo sanguineo");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("Adult i phenotype");
         sem.setHasSemanticRepresentation("Blood Group");
         sem.setHasSemanticReturn("Blood Group");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("Lucas Maciel da Silva");
         st1.setHasEmail("lucassilva@gmail.com");
         st1.setHasInstitutionFiliation("UFRJ");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);        
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("I3");
         h.setHasOperationalSystem("Windows");
         h.setHasRAM("8");
         h.setHasROM("1024");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Fenotipos");
         c.setHasComments("Gera as probabilidades de heranca de cata tipo sanguineo");
         c.setHasDomain("Bloog Group");
         c.setHasInvolved(sts);
         c.setHasLicense("public");
         c.setHasReputation("4");
         c.setHasRestriction("Humans Blood");
         c.setHow("Composicao de servicos");
         c.setWhat("Servico BloodTypeProbability");
         c.setWhen("01-05-2013");
         c.setWhere("UFRJ");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("Agilidade");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("BloodTypeProbability");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
         
                 
         */
         
         // Criando serviços verdadeiros - Serviço 7
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("http://localhost:8080/plscience-ecos/rest/malariaExam");
         sync.setHasReturn("Boolean");
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("identificar presenca de malaria em amostra sanguinea");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("Blood Group");
         sem.setHasSemanticRepresentation("Malaria");
         sem.setHasSemanticReturn("Malaria");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("Eva Maia");
         st1.setHasEmail("evam@gmail.com");
         st1.setHasInstitutionFiliation("UFSJ");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);        
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("I7");
         h.setHasOperationalSystem("Windows");
         h.setHasRAM("4");
         h.setHasROM("500");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Amostra Sanguinea");
         c.setHasComments("Identifica malaria em uma amostra sanguinea");
         c.setHasDomain("Malaria");
         c.setHasInvolved(sts);
         c.setHasLicense("public");
         c.setHasReputation("3");
         c.setHasRestriction("Humans Blood");
         c.setHow("Composicao de servicos");
         c.setWhat("Servico MalariaExam");
         c.setWhen("01-02-2015");
         c.setWhere("UFSJ");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("Reusabilidade");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("MalariaExam");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
         
                 
         */
         
          // Criando serviços verdadeiros - Serviço 8
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("http://localhost:8080/plscience-ecos/rest/malariaIdentification");
         sync.setHasReturn("Object");
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("Analisar presenca de malaria em amostra sanguinea");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("Blood Group");
         sem.setHasSemanticRepresentation("Malaria");
         sem.setHasSemanticReturn("Malaria");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("Carolina Santos");
         st1.setHasEmail("carol@gmail.com");
         st1.setHasInstitutionFiliation("UFRJ");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);        
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("I7");
         h.setHasOperationalSystem("Windows");
         h.setHasRAM("8");
         h.setHasROM("500");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Amostra Sanguinea");
         c.setHasComments("Esse servico analisa a presenca de malaria em uma amostra de sangue");
         c.setHasDomain("Malaria");
         c.setHasInvolved(sts);
         c.setHasLicense("private");
         c.setHasReputation("5");
         c.setHasRestriction("Humans Blood");
         c.setHow("Composicao de servicos");
         c.setWhat("Servico MalariaIdentification");
         c.setWhen("2-01-2015");
         c.setWhere("UFRJ");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("Reusabilidade");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("MalariaIdentification");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
         
                 
         */
         
         // Criando serviços verdadeiros - Serviço 9
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("http://localhost:8080/plscience-ecos/rest/hemolyticAnemiaExam");
         sync.setHasReturn("Object");
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("Analisar presenca de anemia hemolitica em amostra sanguinea");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("Blood Group");
         sem.setHasSemanticRepresentation("Hemolytic anemia");
         sem.setHasSemanticReturn("Hemolytic anemia");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("Francila Neiva");
         st1.setHasEmail("francila@gmail.com");
         st1.setHasInstitutionFiliation("UFJF");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);        
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("I7");
         h.setHasOperationalSystem("Linux");
         h.setHasRAM("4");
         h.setHasROM("500");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Amostra Sanguinea");
         c.setHasComments("Esse servico analisa a presenca de anemia hemolitica em uma amostra de sangue");
         c.setHasDomain("Hemolytic anemia");
         c.setHasInvolved(sts);
         c.setHasLicense("public");
         c.setHasReputation("5");
         c.setHasRestriction("Humans Blood");
         c.setHow("Composicao de servicos");
         c.setWhat("Servico HemolyticAnemiaExam");
         c.setWhen("02-01-2015");
         c.setWhere("UFJF");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("Agilidade");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("HemolyticAnemiaExam");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
                 
         */
         
         // Criando serviços verdadeiros - Serviço 10
         /*
         //add sintática
         SyntacticVO sync = new SyntacticVO();
         sync.setHasAddress("http://localhost:8080/plscience-ecos/rest/hemolyticAnemiaIdentification");
         sync.setHasReturn("String");
         
         //add semântica
         SemanticVO sem = new SemanticVO();
         ArrayList<String> frs = new ArrayList();
         frs.add("Identificar anemia hemolitica em amostra de sangue");
         sem.setHasFunctionalRequirements(frs);
         sem.setHasSemanticReception("Blood Group");
         sem.setHasSemanticRepresentation("Hemolytic anemia");
         sem.setHasSemanticReturn("Blood Group");
         
         //add cientista
         ScientistVO st1 = new ScientistVO();
         st1.setHasCompleteName("Francila Weidt Neiva");
         st1.setHasEmail("francila@gmail.com");
         st1.setHasInstitutionFiliation("UFJF");
         
         ArrayList<ScientistVO> sts = new ArrayList<ScientistVO>();
         sts.add(st1);        
         
         //add hardware
         HardwareVO h = new HardwareVO();
         h.setHasCPU("I7");
         h.setHasOperationalSystem("Windows");
         h.setHasRAM("4");
         h.setHasROM("500");
         
         
         //add contexto
         ContextVO c = new ContextVO();
         c.setHasArtifact("Amostra Sanguinea");
         c.setHasComments("Serve para executar a analise de positivo para a anemia hemolitica em uma amostra de sangue");
         c.setHasDomain("Hemolytic anemia");
         c.setHasInvolved(sts);
         c.setHasLicense("public");
         c.setHasReputation("5");
         c.setHasRestriction("Humans Blood");
         c.setHow("Composicao de servicos");
         c.setWhat("Servico HemolyticAnemiaIdentification");
         c.setWhen("06-01-2015");
         c.setWhere("UFJF");
         
         // Arrumar o who para um cientista. Arrumar como pegar o nome dos individuos, 
         //colocar um propriedade para poder acessar depois.
         c.setWho("");
         
         // add pragmatic
         PragmaticVO p = new PragmaticVO();
         ArrayList<String> nfrs = new ArrayList<String>();
         nfrs.add("Agilidade");
         p.setHasNonFunctionalRequirement(nfrs);
         p.setIncludesContext(c);
         p.setIncludesHardware(h);
     
         
         sdesc.setName("HemolyticAnemiaExam");
         sdesc.setIncludesSyntactic(sync);
         sdesc.setIncludesSemantic(sem);
         sdesc.setIncludesPragmatic(p);
         sr.Register(sdesc);
                 
         */
         /*
         //Testa gerador automático
         sr.automaticGeneration(5);

    } */
}


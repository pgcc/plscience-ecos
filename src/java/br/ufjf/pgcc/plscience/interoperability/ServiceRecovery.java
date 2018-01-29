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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author Fran
 */
public class ServiceRecovery {

    public List<ServiceDescriptionVO> Recovery() {
        List<ServiceDescriptionVO> services = new ArrayList<ServiceDescriptionVO>();

        try {
            StringBuilder sb = new StringBuilder();

            //CAMINHO LOCAL
            //File file = new File("D:\\Ontologias\\ServiceDescriptionInf.owl");
            //File file = new File("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/ontologies/ServiceDescriptionInf.owl");
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") +
            File.separator + "files" + File.separator + "ontologies" + File.separator+
            "ServiceDescriptionInf.owl";
                    
            File file = new File(path);
           
            //CAMINHO NO SERVIDOR
            //File file = new File("/var/www/ontologies/ServiceDescriptionInf.owl");
            RandomAccessFilePlus rafp = new RandomAccessFilePlus(new RandomAccessFile(file, "rw"));

            String line;

            while (!(line = rafp.readLine()).equals("<!-- new individuals -->")) {
                System.out.println(line);

            }

            while (!(line = rafp.readLine()).equals("</rdf:RDF>")) {
                sb.append(line);

            }

            String individuals = sb.toString();
            String partial = individuals;

            while (partial.contains("<PLScienceServiceDescription:includesPragmatic")) {

                int ini = partial.indexOf("<PLScienceServiceDescription:includesPragmatic rdf:resource=\"&PLScienceServiceDescription;");
                partial = partial.substring(ini);
                int pragIni = partial.indexOf(";");
                int pragFim = partial.indexOf("\"/>");
                String pragmaticName = partial.substring(pragIni + 1, pragFim);
                String serviceName = pragmaticName.substring(10);

                int meio = partial.indexOf("<PLScienceServiceDescription:includesSemantic rdf:resource=\"&PLScienceServiceDescription;");
                partial = partial.substring(meio);
                int semIni = partial.indexOf(";");
                int semFim = partial.indexOf("\"/>");
                String semanticName = partial.substring(semIni + 1, semFim);

                int fim = partial.indexOf("<PLScienceServiceDescription:includesSyntactic rdf:resource=\"&PLScienceServiceDescription;");
                partial = partial.substring(meio);
                int synIni = partial.indexOf(";");
                int synFim = partial.indexOf("\"/>");
                String syntacticName = partial.substring(synIni + 1, synFim);

                System.out.println(pragmaticName);
                System.out.println(semanticName);
                System.out.println(syntacticName);
                ServiceDescriptionVO serv = new ServiceDescriptionVO();
                serv.setIncludesSyntactic(SyntacticRecovery(syntacticName, individuals));
                serv.setIncludesSemantic(SemanticRecovery(semanticName, individuals));
                serv.setIncludesPragmatic(PragmaticRecovery(pragmaticName, individuals));
                serv.setName(serviceName);

                services.add(serv);
            }

            // Fechando arquivo
            rafp.close();
        } catch (IOException ex) {
            Logger.getLogger(ServiceRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }

    /**
     * Used to recovery services through Prime Search
     * @return List of Services
     */
    public List<ServiceDescriptionVO> RecoveryPrimeSearch() {
        List<ServiceDescriptionVO> services = new ArrayList<>();

        try {
            StringBuilder sb = new StringBuilder();
            File file = new File("/home/phillipe/NetBeansProjects/plscience-ecos/web/files/ontologies/ServiceDescriptionInf.owl");
            RandomAccessFilePlus rafp = new RandomAccessFilePlus(new RandomAccessFile(file, "rw"));
            String line;
            String individuals;
            String partial;

            while (!(line = rafp.readLine()).equals("</rdf:RDF>")) {
                sb.append(line);
            }

            individuals = sb.toString();
            partial = individuals;

            while (partial.contains("<PLScienceServiceDescription:includesPragmatic")) {
                int ini = partial.indexOf("<PLScienceServiceDescription:includesPragmatic rdf:resource=\"&PLScienceServiceDescription;");
                partial = partial.substring(ini);
                int pragIni = partial.indexOf(";");
                int pragFim = partial.indexOf("\"/>");
                String pragmaticName = partial.substring(pragIni + 1, pragFim);
                String serviceName = pragmaticName.substring(10);

                int meio = partial.indexOf("<PLScienceServiceDescription:includesSemantic rdf:resource=\"&PLScienceServiceDescription;");
                partial = partial.substring(meio);
                int semIni = partial.indexOf(";");
                int semFim = partial.indexOf("\"/>");
                String semanticName = partial.substring(semIni + 1, semFim);

                int fim = partial.indexOf("<PLScienceServiceDescription:includesSyntactic rdf:resource=\"&PLScienceServiceDescription;");
                partial = partial.substring(meio);
                int synIni = partial.indexOf(";");
                int synFim = partial.indexOf("\"/>");
                String syntacticName = partial.substring(synIni + 1, synFim);
                
                System.out.println("Pragmatic Name: "+pragmaticName);
                System.out.println("Semantic Name: "+semanticName);
                System.out.println("Syntatic Name: "+syntacticName);                
                ServiceDescriptionVO serv = new ServiceDescriptionVO();
                //o erro acontece por aqui
                SyntacticVO syntVOaux = SyntacticRecovery(syntacticName, individuals);
                serv.setIncludesSyntactic(syntVOaux);
                serv.setIncludesSemantic(SemanticRecovery(semanticName, individuals));
                serv.setIncludesPragmatic(PragmaticRecovery(pragmaticName, individuals));
                serv.setName(serviceName);
                services.add(serv);
            }

            // Fechando arquivo
            rafp.close();
        } catch (IOException ex) {
            Logger.getLogger(ServiceRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }

    public SyntacticVO SyntacticRecovery(String syntacticName, String individuals) {
        String partial = individuals;
        int ini = partial.indexOf("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;" + syntacticName + "\">");
        partial = partial.substring(ini);
        int returnIni = partial.indexOf("hasReturn>");
        int returnFim = partial.indexOf("</");
        SyntacticVO syn = new SyntacticVO();
        //eh aqui exatamente o erro! INI > FIM ???
        syn.setHasReturn(partial.substring(returnIni + 10, returnFim));

        partial = partial.substring(returnFim + 3);
        int addressIni = partial.indexOf("hasName>");
        int addressFim = partial.indexOf("</");
        syn.setHasAddress(partial.substring(addressIni + 8, addressFim));

        return syn;
    }

    public SemanticVO SemanticRecovery(String semanticName, String individuals) {
        String partial = individuals;
        int ini = partial.indexOf("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;" + semanticName + "\">");
        partial = partial.substring(ini);
        int receptionIni = partial.indexOf("hasSemanticReception>");
        int receptionFim = partial.indexOf("</");
        SemanticVO sem = new SemanticVO();
        sem.setHasSemanticReception(partial.substring(receptionIni + 21, receptionFim));

        partial = partial.substring(receptionFim + 3);
        int returnIni = partial.indexOf("hasSemanticReturn>");
        int returnFim = partial.indexOf("</");
        sem.setHasSemanticReturn(partial.substring(returnIni + 18, returnFim));

        partial = partial.substring(returnFim + 3);
        int representationIni = partial.indexOf("hasSemanticRepresentation>");
        int representationFim = partial.indexOf("</");
        sem.setHasSemanticRepresentation(partial.substring(representationIni + 26, representationFim));

        partial = partial.substring(representationFim + 3);
        int requirementIni = partial.indexOf("hasFunctionalRequirements>");
        int requirementFim = partial.indexOf("</");
        ArrayList<String> funcs = new ArrayList<String>();
        funcs.add(partial.substring(requirementIni + 26, requirementFim));
        sem.setHasFunctionalRequirements(funcs);

        return sem;
    }

    public PragmaticVO PragmaticRecovery(String pragmaticName, String individuals) {
        String partial = individuals;
        int ini = partial.indexOf("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;" + pragmaticName + "\">");
        partial = partial.substring(ini);
        int nonFunctionalIni = partial.indexOf("hasNonFunctionalRequirement>");
        int nonFunctionalFim = partial.indexOf("</");
        PragmaticVO prag = new PragmaticVO();
        ArrayList<String> nonfuncs = new ArrayList<String>();
        nonfuncs.add(partial.substring(nonFunctionalIni + 28, nonFunctionalFim));
        prag.setHasNonFunctionalRequirement(nonfuncs);

        partial = partial.substring(nonFunctionalFim + 3);
        int contextIni = partial.indexOf("includesContext rdf:resource=\"&PLScienceServiceDescription;");
        int contextFim = partial.indexOf("\"/>");
        String contextName = partial.substring(contextIni + 59, contextFim);

        partial = partial.substring(contextFim + 3);
        int hardwareIni = partial.indexOf("includesHardware rdf:resource=\"&PLScienceServiceDescription;");
        int hardwareFim = partial.indexOf("\"/>");
        String hardwareName = partial.substring(hardwareIni + 60, hardwareFim);

        prag.setIncludesHardware(HardwareRecovery(hardwareName, individuals));
        prag.setIncludesContext(ContextRecovery(contextName, individuals));
        return prag;
    }

    public HardwareVO HardwareRecovery(String hardwareName, String individuals) {
        String partial = individuals;
        int ini = partial.indexOf("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;" + hardwareName + "\">");
        partial = partial.substring(ini);
        int cpuIni = partial.indexOf("hasCPU>");
        int cpuFim = partial.indexOf("</");
        HardwareVO hard = new HardwareVO();
        hard.setHasCPU(partial.substring(cpuIni + 7, cpuFim));

        partial = partial.substring(cpuFim + 3);
        int romIni = partial.indexOf("hasROM>");
        int romFim = partial.indexOf("</");
        hard.setHasROM(partial.substring(romIni + 7, romFim));

        partial = partial.substring(romFim + 3);
        int ramIni = partial.indexOf("hasRAM>");
        int ramFim = partial.indexOf("</");
        hard.setHasRAM(partial.substring(ramIni + 7, ramFim));

        partial = partial.substring(ramFim + 3);
        int soIni = partial.indexOf("hasOperationalSystem>");
        int soFim = partial.indexOf("</");
        hard.setHasOperationalSystem(partial.substring(soIni + 21, soFim));

        return hard;
    }

    public ContextVO ContextRecovery(String contextName, String individuals) {
        String partial = individuals;
        int ini = partial.indexOf("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;" + contextName + "\">");
        partial = partial.substring(ini);
        int licenseIni = partial.indexOf("hasLicense>");
        int licenseFim = partial.indexOf("</");
        ContextVO con = new ContextVO();
        con.setHasLicense(partial.substring(licenseIni + 11, licenseFim));

        partial = partial.substring(licenseFim + 3);
        int reputationIni = partial.indexOf("hasReputation>");
        int reputationFim = partial.indexOf("</");
        con.setHasReputation(partial.substring(reputationIni + 14, reputationFim));

        partial = partial.substring(reputationFim + 3);
        int whenIni = partial.indexOf("when>");
        int whenFim = partial.indexOf("</");
        con.setWhen(partial.substring(whenIni + 5, whenFim));

        partial = partial.substring(whenFim + 3);
        int restrictionIni = partial.indexOf("hasRestriction>");
        int restrictionFim = partial.indexOf("</");
        con.setHasRestriction(partial.substring(restrictionIni + 15, restrictionFim));

        partial = partial.substring(restrictionFim + 3);
        int commentsIni = partial.indexOf("hasComments>");
        int commentsFim = partial.indexOf("</");
        con.setHasComments(partial.substring(commentsIni + 12, commentsFim));

        partial = partial.substring(commentsFim + 3);
        int whatIni = partial.indexOf("what>");
        int whatFim = partial.indexOf("</");
        con.setWhat(partial.substring(whatIni + 5, whatFim));

        partial = partial.substring(whatFim + 3);
        int artifactIni = partial.indexOf("hasArtifact>");
        int artifactFim = partial.indexOf("</");
        con.setHasArtifact(partial.substring(artifactIni + 12, artifactFim));

        partial = partial.substring(artifactFim + 3);
        int howIni = partial.indexOf("how>");
        int howFim = partial.indexOf("</");
        con.setHow(partial.substring(howIni + 4, howFim));

        partial = partial.substring(howFim + 3);
        int whereIni = partial.indexOf("where>");
        int whereFim = partial.indexOf("</");
        con.setWhere(partial.substring(whereIni + 6, whereFim));

        partial = partial.substring(whereFim + 3);
        int domainIni = partial.indexOf("hasDomain>");
        int domainFim = partial.indexOf("</");
        con.setHasDomain(partial.substring(domainIni + 10, domainFim));

        ArrayList<ScientistVO> scientists = new ArrayList<ScientistVO>();
        int individuofinal = partial.indexOf("</owl:NamedIndividual>");
        partial = partial.substring(domainFim + 3, individuofinal);

        while (partial.contains("hasInvolved rdf:resource=\"&PLScienceServiceDescription;")) {

            ini = partial.indexOf("hasInvolved rdf:resource=\"&PLScienceServiceDescription;");
            partial = partial.substring(ini);
            int scientistIni = partial.indexOf(";");
            int scientistFim = partial.indexOf("\"/>");
            scientists.add(ScientistRecovery(partial.substring(scientistIni + 1, scientistFim), individuals));
            partial = partial.substring(scientistFim + 3);

        }
        con.setHasInvolved(scientists);

        int whoIni = partial.indexOf("<PLScienceServiceDescription:who rdf:resource=\"&PLScienceServiceDescription;");
        int whoFim = partial.indexOf("/>");
        if (whoIni != -1 && whoFim != -1) {
            con.setWho(partial.substring(whoIni + 76, whoFim));
        }
        return con;
    }

    public ScientistVO ScientistRecovery(String scientistName, String individuals) {
        String partial = individuals;
        int ini = partial.indexOf("<owl:NamedIndividual rdf:about=\"&PLScienceServiceDescription;" + scientistName + "\">");
        partial = partial.substring(ini);
        int afiliationIni = partial.indexOf("hasInstitutionFiliation>");
        int afiliationFim = partial.indexOf("</");
        ScientistVO scie = new ScientistVO();
        scie.setHasInstitutionFiliation(partial.substring(afiliationIni + 24, afiliationFim));

        partial = partial.substring(afiliationFim + 3);
        int nameIni = partial.indexOf("hasCompleteName>");
        int nameFim = partial.indexOf("</");
        scie.setHasCompleteName(partial.substring(nameIni + 16, nameFim));

        partial = partial.substring(nameFim + 3);
        int emailIni = partial.indexOf("hasEmail>");
        int emailFim = partial.indexOf("</");
        scie.setHasEmail(partial.substring(emailIni + 9, emailFim));

        return scie;
    }

}

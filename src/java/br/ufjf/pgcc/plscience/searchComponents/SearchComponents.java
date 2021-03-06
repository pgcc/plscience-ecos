/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import br.ufjf.biocatalogue.core.BioCatalogueClient;
import br.ufjf.biocatalogue.exception.BioCatalogueException;
import br.ufjf.biocatalogue.model.Result;
import br.ufjf.biocatalogue.model.ServiceData;
import br.ufjf.biocatalogue.model.User;
import br.ufjf.myexperiment.model.Search;
import br.ufjf.myexperiment.core.MyExperimentClient;
import br.ufjf.myexperiment.exception.MyExperimentException;
import br.ufjf.myexperiment.model.File;
import br.ufjf.myexperiment.model.Pack;
import br.ufjf.myexperiment.model.Workflow;
import br.ufjf.pgcc.plscience.bean.experiments.prototyping.SearchBioCatalogue;
import br.ufjf.pgcc.plscience.bean.experiments.prototyping.SearchMyExperiment;
import br.ufjf.pgcc.plscience.dao.ActedOnBehalfOfDAO;
import br.ufjf.pgcc.plscience.dao.TaskDAO;
import br.ufjf.pgcc.plscience.dao.UsedDAO;
import br.ufjf.pgcc.plscience.dao.WasAssociatedWithDAO;
import br.ufjf.pgcc.plscience.dao.WasEndedByDAO;
import br.ufjf.pgcc.plscience.dao.WasInformedByDAO;
import br.ufjf.pgcc.plscience.interoperability.ServiceRecovery;
import br.ufjf.pgcc.plscience.interoperability.SimilarityCalculation1;
import br.ufjf.pgcc.plscience.message.InfoMessage;
import br.ufjf.pgcc.plscience.message.WarningMessage;
import br.ufjf.pgcc.plscience.model.ActedOnBehalfOf;
import br.ufjf.pgcc.plscience.model.Task;
import br.ufjf.pgcc.plscience.model.Used;
import br.ufjf.pgcc.plscience.model.WasAssociatedWith;
import br.ufjf.pgcc.plscience.model.WasEndedBy;
import br.ufjf.pgcc.plscience.model.WasInformedBy;
import br.ufjf.pgcc.plscience.ontology.OntologyDAO;
import br.ufjf.pgcc.plscience.vo.ContextVO;
import br.ufjf.pgcc.plscience.vo.HardwareVO;
import br.ufjf.pgcc.plscience.vo.PragmaticVO;
import br.ufjf.pgcc.plscience.vo.RankingVO;
import br.ufjf.pgcc.plscience.vo.SemanticVO;
import br.ufjf.pgcc.plscience.vo.ServiceDescriptionVO;
import br.ufjf.pgcc.plscience.vo.SyntacticVO;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.json.simple.parser.ParseException;
import org.mindswap.wsdl.WSDLOperation;

/**
 *
 * @author phillipe
 */
@ManagedBean()
@ViewScoped
public class SearchComponents implements Serializable {

    private static long serialVersionUID = 1L;
    private BioCatalogueClient bioClient;
    private MyExperimentClient myExpClient;
    private String searchQuery;
    private String[] selectedComponents;
    private String scope;
    private ArrayList<Result> results;
    private ArrayList<ResultsPatternFormat> patternResults;
    private Result selectedResult;
    private ProvenanceDetails provenanceDetails;
    private ServiceDescriptionVO serviceDescriptionVO;
    private List<ServiceDescriptionVO> servicesPrime;

    public SearchComponents() {
        //bioCatalogue
        bioClient = new BioCatalogueClient();
        bioClient.setBaseUri("https://www.biocatalogue.org");

        //myExperiment
        myExpClient = new MyExperimentClient();
        myExpClient.setBaseUri("http://www.myexperiment.org");

        //ESECO Instances - PRIME
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
        servicesPrime = new ArrayList<>();
    }

    /**
     * Search in all repositories
     *
     * @throws BioCatalogueException
     * @throws MyExperimentException
     * @throws java.io.IOException
     * @throws org.json.simple.parser.ParseException
     */
    public void search() throws BioCatalogueException, MyExperimentException, IOException, ParseException {
        setPatternResults(new ArrayList<>());
        if (selectedComponents.length != 0) {
            if (componentWasSelected("service")) {
                bioSearch();
                System.out.println("BioCatalogue Search Completed!");

                //primeSearch
                System.out.println("Searching in E-SECO Instances!");
                primeSearch();
                System.out.println("PRIME Search Completed");
            }
            if (componentWasSelected("workflow") || componentWasSelected("file")
                    || componentWasSelected("pack")) {
                myExperimentSearch();
                System.out.println("MyExperiment Search Completed!");
            }
            System.out.println("Search completed!");
        } else {
            WarningMessage.warnComponentsNotSelected();
        }
    }

    /**
     * Search in myExperiment Repository
     *
     * @throws br.ufjf.myexperiment.exception.MyExperimentException
     * @throws java.io.IOException
     */
    public void myExperimentSearch() throws MyExperimentException, IOException {
        Search myExpResult = SearchMyExperiment.searchAllComponents(searchQuery, myExpClient);
        List<Workflow> workflows = myExpResult.getWorkflow();
        List<File> files = myExpResult.getFile();
        List<Pack> packs = myExpResult.getPack();
        System.out.println("Serching in myExperiment Repository");
        if (componentWasSelected("workflow")) {
            if (workflows == null) {
                InfoMessage.infoWorkflowsNotFound();
            } else {
                for (Workflow w : workflows) {
                    ResultsPatternFormat rpf = new ResultsPatternFormat();
                    if (w.getDescription() != null) {
                        rpf.setDescription(w.getDescription());
                    } else {
                        rpf.setDescription("---");
                    }
                    if (w.getTitle() != null && !w.getTitle().equals("")) {
                        rpf.setName(w.getTitle());
                    } else {
                        rpf.setName("---");
                    }
                    if (w.getUploader() != null && !w.getUploader().equals("")) {
                        rpf.setOwner(w.getUploader());
                    } else {
                        rpf.setOwner("---");
                    }
                    if (w.getContentUri() != null && !w.getContentUri().equals("")) {
                        rpf.setFileLocation(w.getContentUri());
                        //rpf = rpf.createFileMyExperiment(rpf);
                    }
                    if (w.getCountry() != null && !w.getCountry().equals("")) {
                        rpf.setOwnerCountry(w.getCountry());
                    } else {
                        rpf.setOwnerCountry("---");
                    }
                    if (w.getCity() != null && !w.getCity().equals("")) {
                        rpf.setOwnerCity(w.getCity());
                    } else {
                        rpf.setOwnerCity("---");
                    }
                    if (w.getCreatedAt() != null && !w.getCreatedAt().equals("")) {
                        rpf.setCreatedAt(w.getCreatedAt());
                    } else {
                        rpf.setCreatedAt("---");
                    }
                    if (w.getUpdatedAt() != null && !w.getUpdatedAt().equals("")) {
                        rpf.setUpdatedAt(w.getUpdatedAt());
                    } else {
                        rpf.setUpdatedAt("---");
                    }
                    if (w.getLicenseType() != null && !w.getLicenseType().equals("")) {
                        rpf.setLicenseType(w.getLicenseType());
                    } else {
                        rpf.setLicenseType("---");
                    }
                    rpf.setComponentType("Workflow");
                    rpf.setRepositoryName("myExperiment");
                    getPatternResults().add(rpf);
                }

            }
        }

        if (componentWasSelected("file")) {
            if (files == null) {
                InfoMessage.infoFilesNotFound();
            } else {
                for (File f : files) {
                    ResultsPatternFormat rpf = new ResultsPatternFormat();
                    if (f.getDescription() != null) {
                        rpf.setDescription(f.getDescription());
                    } else {
                        rpf.setDescription("---");
                    }
                    if (f.getTitle() != null && !f.getTitle().equals("")) {
                        rpf.setName(f.getTitle());
                    } else {
                        rpf.setName("---");
                    }
                    if (f.getUploader() != null && !f.getUploader().equals("")) {
                        rpf.setOwner(f.getUploader());
                    } else {
                        rpf.setOwner("---");
                    }
                    if (f.getContentUri() != null && !f.getContentUri().equals("")) {
                        rpf.setFileLocation(f.getContentUri());
                        rpf.createFileMyExperiment(rpf);
                    } else {
                        rpf.createGenericServiceFile();
                    }
                    rpf.setComponentType("File");
                    rpf.setRepositoryName("myExperiment");
                    getPatternResults().add(rpf);
                }
            }

        }

        if (componentWasSelected("pack")) {
            if (packs == null) {
                InfoMessage.infoPacksNotFound();
            } else {
                for (Pack p : packs) {
                    ResultsPatternFormat rpf = new ResultsPatternFormat();
                    if (p.getDescription() != null) {
                        rpf.setDescription(p.getDescription());
                    } else {
                        rpf.setDescription("---");
                    }
                    if (p.getTitle() != null && !p.getTitle().equals("")) {
                        rpf.setName(p.getTitle());
                    } else {
                        rpf.setName("---");
                    }
                    if (p.getUploader() != null && !p.getUploader().equals("")) {
                        rpf.setOwner(p.getUploader());
                    } else {
                        rpf.setOwner("---");
                    }
                    if (p.getContentUri() != null && !p.getContentUri().equals("")) {
                        rpf.setFileLocation(p.getContentUri());
                        rpf.createFileMyExperiment(rpf);
                    } else {
                        rpf.createGenericServiceFile();
                    }
                    rpf.setComponentType("Pack");
                    rpf.setRepositoryName("myExperiment");
                    getPatternResults().add(rpf);
                }

            }
        }
    }

    /**
     * Search services in BioCatalogue Repository
     *
     * @throws br.ufjf.biocatalogue.exception.BioCatalogueException
     * @throws java.io.IOException
     * @throws org.json.simple.parser.ParseException
     */
    public void bioSearch() throws BioCatalogueException, IOException, ParseException {
        scope = "services";
        SearchBioCatalogue searchBio = new SearchBioCatalogue();
        results = searchBio.search(searchQuery, scope, results, bioClient);
        System.out.println("Serching in BioCatalogue Repository");
        if (results == null || results.isEmpty()) {
            InfoMessage.infoServicesNotFoundBioCatalogue();
        } else {
            for (Result result : results) {
                ResultsPatternFormat rpf = new ResultsPatternFormat();
                if (result.getName() != null) {
                    rpf.setName(result.getName());
                } else {
                    rpf.setName("---");
                }
                if (result.getDescription() != null) {
                    rpf.setDescription(result.getDescription());
                } else {
                    rpf.setDescription("---");
                }
                if (result.getResource() != null) {
                    String serviceAddress = result.getResource();
                    BioCatalogueClient searchServiceDataById = new BioCatalogueClient();
                    searchServiceDataById.setBaseUri("https://www.biocatalogue.org");
                    String serviceId = serviceAddress.replace("https://www.biocatalogue.org/services/", "");
                    System.out.println("Service Id: " + serviceId);
                    ServiceData serviceData = searchServiceDataById.serviceData(serviceId);
                    rpf.setServiceIdRepository(serviceId);
                    rpf.setFileLocation(serviceData.getServiceVariants().getWsdlLocation());
                }
                if (result.getSubmitter() != null) {
                    String ownerValue = result.getSubmitter();
                    URL url = new URL(ownerValue);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    int code = connection.getResponseCode();
                    if (code == 200) {//it checks that the page exists and does not return error
                        BioCatalogueClient searchUserDataById = new BioCatalogueClient();
                        searchUserDataById.setBaseUri("https://www.biocatalogue.org");
                        String id = ownerValue.replace("https://www.biocatalogue.org/users/", "");
                        try {
                            int b = Integer.parseInt(id);//used to verify if id is an integer
                            User userData = searchUserDataById.userData(id);
                            rpf.setOwner(userData.getName());
                            rpf.setOwnerCountry(userData.getUserLocation().getCountry());
                            rpf.setOwnerCountryFlagImage(userData.getUserLocation().getFlagImagePNG());
                        } catch (NumberFormatException nfe) {
                            rpf.setOwner("---");
                        }
                    } else {
                        rpf.setOwner("---");
                    }
                } else {
                    rpf.setOwner("---");
                }
                if (result.getResource() != null) {
                    rpf.setFileLocation(result.getResource());
                }
                if (result.getCreated_at() != null) {
                    rpf.setCreatedAt(result.getCreated_at());
                } else {
                    rpf.setCreatedAt("---");
                }
                if (result.getArchived_at() != null) {
                    rpf.setArchivedAt(result.getArchived_at());
                } else {
                    rpf.setArchivedAt("---");
                }
                if (result.getLatest_monitoring_status() != null) {
                    rpf.setMonitoringStatusLabel(result.getLatest_monitoring_status().getLabel());
                } else {
                    rpf.setMonitoringStatusLabel("---");
                }
                if (result.getLatest_monitoring_status().getLast_checked() != null) {
                    rpf.setMonitoringStatusLastChecked(result.getLatest_monitoring_status().getLast_checked());
                } else {
                    rpf.setMonitoringStatusLastChecked("---");
                }
                rpf.setComponentType("Service - " + result.getService_technology_types().get(0));
                rpf.setRepositoryName("BioCatalogue");
                getPatternResults().add(rpf);
            }
        }
    }

    /**
     * Search services in E-SECO Instances using PRIME
     *
     * @throws IOException
     */
    public void primeSearch() throws IOException {
        ArrayList<String> s = new ArrayList();
        s.add(serviceDescriptionVO.getIncludesSemantic().getHasFunctionalRequirement());
        serviceDescriptionVO.getIncludesSemantic().setHasFunctionalRequirements(s);

        ServiceRecovery sr = new ServiceRecovery();
        List<ServiceDescriptionVO> servicesRecovery;
        List<ServiceDescriptionVO> servicesRankingSorted = new ArrayList<>();
        servicesRecovery = sr.Recovery();

        SimilarityCalculation1 sc1 = new SimilarityCalculation1();
        ArrayList<RankingVO> rankingServices = new ArrayList<>();

        for (ServiceDescriptionVO sVo : servicesRecovery) {
            RankingVO rVo = new RankingVO();
            rVo.setServiceRecovery(sVo);
            rVo.setServiceComparison(serviceDescriptionVO);
            rVo.setSimilarity(sc1.calculate(serviceDescriptionVO, sVo, 1, 1, 1));
            rankingServices.add(rVo);
        }

        System.out.println("Sorting services using PRIME!");
        Collections.sort(rankingServices);

        for (RankingVO rank : rankingServices) {
            servicesRankingSorted.add(rank.getServiceRecovery());
            //System.out.println("Nome Serv: " + rank.getServiceRecovery().getName() + " Simil: " + rank.getSimilarity());
        }

        setServicesPrime(servicesRankingSorted);

        for (ServiceDescriptionVO se : servicesPrime) {
            ResultsPatternFormat rpf = new ResultsPatternFormat();
            rpf.setComponentType("Service");
            rpf.setRepositoryName("E-SECO");
            rpf.setOwnerCountry("Brazil");

            if (se.getName() != null && !se.getName().equals("")) {
                rpf.setName(se.getName());
            } else {
                rpf.setName("---");
            }

            if (se.getIncludesSyntactic().getHasReturn() != null) {
                rpf.setReturnPrimeSin(se.getIncludesSyntactic().getHasReturn());
            } else {
                rpf.setReturnPrimeSin("---");
            }

            if (se.getIncludesSyntactic().getHasAddress() != null) {
                rpf.setFileLocation(se.getIncludesSyntactic().getHasAddress());
            } else {
                rpf.setFileLocation("---");
            }

            if (se.getIncludesSemantic().getHasSemanticReturn() != null) {
                rpf.setReturnPrimeSem(se.getIncludesSemantic().getHasSemanticReturn());
            } else {
                rpf.setReturnPrimeSem("---");
            }

            if (se.getIncludesSemantic().getHasSemanticReception() != null) {
                rpf.setReceptionPrimeSem(se.getIncludesSemantic().getHasSemanticReception());
            } else {
                rpf.setReceptionPrimeSem("---");
            }

            if (se.getIncludesSemantic().getHasSemanticRepresentation() != null) {
                rpf.setRepresentationPrimeSem(se.getIncludesSemantic().getHasSemanticRepresentation());
            } else {
                rpf.setRepresentationPrimeSem("---");
            }

            if (se.getIncludesSemantic().getHasFunctionalRequirement() != null) {
                rpf.setFunctionalRequirementPrimeSem(se.getIncludesSemantic().getHasFunctionalRequirement());
            } else {
                rpf.setFunctionalRequirementPrimeSem("---");
            }

            if (se.getIncludesPragmatic().getHasNonFunctionalReq() != null) {
                rpf.setNonFunctionalRequirementPrimePra(se.getIncludesPragmatic().getHasNonFunctionalReq());
            } else {
                rpf.setNonFunctionalRequirementPrimePra("---");
            }

            if (se.getIncludesPragmatic().getIncludesContext() != null) {

                if (se.getIncludesPragmatic().getIncludesContext().getHasArtifact() != null) {
                    rpf.setArtifactPrimePra(se.getIncludesPragmatic().getIncludesContext().getHasArtifact());
                } else {
                    rpf.setArtifactPrimePra("---");
                }

                if (se.getIncludesPragmatic().getIncludesContext().getHasDomain() != null) {
                    rpf.setDomainPrimePra(se.getIncludesPragmatic().getIncludesContext().getHasDomain());
                } else {
                    rpf.setDomainPrimePra("---");
                }

                if (se.getIncludesPragmatic().getIncludesContext().getHasLicense() != null) {
                    rpf.setLicenseType(se.getIncludesPragmatic().getIncludesContext().getHasLicense());
                } else {
                    rpf.setLicenseType("---");
                }

                if (se.getIncludesPragmatic().getIncludesContext().getHasComments() != null) {
                    rpf.setDescription(se.getIncludesPragmatic().getIncludesContext().getHasComments());
                } else {
                    rpf.setDescription("---");
                }

                if (se.getIncludesPragmatic().getIncludesContext().getHow() != null) {
                    rpf.setPurposePrimePra(se.getIncludesPragmatic().getIncludesContext().getHow());
                } else {
                    rpf.setPurposePrimePra("---");
                }

                if (se.getIncludesPragmatic().getIncludesContext().getWhere() != null) {
                    rpf.setProviderPrimePra(se.getIncludesPragmatic().getIncludesContext().getWhere());
                } else {
                    rpf.setProviderPrimePra("---");
                }

                if (se.getIncludesPragmatic().getIncludesContext().getWhen() != null) {
                    rpf.setArchivedAt(se.getIncludesPragmatic().getIncludesContext().getWhen());
                } else {
                    rpf.setArchivedAt("---");
                }

                if (se.getIncludesPragmatic().getIncludesContext().getWho() != null) {
                    rpf.setOwner(se.getIncludesPragmatic().getIncludesContext().getWho());
                } else {
                    rpf.setOwner("---");
                }

                if (se.getIncludesPragmatic().getIncludesContext().getHasRestriction() != null) {
                    rpf.setRestrictionPrimePra(se.getIncludesPragmatic().getIncludesContext().getHasRestriction());
                } else {
                    rpf.setRestrictionPrimePra("---");
                }

            } else {
                rpf.setArtifactPrimePra("---");
                rpf.setDomainPrimePra("---");
                rpf.setPurposePrimePra("---");
                rpf.setProviderPrimePra("---");
                rpf.setArchivedAt("---");
                rpf.setRestrictionPrimePra("---");
                rpf.setLicenseType("---");
                rpf.setOwner("---");
                rpf.setDescription("---");
            }

            //adding service
            getPatternResults().add(rpf);
        }
    }

    /**
     * Checks if a component is selected
     *
     * @param term Component name used to search
     * @return
     */
    public boolean componentWasSelected(String term) {
        for (String componentS : selectedComponents) {
            if (componentS.contains(term)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param result
     * @throws java.io.IOException
     * @throws br.ufjf.biocatalogue.exception.BioCatalogueException
     * @throws org.json.simple.parser.ParseException
     */
    public void viewDetails(ResultsPatternFormat result) throws IOException, BioCatalogueException, ParseException {
        String type = result.getComponentType();
        List<String> used = new ArrayList<>();//used prov
        List<String> wib = new ArrayList<>(); //was informed by prov
        List<String> waw = new ArrayList<>();//was associated with prov
        List<String> abo = new ArrayList<>();//acted behalf of prov
        List<String> web = new ArrayList<>();//was ended by prov

        OntologyDAO ontoDAO = new OntologyDAO();//pegar inferencias da ontologia para a tarefa
        //List<String> evolutionTaskOntology = ontoDAO.buscarEvolutionToTask(type);

        provenanceDetails = new ProvenanceDetails();

        if (type.contains("Service") || type.contains("service")) {

            String fileLocation = result.getFileLocation();
            if (result.getRepositoryName().contains("Catalogue")) {

                if (!fileLocation.toUpperCase().contains("WSDL")) {
                    ResultsPatternFormat rpf = new ResultsPatternFormat();
                    fileLocation = rpf.searchWSDLLocationBioCatalogue(result.getServiceIdRepository());
                }

                //System.out.println("Localizacao do WSDL: "+fileLocation);
                if (fileLocation != null) {
                    ServiceManager sM = new ServiceManager();
                    List<WSDLOperation> operations;
                    operations = sM.generatesServiceInformation(fileLocation);
                    provenanceDetails.setServiceOperations(operations);
                    if (operations != null) {
                        for (WSDLOperation op : operations) {
                            Task task;
                            TaskDAO taskDAO = new TaskDAO();
                            UsedDAO usedDAO = new UsedDAO();
                            WasInformedByDAO wasInformedByDAO = new WasInformedByDAO();
                            WasAssociatedWithDAO wasAssociatedWithDAO = new WasAssociatedWithDAO();
                            ActedOnBehalfOfDAO actedOnBehalfOf = new ActedOnBehalfOfDAO();
                            WasEndedByDAO wasEndedByDAO = new WasEndedByDAO();

                            List<Integer> listUsedWorkflows = new ArrayList<>();
                            List<Used> listUsed = new ArrayList<>();
                            List<WasInformedBy> listWasInformedBy = new ArrayList<>();
                            List<WasAssociatedWith> listWasAssociatedWith = new ArrayList<>();
                            List<ActedOnBehalfOf> listActedOnBehalfOf = new ArrayList<>();
                            List<WasEndedBy> listWasEndedBy = new ArrayList<>();

                            task = taskDAO.buscar(op.getName());

                            if (task != null) {
                                //evolutionTaskOntology = ontoDAO.buscarEvolutionToTask(type);
                                listUsed = usedDAO.searchTask(task.getIdTask());
                                for (Used u : listUsed) {
                                    String usedProvenanceDataTask = u.getDescription() + " Task Id: "
                                            + u.getTaskidTask().getIdTask().toString()
                                            + " Workflow Id: " + u.getWorkflowidWorkflow().getIdWorkflow().toString();
                                    if (!used.contains(usedProvenanceDataTask)) {
                                        used.add(usedProvenanceDataTask);
                                    }
                                    if (!listUsedWorkflows.contains(u.getWorkflowidWorkflow().getIdWorkflow())) {
                                        listUsedWorkflows.add(u.getWorkflowidWorkflow().getIdWorkflow());
                                    }
                                }

                                listWasInformedBy = wasInformedByDAO.searchTask(task.getIdTask());
                                for (WasInformedBy w : listWasInformedBy) {
                                    String wasInfByProvDataTask = w.getDescription() + " Task Id: "
                                            + w.getTaskidTask().getIdTask().toString()
                                            + " Activity Id: " + w.getActivityidActivity().getIdActivity().toString();
                                    if (!wib.contains(wasInfByProvDataTask)) {
                                        wib.add(wasInfByProvDataTask);
                                    }
                                }

                                listWasEndedBy = wasEndedByDAO.searchTask(task.getIdTask());
                                for (WasEndedBy w : listWasEndedBy) {
                                    String wasEndedByProvTaskData = w.getDescription() + " Task Id: "
                                            + w.getTaskidTask().getIdTask().toString()
                                            + " Activity Id: " + w.getActivityidActivity().getIdActivity().toString();
                                    if (!web.contains(wasEndedByProvTaskData)) {
                                        web.add(wasEndedByProvTaskData);
                                    }
                                }

                                for (Integer i : listUsedWorkflows) {
                                    listWasAssociatedWith = wasAssociatedWithDAO.buscarWorkflow(i);
                                    for (WasAssociatedWith w : listWasAssociatedWith) {
                                        String wasAssocWithProvDataTask = w.getDescription() + " Experiment Id: "
                                                + w.getExperimentExperiment().getIdExperiment().toString() + " Workflow Id: "
                                                + w.getWorkflowidWorkflow().getIdWorkflow().toString();
                                        if (!waw.contains(wasAssocWithProvDataTask)) {
                                            waw.add(wasAssocWithProvDataTask);
                                        }
                                    }
                                    listActedOnBehalfOf = actedOnBehalfOf.buscar(i);
                                    for (ActedOnBehalfOf a : listActedOnBehalfOf) {
                                        String actOnBehOf = a.getDescription() + " Input Port Id: "
                                                + a.getInputPortidPort().getIdPort().toString() + " Output Port Id: "
                                                + a.getOutputPortidPort().getIdPort().toString();
                                        if (!abo.contains(actOnBehOf)) {
                                            abo.add(actOnBehOf);
                                        }
                                    }
                                }
                                if (used.isEmpty()) {
                                    used.add("Used Provenance Data not found for this service");
                                }
                                if (wib.isEmpty()) {
                                    wib.add("'Was Informed By' Provenance Data not found for this service");
                                }
                                if (waw.isEmpty()) {
                                    waw.add("'Was Associated With' Provenance Data not found for this service");
                                }
                                if (abo.isEmpty()) {
                                    abo.add("'Acted On Behalf Of' Provenance Data not found for this service");
                                }
//                                if(evolutionTaskOntology.isEmpty()){
//                                    evolutionTaskOntology.add("No inferences made for this service");
//                                }
                            }
                        }
                    }
                }
            }

            provenanceDetails.setUsed(used);
            provenanceDetails.setWasInformedBy(wib);
            provenanceDetails.setWasAssociatedWith(waw);
            provenanceDetails.setActedBehalfOf(abo);
            provenanceDetails.setWasEndedBy(web);
//            provenanceDetails.setEvolutionTaskOntology(evolutionTaskOntology);

        }
        provenanceDetails.setResPatF(result);
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the bioClient
     */
    public BioCatalogueClient getBioClient() {
        return bioClient;
    }

    /**
     * @param bioClient the bioClient to set
     */
    public void setBioClient(BioCatalogueClient bioClient) {
        this.bioClient = bioClient;
    }

    /**
     * @return the searchQuery
     */
    public String getSearchQuery() {
        return searchQuery;
    }

    /**
     * @param searchQuery the searchQuery to set
     */
    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return the results
     */
    public ArrayList<Result> getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    /**
     * @return the selectedResult
     */
    public Result getSelectedResult() {
        return selectedResult;
    }

    /**
     * @param selectedResult the selectedResult to set
     */
    public void setSelectedResult(Result selectedResult) {
        this.selectedResult = selectedResult;
    }

    /**
     * @return the patternResults
     */
    public ArrayList<ResultsPatternFormat> getPatternResults() {
        return patternResults;
    }

    /**
     * @param patternResults the patternResults to set
     */
    public void setPatternResults(ArrayList<ResultsPatternFormat> patternResults) {
        this.patternResults = patternResults;
    }

    /**
     * @return the myExpClient
     */
    public MyExperimentClient getMyExpClient() {
        return myExpClient;
    }

    /**
     * @param myExpClient the myExpClient to set
     */
    public void setMyExpClient(MyExperimentClient myExpClient) {
        this.myExpClient = myExpClient;
    }

    /**
     * @return the selectedComponents
     */
    public String[] getSelectedComponents() {
        return selectedComponents;
    }

    /**
     * @param selectedComponents the selectedComponents to set
     */
    public void setSelectedComponents(String[] selectedComponents) {
        this.selectedComponents = selectedComponents;
    }

    /**
     * @return the provenanceDetails
     */
    public ProvenanceDetails getProvenanceDetails() {
        return provenanceDetails;
    }

    /**
     * @param provenanceDetails the provenanceDetails to set
     */
    public void setProvenanceDetails(ProvenanceDetails provenanceDetails) {
        this.provenanceDetails = provenanceDetails;
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
     * @return the servicesPrime
     */
    public List<ServiceDescriptionVO> getServicesPrime() {
        return servicesPrime;
    }

    /**
     * @param servicesPrime the servicesPrime to set
     */
    public void setServicesPrime(List<ServiceDescriptionVO> servicesPrime) {
        this.servicesPrime = servicesPrime;
    }
}

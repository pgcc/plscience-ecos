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
import br.ufjf.pgcc.plscience.controller.WasAssociatedWithBean;
import br.ufjf.pgcc.plscience.dao.WorkflowDAO;
import br.ufjf.pgcc.plscience.model.WasAssociatedWith;
import br.ufjf.pgcc.plscience.model.Workflow;
import static com.lowagie.text.Annotation.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.json.simple.parser.ParseException;
import org.primefaces.component.datalist.DataList;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author phillipe
 */
@ManagedBean(name = "rpf")
@ViewScoped
public final class ResultsPatternFormat {

    private String name;
    private String repositoryName;
    private String serviceIdRepository;
    private String componentType;
    private String description;
    private String owner;
    private String ownerCountry;
    private String ownerCountryFlagImage;
    private String fileLocation;
    private StreamedContent file;
    private List<String> used;
    private List<String> wasAssociatedWith;
    private List<String> wasInformedBy;
    private List<String> wasEndedBy;
    private List<String> actedBehalfOf;

    public ResultsPatternFormat() throws MalformedURLException, IOException {
        createGenericServiceFile();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the repositoryName
     */
    public String getRepositoryName() {
        return repositoryName;
    }

    /**
     * @param repositoryName the repositoryName to set
     */
    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    /**
     * @return the componentType
     */
    public String getComponentType() {
        return componentType;
    }

    /**
     * @param componentType the componentType to set
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the fileLocation
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     *
     * @param fileLocation
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * @return the ownerCountry
     */
    public String getOwnerCountry() {
        return ownerCountry;
    }

    /**
     * @param ownerCountry the ownerCountry to set
     */
    public void setOwnerCountry(String ownerCountry) {
        this.ownerCountry = ownerCountry;
    }

    /**
     * @return the ownerCountryFlagImage
     */
    public String getOwnerCountryFlagImage() {
        return ownerCountryFlagImage;
    }

    /**
     * @param ownerCountryFlagImage the ownerCountryFlagImage to set
     */
    public void setOwnerCountryFlagImage(String ownerCountryFlagImage) {
        this.ownerCountryFlagImage = ownerCountryFlagImage;
    }

    /**
     * @return the file
     */
    public StreamedContent getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(StreamedContent file) {
        this.file = file;
    }

    /**
     * @return the serviceIdRepository
     */
    public String getServiceIdRepository() {
        return serviceIdRepository;
    }

    /**
     * @param serviceIdRepository the serviceIdRepository to set
     */
    public void setServiceIdRepository(String serviceIdRepository) {
        this.serviceIdRepository = serviceIdRepository;
    }

    public void viewDetails(String name, String type){

        if(type.contains("Service") || type.contains("service")){
            System.out.println("Details of "+name);
            String u = "Task geneID was used in workflow GeneExtraction - Task Id: 10 - Workflow Id: 10";
            String u2 = "Task geneID was used in workflow GeneExtraction2 - Task Id: 10 - Workflow Id: 11";
            String wIB = "Task geneID was successful for activity Nuclear Protein Database - Task Id: 10 - Activity Id: 4";
            String wAW = "Workflow GeneExtraction was attributed to experiment Gene Extraction Experiment - Workflow Id: 10 - Experiment Id: 8";
            String aBO = "Task geneID acted on behalf of task geneIDCon";
            
            List<String> us = new ArrayList<>();
            us.add(u);
            us.add(u2);
            
            List<String> wib = new ArrayList<>();
            wib.add(wIB);
            
            List<String> waw = new ArrayList<>();
            waw.add(wAW);
            
            List<String> abo = new ArrayList<>();
            abo.add(aBO);
            
            used = us;
            wasAssociatedWith = waw;
            wasInformedBy = wib;
            actedBehalfOf = abo;
            
        }
    }
    
    public void showDetails(String name, String type){
        System.out.println("Details of "+name);
        System.out.println("Workflow type "+type);
//        if(type.contains("workflow") || type.contains("Workflow")){
//            WorkflowDAO workf = new WorkflowDAO();
//            Workflow w = workf.buscarPorNome(name);
//            if(w != null){
//                Integer id = w.getIdWorkflow();
//               System.out.println("Id do workflow: "+ id);
//               WasAssociatedWithBean wAWB = new WasAssociatedWithBean();
//               listAssociatedExperiments =  wAWB.buscarAssociacoes(w.getIdWorkflow());
//               if(listAssociatedExperiments == null || listAssociatedExperiments.isEmpty()){
//                   System.out.println("Null or empty");
//               }             
//               if(listAssociatedExperiments.size() > 0){
//                   System.out.println("Maior que 0");
//                   if(!listAssociatedExperiments.get(0).getExperimentExperiment().getName().equals("")){
//                       System.out.println(listAssociatedExperiments.get(0).getExperimentExperiment().getName());
//                   }else{
//                       System.out.println("name é ");
//                   }
//               }
//            }
//        }
    }
    
    /**
     * It creates a service file to download
     *
     * @param serviceId
     * @throws MalformedURLException
     * @throws IOException
     * @throws BioCatalogueException
     */
    public void createServiceFile(String serviceId) throws MalformedURLException, IOException, BioCatalogueException, ParseException {
        if (serviceId != null) {
            BioCatalogueClient searchServiceDataById = new BioCatalogueClient();
            searchServiceDataById.setBaseUri("https://www.biocatalogue.org");
            ServiceData serviceData = searchServiceDataById.serviceData(serviceId);
            fileLocation = serviceData.getServiceVariants().getWsdlLocation();
            if (fileLocation != null && fileLocation.contains("http://")) {
                URL url = new URL(fileLocation);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int code = connection.getResponseCode();
                //System.out.println("Connection Code: "+code);
                if (code == 200) {//it checks that the page exists and does not return error
                    InputStream stream = url.openStream();
                    StreamedContent file = new DefaultStreamedContent(stream, "file/wsdl", "service" + serviceId + ".wsdl");
                    this.setFile(file);
                } else {
                    createGenericServiceFile();
                }
            } else {
                createGenericServiceFile();
            }

        } else {
            createGenericServiceFile();
        }
    }

    /**
     * Creates a generic file to download
     *
     * @throws IOException
     */
    public void createGenericServiceFile() throws IOException {
        File file = new File("serviceDocument.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write("File can not be generated");
        writer.flush();
        writer.close();
        FileInputStream fis = new FileInputStream(file);
        InputStream inpS = fis;
        StreamedContent fileCreated = new DefaultStreamedContent(inpS, "file/txt", "serviceDocument.txt");
        this.file = fileCreated;
    }

    public ResultsPatternFormat createFileMyExperiment(ResultsPatternFormat rpf) throws MalformedURLException, IOException {
        if (rpf.getFileLocation() != null && rpf.getFileLocation().contains("http://")) {
            URL url = new URL(rpf.getFileLocation());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            //System.out.println("Connection Code: "+code);
            if (code == 200) {//it checks that the page exists and does not return error
                InputStream stream = url.openStream();
                String s = rpf.getFileLocation();
                String fileExtension = s.substring(s.lastIndexOf(".") + 1, s.length());
                String fileName = s.substring(s.lastIndexOf("/") + 1, s.length());
                StreamedContent file = new DefaultStreamedContent(stream, fileExtension, fileName);
                rpf.setFile(file);
            } else {
                rpf.createGenericServiceFile();
            }
        }else {
            rpf.createGenericServiceFile();
        }
        return rpf;
    }

//    /**
//     * @return the listAssociatedExperiments
//     */
//    public List getListAssociatedExperiments() {
//        return listAssociatedExperiments;
//    }
//
//    /**
//     * @param listAssociatedExperiments the listAssociatedExperiments to set
//     */
//    public void setListAssociatedExperiments(List<WasAssociatedWith> listAssociatedExperiments) {
//        this.listAssociatedExperiments = listAssociatedExperiments;
//    }

    /**
     * @return the used
     */
    public List<String> getUsed() {
        return used;
    }

    /**
     * @param used the used to set
     */
    public void setUsed(List<String> used) {
        this.used = used;
    }

    /**
     * @return the wasAssociatedWith
     */
    public List<String> getWasAssociatedWith() {
        return wasAssociatedWith;
    }

    /**
     * @param wasAssociatedWith the wasAssociatedWith to set
     */
    public void setWasAssociatedWith(List<String> wasAssociatedWith) {
        this.wasAssociatedWith = wasAssociatedWith;
    }

    /**
     * @return the wasInformedBy
     */
    public List<String> getWasInformedBy() {
        return wasInformedBy;
    }

    /**
     * @param wasInformedBy the wasInformedBy to set
     */
    public void setWasInformedBy(List<String> wasInformedBy) {
        this.wasInformedBy = wasInformedBy;
    }

    /**
     * @return the wasEndedBy
     */
    public List<String> getWasEndedBy() {
        return wasEndedBy;
    }

    /**
     * @param wasEndedBy the wasEndedBy to set
     */
    public void setWasEndedBy(List<String> wasEndedBy) {
        this.wasEndedBy = wasEndedBy;
    }

    /**
     * @return the actedBehalfOf
     */
    public List<String> getActedBehalfOf() {
        return actedBehalfOf;
    }

    /**
     * @param actedBehalfOf the actedBehalfOf to set
     */
    public void setActedBehalfOf(List<String> actedBehalfOf) {
        this.actedBehalfOf = actedBehalfOf;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import br.ufjf.biocatalogue.core.BioCatalogueClient;
import br.ufjf.biocatalogue.exception.BioCatalogueException;
import br.ufjf.biocatalogue.model.ServiceData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.json.simple.parser.ParseException;
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
    private String createdAt;
    private String archivedAt;
    private String updatedAt;
    private String ownerCity;
    private String ownerCountry;
    private String ownerCountryFlagImage;
    private String fileLocation;
    private String licenseType;
    private String monitoringStatusLabel;
    private String monitoringStatusLastChecked;

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
    
    public String seachWSDLLocationBioCatalogue(String serviceId) throws BioCatalogueException, ParseException{
        if (serviceId != null) {
            BioCatalogueClient searchServiceDataById = new BioCatalogueClient();
            searchServiceDataById.setBaseUri("https://www.biocatalogue.org");
            ServiceData serviceData = searchServiceDataById.serviceData(serviceId);
            fileLocation = serviceData.getServiceVariants().getWsdlLocation();
        }
        return fileLocation;
    }
    
    public StreamedContent createComponentFile(ResultsPatternFormat rpf) throws IOException, MalformedURLException, BioCatalogueException, ParseException{
        StreamedContent f;
        if(rpf != null){
            if(rpf.getRepositoryName().toLowerCase().contains("catalogue")){
                f = createServiceFile(rpf.getServiceIdRepository());
            }
            else if(rpf.getRepositoryName().toLowerCase().contains("experiment")){
                f = createFileMyExperiment(rpf);
            }else{
                f = createGenericServiceFile();
            }
        }else{
            f = createGenericServiceFile();
        }
        return f;                
    }
    
    /**
     * It creates a service file to download
     *
     * @param serviceId
     * @return 
     * @throws MalformedURLException
     * @throws IOException
     * @throws BioCatalogueException
     * @throws org.json.simple.parser.ParseException
     */
    public StreamedContent createServiceFile(String serviceId) throws MalformedURLException, IOException, BioCatalogueException, ParseException {
        StreamedContent f;
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
                    f = new DefaultStreamedContent(stream, "file/wsdl", "service" + serviceId + ".wsdl");
                } else {
                    f = createGenericServiceFile();
                }
            } else {
                f = createGenericServiceFile();
            }
        } else {
            f = createGenericServiceFile();
        }
        return f;
    }

    /**
     * Creates a generic file to download
     *
     * @return a generic file
     * @throws IOException
     */
    public StreamedContent createGenericServiceFile() throws IOException {
        File file = new File("serviceDocument.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write("File can not be generated");
        writer.flush();
        writer.close();
        FileInputStream fis = new FileInputStream(file);
        InputStream inpS = fis;
        StreamedContent fileCreated = new DefaultStreamedContent(inpS, "file/txt", "serviceDocument.txt");
        return fileCreated;
    }

    public StreamedContent createFileMyExperiment(ResultsPatternFormat rpf) throws MalformedURLException, IOException {
        StreamedContent f;
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
                f = new DefaultStreamedContent(stream, fileExtension, fileName);
            } else {
                f = rpf.createGenericServiceFile();
            }
        }else {
            f = rpf.createGenericServiceFile();
        }
        return f;
    }

    /**
     * @return the ownerCity
     */
    public String getOwnerCity() {
        return ownerCity;
    }

    /**
     * @param ownerCity the ownerCity to set
     */
    public void setOwnerCity(String ownerCity) {
        this.ownerCity = ownerCity;
    }

    /**
     * @return the createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the licenseType
     */
    public String getLicenseType() {
        return licenseType;
    }

    /**
     * @param licenseType the licenseType to set
     */
    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    /**
     * @return the archivedAt
     */
    public String getArchivedAt() {
        return archivedAt;
    }

    /**
     * @param archivedAt the archivedAt to set
     */
    public void setArchivedAt(String archivedAt) {
        this.archivedAt = archivedAt;
    }

    /**
     * @return the monitoringStatusLabel
     */
    public String getMonitoringStatusLabel() {
        return monitoringStatusLabel;
    }

    /**
     * @param monitoringStatusLabel the monitoringStatusLabel to set
     */
    public void setMonitoringStatusLabel(String monitoringStatusLabel) {
        this.monitoringStatusLabel = monitoringStatusLabel;
    }

    /**
     * @return the monitoringStatusLastChecked
     */
    public String getMonitoringStatusLastChecked() {
        return monitoringStatusLastChecked;
    }

    /**
     * @param monitoringStatusLastChecked the monitoringStatusLastChecked to set
     */
    public void setMonitoringStatusLastChecked(String monitoringStatusLastChecked) {
        this.monitoringStatusLastChecked = monitoringStatusLastChecked;
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

}

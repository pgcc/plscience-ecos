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
    private String description;//comments in prime
    private String owner;//scientist name in prime
    private String createdAt;
    private String archivedAt;//publication data in prime
    private String updatedAt;
    private String ownerCity;
    private String ownerCountry;
    private String ownerCountryFlagImage;
    private String fileLocation;//address in prime
    private String licenseType;//license in prime
    private String monitoringStatusLabel;
    private String monitoringStatusLastChecked;

    //to servicesOnly
    private String inputs;
    private String outputs;
    private String serviceType;
    private String dependencies;

    //prime
    private String returnPrimeSin;
    private String returnPrimeSem;
    private String receptionPrimeSem;
    private String representationPrimeSem;
    private String functionalRequirementPrimeSem;
    private String nonFunctionalRequirementPrimePra;
    private String artifactPrimePra;
    private String domainPrimePra;
    private String purposePrimePra;
    private String providerPrimePra;
    private String restrictionPrimePra;
    private String hardwareCPUPrimePra;
    private String hardwareOperationalSystemPrimePra;
    private String hardwareRAMPrimePra;
    private String hardwareROMPrimePra;

    public ResultsPatternFormat() throws MalformedURLException, IOException {

        description = "";
        returnPrimeSin = "";
        returnPrimeSem = "";
        receptionPrimeSem = "";
        representationPrimeSem = "";
        functionalRequirementPrimeSem = "";
        nonFunctionalRequirementPrimePra = "";
        artifactPrimePra = "";
        domainPrimePra = "";
        purposePrimePra = "";
        providerPrimePra = "";
        restrictionPrimePra = "";
        hardwareCPUPrimePra = "";
        hardwareOperationalSystemPrimePra = "";
        hardwareRAMPrimePra = "";
        hardwareROMPrimePra = "";
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

    public void showDetails(String name, String type) {
        System.out.println("Details of " + name);
        System.out.println("Workflow type " + type);
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
//                   }
//               }
//            }
//        }
    }
    
    public String acquireWSDL(String serviceId,String repositoryName) throws BioCatalogueException, ParseException{
        if(repositoryName.toLowerCase().contains("bio")){
            return searchWSDLLocationBioCatalogue(serviceId);
        }
        return "";
    }
	


    public String searchWSDLLocationBioCatalogue(String serviceId) throws BioCatalogueException, ParseException {
        if (serviceId != null) {
            BioCatalogueClient searchServiceDataById = new BioCatalogueClient();
            searchServiceDataById.setBaseUri("https://www.biocatalogue.org");
            ServiceData serviceData = searchServiceDataById.serviceData(serviceId);
            fileLocation = serviceData.getServiceVariants().getWsdlLocation();
        }
        return fileLocation;
    }

    public StreamedContent createComponentFile(ResultsPatternFormat rpf) throws IOException, MalformedURLException, BioCatalogueException, ParseException {
        StreamedContent f;
        if (rpf != null) {
            if (rpf.getRepositoryName().toLowerCase().contains("catalogue")) {
                f = createServiceFile(rpf.getServiceIdRepository());
            } else if (rpf.getRepositoryName().toLowerCase().contains("experiment")) {
                f = createFileMyExperiment(rpf);
            } else {
                f = createGenericServiceFile();
            }
        } else {
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
        File file = new File("/home/phillipe/Documentos/serviceDocument.txt");
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
        } else {
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

    /**
     * @return the returnPrimeSin
     */
    public String getReturnPrimeSin() {
        return returnPrimeSin;
    }

    /**
     * @param returnPrimeSin the returnPrimeSin to set
     */
    public void setReturnPrimeSin(String returnPrimeSin) {
        this.returnPrimeSin = returnPrimeSin;
    }

    /**
     * @return the returnPrimeSem
     */
    public String getReturnPrimeSem() {
        return returnPrimeSem;
    }

    /**
     * @param returnPrimeSem the returnPrimeSem to set
     */
    public void setReturnPrimeSem(String returnPrimeSem) {
        this.returnPrimeSem = returnPrimeSem;
    }

    /**
     * @return the receptionPrimeSem
     */
    public String getReceptionPrimeSem() {
        return receptionPrimeSem;
    }

    /**
     * @param receptionPrimeSem the receptionPrimeSem to set
     */
    public void setReceptionPrimeSem(String receptionPrimeSem) {
        this.receptionPrimeSem = receptionPrimeSem;
    }

    /**
     * @return the representationPrimeSem
     */
    public String getRepresentationPrimeSem() {
        return representationPrimeSem;
    }

    /**
     * @param representationPrimeSem the representationPrimeSem to set
     */
    public void setRepresentationPrimeSem(String representationPrimeSem) {
        this.representationPrimeSem = representationPrimeSem;
    }

    /**
     * @return the functionalRequirementPrimeSem
     */
    public String getFunctionalRequirementPrimeSem() {
        return functionalRequirementPrimeSem;
    }

    /**
     * @param functionalRequirementPrimeSem the functionalRequirementPrimeSem to
     * set
     */
    public void setFunctionalRequirementPrimeSem(String functionalRequirementPrimeSem) {
        this.functionalRequirementPrimeSem = functionalRequirementPrimeSem;
    }

    /**
     * @return the nonFunctionalRequirementPrimePra
     */
    public String getNonFunctionalRequirementPrimePra() {
        return nonFunctionalRequirementPrimePra;
    }

    /**
     * @param nonFunctionalRequirementPrimePra the
     * nonFunctionalRequirementPrimePra to set
     */
    public void setNonFunctionalRequirementPrimePra(String nonFunctionalRequirementPrimePra) {
        this.nonFunctionalRequirementPrimePra = nonFunctionalRequirementPrimePra;
    }

    /**
     * @return the artifactPrimePra
     */
    public String getArtifactPrimePra() {
        return artifactPrimePra;
    }

    /**
     * @param artifactPrimePra the artifactPrimePra to set
     */
    public void setArtifactPrimePra(String artifactPrimePra) {
        this.artifactPrimePra = artifactPrimePra;
    }

    /**
     * @return the domainPrimePra
     */
    public String getDomainPrimePra() {
        return domainPrimePra;
    }

    /**
     * @param domainPrimePra the domainPrimePra to set
     */
    public void setDomainPrimePra(String domainPrimePra) {
        this.domainPrimePra = domainPrimePra;
    }

    /**
     * @return the providerPrimePra
     */
    public String getProviderPrimePra() {
        return providerPrimePra;
    }

    /**
     * @param providerPrimePra the providerPrimePra to set
     */
    public void setProviderPrimePra(String providerPrimePra) {
        this.providerPrimePra = providerPrimePra;
    }

    /**
     * @return the restrictionPrimePra
     */
    public String getRestrictionPrimePra() {
        return restrictionPrimePra;
    }

    /**
     * @param restrictionPrimePra the restrictionPrimePra to set
     */
    public void setRestrictionPrimePra(String restrictionPrimePra) {
        this.restrictionPrimePra = restrictionPrimePra;
    }

    /**
     * @return the hardwareCPUPrimePra
     */
    public String getHardwareCPUPrimePra() {
        return hardwareCPUPrimePra;
    }

    /**
     * @param hardwareCPUPrimePra the hardwareCPUPrimePra to set
     */
    public void setHardwareCPUPrimePra(String hardwareCPUPrimePra) {
        this.hardwareCPUPrimePra = hardwareCPUPrimePra;
    }

    /**
     * @return the hardwareOperationalSystemPrimePra
     */
    public String getHardwareOperationalSystemPrimePra() {
        return hardwareOperationalSystemPrimePra;
    }

    /**
     * @param hardwareOperationalSystemPrimePra the
     * hardwareOperationalSystemPrimePra to set
     */
    public void setHardwareOperationalSystemPrimePra(String hardwareOperationalSystemPrimePra) {
        this.hardwareOperationalSystemPrimePra = hardwareOperationalSystemPrimePra;
    }

    /**
     * @return the hardwareRAMPrimePra
     */
    public String getHardwareRAMPrimePra() {
        return hardwareRAMPrimePra;
    }

    /**
     * @param hardwareRAMPrimePra the hardwareRAMPrimePra to set
     */
    public void setHardwareRAMPrimePra(String hardwareRAMPrimePra) {
        this.hardwareRAMPrimePra = hardwareRAMPrimePra;
    }

    /**
     * @return the hardwareROMPrimePra
     */
    public String getHardwareROMPrimePra() {
        return hardwareROMPrimePra;
    }

    /**
     * @param hardwareROMPrimePra the hardwareROMPrimePra to set
     */
    public void setHardwareROMPrimePra(String hardwareROMPrimePra) {
        this.hardwareROMPrimePra = hardwareROMPrimePra;
    }

    /**
     * @return the purposePrimePra
     */
    public String getPurposePrimePra() {
        return purposePrimePra;
    }

    /**
     * @param purposePrimePra the purposePrimePra to set
     */
    public void setPurposePrimePra(String purposePrimePra) {
        this.purposePrimePra = purposePrimePra;
    }

    /**
     * @return the inputs
     */
    public String getInputs() {
        return inputs;
    }

    /**
     * @param inputs the inputs to set
     */
    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    /**
     * @return the serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @return the outputs
     */
    public String getOutputs() {
        return outputs;
    }

    /**
     * @param outputs the outputs to set
     */
    public void setOutputs(String outputs) {
        this.outputs = outputs;
    }

    /**
     * @return the dependencies
     */
    public String getDependencies() {
        return dependencies;
    }

    /**
     * @param dependencies the dependencies to set
     */
    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
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

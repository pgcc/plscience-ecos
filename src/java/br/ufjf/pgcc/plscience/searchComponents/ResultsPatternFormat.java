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
import static com.lowagie.text.Annotation.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.json.simple.parser.ParseException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author phillipe
 */
@ManagedBean()
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
        writer.write("Enter the text that you want to write");
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

}

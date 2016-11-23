/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

import br.ufjf.pgcc.plscience.serviceComposition.test.TaskOutput;
import br.ufjf.pgcc.plscience.serviceComposition.test.TasksCompositionData;
import br.ufjf.pgcc.plscience.serviceComposition.test.TaskInput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
/**
 *
 * @author phillipe
 */

@ManagedBean()
@ViewScoped
public class ServiceComposition implements Serializable{

    private List<UploadedFile> servicesFile;
    private String requestInpParameterNameForm;
    private String requestInpParameterTypeForm;
    private String requestOutParameterNameForm;
    private String requestOutParameterTypeForm;
    private String keywords;
    private ServiceRequest serviceRequest;
    private List<TasksCompositionData> tasksCompositionData;
    
    public ServiceComposition(){
        servicesFile = new ArrayList<>();
    }
    
    public void generateGraph(){
        
    }
    
    /**
     * Files Handler - Services OWL-S
     * @param event 
     */
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Files Succesful Uploaded!");
        FacesContext.getCurrentInstance().addMessage(null, message);
        if(event.getFile() != null)
            servicesFile.add(event.getFile());
        //testUpload();
    }
    
    /**
     * It just tests if the files was uploaded
     */
    public void testUpload(){
            System.out.println("Total de Arquivos: "+servicesFile.size());
        for(UploadedFile ue: servicesFile){
            System.out.println("Service name: "+ue.getFileName());
            System.out.println("Service content: "+ue.getContentType());
        }
    }

    /**
     * Search all semantic relevant services
     */
    public void searchRelevantServices(){
        //addParametersToServiceRequest();
        //readFilesToSearchTerms();
        listRelevantServices();
    }
    
    public void readFilesToSearchTerms(){
        
    }
    
    public void listRelevantServices(){
        sampleRelevantServicesList();//remover (apenas teste)
    }
    
    public void sampleRelevantServicesList(){
        tasksCompositionData = new ArrayList<>();
        
        //task1
        TasksCompositionData t1 = new TasksCompositionData();
        List<TaskInput> tinpt1 = new ArrayList<>();
        List<TaskOutput> toutt1 = new ArrayList<>();
        TaskInput i1 = new TaskInput();
        i1.setInputParameter("input");
        i1.setInputType("xsd:string");
        tinpt1.add(i1);
        TaskOutput o1 = new TaskOutput();
        o1.setOutputParameter("result");
        o1.setOutputType("xsd:string");
        toutt1.add(o1);

        t1.setTaskName("search.owl");
        t1.setTaskInputs(tinpt1);
        t1.setTaskOutputs(toutt1);
        tasksCompositionData.add(t1);
        
        //task 2
        TasksCompositionData t2 = new TasksCompositionData();
        List<TaskInput> tinpt2 = new ArrayList<>();
        List<TaskOutput> toutt2 = new ArrayList<>();
        TaskInput i2 = new TaskInput();
        i2.setInputParameter("input");
        i2.setInputType("xsd:string");
        tinpt2.add(i2);
        TaskOutput o2 = new TaskOutput();
        o2.setOutputParameter("result");
        o2.setOutputType("xsd:string");
        toutt2.add(o2);

        t2.setTaskName("s1_protein.owl");
        t2.setTaskInputs(tinpt2);
        t2.setTaskOutputs(toutt2);
        tasksCompositionData.add(t2);

        //task 3
        TasksCompositionData t3 = new TasksCompositionData();
        List<TaskInput> tinpt3 = new ArrayList<>();
        List<TaskOutput> toutt3 = new ArrayList<>();
        TaskInput i3 = new TaskInput();
        i3.setInputParameter("field");
        i3.setInputType("xsd:string");
        TaskInput i3_2 = new TaskInput();
        i3_2.setInputParameter("value");
        i3_2.setInputType("xsd:string");        
        tinpt3.add(i3);
        tinpt3.add(i3_2);
        TaskOutput o3 = new TaskOutput();
        o3.setOutputParameter("result");
        o3.setOutputType("xsd:psResult");
        toutt3.add(o3);

        t3.setTaskName("protein01.owl");      
        t3.setTaskInputs(tinpt3);
        t3.setTaskOutputs(toutt3);
        tasksCompositionData.add(t3);
    }
    
    public void addParametersToServiceRequest(){
        if(requestInpParameterNameForm != null && !requestInpParameterNameForm.equals("")){
            addInputParametersNameReq(requestInpParameterNameForm);
        }
        if(requestInpParameterTypeForm != null && !requestInpParameterTypeForm.equals("")){
            addInputParametersTypeReq(requestInpParameterTypeForm);
        }
        if(requestOutParameterNameForm != null && !requestOutParameterNameForm.equals("")){
            addOutputParametersNameReq(requestOutParameterNameForm);
        }
        if(requestOutParameterTypeForm != null && !requestOutParameterTypeForm.equals("")){
            addOutputParametersTypeReq(requestOutParameterTypeForm);
        }
        if(keywords != null && !keywords.equals("")){
            addKeywords(keywords);
        }
    }
    
    /**
     * it converts a string from the in a list of strings
     * @param string
     * @return 
     */
    public List<String> StringFormToList(String string){
        String[] input;
        List<String> listP = new ArrayList<>();
        if(string.contains(",")){
            input = string.split(",");
            listP.addAll(Arrays.asList(input));//adicionando os inputs na lista                    
        }else{
            listP.add(string);
        }
        return listP;
    }
    
    /**
     * add the input parameters name to the service request
     * @param inputParametersName 
     */
    public void addInputParametersNameReq(String inputParametersName){
        System.out.println("InpParName: "+inputParametersName);
        List<String> listP = StringFormToList(inputParametersName);
        serviceRequest.setInputParametersName(listP);
        
        if(listP == null){
            System.out.println("Lista eh null ");
        }else{
            System.out.println("Lista não eh null ");
        }
        
        for(String teste:serviceRequest.getInputParametersName()){
            System.out.println("Teste vale: "+teste);
        }
    }
    
    /**
     * add the input parameters type to the service request
     * @param inputParametersType 
     */
    public void addInputParametersTypeReq(String inputParametersType){
        List<String> listP = StringFormToList(inputParametersType);
        serviceRequest.setInputParametersType(listP);
    }
    
   /**
     * add the output parameters name to the service request
     * @param outputParametersName
     */
    public void addOutputParametersNameReq(String outputParametersName){
        List<String> listP = StringFormToList(outputParametersName);
        serviceRequest.setOutputParametersName(listP);
    }
    
    /**
     * add the output parameters type to the service request
     * @param outputParametersType 
     */
    public void addOutputParametersTypeReq(String outputParametersType){
        List<String> listP = StringFormToList(outputParametersType);
        serviceRequest.setOutputParametersType(listP);
    }    
    
    /**
     * add keywords in a list
     * @param keywords 
     */
    public void addKeywords(String keywords){
        List<String> listKey = StringFormToList(keywords);
        serviceRequest.setKeywords(listKey);
    }
    
    /**
     * @return the serviceRequest
     */
    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    /**
     * @param serviceRequest the serviceRequest to set
     */
    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    /**
     * @return the requestInpParameterNameForm
     */
    public String getRequestInpParameterNameForm() {
        return requestInpParameterNameForm;
    }

    /**
     * @param requestInpParameterNameForm the requestInpParameterNameForm to set
     */
    public void setRequestInpParameterNameForm(String requestInpParameterNameForm) {
        this.requestInpParameterNameForm = requestInpParameterNameForm;
    }

    /**
     * @return the requestInpParameterTypeForm
     */
    public String getRequestInpParameterTypeForm() {
        return requestInpParameterTypeForm;
    }

    /**
     * @param requestInpParameterTypeForm the requestInpParameterTypeForm to set
     */
    public void setRequestInpParameterTypeForm(String requestInpParameterTypeForm) {
        this.requestInpParameterTypeForm = requestInpParameterTypeForm;
    }

    /**
     * @return the requestOutParameterNameForm
     */
    public String getRequestOutParameterNameForm() {
        return requestOutParameterNameForm;
    }

    /**
     * @param requestOutParameterNameForm the requestOutParameterNameForm to set
     */
    public void setRequestOutParameterNameForm(String requestOutParameterNameForm) {
        this.requestOutParameterNameForm = requestOutParameterNameForm;
    }

    /**
     * @return the requestOutParameterTypeForm
     */
    public String getRequestOutParameterTypeForm() {
        return requestOutParameterTypeForm;
    }

    /**
     * @param requestOutParameterTypeForm the requestOutParameterTypeForm to set
     */
    public void setRequestOutParameterTypeForm(String requestOutParameterTypeForm) {
        this.requestOutParameterTypeForm = requestOutParameterTypeForm;
    }

    /**
     * @return the keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * @return the servicesFile
     */
    public List<UploadedFile> getServicesFile() {
        return servicesFile;
    }

    /**
     * @param servicesFile the servicesFile to set
     */
    public void setServicesFile(List<UploadedFile> servicesFile) {
        this.servicesFile = servicesFile;
    }

    /**
     * @return the tasksCompositionData
     */
    public List<TasksCompositionData> getTasksCompositionData() {
        return tasksCompositionData;
    }

    /**
     * @param tasksCompositionData the tasksCompositionData to set
     */
    public void setTasksCompositionData(List<TasksCompositionData> tasksCompositionData) {
        this.tasksCompositionData = tasksCompositionData;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition.test;

import com.predic8.schema.All;
import com.predic8.schema.Attribute;
import com.predic8.schema.ComplexContent;
import com.predic8.schema.ComplexType;
import com.predic8.schema.Element;
import com.predic8.schema.Extension;
import com.predic8.schema.Schema;
import com.predic8.schema.SchemaComponent;
import com.predic8.schema.SimpleType;
import com.predic8.wsdl.Binding;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Message;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Part;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.WSDLParser;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.Service;
import com.predic8.schema.Sequence;
import com.predic8.schema.SimpleContent;
import com.predic8.wstool.creator.RequestTemplateCreator;
import com.predic8.wstool.creator.SOARequestCreator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import groovy.xml.QName;
import groovy.xml.MarkupBuilder;

/**
 *
 * @author phillipe
 */
public class ProcessWSDLInformation {
    
    private String error = "";
    
    public WSDLRecover getWSDLInfo(String wsdlURL, String operationName, List<String> in, List<String> out){
        try{
            WSDLRecover wsdlRecover = recuperarServico(wsdlURL, operationName, in, out);
            
            return wsdlRecover;
        }catch (Exception e){
            System.out.println("Error in get WSDL Info");
            return null;
        }
    }
    
    private WSDLRecover recuperarServico(String wsdlURL, String operacaoNome, List<String> inputs, List<String> outputs){
        try{
            WSDLParser parser = new WSDLParser();
            Definitions wsdl = parser.parse(wsdlURL);
            
            List<WSDLRecoverParams> input = new ArrayList<>();
            List<WSDLRecoverParams> output = new ArrayList<>();
            
            if(inputs != null){
                for(String in : inputs){
                    if(!buscarWSDLInfo(wsdl, in, operacaoNome, input, true)){
                        return null;
                    }
                }
            }
            
            if(outputs != null){
                for(String out : outputs){
                    if(!buscarWSDLInfo(wsdl, out, operacaoNome, output, false)){
                        return null;
                    }
                }
            }            
            return recuperarInfoWSDL(input, output, wsdl);
        }catch(Exception e){
            this.error = "It wasn't possible to recovery service.\n\n" + e;
            System.out.println(this.error);
            return null;
        }
    }
    
    private boolean buscarWSDLInfo(Definitions wsdl, String parametros, String operacao, List<WSDLRecoverParams> lstWSDLRecoverParameters, boolean isInput){
        try{
            Message message = null;
            for(Operation op : wsdl.getOperations()){

                if(op.getName().equals(operacao)){
                    if(isInput){
                        message = op.getInput().getMessage();
                    }else{
                        message = op.getOutput().getMessage();
                    }
                    break;
                }
            }
            
            if(message != null){
           
                for(Part parts : message.getParts()){
                    Element element = wsdl.getElement(parts.getElement());
                    if(element != null){                            
                        if(partElementSearch(wsdl, element, parametros, lstWSDLRecoverParameters)){
                            break;
                        }
                    }
                }                            
            }
        
            return true;
        }catch(Exception e){
            this.error = "It wasn't possible to get WSDL informations.\n\n" + e;
            System.out.println(this.error);
            return false;
        }
    }
    
    private WSDLRecover recuperarInfoWSDL(List<WSDLRecoverParams> inputs, List<WSDLRecoverParams> outputs, Definitions wsdl){
        try{
            WSDLRecover wsdlRecover = new WSDLRecover();
            wsdlRecover.setInputs(inputs);
            wsdlRecover.setOutputs(outputs);
            wsdlRecover.setTargetNameSpace(wsdl.getTargetNamespace());
            
            for(PortType portType : wsdl.getPortTypes()){
                
                WSDLRecoverPortType wsdlRecoverPortType = new WSDLRecoverPortType();
                wsdlRecoverPortType.setName(portType.getName());
                
                wsdlRecover.getPortType().add(wsdlRecoverPortType);
                
            }
            
            for(Service service : wsdl.getServices()){
            
                WSDLRecoverService wsdlRecoverService = new WSDLRecoverService();
                wsdlRecoverService.setServiceName(service.getName());
                
                for(Port port : service.getPorts()){
                                        
                    WSDLRecoverPort wsdlRecoverPort = new WSDLRecoverPort();
                    wsdlRecoverPort.setAdress(port.getAddress().getLocation());
                    wsdlRecoverPort.setPortName(port.getName());
                    
                    Binding binding = port.getBinding();
                    if(binding != null){
                        
                        WSDLRecoverBinding wsdlRecoverBinding = new WSDLRecoverBinding();
                        wsdlRecoverBinding.setBindingName(binding.getName());
                        
                        wsdlRecoverPort.setBinding(wsdlRecoverBinding);
                    }
                    
                    wsdlRecoverService.getPorts().add(wsdlRecoverPort);
                }
                
                wsdlRecover.getServices().add(wsdlRecoverService);
            }
            
            return wsdlRecover;
        }catch(Exception e){
            this.error = "It wasn't possible to recovery WSDL informations.\n\n" + e;
            System.out.println(this.error);
            return null;
        }
    }
    
    private void createElement(Definitions wsdl, Element element, List<WSDLRecoverParams> lstWSDLRecoverParameters, String paramName){
        try{
            if(element.getEmbeddedType() == null){
                WSDLRecoverParams wsdlParams = new WSDLRecoverParams();
                wsdlParams.setName(paramName);
                wsdlParams.setNameSpaceURI(element.getType().getNamespaceURI());
                wsdlParams.setLocalPart(element.getType().getLocalPart());
                wsdlParams.setComplexType(wsdlParams.getNameSpaceURI().contains("XMLSchema") ? false : true);

                lstWSDLRecoverParameters.add(wsdlParams);

                if(wsdlParams.isComplexType()){
                    findOutComplexType(wsdlParams, wsdl);
                }else{
                    if(element.getMinOccurs() != null){
                        int minOccurs = 0;
                        try{
                            minOccurs = Integer.parseInt(element.getMinOccurs());
                        }catch(Exception e){}
                        
                        wsdlParams.setMandatory(((minOccurs == 0) ? false : true));
                    }
                }
            }else{
                WSDLRecoverParams wsdlParams = new WSDLRecoverParams();
                wsdlParams.setName(paramName);
                wsdlParams.setNameSpaceURI(wsdl.getTargetNamespace());
                wsdlParams.setLocalPart(element.getName());
                wsdlParams.setComplexType(true);

                lstWSDLRecoverParameters.add(wsdlParams);

                findOutComplexType(wsdlParams, wsdl);
            } 
        }catch(Exception e){
            this.error = "It wasn't possible to create the element.\n\n" + e;
            System.out.println(this.error);
        }
    }
    
    private void findOutComplexType(WSDLRecoverParams wsdlRecoveryParams, Definitions wsdl){
        try{               
            for(Schema schemas : wsdl.getTypes().getSchemas()){

                if(!schemas.getImports().isEmpty()){
                    Schema schema = schemas.getImportedSchema(wsdlRecoveryParams.getNameSpaceURI());
                    if(schema != null){
                        Element element = schema.getElement(wsdlRecoveryParams.getLocalPart());

                        if(element != null){
                            checkElementComplexity(wsdl, wsdlRecoveryParams, element, schema);
                        }else{
                            boolean achou = false;
                            for(Element el : schema.getElements()){
                                if(el.getType() != null){
                                    if(wsdlRecoveryParams.getLocalPart().equals(el.getType().getLocalPart())){
                                        checkElementComplexity(wsdl, wsdlRecoveryParams, el, schema);       
                                        achou = true;
                                        break;
                                    }
                                }
                            }
                            
                            if(!achou){
                                for(SimpleType simpleType : schema.getSimpleTypes()){
                                    if(simpleType.getName().equals(wsdlRecoveryParams.getName())){                                        
                                        wsdlRecoveryParams.setComplexType(false);
                                        wsdlRecoveryParams.setLocalPart(simpleType.getRestriction().getBase().getLocalPart());
                                        wsdlRecoveryParams.setNameSpaceURI(simpleType.getRestriction().getBase().getNamespaceURI());
                                        achou = true;
                                        break;
                                    }
                                }                                       

                                if(!achou){
                                    ComplexType complexType = (ComplexType) schema.getComplexType(wsdlRecoveryParams.getLocalPart());
                                    if(complexType != null){
                                        findOutSimpleType(wsdl.getTypes().getSchemas(), wsdlRecoveryParams, wsdl, complexType);
                                    }          
                                }
                            }
                        }
                        
                        break;
                    }
                }else{
                    Element element = schemas.getElement(wsdlRecoveryParams.getLocalPart());
                    if(element != null){
                        checkElementComplexity(wsdl, wsdlRecoveryParams, element, schemas);
                    }else{
                        boolean achou = false;
                        
                        for(SimpleType simpleType : schemas.getSimpleTypes()){
                            if(simpleType.getName().equals(wsdlRecoveryParams.getName())){                                        
                                wsdlRecoveryParams.setComplexType(false);
                                wsdlRecoveryParams.setLocalPart(simpleType.getRestriction().getBase().getLocalPart());
                                wsdlRecoveryParams.setNameSpaceURI(simpleType.getRestriction().getBase().getNamespaceURI());
                                achou = true;
                                break;
                            }
                        }                                       

                        if(!achou){
                            ComplexType complexType = (ComplexType) schemas.getComplexType(wsdlRecoveryParams.getLocalPart());
                            if(complexType != null){
                                findOutSimpleType(wsdl.getTypes().getSchemas(), wsdlRecoveryParams, wsdl, complexType);
                            }          
                        }
                    }
                }
            }
        }catch(Exception e){
            this.error = "It wasn't possible to find out complex types in WSDL.\n\n" + e;
            System.out.println(this.error);
        }
    }    
    
    private void findOutSimpleType(List<Schema> schema, WSDLRecoverParams wsdlRecoveryParams, Definitions wsdl, ComplexType complexType){
        try{
            
            if(complexType.getModel() instanceof Sequence){
                
                Sequence sequence = (Sequence) complexType.getModel();
                
                if(sequence.getParticles().isEmpty()){
                    for(Attribute atribute : complexType.getAttributes()){
                        Element element = new Element();
                        element.setType(atribute.getType());
                        element.setName(atribute.getName());

                        if(element != null){                                                        
                            createElement(wsdl, element, wsdlRecoveryParams.getSubParams(), element.getName());
                        }
                    }
                }else{                
                    for(SchemaComponent subParams : sequence.getParticles()){

                        Element element = (Element) subParams;

                        if(element != null){                                                        
                            createElement(wsdl, element, wsdlRecoveryParams.getSubParams(), element.getName());
                        }        
                    }
                }
            }else if(complexType.getModel() instanceof All){
                All all = (All) complexType.getModel();
                
                for(Element element : all.getElements()){
                    if(element != null){     
                        createElement(wsdl, element, wsdlRecoveryParams.getSubParams(), element.getName());                      
                    }
                }
            }else if(complexType.getModel() instanceof ComplexContent){
                ComplexContent complexContent = (ComplexContent) complexType.getModel();
                
                if(complexContent.getDerivation() instanceof Extension){
                    checkExtension(schema, wsdlRecoveryParams, wsdl, (Extension) complexContent.getDerivation(), null);
                }
            }else if(complexType.getModel() instanceof SimpleContent){
                SimpleContent simpleContent = (SimpleContent) complexType.getModel();
                
                if(simpleContent.getDerivation() instanceof Extension){
                    checkExtension(schema, wsdlRecoveryParams, wsdl, (Extension) simpleContent.getDerivation(), null);
                }
            }
        }catch(Exception e){
            this.error = "It wasn't possible to find out simple types in WSDL.\n\n" + e;
            System.out.println(this.error);
        }
    }    
    
    private void checkExtension(List<Schema> schemas, WSDLRecoverParams wsdlRecoveryParams, Definitions wsdl, Extension extension, QName parent){
        try{
            if(extension.getModel() == null){
                
                for(Attribute atribute : extension.getAttributes()){
                    Element element = new Element();
                    element.setType(atribute.getType());
                    element.setName(atribute.getName());
                    
                    if(element != null){                                                        
                        createElement(wsdl, element, wsdlRecoveryParams.getSubParams(), element.getName());
                    }
                }
                
                return;
            }else if(extension.getModel() instanceof Sequence){
                Sequence sequence = (Sequence) extension.getModel();
                
                for(SchemaComponent subParams : sequence.getParticles()){
                    Element element = (Element) subParams;
                    
                    if(element != null){                                                        
                        createElement(wsdl, element, wsdlRecoveryParams.getSubParams(), element.getName());
                    }
                }
            }
            
            wsdlRecoveryParams.setLocalPart(extension.getBase().getLocalPart());
            wsdlRecoveryParams.setNameSpaceURI(extension.getBase().getNamespaceURI());
            
            findOutComplexType(wsdlRecoveryParams, wsdl);
        }catch(Exception e){
            this.error = "It wasn't possible to check extension on WSDL.\n\n" + e;
            System.out.println(this.error);
        }
    }
    
    private void checkElementComplexity(Definitions wsdl, WSDLRecoverParams wsdlRecoveryParams, Element element, Schema schemas){
        try{
            if(element.getEmbeddedType() != null){
                if(element.getEmbeddedType() instanceof ComplexType){
                    ComplexType complexType = (ComplexType) element.getEmbeddedType();

                    findOutSimpleType(wsdl.getTypes().getSchemas(), wsdlRecoveryParams, wsdl, complexType);
                }else{
                    SimpleType simpleType = (SimpleType) element.getEmbeddedType();
                }
            }else{
                if(wsdlRecoveryParams.getLocalPart().equals(element.getName())){
                    ComplexType complexType = schemas.getComplexType(wsdlRecoveryParams.getLocalPart());

                    findOutSimpleType(wsdl.getTypes().getSchemas(), wsdlRecoveryParams, wsdl, complexType);
                }else if(wsdlRecoveryParams.getLocalPart().equals(element.getType().getLocalPart())){
                    ComplexType complexType = schemas.getComplexType(wsdlRecoveryParams.getLocalPart());

                    findOutSimpleType(wsdl.getTypes().getSchemas(), wsdlRecoveryParams, wsdl, complexType);
                }
            } 
        }catch(Exception e){
            this.error = "It wasn't possible to check element complexity.\n\n" + e;
            System.out.println(this.error);
        }
    }
    
    private boolean partElementSearch(Definitions wsdl, Element elementParam, String parametros, List<WSDLRecoverParams> lstWSDLRecoverParameters){
        
        if(elementParam.getEmbeddedType() != null){
            
            ComplexType complexType = (ComplexType) elementParam.getEmbeddedType();
         
            if(complexType.getModel() instanceof Sequence){
                
                Sequence sequence = (Sequence) complexType.getModel();

                if(sequence.getParticles().isEmpty()){
                    for(Attribute atribute : complexType.getAttributes()){
                        Element element = new Element();
                        element.setType(atribute.getType());
                        element.setName(atribute.getName());

                        if(element != null){                                                        
                            if(element.getName().equals(parametros)){
                                createElement(wsdl, element, lstWSDLRecoverParameters, element.getName());
                                return true;
                            }   
                        }
                    }
                }else{                
                    for(SchemaComponent subParams : sequence.getParticles()){

                        Element element = (Element) subParams;

                        if(element != null){      
                            if(element.getName().equals(parametros)){
                                createElement(wsdl, element, lstWSDLRecoverParameters, element.getName());
                                return true;
                            }                            
                        }        
                    }
                }
            }else if(complexType.getModel() instanceof All){
                All all = (All) complexType.getModel();

                for(Element element : all.getElements()){
                    if(element != null){     
                        if(element.getName().equals(parametros)){
                            createElement(wsdl, element, lstWSDLRecoverParameters, element.getName());
                            return true;
                        }                       
                    }
                }
            }
        }else{
            createElement(wsdl, elementParam, lstWSDLRecoverParameters, parametros);
            return true;
        }
        
        return false;
    }
    
    public String generateEnvelopeSOAP(WSDLRecover wsdlRecover, String wsdlURL, String operationName){
        try{
            WSDLParser parser = new WSDLParser();     
            Definitions wsdl = parser.parse(wsdlURL);

            StringWriter writer = new StringWriter();
            
            SOARequestCreator creator = new SOARequestCreator(wsdl, new RequestTemplateCreator(), new MarkupBuilder(writer));

            for(WSDLRecoverPortType portType : wsdlRecover.getPortType()){
            
                for(WSDLRecoverService wsdlService : wsdlRecover.getServices()){
                        
                    for(WSDLRecoverPort port : wsdlService.getPorts()){
                        creator.createRequest(portType.getName(), this.clearNumberInOperation(operationName), port.getBinding().getBindingName());
                        return writer.toString();
                    }
                }
                
            }
            
            return "";
        }catch(Exception e){
            this.error = "It wasn't possible to create SOAP envelop.\n\n" + e;
            return "";
        }
    }
    
    public static String clearNumberInOperation(String operation){
        
        if(operation.lastIndexOf("_") != -1){
            operation = operation.substring(0, operation.lastIndexOf("_"));
        }
        
        return operation;
    }
    
    public SOAPMessage createSOAPMessage(String envelopMessage){
        try{
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            
            byte[] reqBytes = envelopMessage.getBytes();
            ByteArrayInputStream bis = new ByteArrayInputStream(reqBytes);
            StreamSource ss = new StreamSource(bis);
            soapPart.setContent(ss);
            
            soapMessage.saveChanges();

            /*TODO Remove Print the request message */
            //System.out.print("Request SOAP Message = ");
            soapMessage.writeTo(System.out);
            //System.out.println();

            return soapMessage;
        }catch(IOException | SOAPException e){
            this.error = "It wasn't possible generating SOAPMessage.\n\n" + e;
            System.out.println(this.error);
            return null;
        }
    }
    
    public boolean soapRequest(String wsdlURL, SOAPMessage soapMessage, OWLSOperation operation){
        
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            
            SOAPMessage soapResponse = soapConnection.call(soapMessage, wsdlURL);

            // Process the SOAP Response
            Document envelop = soapResponseProcess(soapResponse);
            soapConnection.close();
            
            if(envelop == null){
               return false;
            }
            
            for(OWLSParam output : operation.getOutputs()){
                
                org.w3c.dom.Element docElement = envelop.getDocumentElement();                

                if(docElement != null){
                
                    NodeList outputElement = docElement.getElementsByTagName(output.getParamName());

                    if(outputElement != null){

                        String values = "";
                        for (int i = 0; i < outputElement.getLength(); i++) {
                            //if(output.getParamType().contains("base")){
                            //    values = values + "§" + EScienceNetUtils.decodeBase64(outputElement.item(i).getTextContent());
                            //}else{
                                values = values + "§" + outputElement.item(i).getTextContent();
                            //}                            
                        }
                        
                        if(values.contains("§")){
                            values = values.substring(1, values.length());
                        }else{
                            NodeList fault = docElement.getElementsByTagName("soapenv:Fault");
                            if(fault != null){

                                for (int i = 0; i < fault.getLength(); i++) {
                                    this.error = this.error + "\n\n" + fault.item(i).getTextContent();
                                }
                                
                                if(this.error.equals("")){
                                    fault = docElement.getElementsByTagName("soap:Fault");
                                    if(fault != null){

                                        for (int i = 0; i < fault.getLength(); i++) {
                                            this.error = this.error + "\n\n" + fault.item(i).getTextContent();
                                        }
                                    }
                                }
                            }
                            
                            return false;
                        }
                        
                        output.setValueReturned(values);
                    }
                }
            }
            
            return true;
        } catch (Exception e) {
            this.error = "It wasn't possible generate the SOAP request.\n\n" + e;
            System.out.println(this.error);
            return false;
        }
    }
    
    private Document soapResponseProcess(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        
        File file = createATempFile();
        
        StreamResult result = new StreamResult(file);
        StreamResult result2 = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
        transformer.transform(sourceContent, result2);
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        
        return doc;
    }
    
        public File createATempFile(){
        try{
            String path = File.separator + "home" + File.separator + "phillipe" + File.separator + "Documentos" + File.separator;
            String TEMP_FILE = "TemporaryFile.tmp";
            
            File file = new File(path + File.separator + TEMP_FILE);;
            
            if(file.exists()){
                file.delete();
                file.createNewFile();
            }
            
            return file;
        }catch(Exception e){
            e.printStackTrace();
            return null;            
        }
    }


    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.mindswap.utils.QNameProvider;
import org.mindswap.wsdl.WSDLOperation;
import org.mindswap.wsdl.WSDLParameter;
import org.mindswap.wsdl.WSDLTranslator;

/**
 *
 * @author phillipe
 */
public class ServiceManager {
    
        public boolean OWLSGenerator(String serviceName, WSDLOperation wsdlOperation, 
        String serviceDescription, String logicalURI, JTable inputParams, JTable outputParams,
        QNameProvider qNames){
        try{            
            String name = serviceName.replaceAll(" ", "_");
            
            WSDLTranslator translator = new WSDLTranslator(wsdlOperation, logicalURI, name);

            translator.setServiceName(serviceName);
            translator.setTextDescription(serviceDescription);

            Set usedNames = new HashSet();
            if (! generateOWLsInputs(inputParams, translator, wsdlOperation, usedNames, qNames)){
                return false;
            }
            
            if (! generateOWLsOutputs(outputParams, translator, wsdlOperation, usedNames, qNames)){
                return false;
            }

            String owls = File.separatorChar + serviceName + ".owl";
            
            File owlsFile = new File(owls);
            if (owlsFile.exists()){
                owlsFile.delete();
                owlsFile.createNewFile();
            }
            
            try (FileOutputStream fos = new FileOutputStream(owlsFile)) {
                translator.writeOWLS(fos);
            }            

            return true;
        }catch(Exception e){            
            JOptionPane.showMessageDialog(null, e.getMessage(), ".: e-ScienceNet :.", JOptionPane.ERROR_MESSAGE, 
                                (new javax.swing.ImageIcon(getClass().getResource("/images/error.png"))));
            return false;
        }            
    }
        
    private boolean generateOWLsInputs(JTable inputParams, WSDLTranslator translator, WSDLOperation wsdlOperation, Set usedNames, QNameProvider qNames){
        
        TableModel inputs = inputParams.getModel();
        for(int i = 0; i < inputs.getRowCount(); i++) {
            WSDLParameter param = wsdlOperation.getInput(i);
            String paramName = (String) inputs.getValueAt(i, 2);
            String paramType = (String) inputs.getValueAt(i, 3);            

            String prefix = paramName;
            for( int count = 1; usedNames.contains( paramName ); count++ )
                paramName = prefix + count;
            
            usedNames.add( paramName );
            
            URI paramTypeURI;
            try {
                paramType = qNames.longForm(paramType);
                paramTypeURI = new URI(paramType);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), ".: e-ScienceNet :.", JOptionPane.ERROR_MESSAGE, 
                                (new javax.swing.ImageIcon(getClass().getResource("/images/error.png"))));
                return false;
            }

            translator.addInput(param, paramName, paramTypeURI, null);
        }
        
        return true;
    }
    
    private boolean generateOWLsOutputs(JTable outputParams, WSDLTranslator translator, WSDLOperation wsdlOperation, Set usedNames, QNameProvider qNames){
        
        TableModel outputs = outputParams.getModel();
        
        for(int i = 0; i < outputs.getRowCount(); i++) {
            WSDLParameter param = wsdlOperation.getOutput(i);
            String paramName = (String) outputs.getValueAt(i, 2);
            String paramType = (String) outputs.getValueAt(i, 3);

            String prefix = paramName;
            for( int count = 1; usedNames.contains( paramName ); count++ )
                paramName = prefix + count;
            
            usedNames.add( paramName );

            URI paramTypeURI;
            try {
                paramType = qNames.longForm(paramType);
                paramTypeURI = new URI(paramType);
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), ".: e-ScienceNet :.", JOptionPane.ERROR_MESSAGE, 
                                (new javax.swing.ImageIcon(getClass().getResource("/images/error.png"))));
                return false;
            }
            
            translator.addOutput(param, paramName, paramTypeURI, null);
        }
        
        return true;
    }
        

    
}

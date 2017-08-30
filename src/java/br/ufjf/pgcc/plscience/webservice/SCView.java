/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.webservice;

import br.ufjf.pgcc.plscience.serviceComposition.SCViewInterface;
import br.ufjf.pgcc.plscience.serviceComposition.ServiceComposition;
import br.ufjf.pgcc.plscience.socialNetworkAnalysis.GraphSN;
import br.ufjf.pgcc.plscience.socialNetworkAnalysis.SocialNetworkGraph;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author phillipe
 */
@WebService(serviceName = "SCView")
public class SCView implements SCViewInterface{

    @WebMethod(operationName = "generateGraphServiceComposition")
    @Override
    public void generateGraphServiceComposition(String taskName) {
        try {
            ServiceComposition c = new ServiceComposition();
            c.generateGraph(taskName);
        } catch (IOException ex) {
            Logger.getLogger(SCView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SCView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SCView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod(operationName = "generateSocialNetworkGraph")
    @Override
    public void generateGraphSocialNetwork(GraphSN graph) {
        SocialNetworkGraph sn = new SocialNetworkGraph();
        sn.linkuriousScriptGenerator(graph);
    }
}

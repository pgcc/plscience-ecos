/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.pgcc.plscience.bean.collaborationServices;

import br.ufjf.pgcc.plscience.integration.ConceptXML;
import br.ufjf.pgcc.plscience.integration.ConceptXMLDAO;
import br.ufjf.pgcc.plscience.integration.InteroperabilityStructXML;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Guilherme Martins
 */
@ManagedBean()
@SessionScoped
public class WorkspaceAlignment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private InteroperabilityStructXML interoperabilityStructXML;
        
    public void loadAlignment(){
        try {
            interoperabilityStructXML.setConcepts(new ConceptXMLDAO().getConceptXMLByIdStructXML(interoperabilityStructXML.getIdStructXml()));            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Loaded with success!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error!"));
        }  
    }

    /**
     * @return the interoperabilityStructXML
     */
    public InteroperabilityStructXML getInteroperabilityStructXML() {
        return interoperabilityStructXML;
    }

    /**
     * @param interoperabilityStructXML the interoperabilityStructXML to set
     */
    public void setInteroperabilityStructXML(InteroperabilityStructXML interoperabilityStructXML) {
        this.interoperabilityStructXML = interoperabilityStructXML;
    }
  
      
}

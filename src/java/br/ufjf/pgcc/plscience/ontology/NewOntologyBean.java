/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.ontology;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;

/**
 *
 * @author tassio
 */
@ManagedBean(name = "newOntologyBean")
@ViewScoped
public class NewOntologyBean implements Serializable {

    private List<String> response;
    public String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void load() throws IOException {
        try {
            OntologyController.getInstance();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    public List<String> getEvolutionTo(String workflow) {
        response = new InferenceLayer().jenaGetEvolutionTo(workflow.replace(" ", "."));

        return response;
    }

    public List<String> getEvolutionOf(String workflow) {
        response = new InferenceLayer().jenaGetEvolutionOf(workflow.replace(" ", "."));
        return response;
    }

    public List<String> buscarporsparql(String sql) {
        response = new InferenceLayer().sparqlGetResult(sql);
        this.sql = this.sql.concat("\n \n \n");
        for (String string : response) {
            this.sql = this.sql.concat(string);
            this.sql = this.sql.concat("\n");
        }
        this.setSql(this.sql);
        RequestContext.getCurrentInstance().update("formPrincipal:textarea");
        return response;
    }

    public List<String> allInformation(String individual) {
        response = new InferenceLayer().sparqlGetPropertiesByIndividualInf(individual.replace(" ", "."));
        return response;
    }

    public List<String> buscarsimilar(String experiment) {
        response = new InferenceLayer().jenaGetIsSimilarInf(experiment.replace(" ", "."));
        return response;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";

        // pdf.add(Image.getInstance(logo));
    }
}

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

/**
 *
 * @author tassio
 */
@ManagedBean(name = "ontologyBean")
@ViewScoped
public class OntologyBean implements Serializable {

    private List<String> ontologys;
    private String sql;
    
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
    
    public void load() throws IOException {
        try {
            OntologyDAO ontologyDAO = new OntologyDAO();
            ontologyDAO.loadDAO();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    public List<String> getOntology() {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys = ontologyDAO.buscartodos();

        return ontologys;
    }

    public List<String> getEvolutionTo(String workflow) {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys = ontologyDAO.buscarEvolutionTo(workflow);
        return ontologys;
    }

    public List<String> getEvolutionOf(String workflow) {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys = ontologyDAO.buscarEvolutionOf(workflow);
        return ontologys;
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

    public List<String> buscarporsparql(String sql) {
        System.out.println("sql:" + sql);
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys = ontologyDAO.buscarSPARQL(sql);

        return ontologys;
    }
    
     public List<String> buscarsimilar(int workflow) {
        OntologyDAO ontologyDAO = new OntologyDAO();
        ontologys = ontologyDAO.Similar(workflow);

        return ontologys;
    }

     
    public List<String> getIsSimilar(String experiment) {
        ontologys = new InferenceLayer().jenaGetIsSimilarInf(experiment.replace(" ", "."));
        return ontologys;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.WorkflowhasExperimentDAO;
import br.ufjf.pgcc.plscience.model.WorkflowhasExperiment;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
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
@ManagedBean(name = "workflowhasExperimentBean")
@ViewScoped
public class WorkflowhasExperimentBean {

    WorkflowhasExperiment workflowhasExperiment = new WorkflowhasExperiment();

    List workflowhasExperiments = new ArrayList();

    //construtor
    public WorkflowhasExperimentBean() {
        workflowhasExperiments = new WorkflowhasExperimentDAO().buscarTodas();
        workflowhasExperiment = new WorkflowhasExperiment();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new WorkflowhasExperimentDAO().persistir(workflowhasExperiment);
        workflowhasExperiments = new WorkflowhasExperimentDAO().buscarTodas();
        workflowhasExperiment = new WorkflowhasExperiment();
    }

    public void exclude(ActionEvent actionEvent) {
        new WorkflowhasExperimentDAO().remover(workflowhasExperiment);
        workflowhasExperiments = new WorkflowhasExperimentDAO().buscarTodas();
        workflowhasExperiment = new WorkflowhasExperiment();
    }
    
    //getters and setters
    public WorkflowhasExperiment getWorkflowhasExperiment() {
        return workflowhasExperiment;
    }

    public void setWorkflowhasExperiment(WorkflowhasExperiment workflowhasExperiment) {
        this.workflowhasExperiment = workflowhasExperiment;
    }

    public List getWorkflowhasExperiments() {
        return workflowhasExperiments;
    }

    public void setWorkflowhasExperiments(List workflowhasExperiments) {
        this.workflowhasExperiments = workflowhasExperiments;
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

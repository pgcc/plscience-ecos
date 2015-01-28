/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.WorkflowDAO;
import br.ufjf.pgcc.plscience.model.Workflow;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author tassio
 */
@ManagedBean(name = "workflowBean")
@ViewScoped
public class WorkflowBean {

    Workflow workflow = new Workflow();

    List workflows = new ArrayList();

    private byte[] arquivo;

    //construtor
    public WorkflowBean() {
        workflows = new WorkflowDAO().buscarTodas();
        workflow = new Workflow();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new WorkflowDAO().persistir(workflow);
        workflows = new WorkflowDAO().buscarTodas();
        workflow = new Workflow();
    }

    public void exclude(ActionEvent actionEvent) {
        new WorkflowDAO().remover(workflow);
        workflows = new WorkflowDAO().buscarTodas();
        workflow = new Workflow();
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public List getWorkflows() {
        return workflows;
    }

    public void setWorkflows(List workflows) {
        this.workflows = workflows;
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

    public void doUpload(FileUploadEvent fileUploadEvent) throws FileNotFoundException, IOException {
        UploadedFile uploadedFile = fileUploadEvent.getFile();
        String fileNameUploaded = uploadedFile.getFileName();
        long fileSizeUploaded = uploadedFile.getSize();
        String infoAboutFile = "<br/> File Received: <b>" + fileNameUploaded + "</b><br/>" + "File Size: <b>" + fileSizeUploaded + "</b>";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
        //String caminho = scontext.getRealPath("/home/tassio/");
        arquivo = fileUploadEvent.getFile().getContents();
        //FileOutputStream fos = new FileOutputStream(caminho + fileNameUploaded);
        FileOutputStream fos = new FileOutputStream("/home/tassio/" + fileNameUploaded);
        //fos.write(arquivo);
        fos.close();
        facesContext.addMessage(null, new FacesMessage("Success", infoAboutFile));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.AgenthasResearchGroupDAO;
import br.ufjf.pgcc.plscience.model.AgenthasResearchGroup;
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
@ManagedBean(name = "agenthasResearchGroupBean")
@ViewScoped
public class AgenthasResearchGroupBean {

    AgenthasResearchGroup agenthasResearchGroup = new AgenthasResearchGroup();

    List agenthasResearchGroups = new ArrayList();

    //construtor
    public AgenthasResearchGroupBean() {
        agenthasResearchGroups = new AgenthasResearchGroupDAO().buscarTodas();
        agenthasResearchGroup = new AgenthasResearchGroup();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new AgenthasResearchGroupDAO().persistir(agenthasResearchGroup);
        agenthasResearchGroups = new AgenthasResearchGroupDAO().buscarTodas();
        agenthasResearchGroup = new AgenthasResearchGroup();
    }

    public void exclude(ActionEvent actionEvent) {
        new AgenthasResearchGroupDAO().remover(agenthasResearchGroup);
        agenthasResearchGroups = new AgenthasResearchGroupDAO().buscarTodas();
        agenthasResearchGroup = new AgenthasResearchGroup();
    }
    
    //getters and setters
    public AgenthasResearchGroup getAgenthasResearchGroup() {
        return agenthasResearchGroup;
    }

    public void setAgenthasResearchGroup(AgenthasResearchGroup agenthasResearchGroup) {
        this.agenthasResearchGroup = agenthasResearchGroup;
    }

    public List getAgenthasResearchGroups() {
        return agenthasResearchGroups;
    }

    public void setAgenthasResearchGroups(List agenthasResearchGroups) {
        this.agenthasResearchGroups = agenthasResearchGroups;
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

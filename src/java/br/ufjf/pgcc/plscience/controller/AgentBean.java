package br.ufjf.pgcc.plscience.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.ufjf.pgcc.plscience.dao.AgentDAO;
import br.ufjf.pgcc.plscience.model.Agent;
import br.ufjf.pgcc.plscience.util.EncryptPasswordUtil;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.hibernate.HibernateException;

/**
 *
 * @author tassio
 */
@ManagedBean(name = "agentBean")
@ViewScoped
public class AgentBean {

    Agent agent = new Agent();

    List agents = new ArrayList();
    
    //construtor
    public AgentBean() {
        agents = new AgentDAO().buscarTodas();
        agent = new Agent();
    }
    
    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new AgentDAO().persistir(agent);
        agents = new AgentDAO().buscarTodas();
        //agent = new Agent();
    }

    public void save() throws IOException {
        try {
            String passwordMD5 = EncryptPasswordUtil.md5(getAgent().getPassword());
            getAgent().setPassword(passwordMD5);
            new AgentDAO().persistir(getAgent());
            agents = new AgentDAO().buscarTodas();
            //agent = new Agent();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/plscience-ecos/faces/login.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Scientist registered with success!"));
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));   
        }
    }
    
    public void exclude(ActionEvent actionEvent) {
        new AgentDAO().remover(agent);
        agents = new AgentDAO().buscarTodas();
        agent = new Agent();
    }
    
    //getters and setters
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List getAgents() {
        return agents;
    }

    public void setAgents(List agents) {
        this.agents = agents;
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

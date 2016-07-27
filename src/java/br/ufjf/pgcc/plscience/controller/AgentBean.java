package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.AgentDAO;
import br.ufjf.pgcc.plscience.dao.IsPartOfDAO;
import br.ufjf.pgcc.plscience.integration.InteroperabilityStructXML;
import br.ufjf.pgcc.plscience.integration.InteroperabilityStructXMLDAO;
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
import java.io.Serializable;
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
public class AgentBean implements Serializable {

    Agent agent = new Agent();

    List agents = new ArrayList();

    //Armazena todas as pessoas cadastradas menos a pessoa logada.
    private List agentsLog = new ArrayList();
    private List agentsAll = new ArrayList();
    private List agentsGroup = new ArrayList();
    private List agentsIdGroup = new ArrayList();
    private List agentsOfGroup = new ArrayList();
    
    //Informações do Alinhamento usadas para auxiliar a percepção do usuario.
    private InteroperabilityStructXML interoperabilityStructXML;
    
    public AgentBean() {
        agents = new AgentDAO().buscarTodas();
        agent = new Agent();
        
        agentsLog = new AgentDAO().buscarTodasInternos();
        agentsAll = new AgentDAO().buscarTodasMenosLogada();
        agentsGroup = new AgentDAO().buscarTodasPorIdGrupo();
        agentsIdGroup = new IsPartOfDAO().buscarGruposUsuario();
        
        interoperabilityStructXML = new InteroperabilityStructXMLDAO().getInteroperabilityStructXMLById(1l);
        
        if(agentsIdGroup != null) {
            for (Object i : agentsIdGroup) {
                agentsOfGroup.add(new IsPartOfDAO().listarPessoasPorIdGrupo((Integer) i));
            }
        }
    }

    public void uptadeAgentsLog() {
        agentsLog.clear();
        agentsLog = new AgentDAO().buscarTodasMenosLogada();
    }

    public void updateAgentsGroup() {
        getAgentsGroup().clear();
        setAgentsGroup(new AgentDAO().buscarTodasPorIdGrupo());
    }

    public void record(ActionEvent actionEvent) {
        new AgentDAO().persistir(agent);
        agents = new AgentDAO().buscarTodas();
    }

    public void save() throws IOException {
        try {
            String passwordMD5 = EncryptPasswordUtil.md5(getAgent().getPassword());
            getAgent().setPassword(passwordMD5);
            getAgent().setLocal(true);
            new AgentDAO().persistir(getAgent());
            agents = new AgentDAO().buscarTodas();
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

    /**
     * @return the agentsLog
     */
    public List getAgentsLog() {
        return agentsLog;
    }

    /**
     * @param agentsLog the agentsLog to set
     */
    public void setAgentsLog(List agentsLog) {
        this.agentsLog = agentsLog;
    }

    /**
     * @return the agentsGroup
     */
    public List getAgentsGroup() {
        return agentsGroup;
    }

    /**
     * @param agentsGroup the agentsGroup to set
     */
    public void setAgentsGroup(List agentsGroup) {
        this.agentsGroup = agentsGroup;
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

    /**
     * @return the agentsIdGroup
     */
    public List getAgentsIdGroup() {
        return agentsIdGroup;
    }

    /**
     * @param agentsIdGroup the agentsIdGroup to set
     */
    public void setAgentsIdGroup(List agentsIdGroup) {
        this.agentsIdGroup = agentsIdGroup;
    }

    /**
     * @return the agentsOfGroup
     */
    public List getAgentsOfGroup() {
        return agentsOfGroup;
    }

    /**
     * @param agentsOfGroup the agentsOfGroup to set
     */
    public void setAgentsOfGroup(List agentsOfGroup) {
        this.agentsOfGroup = agentsOfGroup;
    }

    /**
     * @return the agentsAll
     */
    public List getAgentsAll() {
        return agentsAll;
    }

    /**
     * @param agentsAll the agentsAll to set
     */
    public void setAgentsAll(List agentsAll) {
        this.agentsAll = agentsAll;
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

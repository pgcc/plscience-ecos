package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.UsedDAO;
import br.ufjf.pgcc.plscience.model.Used;
import br.ufjf.pgcc.plscience.model.Workflow;
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
@ManagedBean(name = "usedBean")
@ViewScoped
public class UsedBean {

    Used used = new Used();

    List useds = new ArrayList();

    public UsedBean() {
        useds = new UsedDAO().buscarTodas();
        used = new Used();
    }

    public List<Used> buscartaskbyworkflow(int idworkflow) {
        useds = (List) new UsedDAO().buscar(idworkflow);
        return useds;
    }
    
    public List<Used> buscarexperiment(int id) {
        useds = (List) new UsedDAO().buscarporexperimento(id);
        return useds;
    }
    
    public void record(ActionEvent actionEvent) {
        new UsedDAO().persistir(used);
        useds = new UsedDAO().buscarTodas();
        used = new Used();
    }

    public void exclude(ActionEvent actionEvent) {
        new UsedDAO().remover(used);
        useds = new UsedDAO().buscarTodas();
        used = new Used();
    }

    public Used getUsed() {
        return used;
    }

    public void setUsed(Used used) {
        this.used = used;
    }

    public List getUseds() {
        return useds;
    }

    public void setUseds(List useds) {
        this.useds = useds;
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

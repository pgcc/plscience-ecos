package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.WasAssociatedWithDAO;
import br.ufjf.pgcc.plscience.model.WasAssociatedWith;
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
@ManagedBean(name = "wasAssociatedWithBean")
@ViewScoped
public class WasAssociatedWithBean {

    WasAssociatedWith wasAssociatedWith = new WasAssociatedWith();

    List wasAssociatedWiths = new ArrayList();

    public WasAssociatedWithBean() {
        wasAssociatedWiths = new WasAssociatedWithDAO().buscarTodas();
        wasAssociatedWith = new WasAssociatedWith();
    }
    
    public List<WasAssociatedWith> buscar(int id) {
        wasAssociatedWiths = (List) new WasAssociatedWithDAO().buscar(id);
        return wasAssociatedWiths;
    }

    public void record(ActionEvent actionEvent) {
        new WasAssociatedWithDAO().persistir(wasAssociatedWith);
        wasAssociatedWiths = (List) new WasAssociatedWithDAO().buscarTodas();
        wasAssociatedWith = new WasAssociatedWith();
    }

    public void exclude(ActionEvent actionEvent) {
        new WasAssociatedWithDAO().remover(wasAssociatedWith);
        wasAssociatedWiths = new WasAssociatedWithDAO().buscarTodas();
        wasAssociatedWith = new WasAssociatedWith();
    }

    public WasAssociatedWith getWasAssociatedWith() {
        return wasAssociatedWith;
    }

    public void setWasAssociatedWith(WasAssociatedWith wasAssociatedWith) {
        this.wasAssociatedWith = wasAssociatedWith;
    }

    public List getWasAssociatedWiths() {
        return wasAssociatedWiths;
    }

    public void setWasAssociatedWiths(List wasAssociatedWiths) {
        this.wasAssociatedWiths = wasAssociatedWiths;
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

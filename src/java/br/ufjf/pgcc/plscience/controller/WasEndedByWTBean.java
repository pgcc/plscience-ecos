package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.WasEndedByWTDAO;
import br.ufjf.pgcc.plscience.model.WasEndedByWT;
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
@ManagedBean(name = "wasEndedByWTBean")
@ViewScoped
public class WasEndedByWTBean {

    WasEndedByWT wasEndedBy = new WasEndedByWT();

    List wasEndedBys = new ArrayList();

    public WasEndedByWTBean() {
        wasEndedBys = new WasEndedByWTDAO().buscarTodas();
        wasEndedBy = new WasEndedByWT();
    }
    
    public List<WasEndedByWT> buscartaskbyworkflow(int idworkflow) {
        wasEndedBys = (List) new WasEndedByWTDAO().buscar(idworkflow);
        return wasEndedBys;
    }

    public void record(ActionEvent actionEvent) {
        new WasEndedByWTDAO().persistir(wasEndedBy);
        wasEndedBys = (List) new WasEndedByWTDAO().buscarTodas();
        wasEndedBy = new WasEndedByWT();
    }

    public void exclude(ActionEvent actionEvent) {
        new WasEndedByWTDAO().remover(wasEndedBy);
        wasEndedBys = new WasEndedByWTDAO().buscarTodas();
        wasEndedBy = new WasEndedByWT();
    }

    public WasEndedByWT getWasEndedByWT() {
        return wasEndedBy;
    }

    public void setWasEndedByWT(WasEndedByWT wasEndedBy) {
        this.wasEndedBy = wasEndedBy;
    }

    public List getWasEndedByWTs() {
        return wasEndedBys;
    }

    public void setWasEndedByWTs(List wasEndedBys) {
        this.wasEndedBys = wasEndedBys;
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

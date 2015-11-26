package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.WasInformedByDAO;
import br.ufjf.pgcc.plscience.model.WasInformedBy;
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
@ManagedBean(name = "wasInformedByBean")
@ViewScoped
public class WasInformedByBean {

    WasInformedBy wasInformedBy = new WasInformedBy();

    List wasInformedBys = new ArrayList();

    public WasInformedByBean() {
        wasInformedBys = new WasInformedByDAO().buscarTodas();
        wasInformedBy = new WasInformedBy();
    }
    
    public List<WasInformedBy> buscartaskbyworkflow(int idworkflow) {
        wasInformedBys = (List) new WasInformedByDAO().buscar(idworkflow);
        return wasInformedBys;
    }

     public List<WasInformedBy> buscartaskproblem(int idworkflow) {
        wasInformedBys = (List) new WasInformedByDAO().buscarcomproblema(idworkflow);
        return wasInformedBys;
    }
    
    public void record(ActionEvent actionEvent) {
        new WasInformedByDAO().persistir(wasInformedBy);
        wasInformedBys = (List) new WasInformedByDAO().buscarTodas();
        wasInformedBy = new WasInformedBy();
    }

    public void exclude(ActionEvent actionEvent) {
        new WasInformedByDAO().remover(wasInformedBy);
        wasInformedBys = new WasInformedByDAO().buscarTodas();
        wasInformedBy = new WasInformedBy();
    }

    public WasInformedBy getWasInformedBy() {
        return wasInformedBy;
    }

    public void setWasInformedBy(WasInformedBy wasInformedBy) {
        this.wasInformedBy = wasInformedBy;
    }

    public List getWasInformedBys() {
        return wasInformedBys;
    }

    public void setWasInformedBys(List wasInformedBys) {
        this.wasInformedBys = wasInformedBys;
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

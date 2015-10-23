package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.WasStartedByDAO;
import br.ufjf.pgcc.plscience.model.WasStartedBy;
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
@ManagedBean(name = "wasStartedByBean")
@ViewScoped
public class WasStartedByBean {

    WasStartedBy wasStartedBy = new WasStartedBy();

    List wasStartedBys = new ArrayList();

    public WasStartedByBean() {
        wasStartedBys = new WasStartedByDAO().buscarTodas();
        wasStartedBy = new WasStartedBy();
    }
    
    public List<WasStartedBy> buscartaskbyworkflow(int idworkflow) {
        wasStartedBys = (List) new WasStartedByDAO().buscar(idworkflow);
        return wasStartedBys;
    }

    public void record(ActionEvent actionEvent) {
        new WasStartedByDAO().persistir(wasStartedBy);
        wasStartedBys = (List) new WasStartedByDAO().buscarTodas();
        wasStartedBy = new WasStartedBy();
    }

    public void exclude(ActionEvent actionEvent) {
        new WasStartedByDAO().remover(wasStartedBy);
        wasStartedBys = new WasStartedByDAO().buscarTodas();
        wasStartedBy = new WasStartedBy();
    }

    public WasStartedBy getWasStartedBy() {
        return wasStartedBy;
    }

    public void setWasStartedBy(WasStartedBy wasStartedBy) {
        this.wasStartedBy = wasStartedBy;
    }

    public List getWasStartedBys() {
        return wasStartedBys;
    }

    public void setWasStartedBys(List wasStartedBys) {
        this.wasStartedBys = wasStartedBys;
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

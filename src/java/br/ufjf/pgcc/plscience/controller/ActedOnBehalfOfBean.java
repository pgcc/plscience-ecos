package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.ActedOnBehalfOfDAO;
import br.ufjf.pgcc.plscience.model.ActedOnBehalfOf;
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
@ManagedBean(name = "actedOnBeHalfOfBean")
@ViewScoped
public class ActedOnBehalfOfBean {

    ActedOnBehalfOf actedOnBeHalfOf = new ActedOnBehalfOf();

    List actedOnBeHalfOfs = new ArrayList();

    public ActedOnBehalfOfBean() {
        actedOnBeHalfOfs = new ActedOnBehalfOfDAO().buscarTodas();
        actedOnBeHalfOf = new ActedOnBehalfOf();
    }

    public List<ActedOnBehalfOf> buscartaskwithtask(int idworkflow) {
        actedOnBeHalfOfs = (List) new ActedOnBehalfOfDAO().buscar(idworkflow);
        return actedOnBeHalfOfs;
    }
    
    public void record(ActionEvent actionEvent) {
        new ActedOnBehalfOfDAO().persistir(actedOnBeHalfOf);
        actedOnBeHalfOfs = new ActedOnBehalfOfDAO().buscarTodas();
        actedOnBeHalfOf = new ActedOnBehalfOf();
    }

    public void exclude(ActionEvent actionEvent) {
        new ActedOnBehalfOfDAO().remover(actedOnBeHalfOf);
        actedOnBeHalfOfs = new ActedOnBehalfOfDAO().buscarTodas();
        actedOnBeHalfOf = new ActedOnBehalfOf();
    }

    public ActedOnBehalfOf getActedOnBehalfOf() {
        return actedOnBeHalfOf;
    }

    public void setActedOnBehalfOf(ActedOnBehalfOf actedOnBeHalfOf) {
        this.actedOnBeHalfOf = actedOnBeHalfOf;
    }

    public List getActedOnBehalfOfs() {
        return actedOnBeHalfOfs;
    }

    public void setActedOnBehalfOfs(List actedOnBeHalfOfs) {
        this.actedOnBeHalfOfs = actedOnBeHalfOfs;
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

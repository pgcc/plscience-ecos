package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.IsPartOfDAO;
import br.ufjf.pgcc.plscience.model.IsPartOf;
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
@ManagedBean(name = "isPartOfBean")
@ViewScoped
public class IsPartOfBean implements Serializable {

    IsPartOf isPartOf = new IsPartOf();

    List isPartOfs = new ArrayList();

    public IsPartOfBean() {
        isPartOfs = new IsPartOfDAO().buscarTodas();
        isPartOf = new IsPartOf();
    }

    public void record(ActionEvent actionEvent) {
        new IsPartOfDAO().persistir(isPartOf);
        isPartOfs = new IsPartOfDAO().buscarTodas();
        isPartOf = new IsPartOf();
    }

    public void exclude(ActionEvent actionEvent) {
        new IsPartOfDAO().remover(isPartOf);
        isPartOfs = new IsPartOfDAO().buscarTodas();
        isPartOf = new IsPartOf();
    }

    public IsPartOf getIsPartOf() {
        return isPartOf;
    }

    public void setIsPartOf(IsPartOf isPartOf) {
        this.isPartOf = isPartOf;
    }

    public List getIsPartOfs() {
        return isPartOfs;
    }

    public void setIsPartOfs(List isPartOfs) {
        this.isPartOfs = isPartOfs;
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

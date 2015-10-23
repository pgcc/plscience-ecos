package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.dao.ActivityDAO;
import br.ufjf.pgcc.plscience.dao.EntityDAO;
import br.ufjf.pgcc.plscience.model.Activity;
import br.ufjf.pgcc.plscience.model.Entity;
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
@ManagedBean(name = "activityBean")
@ViewScoped
public class ActivityBean implements Serializable{

    Activity activity = new Activity();
    Entity entity = new Entity();
    List activitys = new ArrayList();
    List entitys = new ArrayList();

    public ActivityBean() {
        activitys = new ActivityDAO().buscarTodas();
        activity = new Activity();
        entitys = new EntityDAO().buscarTodas();
        entity = new Entity();
    }

    public void record(ActionEvent actionEvent) {
        activity.setEntityidEntity( entity);
        new ActivityDAO().persistir(activity);
        activitys = new ActivityDAO().buscarTodas();
        activity = new Activity();
    }

    public void exclude(ActionEvent actionEvent) {
        new ActivityDAO().remover(activity);
        activitys = new ActivityDAO().buscarTodas();
        activity = new Activity();
    }
    
    public List getEntitys() {
        return entitys;
    }

    public void setEntitys(List entitys) {    
        this.entitys = entitys;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List getActivitys() {
        return activitys;
    }

    public void setActivitys(List activitys) {
        this.activitys = activitys;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
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

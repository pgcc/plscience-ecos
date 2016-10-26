/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.MappingFile;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author phillipe
 */
public class MappingFileDAO extends GenericDAO {

    public static MappingFileDAO mappingFileDAO;

    public static MappingFileDAO getInstance() {
        if (mappingFileDAO == null) {
            mappingFileDAO = new MappingFileDAO();
        }
        return mappingFileDAO;
    }

    /**
     * save a mapping file on the E-SECO software product line repository
     * @param mf 
     */
    public void save(MappingFile mf) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(mf);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    /**
     * update a mapping file on the E-SECO software product line repository
     * @param mf 
     */
    public void update(MappingFile mf) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(mf);
        getEntityManager().getTransaction().commit();
        finish();
    }    

    /**
     * get all mapping files
     * @return 
     */
    public List<MappingFile> getAll() {
        Query query = getEntityManager().createQuery("SELECT mf FROM MappingFile AS mf");
        List<MappingFile> mappingFileList = query.getResultList();
        finish();
        return mappingFileList;
    }
    
    /**
     * get mapping file
     * @param id
     * @return 
     */
    public MappingFile getMappingFileById(Long id) {
        
        Query query = getEntityManager().createQuery("SELECT mf FROM MappingFile AS mf WHERE mf.id = :id");
        query.setParameter("id", id);

        List<MappingFile> mappingFileList = query.getResultList();
        finish();
        if (mappingFileList != null && mappingFileList.size() > 0) {
            return mappingFileList.get(0);
        }
        return null;
    }    
}

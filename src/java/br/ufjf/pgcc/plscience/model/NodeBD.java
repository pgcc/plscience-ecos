/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phillipe
 */
@javax.persistence.Entity
@Table(name = "node")
@XmlRootElement
public class NodeBD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_network")
    private String idNetwork;    
    @Column(name = "full_name")   
    private String fullName;
    @Column(name = "year_in_network")   
    private String yearInNetwork;
    @Column(name = "level")   
    private String level;
    @Column(name = "image")   
    private String image;
    @Column(name = "local_centrality")   
    private String localCentrality;    
    @Column(name = "global_centrality")   
    private String globalCentrality;
    @Column(name = "has_picture_file")   
    private String hasPictureFile;
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    @ManyToOne
    private University universityId;
    @JoinColumn(name = "aggregator", referencedColumnName = "id")
    @ManyToOne
    private UniversityGroup universityGroupId;    

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the yearInNetwork
     */
    public String getYearInNetwork() {
        return yearInNetwork;
    }

    /**
     * @param yearInNetwork the yearInNetwork to set
     */
    public void setYearInNetwork(String yearInNetwork) {
        this.yearInNetwork = yearInNetwork;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the localCentrality
     */
    public String getLocalCentrality() {
        return localCentrality;
    }

    /**
     * @param localCentrality the localCentrality to set
     */
    public void setLocalCentrality(String localCentrality) {
        this.localCentrality = localCentrality;
    }

    /**
     * @return the globalCentrality
     */
    public String getGlobalCentrality() {
        return globalCentrality;
    }

    /**
     * @param globalCentrality the globalCentrality to set
     */
    public void setGlobalCentrality(String globalCentrality) {
        this.globalCentrality = globalCentrality;
    }

    /**
     * @return the universityId
     */
    public University getUniversityId() {
        return universityId;
    }

    /**
     * @param universityId the universityId to set
     */
    public void setUniversityId(University universityId) {
        this.universityId = universityId;
    }

    /**
     * @return the universityGroupId
     */
    public UniversityGroup getUniversityGroupId() {
        return universityGroupId;
    }

    /**
     * @param universityGroupId the universityGroupId to set
     */
    public void setUniversityGroupId(UniversityGroup universityGroupId) {
        this.universityGroupId = universityGroupId;
    }

    /**
     * @return the idNetwork
     */
    public String getIdNetwork() {
        return idNetwork;
    }

    /**
     * @param idNetwork the idNetwork to set
     */
    public void setIdNetwork(String idNetwork) {
        this.idNetwork = idNetwork;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the hasPictureFile
     */
    public String getHasPictureFile() {
        return hasPictureFile;
    }

    /**
     * @param hasPictureFile the hasPictureFile to set
     */
    public void setHasPictureFile(String hasPictureFile) {
        this.hasPictureFile = hasPictureFile;
    }
}

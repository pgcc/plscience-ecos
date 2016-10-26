/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.model;

import java.io.Serializable;
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
@Table(name = "hardware_configurations")
@XmlRootElement
public class HardwareConfigurations implements Serializable{
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "hardware_configurations_cpu")   
    private String hardwareConfigurationsCPU;
    @Column(name = "hardware_configurations_ram_gb")   
    private String hardwareConfigurationsRamGb;
    @Column(name = "hardware_configurations_rom_gb")   
    private String hardwareConfigurationsRomGb;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne
    private Service serviceId;

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
     * @return the hardwareConfigurationsCPU
     */
    public String getHardwareConfigurationsCPU() {
        return hardwareConfigurationsCPU;
    }

    /**
     * @param hardwareConfigurationsCPU the hardwareConfigurationsCPU to set
     */
    public void setHardwareConfigurationsCPU(String hardwareConfigurationsCPU) {
        this.hardwareConfigurationsCPU = hardwareConfigurationsCPU;
    }

    /**
     * @return the hardwareConfigurationsRamGb
     */
    public String getHardwareConfigurationsRamGb() {
        return hardwareConfigurationsRamGb;
    }

    /**
     * @param hardwareConfigurationsRamGb the hardwareConfigurationsRamGb to set
     */
    public void setHardwareConfigurationsRamGb(String hardwareConfigurationsRamGb) {
        this.hardwareConfigurationsRamGb = hardwareConfigurationsRamGb;
    }

    /**
     * @return the hardwareConfigurationsRomGb
     */
    public String getHardwareConfigurationsRomGb() {
        return hardwareConfigurationsRomGb;
    }

    /**
     * @param hardwareConfigurationsRomGb the hardwareConfigurationsRomGb to set
     */
    public void setHardwareConfigurationsRomGb(String hardwareConfigurationsRomGb) {
        this.hardwareConfigurationsRomGb = hardwareConfigurationsRomGb;
    }

    /**
     * @return the serviceId
     */
    public Service getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }
}

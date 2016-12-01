/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Clesse de definição do Web Service
 *
 * @author Lenita
 */
@javax.ws.rs.ApplicationPath("ws")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(br.ufjf.pgcc.plscience.services.BloodTypeProbability.class);
//        resources.add(br.ufjf.pgcc.plscience.services.BloodTypeProbabilityPrimitiveType.class);
//        resources.add(br.ufjf.pgcc.plscience.services.EquivalentServicesResource.class);
//        resources.add(br.ufjf.pgcc.plscience.services.HelloResource.class);
//        resources.add(br.ufjf.pgcc.plscience.services.MatrixCombinationPhenotypes.class);
//        resources.add(br.ufjf.pgcc.plscience.services.MatrixCombinationPhenotypesPrimitiveTypes.class);
//        resources.add(br.ufjf.pgcc.plscience.services.PhenotypeTranslation.class);
//        resources.add(br.ufjf.pgcc.plscience.services.PhenotypeTranslationPrimitiveType.class);
        resources.add(br.ufjf.pgcc.plscience.services.BloodTypeProbability.class);
        resources.add(br.ufjf.pgcc.plscience.services.BloodTypeProbabilityPrimitiveType.class);
        resources.add(br.ufjf.pgcc.plscience.services.EquivalentServicesResource.class);
        resources.add(br.ufjf.pgcc.plscience.services.HelloResource.class);
        resources.add(br.ufjf.pgcc.plscience.services.MatrixCombinationPhenotypes.class);
        resources.add(br.ufjf.pgcc.plscience.services.MatrixCombinationPhenotypesPrimitiveTypes.class);
        resources.add(br.ufjf.pgcc.plscience.services.PhenotypeTranslation.class);
        resources.add(br.ufjf.pgcc.plscience.services.PhenotypeTranslationPrimitiveType.class);
        resources.add(br.ufjf.pgcc.plscience.services.ServiceResources.class);
    }

}

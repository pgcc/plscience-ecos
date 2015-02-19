/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.util;

import br.ufjf.pgcc.plscience.services.EquivalentServicesResource;

/**
 *
 * @author Fran
 */
public class Testinhos {
     public static void main(String[] args) {
       EquivalentServicesResource er = new EquivalentServicesResource();
       System.out.println(er.getSemanticallyEquivalentServices("Service1"));
    }
}

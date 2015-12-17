/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.util;

import java.text.DecimalFormat;

/**
 *
 * @author Guilherme Martins
 */
public class DecimalUtil {
    
    private String decimalFormat = "##0.00";  
    private DecimalFormat decimalFormatParser = new DecimalFormat(decimalFormat);

    /**
     * @return the decimalFormatParser
     */
    public DecimalFormat getDecimalFormatParser() {
        return decimalFormatParser;
    }

    /**
     * @param decimalFormatParser the decimalFormatParser to set
     */
    public void setDecimalFormatParser(DecimalFormat decimalFormatParser) {
        this.decimalFormatParser = decimalFormatParser;
    }    
    
}

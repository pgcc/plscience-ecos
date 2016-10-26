/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.searchComponents;

import br.ufjf.pgcc.plscience.vo.ContextVO;
import br.ufjf.pgcc.plscience.vo.PragmaticVO;
import br.ufjf.pgcc.plscience.vo.SemanticVO;
import br.ufjf.pgcc.plscience.vo.ServiceDescriptionVO;
import br.ufjf.pgcc.plscience.vo.SyntacticVO;

/**
 *
 * @author phillipe
 */
public class TestPrimeSearch {
    public static void main(String[] args){
        SearchPrime sp = new SearchPrime();
        ServiceDescriptionVO serviceParameters = new ServiceDescriptionVO();
        SyntacticVO synt = new SyntacticVO();
        serviceParameters.setIncludesSyntactic(synt);
        serviceParameters.getIncludesSyntactic().setHasReturn("stringtype");
        PragmaticVO prag = new PragmaticVO();
        ContextVO context = new ContextVO();
        prag.setIncludesContext(context);
        serviceParameters.setIncludesPragmatic(prag);
        serviceParameters.getIncludesPragmatic().getIncludesContext().setHasLicense("public");
        sp.setServiceDescriptionVO(serviceParameters);
        
        sp.searchServices();
    }
}

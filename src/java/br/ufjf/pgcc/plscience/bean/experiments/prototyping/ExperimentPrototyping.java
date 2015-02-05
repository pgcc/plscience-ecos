/*
 * The MIT License
 *
 * Copyright 2014 Pós-Graduação em Ciência da Computação UFJF.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.ufjf.pgcc.plscience.bean.experiments.prototyping;


import br.ufjf.pgcc.plscience.interoperability.ServiceRecovery;
import br.ufjf.pgcc.plscience.vo.ContextVO;
import br.ufjf.pgcc.plscience.vo.HardwareVO;
import br.ufjf.pgcc.plscience.vo.PragmaticVO;
import br.ufjf.pgcc.plscience.vo.SemanticVO;
import br.ufjf.pgcc.plscience.vo.ServiceDescriptionVO;
import br.ufjf.pgcc.plscience.vo.SyntacticVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class ExperimentPrototyping implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String numberStages;
    private String draw;
    private ArrayList<String> stages;
    private ServiceDescriptionVO serviceDescriptionVO;
    private List<ServiceDescriptionVO> services;

    

    public ExperimentPrototyping() {
        serviceDescriptionVO = new ServiceDescriptionVO();
        SyntacticVO sync = new SyntacticVO();
        serviceDescriptionVO.setIncludesSyntactic(sync);
        SemanticVO sem = new SemanticVO();
        serviceDescriptionVO.setIncludesSemantic(sem);
        PragmaticVO prag = new PragmaticVO();
        serviceDescriptionVO.setIncludesPragmatic(prag);
        ArrayList<String> funcs = new ArrayList();
        serviceDescriptionVO.getIncludesSemantic().setHasFunctionalRequirements(funcs);
        ContextVO con = new ContextVO();
        serviceDescriptionVO.getIncludesPragmatic().setIncludesContext(con);
        HardwareVO hard = new HardwareVO();
        serviceDescriptionVO.getIncludesPragmatic().setIncludesHardware(hard);
        services = new ArrayList<ServiceDescriptionVO>();
    }
    
    public void drawStages(){
        System.out.println(numberStages);
        setDraw(numberStages);
        stages = new ArrayList<String>();
        for(int i=0;i<Integer.valueOf(getNumberStages());i++){
            stages.add("Stage "+i);
        }
            
    }

    public String getNumberStages() {
        return numberStages;
    }

    public void setNumberStages(String numberStages) {
        this.numberStages = numberStages;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public ArrayList<String> getStages() {
        return stages;
    }

    public void setStages(ArrayList<String> stages) {
        this.stages = stages;
    }
    
    public ServiceDescriptionVO getServiceDescriptionVO() {
        return serviceDescriptionVO;
    }

    public void setServiceDescriptionVO(ServiceDescriptionVO serviceDescriptionVO) {
        this.serviceDescriptionVO = serviceDescriptionVO;
    }

    public List<ServiceDescriptionVO> getServices() {
        return services;
    }

    public void setServices(List<ServiceDescriptionVO> services) {
        this.services = services;
    }
    
    

    public void searchServices(){
        System.out.println("OLAAA");
        ServiceRecovery sr = new ServiceRecovery();
        setServices(sr.Recovery());
        System.out.println("OLAAA");
        System.out.println(serviceDescriptionVO.getIncludesSyntactic().getHasAddress());
        System.out.println(serviceDescriptionVO.getIncludesSyntactic().getHasReturn());
        System.out.println(serviceDescriptionVO.getIncludesSemantic().getHasSemanticReturn());
        System.out.println(serviceDescriptionVO.getIncludesSemantic().getHasSemanticReception());
        System.out.println(serviceDescriptionVO.getIncludesSemantic().getHasSemanticRepresentation());
        System.out.println(serviceDescriptionVO.getIncludesSemantic().getHasFunctionalRequirement());
        ArrayList<String> s = new ArrayList();
        s.add(serviceDescriptionVO.getIncludesSemantic().getHasFunctionalRequirement());
        serviceDescriptionVO.getIncludesSemantic().setHasFunctionalRequirements(s);
        System.out.println(serviceDescriptionVO.getIncludesSemantic().getHasFunctionalRequirements().get(0));
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getHasNonFunctionalReq());
        ArrayList<String> s2 = new ArrayList();
        s2.add(serviceDescriptionVO.getIncludesPragmatic().getHasNonFunctionalReq());
        serviceDescriptionVO.getIncludesPragmatic().setHasNonFunctionalRequirement(s2);
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getHasNonFunctionalRequirement().get(0));
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasArtifact());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasComments());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasDomain());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasLicense());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasReputation());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHasRestriction());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getHow());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getWhen());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getWhere());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesContext().getWho());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasCPU());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasOperationalSystem());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasOperationalSystem());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasRAM());
        System.out.println(serviceDescriptionVO.getIncludesPragmatic().getIncludesHardware().getHasROM());
        
    }
    
    
}

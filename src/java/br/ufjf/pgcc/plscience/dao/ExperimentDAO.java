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
package br.ufjf.pgcc.plscience.dao;

import br.ufjf.pgcc.plscience.model.Experiment;
import java.util.List;
import javax.persistence.Query;
/**
 *
 * @author vitorfs
 */
public class ExperimentDAO extends GenericDAO {
    
    public void save(Experiment experiment) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(experiment);
        getEntityManager().getTransaction().commit();
        finish();
    }
    
    public void update(Experiment experiment) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(experiment);
        getEntityManager().getTransaction().commit();
        finish();    
    }
    
    public List<Experiment> getAll() {
        Query query = getEntityManager().createQuery("SELECT e FROM Experiment e");
        List<Experiment> experiments = query.getResultList();
        finish();
        return experiments;
    }
    
    public Experiment getId(Long id) {
        Query query = getEntityManager().createQuery("SELECT e FROM Experiment e");
        List<Experiment> experiments = query.getResultList();
        Experiment experiment = new Experiment();
        for(Experiment e: experiments){
            if(e.getId().equals(id)){
                experiment = e;
            }
        }
        finish();
        return experiment;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufjf.pgcc.plscience.repository;

import br.ufjf.pgcc.plscience.domain.Experiment;
import java.util.List;
/**
 *
 * @author vitorfs
 */
public class ExperimentRepository extends RepositoryBase<Experiment>{
    
    public List<Experiment> findAll() {
        return findAll(Experiment.class);
    }
}

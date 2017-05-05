package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.social.api.model.scholar.ScholarAuthor;
import br.ufjf.pgcc.plscience.social.api.model.scholar.Publication;
import br.ufjf.pgcc.plscience.social.apli.client.ScholarClient;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;



/**
 *
 * @author Jonathan
 */
@ManagedBean(name = "socialProfileBean")
@ApplicationScoped
public class SocialProfileBean {
    
    ScholarAuthor scholarAuthor = new ScholarAuthor();
    
    public SocialProfileBean(){
        scholarAuthor = ScholarClient.getAuthor("Regina Braga", "localhost");
    }
    
    public ScholarAuthor getScholarAuthor(){
        return scholarAuthor;
    }
    
    public List<Publication> getPublications(){
        return scholarAuthor.getPublications();
    }
  }
    

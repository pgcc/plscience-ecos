package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.social.api.model.dblp.Coauthor;
import br.ufjf.pgcc.plscience.social.api.model.dblp.DblpAuthor;
import br.ufjf.pgcc.plscience.social.api.model.scholar.Indice;
import br.ufjf.pgcc.plscience.social.api.model.scholar.ScholarAuthor;
import br.ufjf.pgcc.plscience.social.api.model.scholar.Publication;
import br.ufjf.pgcc.plscience.social.apli.client.DblpClient;
import br.ufjf.pgcc.plscience.social.apli.client.ScholarClient;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;



/**
 *
 * @author Jonathan
 */
@ManagedBean(name = "socialProfileBean")
@ApplicationScoped
public class SocialProfileBean {
    
    ScholarAuthor scholarAuthor = new ScholarAuthor();
    DblpAuthor dblpAuthor = new DblpAuthor();
    Publication selectedPublication;
    private TagCloudModel tagCloud;
    
    
    public SocialProfileBean(){
        scholarAuthor = ScholarClient.getAuthor("Regina Braga", "localhost");
        dblpAuthor = DblpClient.getAuthor("Regina M. M. Braga", "localhost");
        
        tagCloud = new DefaultTagCloudModel();
        tagCloud.addTag(new DefaultTagCloudItem("Transformers", 1));
        tagCloud.addTag(new DefaultTagCloudItem("RIA", "#", 3));
        tagCloud.addTag(new DefaultTagCloudItem("AJAX", 2));
        tagCloud.addTag(new DefaultTagCloudItem("jQuery", "#", 5));
        tagCloud.addTag(new DefaultTagCloudItem("NextGen", 4));
        tagCloud.addTag(new DefaultTagCloudItem("JSF 2.0", "#", 2));
        tagCloud.addTag(new DefaultTagCloudItem("FCB", 5));
        tagCloud.addTag(new DefaultTagCloudItem("Mobile",  3));
        tagCloud.addTag(new DefaultTagCloudItem("Themes", "#", 4));
        tagCloud.addTag(new DefaultTagCloudItem("Rocks", "#", 1));
    }
    
    public ScholarAuthor getScholarAuthor(){
        return scholarAuthor;
    }
    
    public DblpAuthor  getDblpAuthor(){
        return dblpAuthor;
    } 
    
    public List<Coauthor> getCoauthors(){
        return dblpAuthor.getCoauthors();
    }

    public List<Publication> getPublications(){
        return scholarAuthor.getPublications();
    }

    public Publication getSelectedPublication() {
        return selectedPublication;
    }

    public void setSelectedPublication(Publication selectedPublication) {
        this.selectedPublication = selectedPublication;
    }
       
    public TagCloudModel getTagCloud() {
        return tagCloud;
    }
 
    public List<Indice> getIndices(){
        List<Indice> indices = new ArrayList<Indice>();
        List<String> researcherIndices = scholarAuthor.getCitationIndices();
        Indice citationIndice = new Indice("Citations", Integer.parseInt(researcherIndices.get(0)), Integer.parseInt(researcherIndices.get(1)));
        Indice hIndex = new Indice("h-index", Integer.parseInt(researcherIndices.get(2)), Integer.parseInt(researcherIndices.get(3))); 
        Indice i10Index = new Indice("i10-index", Integer.parseInt(researcherIndices.get(4)), Integer.parseInt(researcherIndices.get(5))); 
        
        indices.add(citationIndice);
        indices.add(hIndex);
        indices.add(i10Index);
                
        return indices;
    }
  }
    

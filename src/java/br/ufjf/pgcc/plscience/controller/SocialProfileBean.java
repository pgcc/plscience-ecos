package br.ufjf.pgcc.plscience.controller;

import br.ufjf.pgcc.plscience.social.api.model.dblp.Coauthor;
import br.ufjf.pgcc.plscience.social.api.model.dblp.DblpAuthor;
import br.ufjf.pgcc.plscience.social.api.model.scholar.Indice;
import br.ufjf.pgcc.plscience.social.api.model.scholar.ScholarAuthor;
import br.ufjf.pgcc.plscience.social.api.model.scholar.Publication;
import br.ufjf.pgcc.plscience.social.apli.client.DblpClient;
import br.ufjf.pgcc.plscience.social.apli.client.ScholarClient;
import br.ufjf.pgcc.plscience.util.wordCounter.TopWordCounts;
import br.ufjf.pgcc.plscience.util.wordCounter.WordCountAnalyzer;
import br.ufjf.pgcc.plscience.util.wordCounter.WordCounts;
import br.ufjf.pgcc.plscience.util.wordCounter.WordUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
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
    private String scholarAuthorName = "Regina Braga";
    private String dblpAuthorName = "Regina M. M. Braga";
    
    private ScholarAuthor scholarAuthor = new ScholarAuthor();
    private DblpAuthor dblpAuthor = new DblpAuthor();
    private Publication selectedPublication;
    private TagCloudModel tagCloud;
    private String publicationTitles;
    

    public SocialProfileBean() {
        scholarAuthor = ScholarClient.getAuthor(scholarAuthorName, "localhost");
        dblpAuthor = DblpClient.getAuthor(dblpAuthorName, "localhost");
        
        scholarAuthor.getPublications().forEach((pub) -> {
            publicationTitles += " " + pub.getBib().getTitle();
        });
                
        tagCloud = new DefaultTagCloudModel();

        WordCounts wc = WordUtils.countWords(publicationTitles, (c) -> Character.isAlphabetic(c), (s) -> s.toLowerCase());
        TopWordCounts twc = new WordCountAnalyzer(wc, true).findTop(8, (x, y) -> (y - x));
        
        SortedMap<Integer, Set<String>> map = twc.getWordCounts();
        
        map.entrySet().forEach((pair) -> {
            int count = pair.getKey();
            pair.getValue().forEach((s) -> {
                tagCloud.addTag(new DefaultTagCloudItem(s, count));
            });
        }); 
    }

    public ScholarAuthor getScholarAuthor() {
        return scholarAuthor;
    }

    public DblpAuthor getDblpAuthor() {
        return dblpAuthor;
    }

    public List<Coauthor> getCoauthors() {
        return dblpAuthor.getCoauthors();
    }

    public List<Publication> getPublications() {
        return scholarAuthor.getPublications();
    }

    public Publication getSelectedPublication() {
        if(selectedPublication == null){
            return selectedPublication;
        }
        else{
            selectedPublication = ScholarClient.getFilledPublication(scholarAuthorName, selectedPublication.getIdCitations(), "localhost");
            return selectedPublication;
        }
    }

    public void setSelectedPublication(Publication selectedPublication) {
        this.selectedPublication = selectedPublication;
    }

    public TagCloudModel getTagCloud() {
        return tagCloud;
    }

    public List<Indice> getIndices() {
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

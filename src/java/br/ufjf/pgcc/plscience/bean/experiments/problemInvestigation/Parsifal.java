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
package br.ufjf.pgcc.plscience.bean.experiments.problemInvestigation;

import br.ufjf.parsifal.core.ParsifalClient;
import br.ufjf.parsifal.model.Article;
import br.ufjf.parsifal.model.Keyword;
import br.ufjf.parsifal.model.Question;
import br.ufjf.parsifal.model.Review;
import br.ufjf.parsifal.model.SelectionCriteria;
import br.ufjf.parsifal.model.Source;
import br.ufjf.pgcc.plscience.bean.experiments.Workspace;
import br.ufjf.pgcc.plscience.dao.ExperimentDAO;
import br.ufjf.pgcc.plscience.model.Experiment;
import br.ufjf.pgcc.plscience.util.BeanUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author vitorfs
 */
@ManagedBean()
@ViewScoped
public class Parsifal implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final ParsifalClient client;
    
    private final Workspace workspace;
    private Experiment experiment;
    
    private Review review;
    private List<Article> articles;
    private List<Question> questions;
    private List<Source> sources;
    private List<SelectionCriteria> selectionCriterias;
    private List<Keyword> keywords;
    
    private Article selectedArticle;
    
    
    public Parsifal() {
        
        client = new ParsifalClient();
        client.setBaseUri("http://parsif.al/api");
        client.setAuthorization("plscience", "plscience");
        
        workspace = (Workspace) BeanUtil.getManagedBean("workspace");
        if (workspace != null) {
            experiment = workspace.getExperiment();
        }
        
    }

    /**
     * Feed the review, articles, questions, sources, selectionCriterias and
     * keywords resources.
     * Consumes the Parsifal REST API using the given Review ID at 
     * Experiment / Problem Investigation / Parsifal / Settings
     * stored at experiment <strong>parsifalReview</strong> property.
     */
    public final void setUpParsifalReviewResources() {
        if (experiment != null) {
            String reviewId = experiment.getParsifalReview().toString();
            try {
                review = client.getReview(reviewId);
                articles = client.getReviewArticles(reviewId + "&status=A"); // only accepted papers
                questions = client.getReviewQuestions(reviewId);
                selectionCriterias = client.getReviewSelectionCriterias(reviewId);
                keywords = client.getReviewKeywords(reviewId);
                
                List<Source> allSources = client.getSources();
                
                if (review != null) {
                    for (Integer reviewSourceId : review.getSources()) {
                        for (Source source : allSources) {
                            if (reviewSourceId.equals(source.getId())) {
                                sources.add(source);
                            }
                        }   
                    }
                }
                
            } catch (Exception e) {
            }
        }
    }
    
    public void saveSettings() {
        new ExperimentDAO().update(experiment);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Experiment saved with success!"));
    }

    /**
     * @return the experiment
     */
    public Experiment getExperiment() {
        return experiment;
    }

    /**
     * @param experiment the experiment to set
     */
    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    /**
     * @return the review
     */
    public Review getReview() {
        if (review == null) {
            setUpParsifalReviewResources();
        }
        return review;
    }

    /**
     * @param review the review to set
     */
    public void setReview(Review review) {
        this.review = review;
    }

    /**
     * @return the articles
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    /**
     * @return the questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * @return the sources
     */
    public List<Source> getSources() {
        return sources;
    }

    /**
     * @param sources the sources to set
     */
    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    /**
     * @return the selectionCriterias
     */
    public List<SelectionCriteria> getSelectionCriterias() {
        return selectionCriterias;
    }

    /**
     * @param selectionCriterias the selectionCriterias to set
     */
    public void setSelectionCriterias(List<SelectionCriteria> selectionCriterias) {
        this.selectionCriterias = selectionCriterias;
    }

    /**
     * @return the keywords
     */
    public List<Keyword> getKeywords() {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    /**
     * @return the selectedArticle
     */
    public Article getSelectedArticle() {
        return selectedArticle;
    }

    /**
     * @param selectedArticle the selectedArticle to set
     */
    public void setSelectedArticle(Article selectedArticle) {
        this.selectedArticle = selectedArticle;
    }
    
}

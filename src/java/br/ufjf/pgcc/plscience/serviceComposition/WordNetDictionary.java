/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.serviceComposition;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IVerbFrame;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author phillipe
 */
public class WordNetDictionary {

    /**
     * It loads the wordNet dictionary
     *
     * @return
     * @throws MalformedURLException
     */
    public static IDictionary loadDictionary() throws MalformedURLException {
        //String absolPath = "/home/phillipe/NetBeansProjects/plscience-ecos/web/files/WordNet-3.0/dict";

        URL location = WordNetDictionary.class.getProtectionDomain().getCodeSource().getLocation();
        String dictionaryPath = location.getPath();
        dictionaryPath = dictionaryPath.replace(File.separator + "build" + File.separator
                + "web" + File.separator + "WEB-INF" + File.separator + "classes" + File.separator, "");
        dictionaryPath += File.separator + "web" + File.separator + "files" + File.separator
                + "WordNet-3.0" + File.separator + "dict";

        //print the dictionary path
        //System.out.println("Path: "+dictionaryPath);
        URL url = new URL("file", null, dictionaryPath);
        // construct the dictionary object and open it
        IDictionary dict = new Dictionary(url);
        return dict;
    }

    /**
     * get Synonyms from a term
     *
     * @param dict
     * @param term
     */
    public List getSynonyms(IDictionary dict, String term) {
        List<IWord> associatedWordsList;
        List<String> synonynyms = new ArrayList<>();
        // look up first sense of the word " dog "
        IIndexWord idxWord = dict.getIndexWord(term, POS.NOUN);
        if (idxWord != null && idxWord.getWordIDs() != null
                && idxWord.getWordIDs().size() > 0) {
            IWordID wordID = idxWord.getWordIDs().get(0);
            IWord word = dict.getWord(wordID);
            associatedWordsList = word.getSynset().getWords();

            //printing associatedWords
            for (IWord w : associatedWordsList) {
                //System.out.println("Associated word: " + w.getLemma());
                synonynyms.add((String) w.getLemma());
            }
        }
        return synonynyms;
    }

    public List getHypernyms(IDictionary dict, String term) {
        List<String> hypernymsR = new ArrayList<>();
        IIndexWord idxWord = dict.getIndexWord(term, POS.NOUN);
        IWordID wordID = idxWord.getWordIDs().get(0);
        IWord word = dict.getWord(wordID);
        ISynset synset = word.getSynset();

        // get the hypernyms
        List< ISynsetID> hypernyms = synset.getRelatedSynsets(Pointer.HYPERNYM);
        // print out each hypernyms id and synonyms
        List< IWord> words;
        for (ISynsetID sid : hypernyms) {
            words = dict.getSynset(sid).getWords();
            //System.out.print(sid + " { ");
            for (Iterator< IWord> i = words.iterator(); i.hasNext();) {
                //System.out.print(i.next().getLemma());
                hypernymsR.add((String) i.next().getLemma());
                if (i.hasNext()) {
                    //System.out.print(" , ");
                }
            }
            //System.out.println(" } ");
        }
        return hypernymsR;
    }

    public void getVerbs(IDictionary dict, String term) {

        List<IVerbFrame> associatedVerbsList;

        // look up first sense of the word " dog "
        IIndexWord idxWord = dict.getIndexWord(term, POS.NOUN);
        IWordID wordID = idxWord.getWordIDs().get(0);
        IWord word = dict.getWord(wordID);

        associatedVerbsList = word.getVerbFrames();

        //printing associatedVerbs
        for (IVerbFrame v : associatedVerbsList) {
            System.out.println("Associated verb: " + v.getTemplate());
        }
    }

//    public static void main(String[] args) throws MalformedURLException, IOException {
//
//        IDictionary dict = loadDictionary();
//        dict.open();
//
////        getSynonyms(dict, "service");
////        getHypernyms(dict, "service");
//    }
}

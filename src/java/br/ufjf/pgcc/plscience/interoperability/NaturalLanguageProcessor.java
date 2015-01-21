/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.interoperability;

import hultig.sumo.Sentence;
import hultig.sumo.Text;
import hultig.sumo.Word;
import java.io.IOException;

/**
 *
 * @author Fran
 */
public class NaturalLanguageProcessor {
        
        public int stringSimilarityLeveshteinDistance(String a, String b){
            Word w1 = new Word(a);
            Word w2 = new Word(b);
            return w1.editDistance(w2);
        }
        
         public float stringSimilarityLexicalDistance(String a, String b){
            Word w1 = new Word(a);
            Word w2 = new Word(b);
            return w1.distlex(w2);
        }
         
          public float stringSimilarityLeveshteinAAndLexicalDistance(String a, String b){
            Word w1 = new Word(a);
            Word w2 = new Word(b);
            return w1.editDistance(w2)*w1.distlex(w2);
        }
         
         public double stringSimilarityMaxSequence(String a, String b){           
            return Word.distSeqMax(a,b);
        }
        
        public int sentenceSimilarityLeveshteinDistance(String a, String b){
            Sentence s1 = new Sentence(a);
            Sentence s2 = new Sentence(b);
            return s1.dsLevenshtein(s2);
        }
        
        public double sentenceSimilaritySumo(String a, String b){
            Sentence s1 = new Sentence(a);
            Sentence s2 = new Sentence(b);
            return s1.dsumo(s2);
        }
        
        public double sentenceSimilarityGauss(String a, String b){
            Sentence s1 = new Sentence(a);
            Sentence s2 = new Sentence(b);
            return s1.dgauss(s2);
        }
        public double sentenceSimilarityLinear(String a, String b){
            Sentence s1 = new Sentence(a);
            Sentence s2 = new Sentence(b);
            return s1.dLinear(s2);
        }
        
        public double sentenceSimilarityNgram(String a, String b){
            Sentence s1 = new Sentence(a);
            Sentence s2 = new Sentence(b);
            return Sentence.dsNgram(s1,s2);
        }
        
        public double sentenceSimilarityTrigonometric(String a, String b){
            Sentence s1 = new Sentence(a);
            Sentence s2 = new Sentence(b);
            return s1.dSin(s2);
        }
        
        public double textSimilarityLexical(String a, String b){
            Text t1 = new Text(a);
            Text t2 = new Text(b);
            return t1.similarity(t2);
        }
        

        
         public static void main(String[] args) throws IOException {
         NaturalLanguageProcessor nlp = new NaturalLanguageProcessor();
      
             System.out.println(nlp.stringSimilarityLeveshteinAAndLexicalDistance("Rodrigo Luis S. Silva", "Rodrigo Luis de Souza da Silva"));
             System.out.println(nlp.stringSimilarityLeveshteinAAndLexicalDistance("Rodrigo Luis S. Silva", "Rodrigo Silva de Santos"));
             System.out.println(nlp.sentenceSimilarityLeveshteinDistance("Uso da proteina 45", "Usando a proteina 45"));
             System.out.println(nlp.stringSimilarityLeveshteinAAndLexicalDistance("Proteina 45 usada", "Usando a proteina 45"));
             System.out.println(nlp.sentenceSimilarityLeveshteinDistance("Proteina 45 usada", "Usando a proteina 45"));
             System.out.println(nlp.sentenceSimilaritySumo("Proteina 45 usada", "Usando a proteina 45"));
             System.out.println(nlp.sentenceSimilarityGauss("Proteina 45 usada", "Usando a proteina 45"));
             System.out.println(nlp.sentenceSimilarityNgram("Proteina 45 usada", "Usando a proteina 45"));
             System.out.println(nlp.sentenceSimilarityLinear("Proteina 45 usada", "Usando a proteina 45"));
             System.out.println(nlp.sentenceSimilarityTrigonometric("Proteina 45 usada", "Usando a proteina 45"));
             
        
    }
    
}


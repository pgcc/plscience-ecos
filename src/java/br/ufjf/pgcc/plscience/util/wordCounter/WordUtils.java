/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.util.wordCounter;

import java.util.function.UnaryOperator;


public class WordUtils {
    
    public static WordCounts countWords(String text, CharPredicate pred) {
        return countWords(text, pred, null);
    }

    public static WordCounts countWords(String text, CharPredicate pred, UnaryOperator<String> op) {
        assert (text != null);
        WordCounts result = new WordCounts();
        int i = 0;
        while (i < text.length()) {
            while (i < text.length() && !pred.test(text.charAt(i))) {
                i++;
            }
            int bi = i;
            while (i < text.length() && pred.test(text.charAt(i))) {
                i++;
            }
            int ei = i;
            if (bi != ei) {
                String word = text.substring(bi, ei);
                if (op != null) {
                    word = op.apply(word);
                }
                if(word.length() >= 5)
                    result.add(word, 1);
            }
        }
        return result;
    }
    
    public static int getEndWordIndex(String text, CharPredicate pred) {
        int ei = text.length();
        while (ei > 0 && pred.test(text.charAt(ei - 1))) {
            ei--;
        }
        return ei;
    }
}
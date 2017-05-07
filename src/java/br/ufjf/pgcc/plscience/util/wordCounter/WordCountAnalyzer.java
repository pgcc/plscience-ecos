/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.util.wordCounter;

import java.util.Comparator;


/**
 *
 * @author Jonathan
 */
public class WordCountAnalyzer {

    private static final int THRESHOLD = 32 * 1024;
    
    private final WordCounts wc;
    private final boolean par;
    private final int parLevel;
    
    public WordCountAnalyzer(WordCounts wc, boolean par) {
        this(wc, par, ForkJoinComputer.DEFAULT_PAR_LEVEL);
    }
    
    public WordCountAnalyzer(WordCounts wc, boolean par, int parLevel) {
        if (wc == null) {
            throw new IllegalArgumentException("Word counts is null.");
        }
        this.wc = wc;
        this.par = par;
        this.parLevel = parLevel;
    }

    public TopWordCounts findTop(int number, Comparator<Integer> comparator) {
        return analyse(new FindTopAnalysis(number, comparator));
    }
    
    public int getTotal() {
        return analyse(new TotalAnalysis());
    }
    
    private <T> T analyse(Analysis<T> a) {
        if (par) {
            return new ForkJoinComputer<T>(wc.getSize(), THRESHOLD, a::compute, a::merge, parLevel).compute();
        } else {
            return a.compute(0, wc.getSize());
        }
    }
    
    interface Analysis<T> {
        T compute(int lo, int hi);
        
        T merge(T r1, T r2);
    }
    
    final class FindTopAnalysis implements Analysis<TopWordCounts> {

        private final int number;
        private final Comparator<Integer> comparator;

        FindTopAnalysis(int number, Comparator<Integer> comparator) {
            if (number < 0 || number > wc.getSize()) {
                throw new IllegalArgumentException("Number is negative or too big.");
            }
            if (comparator == null) {
                throw new IllegalArgumentException("Comparator is null.");
            }
            this.number = (number != 0) ? number : wc.getSize();
            this.comparator = comparator;
        }

        @Override
        public TopWordCounts compute(int lo, int hi) {
            TopWordCounts result = new TopWordCounts(number, comparator);
            wc.forEachInRange(lo, hi, (word, count) -> result.addIfNeeded(count, word));
            return result;
        }
        
        @Override
        public TopWordCounts merge(TopWordCounts r1, TopWordCounts r2) {
            r1.add(r2);
            return r1;
        }
    }
    
    final class TotalAnalysis implements Analysis<Integer> {

        @Override
        public Integer compute(int lo, int hi) {
            int[] result = new int[] { 0 };
            wc.forEachInRange(lo, hi, (word, count) -> { result[0] += count; });
            return result[0];
        }
        
        @Override
        public Integer merge(Integer r1, Integer r2) {
            return r1 + r2;
        }
    }
    
}

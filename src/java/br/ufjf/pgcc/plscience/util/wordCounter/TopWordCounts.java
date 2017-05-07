/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.util.wordCounter;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Jonathan
 */
public class TopWordCounts {
    
    private final int number;
    private final SortedMap<Integer, Set<String>> m;
    
    public TopWordCounts(int number, Comparator<Integer> comparator) {
        this.number = number;
        m = new TreeMap<>(comparator);
    }
    
    public SortedMap<Integer, Set<String>> getWordCounts() {
        return m;
    }
    
    public int getSize() {
        return m.size();
    }
    
    public void addIfNeeded(int count, String word) {
        if (m.size() < number || shouldInclude(count)) {
            add(count, word);
        }
    }

    public void add(int count, String word) {
        if (m.containsKey(count)) {
            m.get(count).add(word);
        } else {
            putWords(count, asSet(word));
        }
    }

    public void add(int count, Set<String> words) {
        if (m.containsKey(count)) {
            m.get(count).addAll(words);
        } else {
            putWords(count, words);
        }
    }

    public void add(TopWordCounts twc) {
        for (Map.Entry<Integer, Set<String>> e : twc.m.entrySet()) {
            add(e.getKey(), e.getValue());
        }
    }   
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TopWordCounts)) {
            return false;
        }
        TopWordCounts twc = (TopWordCounts) o;
        if (m.size() != twc.m.size()) {
            return false;
        }
        for (Map.Entry<Integer, Set<String>> e : twc.m.entrySet()) {
            Set<String> set = m.get(e.getKey());
            if (set == null) {
                return false;
            }
            Set<String> setx = e.getValue();
            if (set.size() != setx.size()) {
                return false;
            }
            for (String s : setx) {
                if (!set.contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 89 * this.number + Objects.hashCode(this.m);
    }
    
    private boolean shouldInclude(int count) {
        return m.comparator().compare(count, m.lastKey()) <= 0;
    }
    
    private void putWords(int count, Set<String> words) {
        m.put(count, words);
        if (m.size() > number) {
            m.remove(m.lastKey());
        }
    }

    private static Set<String> asSet(String word) {
        Set<String> set = new HashSet<>();
        set.add(word);
        return set;
    }
}

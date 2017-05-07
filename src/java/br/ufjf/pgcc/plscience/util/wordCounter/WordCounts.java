/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.util.wordCounter;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;


public class WordCounts {
    
    private final Map<String, AtomicInteger> m;
    
    public WordCounts() {
        this(1);
    }
    
    public WordCounts(int parLevel) {
        this.m = (parLevel == 1) ? new HashMap<String, AtomicInteger>() : 
            new ConcurrentHashMap<String, AtomicInteger>(4096, 0.75f, parLevel);
    }
    
    public int getSize() {
        return m.size();
    }
    
    public Map<String, AtomicInteger> getWordCounts(){
        return m;
    }
    
    public void add(String word, int count) {
        AtomicInteger cc = m.get(word);
        if (cc != null) {
            cc.addAndGet(count);
        } else {
            if (m instanceof ConcurrentMap) {
                cc = ((ConcurrentMap<String, AtomicInteger>) m).putIfAbsent(word, new AtomicInteger(count));
                // Another thread might have added the same value in the meantime
                if (cc != null) {
                    cc.addAndGet(count);
                }
            } else {
                m.put(word, new AtomicInteger(count));
            }
        }
    }

    public void add(WordCounts wc) {
        for (Map.Entry<String, AtomicInteger> e : wc.m.entrySet()) {
            add(e.getKey(), e.getValue().get());
        }
    }
    
    public void set(String word, int count) {
        AtomicInteger cc = m.get(word);
        if (cc != null) {
            cc.set(count);
        } else {
            if (m instanceof ConcurrentMap) {
                cc = ((ConcurrentMap<String, AtomicInteger>) m).putIfAbsent(word, new AtomicInteger(count));
                // Another thread might have added the same value in the meantime
                if (cc != null) {
                    cc.set(count);
                }
            } else {
                m.put(word, new AtomicInteger(count));
            }
        }
    }
    
    public void forEachInRange(int lo, int hi, BiConsumer<String, Integer> block) {
        Iterator<Map.Entry<String, AtomicInteger>> it = m.entrySet().iterator();
        for (int i = 0; i < lo; i++) {
            it.next();
        }
        for (int i = lo; i < hi; i++) {
            Map.Entry<String, AtomicInteger> e = it.next();
            block.accept(e.getKey(), e.getValue().get());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WordCounts)) {
            return false;
        }
        WordCounts wc = (WordCounts) o;
        if (m.size() != wc.m.size()) {
            return false;
        }
        for (Entry<String, AtomicInteger> e : wc.m.entrySet()) {
            if (m.get(e.getKey()).get() != e.getValue().get()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(m);
    }
    
    Set<Entry<String, AtomicInteger>> getEntries() {
        return m.entrySet();
    }
}
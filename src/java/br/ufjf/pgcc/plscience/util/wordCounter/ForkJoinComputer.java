/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.plscience.util.wordCounter;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author Jonathan
 */


public class ForkJoinComputer<T> {
    
    public static final int DEFAULT_PAR_LEVEL = Runtime.getRuntime().availableProcessors();
    
    public interface Computer<T> {
        T compute(int lo, int hi);
    }
    
    public interface Merger<T> {
        T merge(T result1, T result2);
    }
    
    private final int size;
    private final int threshold;
    private final Computer<T> computer;
    private final Merger<T> merger;
    private final int parLevel;
    private final ForkJoinPool forkJoinPool;
    
    public ForkJoinComputer(int size, int threshlod, Computer<T> computer, Merger<T> merger) {
        this(size, threshlod, computer, merger, DEFAULT_PAR_LEVEL);
    }

    public ForkJoinComputer(int size, int threshlod, Computer<T> computer, Merger<T> merger, 
        int parLevel) {
        if (computer == null || merger == null) {
            throw new NullPointerException();
        }
        this.size = size;
        this.threshold = threshlod;
        this.computer = computer;
        this.merger = merger;
        this.parLevel = parLevel;
        this.forkJoinPool = new ForkJoinPool(parLevel);
    }

    public T compute() {
        return forkJoinPool.invoke(new Task(0, size));
    }

    @SuppressWarnings("serial")
    private final class Task extends RecursiveTask<T> {

        private final int lo;
        private final int hi;

        Task(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected T compute() {
            T result;
            if (hi - lo <= Math.max(size / parLevel, threshold)) {
                result = computer.compute(lo, hi);
            } else {
                int mid = (lo + hi) >>> 1;
                Task t1 = new Task(lo, mid);
                t1.fork();
                Task t2 = new Task(mid, hi);
                T r2 = t2.compute();
                T r1 = t1.join();
                result = merger.merge(r1, r2);
            }
            return result;
        }
    }
}
